package com.picoto.jaas;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;

import org.apache.log4j.Logger;


public class LoginModule implements javax.security.auth.spi.LoginModule {

	protected static Logger logger = Logger.getLogger(LoginModule.class);

	protected Subject subject;
	protected CallbackHandler callbackHandler;
	protected List<Principal> rolesUsuario;

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		logger.debug("Inicio de initialize");

		this.subject = subject;
		this.callbackHandler = callbackHandler;

		logger.debug("fin de initialize");
	}

	public boolean login() throws LoginException {

		logger.debug("Inicio de login");

		NameCallback nc = new NameCallback("login");
		PasswordCallback pc = new PasswordCallback("password", false);

		try {
			Callback[] callbacks = new Callback[2];
			callbacks[0] = nc;
			callbacks[1] = pc;
			callbackHandler.handle(callbacks);

			String username = nc.getName().toUpperCase();
			String password = new String(pc.getPassword());

			CustomLogin cl = new CustomLogin(username, password);

			boolean res = cl.login();

			if (res == true) {
				List<String> roles = cl.getRoles();
				if (roles != null) {
					rolesUsuario = new ArrayList<Principal>(roles.size() + 1);
					rolesUsuario.add(new User(username));
					for (String rol : roles) {
						rolesUsuario.add(new Role(rol));
					}
				}
				return true;
			} else {
				throw new LoginException("No se han validado las credenciales");
			}
		} catch (Exception e) {
			logger.error("Error en la autenticacion", e);
			throw new LoginException("Error autenticando al usuario");
		}

	}

	public boolean commit() throws LoginException {
		logger.debug("Inicio de commit");

		if (subject.isReadOnly()) {
			logger.debug(("Subject is ReadOnly"));
			throw new LoginException("Subject is ReadOnly");
		}

		logger.debug("Aniadiendo rol " + rolesUsuario.size());
		if (rolesUsuario != null) {
			for (Principal rol : rolesUsuario) {
				if (!subject.getPrincipals().contains(rol)) {
					subject.getPrincipals().add(rol);
				}
			}
		}

		logger.debug("fin de commit");
		return true;
	}

	public boolean abort() throws LoginException {
		return logout();
	}

	public boolean logout() throws LoginException {
		logger.debug("Inicio de logout");
		cleanupAll();
		logger.debug("fin de logout");
		return true;
	}

	protected void cleanupAll() {
		if (rolesUsuario != null) {
			rolesUsuario.clear();
		}
	}

}
