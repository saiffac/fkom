/**
 *
 */
package com.sai.ffac.facades.order.impl;

import de.hybris.platform.acceleratorfacades.order.impl.DefaultAcceleratorCheckoutFacade;
import de.hybris.platform.core.enums.OrderStatus;
import de.hybris.platform.core.model.order.CartModel;
import de.hybris.platform.core.model.order.OrderModel;


/**
 * @author tuan.truong
 *
 */
public class FfacCheckoutFacade extends DefaultAcceleratorCheckoutFacade
{

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.commercefacades.order.impl.DefaultCheckoutFacade#afterPlaceOrder(de.hybris.platform.core.model
	 * .order.CartModel, de.hybris.platform.core.model.order.OrderModel)
	 */
	@Override
	protected void afterPlaceOrder(final CartModel cartModel, final OrderModel orderModel)
	{
		if (orderModel != null)
		{
			// Remove cart
			getCartService().removeSessionCart();
			getModelService().refresh(orderModel);
			orderModel.setStatus(OrderStatus.COMPLETED);
			getModelService().save(orderModel);
		}
	}


}
