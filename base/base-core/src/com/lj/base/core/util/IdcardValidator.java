package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * 类说明：
 *  
 * 
 * <p>
 * 详细描述：身份证验证.包括 港澳居民来往内地通行证 台湾居民来往大陆通行证
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class IdcardValidator {

	/** 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古", 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏", 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南", 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆", 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃", 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}. */  
	protected static String codeAndCity[][] = { { "11", "北京" }, { "12", "天津" },   
		{ "13", "河北" }, { "14", "山西" }, { "15", "内蒙古" }, { "21", "辽宁" },   
		{ "22", "吉林" }, { "23", "黑龙江" }, { "31", "上海" }, { "32", "江苏" },   
		{ "33", "浙江" }, { "34", "安徽" }, { "35", "福建" }, { "36", "江西" },   
		{ "37", "山东" }, { "41", "河南" }, { "42", "湖北" }, { "43", "湖南" },   
		{ "44", "广东" }, { "45", "广西" }, { "46", "海南" }, { "50", "重庆" },   
		{ "51", "四川" }, { "52", "贵州" }, { "53", "云南" }, { "54", "西藏" },   
		{ "61", "陕西" }, { "62", "甘肃" }, { "63", "青海" }, { "64", "宁夏" },   
		{ "65", "新疆" }, { "71", "台湾" }, { "81", "香港" }, { "82", "澳门" },   
		{ "91", "国外" } };   

	/** The city code. */
	private static String cityCode[] = { "11", "12", "13", "14", "15", "21", "22",   
		"23", "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",   
		"44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63",   
		"64", "65", "71", "81", "82", "91" };   

	// 每位加权因子   
	/** The power. */
	private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };   

	// 第18位校检码   
	/*private static String verifyCode[] = { "1", "0", "X", "9", "8", "7", "6", "5",   
            "4", "3", "2" };   */


	/**
	 * 验证所有的身份证的合法性.
	 *
	 * @param idcard the idcard
	 * @return true, if is validated all idcard
	 */  
	public static boolean isValidatedAllIdcard(String idcard) {   
		if (idcard.length() == 15) {   
			idcard = convertIdcarBy15bit(idcard);   
		}   
		return isValidate18Idcard(idcard);   
	}   

	/**
	 * <p>
	 * 判断18位身份证的合法性
	 * </p>
	 * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
	 * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * <p>
	 * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
	 * </p>
	 * <p>
	 * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
	 * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码；
	 * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
	 * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
	 * </p>
	 * <p>
	 * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4
	 * 2 1 6 3 7 9 10 5 8 4 2
	 * </p>
	 * <p>
	 * 2.将这17位数字和系数相乘的结果相加。
	 * </p>
	 * <p>
	 * 3.用加出来和除以11，看余数是多少？
	 * </p>
	 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3
	 * 2。
	 * <p>
	 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
	 * </p>
	 *
	 * @param idcard the idcard
	 * @return true, if is validate18 idcard
	 */  
	public static boolean isValidate18Idcard(String idcard) {   
		// 非18位为假   
		if (idcard.length() != 18) {   
			return false;   
		}   
		// 获取前17位   
		String idcard17 = idcard.substring(0, 17);   
		// 获取第18位   
		String idcard18Code = idcard.substring(17, 18);   
		char c[] = null;   
		String checkCode = "";   
		// 是否都为数字   
		if (isDigital(idcard17)) {   
			c = idcard17.toCharArray();   
		} else {   
			return false;   
		}   

		if (null != c) {   
			int bit[] = new int[idcard17.length()];   

			bit = converCharToInt(c);   

			int sum17 = 0;   

			sum17 = getPowerSum(bit);   

			// 将和值与11取模得到余数进行校验码判断   
			checkCode = getCheckCodeBySum(sum17);   
			if (null == checkCode) {   
				return false;   
			}   
			// 将身份证的第18位与算出来的校码进行匹配，不相等就为假   
			if (!idcard18Code.equalsIgnoreCase(checkCode)) {   
				return false;   
			}   
		}   
		return true;   
	}   

	/**
	 * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。.
	 *
	 * @param idcard the idcard
	 * @return true, if is validate15 idcard
	 */  
	public static boolean isValidate15Idcard(String idcard) {   
		// 非15位为假   
		if (idcard.length() != 15) {   
			return false;   
		}   

		// 是否全都为数字   
		if (isDigital(idcard)) {   
			String provinceid = idcard.substring(0, 2);   
			String birthday = idcard.substring(6, 12);   
			int year = Integer.parseInt(idcard.substring(6, 8));   
			int month = Integer.parseInt(idcard.substring(8, 10));   
			int day = Integer.parseInt(idcard.substring(10, 12));   

			// 判断是否为合法的省份   
			boolean flag = false;   
			for (String id : cityCode) {   
				if (id.equals(provinceid)) {   
					flag = true;   
					break;   
				}   
			}   
			if (!flag) {   
				return false;   
			}   
			// 该身份证生出日期在当前日期之后时为假   
			Date birthdate = null;   
			try {   
				birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);   
			} catch (ParseException e) {   
				e.printStackTrace();   
			}   
			if (birthdate == null || new Date().before(birthdate)) {   
				return false;   
			}   

			// 判断是否为合法的年份   
			GregorianCalendar curDay = new GregorianCalendar();   
			int curYear = curDay.get(Calendar.YEAR);   
			int year2bit = Integer.parseInt(String.valueOf(curYear)   
					.substring(2));   

			// 判断该年份的两位表示法，小于50的和大于当前年份的，为假   
			if ((year < 50 && year > year2bit)) {   
				return false;   
			}   

			// 判断是否为合法的月份   
			if (month < 1 || month > 12) {   
				return false;   
			}   

			// 判断是否为合法的日期   
			boolean mflag = false;   
			curDay.setTime(birthdate);  //将该身份证的出生日期赋于对象curDay   
			switch (month) {   
			case 1:   
			case 3:   
			case 5:   
			case 7:   
			case 8:   
			case 10:   
			case 12:   
				mflag = (day >= 1 && day <= 31);   
				break;   
			case 2: //公历的2月非闰年有28天,闰年的2月是29天。   
				if (curDay.isLeapYear(curDay.get(Calendar.YEAR))) {   
					mflag = (day >= 1 && day <= 29);   
				} else {   
					mflag = (day >= 1 && day <= 28);   
				}   
				break;   
			case 4:   
			case 6:   
			case 9:   
			case 11:   
				mflag = (day >= 1 && day <= 30);   
				break;   
			}   
			if (!mflag) {   
				return false;   
			}   
		} else {   
			return false;   
		}   
		return true;   
	}   

	/**
	 * 将15位的身份证转成18位身份证.
	 *
	 * @param idcard the idcard
	 * @return the String
	 */  
	public  static String convertIdcarBy15bit(String idcard) {   
		String idcard17 = null;   
		// 非15位身份证   
		if (idcard.length() != 15) {   
			return null;   
		}   

		if (isDigital(idcard)) {   
			// 获取出生年月日   
			String birthday = idcard.substring(6, 12);   
			Date birthdate = null;   
			try {   
				birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);   
			} catch (ParseException e) {   
				e.printStackTrace();   
			}   
			Calendar cday = Calendar.getInstance();   
			cday.setTime(birthdate);   
			String year = String.valueOf(cday.get(Calendar.YEAR));   

			idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);   

			char c[] = idcard17.toCharArray();   
			String checkCode = "";   

			if (null != c) {   
				int bit[] = new int[idcard17.length()];   

				// 将字符数组转为整型数组   
				bit = converCharToInt(c);   
				int sum17 = 0;   
				sum17 = getPowerSum(bit);   

				// 获取和值与11取模得到余数进行校验码   
				checkCode = getCheckCodeBySum(sum17);   
				// 获取不到校验位   
				if (null == checkCode) {   
					return null;   
				}   

				// 将前17位与第18位校验码拼接   
				idcard17 += checkCode;   
			}   
		} else { // 身份证包含数字   
			return null;   
		}   
		return idcard17;   
	}   

	/**
	 * 数字验证.
	 *
	 * @param str the str
	 * @return true, if is digital
	 */  
	private static boolean isDigital(String str) {   
		return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");   
	}   

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值.
	 *
	 * @param bit the bit
	 * @return the power sum
	 */  
	private static int getPowerSum(int[] bit) {   

		int sum = 0;   

		if (power.length != bit.length) {   
			return sum;   
		}   

		for (int i = 0; i < bit.length; i++) {   
			for (int j = 0; j < power.length; j++) {   
				if (i == j) {   
					sum = sum + bit[i] * power[j];   
				}   
			}   
		}   
		return sum;   
	}   

	/**
	 * 将和值与11取模得到余数进行校验码判断.
	 *
	 * @param sum17 the sum17
	 * @return 校验位
	 */  
	private static String getCheckCodeBySum(int sum17) {   
		String checkCode = null;   
		switch (sum17 % 11) {   
		case 10:   
			checkCode = "2";   
			break;   
		case 9:   
			checkCode = "3";   
			break;   
		case 8:   
			checkCode = "4";   
			break;   
		case 7:   
			checkCode = "5";   
			break;   
		case 6:   
			checkCode = "6";   
			break;   
		case 5:   
			checkCode = "7";   
			break;   
		case 4:   
			checkCode = "8";   
			break;   
		case 3:   
			checkCode = "9";   
			break;   
		case 2:   
			checkCode = "x";   
			break;   
		case 1:   
			checkCode = "0";   
			break;   
		case 0:   
			checkCode = "1";   
			break;   
		}   
		return checkCode;   
	}   

	/**
	 * 将字符数组转为整型数组.
	 *
	 * @param c the c
	 * @return the int[]
	 * @throws NumberFormatException the number format exception
	 */  
	private static int[] converCharToInt(char[] c) throws NumberFormatException {   
		int[] a = new int[c.length];   
		int k = 0;   
		for (char temp : c) {   
			a[k++] = Integer.parseInt(String.valueOf(temp));   
		}   
		return a;   
	}   


	/**
	 * 方法说明：台湾居民来往大陆通行证（台胞证）：来自WIKI。目前仅支持8位数字的台胞证。<br>
	 * <p>自1992年5月启用，台胞证号码由十位阿拉伯数字和带括号的两位阿拉伯数字或一位英文字母组成。<br>
	 * <br>其中前八位数字为终身号码，紧接着的两位数为证件版本号，代表领证次数，而括号内的英文字母或阿拉伯数字则是公安部出入境管理局委托的签发机关的代码，主要委托外交部驻香港特派员公署(A)、香港中国旅行社(B)、外交部驻澳门特派员公署(C)、澳门中国旅行社(D)，由广东省公安厅签发；其他签发地由上海市公安局(31)、江苏省公安厅(32)、福建省公安厅(35)签发。
	 * <br>代表签发机关的两位数字与大陆居民身份证首两位数字的规则定义相同。
	 * <br>自2006年5月19日起，福建、江苏和上海等行政区公安机关签发的5年有效台胞证，取消了签发机关代码。
	 * <br>自2008年9月25日起，台胞证号码实行“一人一号、终身不变”，资料页上的“号码”栏为八位终身号码；多次来往大陆签注和长期居留签注上的“通行证号码”栏仍然为十位号码，前八位为终身号码，紧接两位领证次数，相当于无签发机关代码的旧版台胞证号码。
	 * <br>如：02702256-03</p>.
	 *
	 * @param str the str
	 * @return true, if is validate tw cep
	 * @author 彭阳 CreateDate: 2017年7月1日
	 */
	public  static boolean isValidateTwCep(String str){
		String regex = "^\\d{8}$";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(str);  
		return matcher.matches();
	}


	/**
	 * 方法说明：港澳居民来往内地通行证:来自百度百科。目前仅支持H/M+8位数字的港澳居民来往内地通行证。<br>
	 * <p>港澳居民来往内地通行证（以下简称通行证，式样见附件1）于1999年1月15日启用。
	 * <br>通行证为卡式证件，通行证有效期分为3年和10年两种，持证人年满18周岁的为10年有效，未满18周岁的为3年有效。　
	 * <br>通行证号码组成规则：通行证证件号码共11位。第1位为字母，“H”字头签发给香港居民，“M”字头签发给澳门居民；第2位至第11位为数字，前8位数字为通行证持有人的终身号，后2位数字表示换证次数，首次发证为00，此后依次递增。	 <br>
	 * <br>如：H12345678-00</p>.
	 *
	 * @param str the str
	 * @return true, if is validate h k_ m_ rmp
	 * @author 彭阳 CreateDate: 2017年7月1日
	 */
	public  static boolean isValidateHK_M_RMP(String str){
		String regex = "^[H,M]{1}\\d{8}$";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(str);  
		return matcher.matches();
	}

	/**
	 * 方法说明：工号验证:首页字母+7位数字 只支持普通员工<br>
	 * <br>如：H1234567</p>.
	 *
	 * @param str the str
	 * @return true, if is validate h k_ m_ rmp
	 * @author 彭阳 CreateDate: 2017年7月1日
	 */
	public  static boolean isValidateFoxconnJobNum(String str){
		String regex = "^[a-zA-z]{1}\\d{7}$";  
		Pattern pattern = Pattern.compile(regex);  
		Matcher matcher = pattern.matcher(str);  
		return matcher.matches();
	}


	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws Exception the exception
	 */
	public static void main(String[] args) throws Exception {   
		/*String idcard15 = "";   
		String idcard18 = "330522197506063513";   
		boolean flag = false;   
		flag = IdcardValidator.isValidate18Idcard(idcard18);   
		System.out.println(flag);   

		 flag = IdcardValidator.isValidate15Idcard(idcard15);   
        System.out.println(flag);   

        System.out.println(IdcardValidator.convertIdcarBy15bit(idcard15));   
        flag = IdcardValidator.isValidate18Idcard(IdcardValidator.convertIdcarBy15bit(idcard15));   
        System.out.println(flag);   

		System.out.println(IdcardValidator.isValidatedAllIdcard(idcard18));   */

		//台胞证
		System.out.println(IdcardValidator.isValidateTwCep("02702256"));

		//港澳居民来往内地通行证
		System.out.println(IdcardValidator.isValidateHK_M_RMP("H12345678"));

		//工号验证
		System.out.println(IdcardValidator.isValidateFoxconnJobNum("H1234567"));

	}   
}
