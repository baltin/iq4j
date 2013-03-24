package com.iq4j.persistence.impl;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.iq4j.utils.model.Definition;

@MappedSuperclass
public abstract class DefinitionEntity extends EntityImpl<String> implements Definition {

	private static final long serialVersionUID = 775054682063090542L;

	@Id
	@Column(name="id", unique=true, nullable = false, updatable = false, length=10 )
	@Access(AccessType.PROPERTY)
	private String id;
	
	@Column(nullable = false, unique = true, length = 100, name = "name")
	private String name;
	
	@Override
	@NotBlank
	@Length(min = 1, max = 10)
	public String getId() {
		return id;
	}

	@Override
	public void setId(String id) {
		if(this.id == null && id != null) {			
			this.id = id;
		}
	}
	
	@NotBlank
	@Size(min = 3, max = 100)
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name; 
	}

	public String getCode() {
		return getName();
	}

}
