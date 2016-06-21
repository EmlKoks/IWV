package com.kokoszkiewicz.iwv.services;

import java.util.Date;
import java.util.GregorianCalendar;

public final class FinalVariable {
	public static int defPageOnSite = 10;
	public static int yearMin=1960;
	public static int yearMax=2020;
	public static long dayInMilis= 86400000;
	public static Date rentedTime = new GregorianCalendar(1970, 1, 0).getTime();
	public static String defPoster = "http://ocdn.eu/paas-static/template-engine/3bf22dc2d1e5adb0424883182e790f02/b01.png";
}
