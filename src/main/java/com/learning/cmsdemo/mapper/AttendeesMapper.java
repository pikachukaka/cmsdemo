package com.learning.cmsdemo.mapper;

import com.learning.cmsdemo.model.Attendees;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AttendeesMapper {
    @Insert("insert into attendees(userID,conferenceID,conferenceName,name,id,workplace,telephone,time,house)" +
            "values(#{userID},#{conferenceID},#{conferenceName},#{name},#{id},#{workplace},#{telephone},#{time},#{house})")
    int insertAttendees(Attendees attendees);

    @Select("select conferenceID from attendees where userID=#{userID}")
    List<String> selectJoined(String userID);

    @Select("select * from attendees where conferenceID = #{conferenceID}")
    List<Attendees> selectAll(String conferenceID);

}
