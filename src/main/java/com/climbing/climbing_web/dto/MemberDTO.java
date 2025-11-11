package com.climbing.climbing_web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;
    private String member_id;
    private String name;
    private String password;
}
