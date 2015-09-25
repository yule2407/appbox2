package cn.com.hewoyi.appbox;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.List;

class ViewPagerAdapter extends FragmentStatePagerAdapter {

    //生成的fragment个数
    static int NUM_ITEMS;
    static int GRID_ITEM_COUNT = 12;

    Context mContext;
    LinearLayout group;//导航圆点布局
    ViewPager mViewPager;//滑动布局
    private ImageView[] imageViews;//导航图片
    private ImageView imageView;
    private List<String> data;

    public ViewPagerAdapter(FragmentManager fm, Context context, LinearLayout group, ViewPager viewPager, List<String> list) {
        super(fm);
        this.mContext = context;
        this.group = group;
        this.mViewPager = viewPager;
        this.data = list;
        NUM_ITEMS = list.size() / GRID_ITEM_COUNT + 1;//一个fragment一个gridview，一个gridview有12个图标
        initCirclePoint();
        mViewPager.setOnPageChangeListener(new AdPageChangeListener());//滑动监听

    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        //new新的Fragment
        return new ViewPagerFragment(position, data.subList(position * GRID_ITEM_COUNT,
                        position * GRID_ITEM_COUNT + GRID_ITEM_COUNT > data.size() ? data.size() : position * GRID_ITEM_COUNT + GRID_ITEM_COUNT));

    }

    //动态增长小圆点导航
    private void initCirclePoint() {
        imageViews = new ImageView[NUM_ITEMS];
        //小圆点图标
        for (int i = 0; i < NUM_ITEMS; i++) {
            //创建一个ImageView, 并设置宽高. 将该对象放入到数组中
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(20, 20));
            imageViews[i] = imageView;

            //初始值, 默认第0个选中
            if (i == 0) {
                imageViews[i]
                        .setBackgroundResource(android.R.drawable.presence_online);
            } else {
                imageViews[i]
                        .setBackgroundResource(android.R.drawable.presence_invisible);
            }
            //将小圆点放入到布局中
            group.addView(imageViews[i]);
        }
    }

    /**
     * ViewPager 页面改变监听器
     */
    private final class AdPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 页面滚动状态发生改变的时候触发
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        /**
         * 页面滚动的时候触发
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        /**
         * 页面选中的时候触发
         */
        @Override
        public void onPageSelected(int arg0) {
            //重新设置原点布局集合
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[arg0]
                        .setBackgroundResource(android.R.drawable.presence_online);
                if (arg0 != i) {
                    imageViews[i]
                            .setBackgroundResource(android.R.drawable.presence_invisible);
                }
            }
        }
    }
}
