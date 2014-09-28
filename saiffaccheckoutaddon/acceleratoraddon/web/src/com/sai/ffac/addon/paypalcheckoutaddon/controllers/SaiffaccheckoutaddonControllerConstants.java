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
package com.sai.ffac.addon.paypalcheckoutaddon.controllers;

/**
 */
public interface SaiffaccheckoutaddonControllerConstants
{
	// implement here controller constants used by this extension
	final String ADDON_PREFIX = "addon:/saiffaccheckoutaddon/";
	String SaiFfacPage = ADDON_PREFIX + "pages/checkout/multi/saiFfacPage";
	String SaiFfacSummaryPage = ADDON_PREFIX + "pages/checkout/multi/saiFfacSummaryPage";
	/* for paypal payment */
	final String USER_NAME = "paypal.merchant.username";
	final String PASSWORD = "paypal.merchant.password";
	final String SIGNATURE = "paypal.merchant.signature";
	final String RETURN_URL = "paypal.merchant.url.return";
	final String CANCEL_URL = "paypal.merchant.url.cancel";
	final String PAYMENT_MODE = "paypal.payment.mode"; //sandbox or live
	final String CHECKOUT_URL = "paypal.url.checkout";
	final String CURRENCY_CODE = "paypal.currency.type";
}
