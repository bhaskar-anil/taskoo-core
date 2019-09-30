package com.taskoo.core.aspect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

@Aspect
public class PageableAdvice {

	@Resource
    HttpServletRequest request;

    private Logger LOG = LoggerFactory.getLogger(PageableAdvice.class);
    
    @Before("@annotation(in.taskoo.core.annotation.Paged)")
    public PageRequest aroundAspect() throws Throwable {
    	try {
    		Integer page = Integer.parseInt(request.getParameter("p")); 
    		Integer size = Integer.parseInt(request.getParameter("s"));
    		Direction direction = Direction.fromString(request.getParameter("o"));
    		Sort sort = Sort.by(direction, request.getParameter("a"));
    	   return PageRequest.of(page, size, sort);
    	}catch (Exception e) {
    		LOG.debug(e.getMessage());
    		Integer page = 1; 
    		Integer size = 30;
    		Direction direction = Direction.DESC;
    		Sort sort = Sort.by(direction, "id");
    		return PageRequest.of(page, size, sort);
		}
    }

}
