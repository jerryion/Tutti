package im.pumpkin.tutti.sample;

import im.pumpkin.tutti.Sound;

/**
 * Created by CrazyPumPkin on 2018/1/31.
 */

public interface SoundSet {

    @Sound(resource = R.raw.keyboard)
    void keyboard();

    @Sound(resource = R.raw.camera)
    void camera();

    @Sound(resource = R.raw.thunder)
    void thunderbolt();

}
