package com.koloheohana.mymap.menutab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.koloheohana.mymap.MainActivity;
import com.koloheohana.mymap.MapsActivity;
import com.koloheohana.mymap.PickUpShopActivity;
import com.koloheohana.mymap.R;
import com.koloheohana.mymap.SettingSelectActivity;
import com.koloheohana.mymap.data_base.OrmaMyData;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.me.MyUser;
import com.koloheohana.mymap.server.ServerOperator;


/**
 * Created by User on 2016/08/03.
 */
public class StartMenuTab extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public HueAdapter hue_adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_menu, container, false);
        GridView gridView = (GridView) view.findViewById(R.id.gridview);
        hue_adapter = new HueAdapter(MainActivity.ME);
        gridView.setAdapter(hue_adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                hue_adapter.chengeMenu(position);
            }
        });

        return view;
    }

    public class HueAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater mLayoutInflater;
        private String[] mHueArray = {
                "地図", "グループ", "トーク",
                "店情報", "お気に入り", "設定"
        };

        public void chengeMenu(int position) {
            //ディレクトリにrealm作成
            switch (position) {
                case 0:
                    Intent intent = new Intent(MainActivity.ME, MapsActivity.class);
                    startActivity(intent);
                    return;
                case 1:
                    MainActivity.ME.mPager.setCurrentItem(1);
                    return;
                case 2:
                    MainActivity.ME.mPager.setCurrentItem(2);
                    return;
                case 3:
                    Intent shop_intent = new Intent(MainActivity.ME, PickUpShopActivity.class);
                    startActivity(shop_intent);
/*
                    MainActivity.ME.openQRreader();
*/
                    return;
                case 4:
                    OrmaMyData omd = OrmaOperator.getMyData(MainActivity.ME);
                    ServerOperator.imageUploadAndPush(MainActivity.ME, omd.user_icon, MyUser.ME.getIcon(),1);

                    return;
                case 5:
                    Intent setting_intent = new Intent(MainActivity.ME,SettingSelectActivity.class);
                    startActivity(setting_intent);
                    return;
            }
        }

        public int iconSelect(int position) {
            switch (position) {
                case 0:
                    return R.drawable.worldmap48;
                case 1:
                    return R.drawable.couplemanwoman48;
                case 2:
                    return R.drawable.thinking48;
/*                case 3:
                    return R.drawable.plus48;*/
                case 3:
                    return R.drawable.cafe48;
                case 4:
                    return R.drawable.likeoutline48;
                case 5:
                    return R.drawable.settings348;
            }
            return R.drawable.koloheohana;
        }


        private class ViewHolder {
            public ImageView hueImageView;
            public TextView hueTextView;
        }

        public HueAdapter(Context context) {
            mContext = context;
            mLayoutInflater = LayoutInflater.from(context);
        }

        public int getCount() {
            return mHueArray.length;
        }

        public Object getItem(int position) {
            System.out.println("ゲットアイテム" + position);
            return mHueArray[position];
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder;
            if (convertView == null) {
                convertView = mLayoutInflater.inflate(R.layout.grid_item_hue, null);
                holder = new ViewHolder();
                holder.hueImageView = (ImageView) convertView.findViewById(R.id.hue_imageview);
                holder.hueTextView = (TextView) convertView.findViewById(R.id.hue_textview);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

        /*holder.hueImageView.setImageResource(mHueIdArray[position]);*/
            holder.hueTextView.setText(mHueArray[position]);
/*
            ImageView iv = new ImageView(MainActivity.ME);
            iv.setImageResource(R.drawable.koloheohana);
*/

            holder.hueImageView.setImageResource(iconSelect(position));
            return convertView;
        }

    }

}
