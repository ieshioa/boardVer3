package com.green.boardver3.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostComment {
    private String contents;
    private long writerId;
    private long boardId;
}
