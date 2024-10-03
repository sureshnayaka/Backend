package com.thiruacademy.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.thiruacademy.controller.StudentController;
import com.thiruacademy.entity.Student;
import com.thiruacademy.exception.StudentNotFoundException;
import com.thiruacademy.repository.StudentRepository;
import com.thiruacademy.vo.Department;

@Service
public class StudentService {
	
	Logger logger = LoggerFactory.getLogger(StudentService.class);
	
	//private String baseUrl = "http://localhost:8080/department/";
	private String baseUrl = "http://DEPARTMENTSERVICE:8082/department/";
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}
	
	public String getStudentWithDepartment(Long id) {
		//Student student = studentRepository.findById(id).get();
		Student student = studentRepository.findById(id)
				   .orElseThrow(() -> new StudentNotFoundException(id));
		Department department = restTemplate.getForObject(baseUrl+student.getDepartmentId(), Department.class);
		StringBuffer buffer = new StringBuffer();
		buffer.append("\n");
		buffer.append(student.getStudentId()+"  "+student.getFirstName()+ "  "+student.getLastName()+"  "+student.getEmail()+"  "+student.getDepartmentId());
		buffer.append("\n");
		buffer.append(department.getDepartmentName()+"  "+department.getDepartmentCode()+"  "+department.getDepartmentAddress());
		logger.info("Student with department details :"+buffer.toString());
		return buffer.toString();
	}
}
