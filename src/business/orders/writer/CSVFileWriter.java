package business.orders.writer;

import business.util.CommandExecutor;
import business.util.Writer;
import business.orders.PedidoDTO;
import business.orders.assembler.PedidoAssembler;
import business.orders.command.FindAll;
import config.BasicConnectionPool;
import config.Jdbc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVFileWriter implements Writer {
    private CommandExecutor executor;
    /*
     * Escribe un archivo CSV con todos los pedidos recuperados a partir de base de datos
     *
     * @param String uri, la direccion de la API */
    @Override
    public void writeFile() {
        BasicConnectionPool pool;
        ResultSet rs = null;
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("pedidos.csv"));){
            pool = BasicConnectionPool.create();
            executor = new CommandExecutor(pool);
            rs = executor.execute(new FindAll());
            while(rs.next()){
                PedidoDTO dto = PedidoAssembler.fromResultSet(rs);
                writer.write(dto.toCsvString() + "\n");
            }
            Jdbc.close(rs);
            pool.shutdown();
        }catch (IOException ioException){
            throw new RuntimeException(ioException);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
