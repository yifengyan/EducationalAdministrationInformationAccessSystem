package ujs.edu.lou.table;


import java.io.IOException;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Student_main_activity extends Activity {
	private Button examBtn;
	private Button tableBtn;   
	private Button scoresBtn;
	private Button levelexamBtn;
	private Button classBtn;
	private Button infoBtn;     	
	DefaultHttpClient client=new DefaultHttpClient();
	private ProgressDialog myDialog = null;
	private String xh="";
	private String ExamInfo="";
	private String LevelExamInfo="";	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_main_table);
        examBtn=(Button)findViewById(R.id.exam_search);
        tableBtn=(Button)findViewById(R.id.table_search);
        scoresBtn=(Button)findViewById(R.id.scores_search);
        levelexamBtn=(Button)findViewById(R.id.mycard_info);
        classBtn=(Button)findViewById(R.id.select_class);
        infoBtn=(Button)findViewById(R.id.campus_info);     
        Intent get = getIntent();
		xh = get.getBundleExtra("session").getSerializable("xh").toString();
        examBtn.setOnClickListener(new examBtnListener());      
        scoresBtn.setOnClickListener(new scoresBtnListener());
        infoBtn.setOnClickListener(new infoBtnListener());
        tableBtn.setOnClickListener(new tableBtnListener());
        levelexamBtn.setOnClickListener(new levelexamBtnListener());
        classBtn.setOnClickListener(new classBtnListener());
        try {
			String login=HttpUtil.getUrl("http://xk1.ujs.edu.cn/default_zzjk.aspx",client, "");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
  //设置登录按钮的监听器
    class examBtnListener implements OnClickListener{
    	public void onClick(final View v) {
    		// TODO Auto-generated method stub	
    		myDialog = ProgressDialog.show(Student_main_activity.this, "请稍后...", "考试安排查询中...",
					true);
    		new Thread(new Runnable() {
				public void run() {					
						try{
							ExamInfo=HttpUtil.getUrl("http://xk1.ujs.edu.cn/xskscx.aspx?xh="+xh+"&gnmkdm=N121607",client,"http://xk1.ujs.edu.cn/xs_main.aspx?xh="+xh);
							Context context = v.getContext();	
							Intent intent2=null;
							intent2 = new Intent(context,Exam_info.class);
							Bundle map = new Bundle();
							map.putSerializable("ks", ExamInfo);
							intent2.putExtra("session", map);
							context.startActivity(intent2);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}				
				}
				}).start();
    		myDialog.dismiss();
    		}
    	}   
    class scoresBtnListener implements OnClickListener{
		public void onClick(final View v) {
			new Thread(new Runnable() {

				public void run() {
					Context context = v.getContext();	
					Intent intent2=null;
					intent2 = new Intent(context,Mark_info.class);
					Bundle map = new Bundle();
					map.putSerializable("xh", xh);
					intent2.putExtra("session", map);
					context.startActivity(intent2);		
				}
				}).start();
		}
    }
    class tableBtnListener implements OnClickListener{
		public void onClick(final View v) {
			new Thread(new Runnable() {

				public void run() {
					Context context = v.getContext();	
					Intent intent2=null;
					intent2 = new Intent(context,kb_info.class);
					Bundle map = new Bundle();
					map.putSerializable("xh", xh);
					intent2.putExtra("session", map);
					context.startActivity(intent2);		
				}
				}).start();
		}

    }
    class infoBtnListener implements OnClickListener{
		public void onClick(final View v) {

					Context context = v.getContext();	
					Intent intent2=null;
					intent2 = new Intent(context,School_infomation.class);
					context.startActivity(intent2);		
				
		}

    }
    class levelexamBtnListener implements OnClickListener{
		public void onClick(final View v)
		{
			myDialog = ProgressDialog.show(Student_main_activity.this, "请稍后...", "等级考试成绩查询中...",
					true);
    		new Thread(new Runnable() {

				public void run() {
					
						try{
							LevelExamInfo=HttpUtil.getUrl("http://xk1.ujs.edu.cn/xsdjkscx.aspx?xh="+xh+"&gnmkdm=N121606",client,"http://xk1.ujs.edu.cn/xs_main.aspx?xh="+xh);
							Context context = v.getContext();	
							Intent intent2=null;
							intent2 = new Intent(context,LevelExam_info.class);
							Bundle map = new Bundle();
							map.putSerializable("ks", LevelExamInfo);
							intent2.putExtra("session", map);
							context.startActivity(intent2);
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}				
				}
				}).start();
    		myDialog.dismiss();
		}
    }
    class classBtnListener implements OnClickListener{
    	public void onClick(final View v) {
			new Thread(new Runnable() {

				public void run() {
					Context context = v.getContext();	
					Intent intent2=null;
					intent2 = new Intent(context,xuefen_info.class);
					Bundle map = new Bundle();
					map.putSerializable("xh", xh);
					intent2.putExtra("session", map);
					context.startActivity(intent2);		
				}
				}).start();
		}

    }
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
            .setTitle("退出登录")
            .setIcon(R.drawable.back)
            .setMessage("您确定要退出登录吗？")
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {           
                public void onClick(DialogInterface dialog, int which) {
                  finish();
                }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .show();
            return true;
        }
		return super.onKeyDown(keyCode, event);
	}    
    }
    
    
    
    
