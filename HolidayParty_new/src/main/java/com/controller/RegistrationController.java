package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.model.RegistrationBean;
import com.validate.CustomValidator;

@Controller
public class RegistrationController {

	@Autowired
	private CustomValidator custValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(custValidator);
	}

	@RequestMapping(value = "/registerPage", method = RequestMethod.GET)
	public String registerPage(@ModelAttribute("register") RegistrationBean registrationBean, BindingResult result) {
		return "registrationpage";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String performRegistration(@ModelAttribute("register") @Validated RegistrationBean registrationBean, BindingResult result) {
		if(result.hasErrors()) {
			return "registrationpage";
		}
		return "thankyou";
	}
}
