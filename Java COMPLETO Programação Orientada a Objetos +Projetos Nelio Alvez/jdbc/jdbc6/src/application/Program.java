package application;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import db.BdException;
import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;

		Statement st = null;

		try {
			conn = DB.getConnection();

			conn.setAutoCommit(false);

			st = conn.createStatement();

			int rows1 = st.executeUpdate("update seller set basesalary = 2500 where departmentid = 1");

			int x = 1;
			if (x < 2) {
				throw new SQLException("Fake error");
			}
			int rows2 = st.executeUpdate("update seller set basesalary = 4090 where departmentid = 2");

			conn.commit();

			System.out.println("rows1  " + rows1);

			System.out.println("rows2  " + rows2);

		} catch (SQLException e) {
			try {
				conn.rollback();
				throw new BdException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new BdException("Error trying to rollback! Caused by: " + e1.getMessage());
			}
		} finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}

	}
}
