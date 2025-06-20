package lk.municipalitdivision.cms.dao;

import lk.municipalitdivision.cms.dto.userDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class userModel  {

    private final BasicDataSource dataSource;


    public userModel(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean checkUser(userDTO user) {
        String sql = "SELECT emp_id FROM user WHERE emp_gmail = ? AND emp_password = ?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, user.getEmployee_gmail());
            ps.setString(2, user.getEmployee_password());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveUser(userDTO user) {
        boolean isTrue = false;
        String sql = "INSERT INTO user (emp_id, emp_name, emp_gmail, emp_role, emp_password) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, user.getEmployee_id());
            ps.setString(2, user.getEmployee_name());
            ps.setString(3, user.getEmployee_gmail());
            ps.setString(4, user.getEmployee_role());
            ps.setString(5, user.getEmployee_password());
            int row = ps.executeUpdate();

            if(row >0){
                isTrue = true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isTrue;
    }

    public userDTO getUserDetails(userDTO user) {
        String sql = "SELECT * FROM user WHERE emp_gmail = ? AND emp_password = ?";

        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, user.getEmployee_gmail());
            ps.setString(2, user.getEmployee_password());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new userDTO(
                    rs.getString("emp_id"),
                    rs.getString("emp_name"),
                    rs.getString("emp_gmail"),
                    rs.getString("emp_role"),
                    rs.getString("emp_password")
                );
            }else {
                return null;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkValidUser(userDTO user) {
        String sql = "SELECT emp_id FROM user WHERE emp_id = ?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, user.getEmployee_id());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateUser(userDTO user){
        String sql = "UPDATE user SET emp_name = ?,emp_gmail =?, emp_password = ? WHERE emp_id = ?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, user.getEmployee_name());
            ps.setString(2, user.getEmployee_gmail());
            ps.setString(3, user.getEmployee_password());
            ps.setString(4, user.getEmployee_id());
            int row = ps.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
