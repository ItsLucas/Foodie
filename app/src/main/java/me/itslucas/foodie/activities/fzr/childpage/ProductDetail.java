package me.itslucas.foodie.activities.fzr.childpage;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.service.autofill.UserData;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.List;

import me.itslucas.foodie.ChatActivity;
import me.itslucas.foodie.R;
import me.itslucas.foodie.beans.CartBean;
import me.itslucas.foodie.beans.MessageBean;
import me.itslucas.foodie.beans.UserInfoBean;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProductDetail extends AppCompatActivity {

    ImageButton ib_back;
    Button btn_addCart;
    ImageView imv;
    TextView tv_text;
    TextView tv_num;
    TextView tv_name;
    TextView tv_price;
    ImageButton ib_like;
    Button bp;
    Button br;
    ProgressBar pb;
    boolean likeFlag=false;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    pb.setVisibility(View.GONE);
                    break;
                case 0:
                    pb.setVisibility(View.VISIBLE);
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Bundle b = this.getIntent().getExtras();
        String proName = "null";
        String proPrice = "null";
        int picId = 0;
        if(b!=null){
            proName = b.getString("name");
            proPrice = b.getString("price");
            picId = b.getInt("pic");
        }




        ib_back=findViewById(R.id.apd_back);
        imv =findViewById(R.id.apd_pic);
        tv_text = findViewById(R.id.apd_text);
        tv_text.setMovementMethod(ScrollingMovementMethod.getInstance());
        bp= findViewById(R.id.apd_p);
        br=findViewById(R.id.apd_r);
        tv_num = findViewById(R.id.apd_num);
        ib_like = findViewById(R.id.apd_like);
        tv_name = findViewById(R.id.apd_name);
        tv_price = findViewById(R.id.apd_price);
        btn_addCart= findViewById(R.id.apd_addCart);
        pb = findViewById(R.id.apd_pb);
        pb.setVisibility(View.GONE);
        
        tv_name.setText(proName);
        tv_price.setText(proPrice);
        ib_like.setImageResource(R.drawable.ic_kefu);


        RoundedBitmapDrawable circularBitmapDrawable = RoundedBitmapDrawableFactory.create(this.getResources(), BitmapFactory.decodeResource(this.getResources(), picId));
        circularBitmapDrawable.setCornerRadius(50);
        imv.setImageDrawable(circularBitmapDrawable);
        ib_back.setOnClickListener((v)->{
            onBackPressed();
        });
        bp.setOnClickListener((v)->{

            tv_num.setText(Integer.parseInt(tv_num.getText().toString())+1+"");
        });
        br.setOnClickListener((v)->{
            if(tv_num.getText().toString().equals("1")){

            }else{
                tv_num.setText(Integer.parseInt(tv_num.getText().toString())-1+"");
            }

        });
        
        
        ib_like.setOnClickListener((v)->{
            Intent intent = new Intent(this, ChatActivity.class);
            Bundle bundle = new Bundle();
            bundle.putBoolean("isroom",true);
            bundle.putString("user", "111");
            intent.putExtras(bundle);
            startActivity(intent);

        });

        //===================================
        //购物车按钮点击
        btn_addCart.setOnClickListener((v)->{
//

            OkHttpClient httpClient = new OkHttpClient();

            String pid = fzr_constant.getProductIdByName(tv_name.getText().toString());

            String url = "https://foodie.itslucas.me/cart.php?id="+fzr_constant.userID+"&product_id="+pid+"&quantity="+tv_num.getText().toString();
            Log.i("res",url);
            Request getRequest = new Request.Builder()
                    .url(url)
                    .get()
                    .build();

            Call call = httpClient.newCall(getRequest);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        mHandler.sendEmptyMessage(0);
                        //同步请求，要放到子线程执行
                        Response response = call.execute();

                        String res = response.body().string();
                        Log.i("res",res);




                        mHandler.sendEmptyMessage(1);
                        Toast.makeText(getApplicationContext(),"已添加!",Toast.LENGTH_SHORT).show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        });
    }


}