package com.example.slava.jpademo.repository;

import com.example.slava.jpademo.JpaDemoApplication;
import com.example.slava.jpademo.entity.Course;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@SpringBootTest(classes = JpaDemoApplication.class)
public class NativeQueryTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EntityManager entityManager;

    @Test
    public void native_basic() {
        Query query = entityManager.createNativeQuery("Select * From Course", Course.class);
        List<Course> resultList = query.getResultList();
        logger.info("Select  c  From Course c -> {}",resultList);
    }

    @Test
    public void createQueryWithParameter() {
        Query query = entityManager.createNativeQuery("select * from course where id = ?", Course.class);
        query.setParameter(1, 10001L);
        List resultList = query.getResultList();
        logger.info("Select  c  From Course c -> {}",resultList);
    }

    @Test
    public void createQueryWithNamedParameter() {
        Query query = entityManager.createNativeQuery("select * from course where id = :id", Course.class);
        query.setParameter("id", 10001L);
        List resultList = query.getResultList();
        logger.info("Select  c  From Course c id -> {}",resultList);
    }

    @Transactional
    @Test
    public void update() {
        Query query = entityManager.createNativeQuery("update course c set last_Updated_Date = sysdate() - 1", Course.class);
        int updated = query.executeUpdate();

        logger.info("Updated rows -> {}", updated);
    }
}
