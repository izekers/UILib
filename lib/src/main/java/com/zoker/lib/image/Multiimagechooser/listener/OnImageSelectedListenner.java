package com.zoker.lib.image.Multiimagechooser.listener;

import com.zoker.lib.image.Multiimagechooser.model.ImageModel;

import java.util.List;

/**
 * Created by Administrator on 2017/9/4.
 */

public interface OnImageSelectedListenner {
    void OnImageSelected(int requestCode, List<ImageModel> checkedList);
}
