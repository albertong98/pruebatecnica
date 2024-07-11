import business.util.Reader;
import business.util.ReaderFactory;
import business.util.Writer;
import business.util.WriterFactory;

import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        System.out.println("\n\t1. Recuperar datos desde API REST");
        System.out.println("\t2. Recuperar datos desde archivo CSV");

        System.out.print("\nSeleccione el origen de los datos: ");
        Scanner scanner = new Scanner(System.in);

        Integer seleccion = scanner.nextInt();

        while (seleccion != 1 && seleccion != 2){
            System.out.print("ERROR. Introduzca una opción válida: ");
            seleccion = scanner.nextInt();
        }

        Reader reader = seleccion == 1 ? ReaderFactory.getRestReader() : ReaderFactory.getCSVReader();
        long start = System.currentTimeMillis();

        Map<String,Map<String,Integer>> resumen = reader.readPedidos();
        resumen.forEach((key,map) -> {
            System.out.println("\n"+key+": ");
            map.forEach((k,v) -> System.out.println("\t"+k+": "+v));
        });

        Writer writer = WriterFactory.forCsv();
        writer.writeFile();

        long finish = System.currentTimeMillis();
        long timeElapsed = finish - start;
        System.out.println("\nTiempo de ejecucion: "+(timeElapsed/1000.0)/60.0+" minutos" );
    }
}