package com.byw.flashcard.pojo;

import java.io.Serializable;

/**
 * @author 王碧云
 * @description: 组合图形表
 * @date 2020/2/10/010 22:31
 */
public class Composite implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String path;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
