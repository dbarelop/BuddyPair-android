package org.aegee_zaragoza.buddypair.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Peer;
import org.aegee_zaragoza.buddypair.data.adapter.PeerAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.List;

public class PeerListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peer_list);

        final ListView listView = (ListView) findViewById(R.id.peers_list);
        AsyncTask<Void, Void, List<Peer>> listPopulator = new AsyncTask<Void, Void, List<Peer>>() {
            @Override
            protected List<Peer> doInBackground(Void... params) {
                return DatabaseHelper.getPeers();
            }

            @Override
            protected void onPostExecute(final List<Peer> peerList) {
                PeerAdapter adapter = new PeerAdapter(PeerListActivity.this, R.layout.activity_peer_list_item, peerList);
                listView.setAdapter(adapter);
            }
        };
        listPopulator.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_peer_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
