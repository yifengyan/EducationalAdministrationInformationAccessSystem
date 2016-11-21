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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class xuefen_info extends Activity{
    private String[] semester = new String[]
    		{ "2", "1"};
    private String[] schoolyear = new String[]
    		{ "2012-2013","2011-2012","2010-2011","2009-2010","2008-2009","2007-2008","2006-2007"};
	private String[] zclassName;
	private String[][] kc;
	private Spinner xueqi;
	private Spinner xuenian;
	private ListView list;
	private String xh="";
	private String youryear="2012-2013";
	private String yoursemester="2";
	private String data="";
	private String data1="";
	private String __VIEWSTATE = "";
	DefaultHttpClient client=new DefaultHttpClient();
	private ArrayAdapter<String> adapter1;
	private ArrayAdapter<String> adapter2;
	private ArrayAdapter<String> adapter3;
	private TextView xuefei;
    @Override
    public void onCreate(Bundle savedInstanceState) {
			 super.onCreate(savedInstanceState);
			 
			 setContentView(R.layout.xuefen_info);
			 xueqi=(Spinner)findViewById(R.id.xueqi);
			 xuenian=(Spinner)findViewById(R.id.xuenian);
			 list = (ListView) findViewById(R.id.marklist);
			 xuefei=(TextView)findViewById(R.id.xuefei);
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
                     meclick(view);
		    	 } 
		    	 public void onNothingSelected(AdapterView<?> arg0)
		    	 {
		    	 }});
		     xuenian.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
		    	 public void onItemSelected(AdapterView<?> parent, View view,

		    	 int position, long id) 
		    	 {
                   youryear=schoolyear[position];
                   meclick(view);
		    	 } 
		    	 public void onNothingSelected(AdapterView<?> arg0)
		    	 {
		    	 }});

		     try
		     {
		     data=HttpUtil.getUrl("http://xk1.ujs.edu.cn/xsxkqk.aspx?xh="+xh+"&gnmkdm=N121603",client,"http://xk1.ujs.edu.cn/xs_main.aspx?xh="+xh);
		     ves(data);
		     }
		     catch(IOException e)
		     {
		    	 e.printStackTrace();
		     }
		     list.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		        {
	                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
	                {	
	                	String sended="";
	                	for(int i=1;i<10;++i)
	                	{
	                		String jia="";
	                		switch(i)
	                		{
	                		case 1:jia="课程名称：";break;
	                		case 2:jia="课程性质：";break;
	                		case 3:jia="是否选课：";break;
	                		case 4:jia="教师姓名：";break;
	                		case 5:jia="学分：";break;
	                		case 6:jia="周学时：";break;
	                		case 7:jia="上课时间：";break;
	                		case 8:jia="上课地点：";break;
	                		case 9:jia="教材：";break;
	                		}
	                		sended=sended+jia+kc[pos][i]+"\n";
	                	}
	                	Context context = v.getContext();	
	            			Intent intent3=null;
	            			intent3 = new Intent(context,xuefen_in_detail.class);
	            			Bundle map = new Bundle();
	            			map.putSerializable("sended", sended);
	            			intent3.putExtra("session", map);
	            			context.startActivity(intent3);
	                }
	            });
    }
    public void meclick(View v)
    {
    	List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("__VIEWSTATE", __VIEWSTATE));
        params.add(new BasicNameValuePair("ddlXN", youryear));
        params.add(new BasicNameValuePair("ddlXQ", yoursemester));
        try {
			data1 = HttpUtil.postUrl("http://xk1.ujs.edu.cn/xsxkqk.aspx?xh="+xh+"&gnmkdm=N121603",params, client, "http://xk1.ujs.edu.cn/xsxkqk.aspx?xh="+xh+"&gnmkdm=N121603");
		    
        } catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        data1=data1.replaceAll("&nbsp;", "暂无"); 
        data1=data1.replaceAll("</a>", "");
        kc=str(data1,10);
        zclassName=new String[kc.length];
        double fen=0;
        for(int i=0;i<kc.length;++i)
        {
        	zclassName[i]=kc[i][1];
        	fen+=Double.parseDouble(kc[i][5]) ;
        }
		adapter3=new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_list_item_1,zclassName);	        
        list.setAdapter(adapter3);
        xuefei.setText("本学期学费共"+fen+"个学分，学费为"+(fen*60)+"元");
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
	       		 mydetails[i][j]=arr2[2*j+1].replaceAll("<a.*\">", "");
	       	 }
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
}
