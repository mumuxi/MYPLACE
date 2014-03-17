package sus.scrofa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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

import sus.scrofa.entity.BidHistory;

@Repository
public class BidHistoryDao implements CommonDao<BidHistory> {

	@Override
	public BidHistory add(BidHistory obj) {
		String sql = "INSERT INTO bid_history "
				+ "(user_id, product_id, price, bid_time) "
				+ "VALUES (?, ?, ?, ?)";
		if (this.executeUpdate(obj, sql) == false) {
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
		String sql = "DELETE FROM bid_history WHERE " + name + " = '" + value
				+ "'";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteByUserProduct(int userId, int productId) {
		String sql = "DELETE FROM bid_history WHERE user_id = " + userId
				+ " AND product_id = " + productId;
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public BidHistory update(BidHistory obj) {
		String sql = "UPDATE bid_history SET "
				+ "user_id = ?, product_id = ?, price = ?, bid_time = ? "
				+ "WHERE id = " + obj.getId();
		this.executeUpdate(obj, sql);
		return this.findOneByProperty("id", obj.getId());
	}

	@Override
	public BidHistory findOneByProperty(String name, Object value) {
		String sql = "SELECT * FROM bid_history WHERE " + name + " = '" + value
				+ "'";
		List<BidHistory> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public BidHistory findLastAdd() {
		String sql = "SELECT * FROM bid_history WHERE id = (SELECT MAX(id) FROM bid_history)";
		List<BidHistory> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<BidHistory> findByProperties(String[] names, Object[] values) {
		String sql = "SELECT * FROM bid_history WHERE ";
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
		String sql = "SELECT * FROM bid_history ORDER BY bid_time DESC";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 查找某一用户对所有商品的所有出价，按商品排序
	 * 
	 * @param page
	 * @param count
	 * @param userId
	 * @return
	 */
	public Map<String, Object> findByPageWithUser(int page, int count,
			int userId) {
		String sql = "SELECT * FROM bid_history WHERE user_id = " + userId
				+ " ORDER BY product_id DESC, bid_time DESC";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 查找所有用户中对某一商品的所有出价，按出价时间排序
	 * 
	 * @param page
	 * @param count
	 * @param productId
	 * @return
	 */
	public Map<String, Object> findByPageWithProduct(int page, int count,
			int productId) {
		String sql = "SELECT * FROM bid_history WHERE product_id = "
				+ productId + " ORDER BY bid_time DESC";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 查找某一用户对某一商品的所有出价，按出价时间排序
	 * 
	 * @param page
	 * @param count
	 * @param userId
	 * @param productId
	 * @return
	 */
	public Map<String, Object> findByPageWithDeal(int page, int count,
			int userId, int productId) {
		String sql = "SELECT * FROM bid_history WHERE user_id = " + userId
				+ " AND product_id = " + productId + " ORDER BY bid_time DESC";
		return this.findByPage(page, count, sql);
	}

	public Map<String, Object> findConfirmByPage(int page, int count, int userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String now = sdf.format(new Date());
		String sql = "SELECT id, user_id, product_id, MAX(price) AS price, bid_time"
				+ " FROM bid_history WHERE user_id = "
				+ userId
				+ " AND product_id IN (SELECT id FROM product WHERE finish_time < '"
				+ now + "')" + " GROUP BY product_id";
		return this.findByPage(page, count, sql);
	}

	/**
	 * 查找某商品的最高出价记录
	 * 
	 * @param productId
	 * @return 若无出价，则返回null，否则返回一条出价记录
	 */
	public BidHistory findMaxPrice(int productId) {
		String sql = "SELECT * FROM bid_history" + " WHERE product_id = "
				+ productId
				+ " AND price = (SELECT MAX(price) FROM bid_history"
				+ " WHERE product_id = " + productId + " GROUP BY product_id)";
		List<BidHistory> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	/**
	 * 查找对某商品的出价次数
	 * 
	 * @param productId
	 * @return
	 */
	public int findBidCount(int productId) {
		String sql = "SELECT COUNT(*) AS count FROM bid_history"
				+ " WHERE product_id = " + productId;
		return jdbcTemplate.queryForInt(sql);
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
						List<BidHistory> list = new LinkedList<BidHistory>();
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
	private boolean executeUpdate(BidHistory obj, String sql) {
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

	private BidHistory fetchAll(ResultSet rs) throws SQLException {
		BidHistory obj = new BidHistory();
		obj.setId(rs.getInt("id"));
		obj.setUserId(rs.getInt("user_id"));
		obj.setProductId(rs.getInt("product_id"));
		obj.setPrice(rs.getDouble("price"));
		obj.setBidTime(new java.util.Date(rs.getTimestamp("bid_time").getTime()));
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
		public AllOverPreparedStatementCreator(BidHistory obj, String sql) {
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
			pstmt.setTimestamp(4, new Timestamp(obj.getBidTime().getTime()));
			return pstmt;
		}

		private BidHistory obj;
		private String sql;

	}

	/**
	 * 获取所有字段，构造一个BidHistory对象，根据记录数返回BidHistory列表
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverResultSetExtractor implements
			ResultSetExtractor<List<BidHistory>> {
		@Override
		public List<BidHistory> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			List<BidHistory> list = new LinkedList<BidHistory>();
			while (rs.next()) {
				BidHistory obj = fetchAll(rs);
				list.add(obj);
			}
			return list;
		}
	}

	@Resource
	private JdbcTemplate jdbcTemplate;
}
