package com.byw.flashcard.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/2/10/010 23:30
 */
public interface UniteMapper {
    List<String> findPatByComposite(@Param("compositeId") String compositeId);
}
