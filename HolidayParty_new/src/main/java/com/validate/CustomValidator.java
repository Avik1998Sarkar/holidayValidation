package com.validate;

import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.model.RegistrationBean;

@Component
public class CustomValidator implements Validator {
	@Override
	public void validate(Object arg0, Errors arg1) {
		RegistrationBean user = (RegistrationBean) arg0;
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "userName", "", "User Name cannot be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "contactNumber", "",
				"Contact Number should be of 10 digits/Contact Number should not be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "emailId", "", "Email ID cannot be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(arg1, "confirmEmailId", "", "Confirm EmailID cannot be blank");
		Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		if (!(pattern.matcher(user.getEmailId()).matches())) {
			arg1.rejectValue("emailId", "", "Should be a proper email ID format");
		}
		if (!(pattern.matcher(user.getConfirmEmailId()).matches())) {
			arg1.rejectValue("confirmEmailId", "", "Should be a proper email ID format");
		}
//		if (!user.getEmailId().contains("@")) {
//			arg1.rejectValue("emailId", "", "Should be a proper email ID format");
//		}
//		if (!user.getConfirmEmailId().contains("@")) {
//			arg1.rejectValue("confirmEmailId", "", "Should be a proper email ID format");
//		}
// 		if (!(user.getEmailId().contains("@") && user.getEmailId().contains("."))) {
// 			arg1.rejectValue("emailId", "", "Should be a proper email ID format");
// 		}
// 		if (!(user.getConfirmEmailId().contains("@") && user.getConfirmEmailId().contains("."))) {
// 			arg1.rejectValue("confirmEmailId", "", "Should be a proper email ID format");
// 		}
//		if (!(user.getConfirmEmailId().isBlank() && user.getEmailId().isBlank())) {
//			if (!user.getConfirmEmailId().equals(user.getEmailId())) {
//				arg1.rejectValue("confirmEmailId", "", "Email and Confirm Email should be same");
//			}
//		}

		if (user.getEmailId().equalsIgnoreCase(user.getConfirmEmailId()) == false
				&& user.getConfirmEmailId().isBlank() == false) {
			arg1.rejectValue("confirmEmailId", "", "Email and Confirm Email should be same");
		}

		if (Pattern.matches("^[0-9]{10}$", String.valueOf(user.getContactNumber())) == false) {
			arg1.rejectValue("contactNumber", "",
					"Contact Number should be a 10 digits/Contact Number should not be blank");
		}

// 		if (String.valueOf((user.getContactNumber())).length() != 10) {
// 			arg1.rejectValue("contactNumber", "",
// 					"Contact Number should be of 10 digits/Contact Number should not be blank");
// 		}
		if (!user.isStatus()) {
			arg1.rejectValue("status", "", "please agree to the terms and conditions");
		}
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return RegistrationBean.class.isAssignableFrom(arg0);
	}
}