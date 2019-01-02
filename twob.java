

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class twob
 */
@WebServlet("/twob")
public class twob extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public twob() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		Connection con;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/dbtwob", "root", "");
			
			
		
		
		int n=Integer.parseInt(request.getParameter("n"));
		int c=0;
		switch(n)
		{
		case 1:
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from police ststion");
			while(rs.next())
			{
				String in=request.getParameter("ap");
				if(rs.getString("Phone").equals(in))
				{
					out.println("information found");
					c=1;
					break;
				}
			}
			break;
		case 2:
			Statement st1=con.createStatement();
			ResultSet rs1=st1.executeQuery("select * from police station");
			while(rs1.next())
			{
				String in=request.getParameter("ap");
				if(rs1.getString("Address").equals(in))
				{
					out.println("information found");
					c=1;
					break;
				}
			}
			break;
			default:
				out.println("Information not found");
		}
		if(c==1)
		{
			out.println("Information not found");
		}
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
