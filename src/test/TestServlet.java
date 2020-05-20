package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name = "jdbc/postgres")
	private DataSource ds;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TestServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection con = null;
		long start = 0;
		long end = 0;       

		try {
			con = ds.getConnection();
			start = System.nanoTime();
			PreparedStatement st = (PreparedStatement) con.createStatement();
			st.setInt(1, 1);
			//Statement st = (PreparedStatement) con.createStatement();
			ResultSet rs = st.executeQuery("select id from test2 where id = ?");
			int recordCount = 0;
			while (rs.next()) {
				out.println(rs.getInt("id"));
				recordCount++;
			}
			out.println("<br>");
			out.print("recordCount:");
			out.println(recordCount);
			out.println("<br>");
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				con.close();
				end = System.nanoTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		out.print("time:");
		out.print((end - start)/1000);
		out.println(" micro sec");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
