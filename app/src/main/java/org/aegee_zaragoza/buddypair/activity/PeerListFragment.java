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
import org.aegee_zaragoza.buddypair.data.Peer;
import org.aegee_zaragoza.buddypair.data.Student;
import org.aegee_zaragoza.buddypair.data.adapter.PeerAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PeerListFragment extends Fragment implements StudentFragment {
    private final List<Peer> peerList = new ArrayList<>();
    private final PeerAdapter adapter = new PeerAdapter(peerList);
    private SwipeRefreshLayout refreshLayout;
    private Comparator<Student> comparator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_peer_list, container, false);

        final RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.peer_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        new ListUpdater().execute();

        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh_peers);
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
        Collections.sort(peerList, comparator);
        adapter.notifyDataSetChanged();
    }

    private final class ListUpdater extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            List<Peer> peers = DatabaseHelper.getPeers();
            peerList.clear();
            if (peers != null) {
                peerList.addAll(peers);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (comparator != null) {
                Collections.sort(peerList, comparator);
            }
            adapter.notifyDataSetChanged();
            refreshLayout.setRefreshing(false);
        }
    }
}
