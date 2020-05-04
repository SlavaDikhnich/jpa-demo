package com.example.slava.jpademo.repository;

import com.example.slava.jpademo.JpaDemoApplication;
import com.example.slava.jpademo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.Subgraph;
import java.util.List;

@SpringBootTest(classes = JpaDemoApplication.class)
public class PerformanceTuningTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    public void creatingNPlusOneProblem() {
        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .getResultList();
        logger.info("courses: " + courses.size());
        courses.forEach(course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void solvingNPlusOneProblem_EntityGraph() {
        EntityGraph<Course> graph = entityManager.createEntityGraph(Course.class);
        Subgraph<Object> subgraph = graph.addSubgraph("students");

        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses", Course.class)
                .setHint("javax.persistence.loadgraph", graph)
                .getResultList();
        logger.info("courses: " + courses.size());
        courses.forEach(course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }

    @Test
    @Transactional
    public void creatingNPlusOneProblem_JoinFetch() {
        List<Course> courses = entityManager
                .createNamedQuery("query_get_all_courses_join_fetch", Course.class)
                .getResultList();
        logger.info("courses fetch: " + courses.size());
        courses.forEach(course -> logger.info("Course -> {} Students -> {}", course, course.getStudents()));
    }
}
