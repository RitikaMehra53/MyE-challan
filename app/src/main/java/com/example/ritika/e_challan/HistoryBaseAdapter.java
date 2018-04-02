package com.example.ritika.e_challan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by Ritika on 30-Mar-18.
 */

public class HistoryBaseAdapter extends BaseAdapter{

    Context ctx;
    List<ChallanDetails> details;
    HistoryBaseAdapter(Context ctx, List<ChallanDetails> details){

        this.ctx=ctx;
        this.details=details;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = inflater.inflate(R.layout.history_view, null);
       /* TextView textViewChallanNo = (TextView) view1.findViewById(R.id.textViewChallanNo);
        TextView textViewName= (TextView) view1.findViewById(R.id.textViewName);

        textViewChallanNo.setText(details.get(i).getChallanNo()+ "");

        textViewName.setText(details.get(i).getName()+ "");*/
        return view1;
    }


}
