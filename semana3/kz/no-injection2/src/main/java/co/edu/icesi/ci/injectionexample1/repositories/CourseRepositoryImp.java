package co.edu.icesi.ci.injectionexample1.repositories;

import org.springframework.stereotype.Repository;

import co.edu.icesi.ci.injectionexample1.model.Course;

@Repository
public class CourseRepositoryImp implements CourseRepository{

	public Course getCourse (String id) {
		return new Course(id, "name");
	}
}
