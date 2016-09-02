package model;

import java.util.UUID;
public abstract class AbstractModel {
	private UUID uuid;
	@Override 
	public int hashCode () {
		return 0;
	}   
	@Override 
	public boolean equals (final Object obj) {
		return false;
		
	}
    public UUID getUuid () {
    	if (uuid == null) {
    		uuid = UUID.randomUUID (); 
    	}
    	return uuid; 
    }
}
