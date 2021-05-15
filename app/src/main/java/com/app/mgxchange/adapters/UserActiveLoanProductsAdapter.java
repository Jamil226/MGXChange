package com.app.mgxchange.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mgxchange.R;
import com.app.mgxchange.activities.Dashboard;
import com.app.mgxchange.models.Products;
import com.app.mgxchange.utils.ApiUrls;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class UserActiveLoanProductsAdapter
        extends RecyclerView.Adapter<UserActiveLoanProductsAdapter.ViewHolder> {

    private final LayoutInflater mInflater;
    Context context;
    private List<Products> mData =  new ArrayList<>();

    public UserActiveLoanProductsAdapter(Context context, List<Products> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.snipped_user_active_loan_products,
                viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserActiveLoanProductsAdapter.ViewHolder holder,
                                 final int i) {
        holder.productName.setText(mData.get(i).getProductType());
        holder.productPice.setText("$" + mData.get(i).getProductPrice());
        holder.productDetails.setText(mData.get(i).getProductDetails());
        String url = ApiUrls.imgParentUrl + mData.get(i).getImageOne();
        Glide.with(context).load(url)
                .placeholder(R.drawable.image_default)
                .into(holder.productImage);
        String productID = mData.get(i).getProductID();
        String productName = mData.get(i).getProductType();
        String productDetails = mData.get(i).getProductDetails();
        String productYear = mData.get(i).getProductYear();
        String serialNo = mData.get(i).getSerialNumber();
        String productPrice = mData.get(i).getProductPrice();
        String contactNumber = mData.get(i).getContactNumber();
        String uploadingDate = mData.get(i).getUploadedOn();
        String userID = mData.get(i).getUserID();
        String imgOne = ApiUrls.imgParentUrl + mData.get(i).getImageOne();
        String imgTwo = ApiUrls.imgParentUrl + mData.get(i).getImageTwo();
        String imgThree = ApiUrls.imgParentUrl + mData.get(i).getImageThree();
        String imgFour = ApiUrls.imgParentUrl + mData.get(i).getImageFour();
        String imgFive = ApiUrls.imgParentUrl + mData.get(i).getImageFive();
        holder.cardView.setOnClickListener(v -> {
            Intent intent = new Intent(context, Dashboard.class);
            intent.putExtra("product_id", productID);
            intent.putExtra("user_id", userID);
            intent.putExtra("product_name", productName);
            intent.putExtra("product_details", productDetails);
            intent.putExtra("contact", contactNumber);
            intent.putExtra("uploading_date", uploadingDate);
            intent.putExtra("product_year", productYear);
            intent.putExtra("product_price", productPrice);
            intent.putExtra("image_one", imgOne);
            intent.putExtra("image_two", imgTwo);
            intent.putExtra("image_three", imgThree);
            intent.putExtra("image_four", imgFour);
            intent.putExtra("image_five", imgFive);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });


//        Toast.makeText(context, "ID : " + id, Toast.LENGTH_SHORT).show();
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName, productPice, productDetails;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.iv_user_active_loan_product_image);
            productName = itemView.findViewById(R.id.tv_user_active_loan_product_name);
            productPice = itemView.findViewById(R.id.tv_user_active_loan_product_price);
            productDetails = itemView.findViewById(R.id.tv_user_active_loan_product_details);
            cardView = itemView.findViewById(R.id.cv_user_active_loan_products);
        }
    }
}

