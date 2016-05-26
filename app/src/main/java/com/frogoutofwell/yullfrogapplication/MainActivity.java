package com.frogoutofwell.yullfrogapplication;

import android.app.SearchManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    TabLayout tabs;
    ViewPager pager;

    private SearchView searchView = null;
    private SearchView.OnQueryTextListener queryTextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabs = (TabLayout)findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        tabs.setupWithViewPager(pager);
        tabs.removeAllTabs();

        tabs.addTab(tabs.newTab().setText("메인"));
        tabs.addTab(tabs.newTab().setText("대외활동"));
        tabs.addTab(tabs.newTab().setText("마이페이지"));



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem searchItem = menu.findItem(R.id.search_item);
        SearchManager searchManager = (SearchManager) getSystemService(this.SEARCH_SERVICE);
        if (searchItem != null){
            searchView = (SearchView)searchItem.getActionView();
        }
        if (searchView != null){
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

            queryTextListener = new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Log.i("onQueryTextSubmit",query);
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    Log.i("onQueryTextChange",newText);
                    return true;
                }
            };
            searchView.setOnQueryTextListener(queryTextListener);

        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.search_item) {
            return false;
        }

        searchView.setOnQueryTextListener(queryTextListener);
        return super.onOptionsItemSelected(item);
    }
}
