package com.example.exercise5;

public class ComponentStaffList {

    private byte[] imageBody;
    private String staffId;
    private String staffName;
    private String staffSalary;
    private String staffDepart;
    private int imageBtnDelete;

    public ComponentStaffList(byte[] imageBody,String staffId,
                              String staffName, String staffSalary,
                              String staffDepart) {
        this.imageBody = imageBody;
        this.staffId = staffId;
        this.staffName = staffName;
        this.staffSalary = staffSalary;
        this.staffDepart = staffDepart;
    }

    public byte[] getImageBody() {
        return imageBody;
    }

    public String getStaffId() {
        return staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStaffSalary() {
        return staffSalary;
    }

    public String getStaffDepart() {
        return staffDepart;
    }

    public int getImageBtnDeleteId() {
        return imageBtnDelete;
    }

    public void setImageBodyId(byte[] imageBodyId) {
        this.imageBody = imageBody;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setStaffSalary(String staffSalary) {
        this.staffSalary = staffSalary;
    }

    public void setStaffDepart(String staffDepart) {
        this.staffDepart = staffDepart;
    }
}
