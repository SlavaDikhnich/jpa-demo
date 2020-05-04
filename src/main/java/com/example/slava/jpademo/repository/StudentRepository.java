package com.example.slava.jpademo.repository;

import com.example.slava.jpademo.entity.Course;
import com.example.slava.jpademo.entity.Passport;
import com.example.slava.jpademo.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class StudentRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Student findById(Long id) {
        return entityManager.find(Student.class, id);
    }

    @Transactional
    public Student save(Student student) {
        if (student.getId() == null) {
            entityManager.persist(student);
        } else {
            entityManager.merge(student);
        }
        return student;
    }

    @Transactional
    public void deleteById(Long id) {
        Student student = findById(id);
        entityManager.remove(student);
    }

    @Transactional
    public Long saveStudentWithPassport() {
        Passport passport = new Passport("Z123456");
        entityManager.persist(passport);
        Student student = new Student("Mike", passport);
        entityManager.persist(student);
        passport.setStudent(student);
        return passport.getId();
    }

    @Transactional
    public void insertHardcodedStudentAndCourse(){
        Student student = new Student("Jack", new Passport("08ou09"));
        Course course = new Course("Microservices in 100 Steps");
        entityManager.persist(student);
        entityManager.persist(course);

        student.addCourse(course);
        course.addStudent(student);
        entityManager.persist(student);
    }

    @Transactional
    public void insertStudentAndCourse(Student student, Course course){
        //Student student = new Student("Jack");
        //Course course = new Course("Microservices in 100 Steps");
        student.addCourse(course);
        course.addStudent(student);

        entityManager.persist(student);
        entityManager.persist(course);
    }

}
