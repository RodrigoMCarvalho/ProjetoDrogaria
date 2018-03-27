package br.com.drogaria.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		//destruir a fábrica de sessão
		HibernateUtil.getSessionFactory().close();
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		//criar a fábrica de sessão
		HibernateUtil.getSessionFactory().openSession();
	}

	
}
