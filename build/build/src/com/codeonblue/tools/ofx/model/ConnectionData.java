package com.codeonblue.tools.ofx.model;

/**
 * 
 * Domain class responsible for storing the Connection Data input by the user
 * and make it available to the rest of the application.
 * 
 * @author James Daniel Correia de Freitas
 * @since 26/12/2014
 * 
 */
public class ConnectionData {
	private String oracleServer;
	private String serverName;
	private String instance;
	private String port;
	private String username;
	private String password;

	public ConnectionData() {
	}

	public String getOracleServer() {
		return oracleServer;
	}

	public void setOracleServer(String oracleServer) {
		this.oracleServer = oracleServer;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getInstance() {
		return instance;
	}

	public void setInstance(String instance) {
		this.instance = instance;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}