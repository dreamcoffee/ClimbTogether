package com.climbing.climbing_web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberEntity {
    Long id;
    String member_id;
    String name;
    String password;
}
