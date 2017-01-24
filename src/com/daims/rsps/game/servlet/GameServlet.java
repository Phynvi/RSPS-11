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

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/game/game_view.jsp");
		String pcType = req.getParameter("pcType");
		String userType = req.getParameter("userType");
		String vsResult = req.getParameter("vsResult");
		if(pcType!=null  && userType!=null  && vsResult!=null  ){
			addGame(pcType, userType, vsResult);
			dispatcher.forward(req, res);
		}else{
			dispatcher.forward(req, res);
		}
	}
	
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