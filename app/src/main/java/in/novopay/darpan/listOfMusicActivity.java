package in.novopay.darpan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.novopay.khushpreetsingh.mymusic.R;

import hugo.weaving.DebugLog;

/**
 * Created by khushpreetsingh on 8/4/15.
 */
public class listOfMusicActivity extends FragmentActivity {
    private ViewPager viewPager;
    private final int NUMBER_OF_PAGES = 2;

    private MusicListFragmentStatePagerAdapter musicListFragmentStatePagerAdapter;
    @Override
    @DebugLog
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_viewpage);

        viewPager = (ViewPager) findViewById(R.id.activity_viewpager_viewpager);
        musicListFragmentStatePagerAdapter = new MusicListFragmentStatePagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(musicListFragmentStatePagerAdapter);
    }

    private class MusicListFragmentStatePagerAdapter extends FragmentStatePagerAdapter{

        public MusicListFragmentStatePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch(position){
                case 0:
                    return new FirstFragment();
                case 1:
                    return new SecondFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUMBER_OF_PAGES;
        }
    }
}
