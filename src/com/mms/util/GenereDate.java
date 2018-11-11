package com.mms.util;

import java.text.DateFormat;
import java.util.Date;

public class GenereDate {

	private String dateCourante;
	static Date date;
	static DateFormat longDateFormat = DateFormat.getDateTimeInstance(2, 2);
	static DateFormat shortDateFormat = DateFormat.getDateTimeInstance(3, 3);

	public GenereDate() {
		date = new Date();
		//String dateL = shortDateFormat.format(date);
		String dateF = longDateFormat.format(date);
		setDateCourante(dateF.substring(0, 12));
	}

	public String getDateCourante() {
		return dateCourante;
	}

	public void setDateCourante(String dateCourante) {
		this.dateCourante = dateCourante;
	}

	public static void main(String args[]) {
		System.out.println((new GenereDate()).getDateCourante());
	}

}
