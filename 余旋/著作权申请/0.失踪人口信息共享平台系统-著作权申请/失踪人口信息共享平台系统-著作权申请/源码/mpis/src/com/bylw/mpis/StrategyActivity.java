package com.bylw.mpis;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;

import com.bb.R;

import edu.self.LoginActivity;

public class StrategyActivity extends Activity  {
	
	private Button button1;  //定义一个变量，实现用按钮跳转

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_strategy);
		
		
		findViewById(R.id.button_return);
		button1=(Button) findViewById(R.id.button_return);
		button1.setOnClickListener(new OnClickListener()
	    {
		public void onClick(View v)  {
    	    Intent intent=new Intent();
    	    intent.setClass(StrategyActivity.this,LoginActivity.class) ; //Context 上下文 
            startActivity(intent);//打开 
		}
	});
		findViewById(R.id.button1);
		button1=(Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener()
	    {
		public void onClick(View v)  {
    	    Intent intent=new Intent();
    	    intent.setClass(StrategyActivity.this,LoginActivity.class) ; //Context 上下文 
            startActivity(intent);//打开 
		}
	});
		
	}
}
