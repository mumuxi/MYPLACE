package sus.scrofa.util;

import java.util.regex.Pattern;

public final class Validator {

	public static boolean validateUsername(String username) {
		return false;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param input
	 *            输入字符串
	 * @return 如果字符串为null或者空串，则返回true，否则返回false
	 */
	public static boolean isNull(String input) {
		if (input == null || input.trim().equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 判断字符串是否能表示成整型
	 * 
	 * @param input
	 *            输入字符串
	 * @return 如果字符串能表示成数字，则返回true，否则返回false
	 */
	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断字符串是否是一个合法的密码，要求是6到16位（含）的数字、字母和下划线
	 * 
	 * @param input
	 *            输入字符串
	 * @return 如果字符串是一个合法的密码，则返回true，否则返回false
	 */
	public static boolean isPassword(String input) {
		return PATTERN_PASSWORD.matcher(input).matches();
	}

	/**
	 * 判断字符串是否是一个合法的邮箱地址
	 * 
	 * @param input
	 *            输入字符串
	 * @return 如果字符串是一个合法的邮箱地址，则返回true，否则返回false
	 */
	public static boolean isEmail(String input) {
		return PATTERN_EMAIL.matcher(input).matches();
	}

	public static final Pattern PATTERN_INT = Pattern.compile("\\d+");
	public static final Pattern PATTERN_PASSWORD = Pattern
			.compile("[\\w]{6,16}");
	public static final Pattern PATTERN_EMAIL = Pattern
			.compile("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
}
