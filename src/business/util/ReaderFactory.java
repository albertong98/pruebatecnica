package business.util;

import business.orders.reader.CSVReader;
import business.orders.reader.RESTReaderAsync;

public class ReaderFactory {
    public static Reader getRestReader(){
        return new RESTReaderAsync();
    }
    public static Reader getCSVReader(){
        return new CSVReader();
    }
}
