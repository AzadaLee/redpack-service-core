/**
 * @(#)IdcardValidator.java 1.0.0 2016年4月25日 下午8:25:00
 * <p/>
 * Copyright © 2016 善林金融.  All rights reserved.
 */

package com.slfinance.redpack.core.extend.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 身份证号码验证
 *
 * @author samson
 * @version $Revision:1.0.0, $Date: 2016年4月25日 下午8:25:00 $
 */
public class IdcardValidator {
	/**
	 * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
	 * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
	 * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
	 * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
	 * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
	 * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
	 */
	private static byte CITY_CODE[] = { 11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, 41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82, 91 };

	// 每位加权因子
	private static byte POWER[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	private static String CARD_15_DATE_FORMAT = "yyMMdd";
	private static String CARD_18_DATE_FORMAT = "yyyyMMdd";

	private static byte NUMERIC_MIN = 48;
	private static byte NUMERIC_MAN = 57;

	private static byte X = 88;
	private static byte x = 120;

	// 第18位校检码{ "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" }
	private static byte VERIFY_CODE[] = { 49, 48, X, 57, 56, 55, 54, 53, 52, 51, 50 };

	/**
	 * 验证所有的身份证的合法性
	 *
	 * @param idCard
	 * @return
	 */
	public static boolean isValidatedIdCard(String idCard) {
		if (idCard == null) {
			return false;
		}
		if (idCard.length() == 15) {
			return isValidate15IdCard(idCard);
		}

		return isValidate18IdCard(idCard);
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
	 * @param idCard
	 * @return
	 */
	public static boolean isValidate18IdCard(String idCard) {
		byte[] card;
		// 非18位ASCII为假
		if (idCard == null || (card = idCard.getBytes()).length != 18) {
			return false;
		}

		if (!isDigits(card, 0, 17)) {
			return false;
		}

		convertASCII2BinaryValue(card, 0, 17);

		// 是否为合法的省份
		if (!isLegalProvince(card[0], card[1])) {
			return false;
		}

		// 是否为合法的日期
		if (!isLegalDate(card, 6, 14, CARD_18_DATE_FORMAT)) {
			return false;
		}

		int sum17 = getPowerSum(card, 0, 17);
		// 将和值与11取模得到余数进行校验码判断
		byte checkCode = getCheckCodeBySum(sum17);
		// 将身份证的第18位与算出来的校码进行匹配，不相等就为假
		card[17] = card[17] == x ? X : card[17];
		return card[17] == checkCode;
	}

	/**
	 * 验证15位身份证的合法性,该方法验证不准确，最好是将15转为18位后再判断，该类中已提供。
	 *
	 * @param idCard
	 * @return
	 */
	public static boolean isValidate15IdCard(String idCard) {
		byte[] card;
		// 非15位为假
		if (idCard == null || (card = idCard.getBytes()).length != 15) {
			return false;
		}

		// 是否为数字
		if (!isDigits(card, 0, card.length)) {
			return false;
		}

		convertASCII2BinaryValue(card, 0, idCard.length());

		// 是否为合法的省份
		if (!isLegalProvince(card[0], card[1])) {
			return false;
		}

		// 是否为合法的日期
		return isLegalDate(card, 6, 12, CARD_15_DATE_FORMAT);
	}

	/**
	 * 判断是否为合法的日期
	 *
	 * @param value
	 * @param start
	 * @param end
	 * @param dateFormat
	 * @return
	 */
	protected static boolean isLegalDate(byte[] value, int start, int end, String dateFormat) {
		return getLegalDate(value, start, end, dateFormat) != null;
	}

	/**
	 * 获取合法的日期
	 *
	 * @param value
	 * @param start
	 * @param end
	 * @param dateFormat
	 * @return
	 */
	protected static Date getLegalDate(byte[] value, int start, int end, String dateFormat) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
		simpleDateFormat.setLenient(true);
		Date date = null;
		StringBuilder sb = new StringBuilder();
		for (int i = start; i < end; i++) {
			sb.append(value[i]);
		}
		try {
			date = simpleDateFormat.parse(sb.toString());
			// 该身份证生出日期在当前日期之后时为假
			if (new Date().before(date)) {
				return null;
			}
		} catch (ParseException e) {
			return null;
		}
		return date;
	}

	/**
	 * 将15位的身份证转成18位身份证
	 *
	 * @param idCard
	 * @return
	 */
	protected static String convertIdCar15To18(String idCard) {
		byte[] idCard18 = new byte[18];
		byte[] idCard15;
		// 非15位身份证
		if ((idCard15 = idCard.getBytes()).length != 15) {
			return null;
		}
		convertASCII2BinaryValue(idCard15, 0, idCard15.length);
		Date birthday = getLegalDate(idCard15, 6, 12, CARD_15_DATE_FORMAT);
		if (birthday == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(birthday);
		String year = String.valueOf(calendar.get(Calendar.YEAR));

		// 设置前17位数据
		byte[] yearBytes = year.getBytes();
		convertASCII2BinaryValue(yearBytes, 0, yearBytes.length);
		System.arraycopy(idCard15, 0, idCard18, 0, 6);
		System.arraycopy(yearBytes, 0, idCard18, 6, 4);
		System.arraycopy(idCard15, 8, idCard18, 10, 7);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 17; i++) {
			sb.append(idCard18[i]);
		}

		// 设置第18位校验码
		int sum17 = getPowerSum(idCard18, 0, 17);
		// 获取和值与11取模得到余数进行校验码
		byte $18 = getCheckCodeBySum(sum17);
		sb.append(($18 == x || $18 == X) ? "X" : ($18 - NUMERIC_MIN));
		return sb.toString();
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 *
	 * @param bit
	 * @return
	 */
	protected static int getPowerSum(byte[] bit, int start, int end) {
		int sum = 0;
		if (POWER.length != (end - start)) {
			return sum;
		}

		for (int i = start; i < end; i++) {
			sum = sum + bit[i] * POWER[i];
		}
		return sum;
	}

	/**
	 * 将和值与11取模得到余数进行校验码判断
	 *
	 * @param
	 * @param sum17
	 * @return 校验位
	 */
	protected static byte getCheckCodeBySum(int sum17) {
		return VERIFY_CODE[sum17 % 11];
	}

	/**
	 * 是否为数字
	 *
	 * @param value
	 * @param start
	 * @param end
	 * @return
	 */
	protected static boolean isDigits(byte[] value, int start, int end) {
		for (int i = start; i < end; i++) {
			if (value[i] < NUMERIC_MIN && value[i] > NUMERIC_MAN) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否为合法的省份
	 *
	 * @param value1
	 * @param value2
	 * @return
	 */
	protected static boolean isLegalProvince(byte value1, byte value2) {
		byte provinceId = (byte) (value1 * 10 + value2);
		// 判断是否为合法的省份
		for (byte id : CITY_CODE) {
			if (id == provinceId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 把二进制的数字值转换为二进制的实际值(即byte-48)
	 *
	 * @param value
	 * @param start
	 * @param end
	 */
	protected static void convertASCII2BinaryValue(byte[] value, int start, int end) {
		for (int i = start; i < end; i++) {
			value[i] = (byte) (value[i] - NUMERIC_MIN);
		}
	}
}
