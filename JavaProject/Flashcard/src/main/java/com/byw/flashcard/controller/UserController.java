package com.byw.flashcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.byw.flashcard.mapper.NumberMapper;
import com.byw.flashcard.mapper.UserMapper;
import com.byw.flashcard.pojo.User;
import com.byw.flashcard.util.PhoneCode;
import com.fasterxml.jackson.databind.util.JSONPObject;
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
    @Resource
    private UserMapper userMapper;

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
        json.put("phoneNumber", phoneNumber);
        json.put("verifyCode", code);//验证码
        System.out.println(code);
        json.put("createTime", System.currentTimeMillis());//当前时间
        session.setAttribute("verifyCode", json);
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
    @RequestMapping("/judgeTestCode")
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
        /*if((System.currentTimeMillis() - json.getLong("createTime")) > 60){
            return "验证码过期";
        }*/
        //将用户信息存入数据库

        //这里省略
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
}
