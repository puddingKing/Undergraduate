package com.bylw.mpis;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler; 
import com.bb.R;


public class Star01Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_star01);
		getWindow().setBackgroundDrawableResource(R.drawable.star);
        
		  //延迟2秒后执行run方法中的页面跳转  
        new Handler().postDelayed(new Runnable() {  
  
            @Override  
            public void run() {  
                Intent intent = new Intent(Star01Activity.this, Star02Activity.class);  
                startActivity(intent);  
                Star01Activity.this.finish();  
            }  
        }, 2000);
	}
}