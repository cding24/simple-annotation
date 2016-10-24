package com.linghu.annotation_sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.linghu.annotation.BindView;
import com.linghu.annotation.Onclick;
import com.linghu.annotation.ViewInjector;
import com.linghu.annotation.viewinject_sample.R;

/**
 * Created by linghu on 2016/10/10.
 */
public class SimpleFragment extends Fragment {
    @BindView(R.id.id_textview)
    TextView mIdTextview;
    @BindView(R.id.id_btn)
    Button mIdBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ViewInjector.injectView(this, view);

        mIdTextview.setText("ViewInject");
        mIdBtn.setText("ViewInject ~");

        return view;
    }


    @Onclick({R.id.id_textview, R.id.id_btn})
    public void clickTheButton(View view){
        switch (view.getId()){
            case R.id.id_textview:
                Toast.makeText(this.getActivity(), "========1==========", Toast.LENGTH_LONG).show();
                break;
            case R.id.id_btn:
                Toast.makeText(this.getActivity(), "========2==========", Toast.LENGTH_LONG).show();
                break;
        }
    }

}
