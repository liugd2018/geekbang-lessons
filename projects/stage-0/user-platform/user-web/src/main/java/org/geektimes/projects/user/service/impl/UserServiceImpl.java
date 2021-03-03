package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.repository.UserRepository;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.sql.DBConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * TODO
 *
 * @author <a href="mailto:liugd2020@gmail.com">liuguodong</a>
 * @since 1.0
 */
public class UserServiceImpl implements UserService {

    private static Logger logger = Logger.getLogger(UserServiceImpl.class.getName());


    @Override
    public boolean register(User user) {

        Connection connection = null;
        DBConnectionManager connectionManager = new DBConnectionManager();
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String databaseURL = "jdbc:derby:user-platform;create=true";
            connection = DriverManager.getConnection(databaseURL);
            connectionManager.setConnection(connection);

            UserRepository userRepository = new DatabaseUserRepository(connectionManager);
            return userRepository.save(user);

        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.FINE, e.getMessage(), e.fillInStackTrace());
            return false;
        }finally {
            connectionManager.releaseConnection();
        }

    }

    @Override
    public boolean deregister(User user) {
        return false;
    }

    @Override
    public boolean update(User user) {
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return null;
    }

    @Override
    public List<User> queryAll() {

        Connection connection = null;
        DBConnectionManager connectionManager = new DBConnectionManager();
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            String databaseURL = "jdbc:derby:user-platform;create=true";
            connection = DriverManager.getConnection(databaseURL);
            connectionManager.setConnection(connection);

            UserRepository userRepository = new DatabaseUserRepository(connectionManager);
            Collection<User> userList = userRepository.getAll();
            return (List<User>) userList;

        } catch (SQLException | ClassNotFoundException e) {
            logger.log(Level.FINE, e.getMessage(), e.fillInStackTrace());
            return new ArrayList<>();
        }finally {
            connectionManager.releaseConnection();
        }

    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return null;
    }
}
