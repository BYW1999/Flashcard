package com.byw.flashcard.pojo;

import java.io.Serializable;

/**
 * @author 王碧云
 * @description: 图形表
 * @date 2020/2/10/010 17:17
 */
public class Pattern implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String num;
    private String audio;
    private String spell;
    private String word;

    public Pattern(Long id, String num, String audio, String spell, String word) {
        this.id = id;
        this.num = num;
        this.audio = audio;
        this.spell = spell;
        this.word = word;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
