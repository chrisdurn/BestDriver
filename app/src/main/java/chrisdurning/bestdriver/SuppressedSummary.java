package chrisdurning.bestdriver;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SuppressedSummary extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppressed_summary);

        List<String> list = new ArrayList<>();
        list.add(0, "this is row 1");
        list.add(1, "this is row 2");
        list.add(2, "this is row 3");
        list.add(3, "this is row 4");
        list.add(4, "this is row 5");
        list.add(5, "this is row 6");

        ListView yourListView = (ListView) findViewById(R.id.suppressed_list);

// get data from the table by the ListAdapter
        ListAdapter customAdapter = new ListAdapter(this, R.layout.row_item, list);

        yourListView .setAdapter(customAdapter);
    }

}
