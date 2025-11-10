package com.climbing.climbing_web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardDTO {
    private int postid;
    private String postname;
    private String postdetail;
    private String member_id;
    private String writerName;
}
