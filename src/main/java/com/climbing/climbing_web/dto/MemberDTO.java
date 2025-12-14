package com.climbing.climbing_web.dto;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberDTO {
    private Long id;

    @Size(max = 15, message = "아이디는 최대 15글자까지 가능합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "아이디는 영문과 숫자만 입력 가능합니다.")
    private String member_id;

    private String name;

    @Size(min = 6, max = 20, message = "비밀번호는 6자 이상 20자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9!@#$%^&+=]*$",
            message = "비밀번호는 영문, 숫자, 그리고 일부 특수문자(!@#$%^&+=)만 입력 가능합니다.")
    private String password;
}
