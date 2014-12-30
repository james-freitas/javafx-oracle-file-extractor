package com.codeonblue.tools.ofx.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.codeonblue.tools.ofx.model.QueryExtractionData;

/**
 * 
 * Class responsible for the data access through JDBC queries that get the file
 * name and the content columns.
 * 
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 * 
 */

public class TableDAO {

	public ResultSet getFileTitles(Connection connection,
			QueryExtractionData queryExtractionData) throws SQLException {
		String sql = String.format("SELECT %s FROM %s ORDER BY %s",
				queryExtractionData.getFileNameColumn(),
				queryExtractionData.getTableName(),
				queryExtractionData.getOrderByColumn());
		Statement stmt = connection.createStatement();
		ResultSet resultado = stmt.executeQuery(sql);
		return resultado;
	}

	public ResultSet getStreamingValues(Connection connection,
			QueryExtractionData queryExtractionData) throws SQLException {
		String sql = String.format("SELECT %s FROM %s ORDER BY %s",
				queryExtractionData.getFileContentColumn(),
				queryExtractionData.getTableName(),
				queryExtractionData.getOrderByColumn());
		Statement stmt = connection.createStatement();
		ResultSet resultado = stmt.executeQuery(sql);
		return resultado;
	}

}
