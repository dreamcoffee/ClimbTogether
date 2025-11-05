package com.climbing.climbing_web.repository;

import com.climbing.climbing_web.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
