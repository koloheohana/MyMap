package com.koloheohana.mymap.map;

import com.google.android.gms.maps.model.LatLng;

import java.math.BigDecimal;

/**
 * 計算用クラス
 */
public class Calculation {
    public static
    final double KM = 0.0109;
    /**
     * キロ換算
     */
    public final double Km = 0.010966404715491394;
    public static Double[] rangeCal(LatLng lat,double value){
        double range = KM*value;
        double dx = new BigDecimal(lat.latitude).setScale(5,BigDecimal.ROUND_DOWN).doubleValue();
        double dy = new BigDecimal(lat.longitude).setScale(5,BigDecimal.ROUND_DOWN).doubleValue();
        Double[] D = {dx-range,dx+range,dy-range,dy+range};
        return D;
    }
}
