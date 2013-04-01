package com.iq4j.jdbc.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbutils.AbstractQueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import com.iq4j.jdbc.api.SQLRunner;


public class SPRunner extends AbstractQueryRunner implements SQLRunner {
	
	public SPRunner() {
		super();
	}

	public SPRunner(DataSource ds) {
		super(ds);
	}

	public SPRunner(boolean pmdKnownBroken) {
		super(pmdKnownBroken);
	}

	public SPRunner(DataSource ds, boolean pmdKnownBroken) {
		super(ds, pmdKnownBroken);
	}

	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		return this.query(conn, false, sql, rsh, params);
	}

	public <T> T query(Connection conn, String sql, ResultSetHandler<T> rsh) throws SQLException {
		return this.query(conn, false, sql, rsh, (Object[]) null);
	}

	public <T> T query(String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		Connection conn = this.prepareConnection();
		return this.query(conn, true, sql, rsh, params);
	}

	public <T> T query(String sql, ResultSetHandler<T> rsh) throws SQLException {
		Connection conn = this.prepareConnection();
		return this.query(conn, true, sql, rsh, (Object[]) null);
	}

	/**
	 * Calls stored procedure after checking the parameters to ensure nothing is
	 * null.
	 * 
	 * @param conn
	 *            The connection to use for the query call.
	 * @param closeConn
	 *            True if the connection should be closed, else false.
	 * @param sql
	 *            The stored procedure call to execute.
	 * @param params
	 *            An array of query replacement parameters. Each row in this
	 *            array is one set of batch replacement values.
	 * @return The results of the query.
	 * @throws SQLException
	 *             If there are database or parameter errors.
	 */
	private <T> T query(Connection conn, boolean closeConn, String sql, ResultSetHandler<T> rsh, Object... params) throws SQLException {
		if (conn == null) {
			throw new SQLException("Null connection");
		}
		if (sql == null) {
			if (closeConn) {
				close(conn);
			}
			throw new SQLException("Null SQL statement");
		}
		if (rsh == null) {
			if (closeConn) {
				close(conn);
			}
			throw new SQLException("Null ResultSetHandler");
		}
		if (sql.toUpperCase().indexOf("CALL") == -1) {
			if (closeConn) {
				close(conn);
			}
			throw new SQLException("Not a callable statement");
		}
		CallableStatement stmt = null;
		ResultSet rs = null;
		T result = null;

		try {
			stmt = this.prepareCall(conn, sql);
			this.fillStatement(stmt, params);
			rs = this.wrap(stmt.executeQuery());
			result = rsh.handle(rs);
		} catch (SQLException e) {
			this.rethrow(e, sql, params);
		} finally {
			try {
				close(rs);
			} finally {
				close(stmt);
				if (closeConn) {
					close(conn);
				}
			}
		}
		return result;
	}

	protected CallableStatement prepareCall(Connection conn, String sql) throws SQLException {
		return conn.prepareCall(sql);
	}

	@Override
	public int update(String sql, Object... params) throws SQLException {
		return 0;
	}

	@Override
	public int[] batch(String sql, Object[][] params) throws SQLException {
		return null;
	}

}
