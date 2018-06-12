package com.beuteu.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.beuteu.domain.Path;

@Component
public class ContextFactory {
	 @Autowired Path path;
	 public String path() {
		 return ((ServletRequestAttributes)
	                RequestContextHolder
	                .currentRequestAttributes())
	                .getRequest()
	                .getContextPath().toString()
	                ;
	 }
	 public Path ptx() {
		 path.setContext(path());
		 path.setCss(path()+"/resources/css");
		 path.setImg(path()+"/resources/img");
		 path.setJs(path()+"/resources/js");
		 return path;
	 }
	 
}