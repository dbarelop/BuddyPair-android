package org.aegee_zaragoza.buddypair.activity;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.aegee_zaragoza.buddypair.R;
import org.aegee_zaragoza.buddypair.data.Erasmus;
import org.aegee_zaragoza.buddypair.data.Peer;
import org.aegee_zaragoza.buddypair.data.adapter.ErasmusAdapter;
import org.aegee_zaragoza.buddypair.data.adapter.PeerAdapter;
import org.aegee_zaragoza.buddypair.database.DatabaseHelper;

import java.util.List;

public class ErasmusListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erasmus_list);

        final ListView listView = (ListView) findViewById(R.id.peer_list);
        AsyncTask<Void, Void, List<Erasmus>> listPopulator = new AsyncTask<Void, Void, List<Erasmus>>() {
            @Override
            protected List<Erasmus> doInBackground(Void... params) {
                return DatabaseHelper.getErasmus();
            }

            @Override
            protected void onPostExecute(final List<Erasmus> erasmusList) {
                ErasmusAdapter adapter = new ErasmusAdapter(ErasmusListActivity.this, R.layout.activity_peer_list_item, erasmusList);
                listView.setAdapter(adapter);
            }
        };
        listPopulator.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_erasmus_list, menu);
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
