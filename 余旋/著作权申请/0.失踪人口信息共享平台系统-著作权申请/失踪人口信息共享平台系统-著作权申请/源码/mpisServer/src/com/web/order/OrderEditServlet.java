package com.web.order;

 
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.web.object.Info;

import net.sf.json.JSONArray;



@SuppressWarnings("serial")
public class OrderEditServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
 
		OrderDao orderDao = new OrderDao();
 
	 	if ( req.getParameter("method") != null){
	 		
	 		if( req.getParameter("method").equals("save")  ){
	 			Order order = new Order(); 

	 			order.info_name =   req.getParameter("order.info_name")  ;
	 			order.user_name =  req.getParameter("order.user_name")  ; 
	 			
	 			orderDao.save(order);
	 			resp.setCharacterEncoding("utf-8");
				
		    	try {
		    		resp.getWriter().write( "1");
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
	 		}else  if( req.getParameter("method").equals("update")  ){

	 			orderDao.updateOrder( req.getParameter("order.info_name") , req.getParameter("order.user_name") , req.getParameter("order.flag") ) ;
	 			resp.setCharacterEncoding("utf-8");
				
		    	try {
		    		resp.getWriter().write( "1");
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
	 		}
	 	}	
	}
 
  
	
}
