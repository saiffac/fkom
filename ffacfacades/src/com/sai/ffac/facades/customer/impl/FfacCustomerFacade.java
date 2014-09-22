/**
 *
 */
package com.sai.ffac.facades.customer.impl;

import static de.hybris.platform.servicelayer.util.ServicesUtil.validateParameterNotNullStandardMessage;

import de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade;
import de.hybris.platform.commerceservices.customer.DuplicateUidException;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.user.CustomerModel;
import de.hybris.platform.core.model.user.TitleModel;
import de.hybris.platform.core.model.user.UserModel;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import com.sai.ffac.facades.user.data.FfacCustomerData;
import com.sai.ffac.facades.user.data.FfacRegisterData;


/**
 * @author tuan.truong
 *
 */
public class FfacCustomerFacade extends DefaultCustomerFacade
{

	private Populator<CustomerModel, FfacCustomerData> customerPopulator;

	/**
	 * @return the customerPopulator
	 */
	public Populator<CustomerModel, FfacCustomerData> getCustomerPopulator()
	{
		return customerPopulator;
	}

	/**
	 * @param customerPopulator
	 *           the customerPopulator to set
	 */
	@Required
	public void setCustomerPopulator(final Populator<CustomerModel, FfacCustomerData> customerPopulator)
	{
		this.customerPopulator = customerPopulator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#getCurrentCustomer()
	 */
	@Override
	public FfacCustomerData getCurrentCustomer()
	{
		final UserModel userModel = getUserService().getCurrentUser();
		if (userModel instanceof CustomerModel)
		{
			//			final CustomerPopulator customerPopulator = new CustomerPopulator();
			final CustomerModel customerModel = (CustomerModel) userModel;
			final FfacCustomerData customerData = new FfacCustomerData();
			getCustomerPopulator().populate(customerModel, customerData);
			return customerData;
		}
		else
		{
			// YTODO Auto-generated method stub
			return (FfacCustomerData) super.getCurrentCustomer();
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#updateProfile(de.hybris.platform.
	 * commercefacades.user.data.CustomerData)
	 */
	public void updateProfile(final FfacCustomerData customerData) throws DuplicateUidException
	{
		validateDataBeforeUpdate(customerData);

		final String name = getCustomerNameStrategy().getName(customerData.getFirstName(), customerData.getLastName());
		final CustomerModel customer = getCurrentSessionCustomer();
		customer.setOriginalUid(customerData.getDisplayUid());
		customer.setSapCode(customerData.getSapCode());
		getCustomerAccountService().updateProfile(customer, customerData.getTitleCode(), name, customerData.getUid());
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#validateDataBeforeUpdate(de.hybris.platform
	 * .commercefacades.user.data.CustomerData)
	 */
	protected void validateDataBeforeUpdate(final FfacCustomerData customerData)
	{
		validateParameterNotNullStandardMessage("customerData", customerData);
		Assert.hasText(customerData.getTitleCode(), "The field [TitleCode] cannot be empty");
		Assert.hasText(customerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(customerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(customerData.getUid(), "The field [Uid] cannot be empty");
		Assert.hasText(customerData.getSapCode(), "The field [SAP I or C] cannot be empty");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.commercefacades.customer.impl.DefaultCustomerFacade#register(de.hybris.platform.commercefacades
	 * .user.data.RegisterData)
	 */
	public void register(final FfacRegisterData registerData) throws DuplicateUidException
	{
		validateParameterNotNullStandardMessage("registerData", registerData);
		Assert.hasText(registerData.getFirstName(), "The field [FirstName] cannot be empty");
		Assert.hasText(registerData.getLastName(), "The field [LastName] cannot be empty");
		Assert.hasText(registerData.getLogin(), "The field [Login] cannot be empty");
		Assert.hasText(registerData.getSapCode(), "The field [SAP I or C number] cannot be empty");

		final CustomerModel newCustomer = getModelService().create(CustomerModel.class);
		newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));

		if (StringUtils.isNotBlank(registerData.getFirstName()) && StringUtils.isNotBlank(registerData.getLastName()))
		{
			newCustomer.setName(getCustomerNameStrategy().getName(registerData.getFirstName(), registerData.getLastName()));
		}
		final TitleModel title = getUserService().getTitleForCode(registerData.getTitleCode());
		newCustomer.setTitle(title);
		setUidForRegister(registerData, newCustomer);
		newCustomer.setSapCode(registerData.getSapCode()); //set SAP I or C number to persist
		newCustomer.setSessionLanguage(getCommonI18NService().getCurrentLanguage());
		newCustomer.setSessionCurrency(getCommonI18NService().getCurrentCurrency());
		getCustomerAccountService().register(newCustomer, registerData.getPassword());
	}

}
