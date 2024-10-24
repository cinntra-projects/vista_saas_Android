package com.cinntra.vista.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class EmployeeAllFilterPageData {
    public int id;
    public String role_name;
    public String companyID;
    @SerializedName("SalesEmployeeCode")
    public String salesEmployeeCode;
    @SerializedName("SalesEmployeeName")
    public String salesEmployeeName;
    @SerializedName("EmployeeID")
    public String employeeID;
    public String userName;
    public String firstName;
    public String middleName;
    public String lastName;
    @SerializedName("Email")
    public String email;
    @SerializedName("Mobile")
    public String mobile;
    public String position;
    public String branch;
    public String passwordUpdatedOn;
    public String lastLoginOn;
    public String logedIn;
    @SerializedName("FCM")
    public String fCM;
    @SerializedName("Active")
    public String active;
    public String timestamp;
    public String reportingTo;
    public String user_id;
    public String client_id;
    public int role;
    public int departement;
    public ArrayList<Integer> zone;
    @SerializedName("ReportingDetails")
    public Object reportingDetails;
    @SerializedName("RoleDetails")
    public RoleDetails roleDetails;
    @SerializedName("ZoneDetails")
    public ArrayList<ZoneDetail> zoneDetails;
    @SerializedName("DepartementDetails")
    public DepartementDetails departementDetails;
    @SerializedName("ProjectSetting")
    public ArrayList<ProjectSetting> projectSetting;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getSalesEmployeeCode() {
        return salesEmployeeCode;
    }

    public void setSalesEmployeeCode(String salesEmployeeCode) {
        this.salesEmployeeCode = salesEmployeeCode;
    }

    public String getSalesEmployeeName() {
        return salesEmployeeName;
    }

    public void setSalesEmployeeName(String salesEmployeeName) {
        this.salesEmployeeName = salesEmployeeName;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getPasswordUpdatedOn() {
        return passwordUpdatedOn;
    }

    public void setPasswordUpdatedOn(String passwordUpdatedOn) {
        this.passwordUpdatedOn = passwordUpdatedOn;
    }

    public String getLastLoginOn() {
        return lastLoginOn;
    }

    public void setLastLoginOn(String lastLoginOn) {
        this.lastLoginOn = lastLoginOn;
    }

    public String getLogedIn() {
        return logedIn;
    }

    public void setLogedIn(String logedIn) {
        this.logedIn = logedIn;
    }

    public String getfCM() {
        return fCM;
    }

    public void setfCM(String fCM) {
        this.fCM = fCM;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getReportingTo() {
        return reportingTo;
    }

    public void setReportingTo(String reportingTo) {
        this.reportingTo = reportingTo;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getDepartement() {
        return departement;
    }

    public void setDepartement(int departement) {
        this.departement = departement;
    }

    public ArrayList<Integer> getZone() {
        return zone;
    }

    public void setZone(ArrayList<Integer> zone) {
        this.zone = zone;
    }

    public Object getReportingDetails() {
        return reportingDetails;
    }

    public void setReportingDetails(Object reportingDetails) {
        this.reportingDetails = reportingDetails;
    }

    public RoleDetails getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(RoleDetails roleDetails) {
        this.roleDetails = roleDetails;
    }

    public ArrayList<ZoneDetail> getZoneDetails() {
        return zoneDetails;
    }

    public void setZoneDetails(ArrayList<ZoneDetail> zoneDetails) {
        this.zoneDetails = zoneDetails;
    }

    public DepartementDetails getDepartementDetails() {
        return departementDetails;
    }

    public void setDepartementDetails(DepartementDetails departementDetails) {
        this.departementDetails = departementDetails;
    }

    public ArrayList<ProjectSetting> getProjectSetting() {
        return projectSetting;
    }

    public void setProjectSetting(ArrayList<ProjectSetting> projectSetting) {
        this.projectSetting = projectSetting;
    }
}
