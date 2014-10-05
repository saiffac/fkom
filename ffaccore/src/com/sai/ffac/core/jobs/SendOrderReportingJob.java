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

import java.util.ArrayList;
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
		final String subject = "FFAC Order List Report";
		//		final String content = "Content of order list";
		final String DISTANCE_TAB = "&nbsp;&nbsp;&nbsp;&nbsp;";
		final String END_LINE = "<br>------------------------------------------------------------------<br>";
		final String OPEN_DIV = "<div>";
		final String CLOSE_DIV = "</div>";
		final String HEADER = "<p>" + "<Order Code>" + DISTANCE_TAB + "<Order Created Date>" + DISTANCE_TAB
				+ "<Total price of Order>" + DISTANCE_TAB + "<Title>" + DISTANCE_TAB + "<Name>" + DISTANCE_TAB
				+ "<Email/Customer ID>" + DISTANCE_TAB + "<SAP Number>" + DISTANCE_TAB + "<Mobile Number>" + DISTANCE_TAB
				+ "<Product infos>" + DISTANCE_TAB + "<Product Quantity>" + DISTANCE_TAB + "<Total price of product (*)>" + "</p>";

		final String queryStr = "SELECT {o.code}, {o.date}, {o.totalprice}" + ", {t.code}"
				+ ", {c.name}, {c.originalUid}, {c.sapcode}, {c.mobilenumber}" + ", {oe.info}, {oe.quantity}, {oe.totalprice}"
				+ " FROM {Order as o}, {OrderEntry as oe}, {Customer as c}, {Title as t}"
				+ " WHERE {o.pk}={oe.order} and {o.user}={c.pk} and {c.title}={t.pk}" + " ORDER BY {o.date}, {o.code}";

		final List<Class> resultClasses = new ArrayList<Class>();
		resultClasses.add(String.class);
		resultClasses.add(Date.class);
		resultClasses.add(Double.class);
		resultClasses.add(String.class);
		resultClasses.add(String.class);
		resultClasses.add(String.class);
		resultClasses.add(String.class);
		resultClasses.add(String.class);
		resultClasses.add(String.class);
		resultClasses.add(Long.class);
		resultClasses.add(Double.class);

		final FlexibleSearchQuery query = new FlexibleSearchQuery(queryStr);
		query.setResultClassList(resultClasses);
		final StringBuffer reportContent = new StringBuffer(HEADER);

		final SearchResult<List<Object>> searchResult = flexibleSearchService.search(query);
		final List<List<Object>> resultList = searchResult.getResult();
		final Iterator<List<Object>> resultIte = resultList.iterator();
		List<Object> resultRecords = null;
		while (resultIte.hasNext())
		{
			resultRecords = resultIte.next();

			final String orderCode = (String) resultRecords.get(0);
			final Date orderDate = (Date) resultRecords.get(1);
			final Double orderTotalPrice = (Double) resultRecords.get(2);
			final String titleCode = (String) resultRecords.get(3);

			final String cusName = (String) resultRecords.get(4);
			final String originalUId = (String) resultRecords.get(5);
			final String sapCode = (String) resultRecords.get(6);
			final String mobileNum = (String) resultRecords.get(7);

			final String entryInfo = (String) resultRecords.get(8);
			final Long entryQuantity = (Long) resultRecords.get(9);
			final Double entryTotalPrice = (Double) resultRecords.get(10);

			reportContent.append(OPEN_DIV);
			reportContent.append(orderCode).append(DISTANCE_TAB).append(orderDate.toString()).append(DISTANCE_TAB)
					.append(orderTotalPrice.toString());
			reportContent.append(DISTANCE_TAB).append(titleCode);
			reportContent.append(DISTANCE_TAB).append(cusName).append(DISTANCE_TAB).append(originalUId).append(DISTANCE_TAB)
					.append(sapCode).append(DISTANCE_TAB).append(mobileNum);
			reportContent.append(DISTANCE_TAB).append(entryInfo).append(DISTANCE_TAB).append(entryQuantity).append(DISTANCE_TAB)
					.append(entryTotalPrice);
			reportContent.append(CLOSE_DIV);
			reportContent.append(END_LINE); //next record
		}

		final Configuration cfg = configurationService.getConfiguration();
		final String senderEmailAddress = cfg.getString("mail.from");
		final String receiverEmailAddress = cfg.getString("ffac.merchant.email");
		final String bccEmailAddress = cfg.getString("ffac.admin.email");
		final String replyTo = cfg.getString("mail.replyto");

		String content = reportContent.toString();
		//transform the content to raw text when the size is bigger than 4000 characters <cheat>
		if (content.length() > 4500) //DefaultEmailService.EMAIL_BODY_MAX_LENGTH=4000 -- not good here
		{
			content = content.replaceAll("&nbsp;", " ").replaceAll("<div>", "").replaceAll("<br>", "\r\n");
		}
		LOG.info("Start sending order reporting mails");
		final EmailAddressModel senderAddress = emailService.getOrCreateEmailAddressForEmail(senderEmailAddress, "FFAC Service");
		final EmailAddressModel receiverAddress = emailService.getOrCreateEmailAddressForEmail(receiverEmailAddress, "Mechant");
		final EmailAddressModel bccAddress = emailService.getOrCreateEmailAddressForEmail(bccEmailAddress, "Administrator");
		final EmailMessageModel message = emailService.createEmailMessage(Collections.singletonList(receiverAddress), null,
				Collections.singletonList(bccAddress), senderAddress, replyTo, subject, content, null);
		emailService.send(message);
		LOG.info("Complete sending order reporting mails");


		return new PerformResult(CronJobResult.SUCCESS, CronJobStatus.FINISHED);
	}

}
