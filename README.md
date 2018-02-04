# Tutti
支持同时播放多个音频的便捷API

### Sample
1.为每一个声音设置对应的方法名和音频文件
```
    public interface SoundSet {
    
        @Sound(resource = R.raw.keyboard)
        void keyboard();
    
        @Sound(resource = R.raw.camera)
        void camera();
    
        @Sound(resource = R.raw.thunder)
        void thunderbolt();
    
    }
```
2.调用处
```
    private Tutti mTutti;
    
    private SoundSet mSoundSet;
    
    mTutti = new Tutti(this);
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