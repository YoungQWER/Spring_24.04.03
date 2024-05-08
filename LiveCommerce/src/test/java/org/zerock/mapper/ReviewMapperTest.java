package org.zerock.mapper;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.ReviewVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReviewMapperTest {

    @Autowired
    private ReviewMapper mapper;

    @Test
    public void testGetReview() {
        // 특정 후기 정보 조회
        int reviewID = 1; // 여기에 존재하는 유효한 ReviewID 입력
        ReviewVO review = mapper.getReview(reviewID);
        assertNotNull(review);
        log.info("후기 정보 조회: " + review);
    }

    @Test
    public void testGetAllReviews() {
        // 모든 후기 정보 조회
        List<ReviewVO> reviews = mapper.getAllReviews();
        assertNotNull(reviews);
        reviews.forEach(review -> log.info(review));
    }

    @Test
    public void testInsertReview() {
        // 새로운 후기 정보 추가
        ReviewVO newReview = ReviewVO.builder()
                .userID(1) // 사용자 ID 입력
                .productID(1) // 제품 ID 입력
                .rating(5) // 평점 입력
                .comment("테스트 후기") // 후기 내용 입력
                .build();
        mapper.insertReview(newReview);
        assertNotNull(newReview.getReviewID()); // ReviewID가 제대로 생성되었는지 확인
        log.info("새로운 후기 정보 추가: " + newReview);
    }

    @Test
    public void testUpdateReview() {
        // 기존 후기 정보 수정
        int reviewID = 1; // 수정할 후기의 ReviewID 입력
        ReviewVO review = mapper.getReview(reviewID);
        assertNotNull(review);
        review.setRating(4); // 수정할 내용 입력
        review.setComment("수정된 후기 내용");
        mapper.updateReview(review);
        log.info("후기 정보 수정: " + review);
    }

    @Test
    public void testDeleteReview() {
        // 특정 후기 정보 삭제
        int reviewID = 1; // 삭제할 후기의 ReviewID 입력
        mapper.deleteReview(reviewID);
        log.info("후기 정보 삭제: ReviewID=" + reviewID);
    }
}
