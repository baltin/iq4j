package com.iq4j.jdbc.api;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.ResultSetHandler;

public interface SQLRunner {

	public DataSource getDataSource();
	//public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException;
	//public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh) throws SQLException;
	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException;
	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException;
	public int update(String sql, Object... params) throws SQLException;
	public int[] batch(String sql, Object[][] params) throws SQLException;
	

}
