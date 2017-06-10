package com.demo;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * Hello world!
 *
 */
public class App
{

    ClassLoaderTemplateResolver classLoaderTemplateResolver;
    TemplateEngine templateEngine;

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
