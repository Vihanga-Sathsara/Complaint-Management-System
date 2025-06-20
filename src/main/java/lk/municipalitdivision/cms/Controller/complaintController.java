package lk.municipalitdivision.cms.Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.municipalitdivision.cms.dto.complaintDTO;
import lk.municipalitdivision.cms.dao.complaintModel;
import org.apache.commons.dbcp2.BasicDataSource;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@WebServlet("/complaint")
public class complaintController extends HttpServlet {

    private complaintModel complaintModel;

    @Override
    public void init() {
        BasicDataSource ds = (BasicDataSource) getServletContext().getAttribute("db");
        complaintModel = new complaintModel(ds);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String employeeID = (String) req.getSession().getAttribute("employeeID");
        String jobRole = (String) req.getSession().getAttribute("role");

        try {
            List<complaintDTO> ComplaintDetail = complaintModel.getAllComplaint(employeeID);
            int comp_count = complaintModel.getcomplaintCount(employeeID);
            int resolved_count = complaintModel.resolvedComplaintCount(employeeID);
            int pending_count = complaintModel.pendingComplaintCount(employeeID);

            List<complaintDTO> AllComplaintDetail = complaintModel.getAllComplaint();
            int allComp_count = complaintModel.getAllComplaintCount();
            int allResolved_count = complaintModel.getAllResolvedComplaintCount();
            int allPending_count = complaintModel.getAllPendingComplaintCount();

            if (jobRole.equals("Employee")) {
                req.setAttribute("complaintCount", comp_count);
                req.setAttribute("resolvedCount", resolved_count);
                req.setAttribute("pendingCount", pending_count);
                req.setAttribute("complaintDetail", ComplaintDetail);
                req.getRequestDispatcher("Dashboard.jsp").forward(req, resp);
            } else if (jobRole.equals("Admin")) {
                req.setAttribute("complaintCount", allComp_count);
                req.setAttribute("resolvedCount", allResolved_count);
                req.setAttribute("pendingCount", allPending_count);
                req.setAttribute("AllComplaintDetail", AllComplaintDetail);
                req.getRequestDispatcher("Dashboard.jsp").forward(req, resp);
            }


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        if (("saveComplaint").equals(action)) {
            String comp_id = UUID.randomUUID().toString();
            String employeeID = (String) req.getSession().getAttribute("employeeID");
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            String title =  req.getParameter("title");
            String detail = req.getParameter("complaintDetail");
            String status = "Pending";
            String solution = "Under Review";

            try {
                complaintDTO ComplaintDTO = new complaintDTO(comp_id,employeeID,date,time,title,detail,status,solution);
                boolean result = complaintModel.saveComplaint(ComplaintDTO);
                if (result) {
                    resp.sendRedirect("complaint?complaintSaved=saved");
                }else {
                    resp.sendRedirect("complaint?complaintSaved=failed");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (("delete").equals(action)) {
            try {
                String complaintID = req.getParameter("complaintID");
                boolean result = complaintModel.deleteComplaint(complaintID);
                if (result){
                    resp.sendRedirect("complaint?complaintDeleted=true");
                }else {
                    resp.sendRedirect("complaint?complaintDeleted=false");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (("update").equals(action)) {
            String complaintID = req.getParameter("complaintID");
            String newTitle = req.getParameter("title");
            String newDetail = req.getParameter("complaintDetail");
            try {
               complaintDTO ComplaintDto = new complaintDTO(complaintID,newTitle,newDetail);
               boolean result = complaintModel.updateComplaint(ComplaintDto);
               if (result) {
                   resp.sendRedirect("complaint?complaintUpdated=true");
               }else {
                   resp.sendRedirect("complaint?complaintUpdated=false");
               }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if ("edit".equals(action)) {
            String complaintID = req.getParameter("complaintID");
            String status = req.getParameter("complaintStatus");
            String solution = req.getParameter("remark");
            try {
                complaintDTO ComplaintDto = new complaintDTO(complaintID,status,solution,true);
                boolean result = complaintModel.addComplaintRemark(ComplaintDto);
                if(result){
                    resp.sendRedirect("complaint?complaintUpdated=true");
                }else {
                    resp.sendRedirect("complaint?complaintUpdated=false");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }

}
