package com.climbing.climbing_web.service;

import com.climbing.climbing_web.dto.BoardDTO;
import com.climbing.climbing_web.dto.LoginDTO;
import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.entity.MemberEntity;
import com.climbing.climbing_web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDto){
        MemberEntity memberEntity = new MemberEntity();

        memberEntity.setName(memberDto.getName());
        memberEntity.setPassword(memberDto.getPassword());
        memberEntity.setMember_id(memberDto.getMember_id());

        memberRepository.save(memberEntity);
    }

    public MemberDTO login(LoginDTO loginDTO) throws Exception {
        log.info("로그인 정보 비교");
        MemberEntity memberEntity = memberRepository.getMemberByID(loginDTO.getMember_id());
        if(memberEntity == null){
            log.info("아이디 존재x");
            throw new Exception("아이디가 존재하지 않습니다.");
        }
        if(!memberEntity.getPassword().equals(loginDTO.getPassword())){
            log.info("비밀번호 일치x");
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }

        log.info("로그인 성공");
        MemberDTO loginMember = new MemberDTO();
        loginMember.setId(memberEntity.getId());
        loginMember.setName(memberEntity.getName());
        loginMember.setMember_id(memberEntity.getMember_id());
        loginMember.setPassword(null);
        return loginMember;
    }
}
