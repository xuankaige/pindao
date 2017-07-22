package gekaixuan.bawei.com.pindao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences sp;
    String  jsonstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         sp=getSharedPreferences("setusing",MODE_PRIVATE);
             }

             @Override
             public void onClick(View v) {
            jsonstr=sp.getString("setusing",null);
                 if (jsonstr == null) {
                     List<ChannelBean> list = new ArrayList<>();
                     for (int i = 0; i < 10; i++) {
                         ChannelBean be=null;
                         if (i < 5) {
                             be=new ChannelBean("item-"+1,true);

                         }else{
                             be=new ChannelBean("item-"+1,false);
                         }
                         list.add(be);
                     }
                     ChannelActivity.startChannelActivity(this,list); }
                     else{
                         ChannelActivity.startChannelActivity(this,jsonstr);

                 }
             }

             @Override
             protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                 super.onActivityResult(requestCode, resultCode, data);
                 if(requestCode==ChannelActivity.REQUEST_CODE&&resultCode==ChannelActivity.RESULT_CODE){
                     String  jsonstr=data.getStringExtra(ChannelActivity.RESULT_JSON_KEY);
                     sp.edit().putString("setusing",jsonstr).commit();
                 }
             }
}
