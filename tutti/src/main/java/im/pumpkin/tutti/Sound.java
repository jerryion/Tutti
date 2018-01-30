package im.pumpkin.tutti;

import android.support.annotation.RawRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by CrazyPumPkin on 2018/1/30.
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Sound {

    @RawRes
    int resource() default -1;

}