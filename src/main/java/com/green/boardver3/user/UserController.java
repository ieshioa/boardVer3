package com.green.boardver3.user;

import com.green.boardver3.common.model.ResultDto;
import com.green.boardver3.user.model.GetUserReq;
import com.green.boardver3.user.model.PatchPassword;
import com.green.boardver3.user.model.PostUserReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {
    private final UserService service;

    @PostMapping("signup")
    public ResultDto<Integer> postUser(@RequestBody PostUserReq p) {
        int result = service.postUser(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("ID 등록 완료")
                .resultData(result)
                .build();
    }

    @PostMapping("signin")
    public ResultDto<Long> getUser(@RequestBody GetUserReq p) {
        return service.signinUser(p);
    }

    // 1.성공 2.아이디x 3.비번x
    @PatchMapping
    public ResultDto<Integer> patchPassword(@RequestBody PatchPassword p) {
        int result = service.patchPassword(p);
        String msg = switch (result) {
            case 1 -> "변경 성공";
            case 2 -> "아이디 없음";
            default -> "비밀번호 틀림";
        };

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(msg)
                .resultData(result)
                .build();
    }


}
