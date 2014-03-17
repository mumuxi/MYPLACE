package sus.scrofa.action.user;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sus.scrofa.action.CommonAction;
import sus.scrofa.entity.User;
import sus.scrofa.service.UserService;

@Controller
@Scope("prototype")
public class LoginAction extends CommonAction {

	/**
	 * 用户登录，需要传入的参数：name，password
	 * @return
	 */
	public String login() {
		User user = userService.findOneByProperty("name", name);
		if (user != null && user.getPassword().equals(password)) {
			// 登录成功，将用户id，用户名，用户角色放入session中
			session.put(SESSION_USER_ID, user.getId());
			session.put(SESSION_USER_NAME, user.getName());
			session.put(SESSION_USER_ROLE, user.getRole());
			return SUCCESS;
		}
		this.addActionError("用户名或密码错误。");
		return ERROR;
	}
	
	public String logout() {
		session.clear();
		return SUCCESS;
	}
	
	public String toIndex() {
		if (session.get(SESSION_USER_ID) != null) {
			return SUCCESS;
		}
		return ERROR;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String name;
	private String password;

	@Resource
	private UserService userService;

	private static final long serialVersionUID = 1404635080058019678L;
}
