package com.lifehon_front.filters

import com.lifehon_front.service.LifehonUtils
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView

class LifehonModelInterceptor : HandlerInterceptor {

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) {
        modelAndView?.addObject("current_user", LifehonUtils.getSessionUser(request));
    }
}