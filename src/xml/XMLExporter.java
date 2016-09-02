package xml;

import java.io.File; 
import java.io.FileNotFoundException;
import java.io.FileOutputStream; 
import java.io.IOException; 
import java.util.ArrayList; 
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element; 
import org.jdom2.Namespace;
import org.jdom2.output.Format; 
import org.jdom2.output.XMLOutputter;
import model.User;

public class XMLExporter {
	private List<User> users; 
	private Document   dom;
	private Namespace  bsbNameSpace;
	public XMLExporter () {
		this (new ArrayList<User> ()); 
	}
	
	public XMLExporter (final List<User> users) { 
		this.users = users; 
		buildDOM(); 
	}
	
	public List<User> getUsers () { 
		return users; 
	}
	
	public void setUsers (final List<User> users) { 
		this.users = users;
	}
	
	public void exportData (final File f) { 
		final XMLOutputter writer = new XMLOutputter();
		writer.setFormat(Format.getPrettyFormat());
		try {
			writer.output(dom, new FileOutputStream(f));
		} catch (final FileNotFoundException ex) { 
			ex.printStackTrace(); 
		} catch (final IOException ex) { 
			ex.printStackTrace(); 
		} 
	}
	
	private void buildDOM () { 
		dom = new Document();
		final Element rootElement = new Element ("users");
		bsbNameSpace = Namespace.getNamespace ("model/User"); 
		rootElement.setNamespace(bsbNameSpace); 
		dom.setRootElement (rootElement);
		for (final User u : users) { 
			rootElement.addContent(createUserElement(u)); 
			} 
		}
	
	private Element createUserElement (final User u) { 
		final Element userElement = new Element("user", bsbNameSpace);
		final Element firstNameElement = new Element("first_name", bsbNameSpace);
		firstNameElement.addContent(u.getFirstName());
		userElement.addContent(firstNameElement);
		final Element lastNameElement = new Element("last_name", bsbNameSpace);
		lastNameElement.addContent(u.getLastName());
		userElement.addContent(lastNameElement);
		final Element userNameElement = new Element("user_name", bsbNameSpace); 
		userNameElement.addContent(u.getUserName());
		userElement.addContent(userNameElement);
		final Element emailElement = new Element("email", bsbNameSpace);
		emailElement.addContent(u.getEmail());
		userElement.addContent(emailElement); 
		final Element passwordElement = new Element("password", bsbNameSpace);
		passwordElement.addContent(u.getPassword());
		userElement.addContent(passwordElement);
		final Element adressElement = new Element("adress",bsbNameSpace);
		adressElement.addContent(u.getAdress());
		userElement.addContent(adressElement);
		return userElement; 
	} 
}

