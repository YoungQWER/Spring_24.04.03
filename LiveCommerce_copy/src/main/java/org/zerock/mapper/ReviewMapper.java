package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.ReviewVO;

public interface ReviewMapper {

    // 후기 정보 조회
    ReviewVO getReview(int reviewID);

    // 모든 후기 정보 조회
    List<ReviewVO> getAllReviews();

    // 후기 정보 추가
    void insertReview(ReviewVO review);

    // 후기 정보 수정
    void updateReview(ReviewVO review);

    // 후기 정보 삭제
    void deleteReview(int reviewID);
}
