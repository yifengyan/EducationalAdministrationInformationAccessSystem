package ujs.edu.lou.table;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class LevelExam_info extends Activity implements OnItemClickListener{
	private String[] className;
	private String[][] ExamInformation;
	private String ks;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {  	
    	try {
			 super.onCreate(savedInstanceState);
			    Intent get = getIntent();
				ks = get.getBundleExtra("session").getSerializable("ks").toString();
				String reg1="</*tr.*>";
	            String[] arr=ks.split(reg1);
	            ExamInformation=new String[arr.length-3][10];
	            String reg2="</?td>";
	            for(int i=0;i<arr.length-3;++i)
	            {
	            	 String[] arr2=arr[i+2].split(reg2);
	            	 for(int j=0;j<10;++j)
	            	 {
	            		 ExamInformation[i][j]=arr2[2*j+1];
	            	 }
	            }
				className=new String[ExamInformation.length];
				for(int i=0;i<className.length;++i)
				{
					className[i]=ExamInformation[i][4]+ExamInformation[i][2]+":"+ExamInformation[i][5];
				}
		        setContentView(R.layout.exam_search);
		        ListView listView = (ListView) findViewById(R.id.classlist);
		        ArrayAdapter<String> aaData = new ArrayAdapter<String>(this,
						android.R.layout.simple_list_item_1, className);
		        listView.setAdapter(aaData);
		        listView.setOnItemClickListener(this);		
		} catch (Exception e) {
			e.printStackTrace();
		}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelexam_search);
        ListView listView = (ListView) findViewById(R.id.classlist);
        ArrayAdapter<String> aaData = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, className);
        listView.setAdapter(aaData);
        listView.setOnItemClickListener(this);
    }
    public void onItemClick(AdapterView<?> parent, View view, int position,
			long id)
	{
    	String sended="";
    	for(int i=0;i<10;++i)
    	{
    		String jia="";
    		switch(i)
    		{
    		case 0:jia="ѧ�꣺";break;
    		case 1:jia="ѧ�ڣ�";break;
    		case 2:jia="�ȼ��������ƣ�";break;
    		case 3:jia="׼��֤�ţ�";break;
    		case 4:jia="�������ڣ�";break;
    		case 5:jia="�ɼ���";break;
    		case 6:jia="�����ɼ���";break;
    		case 7:jia="�Ķ��ɼ���";break;
    		case 8:jia="д���ɼ���";break;
    		case 9:jia="�ۺϳɼ���";break;
    		}
    		sended=sended+jia+ExamInformation[position][i].replaceAll("&nbsp;","����")+"\n";
    	}
    	Context context = view.getContext();	
		Intent intent3=null;
		intent3 = new Intent(context,Exam_in_detail.class);
		Bundle map = new Bundle();
		map.putSerializable("sended", sended);
		intent3.putExtra("session", map);
		context.startActivity(intent3);

	}
}