package com.zww.test.config;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties()
@PropertySource("classpath:config/customer.properties")
@Component
public class CustomerConfig {
	private Map<String, String> info;

    public Map<String, String> getInfo() {
        return info;
    }

    public void setInfo(Map<String, String> info) {
        this.info = info;
    }

    private List<String> blogs;

    public List<String> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<String> blogs) {
        this.blogs = blogs;
    }
}
