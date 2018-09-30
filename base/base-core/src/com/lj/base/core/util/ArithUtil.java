package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */

import java.math.BigDecimal;

/**
 * 
 * 
 * 类说明：封装加减乘除操作的自定义工具类
 *  
 * 
 * <p>
 * 详细描述：float ,double 只能用来做科学计算或者工程计算，但在商业计算中要用java.math.BigDecimal
 *   
 * @Company: 领居科技有限公司
 * @author 彭阳
 *   
 * CreateDate: 2017年7月1日
 */
public class ArithUtil {
	
	/** double 100. */
	private static final double DIV_100 = 100d;

	/** BIG_DECIMAL 100. */
	private static final BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");
	
	/** 默认除法运算精度. */
	private static final int DEF_DIV_SCALE = 10;

	/**
	 *不能实例化.
	 */
	private ArithUtil() {
	}

	/**
	 * 说明：
	 * 提供精确的加法运算
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 *
	 * @param v1 被加数
	 * @param v2 加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));// 建议写string类型的参数，下同
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * 说明：
	 * 提供精确的减法运算
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return the double
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}

	/**
	 * 说明：
	 * 提供精确的乘法运算
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return the double
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}

	/**
	 * 说明：
	 * 提供相对精确的除法运算，当发生除不尽的情况，精确到.后10位
	 * @author 彭阳 
	 * 创建日期: 2017-7-1
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @return the double
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}

	/**
	 * 说明：
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 *
	 * @param v1 the v1
	 * @param v2 the v2
	 * @param scale the scale
	 * @return the double
	 */
	private static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(" the scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();// scale 后的四舍五入
	}
	
	/**
	 * 方法说明：元转分.
	 *
	 * @param v1 the v1
	 * @return the double
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 */
	public static double yuanToFen(double v1) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = BIG_DECIMAL_100;
		return b1.multiply(b2).doubleValue();
	}
	
	/**
	 * 方法说明：分转元.
	 *
	 * @param v1 the v1
	 * @return the double
	 * @author 彭阳 
	 * CreateDate: 2017-7-1
	 */
	public static double fenToYuan(double v1) {
		return div(v1, DIV_100, DEF_DIV_SCALE);
	}

}
