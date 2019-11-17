package in.taskoo.core.aspect;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Component;

import in.taskoo.core.context.AppContext;

@Aspect
@Component
public class PageableAdvice {

	@Resource
    HttpServletRequest request;

    private Logger LOG = LoggerFactory.getLogger(PageableAdvice.class);
    
    @Before("@annotation(in.taskoo.core.annotation.Paged)")
    public void aroundAspect() throws Throwable {
    	
    	Pageable pageable=null;
    	try {
    		Integer page = Integer.parseInt(getParams("page","0")); 
    		Integer size = Integer.parseInt(getParams("size","30"));
    		Direction direction = Direction.fromString(getParams("order", "desc"));
    		Sort sort = Sort.by(direction, getParams("sortFields", "id"));
    	   pageable = PageRequest.of(page, size, sort);
    	}catch (Exception e) {
    		LOG.debug(e.getMessage());
    		Integer page = 0; 
    		Integer size = 30;
    		Direction direction = Direction.DESC;
    		Sort sort = Sort.by(direction, "id");
    		pageable = PageRequest.of(page, size, sort);
		}
    	ThreadLocal<Pageable> threadLocal = new ThreadLocal<>();
    	threadLocal.set(pageable);
    	AppContext.pageable = threadLocal;
    }

	private String getParams(String paramName,String defaultValue) {
		return StringUtils.isBlank(request.getParameter(paramName))?defaultValue:request.getParameter(paramName);
	}

}
