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

    public MemberDTO login(MemberDTO memberDTO){

        // 아이디로 DB에서 회원 정보를 조회하여 memberEntity 변수에 담음 (DB에서 가져온 객체)
        MemberEntity memberEntity = memberRepository.findByMemberId(memberDTO.getMember_id());

        if (memberEntity != null) {
            // 비밀번호 비교
            if (memberEntity.getPassword().equals(memberDTO.getPassword())) {

                // 비밀번호 일치 -> 세션에 저장할 DTO를 생성하여 반환
                // 조회된 memberEntity의 정보를 사용
                MemberDTO sessionDTO = new MemberDTO(
                        memberEntity.getId(),
                        memberEntity.getMember_id(),
                        memberEntity.getName(),
                        null
                );
                return sessionDTO; // 로그인 성공 (세션용 DTO 반환)
            }
        }

        // 실패 시
        return null;
    }
}
