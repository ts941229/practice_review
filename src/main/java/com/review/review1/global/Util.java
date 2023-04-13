package com.review.review1.global;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	
	private static Util util;
	
	private Util() {}
	
	public static Util getInstance() {
		if(util==null) {
			util = new Util();
		}
		return util;
	}
	
	public String dateFormat(Date date) {
		
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf1.format(date);
		
	}
}
