package es.aulamentor;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class Consultas
 */
@WebServlet("/Consultas")
public class Consultas extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="java:comp/env/jdbc/biblioteca")
	DataSource datasource;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Consultas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		procesarPeticion(request, response);
	}
	
	private void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String isbn = request.getParameter("isbn");
		String cache = request.getParameter("cache");
		ArrayList<Prestamo> prestamos = null;
		HttpSession sesion = request.getSession(true);
		
		if(cache != null && cache.equals("true")){
			Enumeration<String>isbns = sesion.getAttributeNames();
			while(isbns.hasMoreElements()){
				sesion.removeAttribute(isbns.nextElement());
			}
		}else{
			prestamos = (ArrayList<Prestamo>)sesion.getAttribute(isbn);
		}
		
		if(prestamos==null){
			prestamos = new ArrayList<Prestamo>();
			
			try {
				con = datasource.getConnection();
				pstm = con.prepareStatement("select * from prestamos where isbn =?");
				pstm.setString(1, isbn);
				rs = pstm.executeQuery();
				
				while(rs.next()){
					prestamos.add(new Prestamo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				try
				{
					if(rs != null)
						rs.close();
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
				try
				{
					if(pstm != null)
						pstm.close();
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
				try
				{
					if(con != null)
						con.close();
				}
				catch(SQLException ex)
				{
					ex.printStackTrace();
				}
			}
			
			if(prestamos != null){
				sesion.setAttribute(isbn, prestamos);
			}
		}
		
		Cookie[] cookies = request.getCookies();
		Cookie cookie = null;
		
		for(int i=0;cookies!=null && i<cookies.length;i++){
			if(cookies[i].getName().equals("cont")){
				cookie = cookies[i];
				break;
			}
		}
		
		if(cookie==null){
			cookie = new Cookie("cont","0");
		}
		
		cookie.setMaxAge(2592000);
		cookie.setValue(Integer.toString(Integer.parseInt(cookie.getValue())+1));
		response.addCookie(cookie);
		
		request.setAttribute("prestamos",prestamos);
		request.setAttribute("contador", cookie.getValue());
		request.getRequestDispatcher("/consultas.jsp").forward(request, response);
	}

}
