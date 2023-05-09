import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Insert")
public class parameterInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("name");
		String fname = request.getParameter("fname");
		String address = request.getParameter("address");
		String country = request.getParameter("country");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/register_db", "root", "password");
			
			PreparedStatement ps = conn.prepareStatement("insert into db_table values(?,?,?,?)");
			ps.setString(1, name);
			ps.setString(2, fname);
			ps.setString(3, address);
			ps.setString(4, country);
			
			int i = ps.executeUpdate();
			
			if(i > 0) {
				out.print("You have successfully registered");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
