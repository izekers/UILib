package com.zoker.lib.image.Multiimagechooser.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zoker.lib.R;
import com.zoker.lib.image.Multiimagechooser.model.ImageModel;
import com.zoker.lib.image.Multiimagechooser.ui.ImageDetailsActivity;


/**
 * Created by mummyding on 15-11-3.
 */
public class ShowImageAdapter extends BasicAdapter {
    public ShowImageAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ImageModel imageBean = (ImageModel) getItem(position);
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.item_show_image, null);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.close_btn = (ImageView) convertView.findViewById(R.id.close_btn);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.image.setLayoutParams(frameParams);
        viewHolder.image.setImageURI(Uri.parse(imageBean.getImageUri()));

        viewHolder.close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getList().remove(position);
                updateUI();
            }
        });
        viewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ImageDetailsActivity.class);
                intent.putExtra("imageUri", imageBean.getImageUri().toString());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView image;
        ImageView close_btn;
    }

}
