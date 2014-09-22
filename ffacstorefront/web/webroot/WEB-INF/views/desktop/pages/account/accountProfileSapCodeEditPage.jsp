<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="formElement" tagdir="/WEB-INF/tags/desktop/formElement" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>

<c:url var="profileUrl" value="/my-account/profile" />
<div class="span-24">
	<div class="span-20 last">	
		<div class="accountContentPane clearfix">
			<div class="headline"><spring:theme code="text.account.change.sapCode" text="Profile"/></div>
			<div class="required right"><spring:theme code="form.required" text="Fields marked * are required"/></div>
			<div class="description"><spring:theme code="text.account.profile.updateSapCodeNum" text="Enter your new I or C number"/></div>
			
			<form:form action="update-sap-code" method="post" commandName="updateSapCodeForm">

				<formElement:formInputBox idKey="profile.sapCode" labelKey="profile.sapCode" path="sapCode" inputCSS="text" mandatory="true"/>

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
