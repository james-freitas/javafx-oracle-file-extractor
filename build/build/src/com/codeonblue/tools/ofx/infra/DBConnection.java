package com.codeonblue.tools.ofx.infra;

import java.sql.Connection;

import com.codeonblue.tools.ofx.model.ConnectionData;

import oracle.jdbc.pool.OracleDataSource;

/**
 * 
 * Class responsible for the connection using JDBC 
 *  
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 * 
 */
public class DBConnection {

	public Connection getConnection(ConnectionData connectionData) {
		// Connection String example jdbc:oracle:thin:@lanceta:1521:d01 or jdbc:oracle:thin:@localhost:1521:xe 
		Connection connection = null;
		String url = String.format("jdbc:oracle:thin:@%s:%s/%s",
				connectionData.getOracleServer(), connectionData.getPort(),
				connectionData.getInstance());
		String user = connectionData.getUsername();
		String password = connectionData.getPassword();

		try {
			OracleDataSource ds = new OracleDataSource();
			ds.setURL(url);
			connection = ds.getConnection(user, password);
		} catch (Exception e) {
			System.out.println("Connection error: " + e.getMessage());
		}
		return connection;
	}
}
