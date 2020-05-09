package com.byw.flashcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.byw.flashcard.mapper.*;
import com.byw.flashcard.pojo.Pattern;
import com.byw.flashcard.pojo.Process;
import com.byw.flashcard.pojo.User;
import com.byw.flashcard.util.PhoneCode;
import com.byw.flashcard.util.SessionContext;
import com.fasterxml.jackson.databind.util.JSONPObject;
import jdk.nashorn.internal.ir.debug.PrintVisitor;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/1/1/001 19:32
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static String sessionId;
    @Resource
    private UserMapper userMapper;
    @Resource
    private ProcessMapper processMapper;

    @GetMapping
    public User findUser(@RequestParam(value = "id") Long id){
        return userMapper.findById(id);
    }

    /**
     * 发送短信验证码
     * @param
     * @return 返回发送结果向前台
     */
    @RequestMapping("/getTestCode")
    @ResponseBody
    public String sendTestCode(@RequestParam(value="phoneNumber",defaultValue="") String phoneNumber, HttpServletRequest request){

        JSONObject json = new JSONObject();
        HttpSession session = request.getSession();

        if(phoneNumber == null || phoneNumber.length()==0  ){
            return "手机号码为空";
        }
        String code = PhoneCode.getPhonemsg(phoneNumber);
        /*json.put("phoneNumber", phoneNumber);
        json.put("verifyCode", code);//验证码
        System.out.println(code);
        json.put("createTime", System.currentTimeMillis());//当前时间
        session.setAttribute("verifyCode", json);*/
        sessionId = session.getId();
        System.out.println("sessionId:"+sessionId);
        json.put("verifyCode", code);//验证码
        System.out.println(code);
        json.put("createTime", System.currentTimeMillis());//当前时间
        session.setAttribute(phoneNumber, json);
        return "success";
    }

