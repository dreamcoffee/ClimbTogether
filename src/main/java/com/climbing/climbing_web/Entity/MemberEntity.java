package com.climbing.climbing_web.Entity;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberEntity {
    private Long id;
    private String memberName;
    private String memberEmail;
    private String memberPassword;
}
