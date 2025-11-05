package com.climbing.climbing_web.repository;


import com.climbing.climbing_web.entity.MemberEntity;
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
        sql.insert("Member.save", memberEntity);
    }
}