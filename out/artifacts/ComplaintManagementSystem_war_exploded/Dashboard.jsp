<%@ page import="lk.municipalitdivision.cms.dto.complaintDTO" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Vihanga Sathsara
  Date: 14/06/2025
  Time: 03:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="css/dashboard.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

<nav>
    <h1>Complaint Management System</h1>
    <div class="navButton">
        <button onclick="navigateDashboardSection()">Dashboard</button>
        <%
            if ("Employee".equals(session.getAttribute("role"))){
        %>
        <button onclick="showComplaintsSection()">Create Complaint</button>
            <%
        }
            %>

        <button onclick="navigateTableSection()">View Complaint</button>
    </div>
    <div id = "profile-section">
        <div id="profile-img">
            <h3><%=((String) session.getAttribute("userName")).substring(0, 1).toUpperCase()%></h3>
        </div>
        <div id="name-section">
            <h3 id="name"><%=((String) session.getAttribute("userName")).split(" ")[0]%></h3>
            <h4 id="role"><%=session.getAttribute("role")%></h4>
        </div>
        <%
            if ("Admin".equals(session.getAttribute("role"))){
        %>
            <button id="btnAddMember">Add Member</button>
        <%
        }
        %>
        <button type="button" id="btnAccount" >Account</button>
        <form method="post" action="logout" id="logout">
            <button type="submit">Logout</button>
        </form>

    </div>
</nav>

