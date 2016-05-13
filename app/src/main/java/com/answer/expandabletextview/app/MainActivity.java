package com.answer.expandabletextview.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.answer.expandabletextview.R;
import com.answer.expandabletextview.lib.ExpandableTextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableTextView expandableTextView = (ExpandableTextView)findViewById(R.id.text_view_expandable);

        expandableTextView.setText("故事主要发生在一个虚幻的大陆：维斯特洛上的七大王国。维斯特洛是一片大约有南美洲那么大的大陆，有可以上溯到12000年前的悠久历史。在那里每一个季节通常持续数年。这片大陆的原住民是森林之子。森林之子与大自然和谐共处，并且使用魔法。\n" +
                "\n" +
                "先民们最早期的武士带着青铜武器和骑术，通过维斯特洛与东部大陆相连的陆桥（在随后的战争中被森林之子用魔法摧毁，成为现在的多恩群岛和石阶群岛）登陆维斯特洛，并与森林之子之间展开了一系列长久的战争。战争最后以在千面屿签订的和平协议结束，根据协议，先民得到了所有的开阔地，而森林之子保有森林。\n" +
                "\n" +
                "四千年之后，异鬼的入侵削弱了和平协议。异鬼是一个来自于极北之地的神秘种族，他们横扫过维斯特洛的北部，留下了无尽的死亡和废墟，随之而来的是几乎长达一代人的寒夜和持续几十年的冬季。最终，先民和森林之子联合起来，在黎明之战中将异鬼击溃，并且在其南下的必经之路上建立起了巨大的绝境长城以防其回来。");
    }
}
