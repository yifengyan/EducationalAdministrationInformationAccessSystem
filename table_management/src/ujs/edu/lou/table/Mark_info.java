package ujs.edu.lou.table;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ListView;
import android.view.View.OnClickListener;

public class Mark_info extends Activity{
    private String[] semester = new String[]
    		{ "1", "2", "3"};
    private String[] schoolyear = new String[]
    		{ "2012-2013","2011-2012","2010-2011","2009-2010","2008-2009","2007-2008","2006-2007"};
	private String[] zclassName;
	private String[][] chengji;
	private String[][] chengji2;
	private Spinner xueqi;
	private Spinner xuenian;
	private Button yearButton;
	private Button semesterButton;
	private Button allscoreButton;
	private ListView list;
	private String xh="";
	private String youryear="2012-2013";
	private String yoursemester="1";
	private String data="";
	private String data1="";
	private String __VIEWSTATE = "";
	DefaultHttpClient client=new DefaultHttpClient();
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
			 super.onCreate(savedInstanceState);		 
			 setContentView(R.layout.mark_info);
			 xueqi=(Spinner)findViewById(R.id.xueqi);
			 xuenian=(Spinner)findViewById(R.id.xuenian);
			 yearButton=(Button)findViewById(R.id.School_year);
			 semesterButton=(Button)findViewById(R.id.Semester);
			 allscoreButton=(Button)findViewById(R.id.All_scores);
			 list = (ListView) findViewById(R.id.marklist);
			 Intent get = getIntent();
		     xh = get.getBundleExtra("session").getSerializable("xh").toString();
		     adapter1=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,semester);
		     adapter2=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,schoolyear);
		     xueqi.setAdapter(adapter1);
		     xuenian.setAdapter(adapter2);
		     xueqi.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
		    	 public void onItemSelected(AdapterView<?> parent, View view,
		    	 int position, long id) 
		    	 {
                     yoursemester=semester[position];
		    	 } 
		    	 public void onNothingSelected(AdapterView<?> arg0)
		    	 {
		    	 }});
		     xuenian.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
		    	 public void onItemSelected(AdapterView<?> parent, View view,
		    	 int position, long id) 
		    	 {
                   youryear=schoolyear[position];
		    	 } 
		    	 public void onNothingSelected(AdapterView<?> arg0)
		    	 {
		    	 }});
		     yearButton.setOnClickListener(new yearBtnListener());
		     semesterButton.setOnClickListener(new semesterBtnListener());
		     allscoreButton.setOnClickListener(new allscoreBtnListener());		     	     
		     try
		     {
		     data=HttpUtil.getUrl("http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605",client,"http://xk1.ujs.edu.cn/xs_main.aspx?xh="+xh);
		     ves(data);
		     }
		     catch(IOException e)
		     {
		    	 e.printStackTrace();
		     }
    }	
	public String getValue(String valueToken, String startString,
			String endString, int unStart) {
		int start = valueToken.indexOf(startString);
		int end = valueToken.length();
		String tempStr = valueToken.substring(start + unStart, end);
		end = tempStr.indexOf(endString, unStart);
		if (end == -1) {
			end = tempStr.length();
		}
		return tempStr.substring(0, end);
	}
	public String[][] str(String st,int num)
	   {
		   String reg1="</*tr.*>";
	       String[] arr=st.split(reg1);
	       String[][] mydetails=new String[arr.length-3][num];
	       String reg2="</?td>";
	       for(int i=0;i<arr.length-3;++i)
	       {
	       	 String[] arr2=arr[i+2].split(reg2);
	       	 for(int j=0;j<num;++j)
	       	 {
	       		 mydetails[i][j]=arr2[2*j+1];
	       	 }
	       }
	       for(int i=0;i<arr.length-3;++i)
	       {
	       	 for(int j=0;j<num;++j)
	       	 {
	       		 System.out.print(mydetails[i][j]+" ");
	       	 }
	       	 System.out.println();
	       }
		   return mydetails;
	   }
	public void ves(String data)
	{
		StringTokenizer tokenizer = new StringTokenizer(
				data);
		while (tokenizer.hasMoreTokens()) {
			String valueToken = tokenizer
					.nextToken();
			if ((valueToken.indexOf("value")!=-1)
					&& valueToken.length() > 100) {
				if (getValue(
						valueToken,
						"value", "\"", 7)
						.length() > 100) {
					__VIEWSTATE = getValue(
									valueToken,
									"value",
									"\"", 7);
				}
			}
		}
	}
	class yearBtnListener implements OnClickListener,OnItemClickListener{
		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(final View v) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
	        params.add(new BasicNameValuePair("ddlXN", youryear));
	        params.add(new BasicNameValuePair("Button5", "%B0%B4%D1%A7%C4%EA%B2%E9%D1%AF"));
	        try {
				data1 = HttpUtil.postUrl("http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605",params, client, "http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605");		    
	        } catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        data1=data1.replaceAll("&nbsp;", "暂无");
            String reg1="</*table.*>";
            String[] arr=data1.split(reg1);
            chengji=str(arr[1],15);
            chengji2=str(arr[arr.length-2],6);           
        	zclassName=new String[chengji.length+chengji2.length+2];
        	zclassName[0]="已通过科目：";
			for(int i=0;i<chengji.length;++i)
			{
				zclassName[i+1]=chengji[i][3]+"成绩:"+chengji[i][8];
			}
			zclassName[chengji.length+1]="未通过科目：";
			for(int i=0;i<chengji2.length;++i)
			{
				zclassName[i+chengji.length+2]=chengji2[i][1]+"成绩："+chengji2[i][4];
			}
			adapter3=new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,zclassName);	        
	        list.setAdapter(adapter3);
	        list.setOnItemClickListener(this);
		}
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String sended="";
			if(arg2>0&&arg2<(chengji.length+1))
			{				
	    	for(int i=0;i<15;++i)
	    	{
	    		String jia="";
	    		switch(i)
	    		{
	    		case 0:jia="学年：";break;
	    		case 1:jia="学期：";break;
	    		case 2:jia="课程代码：";break;
	    		case 3:jia="课程名称：";break;
	    		case 4:jia="课程性质：";break;
	    		case 5:jia="课程归属：";break;
	    		case 6:jia="学分：";break;
	    		case 7:jia="绩点：";break;
	    		case 8:jia="成绩：";break;
	    		case 9:jia="辅修标记：";break;
	    		case 10:jia="补考成绩：";break;
	    		case 11:jia="重修成绩：";break;
	    		case 12:jia="学院：";break;
	    		case 13:jia="备注：";break;
	    		case 14:jia="重修标记：";break;
	    		}
	    		sended=sended+jia+chengji[arg2-1][i]+"\n";
	    	}
	    	Context context = arg1.getContext();	
			Intent intent3=null;
			intent3 = new Intent(context,Mark_in_detail.class);
			Bundle map = new Bundle();
			map.putSerializable("sended", sended);
			intent3.putExtra("session", map);
			context.startActivity(intent3);
			}
			else if(arg2>(chengji.length+1))
			{
				for(int i=0;i<6;++i)
		    	{
					String jia="";
		    		switch(i)
		    		{
		    		case 0:jia="课程代码：";break;
		    		case 1:jia="课程名称：";break;
		    		case 2:jia="学分：";break;
		    		case 3:jia="课程性质：";break;
		    		case 4:jia="最高成绩：";break;
		    		case 5:jia="课程归属：";break;
		    		}
		    		sended=sended+jia+chengji2[arg2-chengji.length-2][i]+"\n";	
		    	}
				Context context = arg1.getContext();	
				Intent intent3=null;
				intent3 = new Intent(context,Mark_in_detail.class);
				Bundle map = new Bundle();
				map.putSerializable("sended", sended);
				intent3.putExtra("session", map);
				context.startActivity(intent3);
			}
		}
    }
	class allscoreBtnListener implements OnClickListener,OnItemClickListener{

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(final View v) {

			List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
	        params.add(new BasicNameValuePair("Button2", "%D4%DA%D0%A3%D1%A7%CF%B0%B3%C9%BC%A8%B2%E9%D1%AF"));
	        try {
				data1 = HttpUtil.postUrl("http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605",params, client, "http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605");		    
	        } catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        data1=data1.replaceAll("&nbsp;", "暂无");
            String reg1="</*table.*>";
            String[] arr=data1.split(reg1);
            chengji=str(arr[1],15);
            chengji2=str(arr[arr.length-2],6);
            
        	zclassName=new String[chengji.length+chengji2.length+2];
        	zclassName[0]="已通过科目：";
			for(int i=0;i<chengji.length;++i)
			{
				zclassName[i+1]=chengji[i][3]+"成绩:"+chengji[i][8];
			}
			zclassName[chengji.length+1]="未通过科目：";
			for(int i=0;i<chengji2.length;++i)
			{
				zclassName[i+chengji.length+2]=chengji2[i][1]+"成绩："+chengji2[i][4];
			}
			adapter3=new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,zclassName);	        
	        list.setAdapter(adapter3);
	        list.setOnItemClickListener(this);	
		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			String sended="";
			if(arg2>0&&arg2<(chengji.length+1))
			{
	    	for(int i=0;i<15;++i)
	    	{
	    		String jia="";
	    		switch(i)
	    		{
	    		case 0:jia="学年：";break;
	    		case 1:jia="学期：";break;
	    		case 2:jia="课程代码：";break;
	    		case 3:jia="课程名称：";break;
	    		case 4:jia="课程性质：";break;
	    		case 5:jia="课程归属：";break;
	    		case 6:jia="学分：";break;
	    		case 7:jia="绩点：";break;
	    		case 8:jia="成绩：";break;
	    		case 9:jia="辅修标记：";break;
	    		case 10:jia="补考成绩：";break;
	    		case 11:jia="重修成绩：";break;
	    		case 12:jia="学院：";break;
	    		case 13:jia="备注：";break;
	    		case 14:jia="重修标记：";break;
	    		}
	    		sended=sended+jia+chengji[arg2-1][i]+"\n";
	    	}
	    	Context context = arg1.getContext();	
				Intent intent3=null;
				intent3 = new Intent(context,Mark_in_detail.class);
				Bundle map = new Bundle();
				map.putSerializable("sended", sended);
				intent3.putExtra("session", map);
				context.startActivity(intent3);
			}
			else if(arg2>(chengji.length+1))
			{
				for(int i=0;i<6;++i)
		    	{
					String jia="";
		    		switch(i)
		    		{
		    		case 0:jia="课程代码：";break;
		    		case 1:jia="课程名称：";break;
		    		case 2:jia="学分：";break;
		    		case 3:jia="课程性质：";break;
		    		case 4:jia="最高成绩：";break;
		    		case 5:jia="课程归属：";break;
		    		}
		    		sended=sended+jia+chengji2[arg2-chengji.length-2][i]+"\n";	
		    	}
				Context context = arg1.getContext();	
					Intent intent3=null;
					intent3 = new Intent(context,Mark_in_detail.class);
					Bundle map = new Bundle();
					map.putSerializable("sended", sended);
					intent3.putExtra("session", map);
					context.startActivity(intent3);
			}
		}
    }
	class semesterBtnListener implements OnClickListener,OnItemClickListener{

		/* (non-Javadoc)
		 * @see android.view.View.OnClickListener#onClick(android.view.View)
		 */
		public void onClick(final View v) {
					List<NameValuePair> params = new ArrayList<NameValuePair>();
			        params.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
			        params.add(new BasicNameValuePair("ddlXN", youryear));
			        params.add(new BasicNameValuePair("ddlXQ", yoursemester));
			        params.add(new BasicNameValuePair("Button1", "%B0%B4%D1%A7%C6%DA%B2%E9%D1%AF"));
			        try {
						data1 = HttpUtil.postUrl("http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605",params, client, "http://xk1.ujs.edu.cn/xscj_gc.aspx?xh="+xh+"&gnmkdm=N121605");				    
			        } catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			        data1=data1.replaceAll("&nbsp;", "暂无");
		            String reg1="</*table.*>";
		            String[] arr=data1.split(reg1);
		            chengji=str(arr[1],15);
		            chengji2=str(arr[arr.length-2],6);
		            
		        	zclassName=new String[chengji.length+chengji2.length+2];
		        	zclassName[0]="已通过科目：";
					for(int i=0;i<chengji.length;++i)
					{
						zclassName[i+1]=chengji[i][3]+"成绩:"+chengji[i][8];
					}
					zclassName[chengji.length+1]="未通过科目：";
					for(int i=0;i<chengji2.length;++i)
					{
						zclassName[i+chengji.length+2]=chengji2[i][1]+"成绩："+chengji2[i][4];
					}
					adapter3=new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,zclassName);	        
			        list.setAdapter(adapter3);
			        list.setOnItemClickListener(this);
		}

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			String sended="";
			if(arg2>0&&arg2<(chengji.length+1))
			{
	    	for(int i=0;i<15;++i)
	    	{
	    		String jia="";
	    		switch(i)
	    		{
	    		case 0:jia="学年：";break;
	    		case 1:jia="学期：";break;
	    		case 2:jia="课程代码：";break;
	    		case 3:jia="课程名称：";break;
	    		case 4:jia="课程性质：";break;
	    		case 5:jia="课程归属：";break;
	    		case 6:jia="学分：";break;
	    		case 7:jia="绩点：";break;
	    		case 8:jia="成绩：";break;
	    		case 9:jia="辅修标记：";break;
	    		case 10:jia="补考成绩：";break;
	    		case 11:jia="重修成绩：";break;
	    		case 12:jia="学院：";break;
	    		case 13:jia="备注：";break;
	    		case 14:jia="重修标记：";break;
	    		}
	    		sended=sended+jia+chengji[arg2-1][i]+"\n";
	    	}
	    	Context context = arg1.getContext();	
				Intent intent3=null;
				intent3 = new Intent(context,Mark_in_detail.class);
				Bundle map = new Bundle();
				map.putSerializable("sended", sended);
				intent3.putExtra("session", map);
				context.startActivity(intent3);
			}
			else if(arg2>(chengji.length+1))
			{
				for(int i=0;i<6;++i)
		    	{
					String jia="";
		    		switch(i)
		    		{
		    		case 0:jia="课程代码：";break;
		    		case 1:jia="课程名称：";break;
		    		case 2:jia="学分：";break;
		    		case 3:jia="课程性质：";break;
		    		case 4:jia="最高成绩：";break;
		    		case 5:jia="课程归属：";break;
		    		}
		    			sended=sended+jia+chengji2[arg2-chengji.length-2][i]+"\n";	
		    	}
				    Context context = arg1.getContext();	
					Intent intent3=null;
					intent3 = new Intent(context,Mark_in_detail.class);
					Bundle map = new Bundle();
					map.putSerializable("sended", sended);
					intent3.putExtra("session", map);
					context.startActivity(intent3);
			}    			
		}
    }
}
