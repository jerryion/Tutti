package im.pumpkin.tutti.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import im.pumpkin.tutti.Tutti;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Tutti mTutti;

    private SoundSet mSoundSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTutti = new Tutti.Builder(this)
                .maxStreams(2)
                .Build();
        mSoundSet = mTutti.create(SoundSet.class);

        findViewById(R.id.iv_camera).setOnClickListener(this);
        findViewById(R.id.iv_keyboard).setOnClickListener(this);
        findViewById(R.id.iv_thunderbolt).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_camera:
                mSoundSet.camera();
                break;
            case R.id.iv_keyboard:
                mSoundSet.keyboard();
                break;
            case R.id.iv_thunderbolt:
                mSoundSet.thunderbolt();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTutti.release();
    }
}
