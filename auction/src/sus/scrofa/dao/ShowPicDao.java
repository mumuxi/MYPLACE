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

import sus.scrofa.entity.ShowPic;

@Repository
public class ShowPicDao implements CommonDao<ShowPic> {

	@Override
	public ShowPic add(ShowPic obj) {
		String sql = "INSERT INTO show_pic "
				+ "(deal_id, content, images, publish_time, status) "
				+ "VALUES (?, ?, ?, ?, ?)";
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
		String sql = "DELETE FROM show_pic WHERE " + name + " = '" + value
				+ "'";
		try {
			jdbcTemplate.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ShowPic update(ShowPic obj) {
		String sql = "UPDATE show_pic SET"
				+ " deal_id = ?, content = ?, images = ?, publish_time = ?, status = ?"
				+ " WHERE id = " + obj.getId();
		this.executeUpdate(obj, sql);
		return this.findOneByProperty("id", obj.getId());
	}

	@Override
	public ShowPic findOneByProperty(String name, Object value) {
		String sql = "SELECT * FROM show_pic WHERE " + name + " = '" + value
				+ "'";
		List<ShowPic> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public ShowPic findLastAdd() {
		String sql = "SELECT * FROM show_pic WHERE id = (SELECT MAX(id) FROM show_pic)";
		List<ShowPic> list = jdbcTemplate.query(sql,
				new AllOverResultSetExtractor());
		return list.isEmpty() ? null : list.get(0);
	}

	@Override
	public List<ShowPic> findByProperties(String[] names, Object[] values) {
		String sql = "SELECT * FROM show_pic WHERE ";
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
		String sql = "SELECT * FROM show_pic WHERE status != 0 ORDER BY publish_time DESC";
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
						List<ShowPic> list = new LinkedList<ShowPic>();
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
	private boolean executeUpdate(ShowPic obj, String sql) {
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

	private ShowPic fetchAll(ResultSet rs) throws SQLException {
		ShowPic obj = new ShowPic();
		obj.setId(rs.getInt("id"));
		obj.setDealId(rs.getLong("deal_id"));
		obj.setContent(rs.getString("content"));
		obj.setImages(rs.getString("images"));
		obj.setPublishTime(new java.util.Date(rs.getTimestamp("publish_time")
				.getTime()));
		obj.setStatus(rs.getInt("status"));
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
		public AllOverPreparedStatementCreator(ShowPic obj, String sql) {
			super();
			this.obj = obj;
			this.sql = sql;
		}

		@Override
		public PreparedStatement createPreparedStatement(Connection con)
				throws SQLException {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setLong(1, obj.getDealId());
			pstmt.setString(2, obj.getContent());
			pstmt.setString(3, obj.getImages());
			pstmt.setTimestamp(4, new Timestamp(obj.getPublishTime().getTime()));
			pstmt.setInt(5, obj.getStatus());
			return pstmt;
		}

		private ShowPic obj;
		private String sql;

	}

	/**
	 * 获取所有字段，构造一个ShowPic对象，根据记录数返回ShowPic列表
	 * 
	 * @author jarvis
	 * 
	 */
	private class AllOverResultSetExtractor implements
			ResultSetExtractor<List<ShowPic>> {
		@Override
		public List<ShowPic> extractData(ResultSet rs) throws SQLException,
				DataAccessException {
			List<ShowPic> list = new LinkedList<ShowPic>();
			while (rs.next()) {
				ShowPic obj = fetchAll(rs);
				list.add(obj);
			}
			return list;
		}
	}

	@Resource
	private JdbcTemplate jdbcTemplate;
}
