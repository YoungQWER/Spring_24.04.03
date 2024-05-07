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
public class EventVO {
	
	private int eventID;
	private String eventName;
	private String description;
	private Timestamp eventDate;
	private String location;
	private String photo;

}
