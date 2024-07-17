package com.lifehon_front.service

import com.CheckTokenQuery
import jakarta.servlet.http.HttpServletRequest

class LifehonUtils {
    companion object {
        fun getSessionUser(request: HttpServletRequest): CheckTokenQuery.CheckToken? {
            return request.session.getAttribute("current_user") as CheckTokenQuery.CheckToken?;

        }
    }
}