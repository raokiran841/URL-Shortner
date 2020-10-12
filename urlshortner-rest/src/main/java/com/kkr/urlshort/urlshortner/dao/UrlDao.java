package com.kkr.urlshort.urlshortner.dao;

import org.springframework.stereotype.Repository;

public interface UrlDao {
	
	public boolean isAvailable(String url);
	
	public String getShort(String url);
	
	public void addUrl(String url, String short_url, String url_code);
	
	public String getLong(String short_url);

}
