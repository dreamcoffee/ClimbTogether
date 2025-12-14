package com.climbing.climbing_web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentDTO {
    private int commentId;
    private int postId;
    private String memberId;
    private String writerName;
    private String commentDetail;
}
