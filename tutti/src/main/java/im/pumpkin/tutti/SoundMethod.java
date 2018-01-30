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

    public SoundMethod(Context context,SoundPool soundPool, Method method) {
        for (Annotation annotation : method.getDeclaredAnnotations()) {
            if(annotation instanceof Sound){
                int soundResource = ((Sound) annotation).resource();
                soundId = soundPool.load(context,soundResource,1);
            }
        }
    }
}
