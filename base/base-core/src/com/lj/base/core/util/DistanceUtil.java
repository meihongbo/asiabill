package com.lj.base.core.util;
/**
 * Copyright &copy; 2017-2020  All rights reserved.
 *
 * Licensed under the 深圳市领居科技 License, Version 1.0 (the "License");
 * 
 */


/**
 * 
 * 
 * 类说明：距离计算
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
public class DistanceUtil {



	/** The Constant EARTH_RADIUS. */
	private static final double EARTH_RADIUS = 6378137;

	/**
	 * 方法说明：rad.
	 *
	 * @param d the d
	 * @return the double
	 */
	private static double rad(double d)
	{
		return d * Math.PI / 180.0;
	}

	/**
	 * 方法说明： 根据两点间经纬度坐标（double值），计算两点间距离，单位为米.
	 *
	 * @param lng1 经度 如：22.234235
	 * @param lat1 纬度 如：122.124223
	 * @param lng2 经度
	 * @param lat2 纬度
	 * @return the distance
	 * @author 彭阳 
	 * CreateDate: 2017年7月1日
	 */
	public static double getDistance(double lng1, double lat1, double lng2, double lat2)
	{
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) + 
				Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}

	
	/**
	 * 方法说明：获取某距离内的最大最小经纬度.
	 *
	 * @param lng1 经度
	 * @param lat1 纬度
	 * @param distance 米
	 * @return the max distance
	 * @author 彭阳 
	 * CreateDate: 2017年7月1日
	 */
	public static MaxDistance getMaxDistance(double lng1, double lat1, double distance)
	{
		distance = distance/1000;//转换成千米
		double range = 180 / Math.PI * distance / 6372.797;     //里面的 1 就代表搜索 1km 之内，单位km
		double lngR = range / Math.cos(lat1 * Math.PI / 180);
		double maxLat = lat1 + range;//最大纬度
		double minLat = lat1 - range;//最小纬度
		double maxLng = lng1 + lngR;//最大经度
		double minLng = lng1 - lngR;//最小经度
		
		MaxDistance maxDistance = new MaxDistance();
		maxDistance.setMaxLat(maxLat);
		maxDistance.setMaxLng(maxLng);
		maxDistance.setMinLat(minLat);
		maxDistance.setMinLng(minLng);
		return maxDistance;
		
	}
	
	public static class MaxDistance{
		
		/** 最大纬度. */
		double maxLat;
		
		/** 最小纬度. */
		double minLat;
		
		/** 最大经度. */
		double maxLng;
		
		/** 最小经度. */
		double minLng;
		
		/**
		 * Gets the 最大纬度.
		 *
		 * @return the 最大纬度
		 */
		public double getMaxLat() {
			return maxLat;
		}
		
		/**
		 * Sets the 最大纬度.
		 *
		 * @param maxLat the new 最大纬度
		 */
		public void setMaxLat(double maxLat) {
			this.maxLat = maxLat;
		}
		
		/**
		 * Gets the 最小纬度.
		 *
		 * @return the 最小纬度
		 */
		public double getMinLat() {
			return minLat;
		}
		
		/**
		 * Sets the 最小纬度.
		 *
		 * @param minLat the new 最小纬度
		 */
		public void setMinLat(double minLat) {
			this.minLat = minLat;
		}
		
		/**
		 * Gets the 最大经度.
		 *
		 * @return the 最大经度
		 */
		public double getMaxLng() {
			return maxLng;
		}
		
		/**
		 * Sets the 最大经度.
		 *
		 * @param maxLng the new 最大经度
		 */
		public void setMaxLng(double maxLng) {
			this.maxLng = maxLng;
		}
		
		/**
		 * Gets the 最小经度.
		 *
		 * @return the 最小经度
		 */
		public double getMinLng() {
			return minLng;
		}
		
		/**
		 * Sets the 最小经度.
		 *
		 * @param minLng the new 最小经度
		 */
		public void setMinLng(double minLng) {
			this.minLng = minLng;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("MaxDistance [maxLat=").append(maxLat)
					.append(", minLat=").append(minLat).append(", maxLng=")
					.append(maxLng).append(", minLng=").append(minLng)
					.append("]");
			return builder.toString();
		}
	}
	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		double distance = getDistance(121.01939352181269, 22,121,22);
		System.out.println("Distance is:"+distance);
		MaxDistance maxDistance = getMaxDistance(121.01939352181269,22,2000);
		System.out.println("Distance is:"+maxDistance);
	}


}
