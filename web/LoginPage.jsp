<%--
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
    <title>Login Page</title>
    <link rel="stylesheet" href="css/loginpage.css">
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>
<nav class="navbar">
    <p>Complaint Management System</p>
    <button class="getStarted" id="btnGetStarted">Get Started 🚀</button>
</nav>
<main>
    <section id="main-section">
        <div id="login-side">
            <form id="login-form"  method="POST" action="user" >
                <h1>Sign In</h1>
                <input type="hidden" name="action" value="login">
                <input type="email" name="email" id="email" placeholder="Email">
                <input type="password" name="password" id="password" placeholder="Password">
                <div id="clickHere">
                    <span id="checkAccount">Don't have an account?<a href="#"> Sign Up</a></span>
                </div>
                <button type="submit" id="btnLogin" class="sub-button">Login</button>
            </form>
        </div>
        <div id="register-side">
            <form id="signup-form"  method="POST" action="user">
                <h1>Sign Up</h1>
                <input type="hidden" name="action" value="signup">
                <input type="text" name="employeeID" id="employee_id" placeholder="Employee ID" required>
                <input type="text" name="employeeName" id="employee_name" placeholder="Employee Name" required>
                <input type="email" name="employeeEmail" id="employee_email" placeholder="Email" required>
                <input type="text" name="role" id="role"  value="Employee" readonly>
                <input type="password" name="password" id="employee_password" placeholder="Password" required>
                <div id="suClickHere">
                    <span id="suCheckAccount">Already have an account? <a href="LoginPage.jsp"> Sign In</a></span>
                </div>

                <button type="submit" id="btnSignUp" class="sub-button">Sign Up</button>
            </form>
        </div>
        <div id="v-line"></div>
        <div id="intro-side">
            <h2>Welcome to the Complaint Management System</h2>
            <h3>Effortless Complaint Handling for Municipal Employees</h3>
            <p>
                This system is built to support municipal staff in managing citizen complaints with ease and professionalism. Track issues in real time, assign tasks efficiently, and ensure every complaint is addressed with transparency and accountability. Enhance public service — one complaint at a time.
            </p>

        </div>
    </section>
</main>
<script>
    document.getElementById('btnLogin').addEventListener('click', function(e) {
        e.preventDefault();
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;

        if (!email || !password) {
            Swal.fire({
                icon: "warning",
                title: "Oops...",
                text: "Email and Password must not be empty!"
            });

        }else{
            document.getElementById('login-form').submit();
        }
    });


    document.getElementById("register-side").style.display = "none";


    document.getElementById("checkAccount").addEventListener("click", function(event) {
        event.preventDefault();
        document.getElementById("login-side").style.display = "none";
        document.getElementById("register-side").style.display = "block";
    });

    document.getElementById("btnSignUp").addEventListener("click", function(event) {
        event.preventDefault();
        const emp_id = document.getElementById('employee_id').value;
        const emp_name = document.getElementById('employee_name').value;
        const email = document.getElementById('employee_email').value;
        const role = document.getElementById('role').value;
        const password = document.getElementById('employee_password').value;

        if (!emp_id || !emp_name || !email || !role || !password ) {
            Swal.fire({
                icon: "warning",
                title: "Incomplete Details",
                text: "Please fill in all required fields.",
                confirmButtonText: "OK"
            });
        }else {
            document.getElementById("signup-form").submit();
        }
    });
</script>

<%
    String message = (String) request.getAttribute("error");
    String alert = request.getParameter("error");
    if ("invalid".equals(message)) {
%>
    <script>
            Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "Email or password invalid, please try again."
        });
    </script>
<%
    } else if ("registered".equals(alert)) {
        %>
            <script>
                Swal.fire({
                    icon: "success",
                    title: "Registered Successful",
                    timer: 2000,
                    showConfirmButton: false
                }).then(() => {
                    window.location.href = "LoginPage.jsp";
                });
            </script>
        <%
    } else if ("notSuccess".equals(message)) {
        %>
            <script>
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Something went wrong. Please try again."
                });
            </script>
        <%
    } else if ("isNotValid".equals(message)) { %>
            <script>
                Swal.fire({
                    icon: "error",
                    title: "Oops...",
                    text: "Already have an account"
                });
            </script>
        <%
    } else if ("updated".equals(alert)) {%>
        <script>
            Swal.fire({
                icon: "success",
                title: "Updated Successfully",
                timer: 2000,
                showConfirmButton: false
            });
        </script>
        <%
    }
        %>

   <script>
       document.getElementById('btnGetStarted').addEventListener('click', function() {
           const emailInput = document.getElementById('email');
           emailInput.focus();
       });
   </script>

</body>
</html>