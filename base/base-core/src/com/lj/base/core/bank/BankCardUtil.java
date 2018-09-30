
package com.lj.base.core.bank;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */


/**
 * 
 * 
 * 类说明：
 * 国内的银行卡号是一串根据Luhm校验算法计算出来的数字，Luhm校验规则：16位银行卡号（19位通用）
	1.将未带校验位的 15（或18）位卡号从右依次编号 1 到 15（18），位于奇数位号上的数字乘以 2。
	2.将奇位乘积的个十位全部相加，再加上所有偶数位上的数字。
	3.将加法和加上校验位能被 10 整除。
	演示Luhm算法示例：
	　
	比如卡号：
	6 2 2 5 8 8 1 4 1 4 2 0 7 4 3
	* 2 2 2 2 2 2 2 2
	--------------------------------------------------
	12 2 4 5 16 8 2 4 2 4 4 14 4 6
	将上面的数字加和：1+2+2+4+5+1+6+8+2+4+2+4+4+1+4+4+6 = 60
	由于 60 加上 0 才能被 10 整除，所以校验位为 0
	因此该卡号为 6225 8814 1420 7430
	根据《黑龙江省农村信用社卡片管理办法》第六条规定，鹤卡借记卡卡号组成方案：6位BIN号+2位卡种类（“00”代表普通借记卡、“11” 代表联名卡、“01”代表单位卡、“02”代表单位附属卡、“03”代表个人记名卡、“04”代表个人附属卡、“66”代表白金卡、“77”代表员工卡、“88”代表金卡、“99”代表钻石卡）+3位城市代码+7位顺序号+1位校验位，共19位卡号组成。
	试验：
	黑龙江信合金卡19位：6212288802000001666
	19位卡从右到左去掉最后一位验证,6，奇数位*2后各位相加+偶数位
	（1+2）+6+2+0+0+0+0+0+4+0+（1+6）+8+（1+6）+2+4+1+4+6=54
	要被10整除，所以末尾位为6
	兴业银行理财卡18位：622909563262617111
	18位卡从右到左去掉最后一位验证码1，奇数位*2后各位相加+偶数位
	2+1+（1+4）+1+（1+2）+2+（1+2）+2+6+6+（1+0）+9+0+9+4+2+（1+2）=59
	要被10整除，所以末尾位为1
 *  
 * 
 * <p>
 * 详细描述：
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月14日
 */
public class BankCardUtil {
	
	
	  /**
  	 * 校验银行卡卡号.
  	 *
  	 * @param cardId the card id
  	 * @return true, if check bank card
  	 */
    public static boolean checkBankCard(String cardId) {
    		 char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
    		 if(bit == 'N'){
    			 return false;
    		 }
    		 return cardId.charAt(cardId.length() - 1) == bit;
    }
   
    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位.
     *
     * @param nonCheckCodeCardId the non check code card id
     * @return the bank card check code
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId){
        if(nonCheckCodeCardId == null || nonCheckCodeCardId.trim().length() == 0
                || !nonCheckCodeCardId.matches("\\d+")) {
        	//如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for(int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if(j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;           
        }
        return (luhmSum % 10 == 0) ? '0' : (char)((10 - luhmSum % 10) + '0');
    }

    public static void main(String args []){
    	System.out.println( checkBankCard("6222521315199101"));
    	
    }
    
    
}
