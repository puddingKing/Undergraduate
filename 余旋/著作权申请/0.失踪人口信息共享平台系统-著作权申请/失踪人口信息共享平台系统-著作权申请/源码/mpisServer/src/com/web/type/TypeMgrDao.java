package com.web.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase; 



public class TypeMgrDao {

	

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  type where id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	
	public  void save( Type object ){

    	DAOBase dao = new DAOBase(); 
    	String sql = null; 
    	if( object.getId() == 0) {
    		sql =   "insert into type (  name , xuehao , jiaoshi ,   flag   )" +
					"  values( '" + object.getName()   + "' , '" + object.xuehao  + "' , '" + object.jiaoshi  
					 + "' , '" + object.flag   + "' )";   
    	}else{
    		sql = " update type  set  name ='" + object.getName() + "'  , jiaoshi = '" + object.jiaoshi + "'  , xuehao = '" + object.xuehao 
    				+  "'  where id = " + object.getId() ; 
    	}
		dao.executeSql(sql);   	 
	}


	
//	name , xuehao , jiaoshi , addtime , flag 
	public  Type getObjectById( String id ){
		
    	DAOBase dao = new DAOBase();
		Type object =  null ; 
		try { 
			String sql = "select * from type  where id=" + id ;  
			 
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			if(rs.next()) {
				
				object = new Type();
				object.id =  rs.getInt("id")  ; 
				
				object.name =   rs.getString("name") ;  
				object.xuehao =   rs.getString("xuehao") ; 
				object.jiaoshi =   rs.getString("jiaoshi") ; 
				object.addtime =   rs.getString("addtime") ; 
				object.flag =   rs.getString("flag") ; 
				return object ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return null;
	}
	 
	public  List<Type> getAllByPhone(  ){
		
		List<Type> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
//			select  count(*) , jiaoshi   from type where flag = '0'  group by jiaoshi 
			String sql = " select   jiaoshi ,  count( case flag when '0' then '0' end) as now_count , " +
					"  count( case flag when '1' then '1' end ) as yuding_count    from type    group by jiaoshi " ; 

	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Type object = new Type();
				object.jiaoshi =   rs.getString("jiaoshi") ; 
				object.name =   rs.getString("now_count") ;  
				object.xuehao =   rs.getString("yuding_count") ; 
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
	 
	
	
	public  List getAll(  int pageIndex  , int pageSize  , String flag ){
		
		List<Type> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql = "select * from type ";  
			if( flag != null ){
				sql += " where flag = '" + flag + "' " ;
			}
			sql += " order by id desc  limit " + pageIndex + ", " + pageSize  ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Type object = new Type();

				object.id =  rs.getInt("id")  ; 
				object.name =   rs.getString("name") ;  
				object.xuehao =   rs.getString("xuehao") ; 
				object.jiaoshi =   rs.getString("jiaoshi") ; 
				object.addtime =   rs.getTimestamp("addtime").toString() ; 
				object.flag =   rs.getString("flag") ;  
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
	
	public  List getAll( String search_name , String search_type , String search_info  ){
		
		List<Type> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql = "select * from type where 1=1 ";  
			if( search_name != null ){
				sql += " and  name like '%" + search_name + "%' " ;
			}
			if( search_type != null ){
				sql += " and state like '%" + search_type + "%' " ;
			}
			if( search_info != null ){
				sql += " and content like '%" + search_info + "%' " ;
			}
			 
			sql += " order by id desc  ; "  ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Type object = new Type();
				object.id =  rs.getInt("id")  ; 
				object.name =   rs.getString("name") ;  
				object.xuehao =   rs.getString("xuehao") ; 
				object.jiaoshi =   rs.getString("jiaoshi") ; 
				object.addtime =   rs.getString("addtime") ; 
				object.flag =   rs.getString("flag") ;  
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
	
	
	
	public int getTotalCount( String flag  ){
		
		String sql = " select count(id) ids from type " ;
		if( flag != null ){
			sql += " where flag = '" + flag + "' " ;
		}
		
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
