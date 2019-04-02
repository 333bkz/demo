package com.bkz.demo.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.Scroller;

import com.bkz.demo.R;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

public class CyclePagerAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener {

    private LinkedList<View> views;
    private List<Drawable> drawables;
    private ViewPager viewPager;
    private MScroller scroller;
    private int curPageSelected;

    public CyclePagerAdapter(@NonNull Context context, List<Drawable> drawables, ViewPager viewPager) {
        this.drawables = drawables;
        this.viewPager = viewPager;

        final LayoutInflater inflater = LayoutInflater.from(context);

        viewPager.addOnPageChangeListener(this);

        if (drawables != null
                && !drawables.isEmpty()) {
            int size = drawables.size();

            views = new LinkedList<>();
            ImageView view = (ImageView) inflater.inflate(R.layout.item_drawable_list, null);
            view.setImageDrawable(drawables.get(size - 1));
            views.add(view);

            if (size > 1) {
                for (Drawable drawable : drawables) {
                    view = (ImageView) inflater.inflate(R.layout.item_drawable_list, null);
                    view.setImageDrawable(drawable);
                    views.add(view);
                }

                view = (ImageView) inflater.inflate(R.layout.item_drawable_list, null);
                view.setImageDrawable(drawables.get(0));
                views.add(view);
            }
        }


        //设置scroller
        final Interpolator sInterpolator = new Interpolator() {
            public float getInterpolation(float t) {
                t -= 1.0f;
                return t * t * t * t * t + 1.0f;
            }
        };
        scroller = new MScroller(context, sInterpolator);
        Class<ViewPager> cl = ViewPager.class;
        try {
            Field field = cl.getDeclaredField("mScroller");
            field.setAccessible(true);
            field.set(viewPager, scroller);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(views.get(position));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        curPageSelected = i;
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        if (i == 0) {//跳转完成
            if (views.size() > 1) {
                if (curPageSelected < 1) { //首位之前，跳转到末尾
                    scroller.setNoDuration(true);
                    viewPager.setCurrentItem(drawables.size(), false);
                    scroller.setNoDuration(false);
                } else if (curPageSelected > drawables.size()) { //末位之后，跳转到首位
                    scroller.setNoDuration(true);
                    viewPager.setCurrentItem(1, false);
                    scroller.setNoDuration(false);
                }
            }
        }
    }

    public class MScroller extends Scroller {

        private boolean noDuration;

        public void setNoDuration(boolean noDuration) {
            this.noDuration = noDuration;
        }

        public MScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            //界面滑动不需要时间间隔
            super.startScroll(startX, startY, dx, dy, noDuration ? 0 : duration);
        }
    }

    public void jumpToNextPager() {
        int count = getCount();
        if (count > 2) {
            int index = viewPager.getCurrentItem();
            viewPager.setCurrentItem(index + 1, true);
        }
    }
}
