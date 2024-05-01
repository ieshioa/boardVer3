package com.green.boardver3.comment.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentGetRes {
    // 댓글 pk 댓글내용 댓글작성자pk 댓글작성자이름 댓글작성일시
    private long commentId;
    private String contents;
    private long writerId;
    private String writerName;
    private String createdAt;
}
