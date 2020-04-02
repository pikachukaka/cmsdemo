package com.learning.cmsdemo.service;

import com.learning.cmsdemo.mapper.AttendeesMapper;
import com.learning.cmsdemo.mapper.RecordMapper;
import com.learning.cmsdemo.model.Attendees;
import com.learning.cmsdemo.model.ConferenceRecord;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service
public class ConferenceService {
    @Resource
    RecordMapper recordMapper;
    @Resource
    AttendeesMapper attendeesMapper;

    public void creatConference(String conferenceID,String name,String beginTime,String endTime ,String place,
                                String participants,String hotel,String items,String founder){
        ConferenceRecord record=new ConferenceRecord(conferenceID,name,beginTime,endTime,place,participants,hotel,items,founder);
        recordMapper.insertRecord(record);
    }

    public List<ConferenceRecord> selectCreate(String founder){
        return recordMapper.selectByFounder(founder);

    }

    public List<ConferenceRecord> selectByConferenceID(String conferenceID){
        return recordMapper.selectByConferenceID(conferenceID);
    }

    public List<ConferenceRecord> selectByName(String name){
        return recordMapper.selectByName(name);
    }

    public List<ConferenceRecord> selectItemsByID(String conferenceID){

        return recordMapper.selectItemsByID(conferenceID);
    }

    public void submitApply(String userID,String conferenceID,String conferenceName,String name,String id,String workplace,
                            String telephone,String time,String house){
        Attendees attendees=new Attendees(userID,conferenceID,conferenceName,name,id,workplace,telephone,time,house);
        attendeesMapper.insertAttendees(attendees);

    }

    public List<String> selectJoined(String userID){
        return attendeesMapper.selectJoined(userID);
    }

    public List<Attendees> selectAll(String conferenceID){return attendeesMapper.selectAll(conferenceID);}

    public List<ConferenceRecord> showAll(){return recordMapper.selectAll();}

    public void deleteConferenceID(String conferenceID){recordMapper.deleteConference(conferenceID);}
}
