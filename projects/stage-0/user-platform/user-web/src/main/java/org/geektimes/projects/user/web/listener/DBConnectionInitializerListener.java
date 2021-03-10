package org.geektimes.projects.user.web.listener;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class DBConnectionInitializerListener implements ServletContextListener {


    private ServletContext servletContext;

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        servletContext = sce.getServletContext();
        Connection connection = getConnection();

        if (connection != null){
            System.out.println("获取JNDI 成功！！");
            servletContext.log("获取JNDI 成功！！");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }


    protected Connection getConnection(){
        Context context = null;
        try {
            context = new InitialContext();
            Context envContext = (Context) context.lookup("java:comp/env");

            DataSource dataSource = (DataSource) envContext.lookup("jdbc/UserPlatformDB");
           return dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            servletContext.log(e.getMessage(), e);
            throw new RuntimeException(e);

        }

    }
}
