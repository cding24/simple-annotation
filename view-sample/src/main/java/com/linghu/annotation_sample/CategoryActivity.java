package com.linghu.annotation_sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.linghu.annotation.BindView;
import com.linghu.annotation.ViewInjector;
import com.linghu.annotation.viewinject_sample.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by linghu on 2016/4/12.
 */
public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.id_category_listview)
    ListView mCategoryLv;

    private List<String> mData = new ArrayList<>(Arrays.asList("Simple Use", "RecyclerView Use"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ViewInjector.injectView(this);

        mCategoryLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                categoryItemClick(position);
            }
        });

        mCategoryLv.setAdapter(new CategoryAdapter(this, mData));
    }

    public void categoryItemClick(int position) {
        Toast.makeText(CategoryActivity.this, "position = " + position, Toast.LENGTH_SHORT).show();
    }

}
