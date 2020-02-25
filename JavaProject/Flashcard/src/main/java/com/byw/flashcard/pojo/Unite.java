package com.byw.flashcard.pojo;

import java.io.Serializable;

/**
 * @author 王碧云
 * @description: 图形关联表
 * @date 2020/2/10/010 22:52
 */
public class Unite implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long compositeId;
    private Long patternId;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompositeId() {
        return compositeId;
    }

    public void setCompositeId(Long compositeId) {
        this.compositeId = compositeId;
    }

    public Long getPatternId() {
        return patternId;
    }

    public void setPatternId(Long patternId) {
        this.patternId = patternId;
    }
}
