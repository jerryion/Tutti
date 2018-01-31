package im.pumpkin.tutti;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by CrazyPumPkin on 2018/1/30.
 */

public class Tutti {

    private final int DEFAULT_MAX_STREAMS = 10;

    private final Map<Method, SoundMethod> soundMethodCache = new LinkedHashMap<>();

    private Context mContext;

    private SoundPool mSoundPool;

    public Tutti(Context context) {
        mContext = context;
        init();
    }

    private void init(){
        mSoundPool = new SoundPool(DEFAULT_MAX_STREAMS, AudioManager.STREAM_MUSIC,0);
    }

    public <T> T create(Class<T> subject){
        validateMethods(subject);
        return (T) Proxy.newProxyInstance(subject.getClassLoader(), new Class<?>[]{subject}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                SoundMethod soundMethod = soundMethodCache.get(method);
                if(soundMethod == null){
                    return null;
                }
                return mSoundPool.play(soundMethod.soundId,1,1,1,0,1);
            }
        });
    }

    private void validateMethods(Class<?> sound){
        for(Method method : sound.getDeclaredMethods()){
            loadSoundMethod(method);
        }
    }

    private SoundMethod loadSoundMethod(Method method){
        SoundMethod result;
        synchronized (soundMethodCache){
            result = soundMethodCache.get(method);
            if(result==null){
                result = new SoundMethod(mContext,mSoundPool,method);
                soundMethodCache.put(method,result);
            }
        }
        return result;
    }

    public void release(){
        mSoundPool.release();
        mSoundPool = null;
    }

}
