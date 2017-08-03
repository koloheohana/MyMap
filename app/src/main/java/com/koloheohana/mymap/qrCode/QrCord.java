package com.koloheohana.mymap.qrCode;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.qrcode.QRCodeWriter;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.koloheohana.mymap.MainActivity;

/**
 * Created by User on 2017/07/27.
 */

public class QrCord {
    public static Bitmap getQRCode(String string){
        Bitmap qr_bitmap = createQrCode(string);
        return qr_bitmap;
    }

    /**
     *
     * @param qr_name 生成文字列
     * @return
     */
    private static Bitmap createQrCode(String qr_name){
        Bitmap bitmap = null;
        int size = 400;

        try {
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.encodeBitmap(qr_name,BarcodeFormat.QR_CODE,size,size);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
