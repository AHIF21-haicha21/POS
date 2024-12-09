package at.htlkaindorf.todo_list.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface GeneratedValue {
    long step() default  1L;
    long startValue() default  1L;
}
