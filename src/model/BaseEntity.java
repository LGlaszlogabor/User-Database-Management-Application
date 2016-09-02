package model;

import java.io.Serializable;

public class BaseEntity extends AbstractModel implements Serializable {
	private static final long serialVersionUID = 1L; 
	private Long id;
	public BaseEntity () {
		this (null); 
		}
	public BaseEntity (final Long id) { 
		super (); 
		this.id = id; 
	}
	public Long getId () {
		return id; 
		}
	public void setId (final Long id) { 
		this.id = id;
	}
}

