package ujs.edu.lou.table;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
/** 
 * @author： 严轶锋、娄修俊  
 * @version:1.0
 * @类说明：登录验证界面 
 */  
public class login extends Activity {
	private Button logBtn; //定义登录按钮
	private Button exitBtn;//定义注册按钮
	private RadioGroup group;//定义单选按钮组
	private RadioButton student,teacher;
	private EditText userName,password;
	private CheckBox rem_pw, auto_login;
	private SharedPreferences sp; 
	private ProgressDialog myDialog = null;
	private Handler handler = new Handler();
	private boolean isStudent=true;
	private String accountText="";
	private String passwordText="";
	private String myujs ="my.ujs.edu.cn";
	DefaultHttpClient client =new DefaultHttpClient();
	private int result = 0;	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		menu.add(0, 1, 1, R.string.exit);
		menu.add(0, 2, 2, R.string.about);
		return super.onCreateOptionsMenu(menu);
	}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);  
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.login);
        sp = this.getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);  
        userName=(EditText)findViewById(R.id.editAccount);
        password=(EditText)findViewById(R.id.editPassword);       
        group = (RadioGroup)this.findViewById(R.id.radioGroup); 
        student=(RadioButton)findViewById(R.id.guide);
        teacher=(RadioButton)findViewById(R.id.visitor);       
        rem_pw = (CheckBox) findViewById(R.id.checkbox1);  
        auto_login = (CheckBox) findViewById(R.id.checkbox2);  
        logBtn = (Button) findViewById(R.id.loginbtn);
        exitBtn=(Button)findViewById(R.id.resetbtn);
        accountText = userName.getText().toString();
        passwordText=password.getText().toString();
        logBtn.setOnClickListener(new loginBtnListener());
        exitBtn.setOnClickListener(new exitBtnListenner());   
        if(sp.getBoolean("ISCHECK", false))  
        {    
            rem_pw.setChecked(true);
            userName.setText(sp.getString("USER_NAME",""));  
            password.setText(sp.getString("PASSWORD", ""));  
            if(sp.getBoolean("AUTO_ISCHECK", false))  
            {  
            	auto_login.setChecked(true);
            	final View v=getCurrentFocus();
    			myDialog = ProgressDialog.show(login.this, "请稍后...","验证登录中...",true);		
    			result = checkout();	
    		        	if (result == 1)
    					{					
    						Toast.makeText(v.getContext(),
    								"用户登录成功！ " , Toast.LENGTH_SHORT)
    								.show();
    						Context context = v.getContext();	
    						Intent intent=null;
    						  if(rem_pw.isChecked())  
    		                    {  
    		                     //记住用户名、密码、   
    		                      Editor editor = sp.edit();  
    		                      editor.putString("USER_NAME",accountText);  
    		                      editor.putString("PASSWORD",passwordText);  
    		                      editor.commit();  
    		                    }  
    						if(isStudent)
    						{	
    							intent = new Intent(context,Student_main_activity.class);
    							Bundle map = new Bundle();
    							map.putSerializable("xh", accountText); 
    							intent.putExtra("session", map);	
    						}
    						else
    						{ 
    							intent =new Intent(context, Teacher_main_activity.class);
    						}
    						context.startActivity(intent);
    					}
    					if (result == 0) {
    						Toast.makeText(v.getContext(), "用户验证失败！",
    								Toast.LENGTH_SHORT).show();
    					}
    					if (result == -1) {
    						Toast.makeText(v.getContext(), "请检查网络连接！",
    								Toast.LENGTH_SHORT).show();
    					}
    			    myDialog.dismiss();
            } 
        }   
        rem_pw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				 if (rem_pw.isChecked()) {  
                     
	                    System.out.println("记住密码已选中");  
	                    sp.edit().putBoolean("ISCHECK", true).commit();  
	                      
	                }else {  
	                      
	                    System.out.println("记住密码没有选中");  
	                    sp.edit().putBoolean("ISCHECK", false).commit();                        
	                }  
			}
		}); 
        auto_login.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {  
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {  
                if (auto_login.isChecked()) {  
                    System.out.println("自动登录已选中");  
                    sp.edit().putBoolean("AUTO_ISCHECK", true).commit();  
  
                } else {  
                    System.out.println("自动登录没有选中");  
                    sp.edit().putBoolean("AUTO_ISCHECK", false).commit();  
                }  
            }  
        });    
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId==student.getId()) {
					isStudent=true;	
				}else if (checkedId==teacher.getId()) {
					isStudent=false;
				}
			}		
		});			
    }
	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==1){
			Toast.makeText(login.this, "退出成功", Toast.LENGTH_LONG).show();
			Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			 startActivity(exit);
             System.exit(0);
		}
		if(item.getItemId()==2){
			new AlertDialog.Builder(login.this) 
			                .setTitle("关于教务管理掌中宝")  
			                .setMessage("这是一个针对江苏大学教务管理系统的智能手机客户端，谨代表开发团队成员感谢您的使用！")  
			                .setPositiveButton("确定", null)  
			                .show();  
		}
		return super.onOptionsItemSelected(item);
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
            new AlertDialog.Builder(this)
            .setTitle("退出程序")
            .setMessage("您确定要退出吗？")
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                
                public void onClick(DialogInterface dialog, int which) {
                	Toast.makeText(login.this, "退出成功", Toast.LENGTH_LONG).show();
                    Intent exit = new Intent(Intent.ACTION_MAIN);
                    exit.addCategory(Intent.CATEGORY_HOME);
                    exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(exit);
                    System.exit(0);
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
    class loginBtnListener implements OnClickListener{
		public void onClick(final View v) {
			myDialog = ProgressDialog.show(login.this, "请稍后...","验证登录中...",true);
			new Thread(new Runnable() {
				public void run() {		
			result = checkout();
			handler.post(new Runnable() {
				public void run() {
					if (result == 1)
					{					
						Toast.makeText(v.getContext(),
								"用户登录成功！ " , Toast.LENGTH_SHORT)
								.show();
						Context context = v.getContext();	
						Intent intent=null;
						  if(rem_pw.isChecked())  
		                    {  
		                     //记住用户名、密码、   
		                      Editor editor = sp.edit();  
		                      editor.putString("USER_NAME",accountText);  
		                      editor.putString("PASSWORD",passwordText);  
		                      editor.commit();  
		                    }  
						if(isStudent)
						{	
							intent = new Intent(context,Student_main_activity.class);
							Bundle map = new Bundle();
							map.putSerializable("xh", accountText); 
							intent.putExtra("session", map);	
						}
						else
						{ 
							intent =new Intent(context, Teacher_main_activity.class);
						}
						context.startActivity(intent);
					}
					if (result == 0) {
						Toast.makeText(v.getContext(), "用户验证失败！",
								Toast.LENGTH_SHORT).show();
					}
					if (result == -1) {
						Toast.makeText(v.getContext(), "请检查网络连接！",
								Toast.LENGTH_SHORT).show();
					}
				}
				});
			    myDialog.dismiss();
				}
			}).start();
		}	
    }
    private int checkout()
	{
		String info = "";
		accountText=userName.getText().toString();
		passwordText=password.getText().toString();
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
	        params.add(new BasicNameValuePair("Login.Token1", accountText));
	        params.add(new BasicNameValuePair("Login.Token2", passwordText));
			try {
				info = HttpUtil.postUrl("http://" + myujs+"/userPasswordValidate.portal",params, client, "");				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}   
			if (info.indexOf("handleLoginSuccessed") != -1) 
			{
				return 1;
			} 
			else if (info.indexOf("handleLoginFailure")!=-1) 
			{
				return 0;
			}
			else
			{
				return -1;
			}	
	}
    class exitBtnListenner implements OnClickListener{
		public void onClick(View v) {
			Intent exit = new Intent(Intent.ACTION_MAIN);
            exit.addCategory(Intent.CATEGORY_HOME);
            exit.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(exit);
            System.exit(0);
		}
    }   
}