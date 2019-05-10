package com.eureka.client.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig implements ApplicationListener<WebServerInitializedEvent> {
	private int serverPort;
	
	public int getServerPort() {
		return serverPort;
	}

	public String getUrl() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return "http://" + address.getHostAddress() + ":" + this.serverPort;
	}

	public String getHost() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return address.getHostAddress();
	}

	@Override
	public void onApplicationEvent(WebServerInitializedEvent event) {
		serverPort = event.getWebServer().getPort();
	}

}
