package org.aegee_zaragoza.buddypair.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Student;

import java.util.Comparator;

public class StudentListActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private int sortBy = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new StudentListPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_student_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort:
                DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sortBy = which;
                        // TODO: workaround
                        StudentFragment currentFragment = (StudentFragment) ((StudentListPagerAdapter) viewPager.getAdapter()).getItem(viewPager.getCurrentItem());
                        Comparator<Student> comparator;
                        switch (which) {
                            case 0: // Name
                            default:
                                comparator = new Comparator<Student>() {
                                    @Override
                                    public int compare(Student lhs, Student rhs) {
                                        return lhs.getName().compareTo(rhs.getName());
                                    }
                                };
                                break;
                            case 1: // Surname
                                comparator = new Comparator<Student>() {
                                    @Override
                                    public int compare(Student lhs, Student rhs) {
                                        return lhs.getSurname().compareTo(rhs.getSurname());
                                    }
                                };
                                break;
                            case 2: // Studies
                                comparator = new Comparator<Student>() {
                                    @Override
                                    public int compare(Student lhs, Student rhs) {
                                        return lhs.getStudies().compareTo(rhs.getStudies());
                                    }
                                };
                                break;
                            case 3: // Faculty
                                comparator = new Comparator<Student>() {
                                    @Override
                                    public int compare(Student lhs, Student rhs) {
                                        return lhs.getFaculty().compareTo(rhs.getFaculty());
                                    }
                                };
                                break;
                            case 4: // Register date
                                comparator = new Comparator<Student>() {
                                    @Override
                                    public int compare(Student lhs, Student rhs) {
                                        return lhs.getRegister_date().compareTo(rhs.getRegister_date());
                                    }
                                };
                        }
                        currentFragment.sortBy(comparator);
                        dialog.dismiss();
                    }
                };
                CharSequence[] sortOptions = {"Name", "Surname", "Studies", "Faculty", "Register date"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Sort by");
                builder.setSingleChoiceItems(sortOptions, sortBy, listener);
                builder.show();
                break;
            case R.id.action_settings:

                break;
            default:
                return false;
        }
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
