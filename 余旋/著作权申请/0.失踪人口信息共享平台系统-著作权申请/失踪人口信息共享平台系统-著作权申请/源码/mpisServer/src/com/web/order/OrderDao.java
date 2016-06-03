package com.web.order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.origin.base.DAOBase; 
import com.web.object.Info;
import com.web.util.Constant;
import com.web.util.Tool;



public class OrderDao {

	public  void delete( String id ){
			DAOBase dao = new DAOBase(); 
			String sql = " delete from  orders  where  id=" + id  ; 
			dao.executeSql(sql);   	  
	}
	
	public  void save( Order object ){

    	DAOBase dao = new DAOBase(); 
		String sql =  "insert into  orders  (  info_name ,  user_name  ) values( '" + 
								object.info_name   + "' , '" + object.user_name  + 
								 "' )";   
		dao.executeSql(sql);   	 
	}


	public  void updateOrder( String info_name , String  user_name , String state ){
 
    	DAOBase dao = new DAOBase();  
		String sql =  " update orders  set  flag = '" + state + "'  where  info_name = '" + info_name + "' and  user_name = '" +  user_name + "' ";   
		dao.executeSql(sql);   	 
	}
	
	
	public  List getAllByFlag(String flag ){
		
		List<Order> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from orders where flag = '" + flag +"' order by id desc ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Order object = new Order();
				object.setId( String.valueOf( rs.getInt(1))) ; 
				object.info_name =  rs.getString(2)  ;
				object.user_name =  rs.getString(3)  ;
				object.addtime =   rs.getString(4) ;  
				 
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


	public  List getAll(){
		
		List<Order> list = new ArrayList() ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from orders order by id desc ";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				Order object = new Order();
				object.setId( String.valueOf( rs.getInt(1))) ; 
				object.info_name =  rs.getString(2)  ;
				object.user_name =  rs.getString(3)  ;
				object.addtime =   rs.getString(4) ;  
				 
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
	 
	public  String tongji( String  startDate , String endDate  ){
		
		String sum_price = null ;
    	DAOBase dao = new DAOBase();
		try {  
			String sql=" select sum(price) as sum_price  from orders where order_date > '" + startDate + "' and  order_date < '" + endDate + "'";  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				sum_price  = rs.getString("sum_price") ;  
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return sum_price;
	}

	
	public  Info getFoodById( String food_id ){

		Info object = null ;
    	DAOBase dao = new DAOBase();
		try { 
			String sql=" select * from " + Constant.DB_PREFIX + "info_info  where info_id	=  " + food_id	 + "     order by info_id	 desc  "  ;  
	    	dao.querySql(sql);
    		ResultSet rs = dao.getRes(); 
			while(rs.next()) {
				object = new Info();
				object.info_id =  String.valueOf( rs.getInt(1))  ;
				object.info_name =  rs.getString(2)  ;
				object.info_pic =  rs.getString(3) ; 
				object.info_description =  rs.getString(4) ; 
				object.info_people =   String.valueOf( rs.getFloat(5) ) ;
				object.info_pphone =   rs.getString(6) ;
				object.info_flag = String.valueOf( rs.getInt(7) ) ;
				object.info_count =  rs.getString("info_count") ; 
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(dao != null)
				dao.release();			
		}
		return object ;
	}

	
	public  void updateFoodCount( String food_id , long food_count){
 
    	DAOBase dao = new DAOBase(); 
		String sql =  " update info_info  set  info_count = '" + food_count + "'  where  info_id	=" + food_id ;   
		dao.executeSql(sql);   	 
	}

	
}
