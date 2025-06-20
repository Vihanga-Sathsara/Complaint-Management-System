package lk.municipalitdivision.cms.util;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

@WebListener
public class ServletContextListner implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/cms");
        ds.setUsername("root");
        ds.setPassword("Vihanga@1234");
        ds.setInitialSize(5);
        ds.setMaxTotal(50);

        //add datasource instance to the servlet context
        ServletContext servletcontext = sce.getServletContext();
        servletcontext.setAttribute("db", ds);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ServletContext servletcontext = sce.getServletContext();
            BasicDataSource datasource = (BasicDataSource) servletcontext.getAttribute("db");
            datasource.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
