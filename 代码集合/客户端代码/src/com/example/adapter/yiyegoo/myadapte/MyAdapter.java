package com.example.adapter.yiyegoo.myadapte;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adapter.yiyego.cache.CacheFile;
import com.example.adapter.yiyego.cache.Cachee;
import com.example.adapter.yiyego.image.StreamTool;
import com.example.flower.R;

/**
 * 
 * @���� �Զ�����������������Ʒչʾ ��������ʵ��ͼƬ��ʾ
 */
public class MyAdapter extends BaseAdapter {
	private CacheFile cachefile = new CacheFile();                 //�ļ�����
	private Cachee cachee = new Cachee();						   //�ڴ滺��
	private List<Map<String, String>> data;                        //����Դ
	private LayoutInflater layoutInflater;
	private Context context;
	private Bitmap sdcardbitmap;


	static class MyList { // �Զ���ؼ�����
		public ImageView image;
		public TextView name;
		public TextView price;

	}

	public MyAdapter(Context context, List<Map<String, String>> mymap) {
		this.context = context;
		this.data = (List<Map<String, String>>) mymap;
		this.layoutInflater = LayoutInflater.from(context);

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		MyList mylist = null;
		if (convertView == null) {

			mylist = new MyList();
			convertView = layoutInflater.inflate(R.layout.all_goods, null);
			mylist.image = (ImageView) convertView.findViewById(R.id.image);
			mylist.name = (TextView) convertView.findViewById(R.id.name);
			mylist.price = (TextView) convertView.findViewById(R.id.price);

			// ���ÿؼ�����convertView
			
			convertView.setTag(mylist);

		} else {
			mylist = (MyList) convertView.getTag();

		}
		mylist.name.setText((String) data.get(position).get("name"));

		mylist.price.setText("��"+(String) data.get(position).get("price")+".00");
	
		String url = (String) data.get(position).get("imageurl");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		 if((sdcardbitmap=cachee.get(url))!=null)               //�ж��ڴ����Ƿ��л���
		 {
			 sdcardbitmap= cachee.get(url);
			 System.out.println("--------------ʹ���ڴ滺��----------------");
		 }
		 else if((sdcardbitmap=cachefile.getFile(url))!=null)   //�ж�sd�����Ƿ��л���
		{
			mylist.image.setImageBitmap(sdcardbitmap);
			System.out.println("--------------ʹ��sd������----------------");
		}
		else{
		mylist.image.setImageResource(R.drawable.flower);
		mylist.image.setTag(position);
		map.put("url", url);
		map.put("tag", position);
		map.put("object", convertView);
		new An().execute(map);
		}
		return convertView;

	}

	class An extends AsyncTask<Map<String, Object>, Void, Bitmap> {

		View myl;
		String url;
		int i;

		@Override
				protected Bitmap doInBackground(Map<String, Object>... params) {
					// TODO Auto-generated method stub
					Bitmap img;
				
					 url = (String) params[0].get("url");
					 myl =  (View) params[0].get("object");
					 i = (Integer) params[0].get("tag");					 
					 img= StreamTool.returnBitmap(url);
					 cachee.put(url, img);
					 try {
						new CacheFile().saveMyBitmap(url, img);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 return img;      			                   
				}

		@Override
		protected void onPostExecute(Bitmap result) {
			// TODO Auto-generated method stub
			System.out.println("??????????????????????????????");

			((ImageView) myl.findViewWithTag(i)).setImageBitmap(result);

		}
	}
}