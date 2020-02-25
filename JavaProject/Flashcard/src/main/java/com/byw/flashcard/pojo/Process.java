package com.byw.flashcard.pojo;

/**
 * @author 王碧云
 * @description: 进度表
 * @date 2020/2/24/024 22:39
 */
public class Process {

    private static final long serialVersionUID = 1L;
    private Long id;
    private Long userId;
    private Long numId;
    private Long patId;
    private Long colorId;
    private Long module;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getNumId() {
        return numId;
    }

    public void setNumId(Long numId) {
        this.numId = numId;
    }

    public Long getPatId() {
        return patId;
    }

    public void setPatId(Long patId) {
        this.patId = patId;
    }

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public Long getModule() {
        return module;
    }

    public void setModule(Long module) {
        this.module = module;
    }
}
