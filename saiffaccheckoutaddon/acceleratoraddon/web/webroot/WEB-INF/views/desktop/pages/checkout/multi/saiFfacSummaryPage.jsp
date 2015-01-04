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
 
<template:page pageTitle="${pageTitle}" hideHeaderLinks="true">
 
 <div id="globalMessages">
        <common:globalMessages/>
  </div>
 
    <%-- <multi-checkout:checkoutProgressBar steps="${checkoutSteps}" progressBarId="${progressBarId}"/> --%>
 
    <div class="span-14 append-1">
        <div id="checkoutContentPanel" class="clearfix">
            <div class="description">
            <c:if test="${isSuccessful}">
   				<p>Your orders will be shipped to Singapore, for your pick up at the FKOM venue.<br>
   				Orders accepted through 14 January 2015. Orders made after <b>06 January 2015, 6pm SG time</b> will be shipped to you by 14 February, at the address indicated in your site registration profile.
				</p>
			</c:if>
            <c:if test="${!isSuccessful}">
            	<p>The payment process has not been successful. <b>Please try again...</b></p>
            </c:if>
            </div>
        </div>
    </div>
 
    <%-- <multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="false" showPickupDeliveryEntries="false" showTax="false"/> --%>
    <%-- <cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
        <cms:component component="${feature}"/>
    </cms:pageSlot> --%>
 
</template:page>
<script>
$(function (){
	$('body').scrollTo('#tabAnchor');
});
</script>