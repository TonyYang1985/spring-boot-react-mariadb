package com.ibank.backend.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.ibank.backend.entity.User;
import com.ibank.backend.facade.IUserFacade;
import com.ibank.backend.repository.UserRepository;
import com.ibank.backend.utils.BeanMapper;
import com.ibank.backend.vo.UserVo;
import com.ibank.backend.vo.request.CreateUserRequest;
import com.ibank.backend.vo.request.DeleteUserRequest;
import com.ibank.backend.vo.request.UpdateUserRequest;
import com.ibank.backend.vo.response.CreateUserResponse;
import com.ibank.backend.vo.response.DeleteUserResponse;
import com.ibank.backend.vo.response.ListUserResponse;
import com.ibank.backend.vo.response.UniqueUserResponse;
import com.ibank.backend.vo.response.UpdateUserResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService  implements IUserFacade {
    
   @Autowired  
   private  UserRepository userRepo;

    /**
     * find all User .
     * @return  A list of User  .
     */
    @Override
    public ListUserResponse getAllUsers() {
       ListUserResponse resp = new ListUserResponse();
       List<User> result = this.userRepo.findAll();
       List<UserVo> userVo = BeanMapper.mapList( result, UserVo.class);
       resp.setUsers(userVo);
       resp.setRetCode(HttpStatus.OK.value());
       resp.setRetMsg(" Fetch All User Success");
       return resp;
    }
    /**
     * find User  By Id.
     * @param id
     * @return  UniqueUserResponse .
     */
    @Override
    public UniqueUserResponse findById(int id) {
       UniqueUserResponse resp = new UniqueUserResponse();
       User u = this.userRepo.findByUserId(id);
       if(u !=null ){
         UserVo userVo = BeanMapper.map( u, UserVo.class);
         resp.setUser(userVo);
         resp.setRetCode(HttpStatus.OK.value());
         resp.setRetMsg(" Fetch User By Id Success");
       }else{
         resp.setRetCode(HttpStatus.BAD_REQUEST.value());
         resp.setRetMsg(" Fetch User By Id Failed");
       }
       return resp;
    }
    /**
     * find User list  By firstname.
     * @param name
     * @return  A list of User  .
     */
    @Override
    public ListUserResponse findByName(String name) {
       ListUserResponse resp = new ListUserResponse();
       List<User> result = this.userRepo.findByName(name);
       List<UserVo> userVo = BeanMapper.mapList( result, UserVo.class);
       resp.setUsers(userVo);
       resp.setRetCode(HttpStatus.OK.value());
       resp.setRetMsg(" Fetch Users By Name Success");
       return resp;
    }
    /**
     * create new User
     * @param id
     * @return boolean .
     */
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
      CreateUserResponse resp = new CreateUserResponse();
      try {
         List<User> entitys = userRepo.findByEmail(request.getEmail());
         if(CollectionUtils.isEmpty(entitys)){
            User u = new User();
            u .setFirstname(request.getFirstname());
            u .setLastname(request.getLastname());
            u .setEmail(request.getEmail());

            User result = userRepo.save(u);
            resp.setId(result.getId());
            resp.setRetCode(HttpStatus.OK.value());
            resp.setRetMsg(" Create  User  Success");
         }else{
           log.warn("Users email duplicated {}", request.getEmail());
           resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
           resp.setRetMsg(" User  email Duplicated ");
         }
        } catch (Exception e) {
          log.error("UserService save request {} ,error {}",request, e);
          resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
          resp.setRetMsg(" Create  User  Failed");
        }
       return resp;
    }
    
    /**
     * delete User By userId.
     * @param id
     * @return boolean .
     */
    @Override
    public DeleteUserResponse deleteById(DeleteUserRequest  request) {
        DeleteUserResponse resp = new DeleteUserResponse();
        try {
          userRepo.deleteById(request.getId());
         resp.setResult(Boolean.TRUE);
         resp.setRetCode(HttpStatus.OK.value());
         resp.setRetMsg(" Delete User Success");
         return resp;
        } catch (Exception e) {
          log.error("Delete User Failed userId {} ,error {}",request, e);
          resp.setResult(Boolean.FALSE);
          resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
          resp.setRetMsg(" Delete User Failed");
        }
        return resp;
    }

    /**
     * update  User
     * @param id
     * @param user User
     * @return User .
     */

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest request) {
      UpdateUserResponse resp = new UpdateUserResponse();
        try {
            User entity = userRepo.findByUserId(request.getId());
            if(entity != null){
               User u = new User();
               u.setId(request.getId());
               u .setFirstname(request.getFirstname());
               u .setLastname(request.getLastname());
               u .setEmail(request.getEmail());
               userRepo.save(u);
               UserVo userVo = BeanMapper.map( u, UserVo.class);
               resp.setUser(userVo);
               resp.setRetCode(HttpStatus.OK.value());
               resp.setRetMsg(" Update User Success");
            }
           } catch (Exception e) {
             log.error("UserService update user {} ,error {}",request, e);
             resp.setRetCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
             resp.setRetMsg(" Update User Failed");
           }
          return resp;
    }
}
