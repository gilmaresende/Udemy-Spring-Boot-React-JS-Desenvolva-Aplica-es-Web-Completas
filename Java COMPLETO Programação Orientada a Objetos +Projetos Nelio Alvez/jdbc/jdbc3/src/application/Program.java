package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		Connection conn = null;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		PreparedStatement st = null;

		try {
			conn = DB.getConnection();
			/*
			 * st = conn.prepareStatement("insert into seller " +
			 * "(name, email, birthdate, basesalary, departmentid) " + "values (?,?,?,?,?)",
			 * Statement.RETURN_GENERATED_KEYS);
			 * 
			 * st.setString(1, "Gil2"); st.setString(2, "fab2@gmail.com"); st.setDate(3, new
			 * java.sql.Date(sdf.parse("22/04/1955").getTime())); st.setDouble(4, 3600d);
			 * st.setInt(5, 1);
			 */
			st = conn.prepareStatement("insert into department (Name) values ('D1'),('D2')",
					Statement.RETURN_GENERATED_KEYS);

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Done! Id = " + id);
				}
			} else {
				System.out.println("No rows affected");
			}

		} catch (SQLException  e) {
			e.printStackTrace();
		} finally {
			DB.closeStatement(st);

			DB.closeConnection();
		}
	}
}
