package ujs.edu.lou.table;

import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

public class Table_managementActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
        final Intent localIntent=new Intent(this,login.class);  
        Timer timer=new Timer();//���ö�ʱ��  
        TimerTask tast=new TimerTask()
        {  
         @Override  
         public void run(){  
          startActivity(localIntent);//ִ��  
         }  
        };  
         timer.schedule(tast, 1000*1);//����1������ת         
    }
}