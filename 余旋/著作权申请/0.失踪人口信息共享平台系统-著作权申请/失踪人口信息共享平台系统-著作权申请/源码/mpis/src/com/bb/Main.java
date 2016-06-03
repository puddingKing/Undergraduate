package com.bb;


import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bb.api.HttpApiAccessor;
import com.bb.misc.BounceInterpolator;
import com.bb.misc.EasingType.Type;
import com.bb.ui.InfoListActivity;
import com.bb.ui.MyListActivity;
import com.bb.ui.SearchListActivity;
import com.bb.ui.UserInfoActivity;
import com.bb.util.Constants;

import edu.self.component.AppException;
import edu.self.component.Connect;
import edu.self.model.UserInfo;
import edu.self.utils.AppContext;



/**
 * 系统启动类，显示操作
 * @author Administrator
 *
 */
public class Main extends Activity {
 
	
    private LinearLayout oneRow,  aboutRow,exitRow ,  three_row   , four_row , five_row ;

	private ProgressDialog dialog ;  
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	dialog = new ProgressDialog(this);
		dialog.setMessage("正在加载用户信息...请稍候");
		dialog.show();

		new Thread(){
			public void run() {
				loadAllUserInfo();
				runOnUiThread(new Runnable() {
					public void run() {  
						for (UserInfo userinfo: list) {
							if (userinfo.getUserId().equals(Constants.userId )) {
								AppContext.userinfo = userinfo; 
								load();
							}  
						} 
						dialog.dismiss();
					}
				});
			};
		}.start();
		
    }
    
	private void load(){
		 
		// 查看
		 oneRow = (LinearLayout)findViewById(R.id.foods_row);
		 oneRow.setOnClickListener(new View.OnClickListener() {
		     public void onClick(View v) {
//		         Intent i = new Intent(Main.this, Foods.class);
//		         startActivity(i);
		    	 
		    	 Intent all = new Intent(Main.this, InfoListActivity.class);
		         all.putExtra("type", Constants.FLAG_ALL);
		         startActivity(all);
		         
		     }
		 });
		
		// 
		aboutRow = (LinearLayout)findViewById(R.id.about_row);
		aboutRow.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		         Intent i = new Intent(Main.this, MyListActivity.class);
		         i.putExtra("type", "0" );
		         startActivity(i);
		    }
		});
		 

		// 
		three_row = (LinearLayout)findViewById(R.id.three_row);
		three_row.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	Intent intent3 = new Intent(Main.this, UserInfoActivity.class);
				intent3.putExtra("user", AppContext.userinfo);
				startActivity(intent3);
		    }
		});

		// 
		four_row = (LinearLayout)findViewById(R.id.four_row);
		four_row.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	showDialog(Main.this) ; 
		    }
		});
		
		
		five_row = (LinearLayout)findViewById(R.id.five_row);
		five_row.setOnClickListener(new View.OnClickListener() {
           public void onClick(View v) {
           		showDialog2(Main.this); 
           }
		});
	       
		
		// 退出
		 exitRow = (LinearLayout)findViewById(R.id.exit_row);
		 exitRow.setOnClickListener(new View.OnClickListener() {
		     public void onClick(View v) {
		         finish();
		     }
		 });
		 
	}
    
    
	private void showDialog2( Context context ){
    	
    	LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts_yuding, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setView(promptsView);
		
		final EditText et_search_name = (EditText) promptsView.findViewById(R.id.search_name);
		final EditText et_search_type = (EditText) promptsView.findViewById(R.id.search_type);
		final EditText et_search_song = (EditText) promptsView.findViewById(R.id.search_info);
		
		alertDialogBuilder
			.setCancelable(false)
			.setTitle("简要信息登记")
			.setPositiveButton("OK",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int pid) {
					 
						HashMap map = new HashMap();  
				 
						map.put("name",  et_search_name.getText().toString().trim()  );	
						map.put("xuehao",  et_search_type.getText().toString().trim()  );
						map.put("jiaoshi",  et_search_song.getText().toString().trim() );
						map.put("flag",  "1" );
						
//						发送信息给服务端
						String  result  = HttpApiAccessor.saveYuding(  map )   ;
						Toast.makeText( Main.this, "登记成功！", Toast.LENGTH_SHORT).show();
			    }
		});
		
		alertDialogBuilder.setNegativeButton("取消",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                         
                    }  
		  });

		AlertDialog alertDialog = alertDialogBuilder.create(); 
		alertDialog.show(); 
  }

  
    private void showDialog( Context context ){
    	
    	LayoutInflater li = LayoutInflater.from(context);
		View promptsView = li.inflate(R.layout.prompts_search, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setView(promptsView);
		 
		final EditText et_search_name = (EditText) promptsView.findViewById(R.id.search_name); 
		final EditText et_search_type = (EditText) promptsView.findViewById(R.id.search_type); 
		
		alertDialogBuilder
			.setCancelable(false)
			.setTitle("搜索")
			.setPositiveButton("OK",
			  new DialogInterface.OnClickListener() {
			    public void onClick(DialogInterface dialog,int id) {
			    	 
		        	  Intent all = new Intent(Main.this, SearchListActivity.class);
		              all.putExtra("search_name", et_search_name.getText().toString().trim() );
		              all.putExtra("search_type", et_search_type.getText().toString().trim() ); 
		              startActivity(all);
			    }
			  });
		
		alertDialogBuilder.setNegativeButton("取消",  
                new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int whichButton) {  
                    	dialog.cancel();
                    }  
		  });

		AlertDialog alertDialog = alertDialogBuilder.create(); 
		alertDialog.show(); 
    }
    
    
	private List<UserInfo> list;
	

    /**
	 * 加载所有用户的信息
	 */
	private void loadAllUserInfo() {
		list = new ArrayList<UserInfo>();
		Connect connect = new Connect(AppContext.SERVER_USERS, AppContext.HTTP_POST);
		try {
			byte[] data = connect.queryServer(null);
			JSONObject object = new JSONObject(new String(data, "gb2312"));
			JSONArray userArray = object.getJSONArray("users");
			for (int i = 0;i < userArray.length();i++) {
				JSONObject userObject = userArray.getJSONObject(i);
				UserInfo userinfo = new UserInfo();
				userinfo.setUid(userObject.getInt("uid"));
				userinfo.setPassword(userObject.getString("password"));
				userinfo.setUserId(userObject.getString("userId"));
				userinfo.setUserName(userObject.getString("userName"));
				userinfo.setAddress(userObject.getString("address"));
				userinfo.setPhone(userObject.getString("phone"));
				userinfo.setGrade( userObject.getInt("grade")); 
				list.add(userinfo);
			}
		} catch (AppException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
}
