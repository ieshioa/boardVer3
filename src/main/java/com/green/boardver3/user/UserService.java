package com.green.boardver3.user;

import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.user.model.GetUserReq;
import com.green.boardver3.user.model.PatchPassword;
import com.green.boardver3.user.model.PostUserReq;
import com.green.boardver3.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper mapper;

    public int postUser(PostUserReq p) {
        String hashedpw = BCrypt.hashpw(p.getUpw(), BCrypt.gensalt());
        p.setUpw(hashedpw);

        return mapper.postUser(p);
    }

    // 1.성공 2.아이디x 3.비번x
    public ResultDto<Long>  signinUser (GetUserReq p) {
        UserEntity entity = mapper.getUser(p.getUid());
        if(entity == null) {
            return ResultDto.<Long>builder()
                .statusCode(HttpStatus.NOT_FOUND)
                .resultMsg("아이디를 확인해주세요.")
                .resultData(0L).build();
        }
        if (!BCrypt.checkpw(p.getUpw(), entity.getUpw())){

            return ResultDto.<Long>builder()
                    .statusCode(HttpStatus.NOT_FOUND)
                    .resultMsg("비밀번호를 확인해주세요.")
                    .resultData(0L).build();
        }
        //로그인 성공
        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("로그인 성공")
                .resultData(entity.getUserId()).build();
    }

    // 1.성공 2.아이디x 3.비번x
    public int patchPassword (PatchPassword p) {
        UserEntity entity = mapper.getUser(p.getUid());
        if (entity == null) {
            return 2;
        }
        if (!BCrypt.checkpw(p.getUpw(),entity.getUpw())){
            return 3;
        }
        String newPw = BCrypt.hashpw(p.getNewPw(), BCrypt.gensalt());
        p.setUserId(entity.getUserId());
        p.setNewPw(newPw);
        return mapper.patchPassword(p);

    }

}
