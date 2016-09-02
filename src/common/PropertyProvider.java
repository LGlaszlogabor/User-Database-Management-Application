package common;

import java.util.MissingResourceException; 
import java.util.ResourceBundle;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;

public enum PropertyProvider { 
	INSTANCE;
	private static final Logger LOG = LoggerFactory.getLogger (PropertyProvider.class); 
	private static final String BUNDLE_NAME = "Properties";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle (BUNDLE_NAME);
	public String getProperty (final String key) {
		try { 
			return RESOURCE_BUNDLE.getString (key); 
		} catch (final MissingResourceException e) {
			LOG.error ("Missing resource", e);
			throw e; 
		} 
	}
}
