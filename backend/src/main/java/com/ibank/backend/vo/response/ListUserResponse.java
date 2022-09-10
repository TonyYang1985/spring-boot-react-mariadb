package com.ibank.backend.vo.response;


import java.util.List;

import com.ibank.backend.vo.BaseResponse;
import com.ibank.backend.vo.UserVo;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ListUserResponse extends BaseResponse{
    protected List<UserVo> users;
}
