package com.etender.SecurityRole.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//
@Entity
@Table(name="downloads")
public class DownloadEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private Integer id;
	
	@Column (name="title")
	private String title;
	
	@Column (name="seo_title")
	private String seo_title;
	
	@Column (name="description")
	private String description;
	
	@Column (name="author")
	private String author;
	
	@Column (name="url")
	private String url;
	
	@Column (name="download_count")
	private int download_count;
	
	@Column (name="created")
	private Integer created;
	
	@Column (name="public")
	private Integer mpublic;

	public DownloadEntity() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSeo_title() {
		return seo_title;
	}

	public void setSeo_title(String seo_title) {
		this.seo_title = seo_title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getDownload_count() {
		return download_count;
	}

	public void setDownload_count(int download_count) {
		this.download_count = download_count;
	}

	public int getCreated() {
		return created;
	}

	public void setCreated(Integer created) {
		this.created = created;
	}

	public Integer getMpublic() {
		return mpublic;
	}

	public void setMpublic(Integer mpublic) {
		this.mpublic = mpublic;
	}

	public DownloadEntity(Integer id, String title, String seo_title, String description, String author, String url,
			int download_count, Integer created, Integer mpublic) {
		
		this.id = id;
		this.title = title;
		this.seo_title = seo_title;
		this.description = description;
		this.author = author;
		this.url = url;
		this.download_count = download_count;
		this.created = created;
		this.mpublic = mpublic;
	}

	
	
	
}
