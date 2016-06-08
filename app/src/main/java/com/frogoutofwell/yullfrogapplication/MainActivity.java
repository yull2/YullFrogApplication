package com.frogoutofwell.yullfrogapplication;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.frogoutofwell.yullfrogapplication.data.ActivityDetailResult;
import com.frogoutofwell.yullfrogapplication.data.ActivityNameResult;
import com.frogoutofwell.yullfrogapplication.manager.NetworkManager;

import java.io.IOException;

import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;
    EditText searchView;
    AutoCompleteTextView searchV;
    String[] nameList = {"aa","bb"};
    String keyword;

/*
    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.main_bi);

        tabs = (TabLayout)findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();

        tabs.addTab(tabs.newTab().setText("메인"));
        tabs.addTab(tabs.newTab().setText("대외활동"));
        tabs.addTab(tabs.newTab().setText("마이페이지"));

        tabs.setBackgroundResource(R.color.colorMainTabs);

        Intent intent = new Intent(MainActivity.this, RegistrationIntentService.class);
        startService(intent);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        setActivityList();

        MenuItem searchItem = menu.findItem(R.id.search_item);
        searchV = (AutoCompleteTextView)searchItem.getActionView().findViewById(R.id.auto_search);
        searchV.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                keyword = s.toString();
                Log.i("kkk", "key : " + keyword);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button btn_search = (Button)searchItem.getActionView().findViewById(R.id.btn_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setActivitySearch(keyword);
            }
        });


        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.search_item){
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setActivityList(){
        NetworkManager.getInstance().getActivityNameList(this, new NetworkManager.OnResultListener<ActivityNameResult>() {
            @Override
            public void onSuccess(Request request, ActivityNameResult result) {
                nameList = result.activityName;
                searchV.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_dropdown_item_1line, nameList));
            }
            @Override
            public void onFail(Request request, IOException exception) {

            }
        });
    }

    private void setActivitySearch(String keyword){
        NetworkManager.getInstance().getActivitySearch(this, keyword, new NetworkManager.OnResultListener<ActivityDetailResult>() {
            @Override
            public void onSuccess(Request request, ActivityDetailResult result) {
                int seq = result.activityDetail.getSeq();
                Intent intent = new Intent(MainActivity.this, InterMainActivity.class);
                intent.putExtra("seq",seq);
                //Log.i("mainactivity","seqqqqqqqqqqqq : "+seq);
                startActivity(intent);
            }

            @Override
            public void onFail(Request request, IOException exception) {
                Toast.makeText(MainActivity.this, "fail : " + exception.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
