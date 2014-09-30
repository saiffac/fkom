<%@ tag body-content="scriptless" trimDirectiveWhitespaces="true"%>
<%@ attribute name="pageTitle" required="false" rtexprvalue="true"%>
<%@ attribute name="pageCss" required="false" fragment="true"%>
<%@ attribute name="pageScripts" required="false" fragment="true"%>
<%@ attribute name="hideHeaderLinks" required="false"%>

<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="header" tagdir="/WEB-INF/tags/desktop/common/header"%>
<%@ taglib prefix="footer" tagdir="/WEB-INF/tags/desktop/common/footer"%>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart"%>
<%@ taglib prefix="nav" tagdir="/WEB-INF/tags/desktop/nav"%>
<template:master pageTitle="${pageTitle}">

	<jsp:attribute name="pageCss">
		<jsp:invoke fragment="pageCss" />
	</jsp:attribute>

	<jsp:attribute name="pageScripts">
		<jsp:invoke fragment="pageScripts" />
	</jsp:attribute>

	<jsp:body>

		<div id="page" data-currency-iso-code="${currentCurrency.isocode}">
			<spring:theme code="text.skipToContent" var="skipToContent" />
			<a href="#skip-to-content" class="skiptocontent" data-role="none">${skipToContent}</a>
			<spring:theme code="text.skipToNavigation" var="skipToNavigation" />
			<a href="#skiptonavigation" class="skiptonavigation" data-role="none">${skipToNavigation}</a>
			<header:header hideHeaderLinks="${hideHeaderLinks}" />
			<a id="skiptonavigation"></a>
			
			<div id="content" class="main clearfix">
			
			
 
 <div class="top-main">
      <h3>MANY THANKS TO OUR SPONSORS</h3>
      <div class="logo-company"> <a href="#"><img
							src="${commonResourcePath}/../ffac/images/logo-sharp.png" alt=""></a> <a
							href="#"><img
							src="${commonResourcePath}/../ffac/images/logo-sai.png" alt=""></a> <a
							href="#"><img
							src="${commonResourcePath}/../ffac/images/logo-hybris.png" alt=""></a> </div>
      <h3> buy a handmade item &amp; help the community</h3>
      <p>Just by saving a cup of coffee, a breakfast to help us buy those lovely hand made 
        product crafted by people who has a very hard life right now. 
        Help them, help yourself for a better world, </p>
    </div>
    <nav:topNavigation />
			<header:bottomHeader />
			<a id="skip-to-content"></a>
				<jsp:doBody />
			</div>
			
			<footer:footer />
		</div>

	</jsp:body>

</template:master>
