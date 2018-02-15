package im.pumpkin.tutti;

import android.content.Context;
import android.media.SoundPool;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by CrazyPumPkin on 2018/1/30.
 */
public class SoundMethod {

    public int soundId;
    public float leftVolume;
    public float rightVolume;
    public int priority;
    public boolean loop;
    public float rate;

    private SoundMethod(Context context, SoundPool soundPool, Method method) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if (annotation instanceof Sound) {
                Sound sound = (Sound) annotation;
                leftVolume = sound.leftVolume();
                rightVolume = sound.rightVolume();
                priority = sound.priority();
                loop = sound.loop();
                rate = sound.rate();
                soundId = soundPool.load(context, sound.resource(), 1);
            }
        }
    }

    public static SoundMethod newInstance(Context context, SoundPool soundPool, Method method) {
        SoundMethod soundMethod = new SoundMethod(context, soundPool, method);
        return soundMethod;
    }
}
