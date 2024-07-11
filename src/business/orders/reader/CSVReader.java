package business.orders.reader;

import business.orders.assembler.PedidoAssembler;

import business.util.Reader;
import config.Conf;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


public class CSVReader extends Reader {
    public void parsePedidos() {
        parseCSV(Conf.getInstance().getProperty("csv.big.path"));
    }

    /*
     * Recupera datos de pedidos desde un CSV indicado por el parametro path, lee linea a linea y lo incluye en base de datos
     * Salta la primera linea del archivo, ya que incluye las cabeceras
     * @param String path, la ruta del fichero */
    private void parseCSV(String path){
        try(BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            List<String[]> orders = reader.lines()
                    .map(line -> line.split(","))
                    .skip(1)
                    .collect(Collectors.toList());

            orders.stream().parallel().forEach(order->
                addPedido(PedidoAssembler.fromCSV(order))
            );
        }catch (IOException ioException){
          throw new RuntimeException(ioException);
        }
    }
}
