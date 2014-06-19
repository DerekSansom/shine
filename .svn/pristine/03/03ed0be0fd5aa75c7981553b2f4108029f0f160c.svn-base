package com.sp.entity.admin;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "companies")
public class CompanyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	
	@Column(name = "category")
	private int category;

	@Column(name = "competitor")
	private int competitor;

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public int getCompetitor() {
		return competitor;
	}

	public void setCompetitor(int competitor) {
		this.competitor = competitor;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDate_created(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
