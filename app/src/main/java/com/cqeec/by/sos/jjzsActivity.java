package com.cqeec.by.sos;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class jjzsActivity extends Activity {

    private ListView listView;
    private List<Bean_jjzs> mDatas;
    private MyAdapter_jjzs mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jjzs);

        initView();
        initData();

    }

    //方法：初始化View
    private void initView() {
        listView = (ListView) findViewById(R.id.list_view2);
    }

    //方法；初始化Data
    private void initData() {
        mDatas = new ArrayList<Bean_jjzs>();

        //将数据装到集合中去
        Bean_jjzs bean = new Bean_jjzs("一、足踝扭伤急救法",
                "1、轻度足踝扭伤，应先冷敷患处，２４小时后改用热敷，用绷带缠住足踝，把脚垫高，即可减轻症状。", "2018-11-01");
        mDatas.add(bean);

        bean = new Bean_jjzs("二、触电急救法", "①迅速切断电源。\n" +
                "\n" +
                " ②一时找不到闸门，可用绝缘物挑开电线或砍断电线。\n" +
                "\n" +
                " ③立即将触电者抬到通风处，解开衣扣、裤带，若呼吸停止，必须做口对口人工呼吸或将其送附近医院急救。\n" +
                "\n" +
                " ④可用盐水或凡士林纱布包扎局部烧伤处。", "2018-11-02");
        mDatas.add(bean);

        bean = new Bean_jjzs("三、动脉出血急救法", "①小动脉出血，伤口不大，可用消毒棉花敷在伤口上，加压包扎，一般就能止眩\n" +
                "\n" +
                " ②出血不止时，可将伤肢抬高，减慢血流的速度，协助止眩\n" +
                "\n" +
                " ③四肢出血严重时，可将止血带扎在伤口的上端，扎前应先垫上毛巾或布片，" +
                "然后每隔半小时必须放松１次，绑扎时间总共不得超过两小时，以免肢体缺血坏死。作初步处理后，应立即送医院救治。" , "2018-11-06");
        mDatas.add(bean);

        bean = new Bean_jjzs("四、儿童抽风急救法",
                "1、发现小儿抽风，不宜惊慌失措，乱摇患儿，以致加重病情，不要灌水喂汤，以免吸入气管。" +
                        "应打开窗户，解开患儿上衣让呼吸通畅。将筷子用布包裹塞入患儿上下牙之间以免咬破舌头。" +
                        "发高烧引起的抽风，可用毛巾蘸冷水敷于额部。详细记录抽风的时间、症状，立即送医院治疗", "2018-11-03");
        mDatas.add(bean);
        bean = new Bean_jjzs("五、狗咬伤急救法",
                "被狗咬伤后，应在伤口上下５厘米处用布带勒紧，用吸奶器将污血吸出，然后用肥皂水冲洗伤口。" +
                        "咬人的狗应加隔离，一旦确诊为携带狂犬病毒，应即处死。", "2018-11-015");
        mDatas.add(bean);
        bean = new Bean_jjzs("六、骨折急救法",
                "救护骨折者的方法：\n" +
                        "\n" +
                        " ①止血：可采用指压、包扎、止血带等办法止眩\n" +
                        "\n" +
                        " ②包扎：对开放性骨折用消毒纱布加压包扎，暴露在外的骨端不可送回。\n" +
                        "\n" +
                        " ③固定：以旧衣服等软物衬垫着夹上夹板，无夹板时也可用木棍等代用，把伤肢上下两个关节固定起来。\n" +
                        "\n" +
                        " ④治疗：如有条件，可在清创、止痛后再送医院治疗。", "2018-11-04");
        mDatas.add(bean);

        //为数据绑定适配器
        mAdapter = new MyAdapter_jjzs(this,mDatas);

        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView= (TextView) view.findViewById(R.id.descTv);
                //Toast.makeText(jjzsActivity.this, textView.getText(), Toast.LENGTH_SHORT).show();
                final TextView textView1 =findViewById(R.id.textViewjj);
                textView1.setVisibility(View.VISIBLE);//显示
                textView1.setText(textView.getText());
                textView1.setTextSize(20);
                final ListView textView2 =findViewById(R.id.list_view2);
                textView2.setVisibility(View.GONE);//隐藏
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        textView1.setVisibility(View.GONE);//隐藏
                        textView2.setVisibility(View.VISIBLE);//显示
                        Toast.makeText(jjzsActivity.this, "5555", Toast.LENGTH_SHORT).show();
                    }
                });






            }
        });

    }

}
