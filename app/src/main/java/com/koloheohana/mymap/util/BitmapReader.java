package com.koloheohana.mymap.util;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.koloheohana.mymap.sns.MainTork;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 2017/05/22.
 */
public class BitmapReader {
    public static Bitmap notOnMemory(String file_path){
        return null;
    }
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPathFromUri(final Context context, final Uri uri) {
        boolean isAfterKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        // DocumentProvider
        if (isAfterKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            if ("com.android.externalstorage.documents".equals(
                    uri.getAuthority())) {// ExternalStorageProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }else {
                    return "/stroage/" + type +  "/" + split[1];
                }
            }else if ("com.android.providers.downloads.documents".equals(
                    uri.getAuthority())) {// DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            }else if ("com.android.providers.media.documents".equals(
                    uri.getAuthority())) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                contentUri = MediaStore.Files.getContentUri("external");
                final String selection = "_id=?";
                final String[] selectionArgs = new String[] {
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }else if ("content".equalsIgnoreCase(uri.getScheme())) {//MediaStore
            return getDataColumn(context, uri, null, null);
        }else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
            return uri.getPath();
        }
        return null;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {
        Cursor cursor = null;
        final String[] projection = {
                MediaStore.Files.FileColumns.DATA
        };
        try {
            cursor = context.getContentResolver().query(
                    uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                final int cindex = cursor.getColumnIndexOrThrow(projection[0]);
                return cursor.getString(cindex);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    /*
        bitmapを扱う時はここ
     */
    public static Bitmap getBitmap(Context context,Uri uri){

        Bitmap bitmap = null;
        try {
            InputStream in;
            in = context.getContentResolver().openInputStream(uri);
            BitmapFactory.Options imageOptions = new BitmapFactory.Options();
            imageOptions.inJustDecodeBounds = true;
            imageOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeStream(in);
            in.close();

            //仮設定　本来はスマホに合わせる
            int imageSizeMax = 500;
            in = context.getContentResolver().openInputStream(uri);
            float imageScaleWidth = (float)imageOptions.outWidth /imageSizeMax;
            float imageScaleHeight = (float)imageOptions.outHeight / imageSizeMax;

            // もしも、縮小できるサイズならば、縮小して読み込む
            if (imageScaleWidth > 2 && imageScaleHeight > 2) {
                BitmapFactory.Options imageOptions2 = new BitmapFactory.Options();

                // 縦横、小さい方に縮小するスケールを合わせる
                int imageScale = (int)Math.floor((imageScaleWidth > imageScaleHeight ? imageScaleHeight : imageScaleWidth));

                // inSampleSizeには2のべき上が入るべきなので、imageScaleに最も近く、かつそれ以下の2のべき上の数を探す
                for (int i = 2; i <= imageScale; i *= 2) {
                    imageOptions2.inSampleSize = i;
                }

                bitmap = BitmapFactory.decodeStream(in, null, imageOptions2);
                Log.v("image", "Sample Size: 1/" + imageOptions2.inSampleSize);
            } else {
                bitmap = BitmapFactory.decodeStream(in);
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
    public static void setSizeAndDirection(Context context,ImageView imgView, Bitmap bitmap, Uri uri) {
        String fileName = getPathFromUri(context,uri);
        if(bitmap == null){
            bitmap = getBitmap(context,uri);
        }
        try {
            ExifInterface exifInterface = null;
            exifInterface = new ExifInterface(fileName);
            int orientation = Integer.parseInt(exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION));

            imgView.setScaleType(ImageView.ScaleType.MATRIX);
            imgView.setImageBitmap(bitmap);

            // 画像の幅、高さを取得
            int wOrg = bitmap.getWidth();
            int hOrg = bitmap.getHeight();
            imgView.getLayoutParams();
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) imgView.getLayoutParams();

            float factor;
            Matrix mat = new Matrix();
            mat.reset();
            System.out.println("向きは"+orientation);
            switch (orientation) {
                case 1://only scaling
                    factor = (float) MainTork.ME.viewWidth / (float) wOrg;
                    mat.preScale(factor, factor);
                    lp.width = (int) (wOrg * factor);
                    lp.height = (int) (hOrg * factor);
                    break;
                case 2://flip vertical
                    factor = (float) MainTork.ME.viewWidth / (float) wOrg;
                    mat.postScale(factor, -factor);
                    mat.postTranslate(0, hOrg * factor);
                    lp.width = (int) (wOrg * factor);
                    lp.height = (int) (hOrg * factor);
                    break;
                case 3://rotate 180
                    mat.postRotate(180, wOrg / 2f, hOrg / 2f);
                    factor = (float) MainTork.ME.viewWidth / (float) wOrg;
                    mat.postScale(factor, factor);
                    lp.width = (int) (wOrg * factor);
                    lp.height = (int) (hOrg * factor);
                    break;
                case 4://flip horizontal
                    factor = (float) MainTork.ME.viewWidth / (float) wOrg;
                    mat.postScale(-factor, factor);
                    mat.postTranslate(wOrg * factor, 0);
                    lp.width = (int) (wOrg * factor);
                    lp.height = (int) (hOrg * factor);
                    break;
                case 5://flip vertical rotate270
                    mat.postRotate(270, 0, 0);
                    factor = (float) MainTork.ME.viewWidth / (float) hOrg;
                    mat.postScale(factor, -factor);
                    lp.width = (int) (hOrg * factor);
                    lp.height = (int) (wOrg * factor);
                    break;
                case 6://rotate 90
                    mat.postRotate(90, 0, 0);
                    factor = (float) MainTork.ME.viewWidth / (float) hOrg;
                    mat.postScale(factor, factor);
                    mat.postTranslate(hOrg * factor, 0);
                    lp.width = (int) (hOrg * factor);
                    lp.height = (int) (wOrg * factor);
                    break;
                case 7://flip vertical, rotate 90
                    mat.postRotate(90, 0, 0);
                    factor = (float) MainTork.ME.viewWidth / (float) hOrg;
                    mat.postScale(factor, -factor);
                    mat.postTranslate(hOrg * factor, wOrg * factor);
                    lp.width = (int) (hOrg * factor);
                    lp.height = (int) (wOrg * factor);
                    break;
                case 8://rotate 270
                    mat.postRotate(270, 0, 0);
                    factor = (float) MainTork.ME.viewWidth / (float) hOrg;
                    mat.postScale(factor, factor);
                    mat.postTranslate(0, wOrg * factor);
                    lp.width = (int) (hOrg * factor);
                    lp.height = (int) (wOrg * factor);
                    break;
            }
            lp.height /= 2;
            lp.width /= 2;
            System.out.println("幅"+lp.width);
            System.out.println("高さ"+lp.height);
            imgView.setLayoutParams(lp);
            imgView.setImageMatrix(mat);
            imgView.invalidate();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
