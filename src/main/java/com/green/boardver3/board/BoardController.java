package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.comment.CommentService;
import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.common.model.Paging;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j // 서비스나 컨트롤러에 무조건 줌
@RestController
@RequiredArgsConstructor
@RequestMapping("board")
@Tag(name = "Board (게시판)", description = "게시판 CRUD")
public class BoardController {

    private final BoardService service;
    private final CommentService commentService;

    @PostMapping("param")
    @Operation(summary = "게시글 등록 (RequestParam)", description = "게시글 등록을 할 수 있습니다.")
    public ResultDto<Integer> postBoard2 (@RequestParam("title") String title, @RequestParam("contents") String contents
            ,  @RequestParam("writerId") long writerId) {
        int result = service.postBoard(title, contents, writerId);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("posting 완료")
                .resultData(result)
                .build();
    }

    @PostMapping
    @Operation(summary = "게시글 등록 (RequestBody)", description = "게시글 등록을 할 수 있습니다.")
    public ResultDto<Long> postBoard (@RequestBody PostBoard p){
        log.info("p의 파라미터: {}",p);
        long result = service.postBoard(p);
        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("posting 완료")
                .resultData(result)
                .build();
    }

    @GetMapping("{page}/{size}")
    public ResultDto<List<GetBoardRes>> getBoardList(@PathVariable int page, @PathVariable int size) {
        Paging p = new Paging(page, size);
        List<GetBoardRes> result = service.getBoardList(p);
        int rowCount = result.size();
        return ResultDto.<List<GetBoardRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(rowCount == 0 ? "내용을 찾을 수 없습니다." : String.format("rowCount : %d", rowCount))
                .resultData(result)
                .build();
    }

    @GetMapping
    public ResultDto<List<GetBoardRes>> getBoardList (@ModelAttribute Paging p){
        List<GetBoardRes> result = service.getBoardList(p);
        int rowCount = result.size();
        return ResultDto.<List<GetBoardRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(rowCount == 0 ? "내용을 찾을 수 없습니다." : String.format("rowCount : %d", rowCount))
                .resultData(result)
                .build();
    }

    @GetMapping("{boardId}")
    public ResultDto<GetBoardRes>getBoardOne (@PathVariable long boardId) {
        GetBoardRes result = service.getBoardOne(boardId);

        return ResultDto.<GetBoardRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result == null ? "내용을 찾을 수 없습니다." : HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }

    @PutMapping
    public ResultDto<Integer> putBoard (@RequestBody PutBoard p) {
        int result = service.putBoard(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("수정 완료")
                .resultData(result)
                .build();
    }

    @DeleteMapping("{boardId}")
    public ResultDto<Integer> delBoard (@ModelAttribute DeleteBoard p) {
        int result = service.delBoard(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("삭제 완료")
                .resultData(result)
                .build();
    }

    @DeleteMapping("del,{boardId}")
    public ResultDto<Integer> delCommentsWithBoard (@ModelAttribute DeleteBoard p) {
        commentService.delCommentsWithBoard(p);
        int result = service.delBoard(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("삭제 완료")
                .resultData(result)
                .build();

    }

}

