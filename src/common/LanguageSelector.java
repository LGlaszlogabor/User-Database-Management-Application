package common;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageSelector {
	private Locale locale;
	private ResourceBundle uiMessages;
	
	public LanguageSelector() {
		try {
			locale = new Locale(PropertyProvider.INSTANCE.getProperty("defaultLanguage"),PropertyProvider.INSTANCE.getProperty("defaultRegion"));
		} catch (Exception e) {
			locale = Locale.getDefault();
		} 
		uiMessages = ResourceBundle.getBundle("UIMessageBundle", locale); 
	}
	
	public void changeLanguage(String s1,String s2){
		locale = new Locale(s1,s2);
		uiMessages = ResourceBundle.getBundle("UIMessageBundle", locale); 
	}
	
	public String getLanguage(){
		return uiMessages.getString("language")+":";
	}
	
	public String getFilter(){
		return uiMessages.getString("filter")+":";
	}
	
	public String getSearch(){
		return uiMessages.getString("search");
	}
	
	public String getInsert(){
		return uiMessages.getString("insert");
	}
	
	public String getUpdate(){
		return uiMessages.getString("update");
	}
	
	public String getDelete(){
		return uiMessages.getString("delete");
	}
	
	public String getExit(){
		return uiMessages.getString("exit");
	}
	
	public String getFirstName(){
		return uiMessages.getString("firstName")+":";
	}
	
	public String getLastName(){
		return uiMessages.getString("lastName")+":";
	}
	
	public String getUserName(){
		return uiMessages.getString("userName")+":";
	}
	
	public String getPassword(){
		return uiMessages.getString("password")+":";
	}
	
	public String getAdress(){
		return uiMessages.getString("adress")+":";
	}
	
	public String getSave(){
		return uiMessages.getString("save");
	}
	
	public String getCancel(){
		return uiMessages.getString("cancel");
	}
	
	public String getHu(){
		return uiMessages.getString("hu");
	}
	
	public String getRo(){
		return uiMessages.getString("ro");
	}
	
	public String getEn(){
		return uiMessages.getString("en");
	}
	
	public String getErrSave(){
		return uiMessages.getString("errsave");
	}
	
	public String getErrUpd(){
		return uiMessages.getString("errupd");
	}
	
	public String getErrIns(){
		return uiMessages.getString("errins");
	}
	
	public String getLoadButton(){
		return uiMessages.getString("loadButton");
	}
	
	public String getExportButton(){
		return uiMessages.getString("exportButton");
	}
	
	public String getImportButton(){
		return uiMessages.getString("importButton");
	}
	
	public String getErrLoad(){
		return uiMessages.getString("errload");
	}
}
