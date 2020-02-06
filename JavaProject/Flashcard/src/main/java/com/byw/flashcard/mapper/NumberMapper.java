package com.byw.flashcard.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/1/30/030 20:42
 */
public interface NumberMapper {
    void insertNum(@Param("num") String num);
    List<String> findAllNum();

}
