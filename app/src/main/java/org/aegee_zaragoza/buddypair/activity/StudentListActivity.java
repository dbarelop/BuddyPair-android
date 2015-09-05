package org.aegee_zaragoza.buddypair.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Student;

import java.util.Comparator;

public class StudentListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    // TODO: hide tabs when using search function
    private ViewPager viewPager;
    private Menu menu;
    private int[] sortBy = {0, 0};
    private int[] order = {1, 1};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.student_list_toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.student_list_viewpager);
        PagerAdapter adapter = new StudentListPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.student_list_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                StudentFragment currentFragment = (StudentFragment) ((StudentListPagerAdapter) viewPager.getAdapter()).getItem(viewPager.getCurrentItem());
                currentFragment.scrollToTop();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        this.menu = menu;

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setOnQueryTextListener(this);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                final int i = viewPager.getCurrentItem();
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        order[i] = sortBy[i] == which ? -1 * order[i] : 1;     // Toggle order
                        if (sortBy[i] == which) {
                            startAnimation(item);
                        }
                        sortBy[i] = which;
                        // TODO: workaround
                        sortData();
                        dialog.dismiss();
                    }
                };
                CharSequence[] sortOptions = {"Name", "Surname", "Studies", "Faculty", "Register date"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Sort by");
                builder.setSingleChoiceItems(sortOptions, sortBy[i], listener);
                builder.show();
                break;
            case R.id.action_settings:

                break;
            default:
                return false;
        }
        return true;
    }

    private void sortData() {
        final int i = viewPager.getCurrentItem();
        StudentFragment currentFragment = (StudentFragment) ((StudentListPagerAdapter) viewPager.getAdapter()).getItem(i);
        Comparator<Student> comparator;
        switch (sortBy[i]) {
            case 0: // Name
            default:
                comparator = new Comparator<Student>() {
                    @Override
                    public int compare(Student lhs, Student rhs) {
                        return lhs.getName().compareTo(rhs.getName()) * order[i];
                    }
                };
                break;
            case 1: // Surname
                comparator = new Comparator<Student>() {
                    @Override
                    public int compare(Student lhs, Student rhs) {
                        return lhs.getSurname().compareTo(rhs.getSurname()) * order[i];
                    }
                };
                break;
            case 2: // Studies
                comparator = new Comparator<Student>() {
                    @Override
                    public int compare(Student lhs, Student rhs) {
                        return lhs.getStudies().compareTo(rhs.getStudies()) * order[i];
                    }
                };
                break;
            case 3: // Faculty
                comparator = new Comparator<Student>() {
                    @Override
                    public int compare(Student lhs, Student rhs) {
                        return lhs.getFaculty().compareTo(rhs.getFaculty()) * order[i];
                    }
                };
                break;
            case 4: // Register date
                comparator = new Comparator<Student>() {
                    @Override
                    public int compare(Student lhs, Student rhs) {
                        return lhs.getRegister_date().compareTo(rhs.getRegister_date()) * order[i];
                    }
                };
        }
        currentFragment.sortBy(comparator);
    }

    private void startAnimation(MenuItem item) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        ImageView iv = (ImageView) inflater.inflate(R.layout.iv_sort, null);
        Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_sort);
        rotation.setRepeatCount(0);
        rotation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                MenuItem m = menu.findItem(R.id.action_sort);
                if (m.getActionView() != null) {
                    // Remove the animation
                    m.getActionView().clearAnimation();
                    m.setActionView(null);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        iv.startAnimation(rotation);
        item.setActionView(iv);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final int i = viewPager.getCurrentItem();
        StudentListPagerAdapter adapter = (StudentListPagerAdapter) viewPager.getAdapter();
        StudentFragment currentFragment = (StudentFragment) adapter.getItem(i);
        currentFragment.filter(newText);
        return true;
    }

    public static class StudentListPagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = {"Peers", "Erasmus"};
        private Fragment[] fragments = new Fragment[]{new PeerListFragment(), new ErasmusListFragment()};

        public StudentListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }
}
