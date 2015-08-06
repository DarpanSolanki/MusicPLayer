package in.novopay.darpan;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.novopay.khushpreetsingh.mymusic.R;

import Events.MusicCompletedEvent;
import Events.SeekbarUpdateEvent;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;

/**
 * Created by khushpreetsingh on 8/5/15.
 */
public class MusicServices extends Service{

    private static MediaPlayer mediaPlayer;

    public static final String KEY_METHOD = "method";
    public static final String METHOD_PLAY = "method_play";
    public static final String METHOD_PAUSE = "method_pause";
    public static final String METHOD_REW = "method_rev";
    public static final String METHOD_FF = "method_ff";
    public static final String METHOD_STOP = "method_stop";



    @Override
    @DebugLog
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent==null)
            return super.onStartCommand(intent, flags, startId);
        String method = intent.getStringExtra(KEY_METHOD);
        mediaPlayer=MediaPlayer.create(this, R.raw.a);
        if(method.equals(METHOD_PLAY))
        {
            startMusic();
        }
        if(method.equals(METHOD_PAUSE))
        {
            pauseMusic();
        }
        if(method.equals(METHOD_FF))
        {
            fastForward();
        }
        if(method.equals(METHOD_REW))
        {
            Revind();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                EventBus.getDefault().post(new MusicCompletedEvent());
            }
        });


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // Methods of Media Player

    public static void startMusic(){

        if(mediaPlayer!=null) {
            mediaPlayer.start();
            EventBus.getDefault().post(new SeekbarUpdateEvent());

        }
    }

    public static void pauseMusic(){
            if (MusicServices.isMusicPlaying())
                mediaPlayer.pause();
    }

    public static void fastForward(){
        if(mediaPlayer!=null)
        {
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + 2000);
        }
    }

    public static void Revind(){
        if(mediaPlayer!=null){
            mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - 2000);
        }
    }

    public static int songDuration() {
        return mediaPlayer.getDuration();
    }

    public static int getCurrentPosition(){
        if(mediaPlayer!=null){
            if(mediaPlayer.isPlaying()) {
                return mediaPlayer.getCurrentPosition();
            }
        }
        return -1;
    }

    public static boolean isMusicPlaying(){
        if(mediaPlayer!=null)
        {
            if(mediaPlayer.isPlaying())
            {
                return true;
            }
        }
        return false;
    }

    public static void seekMusicTo(int progress, boolean fromUser){
        if(mediaPlayer!=null && fromUser) {
            mediaPlayer.seekTo(progress);
        }
    }

    public String songName(){
        return this.songName();
    }



}
