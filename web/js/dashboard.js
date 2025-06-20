document.getElementById("btnComplaint").onclick = function(e) {
    e.preventDefault();
    showComplaintsSection();
}


document.addEventListener("DOMContentLoaded", function () {
    navigateDashboardSection();
});

function showComplaintsSection() {
    document.getElementById("statistics").style.display = "none";
    document.getElementById("quick-actions").style.display = "none";
    document.getElementById("pageName").style.display = "none";
    document.getElementById("table-section").style.display = "none";
    document.getElementById("addMember-section").style.display = "none";
    document.getElementById("complaints").style.display = "block";
    const complaintSection = document.getElementById("complaints");
    complaintSection.style.display = "block";
    complaintSection.scrollIntoView({ behavior: 'smooth' });
}

function navigateTableSection() {
    document.getElementById("statistics").style.display = "none";
    document.getElementById("quick-actions").style.display = "none";
    document.getElementById("pageName").style.display = "none";
    document.getElementById("table-section").style.display = "flex";
    document.getElementById("complaints").style.display = "none";
    document.getElementById("addMember-section").style.display = "none";
    document.getElementById("table-section").scrollIntoView({ behavior: 'smooth' });
}

function showAddMemberSection() {
    document.getElementById("statistics").style.display = "none";
    document.getElementById("quick-actions").style.display = "none";
    document.getElementById("pageName").style.display = "none";
    document.getElementById("table-section").style.display = "none";
    document.getElementById("complaints").style.display = "none";
    document.getElementById("addMember-section").style.display = "flex";
    document.getElementById("addMember-section").scrollIntoView({ behavior: 'smooth' });
}

function navigateDashboardSection() {
    document.getElementById("statistics").style.display = "flex";
    document.getElementById("quick-actions").style.display = "flex";
    document.getElementById("pageName").style.display = "flex";
    document.getElementById("complaints").style.display = "none";
    document.getElementById("table-section").style.display = "none";
    document.getElementById("addMember-section").style.display = "none";
}


document.getElementById("viewAllButton").onclick = function(e) {
    e.preventDefault();
    navigateTableSection();
}

document.getElementById("btnSeeResult").onclick = function(e) {
    e.preventDefault();
    navigateTableSection();
}

document.getElementById("btnResult").onclick = function(e) {
    e.preventDefault();
    navigateTableSection();
}

document.getElementById("btnAddMember").onclick = function(e) {
    e.preventDefault();
    showAddMemberSection();
}

function showEditPopUp(){
    document.getElementById("editComplaint-section").style.display = "flex";
}

function closeEditPopup() {
    document.getElementById("editComplaint-section").style.display = "none";
}

function showAccountPopup() {
    document.getElementById("editProfile-section").style.display = "flex";
}

function closeAccountPopup() {
    document.getElementById("editProfile-section").style.display = "none";
}


