package com.bb.ui;


import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bb.R;
import com.bb.api.HttpApiAccessor;
import com.bb.db.DbControl; 
import com.bb.model.Info;
import com.bb.util.AsyncImageLoader;
import com.bb.util.AsyncImageLoader.ImageCallback;
import com.bb.util.Constants;

import edu.self.utils.AppContext;



public class InfoActivity extends Activity {


	private Info object ;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.mpeople_info);
		
//获取上一个activity传过来的参数值
		object = (Info) getIntent().getSerializableExtra("food");
		
		TextView tv_name = (TextView) this.findViewById(R.id.name);
		tv_name.setText(   object.info_name + ": " + object.info_description ) ;
		 
//		从服务器上获取图片，并且显示
		ImageView iv = (ImageView) this.findViewById(R.id.food_img) ; 
        String picPath = Constants.WEB_APP_URL + "upload/" + object.info_pic  ; 
		AsyncImageLoader asyncImageLoader = new AsyncImageLoader();
		
		Drawable cachedImage = asyncImageLoader.loadDrawable(
    			picPath , iv , new ImageCallback() {

					public void imageLoaded(Drawable imageDrawable,
							ImageView imageView, String imageUrl) {
						imageView.setImageDrawable(imageDrawable);
					}
				});

		if (cachedImage == null) {
			iv.setImageResource(R.drawable.pork);
		} else {
			iv.setImageDrawable(cachedImage);
		}
		
//		给按钮做响应事件
		Button btn = (Button) findViewById(R.id.button1) ;
		
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				HashMap orderMap = new HashMap();  
				
				orderMap.put("order.info_name",  object.getInfo_name()   );	
				orderMap.put("order.user_name",  AppContext.userinfo.getUserName()  );

//				发送订单信息给服务端
				String  result  = HttpApiAccessor.saveOrder(  orderMap )   ;
				finish();
			}
		}) ;
		
		
	}
	
	
}
