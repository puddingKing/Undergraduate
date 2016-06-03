package com.bylw.mpis;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import com.bb.R;

import edu.self.LoginActivity;

public class Main06Activity extends Activity  {
	
	private GridView gridView;
	private String[] titles = new String[]
	{ "江苏省淮安市救助管理站", "江苏省如皋市救助管理站", "江苏省泗阳县救助管理站", };
	private int[] images = new int[]
	{R.drawable.jhj, R.drawable.jrj, R.drawable.jsj};

	
	private Button button1;  //定义一个变量，实现用按钮跳转

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main06);
		
		gridView = (GridView) findViewById(R.id.gridview);
		PictureAdapter adapter = new PictureAdapter(titles, images, this);
		gridView.setAdapter(adapter);

		gridView.setOnItemClickListener(new OnItemClickListener()
		{
			public void onItemClick(AdapterView<?> parent, View v, int position, long id)
			{
				Intent intentAction =null ; 
				Log.i("MainActivity", "Position:" + position);
				switch(position)
				{
				case 0:
					 Uri uri01 = Uri.parse("http://www.zgszrkdak.com/bdxq/show.asp?id=1547");    
					 intentAction = new Intent(Intent.ACTION_VIEW, uri01);     
					break;
				case 1:
					Uri uri02 = Uri.parse("http://www.zgszrkdak.com/bdxq/show.asp?id=2146");    
					intentAction = new Intent(Intent.ACTION_VIEW, uri02);    
					break;
				case 2:
					Uri uri03 = Uri.parse("http://www.zgszrkdak.com/bdxq/show.asp?id=1979");    
					intentAction = new Intent(Intent.ACTION_VIEW, uri03);    
					break;
				
				default:
					break;
				}
				startActivity(intentAction);
			}
		});
	
		
		findViewById(R.id.button_return);
		button1=(Button) findViewById(R.id.button_return);
		button1.setOnClickListener(new OnClickListener()
	    {
		public void onClick(View v)  {
    	    Intent intent=new Intent();
    	    intent.setClass(Main06Activity.this,LoginActivity.class) ; //Context 上下文 
            startActivity(intent);//打开 
		}
	});
		findViewById(R.id.button1);
		button1=(Button) findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener()
	    {
		public void onClick(View v)  {
    	    Intent intent=new Intent();
    	    intent.setClass(Main06Activity.this,LoginActivity.class) ; //Context 上下文 
            startActivity(intent);//打开 
		}
	});
	
		
	}
}

class PictureAdapter extends BaseAdapter
{
	private LayoutInflater inflater;
	private List<Picture> pictures;

	public PictureAdapter(String[] titles, int[] images, Context context)
	{
		super();
		pictures = new ArrayList<Picture>();
		inflater = LayoutInflater.from(context);
		for (int i = 0; i < images.length; i++)
		{
			Picture picture = new Picture(titles[i], images[i]);
			pictures.add(picture);
		}
	}

	@Override
	public int getCount()
	{
		if (null != pictures)
		{
			return pictures.size();
		} else
		{
			return 0;
		}
	}

	@Override
	public Object getItem(int position)
	{
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder;
		if (convertView == null)
		{
			convertView = inflater.inflate(R.layout.activity_main06_01, null);
			viewHolder = new ViewHolder();
			viewHolder.title = (TextView) convertView.findViewById(R.id.title);
			viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
			convertView.setTag(viewHolder);
		} else
		{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.title.setText(pictures.get(position).getTitle());
		viewHolder.image.setImageResource(pictures.get(position).getImageId());
		Log.i("MainActivity", "<<<<<<<" + position);
		return convertView;
	}

}

class ViewHolder
{
	public TextView title;
	public ImageView image;
}

class Picture
{
	private String title;
	private int imageId;

	public Picture()
	{
		super();
	}

	public Picture(String title, int imageId)
	{
		super();
		this.title = title;
		this.imageId = imageId;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public int getImageId()
	{
		return imageId;
	}

	public void setImageId(int imageId)
	{
		this.imageId = imageId;
	}
}
