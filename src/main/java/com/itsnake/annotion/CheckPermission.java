package com.itsnake.annotion;


import java.lang.annotation.*;

@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPermission {
     String value() default "";
     String name() default "";
}
