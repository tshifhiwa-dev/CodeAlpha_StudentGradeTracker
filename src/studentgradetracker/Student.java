package studentgradetracker;
import java.util.*;
public class Student {
	  private int studentId;
	    private String name;
	    private ArrayList<Double> grades;

	    public Student(int studentId, String name) {
	        this.studentId = studentId;
	        this.name = name;
	        this.grades = new ArrayList<>();
	    }

	    public int getStudentId() {
	        return studentId;
	    }

	    public String getName() {
	        return name;
	    }

	    public ArrayList<Double> getGrades() {
	        return grades;
	    }
	    public boolean addGrade(double grade) {

	        if (grade < 0 || grade > 100) {
	            return false;
	        }

	        grades.add(grade);
	        return true;
	    }}
