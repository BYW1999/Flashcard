package com.byw.flashcard.mapper;

import java.util.List;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/2/11/011 20:55
 */
public interface CompositeMapper {
    List<String> findAllId();
    String findPathById(String id);
}
