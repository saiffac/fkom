/**
 *
 */
package com.sai.ffac.addon.paypalcheckoutaddon.controllers.pages;

import de.hybris.platform.acceleratorstorefrontcommons.annotations.RequireHardLogIn;
import de.hybris.platform.acceleratorstorefrontcommons.checkout.steps.CheckoutStep;
import de.hybris.platform.acceleratorstorefrontcommons.controllers.util.GlobalMessages;
import de.hybris.platform.cms2.exceptions.CMSItemNotFoundException;
import de.hybris.platform.commercefacades.order.CartFacade;
import de.hybris.platform.commercefacades.order.data.CartData;
import de.hybris.platform.commerceservices.order.CommerceCartModificationException;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.storefront.controllers.pages.checkout.steps.AbstractCheckoutStepController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.configuration.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.xml.sax.SAXException;

import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentReq;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentRequestType;
import urn.ebay.api.PayPalAPI.DoExpressCheckoutPaymentResponseType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsReq;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsRequestType;
import urn.ebay.api.PayPalAPI.GetExpressCheckoutDetailsResponseType;
import urn.ebay.api.PayPalAPI.PayPalAPIInterfaceServiceService;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutReq;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutRequestType;
import urn.ebay.api.PayPalAPI.SetExpressCheckoutResponseType;
import urn.ebay.apis.CoreComponentTypes.BasicAmountType;
import urn.ebay.apis.eBLBaseComponents.AckCodeType;
import urn.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentRequestDetailsType;
import urn.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentResponseDetailsType;
import urn.ebay.apis.eBLBaseComponents.GetExpressCheckoutDetailsResponseDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentActionCodeType;
import urn.ebay.apis.eBLBaseComponents.PaymentDetailsType;
import urn.ebay.apis.eBLBaseComponents.PaymentInfoType;
import urn.ebay.apis.eBLBaseComponents.PaymentStatusCodeType;
import urn.ebay.apis.eBLBaseComponents.SetExpressCheckoutRequestDetailsType;

import com.paypal.core.credential.ICredential;
import com.paypal.core.credential.SignatureCredential;
import com.paypal.exception.ClientActionRequiredException;
import com.paypal.exception.HttpErrorException;
import com.paypal.exception.InvalidCredentialException;
import com.paypal.exception.InvalidResponseDataException;
import com.paypal.exception.MissingCredentialException;
import com.paypal.exception.SSLConfigurationException;
import com.paypal.sdk.exceptions.OAuthException;
import com.sai.ffac.addon.paypalcheckoutaddon.controllers.SaiffaccheckoutaddonControllerConstants;


/**
 * @author tuan.truong
 *
 */
@Controller
@RequestMapping(value = "/checkout/multi/addon/sai-ffac")
public class SaiFfacCheckoutStepController extends AbstractCheckoutStepController
{
	private final static String SAI_FFAC = "sai-ffac";

	@Resource(name = "cartFacade")
	private CartFacade cartFacade;

