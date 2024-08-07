package com.green.boardver3.common.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ResultDto2<T, F> {
    private HttpStatus statusCode;
    private String resultMsg;
    private T resultData1;
    private F resultData2;
}
