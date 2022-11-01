package ca.sheridancollege.changxinsun.stn991572545.anglerhelper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MainActivity extends AppCompatActivity {

    // Declare variables
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;

    ViewPagerFragmentAdapter adapter;

    // array for tab labels
    private String[] labels = new String[]{"Identify", "Log", "Measure","Regulation"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call function to initialize views
        init();

        // bind and set tabLayout to viewPager2 and set labels for every tab
        new TabLayoutMediator(tabLayout, viewPager2, (tab, position) -> {
            tab.setText(labels[position]);
        }).attach();

        // set default position to 1 instead of default 0
        viewPager2.setCurrentItem(0, false);
    }

    private void init() {
        // initialize tabLayout
        tabLayout = findViewById(R.id.tab_layout);
        // initialize viewPager2
        viewPager2 = findViewById(R.id.view_pager2);
        // create adapter instance
        adapter = new ViewPagerFragmentAdapter(this);
        // set adapter to viewPager2
        viewPager2.setAdapter(adapter);

        // remove default elevation of actionbar
        getSupportActionBar().setElevation(0);
    }
}
