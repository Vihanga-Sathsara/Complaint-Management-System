package lk.municipalitdivision.cms.Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.dbcp2.BasicDataSource;
import lk.municipalitdivision.cms.dao.userModel;
import lk.municipalitdivision.cms.dto.userDTO;

import java.io.IOException;

@WebServlet("/user")
public class userController extends HttpServlet {
    private userModel user;
    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("db");
        user = new userModel(ds);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if("login".equals(action)) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            try {
                userDTO userDTO = new userDTO(email,password);
                boolean isTrue = user.checkUser(userDTO);
                userDTO userDetails = user.getUserDetails(userDTO);

                if (isTrue) {
                    HttpSession session = req.getSession();
                    session.setAttribute("userEmail",userDetails.getEmployee_gmail());
                    session.setAttribute("userName", userDetails.getEmployee_name());
                    session.setAttribute("employeeID", userDetails.getEmployee_id());
                    session.setAttribute("role",userDetails.getEmployee_role());
                    session.setAttribute("password", userDetails.getEmployee_password());
                    session.setAttribute("loginSuccess",true);
                    resp.sendRedirect("complaint");
                } else{
                    req.setAttribute("error", "invalid");
                    req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
                }
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }else if("signup".equals(action)) {
            String employee_id = req.getParameter("employeeID");
            String employee_name = req.getParameter("employeeName");
            String employee_gmail = req.getParameter("employeeEmail");
            String employee_role = req.getParameter("role");
            String employee_password = req.getParameter("password");

            try {
                userDTO userDTO = new userDTO(employee_id,employee_name,employee_gmail,employee_role,employee_password);
                boolean isNotValid = user.checkValidUser(userDTO);
                if (!isNotValid) {
                    boolean isTrue = user.saveUser(userDTO);
                    if (isTrue) {
                        resp.sendRedirect("LoginPage.jsp?error=registered");
                    }else {
                        req.setAttribute("error", "notSuccess");
                        req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
                    }
                }else {
                    req.setAttribute("error", "isNotValid");
                    req.getRequestDispatcher("LoginPage.jsp").forward(req, resp);
                }



            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else if("add".equals(action)) {
            String employee_id = req.getParameter("employeeID");
            String employee_name = req.getParameter("employeeName");
            String employee_gmail = req.getParameter("employeeEmail");
            String employee_role = req.getParameter("role");
            String employee_password = req.getParameter("password");

            try {
                userDTO userDTO = new userDTO(employee_id, employee_name, employee_gmail, employee_role, employee_password);
                boolean isNotValid = user.checkValidUser(userDTO);
                if(!isNotValid){
                    boolean isTrue = user.saveUser(userDTO);
                    if (isTrue) {
                        resp.sendRedirect("complaint?status=registered");
                    } else {
                        resp.sendRedirect("complaint?status=notRegistered");
                    }
                }else {
                    resp.sendRedirect("complaint?status=isNotValid");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("change".equals(action)) {
            String employee_id = req.getParameter("employeeID");
            String employee_name = req.getParameter("employeeName");
            String employee_gmail = req.getParameter("employeeEmail");
            String employee_password = req.getParameter("password");
            try {
                userDTO userDTO = new userDTO(employee_id,employee_name, employee_gmail, employee_password);
                boolean isTrue = user.updateUser(userDTO);
                    if (isTrue) {
                        HttpSession session = req.getSession(false);
                        if (session != null) {
                            session.invalidate();
                        }
                        resp.sendRedirect("LoginPage.jsp?error=updated");
                    } else {
                        resp.sendRedirect("complaint?status=notCredentialUpdated");
                    }

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
