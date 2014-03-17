package sus.scrofa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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

import sus.scrofa.entity.Deal;

@Repository
public class DealDao implements CommonDao<Deal> {

	@Override
	public Deal add(Deal obj) {
		String sql = "INSERT INTO deal"
				+ " (user_id, product_id, price, status, create_time, pay_time, deliver_time, receive_time, id)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		if (this.executeUpdate(obj, sql) == false) {
			return null;
		}
		return obj;
	}

	@Override
	public void delete(Object id) {
		this.deleteByProperty("id", id);
	}

	@Override
	public void deleteByProperty(String name, Object value) {
		String sql = "DELETE FROM deal WHERE " + name + " = '" + value
				+ "'";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Deal update(Deal obj) {
		String sql = "UPDATE deal SET"
				+ " user_id = ?, product_id = ?, price = ?, status = ?, create_time = ?, pay_time = ?, deliver_time = ?, receive_time = ?"
				+ " WHERE id = ?";
		this.executeUpdate(obj, sql);
		return obj;
	}

	@Override
	public Deal findOneByProperty(String name, Object value) {
		String sql = "SELECT * FROM deal WHERE " + name + " = '" + value + "'";
		List<Deal> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public Deal findLastAdd() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Deal> findByProperties(String[] names, Object[] values) {
		String sql = "SELECT * FROM deal WHERE ";
		for (int i = 0; i < names.length; ++i) {
			if (i == 0) {
				sql += names[i] + " = '" + values[i] + "' ";
			} else {
				sql += "AND " + names[i] + " = '" + values[i] + "' ";
			}
		}
		return jdbcTemplate.query(sql, new AllOverResultSetExtractor());
	}

	@Override
	public Map<String, Object> findByPage(int page, int count) {
		String sql = "SELECT * FROM deal ORDER BY create_time DESC";
		return this.findByPage(page, count, sql);
	}

	public Map<String, Object> findByPageWithUser(int page, int count,
			int userId) {
		String sql = "SELECT * FROM deal WHERE user_id = " + userId
				+ " ORDER BY create_time DESC";
		return this.findByPage(page, count, sql);
	}

	@Override
	public List<Object[]> findByPropertiesSpec(String[] names, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

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
						List<Deal> list = new LinkedList<Deal>();
						while (rs.next() && ++i <= count) {
							list.add(fetchAll(rs));
						}
						data.put(KEY_LIST, list.size() == 0 ? null : list);
						return data;
					}
				});
	}

	/**
	 * SQL语句可以是INSERT，和UPDATE，占位的参数是所有字段，主键放最后一个
	 * 
	 * @param obj
	 *            从obj中获得值
	 * @param sql
	 *            带参数占位符的INSERT或UPDATE语句
	 * @return 更新成功则返回true，
	 */
	private boolean executeUpdate(Deal obj, String sql) {
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

	/**
	 * 获取所有字段，构成一个Deal对象
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Deal fetchAll(ResultSet rs) throws SQLException {
		Deal obj = new Deal();
		obj.setId(rs.getLong("id"));
		obj.setUserId(rs.getInt("user_id"));
		obj.setProductId(rs.getInt("product_id"));
		obj.setPrice(rs.getDouble("price"));
		obj.setStatus(rs.getInt("status"));
		obj.setCreateTime(new java.util.Date(rs.getTimestamp("create_time")
				.getTime()));
		obj.setPayTime(rs.getTimestamp("pay_time") == null ? null
				: new java.util.Date(rs.getTimestamp("pay_time").getTime()));
		obj.setDeliverTime(rs.getTimestamp("deliver_time") == null ? null
				: new java.util.Date(rs.getTimestamp("deliver_time").getTime()));
		obj.setReceiveTime(rs.getTimestamp("receive_time") == null ? null
				: new java.util.Date(rs.getTimestamp("receive_time").getTime()));
		return obj;
	}

	/**
	 * 所有参数全部设置一遍，包括主键，因为主键是计算生成的
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverPreparedStatementCreator implements
			PreparedStatementCreator {
		public AllOverPreparedStatementCreator(Deal obj, String sql) {
			super();
			this.obj = obj;
			this.sql = sql;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, obj.getUserId());
			pstmt.setInt(2, obj.getProductId());
			pstmt.setDouble(3, obj.getPrice());
			pstmt.setInt(4, obj.getStatus());
			pstmt.setTimestamp(5, new Timestamp(obj.getCreateTime().getTime()));
			pstmt.setTimestamp(6, obj.getPayTime() == null ? null
					: new Timestamp(obj.getPayTime().getTime()));
			pstmt.setTimestamp(7, obj.getDeliverTime() == null ? null
					: new Timestamp(obj.getDeliverTime().getTime()));
			pstmt.setTimestamp(8, obj.getReceiveTime() == null ? null
					: new Timestamp(obj.getReceiveTime().getTime()));
			pstmt.setLong(9, obj.getId());
			return pstmt;
		}

		private Deal obj;
		private String sql;

	}

	/**
	 * 获取所有字段，构造一个Deal对象，根据记录数返回Deal列表
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverResultSetExtractor implements
			ResultSetExtractor<List<Deal>> {
		@Override
		public List<Deal> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			List<Deal> list = new LinkedList<Deal>();
			while (rs.next()) {
				Deal obj = fetchAll(rs);
				list.add(obj);
			}
			return list;
		}
	}

	@Resource
	private JdbcTemplate jdbcTemplate;
}
