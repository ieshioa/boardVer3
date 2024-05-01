package com.green.boardver3.board.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PutBoard {
    private long writerId;
    private long boardId;
    private String title;
    private String contents;
}
