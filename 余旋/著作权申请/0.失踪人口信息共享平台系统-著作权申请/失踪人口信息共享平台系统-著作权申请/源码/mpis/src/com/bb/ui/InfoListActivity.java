package com.bb.ui;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bb.R;
import com.bb.api.HttpApiAccessor;
import com.bb.model.Info;
import com.bb.util.AsyncImageLoader;
import com.bb.util.AsyncImageLoader.ImageCallback;
import com.bb.util.Constants;


/**
 * 列表activity
 * @author Administrator
 *
 */
public class InfoListActivity  extends  ListActivity {

//	刷新菜单键值
    private static final int MENU_REFRESH = Menu.FIRST + 2; 
    
    public static final int COMPOSE_UPDATE_REQUEST_CODE = 1339;

    private FoodsAdapter adapter = null;
    
     
    private ArrayList<Info> foodsList;

    private String type;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = (String) getIntent().getExtras().get("type");        
        setContentView(R.layout.mpeople_list);  
        
//        加载数据
        new LoadTask().execute();     
    }
    
//    继承自android的AsyncTask异步类
    public class LoadTask extends AsyncTask<Void, Void, Void>{
		
		/**
		 * 后台运行，加载数据
		 */
		protected Void doInBackground(Void... arg0) {
			try {
				foodsList =  HttpApiAccessor.getFollowed(-1, -1,type) ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		/**
		 * 在执行时调用，将适配器类传入
		 */
		protected void onPostExecute(Void result) {
			adapter = new FoodsAdapter() ;
			setListAdapter(adapter);
			removeDialog(0);
			super.onPostExecute(result);
		}
		
		protected void onPreExecute() {
			showDialog(0);
			super.onPreExecute();
		}
	}

    
    /**
     *  点击每一行时跳转到FoodInfoActivity
     */
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.i( " onListItemClick "  , "============= foods list ================" );
		Intent intent = new Intent( InfoListActivity.this, InfoActivity.class);
		intent.putExtra("food", foodsList.get(position));
		startActivity(intent);
	}
	
    
/**
 * 适配器类
 * @author Administrator
 *
 */ 
	public class FoodsAdapter extends BaseAdapter {

		private AsyncImageLoader asyncImageLoader;
		
		public int getCount() {
			return foodsList.size();
		}

		
		public Object getItem(int arg0) {
			return foodsList.get(arg0);
		}

		
		public long getItemId(int position) {
			return position;
		}

/**
 * 		将每一行设置为foods_list_row中定义的格式，并且赋值
 */
		public View getView(int position, View convertView, ViewGroup parent) {
		
			asyncImageLoader = new AsyncImageLoader();
			 
			convertView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.mpeople_list_row, null);
 
		    TextView name, todayPrice , description , price ;
		    
		    Info u = foodsList.get(position); 
	        name = (TextView) convertView.findViewById(R.id.name) ; 
	        name.setText(u.info_name);
	        
	        todayPrice = (TextView) convertView.findViewById(R.id.price);
	        todayPrice.setText( "联系人:" + u.info_people  + " 编号:" + String.valueOf(u.info_pphone)  );
	        
	        ImageView iv = (ImageView) convertView.findViewById(R.id.foodPic) ; 
	        String picPath = Constants.WEB_APP_URL + "upload/" + u.info_pic  ;
	        
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
			return convertView;
		}
	}
	
	
//    创建menu菜单
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu); 
        menu.add(0, MENU_REFRESH, 0, "刷新")
                .setAlphabeticShortcut('R'); 
        return true;
    }
    
//响应menu操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_REFRESH: {
            	new LoadTask().execute();
                return true; 
            }
        }
        return super.onOptionsItemSelected(item);
    }
    
}
