package ir.seefa.web.config;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @author Saman Delfani
 * @version 1.0
 * @since 23 Jul 2020 T 11:46:07
 */
//@WebListener()
public class EntityManagerListener implements ServletContextListener,
        HttpSessionListener, HttpSessionAttributeListener {

    private final Logger logger = Logger.getLogger(EntityManagerListener.class);

    private static EntityManagerFactory factory;
    private static EntityManager entityManager;

    // Public constructor is required by servlet spec
    public EntityManagerListener() {
    }

    public static EntityManager getEntityManger() {
        return entityManager;
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {

        try {
            factory = Persistence.createEntityManagerFactory("MySQLSeefaWebsite");
        } catch (Throwable ex) {
            logger.error("Failed to create sessionFactory object." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public void contextDestroyed(ServletContextEvent sce) {
        if (factory != null)
            factory.close();
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        entityManager = factory.createEntityManager();
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        entityManager.close();
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }
}
