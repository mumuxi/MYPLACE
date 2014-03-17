package sus.scrofa.action.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.User;
import sus.scrofa.service.UserService;
import sus.scrofa.util.SimpleDateTime;
import sus.scrofa.util.Validator;

@Controller
@Scope("prototype")
public class UserAction extends CommonAction {

	public String toList() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		page = page <= 0 ? 1 : page;
		count = count <= 0 ? DEFAULT_COUNT : count;
		data = userService.findByPage(page, count);
		return SUCCESS;
	}

	/**
	 * 管理员查看某用户的具体信息，传入的参数：id
	 * @return
	 */
	public String toDetail() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		user = userService.findOneByProperty("id", id);
		return SUCCESS;
	}
	
	/**
	 * 转到个人中心，判断用户是否登录，传出的参数：user
	 * @return
	 */
	public String toInfoCenter() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		user = userService.findOneByProperty("id", session.get(SESSION_USER_ID));
		if (user == null) {
			return ERROR;
		}
		return SUCCESS;
	}

	/**
	 * 管理员添加用户，需要传入的参数：user，birthday，image
	 * 
	 * @return
	 */
	public String add() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}

		// 确认密码是否匹配
		if (Validator.isNull(user.getPassword())
				|| !user.getPassword().equals(confirmPassword)) {
			return PARAM_ERROR;
		}

		// 保存图片
		String url = super.saveTo("/upload/image/user");
		if (Validator.isNull(url)) {
			// 若不上传图片，则设置为默认图片
			user.setLogo(DEFAULT_USER_LOGO);
		} else {
			user.setLogo(url);
		}

		// 设置生日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(sdf.parse(birthday.getYear() + "-"
					+ birthday.getMonth() + "-" + birthday.getDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 保存
		user = userService.add(user);
		if (user == null) {
			return PARAM_ERROR;
		}
		return SUCCESS;
	}
	
	/**
	 * 管理员删除用户，传入的参数：id
	 * @return
	 */
	public String delete() {
		if (this.checkAdminLogin().equals(ERROR)) {
			return ERROR;
		}
		int cur = (Integer) session.get(SESSION_USER_ID);
		if (cur != id) {
			userService.delete(id);
		}
		return SUCCESS;
	}

	/**
	 * 未登录用户注册成为会员，需要传入的参数：user
	 * 
	 * @return 注册成功则返回SUCCESS，参数错误返回PARAM_ERROR
	 */
	public String register() {
		if (Validator.isNull(user.getName())
				|| Validator.isNull(user.getPassword())
				|| Validator.isNull(user.getEmail())) {
			return INPUT;
		}
		// 确认密码是否匹配
		if (!user.getPassword().equals(confirmPassword)) {
			return INPUT;
		}
		// 邮箱是否合法
		if (!Validator.isEmail(user.getEmail())) {
			return INPUT;
		}
		// 设置生日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(sdf.parse("1911-01-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// 设置角色
		user.setRole(User.ROLE_MEMBER);
		// 设置性别
		user.setGender(User.GENDER_SECRET);
		// 设置默认头像
		user.setLogo(DEFAULT_USER_LOGO);
		// 保存
		user = userService.add(user);
		if (user == null) {
			return INPUT;
		}
		return SUCCESS;
	}

	/**
	 * 更新用户，传入的参数：user
	 * @return
	 */
	public String update() {
		if (this.checkLogin().equals(ERROR)) {
			return ERROR;
		}
		User tmp = userService.findOneByProperty("id", id);
		if (tmp == null) {
			return PARAM_ERROR;
		}

		// 设置user.id
		user.setId(id);

		// 对于图片，要删除原来的图片，再保存新的图片
		// 1. 原来无图片，现在有图片
		// 2. 原来无图片，现在无图片
		// 3. 原来有图片，现在有图片
		// 4. 原来有图片，现在无图片
		String url = super.saveTo("/upload/image/user");
		if (Validator.isNull(url)) {
			if (Validator.isNull(tmp.getLogo())) {
				// 情况2
				user.setLogo(DEFAULT_USER_LOGO);
			} else {
				// 情况4
				user.setLogo(tmp.getLogo());
			}
		} else {
			String oldLogo = tmp.getLogo();
			super.deleteFile(oldLogo);
			// 情况1，3
			user.setLogo(url);
		}

		// 设置生日
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			user.setBirthday(sdf.parse(birthday.getYear() + "-"
					+ birthday.getMonth() + "-" + birthday.getDate()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// 如果密码为空，则保持原来的密码
		if (Validator.isNull(user.getPassword())) {
			user.setPassword(tmp.getPassword());
		}

		// 如果修改了用户名，判断是否有同名
		if (!tmp.getName().equals(user.getName())) {
			if (userService.findOneByProperty("name", user.getName()) != null) {
				return PARAM_ERROR;
			}
		}

		user = userService.update(user);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public SimpleDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(SimpleDateTime birthday) {
		this.birthday = birthday;
	}

	public static final String DEFAULT_USER_LOGO = "/images/product/default_logo.jpg";

	private User user;
	private int id;
	private String confirmPassword;
	private SimpleDateTime birthday;

	@Resource
	private UserService userService;
	private static final long serialVersionUID = -6238278147168878946L;

}
