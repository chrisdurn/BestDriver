package chrisdurning.bestdriver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chrisdurning on 13/06/2017.
 */

public class ListAdapter extends ArrayAdapter<String> {
    private ArrayList<String> mTimes;
    private ArrayList<String> mMessages;

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<String> items, ArrayList<String> times, ArrayList<String> messages) {
        super(context, resource, items);
        this.mTimes = times;
        this.mMessages = messages;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.row_item, null);
        }

        String number = getItem(position);
        String time = mTimes.get(position);
        String message = mMessages.get(position);

        TextView numbersTextview = (TextView) v.findViewById(R.id.numbers);
        TextView timeTextview = (TextView) v.findViewById(R.id.times);
        TextView messageTextview = (TextView) v.findViewById(R.id.messages);

        ImageView imageView = (ImageView) v.findViewById(R.id.supressed_info_type);

        //set image and message is message not equal ""
        if(message.equals("")) {
            imageView.setImageResource(R.drawable.phonecall);
            messageTextview.setText(" ");
        } else {
            imageView.setImageResource(R.drawable.text);
            messageTextview.setText(message);
        }

        //set number
        if (numbersTextview != null) {
            numbersTextview.setText(number);
        }

        //set time
        if(timeTextview != null) {
            timeTextview.setText(time);
        }

        return v;
    }

}
