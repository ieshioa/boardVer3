package com.green.boardver3.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostBoard {
    @JsonIgnore
    private long boardId;
    private String title;
    private String contents;
    private long writerId;

}
