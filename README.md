# Tutti
支持同时播放多个音效的便捷API

参数|说明
---|---
resource|音频文件的resId
leftVolume|左声道音量(0 ~ 1)
rightVolume|右声道音量(0 ~ 1)
priority|播放优先级(0 ~ ∞)
loop|是否循环播放
rate|播放速度（0.5 ~ 2.0）

### Sample
1.为每一个声音设置参数
```
    public interface SoundSet {
    
        @Sound(resource = R.raw.keyboard, loop = true)
        void keyboard();
    
        @Sound(resource = R.raw.camera, leftVolume = 0.8f, rightVolume = 0.8f)
        void camera();
    
        @Sound(resource = R.raw.thunder, rate = 1.5f, loop = true, priority = 0)
        void thunderbolt();
    
    }
```
2.初始化并使用播放音频的方法
```
    private Tutti mTutti;
    
    private SoundSet mSoundSet;
    
    mTutti = new Tutti.Builder(this)
                    .maxStreams(2)
                    .Build();
    mSoundSet = mTutti.create(SoundSet.class);
    
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
```
3.释放资源
```
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mTutti.release();
    }
```