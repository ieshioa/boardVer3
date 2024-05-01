package com.green.boardver3.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutComment {
    private long writerId;
    private long commentId;
    private String contents;

}
