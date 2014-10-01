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
 
    <multi-checkout:checkoutProgressBar steps="${checkoutSteps}" progressBarId="${progressBarId}"/>
 
    <div class="span-14 append-1">
        <div id="checkoutContentPanel" class="clearfix">
            <div class="description"><p><b>Paypal Payment</b></p></div>
            <a class="button" href="${cancelUrl}"><spring:theme code="checkout.multi.cancel" text="Cancel"/></a>
            <a class="button" href="${continueUrl}"><spring:theme code="checkout.multi.continue" text="Continue"/></a>
        </div>
    </div>
 
    <multi-checkout:checkoutOrderDetails cartData="${cartData}" showShipDeliveryEntries="false" showPickupDeliveryEntries="false" showTax="false"/>
    <cms:pageSlot position="SideContent" var="feature" element="div" class="span-24 side-content-slot cms_disp-img_slot">
        <cms:component component="${feature}"/>
    </cms:pageSlot>
 
</template:page>