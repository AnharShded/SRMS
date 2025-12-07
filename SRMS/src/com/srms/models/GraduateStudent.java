package com.srms.models;
public class GraduateStudent extends Student {
    private String thesisTitle;
    private String supervisor;

    public GraduateStudent(String id, String name, String department,double gpa,
                           String thesisTitle, String supervisor) {
        super(id, name, department, gpa);
        this.thesisTitle = thesisTitle;
        this.supervisor = supervisor;
    }

    @Override
    public String toString() {
        return super.toString() +
               ", Thesis: " + thesisTitle +
               ", Supervisor: " + supervisor;
    }

    @Override
    public String toCSV() {
        return "Graduate Student," + getId() + "," + getName() + "," + 
                getDepartment() + "," + getGpa()+ "," + thesisTitle + "," + supervisor;
    }
}