package repository;

import java.util.List;

import model.User;

public interface UserDAO {
	List<User> getAllUsers () throws RepositoryException;
	User getUserById (Long id) throws RepositoryException;
	List<User> getUserByFilter (String pattern) throws RepositoryException;
	void insertUser (User user) throws RepositoryException;
	void updateUser (User user) throws RepositoryException;
	void deleteUser (User user) throws RepositoryException;
}