<main>
    <h1 id="pageName">Dashboard</h1>
    <section id="quick-actions">
        <div class="action-card" id="file_complaint">
            <h3>File a new Complaint</h3>
            <h4>Submit a complaint regarding any issue you are facing.</h4>
            <button id="btnComplaint">Click Here</button>
        </div>

        <div class="action-card" id="submitted_complaint">
            <h3>See Your Submitted Complaint</h3>
            <h4>View the status of your previously submitted complaints.</h4>
            <button id="btnResult">Click Here</button>
        </div>

        <div class="action-card" id="view_complaint">
            <h3>See All Complaints</h3>
            <h4>View all complaints.</h4>
            <button id="btnSeeResult">Click Here</button>
        </div>

    </section>
    <section id="statistics">
        <div class="stat-card">
            <h3>Total Complaints</h3>
            <p id="total-complaints"><%=request.getAttribute("complaintCount")%></p>
        </div>
        <div class="stat-card">
            <h3>Resolved Complaints</h3>
            <p id="resolved-complaints"><%=request.getAttribute("resolvedCount")%></p>
        </div>
        <div class="stat-card">
            <h3>Pending Complaints</h3>
            <p id="pending-complaints"><%=request.getAttribute("pendingCount")%></p>
        </div>
    </section>
    <section id = "complaints">
        <h2>File New Complaint</h2>
        <div id="form-section">
            <form method="POST" id="complaint-form" action = "complaint">
                <input type="hidden" name="action" value="saveComplaint">
                <input type="text" name="title" id="title" placeholder="Enter Complaint Title" required>
                <textarea name="complaintDetail" id="complaint" rows="15" cols="100" placeholder="Enter Your Complaint (Max Length = 200)" maxlength="200" required></textarea>
                <div id="buttons">
                    <button type="submit" id="saveButton">Submit Complaint</button>
                    <button type="reset" id="resetButton">Reset</button>
                    <button type="button" id="viewAllButton">View Complaints</button>
                </div>
            </form>
        </div>
    </section>

    <section id="table-section">
        <h2>Complaints</h2>
        <div id="table-container">

            <%
                String acc_type = (String) session.getAttribute("role");
                List<complaintDTO> complaintDetail = null;
                if ("Admin".equals(acc_type)) {
                    complaintDetail = (List<complaintDTO>) request.getAttribute("AllComplaintDetail");
                } else {
                    complaintDetail = (List<complaintDTO>) request.getAttribute("complaintDetail");
                }
            %>

            <table>
                <thead>
                <tr>
                    <%
                        if ("Admin".equals(acc_type)) {
                    %>
                    <th>Complaint ID</th>
                    <th>Employee ID</th>
                    <th>Complaint Date</th>
                    <th>Complaint Time</th>
                    <th>Title</th>
                    <th>Complaint Detail</th>
                    <th>Complaint Status</th>
                    <th>Remark</th>
                    <th>Action</th>
                    <%
                    } else {
                    %>
                    <th>Complaint ID</th>
                    <th>Complaint Date</th>
                    <th>Complaint Time</th>
                    <th>Title</th>
                    <th>Complaint Detail</th>
                    <th>Complaint Status</th>
                    <th>Action</th>
                    <%
                        }
                    %>
                </tr>
                </thead>
                <tbody>
                <%
                    if (complaintDetail != null && !complaintDetail.isEmpty()) {
                        for (lk.municipalitdivision.cms.dto.complaintDTO complaintDetails : complaintDetail) {
                %>
                <tr>



                    <%
                        if ("Employee".equals(acc_type)) {
                    %>
                    <td><%= complaintDetails.getComplaintID() %></td>
                    <td><%= complaintDetails.getComplaintDate() %></td>
                    <td><%= complaintDetails.getComplaintTime() %></td>
                    <td><%= complaintDetails.getTitle() %></td>
                    <td><%= complaintDetails.getDetail() %></td>
                    <td><%= complaintDetails.getComplaintStatus() %></td>
                    <td>
                        <%
                            if ("Pending".equals(complaintDetails.getComplaintStatus())) {
                        %>
                        <button type="button"
                                class="edit-button"
                                data-id="<%= complaintDetails.getComplaintID() %>"
                                data-title="<%= complaintDetails.getTitle() %>"
                                data-detail="<%= complaintDetails.getDetail() %>">
                            Edit
                        </button>
                        <form method="POST" action = "complaint" class="deleteComplaints">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="complaintID" value="<%= complaintDetails.getComplaintID() %>">
                            <button type="submit" class="btn-delete">Delete</button>
                        </form>
                        <%
                        } else {
                        %>
                        <button class="btn-action" disabled>Finished</button>
                        <%
                            }
                        %>
                    </td>
                    <%
                    } else if ("Admin".equals(acc_type)) {
                    %>
                    <td><%= complaintDetails.getComplaintID() %></td>
                    <td><%= complaintDetails.getEmployeeID() %></td>
                    <td><%= complaintDetails.getComplaintDate() %></td>
                    <td><%= complaintDetails.getComplaintTime() %></td>
                    <td><%= complaintDetails.getTitle() %></td>
                    <td><%= complaintDetails.getDetail() %></td>
                    <td><%= complaintDetails.getComplaintStatus() %></td>
                    <td><%= complaintDetails.getSolution()%></td>
                    <td>
                        <button class="addRemark" type="button" data-id="<%= complaintDetails.getComplaintID() %>" data-status = "<%= complaintDetails.getComplaintStatus()%>">Add Remark</button>
                        <form method="POST" action = "complaint" class="deleteComplaints" id="delete-complaint">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="complaintID" value="<%= complaintDetails.getComplaintID() %>">
                            <button type="submit" class="btn-delete">Delete</button>
                        </form>
                    </td>
                    <%
                        }
                    %>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="8">No complaints found.</td>
                </tr>
                <%
                    }
                %>
                </tbody>
            </table>
        </div>
    </section>
    <section id="addMember-section">
        <h2>Add Member</h2>
        <form id="addMember"  method="POST" action="user">
            <input type="hidden" name="action" value="add">
            <input type="text" name="employeeID" id="employee_id" placeholder="Employee ID" required>
            <input type="text" name="employeeName" id="employee_name" placeholder="Employee Name" required>
            <input type="email" name="employeeEmail" id="employee_email" placeholder="Email" required>
            <input type="text" name="role" id="employeeRole"  value="Admin" readonly >
            <input type="password" name="password" id="employee_password" placeholder="Password" required>
            <button type="submit" id="btnRegister" >Add Member</button>
        </form>
    </section>
    <section id="editComplaint-section">
            <div id="edit-complaint">
                <button type="button" onclick="closeEditPopup()" class="btnClose">✖</button>
                <form method="POST" id="editComplaintForm" action = "complaint">
                    <h2>Edit Complaint</h2>
                    <%
                        if("Employee".equals(acc_type)){
                    %>
                    <div class="complaintElements">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="complaintID" id="complaintId">
                        <input type="text" name="title" id="complaintTitle" placeholder="Enter Complaint Title" required>
                        <textarea name="complaintDetail" id="complaintDetails" rows="15" cols="100" placeholder="Enter Your Complaint (Max Length = 200)" maxlength="200" required></textarea>
                    </div>

                    <%
                    } else if ("Admin".equals(acc_type)) {%>
                    <div class="complaintElements">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" name="complaintID" id="complaint_id">
                        <select name="complaintStatus" id="complaintStatus" required>
                            <option value="">Select Status</option>
                            <option value="Pending">Pending</option>
                            <option value="Reviewed">Reviewed</option>
                        </select>
                        <input type="text" name="remark" id="remark" placeholder="Remark" required>
                    </div>
                        <%
                    }
                    %>
                    <button type="submit" id="btnUpdate">Update</button>
                </form>
            </div>
    </section>
    <section id="editProfile-section">
        <h2>Personal Information</h2>
        <button type="button" onclick="closeAccountPopup()" class="btnClose">✖</button>
        <form id="profile"  method="POST" action="user">
            <input type="hidden" name="action" value="change">
            <input type="text" name="employeeID" id="employee-id" placeholder="Employee ID" value="<%= session.getAttribute("employeeID")%>" readonly>
            <input type="text" name="employeeName" id="employee-name" placeholder="Employee Name" value="<%= session.getAttribute("userName")%>" required>
            <input type="email" name="employeeEmail" id="employee-email" placeholder="Email" value="<%= session.getAttribute("userEmail")%>" required>
            <input type="text" name="role" id="employee-Role" value="<%= session.getAttribute("role")%>" readonly >
            <input type="password" name="password" id="employee-password" placeholder="Password" value="<%= session.getAttribute("password")%>" required>
            <button type="submit" id="btnChange" >Update</button>
        </form>
    </section>
</main>



 <%
     String role = (String) session.getAttribute("role");

     if (role.equals("Admin")) {
 %>
    <script>
        document.getElementById("file_complaint").style.display = "none";
        document.getElementById("submitted_complaint").style.display = "none";
    </script>
<%
} else if (role.equals("Employee")) {
%>
        <script>
            document.getElementById("view_complaint").style.display = "none";
        </script>
<%
    }
%>

<%
    String message = request.getParameter("complaintSaved");
    Boolean completeLogin = (Boolean) session.getAttribute("loginSuccess");
    if ("saved".equals(message)) {
%>
        <script>
            Swal.fire({
                position: "top-end",
                icon: "success",
                title: "Complaint has been saved",
                showConfirmButton: false,
                timer: 1500
            }).then(function () {
                showComplaintsSection();
            });

        </script>
    <%
    } else if ("failed".equals(message)) {
    %>
        <script>
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Something went wrong. Please try again."
            }).then(function () {
                showComplaintsSection();
            });
        </script>
    <%
    } else if (Boolean.TRUE.equals(completeLogin)) {
        session.removeAttribute("loginSuccess");
    %>
        <script>
            Swal.fire({
                icon: "success",
                title: "Login Successful",
                text: "Welcome!",
                timer: 2000,
                showConfirmButton: false
            });
        </script>
    <%
        }
    %>

    <%
        String status = request.getParameter("status");
        if ("registered".equals(status)) {
    %>
        <script>
            Swal.fire({
                icon: "success",
                title: "Registered Successful",
                timer: 2000,
                showConfirmButton: false
            }).then(function () {
                showAddMemberSection();
            });
        </script>
            <%
            } else if ("notRegistered".equals(status)) {%>
        <script>
            Swal.fire({
                icon: "error",
                title: "Oops...",
                text: "Something went wrong. Please try again."
            }).then(function () {
                showAddMemberSection();
            });
        </script>
            <%
            } else if ("isNotValid".equals(status)) {%>
        <script>
            Swal.fire({
                icon: "success",
                title: "Update Successful",
                timer: 2000,
                showConfirmButton: false
            }).then(function () {
               showAccountPopup();
            });
        </script>
        <%
        } else if ("notCredentialUpdated".equals(status)) {%>
                <script>
                    Swal.fire({
                        icon: "error",
                        title: "Oops...",
                        text: "Something went wrong. Please try again."
                    }).then(function () {
                        showAccountPopup();
                    });
                </script>
          <%
        }
        %>
            <%
            String action = request.getParameter("complaintDeleted");
            if("true".equals(action)){%>
                <script>
                    Swal.fire({
                        icon: "success",
                        title: "Deleted Successfully",
                        timer: 2000,
                        showConfirmButton: false
                    }).then(function () {
                        navigateTableSection();
                    });
                </script>

                <%
            } else if ("false".equals(action)) {%>
                    <script>
                        Swal.fire({
                            icon: "error",
                            title: "We couldn't delete your complaint right now. Please try again in a moment.",
                            timer: 2000,
                            showConfirmButton: false
                        }).then(function () {
                            navigateTableSection();
                        });
                    </script>
             <%

            }
                %>


        <script>
            document.querySelectorAll(".deleteComplaints").forEach(function(form){
                form.addEventListener("submit", function (e) {
                    e.preventDefault();
                    Swal.fire({
                        title: "Are you sure?",
                        text: "You won't be able to revert this!",
                        icon: "warning",
                        showCancelButton: true,
                        confirmButtonColor: "#3085d6",
                        cancelButtonColor: "#d33",
                        confirmButtonText: "Yes, delete it!"
                    }).then((result) => {
                        if (result.isConfirmed) {
                            form.submit();
                        }
                    });
                });
            });

        </script>

        <script>
            document.querySelectorAll(".edit-button").forEach(function(button){
                button.addEventListener("click", function (e) {
                    e.preventDefault();
                    document.getElementById("complaintId").value = button.getAttribute("data-id");
                    document.getElementById("complaintTitle").value = button.getAttribute("data-title");
                    document.getElementById("complaintDetails").value = button.getAttribute("data-detail");
                    showEditPopUp();
                });
            });
        </script>

        <script>
            document.querySelectorAll(".addRemark").forEach(function(button){
                button.addEventListener("click", function (e) {
                    e.preventDefault();
                    document.getElementById("complaint_id").value = button.getAttribute("data-id");
                    showEditPopUp();
                });
            });
        </script>

<%
    String updated = request.getParameter("complaintUpdated");
    if ("true".equals(updated)){%>
        <script>
            Swal.fire({
                icon: "success",
                title: "Updated Successfully",
                timer: 2000,
                showConfirmButton: false
            }).then(function () {
                navigateTableSection();
            });
        </script>
    <%
    } else if ("false".equals(updated)) {%>
        <script>
            Swal.fire({
                icon: "error",
                title: "We couldn't update your complaint right now. Please try again in a moment.",
                timer: 2000,
                showConfirmButton: false
            }).then(function () {
                navigateTableSection();
            });
        </script>
    <%

    }
    %>

<script>
    document.getElementById("btnAccount").addEventListener("click", function (e) {
        e.preventDefault();
        showAccountPopup();
    });
</script>
        <script src="js/dashboard.js"></script>
    </body>
</html>