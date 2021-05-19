package org.mongo.zee.repository;

import org.mongo.zee.model.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{

    List<Student> findByCity(String city, Pageable page);

    List<Student> findByName(String name, Pageable page);

    List<Student> findBySalary(long salary, Pageable page);

    List<Student> findBySalaryGreaterThan(int number, Pageable page);

    List<Student> findBySalaryLessThan(int number, Pageable page);

    List<Student> findBySalaryBetween(int number1, int number2, Pageable page);
}
