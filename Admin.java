

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Admin
 */
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		int flag = 0;
		int id =Integer.parseInt(request.getParameter("id"));
		String pswd =request.getParameter("pswd");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
			Statement stmt =conn.createStatement();
			String q = "select * from Admin";
			ResultSet res =stmt.executeQuery(q);
			while(res.next()) {
				if(res.getInt(1)== id) {
					flag = 1;
					break;
				}
			}
		}
		catch(Exception e) {
			pw.println(e);
		}
		if(flag==1) {
			pw.println("<html lang='en'>");
	        pw.println("<head>");
	        
	       
	       
	        pw.println("</head>");
	        pw.println("<body style=\"background-image: url('Images/b.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		
			pw.println("<form action = 'Details'><center><table border='1' cellspacing='100' cellpadding='10'>");
			
			pw.println("<tr><td><a href='Details'><img src='Images/customer.jpg'></a></td>");
			
			
			pw.println("<td><a href='CDelete.html'><img src='Images/customer.jpg'></a></td></tr>");
			
			pw.println("<tr><td>Customer Details</td><td>Delete Customer Details</td></tr>");
		
			//pw.println("Customer Details,Update Customer detail,DeleteCustomerDetails");
			
			
			pw.println("</table></center></form></body></html>");
				
		}
		else {
			pw.println("invalid");
			}
			
		}
		
	}


