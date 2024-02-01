package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import utils.Validation;

public class StudentManager {

    ArrayList<Student> sList = new ArrayList<>();
    ArrayList<ITStudent> ITList = new ArrayList<>();
    ArrayList<BizStudent> BizList = new ArrayList<>();

    Address address;
    ITStudent it;
    BizStudent biz;
    Validation val = new Validation();
    Student student = new Student();
    FileManager file = new FileManager();

    public StudentManager() {
    }

    public void menuAddStudent() {
        file.loadData();
        System.out.println("Choose student major: ");
        System.out.println("1. IT Student" + "\n" + "2. Biz Student" + "\n" + "3. Exit");
        while (true) {
            int choice = Integer.parseInt(val.getAndValidValue("Enter your choice: ", "^[1-3]$", "Invalid choice!"));
            switch (choice) {
                case 1:
                    addITStudent();
                    showStudent();
                    continue;
                case 2:
                    addBizStudent();
                    showStudent();
                    continue;
                case 3:
                    System.out.println("Return to menu..");
                    return;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
            return;
        }
    }

    public void addStudent() {
        String ID = val.getAndValidValue("Enter student ID: ", "^(DE|DS)[0-9]{6}$", "Invalid ID!");
        if (ID.startsWith("DE")) {
            if (!val.checkDuplicateITID(ITList, ID)) {
                ID = it.getID();
            }
        } else if (ID.startsWith("DS")) {
            if (!val.checkDuplicateBizID(BizList, ID)) {
                ID = biz.getID();
            }
        }
        String fullName = val.getAndValidValue("Enter student name: ", "[\\pL]+", "Invalid name!".toUpperCase());
        String country = val.getAndValidValue("Enter country: ", "[\\pL]+", "Invalid country!".toUpperCase());
        String city = val.getAndValidValue("Enter city: ", "[\\pL]+", "Invalid city!".toUpperCase());
        String district = val.getAndValidValue("Enter district:", "[\\pL]+", "Invalid district!".toUpperCase());
        String street = val.getAndValidValue("Enter street: ", "[\\pL]+", "Invalid street!".toUpperCase());
        address = new Address(country, city, district, street);
        student = new Student(ID, fullName, address, student.getAverageScore());
    }

    public void addITStudent() {
        addStudent();
        double javaScore = val.getAndValidScore("Enter java score: ");
        double cssScore = val.getAndValidScore("Enter css score: ");
        it = new ITStudent(student.getID(), student.getFullName(), address, javaScore, cssScore, it.calculateITAverageScore());
        ITList.add(it);
        sList.add(it);
        file.saveData();
    }

    public void addBizStudent() {
        addStudent();
        double accountingScore = val.getAndValidScore("Enter accounting score: ");
        double marketingScore = val.getAndValidScore("Enter marketing score: ");
        biz = new BizStudent(student.getID(), student.getFullName(), address, accountingScore, marketingScore, biz.calculateBizAverageScore());
        BizList.add(biz);
        sList.add(biz);
        file.saveData();
    }

    public void showStudent() {
        file.loadData();
        for (Student student : sList) {
            System.out.println("List of Student:");
            System.out.println(student);
        }
        if (sList.isEmpty()) {
            System.out.println("Empty list");
        }
    }

    public void printNameGPA() {
//        file.export();

    }

    public void reportGPA() {
        file.loadData();
        for (ITStudent it : ITList) {
            System.out.println(it.getFullName());
            int count = 0;
            if (it.getJavaScore() >= 5.0) {
                System.out.println("Java: " + it.getJavaScore() + ":Passed\n");
                count++;
            } else {
                System.out.println("Java: " + it.getJavaScore() + ":Not Passed\n");
            }
            if (it.getCssScore() >= 5.0) {
                System.out.println("CSS: " + it.getCssScore() + ":Passed\n");
                count++;
            } else {
                System.out.println("CSS: " + it.getCssScore() + ":Not Passed\n");
            }
            System.out.println("Total subject passes: " + count);
            if (it.getAverageScore() >= 5) {
                System.out.println("\n-->>Passed");
            }
        }
        for (BizStudent biz : BizList) {
            System.out.println(biz.getFullName());
            int count = 0;
            if (biz.getAccountingScore() >= 5.0) {
                System.out.println("Accounting: " + biz.getAccountingScore() + ":Passed\n");
                count++;
            } else {
                System.out.println("Accounting: " + biz.getAccountingScore() + ":Not Passed\n");
            }
            if (biz.getMarketingScore() >= 5.0) {
                System.out.println("Marketing: " + biz.getMarketingScore() + ":Passed\n");
                count++;
            } else {
                System.out.println("Marketing: " + biz.getMarketingScore() + ":Not Passed\n");
            }
            System.out.println("Total subject passes: " + count);
            if (biz.getAverageScore() >= 5) {
                System.out.println("\n-->>Passed");
            }
        }
        if (sList.isEmpty()) {
            System.out.println("Empty list");
        }
    }

    public void sortByName() {
        file.loadData();
        Collections.sort(sList, Comparator.comparing(Student::getFullName));
        showStudent();
        file.saveData();
    }

    public void countStudentSameCity() {
        file.loadData();
        int count = 0;
        System.out.println("Student have same city: ");
        for (int i = 0; i < sList.size(); i++) {
            for (int j = 0; j < sList.size(); i++) {
                if (sList.get(i).getAddress().equals(address.getCity())) {
                    count++;
                }
                System.out.println(i + "\n" + j + "\n");
            }
        }
        System.out.println("Total: " + count);
    }

    public void updateStudent(Student student) {
        file.loadData();
        ITStudent it = (ITStudent) new Student();
        BizStudent biz = (BizStudent) new Student();
        while (true) {
            String[] split = student.toString().split(", ");
            for (int i = 0; i < 9; i++) {
                System.out.print((i + 1) + ". " + split[i]);
            }
            System.out.println("10. Back");
            int choice = Integer.parseInt(val.getAndValidValue("Enter your choice: ", "^[1-9]|10$", "Invalid choice!"));
            switch (choice) {
                case 1:
                    System.out.println("ID is fixed. Can not change!");
                    break;
                case 2:
                    String name = val.getAndValidValue("Enter new name: ", "[\\pL]+", "Invalid name!");
                    student.setFullName(name);
                    if (student.getFullName().equals(name)) {
                        System.out.println("Successfully change name.");
                    }
                    break;
                case 3:
                    String country = val.getAndValidValue("Enter new country: ", ".*", "Invalid country!");
                    address.setCountry(country);
                    if (student.getAddress().equals(address)) {
                        System.out.println("Successfully change country.");
                    }
                    break;
                case 4:
                    String city = val.getAndValidValue("Enter new city: ", ".*", "Invalid city!");
                    address.setCity(city);
                    if (student.getAddress().equals(address)) {
                        System.out.println("Successfully change city.");
                    }
                    break;
                case 5:
                    String district = val.getAndValidValue("Enter new district: ", ".*", "Invalid district!");
                    address.setDistrict(district);
                    if (student.getAddress().equals(address)) {
                        System.out.println("Successfully change district.");
                    }
                    break;
                case 6:
                    String street = val.getAndValidValue("Enter new street: ", ".*", "Invalid street!");
                    address.setStreet(street);
                    if (student.getAddress().equals(address)) {
                        System.out.println("Successfully change street.");
                    }
                    break;
                case 7:
                    double fScore = Double.parseDouble(val.getAndValidValue("Enter new score: ", "[0-9](.[0-9]*)?", "Invalid score!"));
                    if (fScore == it.getJavaScore()) {
                        it.setJavaScore(fScore);
                        if (it.getJavaScore() == fScore) {
                            System.out.println("Successfully change score.");
                        }
                    } else {
                        biz.setAccountingScore(fScore);
                        if (biz.getAccountingScore() == fScore) {
                            System.out.println("Successfully change score.");
                        }
                    }
                    break;
                case 8:
                    double sScore = Double.parseDouble(val.getAndValidValue("Enter new score: ", "[0-9](.[0-9]*)?", "Invalid score!"));
                    if (sScore == it.getCssScore()) {
                        it.setCssScore(sScore);
                        if (it.getCssScore() == sScore) {
                            System.out.println("Successfully change score.");
                        }
                    } else {
                        biz.setMarketingScore(sScore);
                        if (biz.getMarketingScore() == sScore) {
                            System.out.println("Successfully change score.");
                        }
                    }
                    break;
                case 9:
                    System.out.println("Average score will be changed whether you updated your specific scores!");
                    break;
                case 10:
                    System.out.println("Exit to main menu..");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
            file.saveData();
        }
    }

    public void deleteStudent(String id) {
        file.loadData();
        int index = -1;
        for (int i = 0; i < sList.size(); i++) {
            if (sList.get(i).getID().equals(id)) {
                index = i;
                break;
            }
        }
        if (index != 1) {
            sList.remove(index);
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Retry!");
        }
        file.saveData();
    }

    public Student findById(String id) {
        file.loadData();
        for (Student student : sList) {
            if (student.getID().equals(id)) {
                System.out.println(student);
                return student;
            }
        }
        return null;
    }
}