//   多个用户
// @RequestMapping("/getTestCode")
//    @ResponseBody
//    public String sendTestCode(@RequestParam(value="phoneNumber",defaultValue="") String phoneNumber, HttpServletRequest request, HttpServletResponse response){
//
//        JSONObject json = new JSONObject();
//        HttpSession session = request.getSession();
//        String jsonList = request.getParameter("codeList");
//
//
//
//        if(phoneNumber == null || phoneNumber.length()==0  ){
//            return "手机号码为空";
//        }
//        String code = PhoneCode.getPhonemsg(phoneNumber);
//        json.put("phoneNumber", phoneNumber);
//        json.put("verifyCode", code);//验证码
//        System.out.println(code);
//        json.put("createTime", System.currentTimeMillis());//当前时间
//        session.setAttribute("verifyCode", json);
//        return "success";
//    }

    /**
     * 判断验证码是否正确
     * @param
     * @return 返回发送结果向前台
     */
    /*@RequestMapping("/judgeTestCode")
    @ResponseBody
    public Object judgeTestCode(HttpServletRequest request,String verifyCode) {
        JSONObject json = (JSONObject)request.getSession().getAttribute("verifyCode");
        System.out.println(json);
        if(!json.getString("verifyCode").equals(verifyCode)){
            return "验证码错误";
        }
        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
            return "验证码过期";
        }
        *//*if((System.currentTimeMillis() - json.getLong("createTime")) > 60){
            return "验证码过期";
        }*//*
        return "success";
    }*/
    @RequestMapping("/judgeTestCode")
    @ResponseBody
    public Object judgeTestCode(String phoneNumber,String verifyCode) {
        SessionContext sessionContext= SessionContext.getInstance();
        //从自定义session容器中拿到对应session
        HttpSession session = sessionContext.getSession(sessionId);
        JSONObject json = (JSONObject)session.getAttribute(phoneNumber);
        if(!json.getString("verifyCode").equals(verifyCode)){
            return "验证码错误";
        }
        if((System.currentTimeMillis() - json.getLong("createTime")) > 1000 * 60 * 5){
            return "验证码过期";
        }
        //String code= String.valueOf(session.getAttribute(phoneNumber));
        /*if(!code.equals(verifyCode)){
            return "验证码错误";
        }*/
        return "success";
    }

    /**
     *注册/添加用户
     * 入参：手机号、验证码
     */
    @RequestMapping("/register")
    @ResponseBody
    public String register(@RequestParam(value="name",defaultValue="") String name,@RequestParam(value="phone",defaultValue="") String phone){
        userMapper.insertUser(name, phone);
        return "success";
    }

    /*
    判断用户是否存在
     */
    @RequestMapping("/judgeUser")
    @ResponseBody
    public String judgeUser(@RequestParam(value="phone",defaultValue="") String phone){
        User user=userMapper.findUserByPhone(phone);
        if(user!=null){
            return "success";
        }else {
            return "fail";
        }
    }

    /**
     * 存学习进度
     * @param phone
     * @param rate
     * @param type number为数字，pattern为形状，color为颜色
     * @return
     */
    /*@RequestMapping("/saveStudyProgress")
    @ResponseBody
    public String saveStudyProgress(@RequestParam(value = "phone",defaultValue = "") String phone,
                                    @RequestParam(value = "rate",defaultValue = "") int rate,
                                    @RequestParam(value = "type",defaultValue = "") String type){
        Long userId = userMapper.findIdByPhone(phone);
        if(userId==null){
            return "无该用户";
        }
        Record record = recordMapper.findRecordByUserId(userId);
        if(type.equals("number")){   //存颜色进度
            if(record==null){   //无该用户记录，插入记录
                Study study = new Study();
                study.setNumId(Long.valueOf(rate));
                Long id = studyMapper.insertStudyByNumId(study);
                System.out.println("id======"+study.getId());
                recordMapper.insertRecordByStudy(userId, study.getId());
            }else{  //修改记录
                studyMapper.updateStudyByNumId(Long.valueOf(rate), record.getStudyId());
            }
        }else if (type.equals("pattern")){    //存形状进度
            if(record==null){
                Study study = new Study();
                study.setPatId(Long.valueOf(rate));
                Long id = studyMapper.insertStudyByPatId(study);
                recordMapper.insertRecordByStudy(userId, study.getId());
            }else{
                studyMapper.updateStudyByPatId(Long.valueOf(rate), record.getStudyId());
            }
        }else if (type.equals("color")){    //存颜色进度
            if(record==null){
                Study study = new Study();
                study.setColorId(Long.valueOf(rate));
                Long id = studyMapper.insertStudyByColorId(study);
                recordMapper.insertRecordByStudy(userId, study.getId());
            }else{
                studyMapper.updateStudyByColorId(Long.valueOf(rate), record.getStudyId());
            }
        }else {
            return "failed";
        }
        return "success";
    }*/


    /*@RequestMapping("/getStudyProgress")
    @ResponseBody
    public int getStudyProgress(@RequestParam(value = "phone",defaultValue = "") String phone,
                                    @RequestParam(value = "type",defaultValue = "") String type){
        Long userId = userMapper.findIdByPhone(phone);
        Long rate;
        if(userId==null){
            return -1;
        }
        Record record = recordMapper.findRecordByUserId(userId);
        if(type.equals("number")){   //取颜色进度
            rate = studyMapper.findNumber(record.getStudyId());
        }else if (type.equals("pattern")){    //取存形状进度
            rate = studyMapper.findPattern(record.getStudyId());
        }else if (type.equals("color")){    //取颜色进度
            rate = studyMapper.findColor(record.getStudyId());
        }else {
            return -2;
        }
        if(rate==null){
            return -3;
        }
        return Integer.parseInt(String.valueOf(rate));

        HmmJ6CVcnfmOfZC9Lz6JbzQn8rKK5M
    }*/


    /**
     * 存学习进度
     * @param phone 手机号
     * @param rate 学习进度
     * @param type number：数字、pattern：形状、color：颜色
     * @param module 1：学习模块、2：临摹模块、3：练习模块
     * @return
     */
    @RequestMapping("/saveProgress")
    @ResponseBody
    public String saveProgress(@RequestParam(value = "phone",defaultValue = "") String phone,
                                    @RequestParam(value = "rate",defaultValue = "") int rate,
                                    @RequestParam(value = "type",defaultValue = "") String type,
                                    @RequestParam(value = "module",defaultValue = "") int module){
        Long userId = userMapper.findIdByPhone(phone);
        if(userId!=null && rate>0
                && (type.equals("number") || type.equals("pattern") || type.equals("color"))
                && (module==1 || module==2 || module==3)){
            Process process = processMapper.findRecordByUser(userId,Long.valueOf(module));
            if(process==null){
                processMapper.insertRecord(userId, type, Long.valueOf(rate),Long.valueOf(module));
            }else {
                processMapper.updateRecord(userId, type, Long.valueOf(rate),Long.valueOf(module));
            }
        }else {
            return "failed";
        }
        return "success";
    }

    /**
     *  取进度
     * @param phone 手机号
     * @param type 类型（number、pattern、color）
     * @param module 模块（1：学习、2：临摹、3：练习）
     * @return
     */
    @RequestMapping("/getProgress")
    @ResponseBody
    public int getProgress(@RequestParam(value = "phone",defaultValue = "") String phone,
                                    @RequestParam(value = "type",defaultValue = "") String type,
                                    @RequestParam(value = "module",defaultValue = "") int module){
        Long userId = userMapper.findIdByPhone(phone);
        Long rate;
        if(userId==null){
            return -1;
        }
        if(userId!=null && (type.equals("number") || type.equals("pattern") || type.equals("color"))
                && (module==1 || module==2 || module==3)){
            Process process = processMapper.findRecord(userId, Long.valueOf(module));
            if(process==null){
                return -5;
            }

            if(type.equals("number")){   //取颜色进度
                rate = process.getNumId();
            }else if (type.equals("pattern")){    //取存形状进度
                rate = process.getPatId();
            }else if (type.equals("color")){    //取颜色进度
                rate = process.getColorId();
            }else {
                return -2;
            }
            if(rate==null){
                return -3;
            }
        }else{
            return -4;
        }

        return Integer.parseInt(String.valueOf(rate));
    }
}
