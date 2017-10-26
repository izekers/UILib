package com.zoker.lib.image.Multiimagechooser.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.zoker.lib.R;
import com.zoker.lib.image.Multiimagechooser.model.ImageModel;
import com.zoker.lib.image.Multiimagechooser.ui.ImageDetailsActivity;
import com.zoker.lib.image.loader.PhotoTrans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mummyding on 15-11-3.
 */
public class SelectImageAdapter extends BasicAdapter implements AdapterView.OnItemClickListener {
    public List<ImageModel> checkedList = new ArrayList<>();

    public SelectImageAdapter(Context mContext) {
        super(mContext);
    }

    private int maxNumber=9;
    private int currentNumber=0;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        final ImageModel imageBean = (ImageModel) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_choose_image,parent,false);
            viewHolder.image = (ImageView) convertView.findViewById(R.id.image);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.checkBox.setChecked(imageBean.isChecked());
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ViewHolder finalViewHolder = viewHolder;

        finalViewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    currentNumber++;
                    if (currentNumber>maxNumber){
                        finalViewHolder.checkBox.setChecked(false);
                        Toast.makeText(finalViewHolder.checkBox.getContext(),"最多只能选择"+maxNumber+"张",Toast.LENGTH_SHORT).show();
                        currentNumber--;
                        return;
                    }
                }
                else
                    currentNumber--;

                    imageBean.setIsChecked(isChecked);
                if (checkedList.contains(imageBean)) {
                    if (imageBean.isChecked() == false) {
                        checkedList.remove(imageBean);
                    }
                } else if (imageBean.isChecked()) {
                    checkedList.add(imageBean);
                }
                ((AppCompatActivity) mContext).getSupportActionBar().setTitle("选择图片" + "(" + checkedList.size()+"/"+maxNumber + ")");
            }
        });
        ((AppCompatActivity) mContext).getSupportActionBar().setTitle("选择图片(" + checkedList.size() + ")");

        finalViewHolder.checkBox.setChecked(imageBean.isChecked());
        PhotoTrans.loader(mContext).load(Uri.parse(imageBean.getImageUri())).into(viewHolder.image);
//        Glide.with(mContext).load(Uri.parse(imageBean.getImageUri())).into(viewHolder.image);
//        Glide.with(mContext).load(imageBean.getImageUri()).into(viewHolder.image);
//        viewHolder.image.setLayoutParams(frameParams);
//        viewHolder.image.setImageURI(Uri.parse(imageBean.getImageUri()));
        return convertView;
    }

    public void setCheckedList(List<ImageModel> imageList){
        this.checkedList = imageList;
        if (this.checkedList == null)
            currentNumber = 0;
        else
            currentNumber=checkedList.size();
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, ImageDetailsActivity.class);
        intent.putExtra("imageUri", ((ImageModel) getItem(position)).getImageUri().toString());
        mContext.startActivity(intent);
    }

    class ViewHolder {
        ImageView image;
        CheckBox checkBox;
    }

    public void setMaxNumber(int maxNumber) {
        this.maxNumber = maxNumber;
    }
}
