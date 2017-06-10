package com.demo.esper;

/**
 * @author lg
 *         Date: 4/13/17
 *         Time: 3:38 PM
 */
public @interface Timer {

    String name() default "";

    String[] tags() default {};

    String countVal() default "";

    String sumVal() default "";

    String maxVal() default "";

    String minVal() default "";

    String msg() default "";
}
