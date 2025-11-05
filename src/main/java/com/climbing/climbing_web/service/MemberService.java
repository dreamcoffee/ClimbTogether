package com.climbing.climbing_web.service;

import com.climbing.climbing_web.dto.MemberDTO;
import com.climbing.climbing_web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public void save(MemberDTO memberDTO){
        
    }
}
