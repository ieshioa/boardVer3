package com.green.boardver3.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PatchPassword {
    private String uid;
    private String upw;
    private String newPw;
    @JsonIgnore
    private long userId;
}
