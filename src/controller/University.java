package controller;

import model.FileManager;
import model.Student;
import model.StudentManager;
import utils.Validation;
import view.Menu;

public class University extends Menu<String> {

    static String[] menu = {"Add Student", "Print student name&GPA", "Sort & Print", "Count & Print", "Update&Delete", "Report", "Exit"};
    private Menu<String> options;
    StudentManager sList = new StudentManager();
    Validation val = new Validation();
    Student student = new Student();
    FileManager file = new FileManager();
    
    public University() {
        super("Student Management", menu);
    }

    public University(String t, String[] c) {
        super(t, c);
        this.sList = new StudentManager();
    }

    @Override
    public void execute(int ch) {
        switch (ch) {
            case 1:
                file.loadData();
                sList.menuAddStudent();
                break;
            case 2:
                sList.printNameGPA();
                break;
            case 3:
                sList.sortByName();
                break;
            case 4:
                sList.countStudentSameCity();
                break;
            case 5:
                String id = val.getAndValidValue("Enter id: ", "^(DE|DS)[0-9]{4}$", "Invalid id!");
                sList.findById(id);
                System.out.println("1.Update" + "\n" + "2.Delete");
                int choice = val.getInteger("Enter your choice: ");
                switch (choice) {
                    case 1:
                        sList.updateStudent(student);
                        break;
                    case 2:
                        sList.deleteStudent(id);
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
                break;
            case 6:
                sList.reportGPA();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
    }

    public static void main(String[] args) throws Exception {
        University sm = new University();
        sm.run();
    }
}
