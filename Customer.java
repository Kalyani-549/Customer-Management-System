

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.Text;

/**
 * Servlet implementation class Customer
 */
public class Customer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Customer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
	    
	    String str =request.getParameter("action");
		switch(str) {
		case "BACK":
			RequestDispatcher dis = request.getRequestDispatcher("main.html");
			dis.forward(request, response);
		case "ADD TO CART":
		String[] s = request.getParameterValues("act");
		float bill = 0;

			if(s!= null) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					
					Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
			
					/*Statement stmt =conn.createStatement();
					String q = "create table PD(NO int, name varchar(10),price float, itemscount int,totalprice float)";
					
					stmt.executeUpdate(q);
					pw.println("created");*/
					String name = request.getParameter("name");
					String num = request.getParameter("num");
					int No =Integer.parseInt(request.getParameter("no"));
					pw.println("<html lang='en'>");
			        pw.println("<head>");
			        
			       
			       
			        pw.println("</head>");
			        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
				
					pw.println("<h1 style= 'color:blue'><u>Check Your Product Details</u></h1>");
					
					for(String s1 : s) {
				    
				         Float price = Float.parseFloat(request.getParameter(s1+"price"));
				         Integer items = Integer.parseInt(request.getParameter(s1+"Quan"));
					     PreparedStatement pstmt = conn.prepareStatement("insert into PD values(?,?,?,?,?)");
					     pstmt.setInt(1,No);
					     pstmt.setString(2,s1);
					     pstmt.setFloat(3,price);
					     pstmt.setInt(4, items);
					     float total = items*price;
				         pstmt.setFloat(5,total);
						 pstmt.executeUpdate();
						 bill = bill+total;
					}
					
					pw.println("<table border='5' cellSpacing = '2' cellPadding='20'><tr><th>Customer-No </th><th>item name</th><th>price</th><th>no.of items</th><th>total price</th></tr>");
					String q ="select * from PD where NO=?";
				    PreparedStatement p =conn.prepareStatement(q);
				    p.setInt(1, No);
					ResultSet rs =p.executeQuery();
					
					while(rs.next()) {
						pw.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getFloat(3)+"</td><td>"+rs.getInt(4)+"</td><td>"+rs.getFloat(5)+"</td>");
						pw.println("<td><form action='PROUpdate'><input type='hidden' name='id' value="+rs.getInt(1)+"><input type='hidden' name='name' value="+rs.getString(2)+"><input type='submit' value='UPDATE'></form></td>");
						pw.println("<td><form action ='PDelete'><input type='hidden' name='id' value="+rs.getInt(1)+"><input type ='hidden' name='name' value="+rs.getString(2)+"><input type ='submit' value='DELETE' style='color:red'></form></td></tr>");
						
						
					}
						
						 
						 
						 
						pw.println("</table></form></fieldset></center></body></html>");
					 
						 
				    
					
					
	                /*Statement stmt =conn.createStatement();
	                String q = "create table CD(NO int unique,name varchar(10),phn varchar(10),bill float)";
	                stmt.executeUpdate(q);
	                pw.println("CD created");*/
					PreparedStatement pstmt1 = conn.prepareStatement("insert into CD  values(?,?,?,?)");
					pstmt1.setInt(1,No);
					pstmt1.setString(2,name);
					pstmt1.setString(3, num);
					pstmt1.setFloat(4,bill);
					pstmt1.executeUpdate();
					/*Statement stmt = conn.createStatement();
					String q = "create table CDPDdetails(NO int unique,name varchar(10),phn varchar(10),PCD text,bill float)";
					stmt.executeUpdate(q);
					pw.println("created");*/
					pw.println("<h2> your bill is &nbsp;&nbsp;&nbsp; :&nbsp;"+bill+"</h2>"); 
				    pw.println("<a href = 'Cpay.html'><input type='submit' value='proceed to buy' style='color:blue'></a>");
				    
					
					
				  
					
				    }
				
				   
				
			
				  catch(Exception e) {
					  pw.println(e);
				}
			
		    
			          
			}
			else {
				pw.println("no items selected");
			}
		    
		
			
			
		
		}
		
		}

	
			
	}

