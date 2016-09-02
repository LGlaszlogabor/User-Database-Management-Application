package common;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.User;
import repository.DAOFactory;
import repository.UserDAO;

public class UserTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "First Name","Last Name","User Name","Email","Password","Adress"};
	private List<User> userList;
	
	public void setColumnNames(String s1,String s2,String s3,String s4,String s5,String s6){
		columnNames[0] = s1;
		columnNames[1] = s2;
		columnNames[2] = s3;
		columnNames[3] = s4;
		columnNames[4] = s5;
		columnNames[5] = s6;
	}
	
	public UserTableModel() {
		DAOFactory df = DAOFactory.getInstance(); 
		UserDAO bd = df.getUserDAO(); 
		userList = bd.getAllUsers();
	}

	public int getColumnCount() {
		return columnNames.length;
	}
	
	public User getRow(int row){
		return userList.get(row);
	}

	public int getRowCount() {
		return userList.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
	    User user = userList.get(row);
		switch(col){
	    	case 0:
	    		return user.getFirstName();
	    	case 1:
	    		return user.getLastName();
	    	case 2:
	    		return user.getUserName();
	    	case 3:
	    		return user.getEmail();
	    	case 4:
	    		return user.getPassword();
	    	case 5:
	    		return user.getAdress();
	    	case 6:
	    		return user.getId();
	    	default:
	    		return "";
		}
	}

	public Class<? extends Object> getColumnClass(int c) {
	    return getValueAt(0, c).getClass();
	}

	public boolean isCellEditable(int row, int col) {
	    if (col < 2) {
	    	return false;
	    } else {
	        return true;
	    }
	}

	public void setValueAt(Object value, int row, int col) {
		 User user = userList.get(row);
			switch(col){
		    	case 1:
		    		user.setFirstName((String) value);
		    	case 2:
		    		user.setLastName((String) value);
		    	case 3:
		    		user.setUserName((String) value);
		    	case 4:
		    		user.setEmail((String) value);
		    	case 5:
		    		user.setPassword((String) value);
		    	case 6:
		    		user.setAdress((String) value);
		    	default:
		    }
	    fireTableCellUpdated(row, col);
	}
	
	public void update(){
		DAOFactory df = DAOFactory.getInstance(); 
		UserDAO bd = df.getUserDAO(); 
		userList = bd.getAllUsers();
	}
	
	public void updateModel(List<User> list){
		userList = list;
	}
	
	public List<User> getUserList(){
		return userList;
	}
}
