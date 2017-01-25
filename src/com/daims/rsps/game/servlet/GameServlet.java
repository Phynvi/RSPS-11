package com.daims.rsps.game.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daims.rsps.game.dao.GameDAO;

@WebServlet(name = "game", urlPatterns = { "/game" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GameServlet() {
        super();
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/game/game_view.jsp");
		String pcType = request.getParameter("pcType");
		String userType = request.getParameter("userType");
		String vsResult = request.getParameter("vsResult");
		if(pcType!=null  && userType!=null  && vsResult!=null  ){
			addGame(pcType, userType, vsResult);
			dispatcher.forward(request, response);
		}else{
			dispatcher.forward(request, response);
		}
	}
	
	/**
	 * 게임을 할 때 마다 게임을 결과를 저장한다.
	 * @param pcType
	 * @param userType
	 * @param vsResult
	 * @return int cnt 
	 */
	public int addGame(String pcType, String userType, String vsResult) {
		GameDAO dao = new GameDAO();
		int cnt = -1;
		try {
			cnt = dao.addGame(pcType, userType, vsResult);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}