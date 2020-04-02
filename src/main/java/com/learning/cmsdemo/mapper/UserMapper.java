package com.learning.cmsdemo.mapper;

import com.learning.cmsdemo.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface UserMapper {
    @Insert("insert into user(id,password)values(#{id},#{password})")
    int insertUser(User user);

    @Select("select * from user where id=#{id}")
    User selectById(String id);

    @Select("select * from user")
    List<User> selectAll();

    @Delete("delete from user where id=#{id}")
    int deleteUser(String id);


}
