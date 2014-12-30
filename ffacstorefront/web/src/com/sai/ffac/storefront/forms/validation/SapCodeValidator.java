/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2014 hybris AG
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 *
 *
 */
package com.sai.ffac.storefront.forms.validation;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.sai.ffac.storefront.forms.UpdateSapCodeForm;


/**
 * Validator for sap code forms.
 */
@Component("sapCodeValidator")
public class SapCodeValidator implements Validator
{

	@Override
	public boolean supports(final Class<?> aClass)
	{
		return UpdateSapCodeForm.class.equals(aClass);
	}

	@Override
	public void validate(final Object object, final Errors errors)
	{
		final UpdateSapCodeForm profileForm = (UpdateSapCodeForm) object;
		final String sapCode = profileForm.getSapCode();
		//		final int SAP_CODE_LEN = 7;
		//		final String I_CHAR = "I";
		//		final String C_CHAR = "C";
		//		final String D_CHAR = "D";
		//		final String I_CHAR_LOW = "i";
		//		final String C_CHAR_LOW = "c";
		//		final String D_CHAR_LOW = "d";

		//		final boolean isValid = StringUtils.isNotEmpty(sapCode) && StringUtils.isAlphanumeric(sapCode)
		//				&& StringUtils.startsWithAny(sapCode, new String[]
		//				{ I_CHAR, C_CHAR, D_CHAR, I_CHAR_LOW, C_CHAR_LOW, D_CHAR_LOW }) && (sapCode.length() <= SAP_CODE_LEN);
		final boolean isValid = (sapCode.length() <= 30); //bypass validation for sap code
		if (!isValid)
		{
			errors.rejectValue("sapCode", "profile.sapCode.invalid");
		}
	}

}
