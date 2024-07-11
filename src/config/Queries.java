package config;

import java.io.IOException;
import java.util.Properties;

public class Queries {
    private Properties queries;
    private static Queries instance = null;
    private static final String FILE_QUERIES = "queries.properties";

    private Queries() {
        this.queries = new Properties();
        try {
            queries.load(Queries.class.getClassLoader().getResourceAsStream(FILE_QUERIES));
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo de propiedades", e);
        }
    }
    public static Queries getInstance() {
        if (instance == null) {
            instance = new Queries();
        }
        return instance;
    }

    public String getQuery(String key) {
        String value = queries.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Query no encontrada");
        }
        return value;
    }
}
