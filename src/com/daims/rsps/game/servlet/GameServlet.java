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

/**
 * Servlet implementation class GameController
 */
@WebServlet(name = "game", urlPatterns = { "/game" })
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/game/game_view.jsp");
		String ist = "";
		if( req.getAttribute("insert")!= null){
			ist = (String) req.getAttribute("insert");
		}
		
		req.setAttribute("title","R(rock)S(scissors)P(paper)");
		if(ist.equals("1")){
			System.out.println("hihi");
			GameDAO dao = new GameDAO();
			List<Map> list =null;
			try {
				list = dao.getHistory();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("list",list);
			dispatcher.forward(req, res);
		}else{
			dispatcher.forward(req, res);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String p_type = request.getParameter("p_type");
		String u_type = request.getParameter("u_type");
		String vs_result = request.getParameter("vs_result");
		
		GameDAO dao = new GameDAO();
		int cnt = -1;
		try {
			cnt = dao.addGame(p_type, u_type, vs_result);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(cnt != 0){
			System.out.println("no");
		}else{
			request.setAttribute("insert","1");
			doGet(request, response);
		}
	}
}