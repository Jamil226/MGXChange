package com.app.mgxchange.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.mgxchange.R;
import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapterClass extends SliderViewAdapter<SliderAdapterClass.SliderAdapterViewHolder> {

    private final Context context;

    public SliderAdapterClass(Context context) {
        this.context = context;
    }

    @Override
    public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.snipped_image_slider_layout_item, null);
        return new SliderAdapterViewHolder(inflate);

    }

    @Override
    public void onBindViewHolder(SliderAdapterViewHolder viewHolder, int position) {
//        viewHolder.textViewDescription.setText("Product");

        switch (position) {
            case 1:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image_default)
                        .placeholder(R.drawable.image_default)
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image_default)
                        .placeholder(R.drawable.image_default)
                        .into(viewHolder.imageViewBackground);
                break;
            case 3:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image_default)
                        .placeholder(R.drawable.image_default)
                        .into(viewHolder.imageViewBackground);
                break;
            case 4:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image_default)
                        .placeholder(R.drawable.image_default)
                        .into(viewHolder.imageViewBackground);
                break;

            default:
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.image_default)
                        .placeholder(R.drawable.image_default)
                        .into(viewHolder.imageViewBackground);
                break;
        }

    }

    @Override
    public int getCount() {
        return 5;
    }

    class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
        View itemView;
        ImageView imageViewBackground;
        TextView textViewDescription;

        public SliderAdapterViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }
}