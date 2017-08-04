Cosas a configurar en tomcat:

- Crear el fichero ${TOMCAT_HOME}/conf/jaas.config con el contenido:

customSecurity {
com.picoto.jaas.LoginModule REQUIRED debug=true;
};

- En el arranque de Tomcat incluir: set CATALINA_OPTS=-Djava.security.auth.login.config=%CATALINA_HOME%/conf/jaas.config

- En el META-INF/context.xml de la aplicación incluir el nuevo Realm de JAAS y la nueva válvula:

	
<?xml version="1.0" encoding="UTF-8"?>
<Context>

 	<Realm className="org.apache.catalina.realm.JAASRealm" 
        appName="customSecurity" 
        userClassNames="com.picoto.jaas.User" 
        roleClassNames="com.picoto.jaas.Role" debug="99" />



	<Valve className="com.picoto.tomcat.valves.JAASTomcatValve" />

</Context>

Creada nueva librería con la válvula para Tomcat para guardar el HttpServletRequest en el ThreadLocal.

Instalar el jar de la nueva válvula en TOMCAT en ${TOMCAT_HOME}/lib aunque se podría probar en la propia aplicación.
