package com.climbing.climbing_web.service;

import com.climbing.climbing_web.entity.MemberEntity;
import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO){
        // DTO를 Entity로 변환 (DB에 저장하기 위함)
        MemberEntity memberEntity = new MemberEntity(
                null,
                memberDTO.getMember_id(),
                memberDTO.getName(),
                memberDTO.getPassword()
        );

        // Repository를 통해 DB에 저장
        memberRepository.save(memberEntity);
    }
}
