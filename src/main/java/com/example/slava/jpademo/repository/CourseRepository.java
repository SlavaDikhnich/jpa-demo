package com.example.slava.jpademo.repository;

import com.example.slava.jpademo.entity.Course;
import com.example.slava.jpademo.entity.Review;
import com.example.slava.jpademo.entity.ReviewRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class CourseRepository {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    EntityManager entityManager;

    public Course findById(Long id) {
        return entityManager.find(Course.class, id);
    }

    @Transactional
    public Course save(Course course) {
        if (course.getId() == null) {
            entityManager.persist(course);
        } else {
            entityManager.merge(course);
        }
        return course;
    }

    @Transactional
    public void deleteById(Long id) {
        Course course = findById(id);
        entityManager.remove(course);
    }

    @Transactional
    public void hibernatePlaying() {
        Course course1 = new Course("Web Services in 100 Steps");
        Course course2 = new Course("Angular Js in 100 Steps");

        entityManager.persist(course1);
        entityManager.persist(course2);

        entityManager.flush();

        //entityManager.clear();

        course1.setName("Web Services in 100 Steps - updated");
        course2.setName("Angular Js in 100 Steps - updated");

        // entityManager.refresh(course1);

        entityManager.flush();
    }

    @Transactional
    public void addHardcodedReviewsForCourse() {
        //get the course 10003
        Course course = findById(10003L);
        logger.info("course.getReviews() -> {}", course.getReviews());

        //add 2 reviews to it
        Review review1 = new Review(ReviewRating.FIVE, "Great Hands-on Stuff.");
        Review review2 = new Review(ReviewRating.FIVE, "Hatsoff.");

        //setting the relationship
        course.addReview(review1);
        review1.setCourse(course);

        course.addReview(review2);
        review2.setCourse(course);

        //save it to the database
        entityManager.persist(review1);
        entityManager.persist(review2);
    }

    @Transactional
    public void addReviewsToCourse(Long id, List<Review> reviews) {

        Course course = findById(id);

        reviews.forEach(review -> {
            logger.info("course.getReviews() -> {}", course.getReviews());

            review.setCourse(course);
            course.addReview(review);
            entityManager.persist(review);
        });
    }


}
