package com.app.mgxchange.adapters;

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

import com.app.mgxchange.R;
import com.app.mgxchange.activities.ComplainListDetails;
import com.app.mgxchange.models.ComplainListModel;

import java.util.List;

public class ComplainListAdapter extends RecyclerView.Adapter<ComplainListAdapter.ViewHolder> {
    private final List<ComplainListModel> mData;
    private final LayoutInflater mInflater;
    Context context;
    public ComplainListAdapter(Context context, List<ComplainListModel> data) {
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
    public void onBindViewHolder(@NonNull final ComplainListAdapter.ViewHolder holder, final int i) {
        holder.complainMessage.setText(mData.get(i).getComplainMessage());
        holder.productName.setText(mData.get(i).getProductName());
        holder.moreDetails.setOnClickListener(v -> {
            Intent intent = new Intent(context, ComplainListDetails.class);
            intent.putExtra("complain_id", mData.get(i).getComplainID());
            intent.putExtra("reference", mData.get(i).getReference());
            intent.putExtra("message", mData.get(i).getComplainMessage());
            intent.putExtra("serial", mData.get(i).getSerialNo());
            intent.putExtra("status", mData.get(i).getComplainStatus());
            intent.putExtra("product_name", mData.get(i).getProductName());
            intent.putExtra("date", mData.get(i).getComplainDate());
            intent.putExtra("contact", mData.get(i).getContact());
            intent.putExtra("product_details", mData.get(i).getProductDetail());
            context.startActivity(intent);
        });
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, complainMessage;
        Button moreDetails;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tv_complain_list_item_type);
            complainMessage = itemView.findViewById(R.id.tv_complain_list_complain_message);
            moreDetails = itemView.findViewById(R.id.btn_complain_list_more_details);
            cardView = itemView.findViewById(R.id.card_user_complain);
        }
    }
}
