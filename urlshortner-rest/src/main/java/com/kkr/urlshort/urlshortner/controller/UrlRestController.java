package com.kkr.urlshort.urlshortner.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kkr.urlshort.urlshortner.dao.UrlDao;
import com.kkr.urlshort.urlshortner.entity.URLMap;
import com.kkr.urlshort.urlshortner.service.UrlService;

//@RestController
@Controller
public class UrlRestController {

	private static final String base_url = "http://localhost:5000/api/";
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	static SecureRandom rnd = new SecureRandom();
	private String short_url;
	private String long_url;

	// this i am using to create random 5 alphanumric charaters
	public String randomString(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}

	@Autowired
	private UrlService service;

	@Autowired
	private UrlDao dao;

	@GetMapping("/home")
	public String home(@ModelAttribute String message, Model model) {
		System.out.println("Welcome to home page");
		return "index.html";
	}

	@PostMapping("/url_reduce")
	@ResponseBody
	public String reduce(@RequestBody String url, Model model) {
		String result = "";
		String url_code = "";
		try {
			result = java.net.URLDecoder.decode(url, StandardCharsets.UTF_8.name());
			result = result.replace("url=", "");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println("=> " + result);
		if (service.isAvailable(result)) {
			short_url = service.getShort(result);
			String[] last = short_url.split("/");
			url_code = last[last.length-1];
		} else {
			url_code = randomString(5);
			short_url = base_url + url_code;
			service.addUrl(result, short_url, url_code);
			System.out.println("new url added");
		}
		
		return short_url;
	}

	@GetMapping("/api/{code}")
	public String gotourl(@PathVariable String code) {
		try {
			long_url = dao.getLong(code);
			System.out.println("long url fetched "+long_url);
			return "redirect:"+long_url;
		} catch (Exception e) {
			System.out.println(e);
			return "redirect:/home";
		}
	}


}
