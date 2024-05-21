package org.zerock.service;

import java.sql.Timestamp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.ReviewVO;
import org.zerock.mapper.ReviewMapper;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Test
    public void testGetReview() {
        int reviewID = 1; // 가져올 후기의 ID를 설정하세요.

        ReviewVO review = reviewService.getReview(reviewID);

        log.info("Retrieved Review: " + review);
    }

    @Test
    public void testGetAllReviews() {
        log.info("All Reviews:");
        reviewService.getAllReviews().forEach(review -> log.info(review));
    }

    @Test
    public void testInsertReview() {
        ReviewVO review = ReviewVO.builder()
                .reviewID(10)
                .userID(3) // 사용자 ID 입력
                .productID(1) // 제품 ID 입력
                .rating(5) // 평점 입력
                .comment("테스트 후기") // 후기 내용 입력
                .reviewDate(new Timestamp(System.currentTimeMillis())) // 현재시간 
                .build();

        reviewService.insertReview(review);

        log.info("Inserted Review: " + review);
    }

    @Test
    public void testUpdateReview() {
        ReviewVO review = reviewService.getReview(5);
        review.setComment("Updated review content");

        reviewService.updateReview(review);

        log.info("Updated Review: " + review);
    }

    @Test
    public void testDeleteReview() {
        int reviewID = 2; // 삭제할 후기의 ID를 설정하세요.

        reviewService.deleteReview(reviewID);

        log.info("Deleted Review with ID: " + reviewID);
    }
}