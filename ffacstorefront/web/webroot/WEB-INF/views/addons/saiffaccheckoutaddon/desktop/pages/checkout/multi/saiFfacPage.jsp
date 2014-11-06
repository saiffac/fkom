<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="common" tagdir="/WEB-INF/tags/desktop/common" %>
<%@ taglib prefix="breadcrumb" tagdir="/WEB-INF/tags/desktop/nav/breadcrumb" %>
<%@ taglib prefix="multi-checkout" tagdir="/WEB-INF/tags/addons/b2ccheckoutaddon/desktop/checkout/multi" %>
<%@ taglib prefix="address" tagdir="/WEB-INF/tags/desktop/address" %>
 
 
<c:url value="${nextStepUrl}" var="continueUrl"/>
<c:url value="${previousStepUrl}" var="cancelUrl"/>
 
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
 
 <div id="globalMessages">
        <common:globalMessages/>
  </div>
 
    <%-- <multi-checkout:checkoutProgressBar steps="${checkoutSteps}" progressBarId="${progressBarId}"/> --%>
 
    <div class="span-14 append-1 text-paypal">
        <div id="checkoutContentPanel" class="clearfix">
           <div class="description"><h2>Paypal Payment</h2></div>
            <a class="ffacbtnCancel" href="${cancelUrl}"><spring:theme code="checkout.multi.cancel" text="Back"/></a>
            <a href="${continueUrl}"><img src="https://www.paypalobjects.com/en_US/i/btn/x-click-but6.gif"></a>
        </div>
        <div class="term">
	    	<h3><a href="">Terms and conditions</a></h3>
	        <p>Orders are not exchangeable nor refundable once payment has been processed.Please direct order queries to <a href="#">habifootwear@gmail.com</a><br>
	        Your orders will be shipped to Singapore, for your pick up at the FKOM venue. Orders not collected by 5pm, 13 January, will be given to local charity organization</p>
    	</div>
    </div>
 
    <multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="false" showPickupDeliveryEntries="false" showTax="false"/>
    <cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
        <cms:component component="${feature}"/>
    </cms:pageSlot>
 
</template:page>
<script>
$(function (){
	$('body').scrollTo('#tabAnchor');
});
</script>