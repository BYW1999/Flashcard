package com.byw.flashcard.mapper;

import com.byw.flashcard.pojo.Process;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/2/24/024 22:48
 */
public interface ProcessMapper {

    Process findRecordByUser(@Param("userId") Long userId, @Param("module") Long module);
    void insertRecordByNumStudy(@Param("userId") Long userId,@Param("numId") Long numId,@Param("type") Long type);
    void updateRecordByNumStudy(@Param("userId") Long userId,@Param("numId") Long numId,@Param("type") Long type);
    void insertRecordByPatStudy(@Param("userId") Long userId,@Param("patId") Long patId,@Param("type") Long type);
    void updateRecordByPatStudy(@Param("userId") Long userId,@Param("patId") Long patId,@Param("type") Long type);
    void insertRecordByColorStudy(@Param("userId") Long userId,@Param("colorId") Long colorId,@Param("type") Long type);
    void updateRecordByColorStudy(@Param("userId") Long userId,@Param("colorId") Long colorId,@Param("type") Long type);

    //void insertRecord(@Param("userId") Long userId,@Param("numId") Long numId,@Param("patId") Long patId,@Param("colorId") Long colorId,@Param("type") Long type);
    void insertRecord(@Param("userId") Long userId,@Param("type") String type,@Param("rate") Long rate,@Param("module") Long module);
    void updateRecord(@Param("userId") Long userId,@Param("type") String type,@Param("rate") Long rate,@Param("module") Long module);
    Process findRecord(@Param("userId") Long userId,@Param("module") Long module);
}
