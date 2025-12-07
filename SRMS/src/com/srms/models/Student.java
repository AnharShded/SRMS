package com.srms.models;
public class Student {
    private String id;
    private String name;
    //private int year;
    private String department; 
    private double gpa;
    
    public Student(String id, String name, String department,  double gpa) {
        this.id = id;
        this.name = name;
        //this.year = year;
        this.department = department;
        this.gpa = gpa;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public double getGpa() { return gpa; }
   // public int getYear() { return year; }
    public String getDepartment() { return department; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setGpa(double gpa) { this.gpa = gpa; }
   // public void setYear(int year) { this.year = year; }
    public void setDepartment(String department) { this.department = department; }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", GPA: " + gpa +
               ", Department: " + department;
    }

    public String toCSV() {
        return  "Student," +id + "," + name + "," + gpa + "," + department + ",,";
    }
}
