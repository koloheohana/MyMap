package com.koloheohana.mymap;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.github.bassaer.chatmessageview.model.User;
import com.github.bassaer.chatmessageview.util.ChatBot;
import com.github.bassaer.chatmessageview.views.ChatView;
import com.github.bassaer.chatmessageview.views.MessageView;
import com.github.florent37.awesomebar.ActionItem;
import com.github.florent37.awesomebar.AwesomeBar;
import com.koloheohana.mymap.adapter.PicturePagerAdapter;
import com.koloheohana.mymap.data_base.OrmaOperator;
import com.koloheohana.mymap.server.ServerOperator;
import com.koloheohana.mymap.util.Scene;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by User on 2017/11/28.
 */

public class TestActivity extends AppCompatActivity {
    private ChatView mChatView;

    @Override
    protected void onCreate(Bundle save) {
        super.onCreate(save);
        Scene.set(this);
        System.out.println("test");
        setContentView(R.layout.activity_test);



/*
        Bitmap icon1 = BitmapFactory.decodeResource(getResources(), R.mipmap.bell);
        Bitmap icon2 = BitmapFactory.decodeResource(getResources(), R.mipmap.migi);

        int myId = 0;
        int myId2 = 1;
        String name = "testament";
        String name2 = "testament2";

        final User me = new User(myId, name, icon1);
        final User you = new User(myId2, name2, icon2);
        mChatView = (ChatView) findViewById(R.id.chat_view);

        //Set UI parameters if you need
        mChatView.setRightBubbleColor(ContextCompat.getColor(this, R.color.green500));
        mChatView.setLeftBubbleColor(Color.WHITE);
        mChatView.setBackgroundColor(ContextCompat.getColor(this, R.color.blueGray500));
        mChatView.setSendButtonColor(ContextCompat.getColor(this, R.color.blueGray300));
        mChatView.setSendIcon(R.drawable.ic_action_send);
        mChatView.setRightMessageTextColor(Color.WHITE);
        mChatView.setLeftMessageTextColor(Color.BLACK);
        mChatView.setUsernameTextColor(Color.WHITE);
        mChatView.setSendTimeTextColor(Color.WHITE);
        mChatView.setDateSeparatorColor(Color.WHITE);
        mChatView.setInputTextHint("メッセージを入力して下さい...");
        mChatView.setMessageMarginTop(25);
        mChatView.setMessageMarginBottom(25);

        for(int i = 0;i <= 30;i++) {
            final com.github.bassaer.chatmessageview.models.Message message = new com.github.bassaer.chatmessageview.models.Message.Builder().setUser(me).setRightMessage(true).setMessageText("test"+i).hideIcon(true).build();
            mChatView.send(message);
        }


        //Click Send Button
        mChatView.setOnClickSendButtonListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //new message
                final com.github.bassaer.chatmessageview.models.Message message = new com.github.bassaer.chatmessageview.models.Message.Builder().setUser(me).setRightMessage(true).setMessageText(mChatView.getInputText()).hideIcon(true).build();
                //Set to chat view
                mChatView.send(message);
                //Reset edit text
                mChatView.setInputText("");
*/
/*                final com.github.bassaer.chatmessageview.models.Message message2 = new com.github.bassaer.chatmessageview.models.Message.Builder().setUser(you).setRightMessage(false).setMessageText(ChatBot.INSTANCE.talk(me.getName(),message.getMessageText())).build();

                int sendDelay = (new Random().nextInt(4) + 1) * 1000;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mChatView.receive(message2);
                    }
                },sendDelay);*//*

            }

        });
*/

    }
}
