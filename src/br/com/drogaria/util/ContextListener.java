package br.com.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//destruir a f�brica de sess�o
		HibernateUtil.getSessionFactory().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//criar a f�brica de sess�o
		HibernateUtil.getSessionFactory().openSession();
	}

	
}
