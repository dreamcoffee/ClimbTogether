package com.climbing.climbing_web.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberEntity {
    Long id;
    private String member_id;
    private String name;
    private String password;
}
