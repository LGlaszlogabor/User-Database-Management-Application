package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

import org.slf4j.Logger;

import common.PropertyProvider;
import repository.RepositoryException;

public final class ConnectionManager {
	private static final int SIZE = 2;
	private final LinkedList<Connection> pool;
	private static ConnectionManager instance;
	private static Logger logger;
	private ConnectionManager () { 
		pool = new LinkedList<Connection> (); 
		initializePool ();
	}
	public synchronized static ConnectionManager getInstance () { 
		if (instance == null) { 
			instance = new ConnectionManager (); 
		} 
		return instance; 
	}
	public synchronized Connection getConnection () throws RepositoryException {
		Connection con = null; 
		if (pool.size () > 0) { 
			con = pool.get (0); 
			pool.remove (0); 
		} if (con == null) { 
			throw new RepositoryException ("No connections in pool"); 
		} 
		return con; 
	}
	public synchronized void returnConnection (final Connection con) {
		if (pool.size () < SIZE) {
			pool.add (con); 
		} 
	}
	private void initializePool () { 
		try { 
			Class.forName("com.mysql.jdbc.Driver");
			String url = PropertyProvider.INSTANCE.getProperty("dbURL"); 
			Connection con = DriverManager.getConnection(url, PropertyProvider.INSTANCE.getProperty("dbUser"), "");
			pool.add(con);
		}
		catch(ClassNotFoundException | SQLException e){
			logger.error("Conn manager error!");
		}
	}
}
