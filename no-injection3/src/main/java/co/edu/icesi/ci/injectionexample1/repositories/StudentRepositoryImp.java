package co.edu.icesi.ci.injectionexample1.repositories;

import co.edu.icesi.ci.injectionexample1.model.Student;

public class StudentRepositoryImp implements StudentRepository{
	
	public Student getStudent (String id) {
		return new Student(id);
	}
}
