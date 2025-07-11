# Complaint Management System (CMS)

A comprehensive web-based complaint management system designed to streamline complaint submission, tracking, and resolution processes within organizations. Built using Java Servlets, JSP, and MySQL, the system provides role-based access control for employees and administrators.

## 🚀 Project Overview

The Complaint Management System facilitates efficient complaint handling in organizational settings. It offers an intuitive interface for employees to manage their complaints and equips administrators with tools for monitoring, updating, and resolving all submitted complaints.

## ✨ Features

### Employee Features

* **Complaint Submission**: Submit complaints with detailed descriptions and categories.
* **View Complaints**: Review previously submitted complaints.
* **Edit Complaints**: Modify complaint details before admin review.
* **Delete Complaints**: Remove complaints that are no longer relevant.
* **Status Tracking**: Monitor complaint statuses and admin feedback.
* **User Authentication**: Secure login with session management.

### Administrator Features

* **Dashboard Overview**: View all complaints in the system.
* **Manage Complaints**: Review, update, and resolve complaints.
* **Status Updates**: Set statuses such as Pending, In Progress, Resolved, or Rejected.
* **Admin Remarks**: Provide detailed feedback on complaints.
* **User Management**: Manage user accounts and permissions.
* **Reporting**: Generate reports on complaints and resolutions.

## 🛠️ Technologies Used

### Backend

* Java Servlets
* JSP (Java Server Pages)
* JDBC
* Apache Tomcat

### Frontend

* HTML5
* CSS3 (with custom styles in `dashboard.css`, `loginpage.css`)
* JavaScript

### Database

* MySQL
* MySQL Connector/J

### Tools

* Apache Maven
* Git
* IntelliJ IDEA / Eclipse

## 📁 Project Structure

```
ComplaintManagementSystem/
├── src/
│   └── main/
│       ├── java/lk/municipalpitdivision/cms/
│       │   ├── Controller/         # Servlets (complaintController, logoutController, userController)
│       │   ├── dao/                # Data models (complaintModel, userModel)
│       │   ├── db/                 # Database scripts (script.sql)
│       │   ├── dto/                # Data Transfer Objects (complaintDTO, userDTO)
│       │   └── util/               # Utilities (CORSFilter, ServletContextListner)
│       └── resources/
├── web/
│   ├── css/                        # Stylesheets (dashboard.css, loginpage.css)
│   ├── js/                         # JavaScript files (dashboard.js)
│   ├── WEB-INF/
│   │   ├── Dashboard.jsp           # Admin/employee dashboard
│   │   └── LoginPage.jsp           # Login page
├── pom.xml                         # Maven config
├── .gitignore
└── README.md                       # Project documentation
```

## 📖 Setup & Usage

1. **Clone the repository**

   ```
   git clone https://github.com/Vihanga-Sathsara/Complaint-Management-System.git
   ```

2. **Import into IDE**

    * Use IntelliJ IDEA or Eclipse.

3. **Database setup**

    * Create a MySQL database and execute `script.sql` located in `src/main/java/lk/municipalpitdivision/cms/db/`.

4. **Configure database connection**

    * Update `ServletContextListner.java` with your DB credentials.

5. **Deploy**

    * Use Apache Tomcat 10+ to deploy the application.

6. **Access**

   ```
   http://localhost:8080/ComplaintManagementSystem_war_exploded/
   ```

## 🤝 Contributing

Contributions are welcome! Please:

* Fork the repo.
* Create a new branch for your feature/bug fix.
* Submit a pull request with a clear description.

## 📞 Contact

**Maintainer**

* Name: Vihanga Sathsara
* Email: [sathsaravihanga27@gmail.com](mailto:sathsaravihanga27@gmail.com)
* GitHub: [@Vihanga-Sathsara](https://github.com/Vihanga-Sathsara)

**Repository**

* [https://github.com/Vihanga-Sathsara/Complaint-Management-System.git](https://github.com/Vihanga-Sathsara/Complaint-Management-System.git)

---

Thank you for using the Complaint Management System! We hope this tool helps streamline your organization's complaint handling process.
# Complaint-Management-System
