package com.picoto.tomcat.valves;

import javax.servlet.http.HttpServletRequest;

public class Utils {


	static InheritableThreadLocal<HttpServletRequest> requestHolder = new InheritableThreadLocal<HttpServletRequest>();
	

	public static HttpServletRequest getHttpServletRequest() {
		return requestHolder.get();
	}
	
	public static void setHttpServletRequest(HttpServletRequest req) {
		requestHolder.set(req);	
	}
	
	public static void clean() {
		requestHolder.remove();
	}

}
