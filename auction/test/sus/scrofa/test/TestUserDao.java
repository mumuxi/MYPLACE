package sus.scrofa.test;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import sus.scrofa.dao.UserDao;
import sus.scrofa.entity.User;

public class TestUserDao extends TestBase {

	@Test
	public void testAdd() {
		userdao = cxt.getBean(UserDao.class);
		User user = new User();
		user.setName("test2");
		user.setPassword("123456");
		user.setEmail("a@b.c");
		user.setRole(User.ROLE_MEMBER);
		if (userdao.add(user) != null) {
			System.out.println("user added.");
		}
	}

	@Test
	public void testUpdate() {
		userdao = cxt.getBean(UserDao.class);
		User user = new User();
		user.setId(1);
		user.setName("admin");
		user.setPassword("1234567");
		user.setEmail("a@b.c");
		user.setRole(User.ROLE_ADMIN);
		if (userdao.update(user) != null) {
			System.out.println("user updated.");
		}
	}

	@Test
	public void testFindOneByProperty() {
		userdao = cxt.getBean(UserDao.class);
		User user = userdao.findOneByProperty("id", 4);
		if (user != null) {
			System.out.println(user.getName());
		} else {
			System.out.println("no record.");
		}
	}

	@Test
	public void testFindByProperties() {
		userdao = cxt.getBean(UserDao.class);
		List<User> list = userdao.findByProperties(new String[] { "role" },
				new Object[] { User.ROLE_MEMBER });
		if (list != null) {
			for (User u : list) {
				System.out.println(u.getName());
			}
		} else {
			System.out.println("no record.");
		}
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testFindByPage() {
		userdao = cxt.getBean(UserDao.class);
		Map<String, Object> ret = userdao.findByPage(1, 10);
		if (ret.get(UserDao.KEY_LIST) != null) {
			System.out.println(ret.get(UserDao.KEY_TOTAL_PAGE));
			System.out.println(ret.get(UserDao.KEY_TOTAL_RECORD));
			for (User u : (List<User>) ret.get(UserDao.KEY_LIST)) {
				System.out.println(u.getName());
			}
		} else {
			System.out.println("no record.");
		}
	}

	private UserDao userdao;
}
