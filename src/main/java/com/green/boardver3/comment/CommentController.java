package com.green.boardver3.comment;

import com.green.boardver3.board.BoardService;
import com.green.boardver3.board.model.GetBoardRes;
import com.green.boardver3.comment.model.*;
import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.common.model.ResultDto2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment")
public class CommentController {
    private final CommentService service;
    private final BoardService boardService;

    @PostMapping
    public ResultDto<Integer> postComment (@RequestBody PostComment p){
        int result = service.postComment(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("댓글 포스트")
                .resultData(result)
                .build();
    }

    @GetMapping("getOne,{commentId}")
    public ResultDto<CommentEntity> getComment (@PathVariable long commentId) {
        CommentEntity result = service.getComment(commentId);
        return ResultDto.<CommentEntity>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("댓글 하나 불러오기")
                .resultData(result)
                .build();
    }

    @GetMapping("getList,{boardId}")
    public ResultDto<List<CommentGetRes>> getComments (@ModelAttribute CommentPaging p) {
        List<CommentGetRes> list = service.getComments(p);
        int length = list.size();
      //  String result = list.size() == 0 ? "댓글이 없습니다." : String.format("%d개 댓글", list.size());
        String result = list.isEmpty() ? "댓글이 없습니다." : String.format("%d개 댓글", length);

    // 전체 레코드 수  1. 계산해서 구하기
        if(length < p.getSize() && length > 0) {
            int totalRows = (p.getPage() - 1) * p.getLen() + length;
            result = String.format("row: %d, totalRows: %d", length, totalRows);
        }
        // 2. 쿼리에서 COUNT로 구하기
//        if(length < p.getSize()) {
//            int totalRows = service.getCommentsCount(p.getBoardId());
//            result = String.format("row: %d, totalRows: %d", length, totalRows);
//        }

        return ResultDto.<List<CommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result)
                .resultData(list)
                .build();
    }


    @GetMapping("{boardId}")
    public ResultDto2<GetBoardRes,List<CommentGetRes>> getBoardWithComments (@ModelAttribute CommentPaging p) {
        GetBoardRes board = boardService.getBoardOne(p.getBoardId());
        List<CommentGetRes> comments = service.getComments(p);
        String result = board == null ? "내용을 찾을 수 없습니다." :
                comments.size() == 0 ? "댓글이 없습니다." : String.format("%d개 댓글", comments.size());


        return ResultDto2.<GetBoardRes, List<CommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result)
                .resultData1(board)
                .resultData2(comments)
                .build();
    }

    @DeleteMapping("{commentId}")
    public ResultDto<Integer> delComment(@ModelAttribute DeleteComment p){
        int result = service.delComment(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("댓글 삭제 완료")
                .resultData(result)
                .build();
    }

    @PutMapping
    public ResultDto<Integer> putComment(@RequestBody PutComment p){
        int result = service.putComment(p);
        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("댓글 수정")
                .resultData(result)
                .build();
    }
}