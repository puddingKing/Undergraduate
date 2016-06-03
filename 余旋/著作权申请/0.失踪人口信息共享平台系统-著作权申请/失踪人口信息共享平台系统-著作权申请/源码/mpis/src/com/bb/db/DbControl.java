package com.bb.db;


import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log; 
import com.bb.util.Tool;
 

/**
 *  sqlite数据库类
 * @author Administrator
 *
 */
public class DbControl {

 

	private static final String CREATE_TABLE_NOTE = "create table if not exists gwc ( id integer primary key autoincrement, "
		+ "food_name text not null , price text not null , seat text not null , order_date text not null  );" ;
 
	 
	private static final String DATABASE_NAME = "b_food";
 
	private static final String TABLE_NAME = "gwc" ; 
	
	
	private SQLiteDatabase db;
	 

	public DbControl(Context ctx) {
		
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
//		db.execSQL(" drop table " + TABLE_NAME );
		db.execSQL(CREATE_TABLE_NOTE); 

		int test = db.getVersion(); 
		Log.i( "===db version===", String.valueOf( test )  );
		 
		db.close();
	}
	 


	/**
	 *  新增
	 * @param ctx
	 * @param note
	 * @return
	 */
   public boolean addGwc( Context ctx , String food_name , String  seat , String price    ) {
	   
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		ContentValues values = new ContentValues();
//		food_name ,  seat , order_date
		
		values.put("food_name", food_name);
		values.put("seat", seat);
		values.put("price", price ) ;
		values.put("order_date", Tool.getNowTime() ); 

		boolean returnValue = db.insert(TABLE_NAME, null, values) > 0;
		db.close();
		return (returnValue);
    }
   

   
  
	/**
	 *  删除
	 * @param ctx
	 * @param note
	 * @return
	 */
  public boolean deleteGwc( Context ctx , long id  ) {
		
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
		boolean returnValue = false;

		int result = 0;
		result = db.delete(TABLE_NAME, "id=" + id , null);
		db.close();
		
		if (result == 1){
			returnValue = true;
		}
		
		return returnValue;
   }
  
  
  public boolean deleteAllGwc( Context ctx   ) {
		
		db = ctx.openOrCreateDatabase(DATABASE_NAME, 0, null);
		
		boolean returnValue = false;

		int result = 0;
		result = db.delete(TABLE_NAME, null , null);
		db.close();
		
		if (result == 1){
			returnValue = true;
		}
		
		return returnValue;
   }
  
  
  
 
	
  
}

