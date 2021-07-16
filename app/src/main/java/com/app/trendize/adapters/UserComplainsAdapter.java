package com.app.trendize.adapters;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.trendize.R;
import com.app.trendize.activities.ComplainListDetails;
import com.app.trendize.models.Complains;

import java.util.List;

public class UserComplainsAdapter
        extends RecyclerView.Adapter<UserComplainsAdapter.ViewHolder> {

    private final List<Complains> mData;
    private final LayoutInflater mInflater;
    Context context;

    public UserComplainsAdapter(Context context, List<Complains> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.snipped_complain_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserComplainsAdapter.ViewHolder holder, final int i) {
        holder.productName.setText(mData.get(i).getProductType());
        holder.complainMessage.setText(mData.get(i).getComplainMessage());
        holder.moreDetails.setOnClickListener(v -> {
            Intent intent = new Intent(context, ComplainListDetails.class);
            intent.putExtra("complain_id", mData.get(i).getComplainID());
            intent.putExtra("reference", mData.get(i).getComplainReference());
            intent.putExtra("message", mData.get(i).getComplainMessage());
            intent.putExtra("serial", mData.get(i).getSerialNumber());
            intent.putExtra("status", mData.get(i).getComplainStatus());
            intent.putExtra("product_name", mData.get(i).getProductType());
            intent.putExtra("complain_date", mData.get(i).getComplainDate());
            intent.putExtra("contact", mData.get(i).getUserContact());
            intent.putExtra("product_details", mData.get(i).getProductDetails());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        //Toast.makeText(context, "ID : " + complainID, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button moreDetails;
        TextView productName, complainMessage;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            moreDetails = itemView.findViewById(R.id.btn_complain_list_more_details);
            productName = itemView.findViewById(R.id.tv_complain_list_item_type);
            complainMessage = itemView.findViewById(R.id.tv_complain_list_complain_message);
            cardView = itemView.findViewById(R.id.card_user_complain);
        }
    }

}
