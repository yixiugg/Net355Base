/**
 * Copyright (c) 2010 Abbcc Corp.
 * No 225,Wen Yi RD, Hang Zhou, Zhe Jiang, China.
 * All rights reserved.
 *
 * "StringUtil.java is the copyrighted,
 * proprietary property of Abbcc Company and its
 * subsidiaries and affiliates which retain all right, title and interest
 * therein."
 * 
 * Revision History
 *
 * Date              Programmer                   Notes
 * ---------    ---------------------  --------------------------------------------
 * 2009-12-3           yixiugg                      initial
 **/

package com.net355.util;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class StringUtil extends StringUtils {
	static org.apache.log4j.Logger log = org.apache.log4j.Logger
			.getLogger(StringUtil.class);
	public static final String CHINESE = "[\u4e00-\u9fa5]+";
	public static final String DOUBLE_BYTE = "[^\\x00-\\xff]+";

	/**
	 * 按格式显示时间
	 * 
	 * @param dFmt
	 *            显示格式
	 * @param date
	 *            时间
	 * @return String
	 */
	public static String getJsString(String str, int length) {
		if (str == null)
			return "";
		String ret = StringUtil.replace(str, "\"", "“");
		ret = StringUtil.replace(ret, "\r", " ");
		ret = StringUtil.replace(ret, "\n", " ");
		ret = StringUtil
				.replace(ret, System.getProperty("line.separator"), " ");
		if (str.length() <= length || length < 0)
			return ret;
		else
			ret = str.substring(0, length) + "...";
		return ret;
	}

	/**
	 * 按格式显示时间
	 * 
	 * @param dFmt
	 *            显示格式
	 * @param date
	 *            时间
	 * @return String
	 */
	public static String getDateString(SimpleDateFormat dFmt, Date date) {
		try {
			return dFmt.format(date);
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 按格式转换时间
	 * 
	 * @param dFmt
	 *            显示格式
	 * @param date
	 *            时间String
	 * @return Date
	 */
	public static Date getStringDate(SimpleDateFormat dFmt, String date) {
		try {
			return dFmt.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将阿拉伯数字转换为小写的汉字
	 * 
	 * @param num
	 *            数字
	 * @return 小写的汉字
	 */
	public static String changeNum(char num) {
		String result = "";
		if (num == '0') {
			result = "○";
		} else if (num == '1') {
			result = "一";
		} else if (num == '2') {
			result = "二";
		} else if (num == '3') {
			result = "三";
		} else if (num == '4') {
			result = "四";
		} else if (num == '5') {
			result = "五";
		} else if (num == '6') {
			result = "六";
		} else if (num == '7') {
			result = "七";
		} else if (num == '8') {
			result = "八";
		} else if (num == '9') {
			result = "九";
		}
		return result;
	}

	/**
	 * 返回的字符串顺序改变，原标记后的部分放在新的标记前输出,原标记前的部分放在新的标记后输出
	 */
	public static int ORDER_Y = 1;

	/**
	 * 返回的字符串顺序不变
	 */
	public static int ORDER_N = 0;

	/**
	 * 将页面中的数字转化为汉字,数字的大小为(0-99),日期格式除外
	 * 
	 * @param num
	 *            数字
	 * @param isDate
	 *            是否是日期形式 true为日期，false则表示不是日期,如果是日期形式,则标准的格式为"yyyy年mm月dd日"
	 * @param type
	 *            类型，type=0,显示xxxx年xx月xx日;type=1,显示xxxx年xx月;type="2",显示xx月xx日
	 * @return
	 */
	public static String changeNumber(String num, boolean isDate, int type) {
		String result = "";
		String month = "";
		String day = "";
		if (num == null) {
			System.out.println("输入的数字为空");
			result = null;
		} else {
			if (isDate) {
				if (num.charAt(5) == '1' && num.charAt(6) != '0') {
					month = "十" + StringUtil.changeNum(num.charAt(6)) + "月";
				} else if (num.charAt(5) == '1' && num.charAt(6) == '0') {
					month = "十月";
				} else {
					month = StringUtil.changeNum(num.charAt(6)) + "月";
				}
				if (num.charAt(8) == '1' && num.charAt(9) != '0') {
					day = "十" + StringUtil.changeNum(num.charAt(9)) + "日";
				} else if (num.charAt(8) == '1' && num.charAt(9) == '0') {
					day = "十日";
				} else if (num.charAt(8) == '2' && num.charAt(9) != '0') {
					day = "二十" + StringUtil.changeNum(num.charAt(9)) + "日";
				} else if (num.charAt(8) == '2' && num.charAt(9) == '0') {
					day = "二十日";
				} else if (num.charAt(8) == '3' && num.charAt(9) != '0') {
					day = "三十" + StringUtil.changeNum(num.charAt(9)) + "日";
				} else if (num.charAt(8) == '3' && num.charAt(9) == '0') {
					day = "三十日";
				} else {
					day = StringUtil.changeNum(num.charAt(9)) + "日";
				}
				if (type == 0) {
					result = StringUtil.changeNum(num.charAt(0))
							+ StringUtil.changeNum(num.charAt(1));
					result += StringUtil.changeNum(num.charAt(2))
							+ StringUtil.changeNum(num.charAt(3));
					result += "年" + month + day;
				}
				if (type == 1) {
					result = StringUtil.changeNum(num.charAt(0))
							+ StringUtil.changeNum(num.charAt(1));
					result += StringUtil.changeNum(num.charAt(2))
							+ StringUtil.changeNum(num.charAt(3));
					result += "年" + month;
				}
				if (type == 2) {
					result = month + day;
				}
			}
			if (!isDate) {
				int length = num.length();
				if (length == 1) {
					result = StringUtil.changeNum(num.charAt(0));
				}
				if (length == 2) {
					if (num.equals("10")) {
						result = "十";
					} else if (num.charAt(1) != '0' && num.charAt(0) == '1') {
						result = "十" + StringUtil.changeNum(num.charAt(1));
					} else if (num.charAt(1) == '0' && num.charAt(0) != '1') {
						result = StringUtil.changeNum(num.charAt(0)) + "十";
					} else if (num.charAt(1) != '0' && num.charAt(0) != '1') {
						result = StringUtil.changeNum(num.charAt(0)) + "十"
								+ StringUtil.changeNum(num.charAt(1));
					}
				}
			}
		}
		return result;
	}

	/**
	 * 对含有标记的字符串进行转换
	 * 
	 * @param str
	 *            要进行变换的字符串
	 * @param mark
	 *            字符串的标记
	 * @param replace
	 *            要替换的字符
	 * @param int 是否要替换顺序
	 * @return String
	 */
	public static String typeString(String str, char mark, char replace,
			int sort) {
		String result = null;
		if (str != null) {
			if (str.indexOf(mark) < 0) {
				result = str;
			} else {
				result = str.replace(mark, replace);
				if (sort == StringUtil.ORDER_Y) {
					int place = str.indexOf(mark);
					result = str.substring(place + 1, str.length()) + replace
							+ str.substring(0, place);
				}
			}
		}
		return result;
	}

	/**
	 * @param str
	 *            原字符串
	 * @param length
	 *            需要的长度,如果length=-1,取默认长度16
	 * @return 处理后字符串
	 */
	public static String getLeftString(String str, int length) {
		if (str == null)
			return "";
		String ret = "";
		if (length < 0)
			length = 16;
		if (str.length() <= length)
			return str;
		else
			return (str.substring(0, length) + "...");
	}

	/**
	 * @param s
	 *            需转成char型的字符串
	 * @param c
	 *            缺省值
	 * @return 返回char型
	 */
	public static char getFirstChar(String s, char c) {
		try {
			return s.charAt(0);
		} catch (Exception e) {
			return c;
		}
	}

	/**
	 * Read a parameter with the specified name, convert it to an int, and
	 * return it. Return the designated default value if the parameter doesn't
	 * exist or if it is an illegal integer format.
	 */

	public static int getIntParameter(HttpServletRequest request,
			String paramName, int defaultValue) {
		String paramString = request.getParameter(paramName);
		int paramValue;
		try {
			paramValue = Integer.parseInt(paramString);
		} catch (NumberFormatException nfe) { // null or bad format
			paramValue = defaultValue;
		}
		return (paramValue);
	}

/**
	 * Given a string, this method replaces all occurrences of '<' with '&lt;',
	 * all occurrences of '>' with '&gt;', and (to handle cases that occur
	 * inside attribute values), all occurrences of double quotes with '&quot;'
	 * and all occurrences of '&' with '&amp;'. Without such filtering, an
	 * arbitrary string could not safely be inserted in a Web page.
	 */

	public static String filter(String input) {
		// add by zord @ 2002/1/26
		if (null == input)
			return "";
		// end of add
		StringBuffer filtered = new StringBuffer(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			if (c == '<') {
				filtered.append("&lt;");
			} else if (c == '>') {
				filtered.append("&gt;");
			} else if (c == '"') {
				filtered.append("&quot;");
			} else if (c == '&') {
				filtered.append("&amp;");
			} else {
				filtered.append(c);
			}
		}
		return (filtered.toString());
	}

	public static String replace(String source, String find, String replacewith) {
		String ret = "";
		String tmp = source;
		int i;
		int len = find.length();
		while (true) {
			i = tmp.indexOf(find);
			if (i < 0) {
				ret = ret.concat(tmp);
				break;
			}
			ret = ret.concat(tmp.substring(0, i));
			ret = ret.concat(replacewith);
			tmp = tmp.substring(i + len);
		}

		return ret;
	}

	public static String readFromFile(String filename) {
		try {
			FileInputStream fstream = new FileInputStream(filename);
			String s = new String("");
			byte[] buf = new byte[256];
			int read = 0;
			while (true) {
				read = fstream.read(buf);
				String r = new String(buf, 0, read);
				s = s.concat(r);
				if (read < 256) {
					break;
				}
			}

			fstream.close();
			return s;
		} catch (Exception e) {
			return null;
		}
	}

	public static final String toString(byte buffer[]) {
		StringBuffer returnBuffer = new StringBuffer();
		for (int pos = 0, len = buffer.length; pos < len; pos++)
			returnBuffer.append(hexToAscii((buffer[pos] >>> 4) & 0x0F)).append(
					hexToAscii(buffer[pos] & 0x0F));
		return returnBuffer.toString();
	}

	public static byte[] fromString(String inHex) {
		int len = inHex.length();
		int pos = 0;
		byte buffer[] = new byte[((len + 1) / 2)];
		if ((len % 2) == 1) {
			buffer[0] = (byte) asciiToHex(inHex.charAt(0));
			pos = 1;
			len--;
		}

		for (int ptr = pos; len > 0; len -= 2)
			buffer[pos++] = (byte) ((asciiToHex(inHex.charAt(ptr++)) << 4) | (asciiToHex(inHex
					.charAt(ptr++))));
		return buffer;
	}

	public static final int asciiToHex(char c) {
		if ((c >= 'a') && (c <= 'f'))
			return (c - 'a' + 10);
		if ((c >= 'A') && (c <= 'F'))
			return (c - 'A' + 10);
		if ((c >= '0') && (c <= '9'))
			return (c - '0');
		throw new Error("ascii to hex failed");
	}

	public static char hexToAscii(int h) {
		if ((h >= 10) && (h <= 15))
			return (char) ('a' + (h - 10));
		if ((h >= 0) && (h <= 9))
			return (char) ('0' + h);
		throw new Error("hex to ascii failed");
	}

	public static String native2ascii(String str) {
		String tmp;
		StringBuffer sb = new StringBuffer(1000);
		char c;
		int i, j;
		sb.setLength(0);
		for (i = 0; i < str.length(); i++) {
			c = str.charAt(i);
			if (c > 255) {
				sb.append("\\u");
				j = (c >>> 8);
				tmp = Integer.toHexString(j);
				if (tmp.length() == 1)
					sb.append("0");
				sb.append(tmp);
				j = (c & 0xFF);
				tmp = Integer.toHexString(j);
				if (tmp.length() == 1)
					sb.append("0");
				sb.append(tmp);
			} else {
				sb.append(c);
			}

		}
		return (new String(sb));
	}

	public static String readFromFile(String filename, String enc) {
		try {
			FileInputStream fstream = new FileInputStream(filename);
			String s = new String("");

			byte[] buf = new byte[256];
			byte[] content = null;
			int read = 0;
			while (true) {
				read = fstream.read(buf);
				int length;
				if (content == null)
					length = 0;
				else
					length = content.length;
				byte[] t = new byte[length + read];
				int i;
				for (i = 0; i < length; i++) {
					t[i] = content[i];
				}
				for (i = length; i < length + read; i++) {
					t[i] = buf[i - length];
				}
				content = t;
				if (read < 256) {
					break;
				}
			}
			s = new String(content, enc);
			fstream.close();
			return s;
		} catch (Exception e) {
			return null;
		}
	}

	public static HashMap readFromPropertyFile(String filename, String enc) {
		String s = readFromFile(filename, enc);
		HashMap map = new HashMap();

		int index;
		while (s.length() > 0) {
			index = s.indexOf("\n");
			// get one line
			String line;
			if (index >= 0)
				line = s.substring(0, index);
			else
				line = s;
			line = line.trim();
			if (line.length() != 0) {
				while (line.charAt(line.length() - 1) == '\\') {
					line = line.substring(0, line.length() - 1);
					line = line.trim();
					s = s.substring(index);
					s = s.trim();
					index = s.indexOf("\n");
					if (index >= 0)
						line = line.concat(s.substring(0, index));
					else
						line = line.concat(s);
					line = line.trim();
				}
				// parse the line
				if ((line.charAt(0) != '#') && (line.charAt(0) != '!')) {
					String key;
					String element;
					int i = line.indexOf("=");
					if (i >= 0) {
						key = line.substring(0, i);
						key = key.trim();
						element = line.substring(i + 1);
						element = element.trim();
						map.put(key, element);
					} else {
						i = line.indexOf(":");
						if (i >= 0) {
							key = line.substring(0, i);
							key = key.trim();
							element = line.substring(i + 1);
							element = element.trim();
							map.put(key, element);
						}
					}
				}
			}
			if (index >= 0)
				s = s.substring(index);
			else
				s = "";
			s = s.trim();

		}
		return map;
	}

	static public ArrayList seperateString(String stValue, char split) {
		ArrayList valueArray = new ArrayList();
		if (stValue == null)
			return valueArray;
		stValue = stValue.trim();
		String stPart = stValue;
		int pos = 0;
		int pmValueNum = 0;
		do {
			pos = stValue.indexOf(split);
			switch (pos) {
			case 0:
				stPart = "";
				break;
			case -1:
				stPart = stValue;
				break;
			default:
				stPart = stValue.substring(0, pos);
				break;
			}
			stValue = stValue.substring(pos + 1);
			valueArray.add(stPart);
		} while (pos != -1);
		return valueArray;
	}

	static public String appendString(String src, String append, char split) {
		return src + split + append;
	}

	static public String fillString(String src, char fill, int width) {
		int srcWidth = src.length();
		String head = "";
		for (int i = 0; i < width - srcWidth; i++) {
			head += fill;
		}
		return head + src;
	}

	static public String fillStuIdString(String src, char fill, int width) {
		int srcWidth = src.length();
		// String head = SPID.getSPID() ;
		String head = "310108";
		for (int i = 0; i < width - srcWidth; i++) {
			head += fill;
		}
		return head + src;
	}

	/**
	 * 从特殊地址中解析邮件地址，比如somebody <somebody@domain>
	 * 
	 * @param s
	 *            需要解析的字串
	 * @return String － 解析后得到的邮件地址
	 */
	public static String parseMailAddress(String s) {
		// Log.debug("--AddressListTools.parsAddress--");
		if (s.indexOf("<") != -1 && s.indexOf(">") != -1) {
			int begin = s.indexOf("<");
			int end = s.indexOf(">");
			String temp = s.substring(begin + 1, end);
			temp = temp.trim();
			return temp;
		}
		return s;
	}

	/**
	 * 判断一个邮件地址是否合法，条件为是否包含"@"
	 * 
	 * @param s
	 *            邮件地址
	 * @return boolean － 合法与否
	 */
	public static boolean checkMailAddress(String s) {
		// Log.debug("--AddressListTools.checkMailAddress--");
		if (s.indexOf("@") != -1)
			return true;
		return false;
	}

	/**
	 * 将可能对网页显示造成破坏的字符进行替换 ('\r'暂时无法提供转换)
	 * 
	 * @param s
	 *            需要处理的字串
	 * @return String － 进行字符替换后的字串
	 */
	public static String CharFilter(String s) {
		if (null == s) {
			// Log.warn("!!Warning at StringUtility.CharFilter : Input
			// Param[String] is null！！");
			return "";
		}
		int i_length = s.length();
		String str_Temp = "";
		for (int i = 0; i < i_length; i++) {
			// Replace '&' with "&amp;"
			if (s.charAt(i) == '&') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "&amp;";
				s += str_Temp;
				i_length += 4;
				i += 4;
			}
			// Replace '<' with "&lt;"
			if (s.charAt(i) == '<') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "&lt;";
				s += str_Temp;
				i_length += 3;
				i += 3;
			}
			// Replace '>' with "&gt;"
			if (s.charAt(i) == '>') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "&gt;";
				s += str_Temp;
				i_length += 3;
				i += 3;
			}
			// Replace space with "&nbsp;"
			if (s.charAt(i) == ' ') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "&nbsp;";
				s += str_Temp;
				i_length += 5;
				i += 5;
			}
			// Replace ''' with "&quot;"
			if (s.charAt(i) == '\"') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "&quot;";
				s += str_Temp;
				i_length += 5;
				i += 5;
			}
			// begin of modify by King Sun 2002-7-11
			// Replace '\n' with "<br>"
			if (s.charAt(i) == '\n') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "<br>";
				s += str_Temp;
				i_length += 3;
				i += 3;
			}
			// Replace ' ' with "&nbsp;"
			if (s.charAt(i) == ' ') {
				str_Temp = s.substring(i + 1);
				s = s.substring(0, i);
				s += "&nbsp;";
				s += str_Temp;
				i_length += 5;
				i += 5;
			}
			// end of modify by King Sun 2002-7-11
		}
		return s;
	}

	/**
	 * @method isBlank
	 * @param str
	 * @return boolean
	 * @function if the string is null or blank space will be treated as blank
	 *           field
	 */
	public static boolean isBlank(String str) {
		return (str == null || str.trim().length() < 1);
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}

	public static String toGBK(String s) {
		try {
			return new String(s.getBytes("ISO-8859-1"), "gb2312");
		} catch (Exception ex) {
			return "";
		}
	}

	/**
	 * @method getDecodeString
	 * @param s
	 * @return String
	 * @throws IOException
	 * @function get BASE64 decode
	 */
	public static String getDecodeString(String s) throws IOException {
		if (s.length() > 11) {
			if (s.substring(0, 11).equals("=?GB2312?B?")) {
				BASE64Decoder decoder = new BASE64Decoder();
				String s1 = new String(decoder.decodeBuffer(s.substring(11,
						s.length() - 2)));
				return s1;
			}
		}
		return s;
	}

	/**
	 * @method getEncodeString
	 * @param s
	 * @return String
	 * @function get BASE64 coding
	 */
	public static String getEncodeString(String s) {
		BASE64Encoder enc = new BASE64Encoder();
		if (s.length() > 11) {
			if (s.substring(0, 11).equals("=?GB2312?B?")) {
				return s;
			} else {
				String s1 = "=?GB2312?B?" + enc.encode(s.getBytes()) + "?=";
				return s1;
			}
		}
		String s1 = "=?GB2312?B?" + enc.encode(s.getBytes()) + "?=";
		return s1;
	}

	public static boolean lessThan(String str1, String str2) {
		if (str1 == null)
			return true;
		if (str2 == null)
			return false;
		return str1.compareTo(str2) < 0;
	}

	public static boolean moreThan(String str1, String str2) {
		if (str1 == null) {
			return false;
		}
		if (str2 == null) {
			return true;
		}
		return str1.compareTo(str2) > 0;
	}

	public static String trim(String str) {
		if (isBlank(str)) {
			return null;
		}
		return str.trim();
	}

	public static final String[] zodiacArr = { "猴", "鸡", "狗", "猪", "鼠", "牛",
			"虎", "兔", "龙", "蛇", "马", "羊" };

	public static final String[] constellationArr = { "水瓶座", "双鱼座", "牡羊座",
			"金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座" };

	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22,
			23, 23, 23, 23, 22, 22 };

	/**
	 * 根据日期获取生肖
	 * 
	 * @return
	 */
	public static String date2Zodica(Calendar time) {
		return zodiacArr[time.get(Calendar.YEAR) % 12];
	}

	/**
	 * 根据日期获取星座
	 * 
	 * @param time
	 * @return
	 */
	public static String date2Constellation(Calendar time) {
		int month = time.get(Calendar.MONTH);
		int day = time.get(Calendar.DAY_OF_MONTH);
		if (day < constellationEdgeDay[month]) {
			month = month - 1;
		}
		if (month >= 0) {
			return constellationArr[month];
		}
		// default to return 魔羯
		return constellationArr[11];
	}

	/**
	 * 解析String
	 * 
	 * @param s
	 * @return
	 */
	public static String parseString(String s) {
		if (isNotBlank(s))
			return s;
		return "";
	}

	/**
	 * 判断是否有屏蔽字
	 * 
	 * @param s
	 * @return
	 */
	public static boolean hasBannedWords(String s) {
		boolean flag = false;
		// String[] bannedWords = GlobalConstant.bannedWords;
		// if(s!=null&&!s.equals("")){
		// for(int i=0;i<bannedWords.length;i++){
		// String bannedWord = bannedWords[i];
		// if(s.indexOf(bannedWord)>-1)
		// return true;
		// }
		// }
		return flag;
	}

	private static String charDictionary = "0123456789abcdefghijklmnopqrstuvwxyz";
	private static final int imgNameLen = 30;

	public static String getRndString(int length) {
		if (length < 1) {
			return null;
		}
		char[] dictionary = charDictionary.toCharArray();

		StringBuffer sb = new StringBuffer();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			sb.append(dictionary[random.nextInt(dictionary.length)]);
		}
		return sb.toString();
	}

	public static String getRndImgname(String fileName) {
		if (StringUtil.isBlank(fileName)) {
			return null;
		}
		String[] fn = fileName.split("\\.");
		String picFormat = fn[fn.length - 1].toLowerCase();
		String randName = getRndString(imgNameLen);
		if (StringUtil.isBlank(randName)) {
			return null;
		}
		return randName + "." + picFormat;
	}

	public static Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}

	public static String getRandomNumber() {
		double i = Math.random();
		return String.valueOf((int)(i * 10));
	}

	public static String getRandomChar() {
		int rand = (int) Math.round(Math.random() * 2);
		long itmp = 0;
		char ctmp = '\u0000';
		switch (rand) {
		case 1:
			itmp = Math.round(Math.random() * 25 + 65);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		case 2:
			itmp = Math.round(Math.random() * 25 + 97);
			ctmp = (char) itmp;
			return String.valueOf(ctmp);
		default:
			itmp = Math.round(Math.random() * 9);
			return String.valueOf(itmp);
		}
	}

	// 匹配中文字符
	public static boolean hasChinese(String str) {
		return hasExpression(str, CHINESE);
	}

	// 匹配双字节字符
	public static boolean hasDoubleByte(String str) {
		return hasExpression(str, DOUBLE_BYTE);
	}

	public static boolean hasExpression(String str, String expression) {
		Pattern p = Pattern.compile(expression);
		Matcher m = p.matcher(str);
		return m.find();
	}

	public static String encodeURI(String uri) {
		Pattern p = Pattern.compile(DOUBLE_BYTE);
		Matcher m = p.matcher(uri);
		while (m.find()) {
			String s = m.group();
			uri = uri.replaceFirst(s, encode(s));
		}
		return uri;
	}

	public static String encode(String s) {
		return encode(s, "utf-8");
	}

	public static String encode(String s, String enc) {
		try {
			return URLEncoder.encode(s, enc);
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		return s;
	}

	public static String decode(String str) {
		return decode(str, "ISO-8859-1", "utf-8");
	}

	public static String decode(String str, String type, String type2) {
		try {
			return new String(str.getBytes(type), type2);
		} catch (UnsupportedEncodingException e) {
			log.info(e);
		}
		return str;
	}

	public static String pathToSuf(String path, Serializable suf) {
		return path.replaceAll("\\.[^.]+$", "_" + suf + ".jpg");
	}

	public static String pathToSml(String path) {
		return pathToSuf(path, 5);
	}

	public static String pathToMid(String path) {
		return pathToSuf(path, 4);
	}

	public static String pathToBig(String path) {
		return pathToSuf(path, 3);
	}

	public static String[] splitBySemicolon(String s) {
		return s.split(";");
	}

	/**
	 * 把路径转换成为通用类型，linux，windows通用
	 * 
	 * @param s
	 * @return
	 */
	public static String pathReplace(String s) {
		return s.replaceAll("//", System.getProperty(File.separator));
	}

	/**
	 * 把关键字（运算符）替换掉，主要用于搜索时
	 */
	public static String filtrate(String key) {
		if (key.equals(""))
			return "|8**8|";

		String[] notKey = { "+", "-", "*", "(", ")", "[", "]", "{", "}", "~",
				"\\", "^", "!", "?" };
		for (int i = 0; i < notKey.length; i++) {
			if (key.equals(notKey[i])) {
				return "|8**8|";
			}

		}

		String[] replaceKey = { " ", " ", " ", " ", " ", " ", " ", " ", " ",
				" ", " ", " ", " ", " " };
		key = replaceEach(key, notKey, replaceKey);
		return key;
	}

	/**
	 * 截取字符串中的一段，主要用于搜索 content传过来的内容，key关键字
	 */
	public static String getSearchContent(String content, String key) {
		int wz = content.lastIndexOf(key);
		String result = "";
		if (wz == -1) {
			if (content.length() > 160)
				result = content.substring(0, 160);
			else
				result = content;
			return result + "....";
		}

		int length = content.length();
		content = content.substring(0, wz) + "<font color=\"red\">"
				+ content.substring(wz, wz + key.length()) + "</font>"
				+ content.substring(wz + key.length(), length);

		if (wz < 20) {
			if (length - wz > 20)
				result = content.substring(0, wz + 20);

			else
				result = content.substring(0, content.length());

			return result;
		}

		else {
			if (length - wz > 20)
				result = content.substring(wz - 40, wz + 40);

			else

				result = content.substring(0, length);

			return "...." + result + "....";
		}

		// return "……"+result+"……";

	}

	/**
	 * 提取html的文本
	 * 
	 * @param html
	 * @return
	 */
	public static String parseHTMLtoText(String html) {
		html = html.replaceAll("\\&[a-zA-Z]{1,10};", "")
				.replaceAll("<[^>]*>", "").replaceAll("[(/>)<]", "");
		return html;
	}

	// 判断是否为中文
	// GENERAL_PUNCTUATION 判断中文的“号

	// CJK_SYMBOLS_AND_PUNCTUATION 判断中文的。号

	// HALFWIDTH_AND_FULLWIDTH_FORMS 判断中文的，号

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public static boolean isChinese(String strName) {
		char[] ch = strName.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (isChinese(c) == true) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	// 判断是否存在空格
	public static boolean hasKongge(String s) {
		int i = s.indexOf(" ");
		if (i == -1)
			return false;
		return true;
	}

	public static String subTextEdesc(int i, String text) {
		String s = StringUtil.parseHTMLtoText(text);
		if (s.length() > i) {
			String newStr = s.replaceAll("(^[ |　]*|[ |　]*$)", "");
			return newStr.substring(0, i - 1);
		} else {

			return s.replaceAll("(^[ |　]*|[ |　]*$)", "");
		}
	}

	public static void main(String[] args) {
		/*
		 * String kg="dddd"; System.out.println(StringUtil.hasKongge(kg));
		 * String text =
		 * "可以指定第二个参数以指出在字符串中开始进行搜索的起始索引位置，如下所示： ... 您可以使用 replace() 方法在字符串中搜索指定模式并使用指定的替换字符串替换匹配 ...2008年12月22日 ... 用String 的indexOf(\"子串\");方法 ,这个方法还有个重载indexOf(子串 startIndex);. String s wenda.tianya.cn "
		 * ; System.out.println(text.length());
		 * System.out.println(StringUtil.getSearchContent(text, "tianya.cn"));
		 */
		System.out.println(new StringUtil()
				.getEncodeString("Enterp_0000000000000000000000061"));
		try {
			System.out
					.println(new StringUtil()
							.getDecodeString("=?GB2312?B?RW50ZXJwXzAwMDAwMDAwMDAwMDAwMDAwMDAwMDAwNjE=?="));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
