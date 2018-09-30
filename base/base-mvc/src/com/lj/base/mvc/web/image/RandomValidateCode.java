package com.lj.base.mvc.web.image;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 
 * 类说明：随机生成字符串并输出为jpg格式
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class RandomValidateCode {

    /** The Constant RANDOMCODEKEY. */
    public static final String RANDOMCODEKEY = "randomImageCode";//放到session中的key
    
    /** The random. */
    private Random random = new Random();
    
    /** The rand string. */
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
    
    /** The width. */
    private int width = 80;//图片宽
    
    /** The height. */
    private int height = 26;//图片高
    
    /** The line size. */
    private int lineSize = 20;//干扰线数量
    
    /** The string num. */
    private int stringNum = 4;//随机产生字符数量
    /**
     * 获得字体.
     *
     * @return the font
     */
    private Font getFont(){
        return new Font("Fixedsys",Font.CENTER_BASELINE,18);
    }
    /**
     * 获得颜色.
     *
     * @param fc the fc
     * @param bc the bc
     * @return the rand color
     */
    private Color getRandColor(int fc,int bc){
        if(fc > 255)
            fc = 255;
        if(bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc-fc-16);
        int g = fc + random.nextInt(bc-fc-14);
        int b = fc + random.nextInt(bc-fc-18);
        return new Color(r,g,b);
    }
    
    /**
     * 生成随机图片.
     *
     * @param request the request
     * @param response the response
     */
    public void getRandcode(HttpServletRequest request,
            HttpServletResponse response) {
        HttpSession session = request.getSession();
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ROMAN_BASELINE,18));
        g.setColor(getRandColor(110, 133));
        //绘制干扰线
        for(int i=0;i<=lineSize;i++){
            drowLine(g);
        }
        //绘制随机字符
        String randomString = "";
        for(int i=1;i<=stringNum;i++){
            randomString=drowString(g,randomString,i);
        }
        session.removeAttribute(RANDOMCODEKEY);
        session.setAttribute(RANDOMCODEKEY, randomString);

        g.dispose();
        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());//将内存中的图片通过流动形式输出到客户端
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 绘制字符串.
     *
     * @param g the g
     * @param randomString the random string
     * @param i the i
     * @return the string
     */
    private String drowString(Graphics g,String randomString,int i){
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101),random.nextInt(111),random.nextInt(121)));
        String rand = String.valueOf(getRandomString(random.nextInt(randString.length())));
        randomString +=rand;
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13*i, 16);
        return randomString;
    }
    /**
     * 绘制干扰线.
     *
     * @param g the g
     */
    private void drowLine(Graphics g){
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x+xl, y+yl);
    }
    /**
     * 获取随机的字符.
     *
     * @param num the num
     * @return the random string
     */
    public String getRandomString(int num){
        return String.valueOf(randString.charAt(num));
    }
}