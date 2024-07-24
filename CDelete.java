

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CDelete
 */
public class CDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CDelete() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
PrintWriter pw =response.getWriter();
		
        int id =Integer.parseInt(request.getParameter("id"));
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
		String q = "Delete from CDPDdetails where NO = ?";
		PreparedStatement stmt=conn.prepareStatement(q);
		stmt.setInt(1,id);
	    int k = stmt.executeUpdate();
	    String q1 = "Delete from CD where NO = ?";
		PreparedStatement stmt1=conn.prepareStatement(q1);
		stmt1.setInt(1,id);
	    int k1 = stmt1.executeUpdate();
	    String q2 = "Delete from PD where NO = ?";
		PreparedStatement stmt2=conn.prepareStatement(q2);
		stmt2.setInt(1,id);
	    int k2 = stmt2.executeUpdate();
        if( k>0 && k1>0 && k2>0) {
        	pw.println("<html><head></head>");
	        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		   pw.println("<h1 style='color:red'> Deleted successfully</h1><br>");
	       pw.println("<a href = 'Details'><h1>Customer Details</h1></a>");
	       pw.println("</body></html>");
	    }
	    else {
	    	pw.println("<html><head></head>");
	        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		   pw.println("<h1 style='color:red'> Not Deleted successfully</h1><br>");
	       pw.println("<a href = 'Details'><h1>Customer Details </h1></a>");
	       pw.println("</body></html>");
	    }
	    
		}
	    catch(Exception e) {
	    	pw.println(e);
	    }
	}

	
	}

