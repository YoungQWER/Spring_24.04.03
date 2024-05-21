package org.zerock.domain;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/*-- 이벤트 정보 테이블
CREATE TABLE Events (
    EventID INT PRIMARY KEY AUTO_INCREMENT,
    EventName VARCHAR(100) NOT NULL,
    Description TEXT,
    EventDate TIMESTAMP NOT NULL,
    Location VARCHAR(200) NOT NULL,
    Photo VARCHAR(255) -- 사진의 경로를 저장할 열
);*/


@Data
@AllArgsConstructor
@Builder
//이벤트 정보 VO 클래스
public class EventVO {
 private int eventID; // 이벤트 ID
 private String eventName; // 이벤트명
 private String description; // 이벤트 설명
 private Timestamp eventDate; // 이벤트 일자
 private String location; // 이벤트 장소
 private String photo; // 이벤트 사진 경로
 private int productID; // 상품 ID
 
 // 생성자, Getter 및 Setter
}
