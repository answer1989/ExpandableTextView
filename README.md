# ExpandableTextView
类似微信的展开全文控件

使用如下：

```xml
<com.answer.expandabletextview.lib.ExpandableTextView
        android:id="@+id/text_view_expandable"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingBottom="10dp"
        android:paddingLeft="15dp"
        android:paddingRight="15dp"
        android:paddingTop="20dp"
        app:extContentMaxLine="5"
        app:extContentTextColor="#ffc320"
        app:extContentTextSize="13dp"
        app:extLabelCollapseDrawableLeft="@drawable/ic_collapse_all"
        app:extLabelCollapseText="收起全文"
        app:extLabelExpandDrawableLeft="@drawable/ic_expand_all"
        app:extLabelExpandText="展开全文"
        app:extLabelPosition="center"
        app:extLabelTextColor="#009dff"
        app:extLabelTextSize="13dp"></com.answer.expandabletextview.lib.ExpandableTextView>
```

* extContentMaxLine 最大的显示行数
* extContentTextColor 显示的文本颜色
* extContentTextSize 显示的文本大小
* extLabelCollapseDrawableLeft 展开状态时的文本图标
* extLabelCollapseText 展开状态时的文本
* extLabelExpandDrawableLeft 收起状态时的文本图标
* extLabelExpandText 收起状态的文本
* extLabelPosition 展开/收起文本的位置，有 left，right，center
* extLabelTextColor 展开/收起文本的颜色
* extLabelTextSize 展开/收起文本的字体大小

效果图：

![](http://7q5er6.com1.z0.glb.clouddn.com/device-2016-05-13-160724.png?imageView/2/w/300/q/90)![](http://7q5er6.com1.z0.glb.clouddn.com/device-2016-05-13-160743.png?imageView/2/w/300/q/90)
