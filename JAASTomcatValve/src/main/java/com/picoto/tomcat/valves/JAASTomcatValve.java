package com.picoto.tomcat.valves;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.connector.Request;
import org.apache.catalina.connector.Response;
import org.apache.catalina.valves.ValveBase;
import org.apache.log4j.Logger;


public class JAASTomcatValve extends ValveBase {

	protected static Logger logger = Logger.getLogger(JAASTomcatValve.class);
	
	public void invoke(Request request, Response response) throws IOException,
			ServletException {

		logger.debug("***** Interceptando peticion SenadoTomcatValve *****");
		Utils.setHttpServletRequest(request.getRequest());

		try {
			getNext().invoke(request, response);
		} finally {
			Utils.clean();
		}
	}

}
