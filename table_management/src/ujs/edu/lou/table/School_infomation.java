package ujs.edu.lou.table;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.app.TabActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class School_infomation extends TabActivity{ 
	  DefaultHttpClient client =new DefaultHttpClient();
	  private TabHost tabhost;
	  private String data="";
	  private String[] xinxi;
	  private String[] xueshu;
      private String[] zhaopin;
      private String[] tuanxue;
      private String[] xinxiurl;
      private String[] zhaopinurl;
      private String[] tuanxueurl;
      private String[] xueshuurl;
      private String[] arr1;
      private String[] arr2;
      private String[] arr3;
      private String[] arr4;  
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
			 super.onCreate(savedInstanceState);
			 setContentView(R.layout.school_info);
			 tabhost=getTabHost();
			 tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("信息公告", getResources().getDrawable(R.drawable.xinxigonggao)).setContent(R.id.list1));
			 tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("学术报告", getResources().getDrawable(R.drawable.xueshujiangzuo)).setContent(R.id.list2));
			 tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("招聘信息", getResources().getDrawable(R.drawable.jiuyezhaoping)).setContent(R.id.list3));
			 tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator("团学信息", getResources().getDrawable(R.drawable.tuanxuehuodong)).setContent(R.id.list4));
			 tabhost.setCurrentTab(0);			    
		        arr1=getinfo("http://ww.ujs.edu.cn/pub/xiaonei/xngg/");        
	            xinxi=wenzi(arr1[6]);
	            xinxiurl=dizhi(arr1[6]);
	            arr2=getinfo("http://ww.ujs.edu.cn/pub/xiaonei/xueshu/");       
	            xueshu=wenzi(arr2[6]);
	            xueshuurl=dizhi(arr2[6]);
	            arr3=getinfo("http://ww.ujs.edu.cn/pub/xiaonei/jiuye/zpjz/");       
	            zhaopin=wenzi(arr3[6]);
	            zhaopinurl=dizhi(arr3[6]);
	            arr4=getinfo("http://ww.ujs.edu.cn/pub/xiaonei/sthd/");       
	            tuanxue=wenzi(arr4[6]);
	            tuanxueurl=dizhi(arr4[6]);        	 
			    ListView list1 = (ListView) findViewById(R.id.list1);
		        ArrayAdapter<String> aaData1 = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, xinxi);
		        list1.setAdapter(aaData1);		        
				 ListView list2 = (ListView) findViewById(R.id.list2);
			     ArrayAdapter<String> aaData2 = new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1, xueshu);
			    list2.setAdapter(aaData2);		    
			    ListView list3 = (ListView) findViewById(R.id.list3);
			     ArrayAdapter<String> aaData3 = new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1, zhaopin);
			    list3.setAdapter(aaData3);			    
			    ListView list4 = (ListView) findViewById(R.id.list4);
			     ArrayAdapter<String> aaData4 = new ArrayAdapter<String>(this,
							android.R.layout.simple_list_item_1, tuanxue);
			    list4.setAdapter(aaData4);		    
			    list1.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		        {
	                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
	                {
	       		     Intent intent= new Intent();        
	     		    intent.setAction("android.intent.action.VIEW");    
	     		    Uri content_url = Uri.parse(xinxiurl[pos]);   
	     		    intent.setData(content_url);  
	     		    startActivity(intent);		    
	                }
	            });
			    list2.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		        {
	                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
	                {
	       		     Intent intent= new Intent();        
	     		    intent.setAction("android.intent.action.VIEW");    
	     		    Uri content_url = Uri.parse(xueshuurl[pos]);   
	     		    intent.setData(content_url);  
	     		    startActivity(intent);		    
	                }
	            });
			    list3.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		        {
	                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
	                {
	       		     Intent intent= new Intent();        
	     		    intent.setAction("android.intent.action.VIEW");    
	     		    Uri content_url = Uri.parse(zhaopinurl[pos]);   
	     		    intent.setData(content_url);  
	     		    startActivity(intent);		   
	                }
	            });
			    list4.setOnItemClickListener(new AdapterView.OnItemClickListener() 
		        {
	                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
	                {
	       		     Intent intent= new Intent();        
	     		    intent.setAction("android.intent.action.VIEW");    
	     		    Uri content_url = Uri.parse(tuanxueurl[pos]);   
	     		    intent.setData(content_url);  
	     		    startActivity(intent);		  
	                }
	            });
		    }  
  //获取网页文字
	 public String[] wenzi(String arr)
	 {
		 String reg2="<TD.*\">";
          String[] arr2=arr.split(reg2);
          String[] arr3=new String[arr2.length-1];
           for(int i=0;i<arr3.length;++i)
          {
     	   arr3[i]=arr2[i+1].replaceAll("<.*", "").replaceAll(" ", "").replaceAll("\n", "");
          }
           return arr3;
	 }
	 //获取网页地址
	 public String[] dizhi(String arr)
	 {
		 String regex = "\"http:([^\"]*)\"";
          Pattern p = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
          Matcher m = p.matcher(arr);
          String[] arr4=new String[20];
          int j=0;
          while(m.find()){
     	    arr4[j++]=m.group(0).replaceAll("\"", "");
     	   }
          return arr4;
	 }
	//获取网页信息
	 public String[] getinfo(String url)
	 {
		 HttpGet get = new HttpGet(url);
	        HttpResponse response;
			try {
				response = client.execute(get);
				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) 
				{
		            data = EntityUtils.toString(response.getEntity());
		        } 
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}               
	        String reg1="</*table.*>";
         String[] arr=data.split(reg1);
		 return arr;
	 }
}
