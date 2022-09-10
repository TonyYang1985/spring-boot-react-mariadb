package com.ibank.backend.vo.response;

import com.ibank.backend.vo.BaseResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteUserResponse extends BaseResponse{
    protected boolean result;
}
