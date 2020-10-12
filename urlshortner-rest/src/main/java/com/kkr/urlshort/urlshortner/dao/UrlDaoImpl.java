package com.kkr.urlshort.urlshortner.dao;

import java.math.BigInteger;

import javax.persistence.EntityManager;

import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kkr.urlshort.urlshortner.entity.URLMap;

@Repository
public class UrlDaoImpl implements UrlDao{
	
	//@PersistenceContext
	private EntityManager entityManager; 
	
	@Autowired
	public UrlDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public boolean isAvailable(String url) {
		String qstr = "select count(*) from urltable where long_url2 = ?1";
		Query query = (Query) entityManager.createNativeQuery(qstr)
				.setParameter(1, url);
		int count = ((BigInteger)query.getSingleResult()).intValue();
		if(count == 1) {
			return true;
		}
		return false;
	}

	@Override
	public String getShort(String url) {
		String qstr = "select short_url from urltable where long_url2 = ?1";
		Query query = (Query) entityManager.createNativeQuery(qstr)
				.setParameter(1, url);
		String short_url = (String) query.getSingleResult();
		return short_url;
	}

	@Override
	@Transactional
	public void addUrl(String url, String short_url, String url_code) {
		System.out.println("Saving url => "+url);
		URLMap obj = new URLMap(url,short_url,url_code);
		entityManager.persist(obj);
	}

	@Override
	public String getLong(String url_code) {
		String qstr = "select long_url2 from urltable where url_code = ?1";
		Query query = (Query) entityManager.createNativeQuery(qstr)
				.setParameter(1, url_code);
		String long_url = (String) query.getSingleResult();
		return long_url;
	}

}
