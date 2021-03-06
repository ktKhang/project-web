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

import Bean.comment;
import Bean.discussion;
import DAO.loadDiscussionDAO;
import DBConnection.DBConnection;

@WebServlet("/user_thaoluan_baiviet")
public class user_thaoluan_baiviet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public user_thaoluan_baiviet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			Connection conn = DBConnection.CreateConnection();
			String discussionID = request.getParameter("discussionID");
			String Stitle = request.getParameter("Stitle");
			int role = Integer.parseInt(request.getParameter("role"));
			
			System.out.println(discussionID);
			discussion c = new discussion();
			
			
			c = loadDiscussionDAO.LoadDiscussionInfo(discussionID, conn);
			List<comment> listComment = loadDiscussionDAO.LoadListComments(discussionID, conn);
			
			
			request.setAttribute("discussionInfo", c);
			request.setAttribute("listComment", listComment);
			request.setAttribute("Stitle", Stitle);
			request.setAttribute("discussionID", discussionID);
			
			if(role == 1) {
				RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/user_thaoluan_baiviet.jsp");
				rd.forward(request, response);
			}
			else if(role == 2) {
				RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/user_thaoluan_baiviet2.jsp");
				rd.forward(request, response);
			}
			else if(role == 3) {
				RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/user_thaoluan_baiviet3.jsp");
				rd.forward(request, response);
			}
			else if(role == 4) {
				RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/user_thaoluan_baiviet4.jsp");
				rd.forward(request, response);
			}
			else if(role == 5) {
				RequestDispatcher rd= request.getRequestDispatcher("/WEB-INF/user_thaoluan_baiviet5.jsp");
				rd.forward(request, response);
			}
	}

}
