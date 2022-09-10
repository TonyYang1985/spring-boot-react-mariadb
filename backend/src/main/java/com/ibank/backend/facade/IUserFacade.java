package com.ibank.backend.facade;

import com.ibank.backend.vo.request.CreateUserRequest;
import com.ibank.backend.vo.request.DeleteUserRequest;
import com.ibank.backend.vo.request.UpdateUserRequest;
import com.ibank.backend.vo.response.CreateUserResponse;
import com.ibank.backend.vo.response.DeleteUserResponse;
import com.ibank.backend.vo.response.ListUserResponse;
import com.ibank.backend.vo.response.UniqueUserResponse;
import com.ibank.backend.vo.response.UpdateUserResponse;


public interface IUserFacade {
    UniqueUserResponse findById(int id);
    ListUserResponse findByName(String email);
    ListUserResponse getAllUsers();
    CreateUserResponse createUser(CreateUserRequest request);
    UpdateUserResponse updateUser(UpdateUserRequest request);
    DeleteUserResponse deleteById(DeleteUserRequest request);
}
