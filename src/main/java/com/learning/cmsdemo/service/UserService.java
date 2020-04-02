package com.learning.cmsdemo.service;

import com.learning.cmsdemo.mapper.UserMapper;
import com.learning.cmsdemo.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {
    @Resource
    UserMapper userMapper;

    public boolean insertUser(String id,String password){
        User temp=userMapper.selectById(id);
        if(temp==null){
            User user = new User(id,password);
            userMapper.insertUser(user);
            return true;
        }
        return false;
    }

    public int login(String id,String password){
        User temp=userMapper.selectById(id);
        if(temp==null){
            return 0;
        }
        else if(temp.getPassword().equals(password)){
            return 1;
        }
        return -1;
    }

    public List<User> selectAll(){
        return userMapper.selectAll();
    }

    public void deleteUser(String id){
        userMapper.deleteUser(id);
    }

}
