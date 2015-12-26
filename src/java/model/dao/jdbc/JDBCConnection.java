/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * responsible for accessing JDBC connection pool
 * 
 * @author andre
 */
final class JDBCConnection {
    /**
     * singleton instance
     */
    final private static JDBCConnection instance = new JDBCConnection();
    final private static String JDBC_CONNECTION_POOL = "jdbc/Commisiondb";

    private JDBCConnection() {
        if (instance != null) {
            throw new UnsupportedOperationException("can't instantiate");
        }
    }

    /**
     * 
     * @return singleton instance
     */
    final static JDBCConnection getInstance() {
        return instance;
    }

    /**
     * create or get connection to connection pool
     * 
     * @return connection to pool or null if cannot be created
     */
    final static Connection getConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup(JDBC_CONNECTION_POOL);
            return ds.getConnection();
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(JDBCConnection.class.getName()).log(Level.SEVERE, null, ex);
            //ex.printStackTrace();
        }
        return null;
    }

}
