package com.zoker.lib.image.Multiimagechooser.model;

import java.io.Serializable;

/**
 * Created by mummyding on 15-11-3.
 */
public class ImageModel implements Serializable {
    private String imageUri;
    private boolean isChecked;
    private int ID;

    public int getID() {
        return ID;
    }

    public ImageModel setID(int ID) {
        this.ID = ID;
        return this;
    }

    public String getImageUri() {
        return imageUri;
    }

    public ImageModel setImageUri(String imageUri) {
        this.imageUri = imageUri;
        return this;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
