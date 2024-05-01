package com.green.boardver3.board;

import com.green.boardver3.board.model.*;
import com.green.boardver3.common.model.Paging;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int postBoard(PostBoard p);
    List<GetBoardRes> getBoardList(Paging p);
    GetBoardRes getBoardOne (long boardId);
    int hitsUp(long boardId);
    int putBoard(PutBoard p);
    int delBoard(DeleteBoard p);
    int postBoard(String title, String contents, long writerId);

}
