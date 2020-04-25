package com.byw.flashcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.byw.flashcard.mapper.ColorMapper;
import com.byw.flashcard.util.Option;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 王碧云
 * @description: 颜色控制器
 * @date 2020/3/25/025 20:39
 */
@RestController
@RequestMapping("/color")
public class ColorController {
    @Resource
    private ColorMapper colorMapper;

    @RequestMapping("/getColorQuestion")
    public JSONObject getColorQuestion(@RequestParam(value="grade",defaultValue="") String grade){
        List<String> colorList = colorMapper.findAllColor();
        Map<String,Object> allMap = new HashMap<>();
        Random random = new Random();
        Option option = new Option();

        if(grade.equals("1")){  //简单
            for(int i=0;i<5;i++){
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(colorList.size());
                String question = colorList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(colorList, question);
                map.put("errorList", errorList);
                allMap.put(String.valueOf(i+1), map);
            }
        }else if (grade.equals("2")){   //中等
            for(int i=0;i<10;i++){
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(colorList.size());
                String question = colorList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(colorList, question);
                map.put("errorList", errorList);
                allMap.put(String.valueOf(i+1), map);
            }
        }else if (grade.equals("3")){   //困难
            for(int i=0;i<15;i++){
                Map<String,Object> map = new HashMap<>();
                int n = random.nextInt(colorList.size());
                String question = colorList.get(n);
                map.put("question", question);
                map.put("answer", question);
                List<String> errorList = option.getErrorList(colorList, question);
                map.put("errorList", errorList);
                allMap.put(String.valueOf(i+1), map);
            }
        }
        JSONObject jsonObject = new JSONObject(allMap);
        return jsonObject;
    }
}
