package im.pumpkin.tutti.sample;

import im.pumpkin.tutti.Sound;

/**
 * Created by CrazyPumPkin on 2018/1/31.
 */

public interface SoundSet {

    @Sound(resource = R.raw.keyboard, loop = true)
    void keyboard();

    @Sound(resource = R.raw.camera, leftVolume = 0.8f, rightVolume = 0.8f)
    void camera();

    @Sound(resource = R.raw.thunder, rate = 1.5f, loop = true, priority = 0)
    void thunderbolt();

}
