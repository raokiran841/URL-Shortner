package com.kkr.urlshort.urlshortner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kkr.urlshort.urlshortner.dao.UrlDao;

@Service
public class UrlServiceImpl implements UrlService {
	
	@Autowired
	private UrlDao dao;

	@Override
	public boolean isAvailable(String url) {
		return dao.isAvailable(url);
	}

	@Override
	public String getShort(String url) {
		return dao.getShort(url);
	}

	@Override
	public void addUrl(String url, String short_url, String url_code) {
		dao.addUrl(url, short_url, url_code);
	}

}
