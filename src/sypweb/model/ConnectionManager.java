package sypweb.model;

import java.sql.Connection;
import org.postgresql.ds.PGPoolingDataSource;

public class ConnectionManager {
    //INICIO SINGLETON
    private static ConnectionManager instance = null;

    private ConnectionManager() {
        source = new PGPoolingDataSource();
        source.setDataSourceName("sypseguros");
        source.setServerName("localhost");
        source.setPortNumber(5432);
        source.setDatabaseName("sypseguros");
        source.setUser("postgres");
        source.setPassword("si2014syp");
        source.setMaxConnections(200);
        source.setInitialConnections(20);
        source.setTcpKeepAlive(true);
    }

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
        //mantem apenas uma instancia em memoria
    }
    //FIM SINGLETON
    private PGPoolingDataSource source;

    public Connection getConnection() throws Exception {
        Connection conn = source.getConnection();
        conn.setAutoCommit(false);
        return conn;
        //seta o autocommit para false, ou seja, em cada alteração do banco deve-se usar commit ou rollback
    }
}