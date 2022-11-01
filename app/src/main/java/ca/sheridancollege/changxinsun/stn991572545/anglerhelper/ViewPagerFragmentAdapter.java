package ca.sheridancollege.changxinsun.stn991572545.anglerhelper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    // array for tab labels
    private String[] labels = new String[]{"Identify", "Log", "Measure","Regulation"};

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    // return fragments at every position
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new IdentifyFragment();
            case 1:
                return new LogFragment();
            case 2:
                return new MeasureFragment();
            case 3:
                return new RegulationFragment();
        }
        return new IdentifyFragment();
    }

    // return total number of tabs in our case we have 3
    @Override
    public int getItemCount() {
        return labels.length;
    }
}