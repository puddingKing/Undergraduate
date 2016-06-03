package com.bb.ui;

import com.bb.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.self.model.UserInfo;
import edu.self.utils.AppContext;

/**
 * 用户信息
 * @author mzba
 *
 */
public class UserInfoActivity extends Activity {
	
	private UserInfo userinfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userinfo);
		
		userinfo = (UserInfo) getIntent().getSerializableExtra("user");
		
		((TextView) findViewById(R.id.user_name)).setText("类型：" + userinfo.getUserName());
		((TextView) findViewById(R.id.address)).setText("联系地址："
				+ userinfo.getAddress());
		((TextView) findViewById(R.id.phone)).setText("联系电话："
				+ userinfo.getPhone());
		
		 
		Button btnUpdate = (Button) findViewById(R.id.update_userinfo);
		if (userinfo.getUserId().equals(AppContext.userinfo.getUserId())) {
			btnUpdate.setVisibility(View.VISIBLE);
			btnUpdate.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(UserInfoActivity.this, UpdateUserInfoActivity.class);
					intent.putExtra("user", userinfo);
					startActivity(intent);
				}
			});
		} else {
			btnUpdate.setVisibility(View.GONE);
		}
		
//		Button btnExit = (Button) findViewById(R.id.update_exit);
//		btnExit.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				Intent intent = new Intent(UserInfoActivity.this, LoginActivity.class); 
//				startActivity(intent);
//				finish();
//			}
//		});
		
		
	}
}
