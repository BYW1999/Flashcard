package com.byw.flashcard.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.byw.flashcard.mapper.CompositeMapper;
import com.byw.flashcard.mapper.PatternMapper;
import com.byw.flashcard.mapper.UniteMapper;
import com.byw.flashcard.util.Option;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 王碧云
 * @description: 图片控制器
 * @date 2020/2/10/010 17:36
 */
@RestController
@RequestMapping("/pattern")
public class PatternController {

    @Resource
    private PatternMapper patternMapper;
    @Resource
    private UniteMapper uniteMapper;
    @Resource
    private CompositeMapper compositeMapper;

    @RequestMapping("/getPatQuestion")
    /*public JSONObject getPatQuestion(@RequestParam(value="grade",defaultValue="") String grade){
        List<String> patList = patternMapper.findAllPat();
        //List<Map<String,Object>> allList = new ArrayList<>();
        Map<String,Object> allMap = new HashMap<>();
        Random random = new Random();
        Option option = new Option();

        if(grade.equals("1")){  //简单
            for(int i=0;i<5;i++){
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(patList.size());
                String question = patList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(patList, question);
                map.put("errorList", errorList);
                allMap.put(String.valueOf(i+1), map);
            }
        }else if (grade.equals("2")){   //中等
            for(int i=0;i<10;i++){
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(patList.size());
                String question = patList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(patList, question);
                map.put("errorList", errorList);
                allMap.put(String.valueOf(i+1), map);
            }
        }else if (grade.equals("3")){   //困难
            List<String> compositeIdList = compositeMapper.findAllId();
            System.out.println(compositeIdList);
            for(int i=0;i<5;i++){
                Map<String,Object> map = new HashMap<>();
                List<String> errorList = new ArrayList<>();
                int n = random.nextInt(compositeIdList.size());
                String compositeId = compositeIdList.get(n);
                List<String> pats = uniteMapper.findPatByComposite(compositeId);
                String path = compositeMapper.findPathById(compositeId);
                System.out.println(compositeId+"--------"+path);
                map.put("question", path);
                map.put("answer", pats);
                do{
                    int m = random.nextInt(patList.size());
                    String error = patList.get(m);
                    if(pats.contains(error) || errorList.contains(error)){
                        continue;
                    }else {
                        errorList.add(error);
                    }
                }while ((errorList.size()+pats.size())<5);
                map.put("errorList", errorList);
                allMap.put(String.valueOf(i+1), map);
            }
        }
        JSONObject jsonObject = new JSONObject(allMap);
        return jsonObject;
    }*/
    public JSONArray getPatQuestion(@RequestParam(value="grade",defaultValue="") String grade){
        List<String> patList = patternMapper.findAllPat();
        List<Map<String,Object>> allList = new ArrayList<>();
        //Map<String,Object> allMap = new HashMap<>();
        Random random = new Random();
        Option option = new Option();

        if(grade.equals("1")){  //简单
            for(int i=0;i<5;i++){
                Map<String,Object> allMap = new HashMap<>();
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(patList.size());
                String question = patList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(patList, question);
                map.put("errorList", errorList);
                //allMap.put(String.valueOf(i+1), map);
                allMap.put("id", i+1);
                allMap.put("value", map);
                allList.add(allMap);
            }
        }else if (grade.equals("2")){   //中等
            for(int i=0;i<10;i++){
                Map<String,Object> allMap = new HashMap<>();
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(patList.size());
                String question = patList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(patList, question);
                map.put("errorList", errorList);
                //allMap.put(String.valueOf(i+1), map);
                allMap.put("id", i+1);
                allMap.put("value", map);
                allList.add(allMap);
            }
        }else if (grade.equals("3")){   //困难
            List<String> compositeIdList = compositeMapper.findAllId();
            System.out.println(compositeIdList);
            for(int i=0;i<5;i++){
                Map<String,Object> allMap = new HashMap<>();
                Map<String,Object> map = new HashMap<>();
                List<String> errorList = new ArrayList<>();
                int n = random.nextInt(compositeIdList.size());
                String compositeId = compositeIdList.get(n);
                List<String> pats = uniteMapper.findPatByComposite(compositeId);
                String path = compositeMapper.findPathById(compositeId);
                System.out.println(compositeId+"--------"+path);
                map.put("question", path);
                map.put("answer", pats);
                do{
                    int m = random.nextInt(patList.size());
                    String error = patList.get(m);
                    if(pats.contains(error) || errorList.contains(error)){
                        continue;
                    }else {
                        errorList.add(error);
                    }
                }while ((errorList.size()+pats.size())<5);
                map.put("errorList", errorList);
                //allMap.put(String.valueOf(i+1), map);
                allMap.put("id", i+1);
                allMap.put("value", map);
                allList.add(allMap);
            }
        }
        JSONArray array= JSONArray.parseArray(JSON.toJSONString(allList));
        return array;
    }

}
