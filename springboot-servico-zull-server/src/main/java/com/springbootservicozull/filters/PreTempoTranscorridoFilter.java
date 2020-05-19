package com.springbootservicozull.filters;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTempoTranscorridoFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PreTempoTranscorridoFilter.class);
	
	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();

		HttpServletRequest req = ctx.getRequest();
		
		log.info(String.format("%s request enviado a %s", req.getMethod(), req.getRequestURL().toString()));
		
		Long tempoInicio = System.currentTimeMillis();
		req.setAttribute("tempoInicio", tempoInicio);
		
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
