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

    private final int DEFAULT_MAX_STREAMS = 4;

    private final Map<Method, SoundMethod> soundMethodCache = new LinkedHashMap<>();

    private Context mContext;

    private boolean mLoadEagerly;

    private SoundPool mSoundPool;

    private Tutti(Context context, int maxStreams, boolean loadEagerly) {
        mContext = context;
        mLoadEagerly = loadEagerly;
        maxStreams = maxStreams > 0 ? maxStreams : DEFAULT_MAX_STREAMS;
        mSoundPool = new SoundPool(maxStreams, AudioManager.STREAM_MUSIC, 0);
    }


    public <T> T create(Class<T> subject) {
        if (mLoadEagerly) {
            for (Method method : subject.getDeclaredMethods()) {
                loadSoundMethod(method);
            }
        }
        return (T) Proxy.newProxyInstance(subject.getClassLoader(), new Class<?>[]{subject}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass() == Object.class) {
                    return method.invoke(this, args);
                }
                SoundMethod soundMethod = loadSoundMethod(method);
                return mSoundPool.play(soundMethod.soundId, soundMethod.leftVolume, soundMethod.rightVolume, soundMethod.priority, soundMethod.loop ? -1 : 0, soundMethod.rate);
            }
        });
    }

    private SoundMethod loadSoundMethod(Method method) {
        SoundMethod result = soundMethodCache.get(method);
        if (result == null) {
            result = SoundMethod.newInstance(mContext, mSoundPool, method);
            soundMethodCache.put(method, result);
        }
        return result;
    }

    public void release() {
        mSoundPool.release();
        mSoundPool = null;
    }


    public static final class Builder {

        private Context context;
        private int maxStreams;
        private boolean loadEagerly;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder maxStreams(int maxStreams) {
            this.maxStreams = maxStreams;
            return this;
        }

        // TODO: 2018/2/15 暂时关闭
        private Builder loadEagerly(boolean loadEagerly) {
            this.loadEagerly = loadEagerly;
            return this;
        }

        public Tutti Build() {
            // TODO: 2018/2/15 先加载资源
            return new Tutti(this.context, this.maxStreams, true);
        }

    }
}
