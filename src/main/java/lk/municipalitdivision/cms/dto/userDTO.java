package lk.municipalitdivision.cms.dto;


public class userDTO {
    private String employee_id;
    private String employee_name;
    private String employee_gmail;
    private String employee_role;
    private String employee_password;

    public userDTO(String email, String password) {
        this.employee_gmail = email;
        this.employee_password = password;
    }

    public userDTO(String employeeId, String employeeName, String employeeGmail, String employeeRole, String employeePassword) {
            this.employee_id = employeeId;
            this.employee_name = employeeName;
            this.employee_gmail = employeeGmail;
            this.employee_role = employeeRole;
            this.employee_password = employeePassword;
    }

    public userDTO(String employee_id,String employeeName, String employeeGmail, String employeePassword) {
        this.employee_id = employee_id;
        this.employee_name = employeeName;
        this.employee_gmail = employeeGmail;
        this.employee_password = employeePassword;
    }

    public String getEmployee_name() {
        return employee_name;
    }

    public void setEmployee_name(String employee_name) {
        this.employee_name = employee_name;
    }

    public String getEmployee_gmail() {
        return employee_gmail;
    }

    public void setEmployee_gmail(String employee_gmail) {
        this.employee_gmail = employee_gmail;
    }

    public String getEmployee_role() {
        return employee_role;
    }

    public void setEmployee_role(String employee_role) {
        this.employee_role = employee_role;
    }

    public String getEmployee_password() {
        return employee_password;
    }

    public void setEmployee_password(String employee_password) {
        this.employee_password = employee_password;
    }

    public String getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(String employee_id) {
        this.employee_id = employee_id;
    }
}
