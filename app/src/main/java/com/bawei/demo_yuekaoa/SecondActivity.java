package com.bawei.demo_yuekaoa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.bawei.demo_yuekaoa.adapter.RecyclerAdapter;
import com.bawei.demo_yuekaoa.bean.Bean;
import com.bawei.demo_yuekaoa.bean.RowsBean;
import com.bawei.demo_yuekaoa.util.DividerListItemDecoration;
import com.bawei.demo_yuekaoa.util.OkHttpUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.squareup.okhttp.Request;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecondActivity extends AppCompatActivity {
    private XRecyclerView xRecyclerView;
    private int i = 0;
    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //获取控件
        xRecyclerView = (XRecyclerView) findViewById(R.id.xRecyclerView);
        //适配器
        recyclerAdapter = new RecyclerAdapter(SecondActivity.this);
        xRecyclerView.setAdapter(recyclerAdapter);
        //LayoutManager
        xRecyclerView.setLayoutManager(new LinearLayoutManager(SecondActivity.this, LinearLayoutManager.VERTICAL, false));
        //网络请求
        requestData();
        //实现上拉下拉
        UptoDown uptoDown = new UptoDown();
        xRecyclerView.setLoadingListener(uptoDown);
        //添加RecyclerView的分割线
        xRecyclerView.addItemDecoration(new DividerListItemDecoration(SecondActivity.this, DividerListItemDecoration.VERTICAL_LIST));
    }

    //实现上拉下拉
    class UptoDown implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {//下拉
            i = 0;
            requestData();
            xRecyclerView.refreshComplete();
        }

        @Override
        public void onLoadMore() {//上拉
            i++;
            requestData();
            xRecyclerView.loadMoreComplete();
        }
    }


    //请求数据
    private void requestData() {
        String url = "http://api.fang.anjuke.com/m/android/1.3/shouye/recInfosV3/?";
        Map<String, String> map = new HashMap<>();
        map.put("city_id", "14");
        map.put("lat", "40.04652");
        map.put("lng", "116.306033");
        map.put("api_key", "androidkey");
        map.put("sig", "9317e9634b5fbc16078ab07abb6661c5");
        map.put("macid", "45cd2478331b184ff0e15f29aaa89e3e");
        map.put("app", "a-ajk");
        map.put("_pid", "11738");
        map.put("o", "PE-TL10-user+4.4.2+HuaweiPE-TL10+CHNC00B260+ota-rel-keys%2Crelease-keys");
        map.put("from", "mobile");
        map.put("m", "Android-PE-TL10");
        map.put("cv", "9.5.1");
        map.put("cid", "14");
        map.put("i", "864601026706713");
        map.put("v", "4.4.2");
        map.put("pm", "b61");
        map.put("uuid", "1848c59c-185d-48d9-b0e9-782016041109");
        map.put("_chat_id", "0");
        map.put("qtime", i + "");
        //将请求路径，实体类传递
        App.okHttpUtils.setUrl(url, map, Bean.class, OkHttpUtils.Methods.GET);
        App.okHttpUtils.setCallbackM(new OkHttpUtils.CallbackM() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Object response) {
                //设置RecyclerView
                initRecyclerView(response);
            }
        });
    }

    //设置RecyclerView
    private void initRecyclerView(Object response) {
        Bean bean = (Bean) response;
        List<RowsBean> rowsList = bean.getResult().getRows();
        Log.d("大宁", bean.toString());
        if (i == 0) {
            recyclerAdapter.addrest_down(rowsList);
        } else {
            recyclerAdapter.addrest_up(rowsList);
        }
    }
}
