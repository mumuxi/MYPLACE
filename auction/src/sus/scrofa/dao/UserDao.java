package sus.scrofa.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import sus.scrofa.entity.User;

@Repository
public class UserDao implements CommonDao<User> {

	/**
	 * 将obj插入数据库对应的表中
	 * 
	 * @param obj
	 *            用户对象
	 * @return 插入成功则返回User对象，否则返回null
	 */
	@Override
	public User add(User obj) {
		String sql = "INSERT INTO user "
				+ "(name, nickname, password, email, role, gender, birthday, telephone, qq, logo) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		boolean ret = this.executeUpdate(obj, sql);
		if (ret == false) {
			return null;
		}
		return this.findLastAdd();
	}

	@Override
	public void delete(Object id) {
		this.deleteByProperty("id", id);
	}

	@Override
	public void deleteByProperty(String name, Object value) {
		String sql = "DELETE FROM user WHERE " + name + " = '" + value + "'";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新用户，根据用户的id查找需要更新的记录，因此id属性不能为空
	 * 
	 * @param obj
	 *            用户对象
	 * @return 更新成功返回true，否则返回false
	 */
	@Override
	public User update(User obj) {
		String sql = "UPDATE user SET "
				+ "name = ?, nickname = ?, password = ?, email = ?, role = ?, gender = ?, birthday = ?, telephone = ?, qq = ?, logo = ? "
				+ "WHERE id = " + obj.getId();
		this.executeUpdate(obj, sql);
		return this.findOneByProperty("id", obj.getId());
	}

	/**
	 * 根据某一具有唯一性约束的字段值返回该记录
	 * 
	 * @param name
	 *            字段名
	 * @param value
	 *            字段值
	 * @return 如果存在该记录则返回User对象，否则返回null
	 */
	@Override
	public User findOneByProperty(String name, Object value) {
		String sql = "SELECT * FROM user WHERE " + name + " = '" + value + "'";
		List<User> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public User findLastAdd() {
		String sql = "SELECT * FROM user WHERE id = (SELECT MAX(id) FROM user)";
		List<User> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 根据多个字段值返回满足条件的用户列表
	 * 
	 * @param names
	 *            字段名数组
	 * @param values
	 *            字段值数组
	 * @return 返回满足条件的用户列表，不存在的话返回null
	 */
	@Override
	public List<User> findByProperties(String[] names, Object[] values) {
		String sql = "SELECT * FROM user WHERE ";
		for (int i = 0; i < names.length; ++i) {
			if (i == 0) {
				sql += names[i] + " = '" + values[i] + "' ";
			} else {
				sql += "AND " + names[i] + " = '" + values[i] + "' ";
			}
		}
		return jdbcTemplate.query(sql, new AllOverResultSetExtractor());
	}

	/**
	 * 分页查询用户列表
	 */
	@Override
	public Map<String, Object> findByPage(final int page, final int count) {
		String sql = "SELECT * FROM user ORDER BY role ASC";
		return this.findByPage(page, count, sql);
	}

	@Override
	public List<Object[]> findByPropertiesSpec(String[] names, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据SQL语句将查询结果分页
	 * 
	 * @param page
	 * @param count
	 * @param sql
	 * @return
	 */
	private Map<String, Object> findByPage(final int page, final int count,
			final String sql) {
		return jdbcTemplate.query(sql,
				new ResultSetExtractor<Map<String, Object>>() {
					@Override
					public Map<String, Object> extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						Map<String, Object> data = new HashMap<String, Object>();

						rs.last();
						// 计算总记录数
						int totalRecord = rs.getRow();
						data.put(KEY_TOTAL_RECORD, totalRecord);
						// 计算总页数
						int totalPage = totalRecord % count == 0 ? totalRecord
								/ count : totalRecord / count + 1;
						data.put(KEY_TOTAL_PAGE, totalPage);
						// 迭代记录列表
						int i = 0;
						int lastEnd = count * (page - 1);
						if (lastEnd == 0) {
							rs.beforeFirst();
						} else if (lastEnd < 0 || lastEnd >= totalRecord) {
							rs.last();
						} else {
							rs.absolute(lastEnd);
						}
						List<User> list = new LinkedList<User>();
						while (rs.next() && ++i <= count) {
							list.add(fetchAll(rs));
						}
						data.put(KEY_LIST, list.size() == 0 ? null : list);
						return data;
					}
				});
	}

	/**
	 * SQL语句可以是INSERT，和UPDATE，占位的参数是除主键外的所有字段
	 * 
	 * @param obj
	 *            从obj中获得值
	 * @param sql
	 *            带参数占位符的INSERT或UPDATE语句
	 * @return
	 */
	private boolean executeUpdate(User obj, String sql) {
		try {
			jdbcTemplate.execute(new AllOverPreparedStatementCreator(obj, sql),
					new PreparedStatementCallback<Integer>() {
						@Override
						public Integer doInPreparedStatement(
								PreparedStatement ps) throws SQLException,
								DataAccessException {
							return ps.executeUpdate();
						}
					});
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private User fetchAll(ResultSet rs) throws SQLException {
		User obj = new User();
		obj.setId(rs.getInt("id"));
		obj.setName(rs.getString("name"));
		obj.setNickname(rs.getString("nickname"));
		obj.setPassword(rs.getString("password"));
		obj.setEmail(rs.getString("email"));
		obj.setRole(rs.getInt("role"));
		obj.setGender(rs.getInt("gender"));
		obj.setBirthday(new java.util.Date(rs.getDate("birthday").getTime()));
		obj.setTelephone(rs.getString("telephone"));
		obj.setQq(rs.getString("qq"));
		obj.setLogo(rs.getString("logo"));
		return obj;
	}

	/**
	 * 除主键外，其他参数全部设置一遍
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverPreparedStatementCreator implements
			PreparedStatementCreator {
		public AllOverPreparedStatementCreator(User obj, String sql) {
			super();
			this.obj = obj;
			this.sql = sql;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, obj.getName());
			pstmt.setString(2, obj.getNickname());
			pstmt.setString(3, obj.getPassword());
			pstmt.setString(4, obj.getEmail());
			pstmt.setInt(5, obj.getRole());
			pstmt.setInt(6, obj.getGender());
			pstmt.setDate(7, new Date(obj.getBirthday().getTime()));
			pstmt.setString(8, obj.getTelephone());
			pstmt.setString(9, obj.getQq());
			pstmt.setString(10, obj.getLogo());
			return pstmt;
		}

		private User obj;
		private String sql;

	}

	/**
	 * 获取所有字段，构造一个User对象，根据记录数返回User列表
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverResultSetExtractor implements
			ResultSetExtractor<List<User>> {
		@Override
		public List<User> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			List<User> list = new LinkedList<User>();
			while (rs.next()) {
				User obj = fetchAll(rs);
				list.add(obj);
			}
			return list;
		}
	}

	@Resource
	private JdbcTemplate jdbcTemplate;

}
