package com.srms.managers;

import com.srms.models.Student;
import com.srms.models.GraduateStudent;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordManager {
    
    private List<Student> students = new ArrayList<>();
    private List<GraduateStudent> gs = new ArrayList<>();

    //add student
    public boolean addStudent(Student s) {
        for (Student st : students) {
        if (st.getId().equals(s.getId())) {
            System.out.println("Error: ID already exists!");
            return false;
        }
    }
    students.add(s);
               return true;
    }
    
    public boolean addGraduatSStudent(GraduateStudent graduatS) {
    for (GraduateStudent grs : gs) {
        if (grs.getId().equals(graduatS.getId())) {
            System.out.println("Error: ID already exists!");
            return false;
        }
    }
    gs.add(graduatS);   //  إضافة  إلى قائمة الخريجين
    return true;
}

    //update Student gpa
 public boolean updateStudent(String id, double newGpa) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                s.setGpa(newGpa);
                return true;
            }
        }
        return false;
    }
    //deleteStudent 
    public boolean deleteStudent(String id) {
        return students.removeIf(s -> s.getId().equals(id));
    }

    public Student findById(String id) {
       for (Student s : students) {
        if (s.getId().equals(id)) return s;
    }
        return null;
    }
        // تحميل البيانات من ملف  Read CSV 
    public void loadFromFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            Scanner sc = new Scanner(fr);

            students.clear();
            
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.trim().isEmpty() || line.startsWith("id")) continue;
                  
                String[] p = line.split(",");
                String type = p[0];
                String id = p[1];
                String name = p[2];
                String dept = p[3];
                double gpa = Double.parseDouble(p[4]);

                if (type.equals("STU")) {
                    students.add(new Student(id, name, dept, gpa));
                } else if (type.equals("GRAD")) {
                    String thesis = p[5];
                    String supervisor = p[6];
                    students.add(new GraduateStudent(id, name, dept, gpa, thesis, supervisor));
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("Error loading file.");
        }
    }
   
 // حفظ البيانات في ملف
    public void saveToFile(String filename) {
        try {
            FileWriter fw = new FileWriter(filename);
            for (Student s : students) {
 // Normal student
                if (!(s instanceof GraduateStudent)) {
                    fw.write("STU," + s.getId() + "," + s.getName() + "," +
                            s.getDepartment() + "," + s.getGpa() + ",,\n");
                }

                // Graduate student
                else {
                    GraduateStudent g = (GraduateStudent) s;
                    fw.write("GRAD," + g.getId() + "," + g.getName() + "," +
                            g.getDepartment() + "," + g.getGpa() + "," +
                            g.getThesisTitle() + "," + g.getSupervisor() + "\n");
                }            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    public List<Student> getAllStudents() {
        return students;
    }
    
    public List<GraduateStudent> getAllGraduatStudents() {
        return gs;
    }
    
    // تقرير بسيط حسب GPA
    public void reportByGPA() {
        for (Student s : students) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getGpa());
        }
    }

}


