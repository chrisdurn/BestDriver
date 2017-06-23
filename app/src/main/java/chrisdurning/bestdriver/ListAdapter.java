package chrisdurning.bestdriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by chrisdurning on 13/06/2017.
 */

public class ListAdapter extends ArrayAdapter<String> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_item, null);
        }

        String p = getItem(position);

        if (p.equals("this is row 1")) {
            TextView tt1 = (TextView) v.findViewById(R.id.some_text);
            ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

            if (tt1 != null) {
                tt1.setText(p);
                imageView.setImageResource(R.drawable.messenger);
            }
        } else if(p.equals("this is row 2")){
            TextView tt2 = (TextView) v.findViewById(R.id.some_text);
            ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

            if (tt2 != null) {
                tt2.setText(p);
                imageView.setImageResource(R.drawable.snapchat_white);
            }

        } else if(p.equals("this is row 3")){
            TextView tt2 = (TextView) v.findViewById(R.id.some_text);
            ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

            if (tt2 != null) {
                tt2.setText(p);
                imageView.setImageResource(R.drawable.gmail);
            }

        } else if(p.equals("this is row 4")){
            TextView tt2 = (TextView) v.findViewById(R.id.some_text);
            ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

            if (tt2 != null) {
                tt2.setText(p);
                imageView.setImageResource(R.drawable.twitter);
            }

        } else if(p.equals("this is row 5")){
            TextView tt2 = (TextView) v.findViewById(R.id.some_text);
            ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

            if (tt2 != null) {
                tt2.setText(p);
                imageView.setImageResource(R.drawable.text);
            }

        } else if(p.equals("this is row 6")){
            TextView tt2 = (TextView) v.findViewById(R.id.some_text);
            ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

            if (tt2 != null) {
                tt2.setText(p);
                imageView.setImageResource(R.drawable.phonecall);
            }

        }

        return v;
    }

}
