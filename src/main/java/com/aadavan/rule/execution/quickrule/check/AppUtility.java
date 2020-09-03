package com.aadavan.rule.execution.quickrule.check;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.sql.*;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.time.*;
import java.util.Date;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

//import java.util.concurrent.CopyOnWriteArrayList;


//import com.enstage.sas.accosaeas.common.geoip.IpToCountry;
//import org.apache.commons.collections.HashBag;

public class AppUtility {
	private static final Logger logger = Logger.getLogger(AppUtility.class.getName());

	private static String appRoot = System.getProperty("ACCOSA_APP_ROOT");
	private static SecureRandom srandom;
	private static Random random;

	static {
		if (appRoot == null) {
			appRoot = "d:/";
		}

		try {
			random = new Random();
			srandom = SecureRandom.getInstance("SHA1PRNG");
		} catch (Exception e) {
			logger.warning("Error 1:"+AppUtility.getStackTrace(e));
			
		} catch (Error e) {
			logger.warning("Error 2:"+AppUtility.getStackTrace(e));
		}

		/*HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:mysql://192.168.106.69:3306/csr_trident");
		config.setUsername("root");
		config.setPassword("root");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		
		
		if(ds == null){
			ds = new HikariDataSource(config);
			logger.info("******* Mysql DS initialized via HikariDataSource*******");
		}	*/
	}

	public static String getCompleteFilePathX(String relaviePath) {
		if (relaviePath == null) {
			return relaviePath;
		}

		return appRoot + relaviePath;
	}

	public static boolean isInteger(String argument){
		try {
			Integer.parseInt(argument);
			return true;
		} catch (Exception e) {
		
		}
		return false;
	}
	
	
	public static boolean isLong(String argument){
		try {
			Long.parseLong(argument);
			return true;
		} catch (Exception e) {
		
		}
		return false;
	}
	
	public static String currentMethod() {		
		String methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		return methodName;
	}

	public static String getStackTrace(Throwable e) {
		StringWriter writer = new StringWriter(1024);
		e.printStackTrace(new PrintWriter(writer));
		String stackTrace = writer.toString();
		return stackTrace;
	}

	public static String escapeSpecialCharacters(String xml) {
		String escapedString = "";
		if (xml == null) {
			return "";
		}
		if (xml.trim().equals("")) {
			return "";
		}
		escapedString = replaceString(xml, "&", "&amp;");
		escapedString = replaceString(escapedString, "'", "&apos;");
		escapedString = replaceString(escapedString, "\"", "&quot;");
		escapedString = replaceString(escapedString, "<", "&lt;");
		escapedString = replaceString(escapedString, ">", "&gt;");
		return escapedString;
	}

	public static String replaceString(String str, String str1, String str2) {
		if (str == null) {
			return null;
		}
		if (str1 == null) {
			return str;
		}
		if (str2 == null) {
			return str;
		}
		StringBuilder sbStr = new StringBuilder(str);
		int str1length = str1.length();
		int str2length = str2.length();
		int index = 0;
		int fromIndex = -1;
		index = sbStr.toString().indexOf(str1, fromIndex);
		while (index >= 0) {
			sbStr.replace(index, index + str1length, str2);
			fromIndex = index + str2length;
			if (fromIndex <= sbStr.length()) {
				index = sbStr.toString().indexOf(str1, fromIndex);
			} else {
				index = -1;
			}
		}
		return sbStr.toString();
	}
	private static Map sdfMap = new HashMap();

	public static String makeDate(Date date, String format) {
		if (format == null) {
			format = "yyMMdd HH:mm:ss";
		}
		if (date == null) {
			date = new Date();
		}

		SimpleDateFormat sdf = (SimpleDateFormat) sdfMap.get(format);
		if (sdf == null) {
			new SimpleDateFormat(format);
			sdf.setLenient(false);
			//sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
			sdfMap.put(format, sdf);
		}
		String txTime = null;
		try {
			txTime = sdf.format(date);
		} catch (Exception e) {
			txTime = "";
		}
		//logger.debug("date sent["+format+"] : " + txTime);
		return txTime;
	}

	public static String caseConvert(String inputString) {
		if (inputString != null && inputString.trim().equals("") == false) {
			String outputString;
			String result;
			StringTokenizer st = new StringTokenizer(inputString, " ");
			StringBuilder stb = new StringBuilder();
			while (st.hasMoreElements()) {
				result = st.nextToken();
				stb.append((result.charAt(0) + "").toUpperCase()
						+ (result.substring(1, result.length())).toLowerCase() + " ");
			}
			return stb.toString().trim();
		} else {
			return "";
		}
	}

