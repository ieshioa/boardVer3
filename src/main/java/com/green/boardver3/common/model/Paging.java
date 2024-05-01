package com.green.boardver3.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.beans.ConstructorProperties;

@Getter
@Setter
@ToString
public class Paging {
    private int page;
    private int size;
    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int len;

    @ConstructorProperties({"page", "size"})
    public Paging(Integer page, Integer size) {
        this.page = page == null ? 1 : page;
        this.size = size == null ? 10 : size;

        this.startIdx = (this.page - 1) * this.size;
        this.len = this.size;
    }


}
