package Main;
import java.util.Scanner;
import com.srms.models.Student;
import com.srms.models.GraduateStudent;
import com.srms.managers.RecordManager;
import com.srms.managers.ReportManager;
import com.srms.threads.AutoSaveThread;
//import com.srms.utils.FileUtils;

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
            System.out.println("2- Update GPA");
            System.out.println("3- Delete Student");
            System.out.println("4- Load File");
            System.out.println("5- Save File");
            System.out.println("6- Generate Reports");
            System.out.println("7- Exit");
            System.out.print("Choose: ");

            int ch = sc.nextInt();
            sc.nextLine();

            try {
                switch (ch) {
                    case 1:
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

                    case 2:
                        System.out.print("ID to update: ");
                        id = sc.nextLine();
                        System.out.print("New GPA: ");
                        gpa = sc.nextDouble();
                        manager.updateStudent(id,gpa);
                        break;

                    case 3:
                        System.out.print("ID to delete: ");
                        id = sc.nextLine();
                        manager.deleteStudent(id);
                        break;

                    case 4:
                        manager.loadFromFile("data/students.csv");
                        System.out.println("Loaded.");
                        break;

                    case 5:
                        manager.saveToFile("data/students.csv1");
                        System.out.println("Saved.");
                        break;

                    case 6:
                        System.out.println("\n--- Report Menu ---");
                        System.out.println("1- Report By GPA (Highest to Lowest)");
                        System.out.println("2- Top 3 Students");
                        System.out.println("3- Calculate Average GPA");
                        System.out.println("4- Back to Main Menu");
                        System.out.print("Choose Report: ");
                        int reportCh = sc.nextInt();
                        sc.nextLine();
                        manager.reportByGPA();
                        switch (reportCh) {
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
                                break;
                            default:
                                System.out.println("Invalid report option.");
                        }
                        break;
                    case 7:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
