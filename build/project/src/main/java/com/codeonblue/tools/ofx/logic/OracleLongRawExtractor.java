package com.codeonblue.tools.ofx.logic;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.codeonblue.tools.ofx.dao.TableDAO;
import com.codeonblue.tools.ofx.model.QueryExtractionData;

/**
 * 
 * Class that has a concrete implementation of Extractable interface that
 * extracts the file names and their content from the respective table columns.
 * It aims for Oracle Long Raw column type.
 * 
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 * 
 */
public class OracleLongRawExtractor implements Extractable {

	private TableDAO dao;

	public OracleLongRawExtractor() {
		this.dao = new TableDAO();
	}

	@Override
	public List<String> getFileNames(Connection connection,
			QueryExtractionData queryExtractionData) {
		List<String> fileNames = new ArrayList<String>();

		try {
			ResultSet resultado = dao.getFileTitles(connection,
					queryExtractionData);

			while (resultado.next()) {
				fileNames.add(resultado.getString(1));
			}

		} catch (Exception e) {
			System.out.println("Error ocurred when executing query: " + e.getMessage());
			return null;
		}
		return fileNames;
	}

	@Override
	public boolean getFilesFromRows(Connection connection, List<String> fileNames,
			QueryExtractionData queryExtractionData, String outputFolder) {
		try {
			int counter = 0;

			ResultSet resultado = dao.getStreamingValues(connection,
					queryExtractionData);

			while (resultado.next()) {

				int counterToShow = counter + 1;
				System.out.println("Extracting file " + counterToShow + " : "
						+ fileNames.get(counter) + "...");

				// Get the data as a stream from Oracle to the client
				InputStream rawData = resultado
						.getBinaryStream(queryExtractionData
								.getFileContentColumn());

				String refinedOutputFolder = outputFolder.replaceAll("/", "//");
				System.out.println(refinedOutputFolder + "\\" + counter + "-"
						+ fileNames.get(counter));
				FileOutputStream file = null;
				file = new FileOutputStream(refinedOutputFolder + "\\"
						+ counter + "-" + fileNames.get(counter));

				int chunk;
				while ((chunk = rawData.read()) != -1)
					file.write(chunk);

				if (file != null) {
					file.close();
				}

				counter++;

			}
			System.out.println("\n");
			System.out.println("Total of " + counter + " extracted file(s).");
		} catch (Exception e) {
			System.out.println("Error ocurred when executing query: " + e.getMessage());
			return false;
		}
		return true;
	}

}
