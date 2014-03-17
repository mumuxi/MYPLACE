package sus.scrofa.util;

/**
 * 安全的类型转换器
 * 
 * @author Jarvis
 * 
 */
public final class Converter {

	/**
	 * 将字符串转换成整型，发生异常时捕获，返回0
	 * 
	 * @param input
	 *            输入字符串
	 * @return 对应字符串的整型表示，默认返回0
	 */
	public static int optInt(String input) {
		int ret = 0;
		try {
			ret = Integer.parseInt(input);
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 将字符串转换成整型，发生异常时捕获，返回默认值
	 * 
	 * @param input
	 *            input 输入字符串
	 * @param def
	 *            发生异常时需要返回的默认值
	 * @return 对应字符串的整型表示，默认返回def
	 */
	public static int optInt(String input, int def) {
		int ret = def;
		try {
			ret = Integer.parseInt(input);
		} catch (Exception e) {
		}
		return ret;
	}

	/**
	 * 处理中文字符串的函数
	 * 
	 * @param str
	 *            输入串
	 * @return 转换后的中文字符串
	 */
	public static String codeToString(String str) {
		String s = str;
		try {
			byte tempB[] = s.getBytes("ISO-8859-1");
			s = new String(tempB, "utf-8");
		} catch (Exception e) {
		}
		return s;
	}
}
