package me.itslucas.foodie.activities.fzr.childpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import me.itslucas.foodie.Fragment.dashboard.DashboardFragment;
import me.itslucas.foodie.MainActivity;
import me.itslucas.foodie.R;
import me.itslucas.foodie.beans.UserInfoBean;

public class BuyResultActivity extends AppCompatActivity {

    TextView tv;
    ImageView imv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_result);



        ActionBar actionBar = getSupportActionBar();
        ColorDrawable cd = new ColorDrawable(Color.parseColor("#EE756D"));
//        actionBar.setBackgroundDrawable(cd);
        Window window = this.getWindow();
        window.setStatusBarColor(Color.parseColor("#EE756D"));
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }


        tv = findViewById(R.id.buyresult_tv);
        imv = findViewById(R.id.buyresult_pic);


        Bundle bundle2 = this.getIntent().getExtras();
        Float price = bundle2.getFloat("price");
        UserInfoBean user = new UserInfoBean();
        if(price>fzr_constant.userBalance){
            imv.setImageResource(R.drawable.wrong);
            tv.setText("余额不足！购买失败");
        }else{
            fzr_constant.userBalance-=price;
        }

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //返回按钮
            case android.R.id.home:
                this.finish(); // back button
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i  = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}