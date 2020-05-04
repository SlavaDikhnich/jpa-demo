package com.example.slava.jpademo.repository;

import com.example.slava.jpademo.JpaDemoApplication;
import com.example.slava.jpademo.entity.Address;
import com.example.slava.jpademo.entity.Passport;
import com.example.slava.jpademo.entity.Student;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest(classes = JpaDemoApplication.class)
class StudentRepositoryTests {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private StudentRepository repository;
	@Autowired
	private EntityManager entityManager;

	@Test
	public void findByIdTest() {
		Student student = repository.findById(20001L);
		assertEquals("Ranga", student.getName());
	}

	@Test
	@DirtiesContext
	public void deleteById_basic() {
		repository.deleteById(20001L);
		assertNull(repository.findById(20001L));
	}

	@Transactional
	@DirtiesContext
	@Test
	public void retrieveStudentAndPassportDetails() {
		Student student = entityManager.find(Student.class, 20001L);
		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
		Assertions.assertEquals("Ranga", student.getName());
		Assertions.assertEquals("E123456", student.getPassport().getNumber());
	}

	@Test
	public void getStudentByPassportTest() {
		Long id = repository.saveStudentWithPassport();
		Passport passport1 = entityManager.find(Passport.class, id);
		logger.info("student by passport: " + passport1.getStudent());
	}

	@Test
	@Transactional
	public void retrieveStudentAndCourses() {
		Student student = entityManager.find(Student.class, 20001L);

		logger.info("student -> {}", student);
		logger.info("courses -> {}", student.getCourses());
	}

	@Test
	@Transactional
	public void setAddressDetails() {
		Student student = entityManager.find(Student.class, 20001L);
		student.setAddress(new Address("No 101", "Some Street", "Hyderabad"));
		entityManager.flush();

		logger.info("student -> {}", student);
		logger.info("passport -> {}", student.getPassport());
		logger.info("address -> {}", student.getAddress());
	}
}
