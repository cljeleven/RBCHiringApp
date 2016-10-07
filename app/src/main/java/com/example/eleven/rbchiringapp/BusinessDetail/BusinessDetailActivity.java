package com.example.eleven.rbchiringapp.BusinessDetail;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eleven.rbchiringapp.Models.Business;
import com.example.eleven.rbchiringapp.Models.Category;
import com.example.eleven.rbchiringapp.R;
import com.example.eleven.rbchiringapp.utils.Picasso_circle_transformation;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BusinessDetailActivity extends AppCompatActivity implements BusinessDetailViewInterface{

    ImageView businessImg;
    TextView business_price;
    TextView business_rating;
    TextView business_category;
    TextView business_name;
    ViewPager viewPager;
    customerPagerAdp adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail_view);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_detail_toolbar);
        Intent i = getIntent();
        Business business = (Business) i.getSerializableExtra("business");

        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        businessImg = (ImageView) findViewById(R.id.bussiness_detail_headImg);
        business_price = (TextView) findViewById(R.id.bussiness_detail_price);
        business_rating = (TextView) findViewById(R.id.bussiness_detail_rating);
        business_category = (TextView) findViewById(R.id.bussiness_detail_category);
        business_name = (TextView)findViewById(R.id.bussiness_detail_name);
        viewPager = (ViewPager) findViewById(R.id.bussiness_detail_viewpager);

        BusinessDetailPresenter mpresenter = new BusinessDetailPresenter(this,new BusinessDetailModel());
        if(business!=null){
            Picasso.with(this)
                    .load(business.getImageUrl())
                    .transform(new Picasso_circle_transformation())
                    .resize(500,500)
                    .into(businessImg);

            business_name.setText(business.getName());
            business_price.setText(business.getPrice());
            String category = "Category:";
            for(Category c : business.getCategories()){
                category+=c.getTitle()+",";
            }
            category = category.substring(0,category.length()-1);
            business_category.setText(category);
            getSupportActionBar().setTitle(business.getName());
        }


        adp = new customerPagerAdp(this);
        viewPager.setAdapter(adp);
        mpresenter.fetchdetail(business.getId());
       // mpresenter.fetchreview(business.getId());
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                Intent upIntent = NavUtils.getParentActivityIntent(this);
                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
                    // This activity is NOT part of this app's task, so create a new task
                    // when navigating up, with a synthesized back stack.
                    TaskStackBuilder.create(this)
                            // Add all of this activity's parents to the back stack
                            .addNextIntentWithParentStack(upIntent)
                            // Navigate up to the closest parent
                            .startActivities();
                } else {
                    // This activity is part of this app's task, so simply
                    // navigate up to the logical parent activity.
                    NavUtils.navigateUpTo(this, upIntent);
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void updateImg(List<String> photos) {
        adp.setPhotos(photos);
    //    Toast.makeText(this,"got photo",Toast.LENGTH_LONG).show();
    }
}
