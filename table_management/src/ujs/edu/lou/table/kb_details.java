package ujs.edu.lou.table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class kb_details  extends Activity{
	private TextView kbView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kb_detail);
        kbView=(TextView)findViewById(R.id.my_kb_info);
        Intent get = getIntent();
        String re=get.getBundleExtra("session").getSerializable("sended").toString();
        kbView.setText(re);
        kbView.setTextSize(20);
        kbView.setTextColor(Color.rgb(0, 0, 0));
    }
}