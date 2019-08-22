package starsoft.hln.tis.mic.j2ee.listener;

import starsoft.hln.tis.mic.config.AppConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        System.out.println("System is running");

        ServletContext servletContext = servletContextEvent.getServletContext();
        try (InputStreamReader reader =
                     new InputStreamReader(
                             servletContext
                                     .getResourceAsStream("/WEB-INF/settings.json"))) {
            AppConfig.initialize(reader);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("System is down");
    }
}
