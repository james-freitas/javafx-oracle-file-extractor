package com.codeonblue.tools.ofx.model;

/**
 * 
 * Domain class responsible for storing the Query Extraction Data input by the user
 * and make it available to the rest of the application.
 * 
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 * 
 */
public class QueryExtractionData {

	private String tableName;
	private String fileNameColumn;
	private String fileContentColumn;
	private String orderByColumn;
	private String outputFolder;
	

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getFileNameColumn() {
		return fileNameColumn;
	}
	public void setFileNameColumn(String fileNameColumn) {
		this.fileNameColumn = fileNameColumn;
	}
	public String getFileContentColumn() {
		return fileContentColumn;
	}
	public void setFileContentColumn(String fileContentColumn) {
		this.fileContentColumn = fileContentColumn;
	}
	public String getOrderByColumn() {
		return orderByColumn;
	}
	public void setOrderByColumn(String orderByColumn) {
		this.orderByColumn = orderByColumn;
	}
	public String getOutputFolder() {
		return outputFolder;
	}
	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}
	
	
}


