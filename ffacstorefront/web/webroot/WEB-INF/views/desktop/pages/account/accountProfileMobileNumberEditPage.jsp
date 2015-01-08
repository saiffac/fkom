<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url var="profileUrl" value="/my-account/profile" />
<div class="span-24">
	<div class="span-20">	
		<div class="accountContentPane clearfix">
			<div class="headline"><spring:theme code="text.account.change.mobileNumber" text="Profile"/></div>
			<div class="required right"><spring:theme code="form.required" text="Fields marked * are required"/></div>
			<div class="description"><spring:theme code="text.account.profile.updateMobileNumberNum" text="Enter your new Mobile & Shipping address"/></div>
			
			<form:form action="update-mobile-number" method="post" commandName="updateMobileNumberForm">

				<formElement:formInputBox idKey="profile.mobileNumber" labelKey="profile.mobileNumber" path="mobileNumber" inputCSS="text" mandatory="false"/>
				<formElement:formInputBox idKey="profile.shippingAddress" labelKey="profile.shippingAddress" path="shippingAddress" inputCSS="text" mandatory="false"/>

				<div class="form-actions">
					<button type="button" class="negative" onclick="window.location='${profileUrl}'"><spring:theme code="text.account.profile.cancel" text="Cancel"/></button>
					<ycommerce:testId code="myAccount_profile_SaveUpdates_button">
						<button class="positive" type="submit"><spring:theme code="text.account.profile.saveUpdates" text="Save Updates"/></button>
					</ycommerce:testId>
				</div>
			</form:form>
			
		</div>
	</div>
</div>
