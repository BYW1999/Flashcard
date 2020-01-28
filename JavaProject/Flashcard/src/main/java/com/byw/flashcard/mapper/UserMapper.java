package com.byw.flashcard.mapper;

import com.byw.flashcard.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/1/1/001 19:31
 */
public interface UserMapper {
    User findById(Long id);
    void insertUser(@Param("name") String name,@Param("phone") String phone);
    List<User> findAll();
    User findUserByPhone(@Param("phone") String phone);

}
