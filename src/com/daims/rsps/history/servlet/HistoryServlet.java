package com.daims.rsps.history.servlet;

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

import com.daims.rsps.history.dao.HistoryDAO;
import com.daims.rsps.util.PagingUtil;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public HistoryServlet() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/history/history_list.jsp");
    	String currentPage= (String) request.getParameter("page");
    	List<Map<String, Object>> list =getHistory(currentPage);
    	 request.setAttribute("list", list);
    	 request.setAttribute("pageLimit", getPaging(currentPage).get("pageLimit"));  
    	 request.setAttribute("totalPage",getPaging(currentPage).get("totalPage")); 
    	 request.setAttribute("currentPage",getPaging(currentPage).get("page")); 
    	 request.setAttribute("defaultPageLimit",getPaging(currentPage).get("defaultPageLimit")); 
    	 request.setAttribute("startPage",getPaging(currentPage).get("startPage")); 
    	 dispatcher.forward(request, response);
	}
    
	/**
	 * paging에 필요한 요소를 Map에 담음.
	 * @param page
	 * @return  Map<String, Integer> paging
	 */
	public Map<String, Integer> getPaging(String page){
		 Map<String, Integer>paging = new HashMap<String, Integer>();
		 Map<String, Object> params = new HashMap<String, Object>();
		 PagingUtil PagingUtils = new PagingUtil();
		 List<Map<String, Object>> list = null;

		 int totalCnt =0;
		 int limit = 10;
		 int startPage = 0;
		 int totalPage = 0;
		 int defaultPageLimit = 10;
		 int pageLimit = 0;
		 int currentPage = 1;
		 
			if(page != null){
				currentPage = Integer.parseInt(page);
			}
		 
		 totalCnt = getHistoryCount( params) ;
		 totalPage = PagingUtils.calculateTotalPage(limit, totalCnt);
	 	 startPage = PagingUtils.startPage(currentPage, defaultPageLimit);
	  	 pageLimit = PagingUtils.pageLimit(defaultPageLimit, totalPage, startPage);
	  	 paging.put("pageLimit", pageLimit);
	  	 paging.put("totalPage", totalPage);
	  	 paging.put("page", currentPage);
	  	 paging.put("defaultPageLimit", defaultPageLimit);
	  	 paging.put("startPage", startPage);
    	 
		 return paging;
	}
	
	/**
	 * 가위바위보 이력 List를 HistoryDAO에서 가져온다.
	 * @param page
	 * @return List<Map<String, Object>> list
	 */
	public List<Map<String, Object>> getHistory(String page){
		HistoryDAO dao = new HistoryDAO();
		PagingUtil PagingUtil = new PagingUtil();
		Map<String, Object> params = new HashMap<String, Object>();
		List<Map<String, Object>> list =null;
		int currentPage = 1;
		if(page != null){
			currentPage = Integer.parseInt(page);
		}
		int limit = 10;
		int startIndex = 0;
		startIndex = PagingUtil.startIndex(currentPage, limit);
		
		if(startIndex!= 0){
			limit = startIndex + limit;
			startIndex +=1;
		}
		params.put ("limit", limit);
		params.put ("startIndex", startIndex);
		try {
			list = dao.getHistoryList(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 *  총 가위바위보 게임수를 HistoryDAO에서 받아온다
	 * @param params
	 * @return int cnt
	 */
	public int getHistoryCount(Map<String, Object> params){
		HistoryDAO dao = new HistoryDAO();
		int cnt =0;
		try {
			cnt =  dao.getHistoryCnt(params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
}
