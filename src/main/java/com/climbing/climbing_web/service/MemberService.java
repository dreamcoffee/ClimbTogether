package com.climbing.climbing_web.service;

import com.climbing.climbing_web.Entity.MemberEntity;
import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    // MemberRepository 주입
    private final MemberRepository memberRepository;

    public void save(MemberDTO memberDTO){
        // 1. DTO를 Entity로 변환 (DB에 저장하기 위함)
        // DTO의 필드를 Entity의 생성자에 맞춰 주입
        MemberEntity memberEntity = new MemberEntity(
                null, // ID는 DB에서 자동 생성되므로 null
                memberDTO.getMemberName(),
                memberDTO.getMemberEmail(),
                memberDTO.getMemberPassword()
        );

        // 2. Repository를 통해 DB에 저장
        memberRepository.save(memberEntity);
    }
}
