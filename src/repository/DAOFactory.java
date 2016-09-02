package repository;

import jdbc.JdbcDAOFactory;

public abstract class DAOFactory {
	public static DAOFactory getInstance () { 
		return new JdbcDAOFactory (); 
	}
	public abstract UserDAO getUserDAO ();
}
