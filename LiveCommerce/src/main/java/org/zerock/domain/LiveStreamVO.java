package org.zerock.domain;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;

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
public class LiveStreamVO {
    private int streamID;
    private String title;
    private String url;
    private String description;
    private Timestamp startTime;
    private Timestamp endTime;
    private String photo;
}