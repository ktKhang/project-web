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

import Bean.Class;
import Bean.course;
import DAO.editClassDAO;
import DAO.loadClassDAO;
import DAO.loadCourseDAO;
import DBConnection.DBConnection;

@WebServlet("/addClassInfo")
public class addClassInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public addClassInfo() {
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
			request.setCharacterEncoding("UTF-8");
			Connection conn = DBConnection.CreateConnection();
			String message="";
			
	        Class c = new Class();
            c.setClassname(request.getParameter("classname"));
            c.setCourse_id(Integer.parseInt(request.getParameter("ListCourse")));
            c.setStartday(request.getParameter("startday"));
            c.setEndday(request.getParameter("endday"));
            c.setTimestudy(request.getParameter("timestudy"));
            c.setTestday(request.getParameter("testday"));
            c.setLocation(request.getParameter("location"));
            c.setNumber_of_students(0);
            
            boolean flag = editClassDAO.InsertClass(c, conn);
            if(flag == true) {
    			message= "Thêm lớp học thành công!";
    		}
    		else {
    			message= "Thêm thất bại!";
    		}
            // load lại trang admin_course
            List<Class> listClass = loadClassDAO.LoadListClasses(conn);
			List<course> listCourse = loadCourseDAO.LoadListCourses(conn);
			
			request.setAttribute("listClass", listClass);
			request.setAttribute("listCourse", listCourse);
			request.setAttribute("message", message);
			request.setAttribute("course", "0");
			
			RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/admin_class.jsp");
			rd.forward(request, response);
		}
	}

}
