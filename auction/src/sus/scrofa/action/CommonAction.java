package sus.scrofa.action;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import sus.scrofa.action.product.ProductAdminAction;
import sus.scrofa.action.user.UserAction;
import sus.scrofa.entity.Notice;
import sus.scrofa.entity.User;
import sus.scrofa.util.Validator;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CommonAction extends ActionSupport {

	/**
	 * 判断用户是否登录，包括管理员和普通会员
	 * 
	 * @return SUCCESS表示已登录，ERROR表示未登录
	 */
	public String checkLogin() {
		if (session.isEmpty()) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 判断管理员是否登录
	 * 
	 * @return SUCCESS表示已登录，ERROR表示未登录
	 */
	public String checkAdminLogin() {
		if (session.get(SESSION_USER_ROLE) == null
				|| (Integer) session.get(SESSION_USER_ROLE) != User.ROLE_ADMIN) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 将文件保存到path路径下，path为相对于项目名的上下文路径，如/upload/image/user，最后不带文件名
	 * 
	 * @param path
	 *            目标文件夹的相对路径
	 * @return 带文件名的路径，多个文件用逗号隔开，如/upload/image/user/123.jpg
	 */
	public String saveTo(String path) {
		// 得到path的物理路径
		String realpath = ServletActionContext.getServletContext().getRealPath(
				path);
		// System.out.println(realpath);

		String url = "";
		String curFileName = "";
		if (image != null && image.length > 0) {
			for (int i = 0; i < image.length; ++i) {

				curFileName = new Date().getTime()
						+ imageFileName[i].substring(imageFileName[i]
								.lastIndexOf('.'));
				File savefile = new File(new File(realpath), curFileName);
				// 判断savefile的上级目录，是否存在
				if (!savefile.getParentFile().exists()) {
					savefile.getParentFile().mkdirs();
				}
				// 保存文件
				try {
					FileUtils.copyFile(image[i], savefile);
					url += (i == 0 ? "" : ",") + path + "/" + curFileName;
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return url;
	}

	/**
	 * 删除文件，若参数为空，表示文件不存在，即已经删除，可以返回true，若是默认用户头像，不能删除，也返回true
	 * 
	 * @param path
	 *            图片路径，多张图片由逗号隔开
	 * @return
	 */
	public boolean deleteFile(String path) {
		if (Validator.isNull(path)) {
			return true;
		}

		String[] paths = path.split(",");

		for (String p : paths) {
			if (Validator.isNull(p)
					|| p.equals(ProductAdminAction.DEFAULT_PRODUCT_IMAGE)
					|| p.equals(UserAction.DEFAULT_USER_LOGO)) {
				continue;
			}
			String realpath = ServletActionContext.getServletContext()
					.getRealPath(p);
			// System.out.println(realpath);
			File file = new File(realpath);
			if (file.exists() && file.isFile()) {
				file.delete();
			}
		}
		return true;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getNow() {
		return now;
	}

	public void setNow(String now) {
		this.now = now;
	}

	public static final SimpleDateFormat DATE_FORMAT_1 = new SimpleDateFormat(
			"yyyy-MM-dd");
	public static final SimpleDateFormat DATE_TIME_FORMAT_1 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static final String SESSION_USER_ID = "_user_id";
	public static final String SESSION_USER_NAME = "_user_name";
	public static final String SESSION_USER_ROLE = "_user_role";

	public static final String PARAM_ERROR = "paramError";
	public static final String PRICE_ERROR = "priceError";
	public static final String TIME_LTD = "timeLTD";
	public static final String TIME_PRE = "timePRE";
	public static final String LOGIN = "login";

	public static final List<Integer> YEAR_LIST = new LinkedList<Integer>();
	public static final List<Integer> YEAR_LIST_2 = new LinkedList<Integer>();
	public static final List<Integer> MONTH_LIST = new LinkedList<Integer>();
	public static final List<Integer> DAY_LIST = new LinkedList<Integer>();
	public static final List<Integer> HOUR_LIST = new LinkedList<Integer>();
	public static final List<Integer> MINUTE_LIST = new LinkedList<Integer>();
	public static final List<Integer> SECOND_LIST = new LinkedList<Integer>();
	static {
		// 初始化年，月，日列表
		GregorianCalendar gc = new GregorianCalendar();
		for (int i = 1911; i <= gc.get(GregorianCalendar.YEAR); ++i) {
			YEAR_LIST.add(i);
		}
		for (int i = gc.get(GregorianCalendar.YEAR); i <= gc
				.get(GregorianCalendar.YEAR) + 1; ++i) {
			YEAR_LIST_2.add(i);
		}
		for (int i = 1; i <= 12; ++i) {
			MONTH_LIST.add(i);
		}
		// 暂时按润年计算
		for (int i = 1; i <= 31; ++i) {
			DAY_LIST.add(i);
		}
		for (int i = 0; i <= 23; ++i) {
			HOUR_LIST.add(i);
		}
		for (int i = 0; i <= 59; ++i) {
			MINUTE_LIST.add(i);
		}
		for (int i = 0; i <= 59; ++i) {
			SECOND_LIST.add(i);
		}
	}
	
	/**
	 * 用户的角色映射表
	 */
	public static final Map<Integer, String> MAP_ROLE;
	static {
		MAP_ROLE = new HashMap<Integer, String>();
		MAP_ROLE.put(User.ROLE_ADMIN, "管理员");
		MAP_ROLE.put(User.ROLE_MEMBER, "普通会员");
	}
	
	/**
	 * 用户的性别映射表
	 */
	public static final Map<Integer, String> MAP_GENDER;
	static {
		MAP_GENDER = new HashMap<Integer, String>();
		MAP_GENDER.put(User.GENDER_SECRET, "保密");
		MAP_GENDER.put(User.GENDER_MALE, "男");
		MAP_GENDER.put(User.GENDER_FEMAIL, "女");
	}
	
	/**
	 * 新闻的显示状态映射表
	 */
	public static final Map<Integer, String> MAP_NOTICE_STATUS;
	static {
		MAP_NOTICE_STATUS = new HashMap<Integer, String>();
		MAP_NOTICE_STATUS.put(Notice.STATUS_HIDE, "隐藏");
		MAP_NOTICE_STATUS.put(Notice.STATUS_SHOW, "显示");
	}
	
	/**
	 * 新闻的置顶级别映射表
	 */
	public static final Map<Integer, String> MAP_NOTICE_TOP;
	static {
		MAP_NOTICE_TOP = new HashMap<Integer, String>();
		MAP_NOTICE_TOP.put(Notice.TOP_LEVEL_NORMAL, "普通");
	}

	/**
	 * 默认每页几条
	 */
	public static final int DEFAULT_COUNT = 10;

	/**
	 * 第几页
	 */
	protected int page = 1;
	/**
	 * 每页几条
	 */
	protected int count;
	/**
	 * 查找结果
	 */
	protected Map<String, Object> data;

	/**
	 * 文件上传的一系列属性
	 */
	protected File[] image;
	protected String[] imageFileName;
	protected String[] imageContentType;

	/**
	 * Session键值对
	 */
	protected Map<String, Object> session = ActionContext.getContext()
			.getSession();

	private String now = DATE_TIME_FORMAT_1.format(new Date());

	private static final long serialVersionUID = -7515177497870303256L;
}
