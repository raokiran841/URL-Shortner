package com.kkr.urlshort.urlshortner.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public interface UrlService {
	
	public boolean isAvailable(String url);
	
	public String getShort(String url);
	
	public void addUrl(String url, String short_url, String url_code);

}
