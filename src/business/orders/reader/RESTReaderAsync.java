package business.orders.reader;

import business.orders.assembler.PedidoAssembler;
import business.util.Reader;

import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.ExecutionException;
import java.util.stream.IntStream;

import config.Conf;

import javax.json.*;


public class RESTReaderAsync extends Reader {
    private final int MAX_CALLS = 1000;
    /*
     * Recupera datos de pedidos desde la API REST y los incluye en base de datos de manera paralela
     *
     * */
    @Override
    public void parsePedidos(){
        IntStream.range(0, MAX_CALLS)
                .parallel()
                .forEach(i -> {
                    try {
                        executeRequest(Conf.getInstance().getProperty("api.base.url") + "orders?page="+(i+1)+"&max-per-page=1000");
                    } catch (ExecutionException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
    }
    /*
     * Ejecuta las peticiones a la API y llama al metodo responsable del tratamiento del JSON
     *
     * @param String uri, la direccion de la API */
    private void executeRequest(String uri) throws ExecutionException, InterruptedException {
        System.out.println(uri);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .timeout(Duration.ofMinutes(1))
                .build();

        HttpClient client = HttpClient.newBuilder()
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(this::parseJson).get();
    }

    /*
     * A partir de un String, construye un JSON array desde el que se recuperan los pedidos y se añaden a base de datos
     *
     * @param String jsonString, cadena que representa el objeto JSON recuperado */
    private void parseJson(String jsonString){
        JsonReader jsonReader = Json.createReader(new StringReader(jsonString));

        JsonObject response = jsonReader.readObject();
        JsonArray jsonArray = response.get("content").asJsonArray();
        jsonArray.stream().parallel().forEach(item -> {
            this.addPedido(PedidoAssembler.fromJsonValue(item.asJsonObject()));
        });
        //return response.get("links").asJsonObject().getString("next");
    }
}
