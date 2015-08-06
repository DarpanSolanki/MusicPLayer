package in.novopay.darpan;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.novopay.khushpreetsingh.mymusic.R;
import com.squareup.picasso.Picasso;

import in.novopay.darpan.Models.MusicApiResponse;

import Events.MusicCompletedEvent;
import Events.SeekbarUpdateEvent;
import de.greenrobot.event.EventBus;
import hugo.weaving.DebugLog;


public class MusicActivity extends ActionBarActivity {

    public ImageButton mPlayButton, mPauseButton,mFastForward,mRewind;
    //private MediaPlayer mediaPlayer;
    private SeekBar mSeek;
    private TextView mSongName, mArtistName;
    private ImageView songphoto;
    public int music_current_position;
    public static int POS;

    //******
    public static int MESSAGE_WAKE_UP=10;
    // *******

    MusicHandler musicHandler = new MusicHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mPlayButton = (ImageButton) findViewById(R.id.activity_main_play);
        mPlayButton.setVisibility(View.INVISIBLE);
        mPauseButton = (ImageButton) findViewById(R.id.activity_main_pause);
        mPauseButton.setVisibility(View.VISIBLE);
        mFastForward = (ImageButton) findViewById(R.id.activity_main_fastforward);
        mRewind = (ImageButton) findViewById(R.id.activity_main_rewind);
        //music_current_position = MusicServices.getCurrentPosition();
        mSeek = (SeekBar) findViewById(R.id.activity_main_seek);
        mSongName = (TextView) findViewById(R.id.main_songName);
        mArtistName = (TextView) findViewById(R.id.main_artistName);
        songphoto = (ImageView) findViewById(R.id.display_image);

        Intent intent = getIntent();
        if(intent!=null) {
            MusicApiResponse musicResponse = (MusicApiResponse) intent.getSerializableExtra("MUSICLIST");
            POS = intent.getIntExtra("POS" ,-1);
            mSongName.setText(musicResponse.getResults().getCollection1().get(POS).getSongName().getText());
            mArtistName.setText(musicResponse.getResults().getCollection1().get(POS).getArtistName().getText());
            Picasso.with(this).load(musicResponse.getResults().getCollection1().get(POS).getImageUrl().getSrc()).into(songphoto);


        }
        if(MusicServices.isMusicPlaying())
            musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP);


        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicServices.startMusic();
                mPlayButton.setVisibility(View.INVISIBLE);
                mPauseButton.setVisibility(View.VISIBLE);
            }
        });


        mPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicServices.pauseMusic();
                mPlayButton.setVisibility(View.VISIBLE);
                mPauseButton.setVisibility(View.INVISIBLE);
            }
        });
        mFastForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicServices.fastForward();
            }
        });

        mRewind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MusicServices.Revind();
            }
        });


    mSeek.setMax(MusicServices.songDuration());
    mSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MusicServices.seekMusicTo(progress,fromUser);
            if(progress==100)
            {
                mPlayButton.setVisibility(View.VISIBLE);
                mPauseButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    class MusicHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MESSAGE_WAKE_UP) {
                if (MusicServices.isMusicPlaying()) {
                        mSeek.setProgress(MusicServices.getCurrentPosition());
                        sendEmptyMessageDelayed(MESSAGE_WAKE_UP, 200);
                }
            }
            super.handleMessage(msg);
        }
    }


    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onEvent(MusicCompletedEvent event) {
        Toast.makeText(MusicActivity.this, "Song Ended", Toast.LENGTH_SHORT).show();
        mPlayButton.setVisibility(View.VISIBLE);
        mPauseButton.setVisibility(View.INVISIBLE);
    };

    @DebugLog
    public void onEvent(SeekbarUpdateEvent event){
        musicHandler.sendEmptyMessage(MESSAGE_WAKE_UP);
    }
}
