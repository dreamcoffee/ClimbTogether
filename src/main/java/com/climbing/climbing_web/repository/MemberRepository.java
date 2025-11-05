package com.climbing.climbing_web.repository;


import com.climbing.climbing_web.Entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final SqlSessionTemplate sql;

    public void save(MemberEntity memberEntity){
        log.info("MemberRepository.save 호출: {}", memberEntity);
        // "Member는 MemberMapper.xml의 <mapper namespace>를 의미합니다.
        // "save"는 <insert id>를 의미합니다.
        sql.insert("Member.save", memberEntity);
    }
}