package com.web.type;

 
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder; 
import com.web.util.Constants;

import net.sf.json.JSONArray;



@SuppressWarnings("serial")
public class TypeMgrServlet extends HttpServlet {

	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String method = req.getParameter("method");
		TypeMgrDao newsMgrDao = new TypeMgrDao();
 
		
		if (method.equals("search")) {

			 String type = req.getParameter("type");
			 if (type != null && type.equals("json")) { 
				 
			    	JSONArray jsonArray  =  JSONArray.fromObject( newsMgrDao.getAllByPhone() ) ;
				    resp.setCharacterEncoding("gb2312");
			    	try {
			    		resp.getWriter().write(jsonArray.toString());
			    	} catch (IOException e) {
			    		e.printStackTrace();
			    	}
			    	return ;		
			 }  
		}else if (method.equals("tongji")) {
			
			req.setAttribute("list", newsMgrDao.getAllByPhone() ); 
			req.getRequestDispatcher("/type_list2.jsp").forward(req, resp);
			return;
		}else  if (method.equals("savePhone")) {
			
			  	Type news  = new Type();
			   	news.name =   req.getParameter("name")   ;
			   	news.xuehao =   req.getParameter("xuehao")   ; 
			   	news.jiaoshi =   req.getParameter("jiaoshi")   ;
			   	news.flag =   "1"  ; 

			   	newsMgrDao.save( news ) ;
				resp.setCharacterEncoding("utf-8");
		    	try {
		    		resp.getWriter().write( "1");
		    	} catch (IOException e) {
		    		e.printStackTrace();
		    	}
		    	return;
			
		}else if (method.equals("list")) {

			String type = req.getParameter("type");
		    
		    if (type != null && type.equals("json")) {
		    	 
//		    	JSONArray jsonArray  =  JSONArray.fromObject( newsMgrDao.getAll(req.getParameter("name")) );
//		    	resp.setCharacterEncoding("utf-8");
		    	
//		    	try {
//		    		resp.getWriter().write(jsonArray.toString());
//		    	} catch (IOException e) {
//		    		e.printStackTrace();
//		    	}
//		    	return ;		    	
			}  else {
				getAll(req, newsMgrDao);
				
				if(  req.getParameter("yd") != null &&  req.getParameter("yd").equals("1") )
					req.getRequestDispatcher("/type_list3.jsp").forward(req, resp);
				else
					req.getRequestDispatcher("/type_list.jsp").forward(req, resp);
			}
		    
		} else if (   method.equals("goUpdate") ){

			String id = req.getParameter("id");

			req.setAttribute("map", newsMgrDao.getObjectById(id) ); 
			req.getRequestDispatcher("/type_add.jsp").forward(req, resp);
			
		} else if ( method.equals("goAdd") ) { 
			
			req.getRequestDispatcher("/type_add.jsp").forward(req, resp);
			
		} else if (method.equals("save")) { 
  
			   	Type news  = new Type();
			   	boolean isAdd  = false ;
			   	if( req.getParameter("id") != null && !req.getParameter("id") .equals("") ){
				   	news.setId( Integer.valueOf( req.getParameter("id") )  ) ;
			   	}else {
			   		isAdd = true; 
			   	}
			   	news.name =   req.getParameter("name")   ;
			   	news.xuehao =   req.getParameter("xuehao")   ; 
			   	news.jiaoshi =   req.getParameter("jiaoshi")   ;
			   	news.flag =   "0"  ; 

			   	newsMgrDao.save( news ) ;
			   	if( isAdd) {
					req.setAttribute("s", "0") ; 
					req.getRequestDispatcher("/type_add.jsp").forward(req, resp);
			   	} else {
			   		getAll(req, newsMgrDao);
					req.getRequestDispatcher("/type_list.jsp").forward(req, resp);
			   	}
			
		} else if (method.equals("del")) {
			String id = req.getParameter("id");
			newsMgrDao.delete(id);
			getAll(req, newsMgrDao);
			req.getRequestDispatcher("/type_list.jsp").forward(req, resp);
		}

	}

	private void getAll(HttpServletRequest req, TypeMgrDao objectDao) { 
		
		int pageNo = 0 ; 
		int start =  0 ;

		String pageName = new ParamEncoder("element").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		
		if( req.getParameter(pageName) != null  && !req.getParameter(pageName).equals("")){
			pageNo = Integer.parseInt( req.getParameter(pageName) );
		}else{
			pageNo = 1 ;
		}

		start = (pageNo-1) * Constants.PAGE_SIZE  ;
 
		List list = objectDao.getAll( start , Constants.PAGE_SIZE , req.getParameter("yd")  ); 
 
		req.setAttribute("total",  objectDao.getTotalCount(  req.getParameter("yd")  ) );
		req.setAttribute("list", list);  
	}

//	private void getAll(HttpServletRequest req, TypeMgrDao newsMgrDao) {
//
//		List list = newsMgrDao.getAll( null );
//
//		req.setAttribute("list", list); 
//	}
  
	
}
