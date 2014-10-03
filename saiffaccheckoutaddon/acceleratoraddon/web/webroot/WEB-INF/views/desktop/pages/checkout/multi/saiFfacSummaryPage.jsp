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
   				<p>Your order will be available for collection from <b><c:out value="${deliveryDate}"/></b><br>
				All orders are to be collected at the following address: <br>
				<b>SAP Asia Pte Ltd</b> 30 Pasir Panjang Road, Level 3 Mapletree Business City Singapore 117440</p>
				<p>An email and SMS alert will sent to you describing the exact location and timing to collect your orders.</p>
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