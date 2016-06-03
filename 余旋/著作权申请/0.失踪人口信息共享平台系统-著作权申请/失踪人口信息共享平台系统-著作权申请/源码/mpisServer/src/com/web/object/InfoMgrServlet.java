package com.web.object;

 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;

import com.web.user.UserDao;
import com.web.util.Constants;

import net.sf.json.JSONArray;



@SuppressWarnings("serial")
public class InfoMgrServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	    String flag = req.getParameter("f");
	    
		InfoMgrDao infoMgrDao = new InfoMgrDao();

	 	String type = req.getParameter("type");
	 	String method = req.getParameter("method")  ;
	 	
	 	if ( method != null ){
	 		
	 		if( method.equals("del") ) {
	 	        String id = req.getParameter("id") ;
	 	        infoMgrDao.delete(id);
	 		}else if( method.equals("getFollowedByName") && type.equals("json") ) {
	 			  JSONArray jsonArray = null;
	 		      jsonArray = JSONArray.fromObject( infoMgrDao.getFollowedByName( req.getParameter("name") ) );
	 		      
//	 		      resp.setContentType("application/x-json");
	 		      resp.setCharacterEncoding("gb2312");

	 		      resp.getWriter().write(jsonArray.toString());
	 		}else if( method.equals("search") && type.equals("json") ) {
	 			
	 			  JSONArray jsonArray = null;
	 		      jsonArray = JSONArray.fromObject( infoMgrDao.search( req.getParameter("search_name") ,  req.getParameter("search_type")  ) );
	 		      
//	 		      resp.setContentType("application/x-json");
	 		      resp.setCharacterEncoding("gb2312");

	 		      resp.getWriter().write(jsonArray.toString());
	 		}
	 		
	 	}
	 		
	    if (type != null) {
	      JSONArray jsonArray = null;
	      jsonArray = JSONArray.fromObject( infoMgrDao.getAll(0,10000, flag ) );
	      
//	      resp.setContentType("application/x-json");
	      resp.setCharacterEncoding("gb2312");

	      resp.getWriter().write(jsonArray.toString());
	    }  else {
	    
	    	if( req.getParameter("all") != null && !"null".equals( req.getParameter("all") ) ){
	    		req.setAttribute("all",  "1" );
	    		getAll(req, infoMgrDao) ;
//	  			req.setAttribute("list",   ); 
	    	}else{
	  	      if ((flag != null) && (!flag.equals(""))) {
 
	  	    	  	getAll(req, infoMgrDao);
	  			
//		    	  	req.setAttribute("list", infoMgrDao.getFoodsByFlag(flag));
		    	  	req.setAttribute("flag", flag);
		      } else { 
		  			req.setAttribute("all",  "1" ); 
//		  			req.setAttribute("list",  getAll(req, infoMgrDao) ); 
		    		getAll(req, infoMgrDao) ;
		      }
	    	}
			req.getRequestDispatcher("/infoList.jsp").forward(req, resp);
	    }
	}
 
  
	private void getAll(HttpServletRequest req, InfoMgrDao dao) { 
		
		int pageNo = 0 ; 
		int start =  0 ;

		String pageName = new ParamEncoder("element").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		
		if( req.getParameter(pageName) != null  && !req.getParameter(pageName).equals("")){
			pageNo = Integer.parseInt( req.getParameter(pageName) );
		}else{
			pageNo = 1 ;
		}

		start = (pageNo-1) * Constants.PAGE_SIZE  ;
 
		List list = dao.getAll( start , Constants.PAGE_SIZE , req.getParameter("f") ); 
 
		req.setAttribute("total",  dao.getTotalCount() );
		req.setAttribute("list", list);  
	}
	
	
	
}
