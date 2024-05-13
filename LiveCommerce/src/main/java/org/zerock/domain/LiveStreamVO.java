package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
CREATE TABLE LiveStreams (
	    StreamID INT PRIMARY KEY AUTO_INCREMENT,
	    Title VARCHAR(100) NOT NULL,
	    URL VARCHAR(255) NOT NULL,
	    Description TEXT,
	    StartTime TIMESTAMP NOT NULL,
	    EndTime TIMESTAMP,
	    Photo VARCHAR(255) -- 사진의 경로를 저장할 열
	);
*/

@Data
@AllArgsConstructor
@NoArgsConstructor  // 기본 생성자 추가
@Builder
//라이브스트리밍 정보 VO 클래스
public class LiveStreamVO {
 private int streamID; // 스트리밍 ID
 private String title; // 제목
 private String url; // URL
 private String description; // 설명
 private Timestamp startTime; // 시작 시간
 private Timestamp endTime; // 종료 시간
 private String photo; // 사진의 경로
 private int productID; //상품 ID
 private int chatID; //체팅 ID
 
 // 생성자, Getter 및 Setter
}