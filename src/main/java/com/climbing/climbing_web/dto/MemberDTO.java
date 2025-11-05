package com.climbing.climbing_web.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long id;
    private String member_id;
    private String name;
    private String password;
}
