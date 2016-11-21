package ujs.edu.lou.table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class xuefen_in_detail extends Activity{
	/** Called when the activity is first created. */
    private TextView xuefenView;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xuefen_in_detail);
        xuefenView=(TextView)findViewById(R.id.mymark);
        Intent get = getIntent();
        String re=get.getBundleExtra("session").getSerializable("sended").toString();
        xuefenView.setText(re);
        xuefenView.setTextSize(20);
        xuefenView.setTextColor(Color.rgb(0,0,0));
    }
}
