package com.byw.flashcard.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 王碧云
 * @description: 错误选项
 * @date 2020/2/10/010 21:55
 */
public class Option {

    /*
    获取错误选项
     */
    public List<String> getErrorList(List<String> list,String question){
        ArrayList<String> errorList = new ArrayList<>();
        Random random = new Random();
        do {
            int m = random.nextInt(list.size());
            String error = list.get(m);
            if(!error.equals(question)){
                if(!errorList.contains(error)){
                    errorList.add(error);
                }else {
                    continue;
                }
            }else {
                continue;
            }
        }while (errorList.size()<3);
        return errorList;
    }

}
