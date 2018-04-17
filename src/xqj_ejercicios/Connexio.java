package xqj_ejercicios;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;

import net.xqj.exist.ExistXQDataSource;

public class Connexio {
	XQDataSource server;
	public Connexio() throws XQException{
		server = new ExistXQDataSource();
		//Inicialitzem les propietats de la connexió
		server.setProperty("serverName","localhost");
		server.setProperty("port","8080");
		server.setProperty("user","admin");
		server.setProperty("password","admin");
	}
	public XQConnection getConnection() throws XQException{
		return server.getConnection();
	}

}
