package com.byw.flashcard.util;

import com.byw.flashcard.mapper.NumberMapper;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 王碧云
 * @description: 生成数字图片
 * @date 2020/1/30/030 10:06
 */
//@Component
public class NumberPic {
    /*@Autowired
    private NumberMapper numberMapper;

    private static NumberPic numberPic;

    @PostConstruct
    public void NumberPic(){
        numberPic = this;
        NumberPic.numberMapper = this.numberMapper;
    }*/

    private BufferedImage image;
    private int imageWidth = 300;  //图片的宽度
    private int imageHeight = 300; //图片的高度
    //生成图片文件
    @SuppressWarnings("restriction")
    public void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if(image != null){
            try {
                FileOutputStream fos = new FileOutputStream(fileLocation);
                bos = new BufferedOutputStream(fos);

                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
                encoder.encode(image);
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(bos!=null){//关闭输出流
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void graphicsGeneration() {

        for(int i=0;i<=100;i++){
            String number = String.valueOf(i);
            //String fileLocation = "G:\\pic\\"+number+".jpg";
            String fileLocation = "src\\main\\resources\\images\\"+number+".jpg";
            image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
            //设置图片的背景色
            Graphics2D main = image.createGraphics();
            main.setColor(Color.white);
            main.fillRect(0, 0, imageWidth, imageHeight);

            Graphics title = image.createGraphics();
            title.setColor(Color.BLACK);
            //设置字体
            Font titleFont = new Font("宋体", Font.BOLD, 45);
            title.setFont(titleFont);
            title.drawString(number, (imageHeight)/2-5, (imageHeight)/2-5);

//            System.out.println(i+"======"+fileLocation);
//            numberMapper.insertNumPic(fileLocation);
            createImage(fileLocation);
        }
    }

    public static void main(String[] args) {

        NumberPic cg = new NumberPic();
        try {
            cg.graphicsGeneration();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
