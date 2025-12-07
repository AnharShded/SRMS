package com.srms.managers;

import com.srms.models.Student;
import com.srms.models.GraduateStudent;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ReportManager {

    private RecordManager manager;

    public ReportManager(RecordManager manager) {
        this.manager = manager;
    }

    //  ترتيب الطلاب حسب GPA من الأعلى للأقل
    public void reportByGPA() {
        List<Student> list = manager.getAllStudents();

        if (list.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        Collections.sort(list, Comparator.comparingDouble(Student::getGpa).reversed());

        System.out.println("\n===== GPA Report =====");
        for (Student s : list) {
            System.out.println(s.getName() + " - GPA: " + s.getGpa());
        }
    }

    // Report: أعلى 3 طلاب
    public void top3Students() {
        List<Student> list = manager.getAllStudents();

        if (list.size() < 3) {
            System.out.println("Not enough students.");
            return;
        }

        Collections.sort(list, Comparator.comparingDouble(Student::getGpa).reversed());

        System.out.println("\n===== Top 3 Students =====");
        for (int i = 0; i < 3; i++) {
            Student s = list.get(i);
            System.out.println((i+1) + ") " + s.getName() + " - " + s.getGpa());
        }
    }

    // Report: المعدل العام
    public void averageGPA() {
        List<Student> list = manager.getAllStudents();

        if (list.isEmpty()) {
            System.out.println("No students available.");
            return;
        }

        double sum = 0;
        for (Student s : list) {
            sum += s.getGpa();
        }

        double avg = sum / list.size();
        System.out.println("Average GPA: " + avg);
    }
    //listGraduateStudents
public void listGraduateStudents() {
    List<GraduateStudent> grads = manager.getAllGraduatStudents();

    if (grads.isEmpty()) {
        System.out.println("No graduate students found.");
        return;
    }

    System.out.println("\n--- Graduate Students ---");

    for (GraduateStudent g : grads) {
        System.out.println(g.getId() + " - " + g.getName());
    }
}


}
