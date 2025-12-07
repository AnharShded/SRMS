package Main;
import java.util.Scanner;
import com.srms.models.Student;
import com.srms.models.GraduateStudent;
import com.srms.managers.RecordManager;
import com.srms.managers.ReportManager;
import com.srms.threads.AutoSaveThread;

public class Main {
    public static void main(String[] args) {
        RecordManager manager = new RecordManager();
        // for report 
        ReportManager report = new ReportManager(manager);        
        Scanner sc = new Scanner(System.in);

        //to load student data from csv
        manager.loadFromFile("data/students.csv");
        // Start auto-save thread
        new AutoSaveThread(manager, "data/students.csv").start();
        
        while (true) {
            System.out.println("\n1- Add Student");
            System.out.println("2- Add GraduateStudent");
            System.out.println("3- Update GPA");
            System.out.println("4- Delete Student");
            System.out.println("5- Load File");
            System.out.println("6- Save File");
            System.out.println("7- Generate Reports");
            System.out.println("8- Exit");
            System.out.print("Choose: ");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {
                    case 1:// Add normal student
                        System.out.print("ID: ");
                        String id = sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Dept: ");
                        String dept = sc.nextLine();
                        System.out.print("GPA: ");
                        double gpa = sc.nextDouble();

                        manager.addStudent(new Student(id, name, dept, gpa));
                        break;

                    // Add Graduate Student
                    case 2:
                        System.out.print("ID: ");
                        String gid = sc.nextLine();
                        System.out.print("Name: ");
                        String gname = sc.nextLine();
                        System.out.print("Department: ");
                        String gdept = sc.nextLine();
                        System.out.print("GPA: ");
                        double ggpa = sc.nextDouble();
                        sc.nextLine(); // consume
                        System.out.print("Thesis Title: ");
                        String thesis = sc.nextLine();
                        System.out.print("Supervisor: ");
                        String supervisor = sc.nextLine();

                        manager.addGraduatSStudent(
                                new GraduateStudent(gid, gname, gdept, ggpa, thesis, supervisor));
                        break;
                    case 3:
                        System.out.print("ID to update: ");
                        id = sc.nextLine();
                        System.out.print("New GPA: ");
                        gpa = sc.nextDouble();
                        manager.updateStudent(id,gpa);
                        break;

                    case 4:
                        System.out.print("ID to delete: ");
                        id = sc.nextLine();
                        manager.deleteStudent(id);
                        break;

                    case 5:
                        manager.loadFromFile("data/students.csv");
                        System.out.println("Loaded.");
                        break;

                    case 6:
                        manager.saveToFile("data/students.csv");
                        System.out.println("Saved.");
                        break;

                    case 7:
                        System.out.println("\n--- Report Menu ---");
                        System.out.println("1- Report By GPA (Highest to Lowest)");
                        System.out.println("2- Top 3 Students");
                        System.out.println("3- Calculate Average GPA");
                        System.out.println("4- List Graduate Students");
                        System.out.println("5- Back to Main Menu");
                        System.out.print("Choose Report: ");

                        int r = sc.nextInt();
                        sc.nextLine();

                        switch (r) {
                            case 1:
                                report.reportByGPA();
                                        
                                break;
                            case 2:
                                report.top3Students();
                                break;
                            case 3:
                                report.averageGPA();
                                break;
                            case 4:
                                report.listGraduateStudents();
                                break;
                            case 5:
                                break;
                        }
                        break;
                    case 8:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