	@Resource(name = "configurationService")
	private ConfigurationService configurationService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.CheckoutStepController#enterStep(org.springframework
	 * .ui.Model, org.springframework.web.servlet.mvc.support.RedirectAttributes)
	 */
	@Override
	@RequestMapping(method = RequestMethod.GET)
	@RequireHardLogIn
	public String enterStep(final Model model, final RedirectAttributes redirectAttributes) throws CMSItemNotFoundException,
			CommerceCartModificationException
	{
		this.prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		//		model.addAttribute(WebConstants.BREADCRUMBS_KEY,
		//				getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryMethod.breadcrumb"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		setCheckoutStepLinksForModel(model, getCheckoutStep());
		return SaiffaccheckoutaddonControllerConstants.SaiFfacPage;
	}

	/**
	 * @return CheckoutStep
	 */
	private CheckoutStep getCheckoutStep()
	{
		return getCheckoutStep(SAI_FFAC);
	}

	@RequestMapping(value = "/payment", method = RequestMethod.GET)
	@RequireHardLogIn
	public String doPayment(final Model model) throws CMSItemNotFoundException, CommerceCartModificationException
	{
		//		final String userName = "singaporemerchant_api1.sai-it.com";
		//		final String password = "F87M5GHSS9B4D6BT";
		//		final String signature = "AFcWxV21C7fd0v3bYYYRCpSSRl31ATh1YpYuS4pLJvtEWtvep.mhDM3.";
		//		final String returnURL = "https://54.169.43.57:9002/ffacstorefront/en/checkout/multi/addon/sai-ffac/summary";
		//		final String cancelURL = "https://54.169.43.57:9002/ffacstorefront/en/checkout/multi/addon/sai-ffac";
		//		final String mode = "sandbox"; //or live
		//		final String payPalUrl = "https://www.sandbox.paypal.com/cgi-bin/webscr?cmd=_express-checkout&token=";
		//		final String currencyCode = "SGD";

		final Configuration cfg = configurationService.getConfiguration();
		final String userName = cfg.getString(SaiffaccheckoutaddonControllerConstants.USER_NAME);
		final String password = cfg.getString(SaiffaccheckoutaddonControllerConstants.PASSWORD);
		final String signature = cfg.getString(SaiffaccheckoutaddonControllerConstants.SIGNATURE);
		final String returnURL = cfg.getString(SaiffaccheckoutaddonControllerConstants.RETURN_URL);
		final String cancelURL = cfg.getString(SaiffaccheckoutaddonControllerConstants.CANCEL_URL);
		final String mode = cfg.getString(SaiffaccheckoutaddonControllerConstants.PAYMENT_MODE);
		final String payPalUrl = cfg.getString(SaiffaccheckoutaddonControllerConstants.CHECKOUT_URL);
		final String currencyCode = cfg.getString(SaiffaccheckoutaddonControllerConstants.CURRENCY_CODE);

		final String orderDescription = "Order to SAI FFAC. Total: S$ ";

		String token = null;

		final CurrencyCodeType currencyType = CurrencyCodeType.fromValue(currencyCode);
		final CartData cart = cartFacade.getSessionCart();
		final double orderSum = cart.getTotalPrice().getValue().doubleValue();
		final String customId = cart.getCode();
		final String invoiceId = "INVOICE-" + cart.getCode() + "_" + Math.random();

		try
		{
			token = SetExpressCheckout(userName, password, signature, returnURL, cancelURL, mode, customId, orderDescription,
					invoiceId, orderSum, currencyType);
		}
		catch (SSLConfigurationException | InvalidCredentialException | HttpErrorException | InvalidResponseDataException
				| ClientActionRequiredException | MissingCredentialException | OAuthException | IOException | InterruptedException
				| ParserConfigurationException | SAXException e)
		{
			// YTODO Auto-generated catch block
			e.printStackTrace();
		}

		if (token == null)
		{
			this.prepareDataForPage(model);
			storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
			//			model.addAttribute(WebConstants.BREADCRUMBS_KEY,
			//					getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryMethod.breadcrumb"));
			model.addAttribute("metaRobots", "noindex,nofollow");
			model.addAttribute("payStatus", "service error");
			setCheckoutStepLinksForModel(model, getCheckoutStep()); //check if it is redundant?
			return SaiffaccheckoutaddonControllerConstants.SaiFfacSummaryPage;
		}

		return REDIRECT_PREFIX + payPalUrl + token;
	}


	@RequestMapping(value = "/summary", method = RequestMethod.GET)
	@RequireHardLogIn
	public String summarize(final Model model, final RedirectAttributes redirectAttributes,
			@RequestParam(value = "token", required = true) final String token,
			@RequestParam(value = "PayerID", required = true) final String payerId) throws CMSItemNotFoundException,
			CommerceCartModificationException, ParseException
	{
		final Configuration cfg = configurationService.getConfiguration();
		final String userName = cfg.getString(SaiffaccheckoutaddonControllerConstants.USER_NAME);
		final String password = cfg.getString(SaiffaccheckoutaddonControllerConstants.PASSWORD);
		final String signature = cfg.getString(SaiffaccheckoutaddonControllerConstants.SIGNATURE);
		final String mode = cfg.getString(SaiffaccheckoutaddonControllerConstants.PAYMENT_MODE);

		GetExpressCheckoutDetailsResponseDetailsType expCheckoutDetail;
		boolean isSuccess = false;
		try
		{
			expCheckoutDetail = getExpressCheckoutDetails(token, userName, password, signature, mode);
			if (expCheckoutDetail != null)
			{
				isSuccess = commitExpressCheckout(expCheckoutDetail, userName, password, signature, token, payerId, mode);
			}
		}
		catch (OAuthException | SSLConfigurationException | InvalidCredentialException | HttpErrorException
				| InvalidResponseDataException | ClientActionRequiredException | MissingCredentialException | IOException
				| InterruptedException | ParserConfigurationException | SAXException e)
		{
			// YTODO Auto-generated catch block
			e.printStackTrace();
		}
		if (isSuccess)
		{
			cartFacade.removeSessionCart();

			String deliveryDate = "";

			final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			final Date currentDate = new Date();
			//		final Date currentDate = dateFormat.parse("15/11/2014");
			//		final Date currentDate = dateFormat.parse("18/10/2014");
			//			final Date currentDate = dateFormat.parse("02/10/2014");

			final Date milestone1001 = dateFormat.parse("01/10/2014");
			final Date milestone1017 = dateFormat.parse("17/10/2014");
			final Date milestone1114 = dateFormat.parse("14/11/2014");
			final Date milestone1230 = dateFormat.parse("30/12/2014");

			if ((currentDate.compareTo(milestone1001) >= 0) && (currentDate.compareTo(milestone1017) <= 0))
			{
				deliveryDate = "13/11/2014";
			}
			else if ((currentDate.compareTo(milestone1017) > 0) && (currentDate.compareTo(milestone1114) <= 0))
			{
				deliveryDate = "09/12/2014";
			}
			else if ((currentDate.compareTo(milestone1114) > 0) && (currentDate.compareTo(milestone1230) <= 0))
			{
				deliveryDate = "12/01/2015";
			}
			else
			{
				deliveryDate = currentDate.toString();
			}

			model.addAttribute("isSuccessful", Boolean.TRUE);
			model.addAttribute("deliveryDate", deliveryDate);
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.CONF_MESSAGES_HOLDER, "checkout.multi.successful");

		}
		else
		{
			model.addAttribute("isSuccessful", Boolean.FALSE);
			GlobalMessages.addFlashMessage(redirectAttributes, GlobalMessages.ERROR_MESSAGES_HOLDER, "checkout.multi.fail");
		}

		this.prepareDataForPage(model);
		storeCmsPageInModel(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		setUpMetaDataForContentPage(model, getContentPageForLabelOrId(MULTI_CHECKOUT_SUMMARY_CMS_PAGE_LABEL));
		//			model.addAttribute(WebConstants.BREADCRUMBS_KEY,
		//					getResourceBreadcrumbBuilder().getBreadcrumbs("checkout.multi.deliveryMethod.breadcrumb"));
		model.addAttribute("metaRobots", "noindex,nofollow");
		setCheckoutStepLinksForModel(model, getCheckoutStep()); //check if it is redundant?
		return SaiffaccheckoutaddonControllerConstants.SaiFfacSummaryPage;

	}

	public String SetExpressCheckout(final String userName, final String password, final String signature, final String returnURL,
			final String cancelURL, final String mode, final String customId, final String orderDescription, final String invoiceId,
			final double orderSum, final CurrencyCodeType currencyType) throws SSLConfigurationException,
			InvalidCredentialException, HttpErrorException, InvalidResponseDataException, ClientActionRequiredException,
			MissingCredentialException, OAuthException, IOException, InterruptedException, ParserConfigurationException,
			SAXException
	{

		final BasicAmountType orderTotal = new BasicAmountType();
		orderTotal.setValue(Double.toString(orderSum));
		orderTotal.setCurrencyID(currencyType);

		//		final PaymentDetailsItemType paymentItem = new PaymentDetailsItemType();
		//		paymentItem.setAmount(new BasicAmountType(currencyType, "20.5"));
		//		paymentItem.setDescription("bla description");
		//		paymentItem.setName("bla name");

		final PaymentDetailsType payDetail = new PaymentDetailsType();
		payDetail.setOrderDescription(orderDescription + Double.toString(orderSum));
		payDetail.setInvoiceID(invoiceId);
		payDetail.setOrderTotal(orderTotal);
		payDetail.setPaymentAction(PaymentActionCodeType.SALE);
		//		payDetail.getPaymentDetailsItem().add(paymentItem);

		final List<PaymentDetailsType> paymentDetails = new ArrayList<PaymentDetailsType>();
		paymentDetails.add(payDetail);

		final SetExpressCheckoutRequestDetailsType expCheckoutReqDetail = new SetExpressCheckoutRequestDetailsType();
		expCheckoutReqDetail.setPaymentDetails(paymentDetails);
		expCheckoutReqDetail.setReturnURL(returnURL);
		expCheckoutReqDetail.setCancelURL(cancelURL);
		expCheckoutReqDetail.setCustom(customId);

		final SetExpressCheckoutRequestType pprequest = new SetExpressCheckoutRequestType();
		pprequest.setSetExpressCheckoutRequestDetails(expCheckoutReqDetail);

		final SetExpressCheckoutReq request = new SetExpressCheckoutReq();
		request.setSetExpressCheckoutRequest(pprequest);

		final Properties profile = new Properties();
		//		profile.put("service.EndPoint", "https://api-3t.sandbox.paypal.com/2.0"); //or set mode=sandbox
		profile.put("mode", mode); //sandbox or live

		final PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(profile);

		final ICredential credential = new SignatureCredential(userName, password, signature);
		final SetExpressCheckoutResponseType response = service.setExpressCheckout(request, credential);

		String token = null;
		final AckCodeType ackCode = response.getAck();
		if (ackCode == AckCodeType.SUCCESS || ackCode == AckCodeType.SUCCESSWITHWARNING)
		{
			token = response.getToken();
		}

		//		final String ack = response.getAck().getValue();
		return token;
	}

	public static GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetails(final String token,
			final String userName, final String password, final String signature, final String mode) throws OAuthException,
			SSLConfigurationException, InvalidCredentialException, HttpErrorException, InvalidResponseDataException,
			ClientActionRequiredException, MissingCredentialException, IOException, InterruptedException,
			ParserConfigurationException, SAXException
	{
		final GetExpressCheckoutDetailsRequestType pprequest = new GetExpressCheckoutDetailsRequestType();
		pprequest.setToken(token);

		final GetExpressCheckoutDetailsReq request = new GetExpressCheckoutDetailsReq();
		request.setGetExpressCheckoutDetailsRequest(pprequest);

		final Properties profile = new Properties();
		//		profile.put("service.EndPoint", "https://api-3t.sandbox.paypal.com/2.0");
		profile.put("mode", mode);

		final PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(profile);

		final ICredential credential = new SignatureCredential(userName, password, signature);
		final GetExpressCheckoutDetailsResponseType expCheckoutDetailResponse = service.getExpressCheckoutDetails(request,
				credential);
		GetExpressCheckoutDetailsResponseDetailsType expCheckoutDetail = null;
		final AckCodeType ack = expCheckoutDetailResponse.getAck();
		if (ack == AckCodeType.SUCCESS || ack == AckCodeType.SUCCESSWITHWARNING)
		{
			expCheckoutDetail = expCheckoutDetailResponse.getGetExpressCheckoutDetailsResponseDetails();
		}

		return expCheckoutDetail;
	}

	public static boolean commitExpressCheckout(final GetExpressCheckoutDetailsResponseDetailsType expCheckoutDetail,
			final String userName, final String password, final String signature, final String token, final String payerId,
			final String mode) throws SSLConfigurationException, InvalidCredentialException, HttpErrorException,
			InvalidResponseDataException, ClientActionRequiredException, MissingCredentialException, OAuthException, IOException,
			InterruptedException, ParserConfigurationException, SAXException
	{
		final DoExpressCheckoutPaymentRequestDetailsType expCheckoutPaymentDetail = new DoExpressCheckoutPaymentRequestDetailsType();
		expCheckoutPaymentDetail.setToken(token);
		expCheckoutPaymentDetail.setPayerID(payerId);
		expCheckoutPaymentDetail.setPaymentDetails(expCheckoutDetail.getPaymentDetails());
		expCheckoutPaymentDetail.setPaymentAction(expCheckoutDetail.getPaymentDetails().get(0).getPaymentAction()); //remember to check null

		final DoExpressCheckoutPaymentRequestType pprequest = new DoExpressCheckoutPaymentRequestType();
		pprequest.setDoExpressCheckoutPaymentRequestDetails(expCheckoutPaymentDetail);

		final DoExpressCheckoutPaymentReq request = new DoExpressCheckoutPaymentReq();
		request.setDoExpressCheckoutPaymentRequest(pprequest);

		final Properties profile = new Properties();
		//		profile.put("service.EndPoint", "https://api-3t.sandbox.paypal.com/2.0");
		profile.put("mode", mode);

		final PayPalAPIInterfaceServiceService service = new PayPalAPIInterfaceServiceService(profile);

		final ICredential credential = new SignatureCredential(userName, password, signature);
		final DoExpressCheckoutPaymentResponseType expCheckoutPaymentResponse = service.doExpressCheckoutPayment(request,
				credential);

		final DoExpressCheckoutPaymentResponseDetailsType expCheckoutPaymentDetails = expCheckoutPaymentResponse
				.getDoExpressCheckoutPaymentResponseDetails();
		if (expCheckoutPaymentDetails != null)
		{
			final PaymentInfoType paymentInfo = expCheckoutPaymentDetails.getPaymentInfo().get(0);
			if (paymentInfo.getPaymentStatus() == PaymentStatusCodeType.COMPLETED)
			{
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.CheckoutStepController#back(org.springframework
	 * .web.servlet.mvc.support.RedirectAttributes)
	 */
	@Override
	@RequireHardLogIn
	@RequestMapping(value = "/back", method = RequestMethod.GET)
	public String back(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().previousStep();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.storefront.controllers.pages.checkout.steps.CheckoutStepController#next(org.springframework
	 * .web.servlet.mvc.support.RedirectAttributes)
	 */
	@Override
	@RequireHardLogIn
	@RequestMapping(value = "/next", method = RequestMethod.GET)
	public String next(final RedirectAttributes redirectAttributes)
	{
		return getCheckoutStep().nextStep();
	}

}
