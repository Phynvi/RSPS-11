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

@WebServlet("/board")
public class BoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/board/board_list.jsp");
    	
    	List<Map<String, Object>> list =getBoard();
    	 request.setAttribute("list", list);
    	 dispatcher.forward(request, response);
	}

    
	public List<Map<String, Object>> getBoard(){
		BoardDAO dao = new BoardDAO();
		List<Map<String, Object>> list =null;
		try {
			list = dao.getBoardList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
