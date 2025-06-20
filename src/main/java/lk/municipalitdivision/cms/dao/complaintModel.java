package lk.municipalitdivision.cms.dao;

import lk.municipalitdivision.cms.dto.complaintDTO;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class complaintModel {
    private final BasicDataSource dataSource;

    public complaintModel(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int getcomplaintCount(String emp_id) {
       String sql = "select count(*) from complaint where emp_id=?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, emp_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAllComplaintCount() {
        String sql = "select count(*) from complaint";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int resolvedComplaintCount(String emp_id) {
        String sql = "select count(*) from complaint where comp_status = 'Reviewed' and emp_id=?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, emp_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int pendingComplaintCount(String emp_id) {
        String sql = "select count(*) from complaint where comp_status = 'Pending' and emp_id=?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, emp_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }else{
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getAllResolvedComplaintCount() {
        String sql = "select count(*) from complaint where comp_status = 'Reviewed'";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getAllPendingComplaintCount() {
        String sql = "select count(*) from complaint where comp_status = 'Pending'";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }else{
                return 0;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean saveComplaint(complaintDTO complaint) {
        String sql = "INSERT INTO complaint (comp_id,emp_id,comp_date,comp_time,comp_title,comp_detail,comp_status,solution) VALUES (?, ?, ?, ?, ?,?,?,?)";

        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, complaint.getComplaintID());
            ps.setString(2, complaint.getEmployeeID());
            ps.setDate(3,   Date.valueOf(complaint.getComplaintDate()));
            ps.setTime(4,   Time.valueOf(complaint.getComplaintTime()));
            ps.setString(5, complaint.getTitle());
            ps.setString(6, complaint.getDetail());
            ps.setString(7, complaint.getComplaintStatus());
            ps.setString(8, complaint.getSolution());
            int row = ps.executeUpdate();
            if (row > 0) {
                return true;
            }else {
                return false;
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<complaintDTO> getAllComplaint(String emp_id) {
        List<complaintDTO> allComplaints = new ArrayList<>();
        String sql = "select * from complaint where emp_id=? order by comp_date desc , comp_time desc";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, emp_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                complaintDTO dto = new complaintDTO(
                        rs.getString("comp_id"),
                        rs.getString("emp_id"),
                        rs.getDate("comp_date").toLocalDate(),
                        rs.getTime("comp_time").toLocalTime(),
                        rs.getString("comp_title"),
                        rs.getString("comp_detail"),
                        rs.getString("comp_status"),
                        rs.getString("solution")
                );
                allComplaints.add(dto);
            }
            return allComplaints;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<complaintDTO> getAllComplaint() {
        List<complaintDTO> allComplaints = new ArrayList<>();
        String sql = "select * from complaint order by comp_date desc , comp_time desc";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                complaintDTO dto = new complaintDTO(
                        rs.getString("comp_id"),
                        rs.getString("emp_id"),
                        rs.getDate("comp_date").toLocalDate(),
                        rs.getTime("comp_time").toLocalTime(),
                        rs.getString("comp_title"),
                        rs.getString("comp_detail"),
                        rs.getString("comp_status"),
                        rs.getString("solution")
                );
                allComplaints.add(dto);
            }
            return allComplaints;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteComplaint(String complaintID) {
        String sql = "delete from complaint where comp_id=?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, complaintID);
            int row = ps.executeUpdate();
            return row > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateComplaint (complaintDTO complaint) {
        String sql = "update complaint set comp_title = ?, comp_detail = ? where comp_id = ?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, complaint.getTitle());
            ps.setString(2, complaint.getDetail());
            ps.setString(3, complaint.getComplaintID());
            int row = ps.executeUpdate();
            return row > 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public boolean addComplaintRemark (complaintDTO complaint) {
        String sql = "update complaint set comp_status = ?, solution = ? where comp_id = ?";
        try {
            PreparedStatement ps = dataSource.getConnection().prepareStatement(sql);
            ps.setString(1, complaint.getComplaintStatus());
            ps.setString(2, complaint.getSolution());
            ps.setString(3, complaint.getComplaintID());
            int row = ps.executeUpdate();
            return row > 0;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

}
