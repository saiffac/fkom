/**
 *
 */
package com.sai.ffac.core.jobs;

import de.hybris.platform.acceleratorservices.email.EmailService;
import de.hybris.platform.acceleratorservices.model.email.EmailAddressModel;
import de.hybris.platform.acceleratorservices.model.email.EmailMessageModel;
import de.hybris.platform.cronjob.enums.CronJobResult;
import de.hybris.platform.cronjob.enums.CronJobStatus;
import de.hybris.platform.cronjob.model.CronJobModel;
import de.hybris.platform.servicelayer.config.ConfigurationService;
import de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable;
import de.hybris.platform.servicelayer.cronjob.PerformResult;
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.log4j.Logger;


/**
 * @author tuan.truong
 *
 */
public class SendOrderReportingJob extends AbstractJobPerformable<CronJobModel>
{
	private EmailService emailService;
	//	private FlexibleSearchService flexibleSearchService;
	private ConfigurationService configurationService;

	//		private DefaultOrderReportService orderReportService;

	private static final Logger LOG = Logger.getLogger(SendOrderReportingJob.class);

	/**
	 * @param emailService
	 *           the emailService to set
	 */
	public void setEmailService(final EmailService emailService)
	{
		this.emailService = emailService;
	}

	/**
	 * @param flexibleSearchService
	 *           the flexibleSearchService to set
	 */
	//	@Override
	//	public void setFlexibleSearchService(final FlexibleSearchService flexibleSearchService)
	//	{
	//		this.flexibleSearchService = flexibleSearchService;
	//	}

	/**
	 * @param configurationService
	 *           the configurationService to set
	 */
	public void setConfigurationService(final ConfigurationService configurationService)
	{
		this.configurationService = configurationService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.hybris.platform.servicelayer.cronjob.AbstractJobPerformable#perform(de.hybris.platform.cronjob.model.CronJobModel
	 * )
	 */
	@Override
	public PerformResult perform(final CronJobModel arg0)
	{
		final String subject = "FKOM Order List Report";
		final String queryStr = "SELECT {o.code}, {o.date}, {o.totalprice}" + ", {t.code}"
				+ ", {c.name}, {c.originalUid}, {c.sapcode}, {c.mobilenumber}, {c.shippingaddress}"
				+ ", {oe.info}, {oe.quantity}, {oe.totalprice}"
				+ " FROM {Order as o}, {OrderEntry as oe}, {Customer as c}, {Title as t}"
				+ " WHERE {o.pk}={oe.order} and {o.user}={c.pk} and {c.title}={t.pk}" + " ORDER BY {o.date}, {o.code}";

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);
		query.setResultClassList(Arrays.asList(String.class, Date.class, Double.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, Long.class, Double.class));

		final StringBuilder reportContent = new StringBuilder(generateHeader());

		final SearchResult<List<Object>> searchResult = flexibleSearchService.search(query);
		final List<List<Object>> resultList = searchResult.getResult();
		final Iterator<List<Object>> resultIte = resultList.iterator();
		List<Object> resultRecords = null;
		while (resultIte.hasNext())
		{
			resultRecords = resultIte.next();
			//order infos
			final String orderCode = (String) resultRecords.get(0);
			final Date orderDate = (Date) resultRecords.get(1);
			//			final String orderDateStr = formatDatetime(orderDate);
			final Double orderTotalPrice = (Double) resultRecords.get(2);
			//title info
			final String titleCode = (String) resultRecords.get(3);
			//customer info
			final String cusName = (String) resultRecords.get(4);
			final String originalUId = (String) resultRecords.get(5);
			final String sapCode = (String) resultRecords.get(6);
			final String mobileNum = (String) resultRecords.get(7);
			final String shippingAdd = (String) resultRecords.get(8);
			//order entry info
			final String entryInfo = (String) resultRecords.get(9);
			final Long entryQuantity = (Long) resultRecords.get(10);
			final Double entryTotalPrice = (Double) resultRecords.get(11);

			reportContent.append(generateBodyLine(orderCode, orderDate, orderTotalPrice, titleCode, cusName, originalUId, sapCode,
					mobileNum, shippingAdd, entryInfo, entryQuantity, entryTotalPrice));
		}

		reportContent.append(generateFooter());

		final Configuration cfg = configurationService.getConfiguration();
		final String senderEmailAddress = cfg.getString("mail.from");
		final String receiverEmailAddress = cfg.getString("ffac.merchant.email");
		final String bccEmailAddress = cfg.getString("ffac.admin.email");
		final String replyTo = cfg.getString("mail.replyto");

