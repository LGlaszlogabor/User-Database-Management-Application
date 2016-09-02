package service;

import java.util.List;

import common.PasswordEncrypter;
import model.User;
import repository.DAOFactory;
import repository.UserDAO;

public class DatabaseService {
	private DAOFactory df = DAOFactory.getInstance();
	private UserDAO bd = df.getUserDAO(); 
	
	private static final DatabaseService INSTANCE = new DatabaseService();

    private DatabaseService() {}

    public static DatabaseService getInstance() {
        return INSTANCE;
    }
	
	public void insertUser(User user){
		bd.insertUser(new User(user.getFirstName(),user.getLastName(),user.getUserName(),user.getEmail(),
				PasswordEncrypter.generateHashedPassword (user.getPassword(),""),user.getAdress()));
	}
	
	public void deleteUser(User user){
		bd.deleteUser(user);
	}
	
	public List<User> getAllUsers(){
		return bd.getAllUsers();
	}
	
	public List<User> getUsersByFilter(String filter){
		return bd.getUserByFilter(filter);
	}
	
	public void updateUser(User user){
		bd.updateUser(new User(user.getFirstName(),user.getLastName(),user.getUserName(),user.getEmail(),
				PasswordEncrypter.generateHashedPassword (user.getPassword(),""),user.getAdress()));
	}
}
