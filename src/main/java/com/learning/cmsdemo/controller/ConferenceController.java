package com.learning.cmsdemo.controller;

import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.learning.cmsdemo.model.Attendees;
import com.learning.cmsdemo.model.ConferenceRecord;
import com.learning.cmsdemo.service.ConferenceService;
import com.learning.cmsdemo.util.HandleFile;
import com.learning.cmsdemo.util.ZxingUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ConferenceController {
    @Autowired
    ConferenceService conferenceService;

    @PostMapping("/user/operate1")
    public String creatOne(@RequestParam("name") String name, @RequestParam("beginTime") String beginTime,@RequestParam("endTime") String endTime, @RequestParam("place") String place,
                           @RequestParam("participants") String participants, @RequestParam("hotel") String hotel, @RequestParam("items") String items,
                           Map<String, Object> map, HttpServletRequest request) throws IOException {
        String founder= request.getSession().getAttribute("loginUser").toString();
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
        String conferenceID = 1 + String.format("%015d", hashCodeV);
        conferenceService.creatConference(conferenceID, name, beginTime,endTime, place, participants, hotel, items,founder);
        map.put("newID", conferenceID);
        return "showID";

    }

    @RequestMapping("/generate/{conferenceID}")
    public void qrCode(@PathVariable String conferenceID,HttpServletResponse response) throws IOException {
        response.setContentType("image/jpg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        OutputStream stream = response.getOutputStream();
        String content="会议号:"+conferenceID;
        //获取一个二维码图片
        BitMatrix bitMatrix = ZxingUtils.createCode(content);
        //以流的形式输出到前端
        MatrixToImageWriter.writeToStream(bitMatrix , "jpg" , stream);

    }

    @GetMapping("/showRecord")
    public  String getCreated(Model model,HttpServletRequest request,Map<String,Object>map) {
        List<ConferenceRecord> list=conferenceService.selectCreate(request.getSession().getAttribute("loginUser").toString());
        model.addAttribute("records",list);
        if(list.isEmpty()) map.put("recordMSG","您尚未创建任何会议！");
        return "operate::recordDiv";
    }



   @GetMapping("/attendeeSelect/{conferenceName}")
    public String search(@PathVariable("conferenceName") String name, Model model,Map<String,Object>map){
       List<ConferenceRecord> list=conferenceService.selectByName(name);
       model.addAttribute("search",list);
       if(list.isEmpty()) map.put("selectMSG","该会议不存在！");
        return "operate::forAttendee";
    }

    @GetMapping("/join/{conferenceID}")
    public  String join(@PathVariable("conferenceID") String conferenceID, Model model){
       List<ConferenceRecord> record=conferenceService.selectItemsByID(conferenceID);
       model.addAttribute("joinTable",record);
       return  "operate::myModal";
    }

    @GetMapping("/joinSubmit/{conferenceID}/{conferenceName}")
    public String submitApply(@PathVariable ("conferenceID") String conferenceID, @PathVariable("conferenceName") String conferenceName,
                              @RequestParam(value = "attendeeName",required = false) String name, @RequestParam(value = "attendeeID",required = false) String id,
                              @RequestParam(value = "attendeeWorkplace",required = false) String workplace, @RequestParam(value = "attendeeTel",required = false) String telephone,
                              @RequestParam(value = "attendanceTime",required = false)String time,@RequestParam(value = "isHouse",required = false)String house,
                              HttpServletRequest request,Map<String,Object>map){
       String userID=request.getSession().getAttribute("loginUser").toString();
       if(StringUtils.isEmpty(name)) name="/";
       if(StringUtils.isEmpty(id)) id="/";
       if(StringUtils.isEmpty(workplace)) workplace="/";
       if(StringUtils.isEmpty(telephone)) telephone="/";
       if(StringUtils.isEmpty(time)) time="/";
        if(StringUtils.isEmpty(house)) house="/";
       conferenceService.submitApply(userID,conferenceID,conferenceName,name,id,workplace,telephone,time,house);
        return "redirect:/main.html";
   }

    @GetMapping("/showJoined")
    public  String getJoined(Model model,HttpServletRequest request,Map<String,Object>map) {
        List<String> list=conferenceService.selectJoined(request.getSession().getAttribute("loginUser").toString());
        List<List<ConferenceRecord>>joined=new ArrayList<>();
        for(String i:list){
            joined.add(conferenceService.selectByConferenceID(i));
        }

        model.addAttribute("joined",joined);
        if(joined.isEmpty()) map.put("joinedMSG","您尚未参加任何会议！");
        return "operate::joinedDiv";

    }

    @GetMapping("/lookFor/{conferenceID}")
    public String lookFor(@PathVariable String conferenceID,Model model,Map<String,Object>map){
       List<Attendees> attendees=conferenceService.selectAll(conferenceID);
       model.addAttribute("attendees",attendees);
       if(attendees.isEmpty()) {
           map.put("detailMSG","尚未有人报名!");}
       else map.put("id",attendees.get(0).getConferenceID());
       return "detailsTable";
    }

    @GetMapping("/download/{conferenceID}")
    public void download(HttpServletResponse response, @PathVariable String conferenceID) throws Exception {
        Map<String,Object> excelMap = new HashMap<>();
        //1.设置Excel表头
        List<String> headerList = new ArrayList<>();
        headerList.add("姓名");
        headerList.add("身份证号");
        headerList.add("工作单位");
        headerList.add("电话号码");
        headerList.add("参会时间");
        headerList.add("是否需要安排房间");
        excelMap.put("header",headerList);

        //2.是否需要生成序号，序号从1开始(true-生成序号 false-不生成序)
        boolean isSerial = false;
        excelMap.put("isSerial",isSerial);

        //3.sheet名
        String sheetName = "会议表";
        excelMap.put("sheetName",sheetName);


        //4.需要放入Excel中的数据
        Map<String,Object> map = new HashMap<>();
        List<Attendees> attendees=conferenceService.selectAll(conferenceID);
        List<List<String>> data= new ArrayList<>();
        for (Attendees attendees1:attendees){
            //所有的数据顺序必须和表头一一对应
            //list存放每一行的数据（让所有的数据类型都转换成String，这样就无需担心Excel表格中数据不对）
            List<String> list = new ArrayList<>();
            list.add(String.valueOf(attendees1.getName()));
            list.add(String.valueOf(attendees1.getId()));
            list.add(String.valueOf(attendees1.getWorkplace()));
            list.add(String.valueOf(attendees1.getTelephone()));
            list.add(attendees1.getTime());
            list.add(attendees1.getHouse());
            //data存放全部的数据
            data.add(list);
        }
        excelMap.put("data",data);

        //Excel文件内容设置
        HSSFWorkbook workbook = HandleFile.createExcel(excelMap);

        String fileName = "会议表.xls";

        //生成excel文件
        HandleFile.buildExcelFile(fileName, workbook);

        //浏览器下载excel
        HandleFile.buildExcelDocument(fileName,workbook,response);


    }

    }








