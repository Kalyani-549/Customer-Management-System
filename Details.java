
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Details
 */
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Details() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter pw =response.getWriter();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
			Statement st =conn.createStatement();  
		   	String s2 = "select CD.NO,CD.name,CD.phn,group_concat(PD.name,' - ',PD.price,' * ',PD.itemscount,' = ',PD.totalprice separator '<br><br>') AS productdetails,CD.bill from CD join PD on CD.NO = PD.NO Group By CD.NO,CD.name,CD.phn,CD.bill";
		   	ResultSet res =st.executeQuery(s2);
		   	String q2 = "insert into CDPDdetails values(?,?,?,?,?)";
		   	PreparedStatement pst =conn.prepareStatement(q2);
		   	while(res.next()) {
		   		int CNO = res.getInt(1);
		   		String sr = "select * from CDPDdetails where NO=?";
		   		PreparedStatement ps =conn.prepareStatement(sr);
		   	    ps.setInt(1, CNO);
		   	    ResultSet r = ps.executeQuery();
		   	    if(!r.next()) {
		   	    	pst.setInt(1,res.getInt(1));
		   	    	pst.setString(2,res.getString(2));
		   	    	pst.setString(3,res.getString(3));
		   	    	pst.setString(4,res.getString(4));
		   	    	pst.setFloat(5,res.getFloat(5));
		   	    	pst.executeUpdate();
		   	    }
		   	}
		   	    	
			Statement stmt =conn.createStatement();
			String q = "select * from CDPDdetails";
			Statement st1 =conn.createStatement();
			ResultSet res1 = st.executeQuery(q);
			pw.println("<h1 style ='color:blue'><u> CUSTOMER DETAILS </u></h1>");
			pw.println("<html lang='en'>");
	        pw.println("<head>");
	        
	       
	       
	        pw.println("</head>");
	        pw.println("<body style=\"background-image: url('Images/p.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
		    
			pw.println("<table border='5' cellpadding='50' cellspacing='2'> <tr><th>NO</th><th>customerName</th><th>phnNumber</th><th>Product Details<br>(product-name,price,No.of items,totalAmount)</th><th>Total bill</th><tr>");
		    while(res1.next()) {
				pw.println("<tr><td>"+res1.getInt(1)+"</td><td>"+res1.getString(2)+"</td><td>"+res1.getString(3)+"</td><td>"+(res1.getString(4))+"</td><td>"+res1.getFloat(5)+"</td></tr>");
				
				
			}
			pw.println("</table>");
			
			pw.println("<a href = 'main.html'><h1>HOME PAGE!</h1></a>");
			pw.println("</body></html>");
			
			
			
			
		}
		catch(Exception e) {
			pw.println(e);
		}
}

}

