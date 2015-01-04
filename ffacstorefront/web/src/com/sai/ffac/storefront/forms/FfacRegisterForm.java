/**
 *
 */
package com.sai.ffac.storefront.forms;

import de.hybris.platform.acceleratorstorefrontcommons.forms.RegisterForm;


/**
 * @author tuan.truong
 *
 */
public class FfacRegisterForm extends RegisterForm
{
	private String sapCode; //SAP I or C number
	private String mobileNumber; //mobile number
	private String shippingAddress; //shipping address

	/**
	 * @return the shippingAddress
	 */
	public String getShippingAddress()
	{
		return shippingAddress;
	}

	/**
	 * @param shippingAddress
	 *           the shippingAddress to set
	 */
	public void setShippingAddress(final String shippingAddress)
	{
		this.shippingAddress = shippingAddress;
	}

	/**
	 * @return the mobileNumber
	 */
	public String getMobileNumber()
	{
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 *           the mobileNumber to set
	 */
	public void setMobileNumber(final String mobileNumber)
	{
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return the sapCode
	 */
	public String getSapCode()
	{
		return sapCode;
	}

	/**
	 * @param sapCode
	 *           the sapCode to set
	 */
	public void setSapCode(final String sapCode)
	{
		this.sapCode = sapCode;
	}
}
