package com.climbing.climbing_web.repository;

import com.climbing.climbing_web.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberRepository{
    private final SqlSessionTemplate sql;

    public void save(MemberEntity memberEntity){
        log.info("회원 정보 데이터베이스 저장[MemberRepository" + memberEntity);
        sql.insert("Member.save",memberEntity);
    }

    public MemberEntity getMemberByID(String memberID) {
        MemberEntity memberEntity = sql.selectOne("Member.findByMemberId", memberID);
        log.info("memberEntity:{}", memberEntity);
        return memberEntity;
    }
}
