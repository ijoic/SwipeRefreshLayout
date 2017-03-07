package demo.demievil.swiperefreshlayout;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.demievil.swiprefreshlayout.RefreshLayout;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity {

    private RefreshLayout mRefreshLayout;
    private ListView mListView;
    private ArrayAdapter<String> mArrayAdapter;
    private ArrayList<String> values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.demievil.swiperefreshlayout.R.layout.activity_main);

        mRefreshLayout = (RefreshLayout) findViewById(com.demievil.swiperefreshlayout.R.id.swipe_container);
        mListView = (ListView) findViewById(com.demievil.swiperefreshlayout.R.id.list);
        mRefreshLayout.setFooterView(this, mListView, com.demievil.swiperefreshlayout.R.layout.listview_footer);

        values = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            values.add("Item " + i);
        }
        mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        mListView.setAdapter(mArrayAdapter);

        mRefreshLayout.setColorSchemeResources(com.demievil.swiperefreshlayout.R.color.google_blue,
                com.demievil.swiperefreshlayout.R.color.google_green,
                com.demievil.swiperefreshlayout.R.color.google_red,
                com.demievil.swiperefreshlayout.R.color.google_yellow);

        mRefreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        values.add(0, "Swipe Down to Refresh " + values.size());
                        mArrayAdapter.notifyDataSetChanged();
                        mRefreshLayout.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadListener(new RefreshLayout.OnLoadListener() {
            @Override
            public void onLoad() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        values.add("Swipe Up to Load More "+ values.size());
                        mArrayAdapter.notifyDataSetChanged();
                        mRefreshLayout.setLoading(false);
                    }
                }, 2000);
            }
        });
    }
}
