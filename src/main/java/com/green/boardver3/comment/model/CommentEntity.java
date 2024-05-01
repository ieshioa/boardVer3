package com.green.boardver3.comment.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CommentEntity {
    private long commentId;
    private String contents;
    private long writerId;
    private long boardId;
    private String createdAt;
    private String updatedAt;

}
