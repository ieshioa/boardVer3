package com.green.boardver3.comment;

import com.green.boardver3.board.model.DeleteBoard;
import com.green.boardver3.comment.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    int postComment (PostComment p);
    CommentEntity getComment(long commentId);
    int delComment(DeleteComment p);
    int putComment(PutComment p);
    List<CommentGetRes> getComments(CommentPaging p);
    int delCommentsWithBoard (DeleteBoard p);
    int getCommentsCount (long boardId);
    int getTotalCommentPage (CommentPaging p);
}
