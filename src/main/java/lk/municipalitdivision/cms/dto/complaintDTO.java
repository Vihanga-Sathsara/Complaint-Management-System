package lk.municipalitdivision.cms.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class complaintDTO {
    private String complaintID;
    private String employeeID;
    private LocalDate complaintDate;
    private LocalTime complaintTime;
    private String title;
    private String detail;
    private String complaintStatus;
    private String solution;


    public complaintDTO(String compId, String empId, LocalDate compDate, LocalTime compTime, String title, String compDetail, String compStatus, String solution) {
        this.complaintID = compId;
        this.employeeID = empId;
        this.complaintDate = compDate;
        this.complaintTime = compTime;
        this.title = title;
        this.detail = compDetail;
        this.complaintStatus = compStatus;
        this.solution = solution;
    }


    public complaintDTO(String complaintID, String title, String detail) {
        this.complaintID = complaintID;
        this.title = title;
        this.detail = detail;
    }

    public complaintDTO(String complaintID, String status, String solution, boolean b) {
        this.complaintID = complaintID;
        this.complaintStatus = status;
        this.solution = solution;
    }

    public String getComplaintID() {
        return complaintID;
    }

    public void setComplaintID(String complaintID) {
        this.complaintID = complaintID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public LocalDate getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(LocalDate complaintDate) {
        this.complaintDate = complaintDate;
    }

    public LocalTime getComplaintTime() {
        return complaintTime;
    }

    public void setComplaintTime(LocalTime complaintTime) {
        this.complaintTime = complaintTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getComplaintStatus() {
        return complaintStatus;
    }

    public void setComplaintStatus(String complaintStatus) {
        this.complaintStatus = complaintStatus;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
}
