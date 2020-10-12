package com.kkr.urlshort.urlshortner.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="urltable")
public class URLMap {
	@Id
	@Column(name="long_url2", length = 500)
	private String long_url = "";
	private String short_url = "";
	private String url_code = "";
	
	public URLMap() {
		
	}
	
	public URLMap(String long_url, String short_url, String url_code) {
		this.long_url = long_url;
		this.short_url = short_url;
		this.url_code = url_code;
	}
	
	public String getLong_url() {
		return long_url;
	}
	public String getShort_url() {
		return short_url;
	}
	public String getUrl_code() {
		return url_code;
	}
	public void setLong_url(String long_url) {
		this.long_url = long_url;
	}
	public void setShort_url(String short_url) {
		this.short_url = short_url;
	}
	public void setUrl_code(String url_code) {
		this.url_code = url_code;
	}
	
}
