package studentgradetracker;
import java.util.*;
public class Main {
	  public static void main(String[] args) {

	        Scanner scanner = new Scanner(System.in);
	        GradeManager manager = new GradeManager();

	        boolean running = true;

	        while (running) {

	            System.out.println("\n=================================");
	            System.out.println("       STUDENT GRADE TRACKER");
	            System.out.println("=================================");
	            System.out.println("1. Add Student");
	            System.out.println("2. Add Grade");
	            System.out.println("3. View Student Grades");
	            System.out.println("4. View Student Report");
	            System.out.println("5. View All Students");
	            System.out.println("6. Exit");
	            System.out.println("=================================");
	       

	            int choice = getIntegerInput(scanner, "Enter your choice: ");

	            switch (choice) {

	                case 1:
	                    addStudent(scanner, manager);
	                    break;

	                case 2:
	                    addGrade(scanner, manager);
	                    break;

	                case 3:
	                    viewStudentGrades(scanner, manager);
	                    break;

	                case 4:
	                	viewStudentReport(scanner, manager);
	                    break;

	                case 5:
	                    viewAllStudents(manager);
	                    break;

	                case 6:
	                    running = false;
	                    System.out.println("\nThank you for using Student Grade Tracker!");
	                    break;

	                default:
	                    System.out.println("\nInvalid choice. Please try again.");
	            }
	        }

	        scanner.close();
	    }

	    private static void addStudent(Scanner scanner, GradeManager manager) {

	    	int studentId = getIntegerInput(scanner, "\nEnter student ID: ");

	        System.out.print("Enter student name: ");
	        String name = scanner.nextLine();

	        if (manager.findStudent(studentId) != null) {
	            System.out.println("A student with that ID already exists.");
	            return;
	        }

	        Student student = new Student(studentId, name);
	        manager.addStudent(student);

	        System.out.println("Student added successfully!");
	    }

	    private static void addGrade(Scanner scanner, GradeManager manager) {

	        int studentId = getIntegerInput(scanner, "\nEnter student ID: ");

	        Student student = manager.findStudent(studentId);

	        if (student == null) {
	            System.out.println("Student not found.");
	            return;
	        }

	        boolean addingGrades = true;

	        while (addingGrades) {

	            double grade = getGradeInput(scanner);

	            if (student.addGrade(grade)) {
	                System.out.println("Grade added successfully!");
	            }

	            System.out.print("Add another grade? (y/n): ");
	            String answer = scanner.nextLine();

	            if (answer.equalsIgnoreCase("n")) {
	                addingGrades = false;
	            } else if (!answer.equalsIgnoreCase("y")) {
	                System.out.println("Invalid choice. Returning to main menu.");
	                addingGrades = false;
	            }
	        }

	        System.out.println("Returning to main menu...");
	    }

	    private static void viewStudentGrades(Scanner scanner, GradeManager manager) {

	    	int studentId = getIntegerInput(scanner, "\nEnter student ID: ");

	        Student student = manager.findStudent(studentId);

	        if (student == null) {
	            System.out.println("Student not found.");
	            return;
	        }

	        System.out.println("\nStudent: " + student.getName());
	        System.out.println("ID: " + student.getStudentId());
	        System.out.println("Grades: " + student.getGrades());
	    }

	    private static void viewStudentReport(Scanner scanner, GradeManager manager) {

	    	int studentId = getIntegerInput(scanner, "\nEnter student ID: ");

	        Student student = manager.findStudent(studentId);

	        if (student == null) {
	            System.out.println("Student not found.");
	            return;
	        }

	        if (student.getGrades().isEmpty()) {
	            System.out.println("This student has no grades yet.");
	            return;
	        }

	        double average = manager.calculateAverage(student);
	        String letterGrade = manager.getLetterGrade(average);
	        double highest = manager.findHighest(student);
	        double lowest = manager.findLowest(student);
	        String performance = manager.getPerformance(average);
	        System.out.println("\n========================================");
	        System.out.println("           STUDENT GRADE REPORT");
	        System.out.println("========================================");
	        System.out.println("Student Name     : " + student.getName());
	        System.out.println("Student ID       : " + student.getStudentId());
	        System.out.println("----------------------------------------");
	        System.out.println("Number of Grades : " + student.getGrades().size());
	        System.out.println("Grades           : " + student.getGrades());
	        System.out.println();
	        System.out.printf("Average          : %.2f%n", average);
	        System.out.println("Letter Grade     : " + letterGrade);
	        System.out.printf("Highest Grade    : %.2f%n", highest);
	        System.out.printf("Lowest Grade     : %.2f%n", lowest);
	        System.out.println("Performance      : " + performance);
	        System.out.println("========================================");
	    }

	    private static void viewAllStudents(GradeManager manager) {

	        if (manager.getStudents().isEmpty()) {
	            System.out.println("\nNo students have been added yet.");
	            return;
	        }

	        System.out.println("\n========================================================");
	        System.out.println("                    ALL STUDENTS");
	        System.out.println("========================================================");
	        System.out.printf("%-8s %-16s %-10s %-12s %-15s%n",
	                "ID", "Name", "Grades", "Average", "Performance");
	        System.out.println("--------------------------------------------------------");

	        for (Student student : manager.getStudents()) {

	        	int numberOfGrades = student.getGrades().size();

	        	if (numberOfGrades == 0) {

	        	    System.out.printf("%-8d %-16s %-10d %-12s %-15s%n",
	        	            student.getStudentId(),
	        	            student.getName(),
	        	            numberOfGrades,
	        	            "N/A",
	        	            "No grades");

	        	} else {

	        	    double average = manager.calculateAverage(student);
	        	    String performance = manager.getPerformance(average);

	        	    System.out.printf("%-8d %-16s %-10d %-12.2f %-15s%n",
	        	            student.getStudentId(),
	        	            student.getName(),
	        	            numberOfGrades,
	        	            average,
	        	            performance);
	        	}
	        }

	        System.out.println("========================================================");
	    }
	    private static int getIntegerInput(Scanner scanner, String message) {

	        while (true) {

	            System.out.print(message);

	            if (scanner.hasNextInt()) {
	                int value = scanner.nextInt();
	                scanner.nextLine();
	                return value;
	            }

	            System.out.println("Invalid input. Please enter a whole number.");
	            scanner.nextLine();
	        }
	    }

	    private static double getGradeInput(Scanner scanner) {

	        while (true) {

	            System.out.print("Enter grade (0 - 100): ");

	            if (scanner.hasNextDouble()) {

	                double grade = scanner.nextDouble();
	                scanner.nextLine();

	                if (grade >= 0 && grade <= 100) {
	                    return grade;
	                }

	                System.out.println("Grade must be between 0 and 100.");

	            } else {

	                System.out.println("Invalid input. Please enter a number.");
	                scanner.nextLine();
	            }
	        }
	    }
	
}
