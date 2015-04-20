package com.mifmif.gefmmat.testbed.student;

import java.util.ArrayList;
import java.util.List;

/**
 * any student that join the system register itself using this class, this class
 * is used to lookup for status of each of registered students
 * 
 * @author y.mifrah
 *
 */
public class StudentStatusRegister {
	private static volatile List<Student> students = new ArrayList<Student>();

	public static List<Student> getStudents() {
		return students;
	}

	public static synchronized void addStudent(Student student) {
		students.add(student);
	}
}
