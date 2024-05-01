package com.green.boardver3.board.model;

import com.green.boardver3.comment.model.CommentGetRes;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class GetBoardRes {
    private long boardId;
    private String title;
    private String contents;
    private long writerId;
    private int hits;
    private String createdAt;
    private String updatedAt;
    private int totalCommentPage;
    private List<CommentGetRes> comments;
}