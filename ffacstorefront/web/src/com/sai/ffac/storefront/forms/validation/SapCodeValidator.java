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


import org.apache.commons.lang.StringUtils;
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
		final int SAP_CODE_LEN = 7;

		if (!(StringUtils.isNotEmpty(sapCode) && StringUtils.startsWithAny(sapCode, new String[]
		{ "I", "C" }) && StringUtils.isAlphanumeric(sapCode) && (sapCode.length() <= SAP_CODE_LEN)))
		{
			errors.rejectValue("sapCode", "profile.sapCode.invalid");
		}
	}

}
