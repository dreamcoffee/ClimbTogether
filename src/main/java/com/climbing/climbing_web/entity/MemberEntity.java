package com.climbing.climbing_web.entity;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    private Long id;
    private String member_id;
    private String name;
    private String password;
}
