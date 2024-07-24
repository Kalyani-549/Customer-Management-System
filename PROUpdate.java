

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
 * Servlet implementation class PROUpdate
 */
public class PROUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PROUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw =response.getWriter();
		
		   int id = Integer.parseInt(request.getParameter("id"));
		   String name = request.getParameter("name");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/db","root","kalyani@123");
			String q = "select * from PD where NO = ? AND name=?";
			PreparedStatement pst =conn.prepareStatement(q);
			pst.setInt(1,id);
			pst.setString(2,name);
			ResultSet re = pst.executeQuery();
			pw.println("<html><head></head>");
			pw.println("<body style=\"background-image: url('Images/b.jpg'); background-size: cover; background-repeat: no-repeat; background-position: center;\">");
			pw.println("</body></html>");
			 pw.println("<html><body style='padding-top:50px;'><center><fieldset style='height:275px; width:350px;'><form action = 'pupdate'>");
			pw.println("<h1> Update the details</h1>");
			while(re.next()) {
				pw.println("<b>Product-NO:</b><input type = 'text' name='id' value="+re.getInt(1)+"><br><br>");
				pw.println("<b>Product-name:</b><input type='text' name='name' value="+re.getString(2)+"><br><br>");
				pw.println("<b>No.of.items:</b><input type='text' name='items' value='value'><br><br>");
				
				
				pw.println("<input type ='submit' value ='save'>");
			}
			pw.println("</form></center></body></html>");
	 }
      catch(Exception e) {
 	     pw.println(e);
  }
}
	}


