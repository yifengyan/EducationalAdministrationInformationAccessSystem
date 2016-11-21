package ujs.edu.lou.table;

import java.io.IOException;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

public class kb_info extends TabActivity{
	DefaultHttpClient client =new DefaultHttpClient();
	private TabHost tabhost;
	private String xh="";
    String[][] title1=new String[5][];
    String[][] title2=new String[5][];
    String[][] title3=new String[5][];
    String[][] title4=new String[5][];
    String[][] title5=new String[5][];	            
    String[][] week1=new String[5][];
    String[][] week2=new String[5][];
    String[][] week3=new String[5][];
    String[][] week4=new String[5][];
    String[][] week5=new String[5][]; 
    String[] listweek1=new String[5];
    String[] listweek2=new String[5];
    String[] listweek3=new String[5];
    String[] listweek4=new String[5];
    String[] listweek5=new String[5];
    String data="";	
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 setContentView(R.layout.kbcx);
		 tabhost=getTabHost();
		 tabhost.addTab(tabhost.newTabSpec("tab1").setIndicator("周一", getResources().getDrawable(R.drawable.monday)).setContent(R.id.week1));
		 tabhost.addTab(tabhost.newTabSpec("tab2").setIndicator("周二", getResources().getDrawable(R.drawable.tuesday)).setContent(R.id.week2));
		 tabhost.addTab(tabhost.newTabSpec("tab3").setIndicator("周三", getResources().getDrawable(R.drawable.wednesday)).setContent(R.id.week3));
		 tabhost.addTab(tabhost.newTabSpec("tab4").setIndicator("周四", getResources().getDrawable(R.drawable.thursday)).setContent(R.id.week4));
		 tabhost.addTab(tabhost.newTabSpec("tab5").setIndicator("周五", getResources().getDrawable(R.drawable.friday)).setContent(R.id.week5));
		 tabhost.setCurrentTab(0);	
		 Intent get = getIntent();
	     xh = get.getBundleExtra("session").getSerializable("xh").toString();
	            try {
					data= HttpUtil.getUrl("http://xk1.ujs.edu.cn/xskbcx.aspx?xh="+xh+"&gnmkdm=N121602",client,"http://xk1.ujs.edu.cn/xs_main.aspx?xh="+xh);
				} catch (IOException e) {
					e.printStackTrace();
				}
	            String reg1="</*table.*>";
	            String[] arr=data.split(reg1);  		            
	            String reg11="</*tr.*>";
	            String[] arr1=arr[1].split(reg11);
	            String reg2="</?td>";  	          
     		    String regt="<br>";
	            for(int i=3;i<=11;i+=2)
	            {
	            	String[] t;  
	            	String[][] ztitle=new String[5][];
	            	String[] title=new String[5];
	            	t=arr1[i].split(reg2);
	            	int jia=0;
	            	if(i==7||i==11)
	            		jia=1;
	            	for(int j=2+jia;j<=6+jia;++j)
	            	{
	            		title=t[j].split(regt);
	            		title[0]=title[0].replaceAll("<.*>", "");
	            		title[0]=title[0].replaceAll("&nbsp;","暂无");
	            		ztitle[j-jia-2]=title;
	            	}
	            	switch(i)
	            	{
	            	case 3:title1=ztitle;break;
	            	case 5:title2=ztitle;break;
	            	case 7:title3=ztitle;break;
	            	case 9:title4=ztitle;break;
	            	case 11:title5=ztitle;break;
	            	}
	            }           
	            for(int i=0;i<5;++i)
	            {
	            	    String[][] week=new String[5][];
	            		week[0]=title1[i];
	            		week[1]=title2[i];
	            		week[2]=title3[i];
	            		week[3]=title4[i];
	            		week[4]=title5[i];
	            		switch(i)
	            		{
	            		case 0:week1=week;break;
	            		case 1:week2=week;break;
	            		case 2:week3=week;break;
	            		case 3:week4=week;break;
	            		case 4:week5=week;break;
	            		}
	            }
	            for(int i=0;i<5;++i)
	            {
	            	String[][] week=new String[5][];
	            	String[] listweek=new String[5];
	            	switch(i)
         		{
         		case 0:week=week1;break;
         		case 1:week=week2;break;
         		case 2:week=week3;break;
         		case 3:week=week4;break;
         		case 4:week=week5;break;
         		}
	            	for(int j=0;j<5;++j)
	            	{ 
	            		String jie="";
	            		switch(j)
	            		{
	            		case 0:jie="第1-2节课：";break;
	            		case 1:jie="第3-4节课：";break;
	            		case 2:jie="第5-6节课：";break;
	            		case 3:jie="第7-8节课：";break;
	            		case 4:jie="第9-11节课：";break;
	            		}
	            		listweek[j]=jie+week[j][0];
	            	}
	            	switch(i)
	         		{
	         		case 0:listweek1=listweek;break;
            		case 1:listweek2=listweek;break;
            		case 2:listweek3=listweek;break;
            		case 3:listweek4=listweek;break;
            		case 4:listweek5=listweek;break;
	         		}
	            }                		               		            	          		 
		    ListView list1 = (ListView) findViewById(R.id.week1);
	        ArrayAdapter<String> aaData1 = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, listweek1);
	        list1.setAdapter(aaData1);        
			 ListView list2 = (ListView) findViewById(R.id.week2);
		     ArrayAdapter<String> aaData2 = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, listweek2);
		    list2.setAdapter(aaData2);		    
		    ListView list3 = (ListView) findViewById(R.id.week3);
		     ArrayAdapter<String> aaData3 = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, listweek3);
		    list3.setAdapter(aaData3);	    
		    ListView list4 = (ListView) findViewById(R.id.week4);
		     ArrayAdapter<String> aaData4 = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, listweek4);
		    list4.setAdapter(aaData4);	    
		    ListView list5 = (ListView) findViewById(R.id.week5);
		     ArrayAdapter<String> aaData5 = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, listweek5);
		    list5.setAdapter(aaData5);	    
		    list1.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	        {
                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
                {	
                	String sended="";
                	for(int i=0;i<week1[pos].length;++i)
                	{
                		String jia="";
                		switch(i)
                		{
                		case 0:jia="课程名称：";break;
                		case 1:jia="课程类型：";break;
                		case 2:jia="上课时间：";break;
                		case 3:jia="任课教师：";break;
                		case 4:jia="上课地点：";break;
                		case 5:jia="考试时间：";break;
                		case 6:jia="考试地点：";break;
                		}
                		sended=sended+jia+week1[pos][i]+"\n";
                	}
                	Context context = v.getContext();	
            		Intent intent3=null;
            		intent3 = new Intent(context,kb_details.class);
            		Bundle map = new Bundle();
            		map.putSerializable("sended", sended);
            		intent3.putExtra("session", map);
            		context.startActivity(intent3);
                }
            });
		    list2.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	        {
                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
                {	
                	String sended="";
                	for(int i=0;i<week2[pos].length;++i)
                	{
                		String jia="";
                		switch(i)
                		{
                		case 0:jia="课程名称：";break;
                		case 1:jia="课程类型：";break;
                		case 2:jia="上课时间：";break;
                		case 3:jia="任课教师：";break;
                		case 4:jia="上课地点：";break;
                		case 5:jia="考试时间：";break;
                		case 6:jia="考试地点：";break;
                		}
                		sended=sended+jia+week2[pos][i]+"\n";
                	}
                	Context context = v.getContext();	
            		Intent intent3=null;
            		intent3 = new Intent(context,kb_details.class);
            		Bundle map = new Bundle();
            		map.putSerializable("sended", sended);
            		intent3.putExtra("session", map);
            		context.startActivity(intent3);
                }
            });
		    list3.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	        {
                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
                {	
                	String sended="";
                	for(int i=0;i<week3[pos].length;++i)
                	{
                		String jia="";
                		switch(i)
                		{
                		case 0:jia="课程名称：";break;
                		case 1:jia="课程类型：";break;
                		case 2:jia="上课时间：";break;
                		case 3:jia="任课教师：";break;
                		case 4:jia="上课地点：";break;
                		case 5:jia="考试时间：";break;
                		case 6:jia="考试地点：";break;
                		}
                		sended=sended+jia+week3[pos][i]+"\n";
                	}
                	Context context = v.getContext();	
            		Intent intent3=null;
            		intent3 = new Intent(context,kb_details.class);
            		Bundle map = new Bundle();
            		map.putSerializable("sended", sended);
            		intent3.putExtra("session", map);
            		context.startActivity(intent3);
                }
            });
		    list4.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	        {
                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
                {	
                	String sended="";
                	for(int i=0;i<week4[pos].length;++i)
                	{
                		String jia="";
                		switch(i)
                		{
                		case 0:jia="课程名称：";break;
                		case 1:jia="课程类型：";break;
                		case 2:jia="上课时间：";break;
                		case 3:jia="任课教师：";break;
                		case 4:jia="上课地点：";break;
                		case 5:jia="考试时间：";break;
                		case 6:jia="考试地点：";break;
                		}
                		sended=sended+jia+week4[pos][i]+"\n";
                	}
                	Context context = v.getContext();	
            		Intent intent3=null;
            		intent3 = new Intent(context,kb_details.class);
            		Bundle map = new Bundle();
            		map.putSerializable("sended", sended);
            		intent3.putExtra("session", map);
            		context.startActivity(intent3);
                }
            });
		    list5.setOnItemClickListener(new AdapterView.OnItemClickListener() 
	        {
                public void onItemClick(AdapterView<?> av, View v, int pos, long id)
                {	
                	String sended="";
                	for(int i=0;i<week5[pos].length;++i)
                	{
                		String jia="";
                		switch(i)
                		{
                		case 0:jia="课程名称：";break;
                		case 1:jia="课程类型：";break;
                		case 2:jia="上课时间：";break;
                		case 3:jia="任课教师：";break;
                		case 4:jia="上课地点：";break;
                		case 5:jia="考试时间：";break;
                		case 6:jia="考试地点：";break;
                		}
                		sended=sended+jia+week5[pos][i]+"\n";
                	}
                	Context context = v.getContext();	
            		Intent intent3=null;
            		intent3 = new Intent(context,kb_details.class);
            		Bundle map = new Bundle();
            		map.putSerializable("sended", sended);
            		intent3.putExtra("session", map);
            		context.startActivity(intent3);
                }
            });
	    }  
}