	public static Date rollMonth(Date date, int roll) throws Exception {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		boolean forward = true;
		if (roll < 0) {
			forward = false;
		}

		GregorianCalendar newgc = (GregorianCalendar) gc.clone();
		while (true) {
			if (forward) {
				newgc.roll(Calendar.MONTH , roll);
				if (gc.before(newgc) == false) {
					newgc.roll(Calendar.YEAR, 1);
					gc = (GregorianCalendar) newgc.clone();
				}
				break;
			} else {
				newgc.roll(Calendar.MONTH, roll);
				if (gc.after(newgc) == false) {
					newgc.roll(Calendar.YEAR, -1);
					gc = (GregorianCalendar) newgc.clone();
				}
				break;
			}
		}
		return newgc.getTime();
	}

	/**
	 * Rolls the date	*
	 */
	public static Date rollDate(Date date, int roll) throws Exception {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);

		//logger.debug("In rollDate : Roll="+roll);
		//logger.debug("Date Got : "+gc.getTime());

		boolean forward = true;
		if (roll < 0) {
			forward = false;
		}

		GregorianCalendar newgc = (GregorianCalendar) gc.clone();
		while (true) {
			if (forward) {
				if (roll > 365) {
					newgc.roll(Calendar.DAY_OF_YEAR, 365);
					roll = roll - 365;
					if (gc.before(newgc) == false) {
						//logger.debug("Rolling year");
						newgc.roll(Calendar.YEAR, 1);
						gc = (GregorianCalendar) newgc.clone();
					}
				} else {
					newgc.roll(Calendar.DAY_OF_YEAR, roll);
					if (gc.before(newgc) == false) {
						newgc.roll(Calendar.YEAR, 1);
						//logger.debug("Rolling year");
						gc = (GregorianCalendar) newgc.clone();
					}
					break;
				}
			} else {
				if (roll < -365) {
					newgc.roll(Calendar.DAY_OF_YEAR, -365);
					roll = roll + 365;
					if (gc.after(newgc) == false) {
						//logger.debug("Rolling year");
						newgc.roll(Calendar.YEAR, -1);
						gc = (GregorianCalendar) newgc.clone();
					}
				} else {
					newgc.roll(Calendar.DAY_OF_YEAR, roll);
					if (gc.after(newgc) == false) {
						newgc.roll(Calendar.YEAR, -1);
						//logger.debug("Rolling year");
						gc = (GregorianCalendar) newgc.clone();
					}
					break;
				}
			}
		}
		//logger.debug("Date Sending : "+newgc.getTime());
		return newgc.getTime();
	}

	/**
	 * Rolls the date

	 * Date 01-April-2004
	 */
	public static int dateCompare(String d2) {
		int result = 100;
		try {
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");

			Date currentDate = sdf.parse(sdf.format(gc.getTime()));
			Date d2_1 = sdf.parse(d2);
			result = currentDate.compareTo(d2_1);

		} catch (Exception ex) {
			logger.warning("In exception");
		}
		return result;
	}

	public static int dateCompare2(String d2) {
		int result = 100;
		try {
			GregorianCalendar gc = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date currentDate = new Date();
			Date d2_1 = sdf.parse(d2);
			result = currentDate.compareTo(d2_1);
			logger.info("d2_1 : "+d2_1+", Current Date: "+currentDate+" result: "+result);

		} catch (Exception ex) {
			ex.printStackTrace();
			//logger.warning("In exception");
		}
		return result;
	}

	public static String escapeQuote(String aRegexFragment) {
		if (aRegexFragment == null) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		StringCharacterIterator iterator = new StringCharacterIterator(aRegexFragment);

		char character = iterator.current();
		while (character != StringCharacterIterator.DONE) {
			if (character == '\'') {
				result.append("''");
			} else {
				result.append(character);
			}
			character = iterator.next();
		}
		return result.toString();
	}

	public static boolean validateIsNumber(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (Character.isDigit(str.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	private static Map gmtSdfMap = new HashMap();
	public static String makeGMTDate(Date date, String format) {
		if (format == null) {
			format = "yyMMdd HH:mm:ss";
		}

		SimpleDateFormat sdf = (SimpleDateFormat) gmtSdfMap.get(format);
		if (sdf == null) {
			new SimpleDateFormat(format);
			sdf.setLenient(false);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
			gmtSdfMap.put(format, sdf);
		}
		String txTime = null;
		try {
			txTime = sdf.format(date);
			//logger.debug("txTime GOT :" + txTime);
		} catch (Exception e) {
			txTime = "";
		}
		return txTime;
	}

	public static String extractField(String body, String searchStart, String searchEnd,
			boolean includeSearchTerm) {
		return extractField(body, searchStart, searchEnd, includeSearchTerm, "0");
	}

	public static String extractField(String body, String searchStart, String searchEnd,
			boolean includeSearchTerm, String _startAt) {
		int i = 0;
		int j = 0;
		int searchStartLength = searchStart.length();
		int searchEndLength = searchEnd.length();

		//System.out.println("=body\n:"+body);

		int startAt = Integer.parseInt(_startAt);

		i = body.indexOf(searchStart, startAt);
		if (i == -1) {
			return null;
		}
		j = body.indexOf(searchEnd, i + searchStartLength);

		if (j == -1) {
			return null;
		}

		//System.out.println("i:"+i);
		//System.out.println("j:"+j);
		String res[] = null;

		if (includeSearchTerm) {
			res = new String[]{body.substring(i, j + searchEndLength), "" + (j + searchEndLength - 1)};
		} else {
			res = new String[]{body.substring(i + searchStartLength, j), "" + (j + searchEndLength - 1)};
		}

		return res[0];
	}

	public static GregorianCalendar rollCalender(String usageEndDate, int roll) throws Exception {
		Date d = new SimpleDateFormat("dd-MMM-yyyy").parse(usageEndDate);
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(d);

		//logger.debug("In rollCalender : Roll="+roll);
		//logger.debug("Date Got : "+gc.getTime());

		GregorianCalendar newgc = (GregorianCalendar) gc.clone();
		while (true) {
			if (roll > 365) {
				newgc.roll(Calendar.DAY_OF_YEAR, 365);
				roll = roll - 365;
				if (gc.before(newgc) == false) {
					//logger.debug("Rolling year");
					newgc.roll(Calendar.YEAR, 1);
					gc = (GregorianCalendar) newgc.clone();
				}
			} else {
				newgc.roll(Calendar.DAY_OF_YEAR, roll);
				if (gc.before(newgc) == false) {
					newgc.roll(Calendar.YEAR, 1);
					//logger.debug("Rolling year");
					gc = (GregorianCalendar) newgc.clone();
				}
				break;
			}
		}
		//logger.debug("Date Sending : "+newgc.getTime());
		return newgc;
	}

	public static HashMap setAlertMap(String message) {
		HashMap hm = new HashMap();
		try {
			hm.put("<MESSAGE>", message);
		} catch (Exception e) {
			return null;
		}
		return hm;
	}

	/**
	 * generateRandomNumber
	 */
	public static String generateRandomNumber(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len;) {
			sb.append((char) ((int) '0' + srandom.nextInt(10)));
			i++;
		}
		sb.append(System.nanoTime());
		return sb.toString();
	}

	public static String generateRandomNumber() {
		StringBuilder sb = new StringBuilder();
		long currTime = System.currentTimeMillis();
		int randomIntNO = random.nextInt();
		sb.append(randomIntNO);
		//sb.append(System.nanoTime());
		sb.append(System.currentTimeMillis());
		return sb.toString();
	}

	/*public static IpToLocation getIpToCountry(String ipOrDomain) {
		IpToLocation ipToCountry = null;
		long start = System.currentTimeMillis();
		long end = 0;
		try {
			InetAddress ip;
			//124.30.49.75
			//59.96.20.104
			ip = InetAddress.getByName(ipOrDomain);
			ipToCountry = IpToLocation.getFromIP(ipOrDomain);
			//System.out.println("IpToCountry: "+ipToCountry);
		} catch (UnknownHostException e) {
			logger.warning("Error :"+AppUtility.getStackTrace(e));
		}
		end = System.currentTimeMillis();
		logger.info("TimeTaken for IPtoCountry :"+(end-start) +":iptoCountry status:"+ipToCountry);
		return ipToCountry;
	}*/

	public static String getClassNameOnly(Class c) {
		String FQClassName = c.getName();
		int firstChar;
		firstChar = FQClassName.lastIndexOf('.') + 1;
		if (firstChar > 0) {
			FQClassName = FQClassName.substring(firstChar);
		}
		return FQClassName;
	}

	public static String convertToDisplayAmt(String amt, int cExponent) {
		StringBuilder pAmt = new StringBuilder(amt);
		int l = pAmt.length();
		while (l <= cExponent) {
			pAmt.insert(0, "0");
			l = pAmt.length();
		}
		pAmt.insert(l - cExponent, ".");
		return pAmt.toString();
	}

	public static String convertToDisplayAmt(String pAmt, String cExponent) {
		return convertToDisplayAmt(pAmt, Integer.parseInt(cExponent));
	}

	public static String convertToDisplayAmt(long intPAmt, int intCExponent) {
		return convertToDisplayAmt(intPAmt+"", intCExponent);
	}

	public static BigInteger getIpResolvedValue(String ips) {

		BigInteger base = new BigInteger("256");
		String[] numberTokens = ips.split("\\.");
		int power = 3;
		BigInteger bigResult = new BigInteger("0");
		for (String number : numberTokens) {
			// logger.debug("number: "+number);
			BigInteger bigNumber = new BigInteger(number);
			BigInteger raise = raiseToPow(base, power--);
			// logger.debug("raise: "+raise);
			bigNumber = bigNumber.multiply(raise);
			bigResult = bigResult.add(bigNumber);
		}
		//System.out.println("Calculated result for " + ips + " is " + bigResult);
		return bigResult;
	}

	public static BigInteger getVersionRealNum(String ips) {
		ips = getOnlyNumerics(ips);
		BigInteger base = new BigInteger("256");
		String[] numberTokens = ips.split("\\.");

		if (ips.contains(".")) {
			numberTokens = ips.split("\\.");
		} else if (ips.contains("_")) {
			numberTokens = ips.split("\\_");
		}
		//int power = 3;
		int power = numberTokens.length - 1;
		BigInteger bigResult = new BigInteger("0");
		try {
			for (String number : numberTokens) {
				// logger.debug("number: "+number);
				BigInteger bigNumber = new BigInteger(number);
				BigInteger raise = raiseToPow(base, power--);
				// logger.debug("raise: "+raise);
				bigNumber = bigNumber.multiply(raise);
				bigResult = bigResult.add(bigNumber);
			}
		} catch (Exception e) {
			bigResult = new BigInteger("0");
		}

		//System.out.println("Calculated result for " + ips + " is " + bigResult);
		return bigResult;
	}

	private static BigInteger raiseToPow(BigInteger base, int count) {
		BigInteger bi = new BigInteger("1");
		for (int i = 0; i < count; i++) {
			bi = bi.multiply(base);
		}
		return bi;
	}

	public static String getOnlyNumerics(String str) {
		if (str == null) {
			return null;
		}
		StringBuilder strBuff = new StringBuilder();
		char c;
		for (int i = 0; i < str.length(); i++) {
			c = str.charAt(i);

			if (c == '_') {
				strBuff.append(c);
			}
			if (c == '.') {
				strBuff.append(c);
			}
			if (Character.isDigit(c)) {
				strBuff.append(c);
			}
		}
		return strBuff.toString();
	}

	public static String mask(String number, String instanceId) {
		String maskedCardNo = "";
		String maskingPattern = null;
		if (maskingPattern == null) {
			maskingPattern = "NNNN xxxx xxxx NNNN";
		}
		maskedCardNo = mask(maskingPattern, number, "x");
		return maskedCardNo;
	}

	public static String mask(String pattern, String number, String placeholder) {
		if (number == null || "NNNNNNNNNNNNNNNN".equals(pattern)) {
			return number;
		}

		if ("".equals(number) || pattern == null) {
			return number;
		}

		StringBuilder output = new StringBuilder();
		if (pattern != null && number != null) {
			char[] lpattern = pattern.toCharArray();
			char[] lnumber = number.toCharArray();
			for (int i = 0, j = 0; i < lpattern.length & j < lnumber.length; i++, j++) {
				switch (lpattern[i]) {
					case 'N': {
						output.append(lnumber[j]);
						break;
					}
					case 'x':
					case 'X': {
						if (placeholder != null) {
							output.append(placeholder);
						}
						break;
					}
					default: {
						output.append(lpattern[i]);
						j--;
					}
				}
			}
		} else {
			logger.fine("pattern: " + pattern);
			logger.fine("placeholder: " + placeholder);
			return number;
		}
		return output.toString();
	}
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static Date getTodayDate() {
		Date dtx = new Date();
		String temp = sdf.format(dtx);
		try {
			dtx = sdf.parse(temp);
		} catch (ParseException e) {
			logger.warning("Error :"+AppUtility.getStackTrace(e));
		}
		return dtx;
	}

	public static java.sql.Date getSqlTodayDate() {
		java.sql.Date datex = new java.sql.Date(System.currentTimeMillis());
		//logger.finest("Curr Date:" + datex);
		return datex;
	}

	public static Timestamp get24hrBackDate(){
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -1);
		return new Timestamp(calendar.getTimeInMillis());
	}
	public static Timestamp getHourNextDate(int hours){
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.HOUR_OF_DAY, hours);
		return new Timestamp(calendar.getTimeInMillis());
	}

	public static java.sql.Date getSqlDateDayOffSet(int noOfDays) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, noOfDays);
		Timestamp currentTime = new Timestamp(calendar.getTimeInMillis());
		java.sql.Date datex = new java.sql.Date(calendar.getTimeInMillis());
		return datex;
	}

	public static java.sql.Date getSqlDateMonthOffSet(int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, month);
		Timestamp currentTime = new Timestamp(calendar.getTimeInMillis());
		java.sql.Date datex = new java.sql.Date(calendar.getTimeInMillis());
		return datex;
	}

	public static java.sql.Date getSqlDateYearOffSet(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, year);
		Timestamp currentTime = new Timestamp(calendar.getTimeInMillis());
		java.sql.Date datex = new java.sql.Date(calendar.getTimeInMillis());
		return datex;
	}
	static SimpleDateFormat sdfFileDate = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

	public static String getFileAppendDate() {
		String tempDate = sdfFileDate.format(new Date());
		tempDate = tempDate.replace(" ", "_");
		tempDate = tempDate.replace(":", "_");
		return tempDate;
	}

	public static boolean checkEmptyOrNull(String test) {
		if (test != null && !test.trim().equals("")) {
			return false;
		}
		return true;
	}

	public static void closeDBQuietly(Connection conn, Statement stmt, ResultSet rs) {
		try {
			closeDBQuietly(rs);
		} finally {
			try {
				closeDBQuietly(stmt);
			} finally {
				closeDBQuietly(conn);
			}
		}
	}

	public static void closeDBQuietly(ResultSet rs) {
		try {
			close(rs);
		} catch (Exception e) {
			logger.warning("Error: "+AppUtility.getStackTrace(e));
		}
	}

	public static void closeDBQuietly(Statement stmt) {
		try {
			close(stmt);
		} catch (Exception e) {
			logger.warning("Error: "+AppUtility.getStackTrace(e));
		}
	}

	public static void closeDBQuietly(Connection conn) {
		try {
			close(conn);
		} catch (Exception e) {
			logger.warning("Error: "+AppUtility.getStackTrace(e));
		}
	}

	public static void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * Close a <code>ResultSet</code>, avoid closing if null.
	 *
	 * @param rs ResultSet to close.
	 * @throws SQLException if a database access error occurs
	 */
	public static void close(ResultSet rs) throws SQLException {
		if (rs != null) {
			rs.close();
		}
	}

	/**
	 * Close a <code>Statement</code>, avoid closing if null.
	 *
	 * @param stmt Statement to close.
	 * @throws SQLException if a database access error occurs
	 */
	public static void close(Statement stmt) throws SQLException {
		if (stmt != null) {
			stmt.close();
		}
	}

	public static boolean isBlankNull(String arg) {
		//logger.info("Testing for Null *********remove it*************"+arg);

		if (arg == null) {
			return true;
		}

		if ("".equals(arg)) {
			return true;
		}

		if ("null".equals(arg)) {
			return true;
		}

		if ("NULL".equals(arg)) {
			return true;
		}

		return false;
		/*if(!(arg == null) || "".equals(arg)){
		return true;
		}else{
		return false;
		}*/

	}

	public static String getCurrentTableSuffix(String clientId){
		String tableprefix = null;
		if(clientId != null){
			tableprefix = clientId.substring(0, 6);
		}else{
			tableprefix = getCurrentTableSuffix();
		}
		return tableprefix;
	}

	public static String getCurrentTableSuffix(){
		Date date = new Date();
		return getCurrentTableSuffix(date);
	}

	public static String getCurrentTableSuffix(Date date,String datePattern){
		Format sdfYYYYMM = null;
		if(isBlankNull(datePattern)) {
			sdfYYYYMM = new SimpleDateFormat("yyyyMMddHHmmss");
		}else{
			sdfYYYYMM = new SimpleDateFormat(datePattern);
		}
		return sdfYYYYMM.format(date);
	}

	public static String getCurrentTableSuffix(Date date){
		Format sdfYYYYMM = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdfYYYYMM.format(date);
	}

	public static List getAllTableleSuffix(int numberOfMonths){
		Format sdfYYYYMM =  new SimpleDateFormat("yyyyMM");
		List list = new ArrayList();
		Calendar calendar;
		Date date;
		calendar = Calendar.getInstance();

		date = new Date(calendar.getTimeInMillis());
		list.add(sdfYYYYMM.format(date));
		for (int i = 0; i < numberOfMonths; i++) {
			calendar.add(Calendar.MONTH, -1);
			date = new Date(calendar.getTimeInMillis());
			calendar.setTime(date);
			list.add(sdfYYYYMM.format(date));
		}
		return list;
	}

	public static ArrayList<String> getTimeStampKey(String key, int numberOfMin){
		return getTimeStampKey(key, numberOfMin, new Date());
	}
	public static ArrayList<String> getTimeStampKey(String key, int numberOfHours, boolean hourFlag){
		return getTimeStampKey(key, numberOfHours, new Date(), hourFlag);
	}
	public static ArrayList<String> getTimeStampKey(String key, int numberOfMin, Date time){
        ArrayList<String> list = new ArrayList<String>();
        Calendar currentTime = new GregorianCalendar();
        currentTime.setTime(time);

        currentTime.add(Calendar.MINUTE, 1);
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int min = currentTime.get(Calendar.MINUTE);
        int tempMin, tempHr;
        for (int i=0;i<numberOfMin;i++ ){
            if((min-1)>=0){
                tempMin = min = (min-1);
                tempHr = hour;
            }else{
                tempMin = min = 59;
                if(hour>0) {
                    tempHr = hour = (hour-1);
                } else {
                     tempHr = hour = 23;
                }
            }
            list.add(tempHr + ":" + tempMin + "|" + key);
        }
        return list;
    }
	public static ArrayList<String> getTimeStampKey(String key, int numberOfHours, Date time, boolean hourFlag){
        ArrayList<String> list = new ArrayList<String>();
        Calendar currentTime = new GregorianCalendar();
        currentTime.setTime(time);
        currentTime.add(Calendar.HOUR_OF_DAY, 1);
        int hour = currentTime.get(Calendar.HOUR_OF_DAY);
        int tempHr;
        for (int i=0;i<numberOfHours;i++ ){
        	if(hour>0){
        		tempHr = hour = (hour-1);
        	}else{
        		tempHr = hour = 23;
        	}
        	list.add(tempHr + ":" + key);
        }
        return list;
    }
	/*
        //============================JSON array return

        public static JsonArray getJsonArrayTimeStampKey(List keys, int numberOfMin){
            return getJsonArrayTimeStampKey(keys, numberOfMin, new Date());
        }

        public static JsonArray getJsonArrayTimeStampKey(List keys, int numberOfMin, Date time){
            JsonArray list = JsonArray.create().empty();
            Calendar currentTime = new GregorianCalendar();
            currentTime.setTime(time);

            currentTime.add(Calendar.MINUTE, 1);
            int hour = currentTime.get(Calendar.HOUR_OF_DAY);
            int min = currentTime.get(Calendar.MINUTE);
            int tempMin, tempHr;
            for (int i=0;i<numberOfMin;i++ ){
                if((min-1)>=0){
                    tempMin = min = (min-1);
                    tempHr = hour;
                }else{
                    tempMin = min = 59;
                    if(hour>0) {
                        tempHr = hour = (hour-1);
                    } else {
                         tempHr = hour = 23;
                    }
                }
                JsonArray ja = JsonArray.create().empty();

                for (Object object : keys) {
                    ja.add(object);
                }


                ja.add(tempHr);
                ja.add(tempMin);
                list.add(ja);
            }
            return list;
        }

        public static JsonArray getJsonArrayTimeStampKey(List keys, int numberOfHours, boolean hourFlag){
            return getJsonArrayTimeStampKey(keys, numberOfHours, new Date(), hourFlag);
        }
        public static JsonArray getJsonArrayTimeStampKey(List keys, int numberOfHours, Date time, boolean hourFlag){
            JsonArray list = JsonArray.create().empty();
            Calendar currentTime = new GregorianCalendar();
            currentTime.setTime(time);
            currentTime.add(Calendar.HOUR_OF_DAY, 1);
            int hour = currentTime.get(Calendar.HOUR_OF_DAY);
            int tempHr;
            for (int i=0;i<numberOfHours;i++ ){
                if(hour>0){
                    tempHr = hour = (hour-1);
                }else{
                    tempHr = hour = 23;
                }

                JsonArray ja = JsonArray.create().empty();

                for (Object object : keys) {
                    ja.add(object);
                }

                ja.add(tempHr);

                list.add(ja);
            }
            return list;
        }

        public static JsonArray getJsonArrayTimeStampKeyDay(List keys, int numberOfHours){
            return getJsonArrayTimeStampKeyDay(keys, numberOfHours, new Date());
        }
        public static JsonArray getJsonArrayTimeStampKeyDay(List keys, int numberOfHours, Date time){
            JsonArray list = JsonArray.create().empty();
            Calendar calendar;
            calendar = Calendar.getInstance();
            JsonArray ja = JsonArray.create().empty();

            for (Object object : keys) {
                ja.add(object);
            }

            ja.add(calendar.get(Calendar.YEAR));
            ja.add(calendar.get(Calendar.MONTH)+1);
            ja.add(calendar.get(Calendar.DATE));


            list.add(ja);
            for (int i=0;i<(numberOfHours-1);i++ ){

                calendar.add(Calendar.DATE, -1);

                ja = JsonArray.create().empty();

                for (Object object : keys) {
                    ja.add(object);
                }
                ja.add(calendar.get(Calendar.YEAR));
                ja.add(calendar.get(Calendar.MONTH)+1);
                ja.add(calendar.get(Calendar.DATE));
                list.add(ja);

            }
            return list;
        }

    */
	//=======================Added by Deepak.Modi Start=========================
	public static Date getUtilDateMinOffSet(int noOfMin) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, noOfMin);
		Date dateOffSet = new Date(calendar.getTimeInMillis());
		return dateOffSet;
	}


	public static Timestamp getSqlDatePastNoOfHours(int noOfHours) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR, -noOfHours);
		Timestamp currentTime = new Timestamp(calendar.getTimeInMillis());
		return currentTime;
	}
	public static Timestamp getSqlDatePastNoOfMinutes(int noOfMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, -noOfMinutes);
		Timestamp currentTime = new Timestamp(calendar.getTimeInMillis());
		return currentTime;
	}

	public static Timestamp getSqlDateNextNoOfMinutes(int noOfMinutes) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, noOfMinutes);
		Timestamp currentTime = new Timestamp(calendar.getTimeInMillis());
		return currentTime;
	}

	/*public static String[] getEntityNameWithMaxCountCondition(List elements, int maxCount){
		String data[]=new String[2];
		data[0] = new String("");  //Element name
		data[1] = new String("");  //Max count

		String elementWithMaxCount=null;

		//HashBag extends DefaultMapBag implements Bag, provided by Apache Collections.
		//HashBag is a kind of Map. This is used to map bag elements to a number; the number represents the number
		//of occurrences of that element in the bag.
		HashBag bag=new HashBag();
		bag.addAll(elements);
		int tempCount=0;

		for(Object elem:bag.uniqueSet()){
			//System.out.println("Element: "+elem+":"+bag.getCount(elem));
			tempCount=bag.getCount(elem);
			if(tempCount>=maxCount){
				elementWithMaxCount=elem.toString();
				break;
			}
		}
		data[0] = elementWithMaxCount;
		data[1] = tempCount+"";
		return data;
	}*/
	public static int getRatingValueUsingPercent(int ratingValue, int ratingPercent){
		int value=0;
		try {
			double temp = (ratingValue*(double)ratingPercent/100);
			value = (int)temp;
		}
		catch(Exception e){
			logger.warning("Error : "+AppUtility.getStackTrace(e));
		}
		return value;
	}
	//=======================Added by Deepak.Modi End=========================
	/*
	public static String getDateDifferenceInDay(String fromDate){
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
		long dateDiff = 0;
		try {
			Date date1 = myFormat.parse(fromDate);
			Date date2 = new Date();
			long diff = date2.getTime() - date1.getTime();
			dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateDiff = dateDiff+1;
		return dateDiff+"";

	}
	*/
	public static long getDateDifferenceInDay(String fromDate){
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
		long dateDiff = 0;
		try {
			Date date1 = myFormat.parse(fromDate);
			Date date2 = new Date();
			long diff = date2.getTime() - date1.getTime();
			dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		dateDiff = dateDiff+1;
		return dateDiff;

	}

	public static long getDateDifferenceInMin(String fromDate){
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMddhhmm");
		long dateDiff = 0;
		try {
			Date date1 = myFormat.parse(fromDate);
			Date date2 = new Date();
			long diff = date2.getTime() - date1.getTime();
			dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			logger.warning("Error: "+AppUtility.getStackTrace(e));
		}
		dateDiff = dateDiff+1;
		return dateDiff;

	}

	public static long getDateDifferenceInHour(String fromDate){
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMddhh");
		long dateDiff = 0;
		try {
			Date date1 = myFormat.parse(fromDate);
			Date date2 = new Date();
			long diff = date2.getTime() - date1.getTime();
			dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			logger.warning("Error: "+AppUtility.getStackTrace(e));
		}
		dateDiff = dateDiff+1;
		return dateDiff;

	}

	public static long getDateDifferenceInMonth(String fromDate){
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyyMMdd");
		long dateDiff = 0;
		try {
			Date date1 = myFormat.parse(fromDate);
			Date date2 = new Date();
			long diff = date2.getTime() - date1.getTime();
			dateDiff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

		} catch (ParseException e) {
			logger.warning("Error: "+AppUtility.getStackTrace(e));
		}
		dateDiff = dateDiff+1;
		return dateDiff;

	}

	public static int getDateDifferenceNextCalDateInSeconds(int numberOfDays, boolean tillNextMidNight){
		LocalDateTime currentTime = LocalDateTime.now(); // now() : since Joda Time 2.0
		LocalDateTime temp = currentTime.plusDays(numberOfDays);
		LocalDateTime endDate = null;
		if(tillNextMidNight){
			endDate = LocalDateTime.of(
					LocalDate.of(currentTime.getYear(), currentTime.getMonth(), currentTime.getDayOfMonth()),
					LocalTime.of(temp.getHour(), temp.getMinute(), temp.getSecond(), temp.getNano()));
		}else{
			endDate = temp;
		}
		Duration sec = Duration.between(currentTime, endDate);
		return (int) sec.toMillis();
	}

	public static Set getDateString(int numberofDays,  String minOrDay, boolean includeCurrentDay){
		Date time = new Date();
		Format sdfYYYYMM =  new SimpleDateFormat("yyyyMMdd");
		if("DAY".equalsIgnoreCase(minOrDay)){
			sdfYYYYMM =  new SimpleDateFormat("yyyyMMdd");
		}else if("MIN".equalsIgnoreCase(minOrDay)){
			sdfYYYYMM =  new SimpleDateFormat("yyyyMMddHHmm");
		}
		Set set = new HashSet();
		Calendar calendar;
		calendar = Calendar.getInstance();
		if(includeCurrentDay){
			set.add(sdfYYYYMM.format(time));
		}
		for (int i = 0; i < numberofDays; i++) {
			if("DAY".equalsIgnoreCase(minOrDay)){
				calendar.add(Calendar.DATE, -1);
			}
			if("MIN".equalsIgnoreCase(minOrDay)){
				calendar.add(Calendar.MINUTE, -1);
			}
			time = new Date(calendar.getTimeInMillis());
			calendar.setTime(time);
			set.add(sdfYYYYMM.format(time));
		}
		return set;
	}



	public static List getDateString(int numberOfDays){
		Format yyyyMMdd =  new SimpleDateFormat("yyyyMMdd");
		List list = new ArrayList();
		Calendar calendar;
		Date date;
		calendar = Calendar.getInstance();

		date = new Date(calendar.getTimeInMillis());
		list.add(yyyyMMdd.format(date));
		for (int i = 0; i < numberOfDays; i++) {
			calendar.add(Calendar.DATE, -1);
			date = new Date(calendar.getTimeInMillis());
			calendar.setTime(date);
			list.add(yyyyMMdd.format(date));
		}
		return list;
	}
	
	public static Map mergeTwoMapsWithList(Map a, Map b) {
		Map result = new ConcurrentHashMap<String, ArrayList>();

		Set tempA = new HashSet(a.keySet());
		Set tempB = new HashSet(b.keySet());

		// System.out.println("a.keySet():"+a.keySet());
		Iterator<String> it = a.keySet().iterator();
		// System.out.println("a:"+a+" it :"+it);
		while (it.hasNext()) {
			String key = (String) it.next();
			// System.out.println("key :"+key);
			ArrayList listA = (ArrayList) a.get(key);
			ArrayList listB = (ArrayList) b.get(key);
			ArrayList resultList = new ArrayList();

			if (listA != null) {
				resultList.addAll(listA);
			}

			if (listB != null) {
				resultList.addAll(listB);
			}
			result.put(key, resultList);
			tempA.remove(key);
			tempB.remove(key);
		}

		it = tempB.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			List listB = (List) b.get(key);
			result.put(key, listB);
		}
		return result;
	}

	public static Date toUtilDate(Timestamp reqArrivalTimeStamp) {
		//////TAG : LATER
		return null;
	}
}
