package com.byw.flashcard.pojo;

import java.io.Serializable;

/**
 * @author 王碧云
 * @description: TODO
 * @date 2020/3/25/025 20:44
 */
public class Color implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String color;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
