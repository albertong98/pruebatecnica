package business.util;

import business.orders.writer.CSVFileWriter;

public class WriterFactory {

    public static Writer forCsv(){
        return new CSVFileWriter();
    }
}
