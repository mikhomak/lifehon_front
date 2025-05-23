package com.lifehon_front.service

import com.CheckTokenQuery
import jakarta.servlet.http.HttpServletRequest
import org.springframework.ui.Model

class LifehonUtils {
    companion object {
        fun getSessionUser(request: HttpServletRequest): CheckTokenQuery.CheckToken? {
            return request.session.getAttribute("current_user") as CheckTokenQuery.CheckToken?;
        }

        fun addErrorToModel(error: String, model: Model) {
            var errorsAttribute: MutableList<String>? = model.getAttribute("errors") as? MutableList<String>;

            if (errorsAttribute == null) {
                errorsAttribute = mutableListOf(error);
            } else {
                errorsAttribute.add(error);
            }

            model.addAttribute(errorsAttribute)
        }

        fun addSuccessToModel(error: String, model: Model) {
            var successAttribute: MutableList<String>? = model.getAttribute("success_message") as? MutableList<String>;

            if (successAttribute == null) {
                successAttribute = mutableListOf(error);
            } else {
                successAttribute.add(error);
            }

            model.addAttribute(successAttribute)
        }
    }
}