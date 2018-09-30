package com.lj.base.core.encryption;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.security.MessageDigest;

/**
 * 
 * 
 * 类说明：
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
public class SHA{
	
	/** The Constant SHA1. */
	public static final String SHA1="SHA1";
	
	/** The Constant SHA256. */
	public static final String SHA256="SHA-256";
	
	/** The Constant SHA384. */
	public static final String SHA384="SHA-384";
	
	/** The Constant SHA512. */
	public static final String SHA512="SHA-512";
    
    /**
     * The Constructor.
     */
    public SHA() {
    }

    /**
     * Encrypt sha.
     *
     * @param data the data
     * @param shaKey the sha key
     * @return the byte[]
     * @throws Exception the exception
     */
    public static byte[] encryptSHA(byte data[],String shaKey)
        throws Exception {
        MessageDigest sha = MessageDigest.getInstance(shaKey);
        sha.update(data);
        return sha.digest();
    }
    

    /**
     * Encrypt sha byte.
     *
     * @param str the str
     * @param shaKey the sha key
     * @return the byte[]
     * @throws Exception the exception
     */
    public static byte[] encryptSHAByte(String str,String shaKey)
        throws Exception {
        return encryptSHA(str.getBytes("UTF-8"),shaKey);
    }

    /**
     * Encrypt sha.
     *
     * @param str the str
     * @param shaKey the sha key
     * @return the string
     * @throws Exception the exception
     */
    public static String encryptSHA(String str,String shaKey)
        throws Exception {
        return getFormattedText(encryptSHA(str.getBytes("UTF-8"),shaKey));
    }
    
    /**
     * Gets the formatted text.
     *
     * @param bytes the bytes
     * @return the formatted text
     */
    private static String getFormattedText(byte bytes[]){
        int len = bytes.length;
        StringBuilder buf = new StringBuilder(len * 2);
        for(int j = 0; j < len; j++)
        {
            buf.append(HEX_DIGITS[bytes[j] >> 4 & 15]);
            buf.append(HEX_DIGITS[bytes[j] & 15]);
        }

        return buf.toString();
    }

    /**
     * The main method.
     *
     * @param args the args
     * @throws Exception the exception
     */
    public static void main(String args[])
        throws Exception {
//        for(int i = 0; i < 5; i++)
            String str="15325487asdfasdfjkljyo";
            System.out.println(SHA.encryptSHA(str,SHA.SHA1));
            System.out.println(SHA.encryptSHA(str,SHA.SHA256));
            System.out.println(SHA.encryptSHA(str,SHA.SHA384));
            System.out.println(SHA.encryptSHA(str,SHA.SHA512));

    }

    /** The Constant HEX_DIGITS. */
    private static final char HEX_DIGITS[] = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 
        'a', 'b', 'c', 'd', 'e', 'f'
    };

}