		LOG.info("Start sending order reporting mails");
		final String content = reportContent.toString();
		final EmailAddressModel senderAddress = emailService.getOrCreateEmailAddressForEmail(senderEmailAddress, "FKOM Service");
		final EmailAddressModel receiverAddress = emailService.getOrCreateEmailAddressForEmail(receiverEmailAddress, "Mechant");
		final EmailAddressModel bccAddress = emailService.getOrCreateEmailAddressForEmail(bccEmailAddress, "Administrator");
		final EmailMessageModel message = emailService.createEmailMessage(Collections.singletonList(receiverAddress), null,
				Collections.singletonList(bccAddress), senderAddress, replyTo, subject, content, null);
		emailService.send(message);
		LOG.info(content);
		LOG.info("Complete sending order reporting mails");

		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

	public String generateHeader()
	{
		final String header = "<table cellspacing='0' border='0' style=' background-color: #fff;' cellpadding='0' width='100%'><tr><td valign='top'>"
				+ "<table cellspacing='0' border='0' align='center' style='background: #fff' cellpadding='0' width='1024'><tr><td>"
				+ "<table cellspacing='0' border='0' height='40' cellpadding='0' width='1024' style='border:1px solid #999;padding:0 4px'><tr>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>Order Code</th><th style='border-right:1px solid #999;padding:0 4px'>Order Created Date</th>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>Total price of Order</th><th style='border-right:1px solid #999;padding:0 4px'>Title</th>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>Name</th><th style='border-right:1px solid #999;padding:0 4px'>Email-Customer ID</th>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>SAP Number</th><th style='border-right:1px solid #999;padding:0 4px'>Mobile Number</th>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>Shipping address</th>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>Product infos</th><th style='border-right:1px solid #999;padding:0 4px'>Product Quantity</th>"
				+ "<th style='border-right:1px solid #999;padding:0 4px'>Total price of product</th><th style='padding:0 4px'>Collection date</th></tr>";

		return header;
	}

	public String generateBodyLine(final String orderCode, final Date orderDate, final Double orderTotalPrice,
			final String titleCode, final String cusName, final String originalUId, final String sapCode, final String mobileNum,
			final String shippingAdd, final String entryInfo, final Long entryQuantity, final Double entryTotalPrice)
	{
		final String orderDateStr = formatDatetime(orderDate);
		final StringBuilder lineBuilder = new StringBuilder();
		lineBuilder.append("<tr>").append("<td style='border-right:1px solid #999; border-top:1px solid #999'>").append(orderCode)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>")
				.append(orderDateStr)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>")
				.append(orderTotalPrice.toString())
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(titleCode)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(cusName)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(originalUId)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(sapCode)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(mobileNum)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(shippingAdd)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>").append(entryInfo)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>")
				.append(entryQuantity)
				.append("</td><td style='border-right:1px solid #999; border-top:1px solid #999;padding:0 4px'>")
				.append(entryTotalPrice).append("</td><td style=' border-top:1px solid #999;padding:0 4px'>")
				.append(getCollectionDate(orderDate)).append("</td></tr>");

		return lineBuilder.toString();
	}

	public String generateFooter()
	{
		return "</table></td></tr></table></td></tr></table>";
	}

	public String getCollectionDate(final Date orderDate)
	{
		final String deliveryDate = "6 Jan";

		/*
		 * final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		 *
		 * try { final Date milestone1001 = dateFormat.parse("01/10/2014"); final Date milestone1017 =
		 * dateFormat.parse("17/10/2014"); final Date milestone1114 = dateFormat.parse("14/11/2014"); final Date
		 * milestone1230 = dateFormat.parse("30/12/2014");
		 *
		 * if ((orderDate.compareTo(milestone1001) >= 0) && (orderDate.compareTo(milestone1017) <= 0)) { deliveryDate =
		 * "13/11/2014"; } else if ((orderDate.compareTo(milestone1017) > 0) && (orderDate.compareTo(milestone1114) <= 0))
		 * { deliveryDate = "09/12/2014"; } else if ((orderDate.compareTo(milestone1114) > 0) &&
		 * (orderDate.compareTo(milestone1230) <= 0)) { deliveryDate = "12/01/2015"; } else { deliveryDate =
		 * orderDate.toString(); } } catch (final ParseException e) { // YTODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		return deliveryDate;
	}

	public String formatDatetime(final Date srcDate)
	{
		final DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm z");
		final String dateStr = dateFormat.format(srcDate);

		return dateStr;
	}

}
