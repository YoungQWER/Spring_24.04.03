package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.ReviewVO;
import org.zerock.mapper.ProductMapper;
import org.zerock.mapper.ReviewMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;



    @Override
    public ReviewVO getReview(int reviewID) {
        return reviewMapper.getReview(reviewID);
    }

    @Override
    public List<ReviewVO> getAllReviews() {
        return reviewMapper.getAllReviews();
    }

    @Override public void insertReview(ReviewVO review) {
        reviewMapper.insertReview(review);
        // 여기서 다른 비즈니스 로직 처리 가능
    }

    @Override
    public void updateReview(ReviewVO review) {
        reviewMapper.updateReview(review);
        // 여기서 다른 비즈니스 로직 처리 가능
    }

    @Override
    public void deleteReview(int reviewID) {
        reviewMapper.deleteReview(reviewID);
        // 여기서 다른 비즈니스 로직 처리 가능
    }
}