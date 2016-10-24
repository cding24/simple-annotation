package com.linghu.dynamic.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.linghu.annotation.R;
import com.linghu.dynamic.InjectClick;
import com.linghu.dynamic.InjectView;
import com.linghu.dynamic.ProcessInject;

/**
 * created by linghu on 2016/10
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @InjectView(R.id.Dinject1)
    TextView view1;
    @InjectView(R.id.Dinject2)
    TextView view2;
    @InjectView(R.id.Dinject3)
    TextView view3;
    @InjectView(R.id.Dinject4)
    TextView view4;

    @InjectView(R.id.modified_btn)
    Button modifiedBtn;
    @InjectView(R.id.jump_btn)
    Button jumpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProcessInject.injectView(MainActivity.this, MainActivity.this);
        ProcessInject.injectClick(MainActivity.this, MainActivity.this);
    }

    @Override
    @InjectClick({R.id.modified_btn, R.id.jump_btn})
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.modified_btn:
                view1.setText("Please user runtime annotation to handle the repeat work of findViewById and setOnclick and so on things.");
                break;
            case R.id.jump_btn:
                Intent intent = new Intent(this, TwoActivity.class);
                startActivity(intent);
                break;
        }
    }

}
