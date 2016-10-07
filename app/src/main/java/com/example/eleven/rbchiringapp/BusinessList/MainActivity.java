package com.example.eleven.rbchiringapp.BusinessList;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.eleven.rbchiringapp.BusinessList.ListAdapters.MainlistAdapter;
import com.example.eleven.rbchiringapp.BusinessList.ListAdapters.MainlistAdapter_cat;
import com.example.eleven.rbchiringapp.Models.YelpSearchResult;
import com.example.eleven.rbchiringapp.R;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

    RelativeLayout mainlayout;
    RecyclerView mainlist;
    private RecyclerView.LayoutManager mLayoutManager;
    ViewGroup popupsearchview;
    ViewGroup popupfilterview;
    EditText nameET;
    EditText locationET;
    Button searchBtn;

    CheckBox categorize_checkbox;


    MainPresenter mpresenter;
    PopupWindow searchwindow;
    PopupWindow filterwindow;
    Spinner filterspinner;
    Button filtersetbtn;

    String sort;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        mpresenter = new MainPresenter(this,this, new MainModel());

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        popupsearchview = (ViewGroup) inflater.inflate(R.layout.search_layout,null);
        popupfilterview = (ViewGroup) inflater.inflate(R.layout.filter_layout,null);

        mainlayout = (RelativeLayout) findViewById(R.id.mainlayout);
        mainlist = (RecyclerView)findViewById(R.id.main_recyclerview);

        mLayoutManager = new LinearLayoutManager(this);
        mainlist.setLayoutManager(mLayoutManager);



        nameET = (EditText) popupsearchview.findViewById(R.id.searchview_search_restaurant);
        locationET = (EditText) popupsearchview.findViewById(R.id.searchview_search_location);
        searchBtn = (Button)popupsearchview.findViewById(R.id.searchview_search_btn);

        categorize_checkbox = (CheckBox)popupsearchview.findViewById(R.id.search_categorize_checkbox);

        filtersetbtn = (Button) popupfilterview.findViewById(R.id.filter_set_btn);
        filterspinner = (Spinner) popupfilterview.findViewById(R.id.filter_spinner);



        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this,
                R.array.sort_options, R.layout.spinner_cell);
        adapter.setDropDownViewResource(R.layout.spinner_dropcell);
        filterspinner.setAdapter(adapter);
        filterspinner.setSelection(0);

        filterspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sort = String.valueOf(adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        filtersetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mpresenter.set_sort(sort);
                filterwindow.dismiss();
            }
        });

        mpresenter.searchresult("ethopian","Toronto");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // TODO save your instance to outState
        mpresenter.onSaveInstanceState(outState);

    }
    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void setadapter(YelpSearchResult result) {
        if(mainlist!=null){
            MainlistAdapter adp ;
            adp = new MainlistAdapter(this,mpresenter);
            mainlist.setAdapter(adp);
            adp .setList(result.getBusinesses());
        }
    }

    @Override
    public void setcategorizedadapter(YelpSearchResult result) {
        if(mainlist!=null){
            MainlistAdapter_cat adp ;
            adp = new MainlistAdapter_cat(this,mpresenter);
            mainlist.setAdapter(adp);
            adp .setList(result.getBusinesses());
        }
    }

    @Override
    public boolean check_checkbox() {
        return categorize_checkbox.isChecked();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_search_item:
                searchBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = (nameET.getText().toString().length()>0)?nameET.getText().toString():"";
                        String location = (locationET.getText().toString().length()>0)?locationET.getText().toString():"Toronto";
                        mpresenter.searchresult(name,location);
                    //    Toast.makeText(MainActivity.this,"searching " + name+" at "+location ,Toast.LENGTH_LONG).show();
                    }
                });
                if(searchwindow==null) {
                    searchwindow = new PopupWindow(popupsearchview, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
                }
                searchwindow.showAtLocation(mainlayout, Gravity.CENTER,0,0);

                break;

            case R.id.menu_filter_item:


                if(filterwindow==null) {
                    filterwindow = new PopupWindow(popupfilterview, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, true);

                }
                filterwindow.showAtLocation(mainlayout, Gravity.CENTER,0,0);

               // Toast.makeText(getApplication(),"filter",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
