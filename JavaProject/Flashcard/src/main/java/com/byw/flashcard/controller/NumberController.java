package com.byw.flashcard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.byw.flashcard.mapper.NumberMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/1/30/030 22:44
 */
@RestController
@RequestMapping("/number")
public class NumberController {
    @Resource
    private NumberMapper numberMapper;

    @RequestMapping("/insertNumber")
    public void insertNumber(){
        for(int i=0;i<=100;i++){
            String num = String.valueOf(i);
            numberMapper.insertNum(num);
        }
    }

    /**
     * 获取练习题
     * @param grade（1：简单、2：中等、3：困难）
     * @return 题目、正确答案、选项（每次获取一道题）
     */
    /*@RequestMapping("/getNumQuestion")
    public JSONObject getNumQuestion(@RequestParam(value="grade",defaultValue="") String grade){
        List<String> numList = numberMapper.findAllNum();
        ArrayList<String> optionList = new ArrayList<>();
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        String[] lastOrNext = {"0","1"};    //"0":上一位 "1"下一位
        Random random = new Random();
        int n = random.nextInt(numList.size());

        if (grade.equals("1")){    //5个题目，每道题目4个选项，其中包含一个正确选项和一个错误选项
            String question = numList.get(n);
            map.put("question", question);
            map.put("answer", question);
            System.out.println("question========="+question);
            for(int i=0;i<3;i++){
                int m = random.nextInt(numList.size());
                String error = numList.get(m);
                if (!error.equals(question)){
                    optionList.add(error);
                }else {
                    int error1 = Integer.valueOf(error)+1;
                    optionList.add(String.valueOf(error1));
                }
            }
            map.put("errorList", optionList);
            jsonObject = new JSONObject(map);
            return jsonObject;

        }else if(grade.equals("2")){    //10个数字，选出上一位、下一位
            int j = random.nextInt(lastOrNext.length);
            String randLN = lastOrNext[j];
            String question = numList.get(n);
            String answer = "";
            if(randLN.equals("0")){    //上一位
                String questionDes = question + "的上一个数字是什么？";
                map.put("question", questionDes);
                answer = String.valueOf(Integer.valueOf(question)-1);
                map.put("answer", answer);
            }else if (randLN.equals("1")){  //下一位
                String questionDes = question + "的下一个数字是什么？";
                map.put("question", questionDes);
                answer = String.valueOf(Integer.valueOf(question)+1);
                map.put("answer", answer);
            }
            for(int i=0;i<3;i++){
                int m = random.nextInt(numList.size());
                String error = numList.get(m);
                if (!error.equals(answer)){
                    optionList.add(error);
                }else {
                    int error1 = Integer.valueOf(error)+2;
                    optionList.add(String.valueOf(error1));
                }
            }
            map.put("errorList", optionList);
            jsonObject = new JSONObject(map);
            return jsonObject;
        }else if(grade.equals("3")){    //10个，第二个或者第三个
            int k = random.nextInt(lastOrNext.length);
            String rand = lastOrNext[k];
            if(rand.equals("0")){
                jsonObject = getNumQuestion("1");
            }else if (rand.equals("1")){
                jsonObject = getNumQuestion("2");
            }
        }
        return jsonObject;
    }*/

