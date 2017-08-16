package com.example.demo.exception.resolver;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.example.demo.exception.common.CommonMvcExceptionFactor;
import com.example.demo.exception.common.MvcException;
import com.example.demo.exception.common.MvcExceptionModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MvcHandlerExceptionResolver extends AbstractHandlerExceptionResolver {


    @Override
    protected ModelAndView doResolveException(HttpServletRequest httpServletRequest,
                                              HttpServletResponse httpServletResponse, Object o, Exception e) {
        MvcExceptionModel model;
        if (e instanceof MvcException) {
            model = ((MvcException) e).getExceptionFactor().getExModel();
        } else if (e instanceof ConstraintViolationException) {
            ConstraintViolation violation = ((ConstraintViolationException) e).getConstraintViolations().stream().findFirst().orElse(null);
            if (violation == null) {
                log.error(e.getMessage(), e);
                model = new MvcException().getExceptionFactor().getExModel();
            } else {
                model = new MvcException(CommonMvcExceptionFactor.PARAM_ERROR).getExceptionFactor().getExModel();
                model.setDetailMsg(violation.getMessage());
            }
        } else {
            log.error(e.getMessage(), e);
            model = new MvcException().getExceptionFactor().getExModel();
        }

        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        view.setExtractValueFromSingleKeyModel(true);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("response", model);
        view.setAttributesMap(attributes);
        httpServletResponse.setStatus(model.getHttpCode());
        mv.setView(view);
        return mv;

    }
}
