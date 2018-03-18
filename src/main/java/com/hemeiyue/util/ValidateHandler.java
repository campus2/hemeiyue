package com.hemeiyue.util;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

public class ValidateHandler {
	/**
     * @param result 绑定结果
     * @param model 数据模型
     * @return 是否发生错误（ true：发生错误，false:未发生错误）
     * @Description: 后台校验
     */
    public static boolean validate(BindingResult result, Model model) {
        boolean validateResult = false;
        if (result.hasErrors()) {
            validateResult = true;
            if (model != null) {
                FieldError error = result.getFieldErrors().get(0);// 为了避免大量的校验在前端堆积,影响用户体验，只返回一个错误提示
                System.out.println("validate error: " + error.getDefaultMessage());
                model.addAttribute("msg", error.getDefaultMessage());
            }
        }
        return validateResult;
    }
}