    /**
     * 获取练习题
     * @param grade（1：简单、2：中等、3：困难）
     * @return 题目、正确答案、选项(一次全部获取)
     */
    @RequestMapping("/getNumQuestion")
    public JSONObject getNumQuestion(@RequestParam(value="grade",defaultValue="") String grade){
        List<String> numList = numberMapper.findAllNum();
        Map<String,Object> allMap = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        Random random = new Random();

        if (grade.equals("1")){    //5个题目，每道题目4个选项，其中包含一个正确选项和一个错误选项
            for(int j=0;j<5;j++){
                ArrayList<String> optionList = new ArrayList<>();   //错误选项列表
                int n = random.nextInt(numList.size());
                Map<String,Object> map = new HashMap<>();
                String question = numList.get(n);
                map.put("question", question);
                map.put("answer", question);
                System.out.println("question========="+question);
                for(int i=0;i<3;i++){
                    int m = random.nextInt(numList.size());
                    String error = numList.get(m);
                    if (!error.equals(question)){
                        optionList.add(error);
                    }else {
                        int error1 = Integer.valueOf(error)+1;
                        optionList.add(String.valueOf(error1));
                    }
                }
                map.put("errorList", optionList);
                allMap.put(String.valueOf(j+1), map);
            }

        }else if(grade.equals("2")){    //10个数字，选出上一位、下一位
            String[] lastOrNext = {"0","1"};    //"0":上一位 "1"下一位
            for(int o=0;o<10;o++){
                ArrayList<String> optionList = new ArrayList<>();
                Map<String,Object> map = new HashMap<>();
                int j = random.nextInt(lastOrNext.length);
                String randLN = lastOrNext[j];
                int n = random.nextInt(numList.size());
                String question = numList.get(n);
                String answer = "";
                if(randLN.equals("0")){    //上一位
                    String questionDes = question + "的上一个数字是什么？";
                    map.put("question", questionDes);
                    answer = String.valueOf(Integer.valueOf(question)-1);
                    map.put("answer", answer);
                }else if (randLN.equals("1")){  //下一位
                    String questionDes = question + "的下一个数字是什么？";
                    map.put("question", questionDes);
                    answer = String.valueOf(Integer.valueOf(question)+1);
                    map.put("answer", answer);
                }
                for(int i=0;i<3;i++){
                    int m = random.nextInt(numList.size());
                    String error = numList.get(m);
                    if (!error.equals(answer)){
                        optionList.add(error);
                    }else {
                        int error1 = Integer.valueOf(error)+2;
                        optionList.add(String.valueOf(error1));
                    }
                }
                map.put("errorList", optionList);
                allMap.put(String.valueOf(o+1), map);
            }

        }else if(grade.equals("3")){    //10个，第二个或者第三个
            String[] lastOrNext = {"0","1","2"};    //"0":上一位 "1"下一位，“2”没有
            for(int o=0;o<10;o++){
                ArrayList<String> optionList = new ArrayList<>();
                Map<String,Object> map = new HashMap<>();
                int j = random.nextInt(lastOrNext.length);
                String randLN = lastOrNext[j];
                int n = random.nextInt(numList.size());
                String question = numList.get(n);
                String answer = "";
                if(randLN.equals("0")){    //上一位
                    String questionDes = question + "的上一个数字是什么？";
                    map.put("question", questionDes);
                    answer = String.valueOf(Integer.valueOf(question)-1);
                    map.put("answer", answer);
                }else if (randLN.equals("1")){  //下一位
                    String questionDes = question + "的下一个数字是什么？";
                    map.put("question", questionDes);
                    answer = String.valueOf(Integer.valueOf(question)+1);
                    map.put("answer", answer);
                }else if(randLN.equals("2")){   //不设置上一位或者下一位
                    map.put("question", question);
                    map.put("answer", question);
                }
                for(int i=0;i<3;i++){
                    int m = random.nextInt(numList.size());
                    String error = numList.get(m);
                    if (!error.equals(answer)){
                        optionList.add(error);
                    }else {
                        int error1 = Integer.valueOf(error)-2;
                        optionList.add(String.valueOf(error1));
                    }
                }
                map.put("errorList", optionList);
                allMap.put(String.valueOf(o+1), map);
            }
        }else {
            allMap.put("error", "参数异常！");
        }
        jsonObject = new JSONObject(allMap);
        return jsonObject;
    }

    /**
     *
     * @param answerList 正确答案
     * @param pickList  用户选择的答案
     * @return  正确的题数以及错误的题数
     */
    @RequestMapping("/judgeNumQuestion")
    public JSONObject judgeNumQuestion(@RequestParam(value="answerList",defaultValue="") String[] answerList,
                                       @RequestParam(value="pickList",defaultValue="") String[] pickList){
        int length = answerList.length;
        List<String> trueList = new ArrayList<>();
        List<String> falseList = new ArrayList<>();
        Map<String,Object> resultMap = new HashMap<>();

        for(int i=0;i<length;i++){
            String answer = answerList[i];
            String pick = pickList[i];
            if(pick == null || pick == ""){
                falseList.add(String.valueOf(i+1));
            }else {
                if(answer.equals(pick)){
                    trueList.add(String.valueOf(i+1));
                }else {
                    falseList.add(String.valueOf(i+1));
                }
            }
        }
        resultMap.put("trueList", trueList);
        resultMap.put("falseList", falseList);
        JSONObject jsonObject = new JSONObject(resultMap);
        return jsonObject;
    }

}
