package org.zerock.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
    private int pageNum; // 페이지 번호
    private int amount; // 페이지당 표시할 데이터 수
    
    private String productName; // 검색할 제품명
    private String keyword; // 검색 키워드
    
    public Criteria() {
        this(1, 10);
    }
    
    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }   
    
    public int getStartRow() {
        return (pageNum - 1) * amount;
    }

    public int getEndRow() {
        return pageNum * amount;
    }
}
