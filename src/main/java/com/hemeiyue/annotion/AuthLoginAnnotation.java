package com.hemeiyue.annotion;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.hemeiyue.eumn.Auth;

@Documented //文档生成时，该注解将被包含在javadoc中，可去掉
//@Target(ElementType.METHOD)//目标是方法 
@Retention(RetentionPolicy.RUNTIME) //注解会在class中存在，运行时可通过反射获取  
@Inherited
public @interface AuthLoginAnnotation {

	boolean checkLogin() default false;
	
	Auth checkAuth() default Auth.NULL;
}
