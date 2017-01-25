package com.daims.rsps.board.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daims.rsps.board.dao.BoardDAO;
import com.daims.rsps.history.dao.HistoryDAO;
import com.daims.rsps.util.PagingUtil;

@WebServlet("/write")
public class WriteBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteBoardServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/board/board_write.jsp");
    	 dispatcher.forward(request, response);
	}

    public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String title = request.getParameter("title");
    	String writer = request.getParameter("writer");
    	String content = request.getParameter("content");
    	
    	if(title!=null  && writer!=null  && content!=null  ){
    		addBoard(title, writer, content);
    	}
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp");
    	BoardServlet board = new BoardServlet();
    	List<Map<String, Object>> list =board.getBoard();
	   	 request.setAttribute("list", list);
	   	 dispatcher.forward(request, response);
    }
    
    public int addBoard(String title, String writer, String content){
    	BoardDAO dao = new BoardDAO();
    	int cnt =0;
    	try {
			cnt = dao.addBoard(title, writer, content);
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return cnt;
    }
}
