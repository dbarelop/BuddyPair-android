package org.aegee_zaragoza.buddypair.activity;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;
import org.aegee_zaragoza.buddypair.data.Student;
import org.aegee_zaragoza.buddypair.data.adapter.ErasmusAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ErasmusListFragment extends Fragment implements StudentFragment {
    private final List<Erasmus> erasmusList = new ArrayList<>();
    private final ErasmusAdapter adapter = new ErasmusAdapter(erasmusList);
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private Comparator<Student> comparator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_erasmus_list, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.erasmus_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        new ListUpdater().execute();

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_erasmus);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ListUpdater().execute();
            }
        });

        return rootView;
    }

    @Override
    public void sortBy(Comparator<Student> comparator) {
        this.comparator = comparator;
        List<Erasmus> sortedList = new ArrayList<>(erasmusList);
        Collections.sort(sortedList, comparator);
        //adapter.setModel(erasmusList);
        adapter.animateTo(sortedList);
        scrollToTop();
    }

    @Override
    public void filter(String text) {
        List<Erasmus> filteredList = new ArrayList<>();
        for (Erasmus e : erasmusList) {
            if (e.toString().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(e);
            }
        }
        adapter.animateTo(filteredList);
        scrollToTop();
    }

    @Override
    public void scrollToTop() {
        recyclerView.scrollToPosition(0);
    }

    private final class ListUpdater extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            List<Erasmus> erasmus = DatabaseHelper.getErasmus();
            erasmusList.clear();
            if (erasmus != null) {
                erasmusList.addAll(erasmus);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            List<Erasmus> sortedList = new ArrayList<>(erasmusList);
            if (comparator != null) {
                Collections.sort(sortedList, comparator);
            }
            //adapter.setModel(erasmusList);
            adapter.animateTo(sortedList);
            scrollToTop();
            refreshLayout.setRefreshing(false);
        }
    }
}
