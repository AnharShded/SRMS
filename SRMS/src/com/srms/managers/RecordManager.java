package com.srms.managers;

import com.srms.models.Student;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecordManager {
    
    private List<Student> students = new ArrayList<>();
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
    //updateStudent gpa
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
// تحميل البيانات من ملف
    public void loadFromFile(String filename) {
        try {
            FileReader fr = new FileReader(filename);
            Scanner sc = new Scanner(fr);

            students.clear();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                 
                if (line.trim().isEmpty() || line.startsWith("id")) continue;
                  
                String[] parts = line.split(",");
                Student s = new Student(parts[0], parts[1], parts[2], Double.parseDouble(parts[3]));
                students.add(s);
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
                    fw.write(s.getId() + "," + s.getName() + "," + s.getDepartment() + "," + s.getGpa() + "\n");
            }
            fw.close();
        } catch (Exception e) {
            System.out.println("Error saving file.");
        }
    }

    public List<Student> getAllStudents() {
        return students;
    }
    // تقرير بسيط حسب GPA
    public void reportByGPA() {
        for (Student s : students) {
            System.out.println(s.getId() + " - " + s.getName() + " - " + s.getGpa());
        }
    }

}


