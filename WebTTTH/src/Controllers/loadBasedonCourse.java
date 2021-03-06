package Controllers;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.course;
import Bean.Class;
import DAO.loadClassDAO;
import DAO.loadCourseDAO;
import DBConnection.DBConnection;

@WebServlet("/loadBasedonCourse")
public class loadBasedonCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loadBasedonCourse() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Khởi tạo đối tượng Session
		HttpSession session = request.getSession(false);
		// Lấy ra username đăng nhập vào
		String usname = (String) session.getAttribute("username");
		
		if(usname == null) {
			response.sendRedirect("login");
		}
		else {
			Connection conn = DBConnection.CreateConnection();
			int courseID = Integer.parseInt(request.getParameter("courseID"));
			
			List<Class> listClass = loadClassDAO.LoadClassBasedonCourse(courseID, conn);
			List<course> listCourse = loadCourseDAO.LoadListCourses(conn);
			
			request.setAttribute("listClass", listClass);
			request.setAttribute("listCourse", listCourse);
			request.setAttribute("course", courseID);
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/admin_class.jsp");
			rd.forward(request, response);
		}
	}

}
