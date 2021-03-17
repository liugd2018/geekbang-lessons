package org.geektimes.projects.user.web.listener;

import org.geektimes.context.ComponentContext;
import org.geektimes.jmx.UserManager;
import org.geektimes.projects.user.domain.User;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author liuguodong
 */
@WebListener
public class ManagementInitializerListener implements ServletContextListener {



    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        try {
            // 为 UserMXBean 定义 ObjectName
            ObjectName objectName = new ObjectName("org.geektimes.projects.user.management:type=User");
            // 创建 UserMBean 实例
            User user = new User();
            mBeanServer.registerMBean(createUserMBean(user), objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object createUserMBean(User user) throws Exception {
        return new UserManager(user);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }



}
