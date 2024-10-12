package dopamine.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import dopamine.connection.ConnectionPool;
import dopamine.dao.UserDAO;
import dopamine.model.User;
import dopamine.utils.DBUtil;

public class UserDAOImpl implements UserDAO {

    @Override
    public int insert(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO User (Email, FirstName, LastName) VALUES (?, ?, ?)";
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getFirstName());
            ps.setString(3, user.getLastName());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public int update(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "UPDATE User SET FirstName = ?, LastName = ? WHERE Email = ?";
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public int delete(User user) {
        Connection connection = null;
        PreparedStatement ps = null;
        String query = "DELETE FROM User WHERE Email = ?";
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getEmail());
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public boolean emailExists(String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT Email FROM User WHERE Email = ?";
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public User selectUser(String email) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM User WHERE Email = ?";
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
    }

    @Override
    public ArrayList<User> selectUsers() {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = "SELECT * FROM User";
        try {
            connection = ConnectionPool.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            ArrayList<User> users = new ArrayList<User>();
            while (rs.next()) {
                User user = new User();
                user.setFirstName(rs.getString("FirstName"));
                user.setLastName(rs.getString("LastName"));
                user.setEmail(rs.getString("Email"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace(); // Better error handling
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);
        }
    }
}
