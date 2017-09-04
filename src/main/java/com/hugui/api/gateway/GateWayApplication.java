package com.hugui.api.gateway;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.hugui.api.gateway.filter.AccessFilter;

/**
 * @date : 2017年8月31日
 * @author : hugui
 * @description : TODO
 **/


@EnableDiscoveryClient
@EnableZuulProxy
@SpringCloudApplication
public class GateWayApplication {

	public static void main(String[] args) {
		
		new SpringApplicationBuilder(GateWayApplication.class).web(true).run(args);
		
	}
	
	@Bean
	public AccessFilter accessFilter(){
		return new AccessFilter();
	}
}
