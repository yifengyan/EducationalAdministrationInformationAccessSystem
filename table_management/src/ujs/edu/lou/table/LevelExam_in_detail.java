package ujs.edu.lou.table;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class LevelExam_in_detail  extends Activity{
	private TextView levelexamView;
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.levelexam_in_detail);
        levelexamView=(TextView)findViewById(R.id.my_exam_info);
        Intent get = getIntent();
        String re=get.getBundleExtra("session").getSerializable("sended").toString();
        levelexamView.setText(re);
        levelexamView.setTextSize(20);
        levelexamView.setTextColor(Color.rgb(0, 0, 0));
    }
}
