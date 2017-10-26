package com.zoker.lib.image.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/8/30.
 */

public class PhotoUtil {

    public static boolean compressionBitmapAndSave(Bitmap srcBitmap, int quality, String savePath) {
        if (srcBitmap != null) {
            try {
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
                srcBitmap.compress(Bitmap.CompressFormat.JPEG, quality, bos);
                bos.flush();
                bos.close();
                srcBitmap.recycle();
                File targetPicFile = new File(savePath);
//				File srcPicFile = new File(picPath);
//				double rate = (double) srcPicFile.length() / (double) targetPicFile.length();
//				LogPrint.debug("原始图片大小: " + srcPicFile.length() + "; 最终图片大小: " + targetPicFile.length() + "; 压缩比: " + rate);
                return true;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static int getRotation(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        int rotation = 0;
        if ("image/jpeg".equalsIgnoreCase(opts.outMimeType)) {
            try {
                ExifInterface exif = new ExifInterface(path);
                int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
                switch (exifOrientation) {
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                    case ExifInterface.ORIENTATION_NORMAL:
                        rotation = 0;
                        break;
                    case ExifInterface.ORIENTATION_TRANSVERSE:
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotation = 90;
                        break;
                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotation = 180;
                        break;
                    case ExifInterface.ORIENTATION_TRANSPOSE:
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotation = 270;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return rotation;
    }

    public static String getRealFilePath(final Context context, final Uri uri ) {
        if ( null == uri ) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if ( scheme == null )
            data = uri.getPath();
        else if ( ContentResolver.SCHEME_FILE.equals( scheme ) ) {
            data = uri.getPath();
        } else if ( ContentResolver.SCHEME_CONTENT.equals( scheme ) ) {
            Cursor cursor = context.getContentResolver().query( uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null );
            if ( null != cursor ) {
                if ( cursor.moveToFirst() ) {
                    int index = cursor.getColumnIndex( MediaStore.Images.ImageColumns.DATA );
                    if ( index > -1 ) {
                        data = cursor.getString( index );
                    }
                }
                cursor.close();
            }
        }
        return data;
    }
}
