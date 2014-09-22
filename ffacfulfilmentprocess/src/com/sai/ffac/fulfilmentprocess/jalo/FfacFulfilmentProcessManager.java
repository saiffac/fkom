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
package com.sai.ffac.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.sai.ffac.fulfilmentprocess.constants.FfacFulfilmentProcessConstants;

import org.apache.log4j.Logger;

@SuppressWarnings("PMD")
public class FfacFulfilmentProcessManager extends GeneratedFfacFulfilmentProcessManager
{
	@SuppressWarnings("unused")
	private static Logger log = Logger.getLogger( FfacFulfilmentProcessManager.class.getName() );
	
	public static final FfacFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (FfacFulfilmentProcessManager) em.getExtension(FfacFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
