package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.annotation.Resource;
import javax.sql.DataSource;

public class TestBean {

	@Resource(name = "jdbc/postgres")
	private DataSource ds;
	
	public void doBusiness() {
		Connection con = null;
		try {
			con = ds.getConnection();
			PreparedStatement st = (PreparedStatement) con.createStatement();
			st.setInt(1, 1);
			//Statement st = (PreparedStatement) con.createStatement();
			ResultSet rs = st.executeQuery("select id from test2 where id = ?");
			int recordCount = 0;
			while (rs.next()) {
				System.out.println(rs.getInt("id"));
				recordCount++;
			}
			System.out.print("recordCount:");
			System.out.println(recordCount);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
