package com.web.object;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase;  
import com.web.util.Constant;



public class InfoMgrDao {

	

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  " + Constant.DB_PREFIX + "info_info   where info_id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	
	public  void save( Info object ){

    	DAOBase dao = new DAOBase(); 
		String sql =  "insert into " + Constant.DB_PREFIX + "info_info ( info_name  , info_pic , info_description , info_people , info_pphone ,info_flag ) values( '" + 
								object.info_name  + "' , '" + object.info_pic  + "' , '" + object.info_description + "' , '" + 
								object.info_people  + "' , '"  + object.info_pphone +  "' , " + object.info_flag  + ")";   
		dao.executeSql(sql);   	   
	}


	public  List getFollowedByName( String username   ){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from info_info , orders  where  user_name ='" + username + "' and  orders.info_name = info_info.info_name  order by info_id desc " ; 
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) { 
				
				Info object = new Info();
				object.info_id =  String.valueOf( rs.getInt("id"))  ;
				object.info_name =  rs.getString("info_name")  ;
				object.info_pic =  rs.getString("info_pic") ; 
				object.info_description =  rs.getString("info_description") ; 
				object.info_people =   rs.getString("info_people") ;
				object.info_pphone =   rs.getString("info_pphone") ;
				object.info_flag = String.valueOf( rs.getInt("info_flag") ) ;
				object.info_count =  rs.getString("info_count") ; 
				list.add(object); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return list;
	}


	
	public  List search( String search_name , String search_type    ){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "info_info where 1=1   " ;
			
			if( search_name != null ){
				sql += " and  info_name like '%" + search_name + "%' " ;
			}
			if( search_type != null ){
				sql += " and  info_people like '%" + search_type + "%' " ;
			} 
			
			sql += " order by info_id desc  " ; 
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) { 
				
				Info object = new Info();
				object.info_id =  String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_people =     rs.getString(5)   ;
				object.info_pphone =   rs.getString(6) ;
				object.info_flag = String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
				list.add(object); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return list;
	}
	
	
	public  List getAll( int pageIndex  , int pageSize , String flag  ){
		
		List<Info> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "info_info  " ;
			
			if( flag !=null && !flag.equals("") )
					sql += " where info_flag=  " + flag ;  
			
			sql += " order by info_id desc  limit " + pageIndex  + ", " + pageSize ; 
			
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) { 
				
				Info object = new Info();
				object.info_id =  String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_people =   rs.getString(5) ;
				object.info_pphone =   rs.getString(6) ;
				object.info_flag = String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
				list.add(object); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return list;
	}
	
	
	public int getTotalCount( ){
		
		String sql = " select count(info_id) as ids from info_info " ;
	 	DAOBase dao = new DAOBase();
		int rowCount = 0; 
		try { 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
    		if(rs.next()) { 
    		    rowCount = rs.getInt("ids"); 
    		}
    		 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return rowCount ;
	}
	
	 
}
