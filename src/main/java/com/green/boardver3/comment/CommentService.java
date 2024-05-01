package com.green.boardver3.comment;

import com.green.boardver3.board.model.DeleteBoard;
import com.green.boardver3.comment.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper mapper;

    public int postComment(PostComment p) {
        return mapper.postComment(p);
    }
    public CommentEntity getComment(long commentId) {
        return mapper.getComment(commentId);
    }
    public int delComment(DeleteComment p) {
        return mapper.delComment(p);
    }
    public int putComment (PutComment p){
        return mapper.putComment(p);
    }
    public List<CommentGetRes> getComments (CommentPaging p){
        return mapper.getComments(p);
    }
    public int delCommentsWithBoard (DeleteBoard p) {
        return mapper.delCommentsWithBoard(p);
    }
    public int getCommentsCount (long boardId) {
        return mapper.getCommentsCount(boardId);
    }
}