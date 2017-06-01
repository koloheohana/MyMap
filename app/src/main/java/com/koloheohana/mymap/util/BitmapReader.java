package com.koloheohana.mymap.util;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.media.ExifInterface;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.koloheohana.mymap.sns.MainTork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by User on 2017/05/22.
 */
public class BitmapReader {

    public static float imageDisplayWidth = Window.WINDOW_SIZE.x - (Window.WINDOW_SIZE.x/3);
    public static float imageDisplayHeight = Window.WINDOW_SIZE.y / 2;
    public static Bitmap notOnMemory(String file_path) {
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
                } else {
                    return "/stroage/" + type + "/" + split[1];
                }
            } else if ("com.android.providers.downloads.documents".equals(
                    uri.getAuthority())) {// DownloadsProvider
                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                return getDataColumn(context, contentUri, null, null);
            } else if ("com.android.providers.media.documents".equals(
                    uri.getAuthority())) {// MediaProvider
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];
                Uri contentUri = null;
                contentUri = MediaStore.Files.getContentUri("external");
                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {//MediaStore
            return getDataColumn(context, uri, null, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {// File
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
    public static Bitmap getBitmap(Context context, Uri uri, boolean reduction) {
        Bitmap bitmap = null;
        String fileName = null;


        try {
            if (uri.toString().startsWith("file")) {
                fileName = new File(uri.getPath()).getName();
            }
            InputStream in;
            if (uri.toString().startsWith("file")) {
                in = context.openFileInput(fileName);
            } else {
                in = context.getContentResolver().openInputStream(uri);
            }
            if (!reduction) {
                return bitmap;
            }

            BitmapFactory.Options imageOptions = new BitmapFactory.Options();
            imageOptions.inJustDecodeBounds = true;
            imageOptions.inPreferredConfig = Bitmap.Config.RGB_565;
            BitmapFactory.decodeStream(in, null, imageOptions);
            Log.v("bitmap", "imageSize:" + imageOptions.outWidth + "x" + imageOptions.outHeight);
            in.close();
            if (uri.toString().startsWith("file")) {
                in = context.openFileInput(fileName);
            } else {
                in = context.getContentResolver().openInputStream(uri);
            }
            float imageScaleWidth = (float) imageOptions.outWidth / imageDisplayWidth;
            float imageScaleHeight = (float) imageOptions.outHeight / imageDisplayHeight;


            // もしも、縮小できるサイズならば、縮小して読み込む
            if (imageScaleWidth > 1 || imageScaleHeight > 1) {
                float sizeScale;
                if (imageScaleWidth > imageScaleHeight) {
                    sizeScale = imageScaleWidth;
                } else {
                    sizeScale = imageScaleHeight;
                }

                // 縦横、小さい方に縮小するスケールを合わせる
                int new_x = (int) (imageOptions.outWidth / sizeScale);
                int new_y = (int) (imageOptions.outHeight / sizeScale);
                bitmap = BitmapFactory.decodeStream(in);
                Bitmap resizeBitmap = Bitmap.createScaledBitmap(bitmap, new_x, new_y, false);
                bitmap = null;
                return resizeBitmap;
            }

            in.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public static int getIntDirection(Context context, Uri uri) {
        int direction = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(getPathFromUri(context, uri));
            direction = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);

        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("direction:" + direction);
        return direction;
    }
    //リサイズアンド回転
    public static Bitmap rotateAndResize(Context context, Uri uri) {
        Bitmap loadBitmap = null;
        try {
            InputStream in;
            String filePath = getPathFromUri(context, uri);
            BitmapFactory.Options options = new BitmapFactory.Options();
            // 情報のみ取得
            options.inJustDecodeBounds = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            //ファイルの各種情報取得
            in = context.getContentResolver().openInputStream(uri);
            BitmapFactory.decodeStream(in,null,options);
            setResizeOption(options);
            Matrix matrix_rotate = getMatrix(context,uri);
            in.close();

            //実際に読み込む
            options.inJustDecodeBounds = false;
            in = context.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeFile(filePath,options);
            loadBitmap = Bitmap.createBitmap(bitmap,0,0,options.outWidth,options.outHeight,matrix_rotate,false);

            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadBitmap;
    }

    public static void setResizeOption(BitmapFactory.Options options){
        //サイズ
        int intScaleWidth = options.outWidth / 1920;
        int intScaleHeight = options.outHeight / 1920;
        int intScale;
        //大きい方のScaleを採用
        if (intScaleWidth >= intScaleHeight) {
            intScale = intScaleWidth;
        } else {
            intScale = intScaleHeight;
        }
        if (intScale < 1) {
            intScale = 1;
        }
        options.inSampleSize = intScale;
    }

    public static Matrix getMatrix(Context context,Uri uri) {
        int direction = getIntDirection(context,uri);
        Matrix matrix = new Matrix();
        //　回転させる
        switch (direction) {
            case ExifInterface.ORIENTATION_UNDEFINED:
                break;
            case ExifInterface.ORIENTATION_NORMAL:
                break;
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.postScale(-1f, 1f);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180f);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.postScale(1f, -1f);
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90f);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                matrix.postRotate(-90f);
                matrix.postScale(1f, -1f);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                matrix.postRotate(90f);
                matrix.postScale(1f, -1f);
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(-90f);
                break;
        }
        return matrix;
    }

    public static void setSizeAndDirection(Context context, ImageView imgView, Uri uri, boolean resize) {
        String fileName = getPathFromUri(context, uri);
        Bitmap bitmap = getBitmap(context, uri, resize);
        System.out.println("サイズ：" + bitmap.getByteCount());
        try {
/*            ExifInterface exifInterface = null;
            exifInterface = new ExifInterface(fileName);
            int orientation = Integer.parseInt(exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION));*/
            int orientation = 0;
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
            System.out.println("向きは" + orientation);
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
            System.out.println("幅" + lp.width);
            System.out.println("高さ" + lp.height);
            imgView.setLayoutParams(lp);
            imgView.setImageMatrix(mat);
            imgView.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
