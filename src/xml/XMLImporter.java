package xml;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import model.User;

public class XMLImporter {
	private final File file;
	private Document dom; 
	private List<User> users;
	
	public XMLImporter (final File file) {
		this.file = file;
	}
	
	private void buildDOM () { 
		dom = new Document (); 
		final SAXBuilder builder = new SAXBuilder ();
		try { 
			dom = builder.build (file);
		} catch (JDOMException | IOException ex) {
			ex.printStackTrace ();
		}
	} 
	
	private void buildUserList() { 
		users = new ArrayList<User>();
		final Element root = dom.getRootElement();
		final Namespace ns = root.getNamespace();
		final List<Element> liveList = root.getChildren ();
		for (final Element e : liveList) { 
			final User u = new User();
			u.setFirstName(e.getChildText("first_name", ns));
			u.setLastName(e.getChildText("last_name",ns));
			u.setUserName(e.getChildText("user_name",ns));
			u.setEmail(e.getChildText("email",ns));
			u.setPassword(e.getChildText("password",ns));
			u.setAdress(e.getChildText("adress",ns));
			users.add(u);
		}
	}
	
	public List<User> getUserList() { 
		buildDOM();
		buildUserList(); 
		return users; 
	}
}