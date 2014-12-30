package com.codeonblue.tools.ofx.logic;

import java.sql.Connection;
import java.util.List;

import com.codeonblue.tools.ofx.model.QueryExtractionData;

/**
 * 
 * Interface for extracting the file names and their content from the respective
 * table columns
 * 
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 */
public interface Extractable {

	boolean getFilesFromRows(Connection connection, List<String> fileNames,
			QueryExtractionData queryExtractionData, String outputFolder);

	List<String> getFileNames(Connection connection,
			QueryExtractionData queryExtractionData);

}