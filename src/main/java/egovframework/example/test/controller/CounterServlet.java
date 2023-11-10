package egovframework.example.test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
@WebServlet(urlPatterns = { "/sessionTest.do" }, asyncSupported = true)
public class ServletTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ServletTest() {
        super();
        
        
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		System.out.println("========11111111====================");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		System.out.println("========22222222====================");
	}

}

*/
///////////////////////////////////////////////////


@WebServlet("/counter")		// /counter로 접근
public class CounterServlet extends HttpServlet {
	private int count = 0;		//접근하는 count 세기
	
	protected synchronized void incrementCount() {
		count++;
	}
	
	protected synchronized int getCount() {
		return count;
		
	}
	
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		incrementCount();
		 
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>Counter</title></head></body>");
		out.println("<h1>Counter Value: " + getCount() + "</h1>");
		out.println("</body></html>");
		
	}
}








