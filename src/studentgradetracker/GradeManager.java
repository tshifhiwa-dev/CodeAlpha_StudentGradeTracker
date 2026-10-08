package studentgradetracker;

import java.util.*;
public class GradeManager {
	  private ArrayList<Student> students;

	    public GradeManager() {
	        students = new ArrayList<>();
	    }

	    public void addStudent(Student student) {
	        students.add(student);
	    }

	    public Student findStudent(int studentId) {
	        for (Student student : students) {
	            if (student.getStudentId() == studentId) {
	                return student;
	            }
	        }

	        return null;
	    }

	    public double calculateAverage(Student student) {
	        ArrayList<Double> grades = student.getGrades();

	        if (grades.isEmpty()) {
	            return 0;
	        }

	        double total = 0;

	        for (double grade : grades) {
	            total += grade;
	        }

	        return total / grades.size();
	    }

	    public double findHighest(Student student) {
	        ArrayList<Double> grades = student.getGrades();

	        if (grades.isEmpty()) {
	            return 0;
	        }

	        double highest = grades.get(0);

	        for (double grade : grades) {
	            if (grade > highest) {
	                highest = grade;
	            }
	        }

	        return highest;
	    }

	    public double findLowest(Student student) {
	        ArrayList<Double> grades = student.getGrades();

	        if (grades.isEmpty()) {
	            return 0;
	        }

	        double lowest = grades.get(0);

	        for (double grade : grades) {
	            if (grade < lowest) {
	                lowest = grade;
	            }
	        }

	        return lowest;
	    }

	    public ArrayList<Student> getStudents() {
	        return students;
	    }
	    public String getLetterGrade(double average) {

	        if (average >= 80) {
	            return "A";
	        } else if (average >= 70) {
	            return "B";
	        } else if (average >= 60) {
	            return "C";
	        } else if (average >= 50) {
	            return "D";
	        } else {
	            return "F";
	        }
	    }
	    public String getPerformance(double average) {

	        if (average >= 80) {
	            return "Excellent";
	        } else if (average >= 70) {
	            return "Very Good";
	        } else if (average >= 60) {
	            return "Good";
	        } else if (average >= 50) {
	            return "Pass";
	        } else {
	            return "Fail";
	        }
	    }
}
