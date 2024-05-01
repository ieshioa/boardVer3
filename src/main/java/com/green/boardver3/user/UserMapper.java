package com.green.boardver3.user;

import com.green.boardver3.user.model.PatchPassword;
import com.green.boardver3.user.model.PostUserReq;
import com.green.boardver3.user.model.UserEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int postUser (PostUserReq p);
    UserEntity getUser (String uid);
    int patchPassword (PatchPassword p);
}
