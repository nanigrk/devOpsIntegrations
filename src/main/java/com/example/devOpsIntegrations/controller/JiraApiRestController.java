package com.example.devOpsIntegrations.controller;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.swagger.annotations.Api;

@Api(value = "JiraApiRestController", description = "REST Apis related to Jira!!!!", tags="JIRA Operations")
@RestController
@RequestMapping("/devops/jira")
public class JiraApiRestController {

	@Value("${application.jenkins.root.uri}")
	private String ROOT_URI;

	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping(value = "/stories", method = RequestMethod.GET)
	public String build(@PathVariable("jobName") String jobName) {
		return "stories";
	}
	
	HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}
