package com.climbing.climbing_web.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

// 새로 추가한 의존성인 jpa는 데이터베이스 테이블을 일종의 자바 객체처럼 사용 할 수 있도록 함
@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(unique = true)
    private String memberEmail;

    @Column
    private String memberPassword;

    @Column
    private String memberName;
}
