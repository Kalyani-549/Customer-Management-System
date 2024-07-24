

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PDelete
 */
public class PDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw =response.getWriter();
		int id =Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int flag =0;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
		String q = "Delete from PD where NO = ? AND name =?";
		PreparedStatement stmt=conn.prepareStatement(q);
		stmt.setInt(1,id);
		stmt.setString(2,name);
		int k =stmt.executeUpdate();
		if(k>0) {
			flag =1;
		}
		}catch(Exception e) {
			pw.println(e);
		}
		if(flag==1) {
			pw.println("<html <head></head>");
	        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		
			pw.println("<h1 style='color:red'> DELETED SUCESSFULLY </h1>");
			pw.println("<table border='5' cellSpacing = '2' cellPadding='20'><tr><th>Customer-No </th><th>item name</th><th>price</th><th>no.of items</th><th>total price</th></tr>");
			try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
			String q1 ="select * from PD where NO = ?";
		    PreparedStatement p =con.prepareStatement(q1);
		    p.setInt(1, id);
			ResultSet rs =p.executeQuery();
			float totalprice = 0f;
			while(rs.next()) {
				
				pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getFloat(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getFloat(5)+"</td>");
				pw.println("<td><form action='PROUpdate'><input type='hidden' name='id' value="+rs.getInt(1)+"><input type='hidden' name='name' value="+rs.getString(2)+"><input type='submit' value='UPDATE'></form></td>");
				pw.println("<td><form action ='PDelete'><input type='hidden' name='id' value="+rs.getInt(1)+"><input type ='hidden' name='name' value="+rs.getString(2)+"><input type ='submit' value='DELETE' style='color:red'></form></td></tr>");
				
				totalprice = totalprice+rs.getFloat(5);
			}
			
		     pw.println("</table></form></fieldset></center></body></html>");
		     pw.println("<h2> your bill is &nbsp;&nbsp;&nbsp; :&nbsp;"+ totalprice+"</h2>"); 
		     pw.println("<a href = 'Cpay.html'><input type='submit' value='proceed to pay'></a>");
		     //pw.println("<h3> if you want to update the details the click here</h3>");
			 //pw.println("<a href = 'PUpdate.html'><input type='submit' value='UPDATE' style='color:green'></a>");
			// pw.println("<h3> if you want to delete the product details</h3>");
			// pw.println("<a href= 'PDelete.html'><input type = 'submit' value='DELETE' style='color:red'></a>");
			 
			     
			
		 }catch(Exception e) {
				pw.println(e);
			}
		}
		else {
			pw.println("<html <head></head>");
	        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		
			
	    	pw.println("<h1 style='color:red'> your product details  Not Deleted</h1><br>");
	    	
	    	pw.println("<a href='main.html'> <h2>Home Page! </h2></a>");
	    	pw.println("<a href='Customer'> <h2>Product Details</h2></a>");
	    	
	    }
		
		}

}

		
	


