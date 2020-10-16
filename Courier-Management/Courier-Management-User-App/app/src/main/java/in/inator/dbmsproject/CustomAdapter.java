package in.inator.dbmsproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shashank.sony.fancytoastlib.FancyToast;
import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder > {

    ArrayList<single_data> mList = new ArrayList<single_data>();

    public CustomAdapter(ArrayList<single_data> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        // View view = inflater.inflate(R.layout.single_ball, viewGroup, false);
        View view = inflater.inflate(R.layout.single_order, viewGroup, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        final single_data _single = mList.get(i);
        holder.title.setText(_single.Title);
        holder.desc.setText("Package details: " +_single.Description);
        holder.address.setText("Shipping to: " + _single.Address);
        System.out.println("I am here as well");
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected class Holder extends RecyclerView.ViewHolder {

        TextView title, desc, address;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.it_title);
            desc = itemView.findViewById(R.id.it_desc);
            address = itemView.findViewById(R.id.it_address);
        }
    }

}