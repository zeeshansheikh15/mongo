package org.mongo.zee.controller;

import java.util.List;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.mongo.zee.model.Student;
import org.mongo.zee.repository.StudentRepo;
import org.mongo.zee.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {
	
	private StudentRepository studentRepository;
	
	private StudentRepo studentRepo;

	@Autowired
	private MongoTemplate mongoTemplate;

	public StudentController(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	
	@GetMapping("/{pageno}")
	public Page<Student> getStudents(@PathVariable Integer pageno) {
		List<Student> li = studentRepository.findAll();
		for(Student stu: li) {
			System.out.print(stu.getName());
		}
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		return studentRepository.findAll(paging);
	}
	
	@PostMapping("/")
	public void addStudents(@RequestBody Student student) {
		studentRepository.save(student);
	
	}

	@PutMapping("/{city}")
	public void addStudents(@RequestBody Student student, @PathVariable String city) {
		student.setCity(city);
		studentRepository.save(student);
	
	}	
	@DeleteMapping("/{id}")
	public void deleteStudents(@RequestBody Student student, @PathVariable String id) {
		studentRepository.deleteById(id);
	
	}	
	
	@DeleteMapping("/")
	public void deleteAll() {
		studentRepository.deleteAll();
	
	}
	
	
	
	@GetMapping("/filterlt/{number}")
	public List <Student> filterStudentlt(@PathVariable Integer number) {
		return mongoTemplate.find(query(where("salary").lt(number)) , Student.class);
	// .and("id").gt(3))
	}
	
	
	
	@GetMapping("/filterltgt/{lt}/{gt}")
	public List <Student> filterStudentltgt(@PathVariable Integer lt, @PathVariable Integer gt) {
		return mongoTemplate.find(query(where("salary").lt(lt).gte(gt)) , Student.class);
	// 
	}
	
	
	@GetMapping("/filterin")
	public List <Student> filterStudentin() {
		return mongoTemplate.find(query(where("salary").in(500000, 20000, 10000).and("id").is("4")) , Student.class);
	// 
	}
	

	
	@GetMapping("/filterne")
	public List <Student> filterStudentne() {
	return mongoTemplate.find(query(where("salary").ne(10000).and("id").ne("7")) , Student.class);

	// 
	}




	@GetMapping("/page/{pageno}")
	public Page<Student> paging(@PathVariable Integer pageno) {
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		Page<Student> students = studentRepository.findAll(paging);
		return students;
	}


	@GetMapping("/name/{name}/{pageno}")
	public List<Student> findbyname(@PathVariable String name, @PathVariable Integer pageno) {
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		return studentRepository.findByName(name, paging);
		//
	}

	@GetMapping("/city/{city}/{pageno}")
	public List<Student> findbycity(@PathVariable String city, @PathVariable Integer pageno) {
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		return studentRepository.findByCity(city, paging);
		//
	}


	@GetMapping("/salgreat/{great}/{pageno}")
	public List<Student> salgreat(@PathVariable Integer great, @PathVariable Integer pageno) {
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		return studentRepository.findBySalaryGreaterThan(great, paging);
		//
	}


	@GetMapping("/salless/{less}/{pageno}")
	public List<Student> salless(@PathVariable Integer less, @PathVariable Integer pageno) {
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		return studentRepository.findBySalaryLessThan(less, paging);
		//
	}


	@GetMapping("/salbetw/{less}/{great}/{pageno}")
	public List<Student> salbetw(@PathVariable Integer less, @PathVariable Integer great, @PathVariable Integer pageno) {
		Pageable paging = PageRequest.of(pageno, 3, Sort.Direction.ASC, "salary");
		return studentRepository.findBySalaryBetween(great, less, paging);
		//
	}




}
