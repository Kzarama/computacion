package co.edu.icesi.ci.injectionexample1.repositories;

import co.edu.icesi.ci.injectionexample1.model.Course;

public class CourseRepositoryImp implements CourseRepository{

	public Course getCourse (String id) {
		return new Course(id, "1");
	}
}
