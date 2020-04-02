package com.learning.cmsdemo.mapper;

import com.learning.cmsdemo.model.ConferenceRecord;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RecordMapper {
    @Insert("insert into record(conferenceID,name,beginTime,endTime,place,participants,hotel,items,founder)values(#{conferenceID}," +
            "#{name},#{beginTime},#{endTime},#{place},#{participants},#{hotel},#{items},#{founder})")
    int insertRecord(ConferenceRecord record);

    @Select("select * from record where founder=#{founder}")
    List<ConferenceRecord> selectByFounder(String founder);

    @Select("select * from record where conferenceID=#{conferenceID}")
    List<ConferenceRecord> selectByConferenceID(String conferenceID);

    @Select("select * from record where name=#{name}")
    List<ConferenceRecord> selectByName(String name);


    @Select("select * from record where conferenceID=#{conferenceID}")
    List<ConferenceRecord> selectItemsByID(String conferenceID);


   @Select("select * from record")
   List<ConferenceRecord> selectAll();

   @Delete("delete from record where conferenceID =#{conferenceID}")
    int deleteConference(String conferenceID);




}
