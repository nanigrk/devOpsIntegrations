package com.example.devOpsIntegrations.configuration;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.example.devOpsIntegrations.controller.MyJiraClient;

@Configuration
public class BeanConfiguration {
	
	@Value("${application.jira.root.uri}")
	private String ROOT_URI;
	private String username;
    private String password;
	
	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public JiraRestClient getJiraRestClient() {
       return new AsynchronousJiraRestClientFactory()
         .createWithBasicHttpAuthentication(getJiraUri(), this.username, this.password);
    }
	
	@Bean
	public MyJiraClient getMyJiraClient() {
       return new MyJiraClient(this.username, this.password,this.ROOT_URI ,getJiraRestClient());
    }
	
	private URI getJiraUri() {
        return URI.create(this.ROOT_URI);
    }
	
	


}
