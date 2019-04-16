package com.karl.videoplayer;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dou361.ijkplayer.bean.VideoijkBean;
import com.dou361.ijkplayer.listener.OnShowThumbnailListener;
import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频播放ijkplayer框架二次封装
 *  https://github.com/lingcimi/jjdxm_ijkplayer
 *
 * author Fan.Huang
 * eamil  15209187329@qq.com
 * created 2019/4/16 13:55
 */
public class MainActivity extends AppCompatActivity {

    View rootView;
    String Url1 = "http://172.16.1.220:9000/UploadFile/QuestionBankImgs/889003d7-24c8-4db9-9ea0-220d1d30de3c.mp4";
    String Url2 = "http://172.16.1.220:9000/UploadFile/QuestionBankImgs/889003d7-24c8-4db9-9ea0-220d1d30de3c.mp4";

    PlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().from(this).inflate(R.layout.simple_player_view_player, null);
        setContentView(rootView);

        List<VideoijkBean> list = new ArrayList<VideoijkBean>();
        VideoijkBean m1 = new VideoijkBean();
        m1.setStream("标清");
        m1.setUrl(Url1);
        VideoijkBean m2 = new VideoijkBean();
        m2.setStream("高清");
        m2.setUrl(Url2);
        list.add(m1);
        list.add(m2);

        player = new PlayerView(this, rootView)
                .setTitle("什么")
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .showThumbnail(new OnShowThumbnailListener() {
                    @Override
                    public void onShowThumbnail(ImageView ivThumbnail) {
                        Glide.with(MainActivity.this)
                                .load(Url1)
                                .into(ivThumbnail);
                    }
                })
                .setPlaySource(list)
                .startPlay();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.onPause();
        }
        /**demo的内容，恢复系统其它媒体的状态*/
        //MediaUtils.muteAudioFocus(mContext, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (player != null) {
            player.onResume();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.onDestroy();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (player != null) {
            player.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public void onBackPressed() {
        if (player != null && player.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
