/**
 *
 */
package com.sai.ffac.storefront.forms.validation;

import de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.sai.ffac.storefront.forms.FfacRegisterForm;


/**
 * @author tuan.truong
 *
 */
@Component("ffacRegistrationValidator")
public class FfacRegistrationValidator extends RegistrationValidator
{

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(final Class<?> aClass)
	{
		return FfacRegisterForm.class.equals(aClass);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.acceleratorstorefrontcommons.forms.validation.RegistrationValidator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(final Object object, final Errors errors)
	{
		// YTODO Auto-generated method stub
		super.validate(object, errors);
		final FfacRegisterForm registerForm = (FfacRegisterForm) object;
		final String sapCode = registerForm.getSapCode();
		if (!validateSapCode(sapCode))
		{
			errors.rejectValue("sapCode", "register.sapCode.invalid");
		}
	}

	/**
	 * @param sapCode
	 * @return
	 */
	protected boolean validateSapCode(final String sapCode)
	{
		final int SAP_CODE_LEN = 7; //should move this to properties file
		final boolean isValid = StringUtils.isNotEmpty(sapCode) && StringUtils.startsWithAny(sapCode, new String[]
		{ "I", "C" }) && StringUtils.isAlphanumeric(sapCode) && (sapCode.length() <= SAP_CODE_LEN);

		return isValid;
	}

}
