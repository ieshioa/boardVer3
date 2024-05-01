package com.green.boardver3.comment.model;

import com.green.boardver3.common.model.Paging;
import lombok.Getter;

@Getter
public class CommentPaging extends Paging {
    private long boardId;

    public CommentPaging(Integer page, Integer size, long boardId) {
        super(page, size);
        this.boardId = boardId;
    }
}
