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
		final String mobileNumber = registerForm.getMobileNumber();

		if (!validateSapCode(sapCode))
		{
			errors.rejectValue("sapCode", "register.sapCode.invalid");
		}

		if (!validateMobileNumber(mobileNumber))
		{
			errors.rejectValue("mobileNumber", "register.mobileNumber.invalid");
		}
	}

	/**
	 * @param mobileNumber
	 * @return
	 */
	private boolean validateMobileNumber(final String mobileNumber)
	{
		//		final int MOBILE_NUM_LEN = 15; //should move this to properties file
		//		final String SIX_CHAR = "6";
		//		final String EIGHT_CHAR = "8";
		//		final String NINE_CHAR = "9";
		//
		//		final boolean isValid = StringUtils.isNotEmpty(mobileNumber) && StringUtils.isNumeric(mobileNumber)
		//				&& StringUtils.startsWithAny(mobileNumber, new String[]
		//				{ EIGHT_CHAR, NINE_CHAR, SIX_CHAR }) && (mobileNumber.length() <= MOBILE_NUM_LEN);
		final boolean isValid = true;
		return isValid;
	}

	/**
	 * @param sapCode
	 * @return
	 */
	protected boolean validateSapCode(final String sapCode)
	{
		final int SAP_CODE_LEN = 7; //should move this to properties file
		final String I_CHAR = "I";
		final String C_CHAR = "C";
		final String D_CHAR = "D";
		final String I_CHAR_LOW = "i";
		final String C_CHAR_LOW = "c";
		final String D_CHAR_LOW = "d";

		final boolean isValid = StringUtils.isNotEmpty(sapCode) && StringUtils.isAlphanumeric(sapCode)
				&& StringUtils.startsWithAny(sapCode, new String[]
				{ I_CHAR, C_CHAR, D_CHAR, I_CHAR_LOW, C_CHAR_LOW, D_CHAR_LOW }) && (sapCode.length() <= SAP_CODE_LEN);

		return isValid;
	}

}
