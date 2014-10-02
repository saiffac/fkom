<%@ tag body-content="empty" trimDirectiveWhitespaces="true" %>
<%@ attribute name="product" required="true" type="de.hybris.platform.commercefacades.product.data.ProductData" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="template" tagdir="/WEB-INF/tags/desktop/template" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/desktop/product" %>
<%@ taglib prefix="cart" tagdir="/WEB-INF/tags/desktop/cart" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="ycommerce" uri="http://hybris.com/tld/ycommercetags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="theme" tagdir="/WEB-INF/tags/shared/theme" %>
<%@ taglib prefix="format" tagdir="/WEB-INF/tags/shared/format" %>
<%@ taglib prefix="storepickup" tagdir="/WEB-INF/tags/desktop/storepickup" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="action" tagdir="/WEB-INF/tags/desktop/action" %>
<script type="text/javascript">
function PopupCenter(url, title, w, h) {
    // Fixes dual-screen position                         Most browsers      Firefox
    var dualScreenLeft = window.screenLeft != undefined ? window.screenLeft : screen.left;
    var dualScreenTop = window.screenTop != undefined ? window.screenTop : screen.top;

    width = window.innerWidth ? window.innerWidth : document.documentElement.clientWidth ? document.documentElement.clientWidth : screen.width;
    height = window.innerHeight ? window.innerHeight : document.documentElement.clientHeight ? document.documentElement.clientHeight : screen.height;

    var left = ((width / 2) - (w / 2)) + dualScreenLeft;
    var top = ((height / 2) - (h / 2)) + dualScreenTop;
    var newWindow = window.open(url, title, 'toolbar=no, location=no, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

    // Puts focus on the newWindow
    if (window.focus) {
        newWindow.focus();
    }
}
</script>
<spring:theme code="text.addToCart" var="addToCartText"/>
<%-- <c:url value="${product.url}/zoomImages" var="productUrl"/> --%>
<c:url value="${product.url}" var="productUrl"/>

<c:set value="${not empty product.potentialPromotions}" var="hasPromotion"/>

<ycommerce:testId code="product_wholeProduct">
<div class="product-image-wrapper">
              <div class="single-products">
	<div class="productinfo text-center ${hasPromotion ? 'productGridItemPromotion' : ''}">
		<%-- <a href="${productUrl}" data-href="${productUrl}"  title="${product.name}" class="productMainLink"
		onclick="PopupCenter(this.href, '${product.name}',980, 390); return false;"> --%>
		<a href="${productUrl}" data-href="${productUrl}"  title="${product.name}" class="productMainLink">
			<div class="thumb">
				<product:productPrimaryImage product="${product}" format="product"/>
				<c:if test="${not empty product.potentialPromotions and not empty product.potentialPromotions[0].productBanner}">
					<img class="promo" src="${product.potentialPromotions[0].productBanner.url}" alt="${product.potentialPromotions[0].description}" title="${product.potentialPromotions[0].description}"/>
				</c:if>
			</div>

			


			<div class="details">
				<ycommerce:testId code="product_productName">${product.name}</ycommerce:testId>
			</div>
			
			<div class="priceContainer">
				<c:set var="buttonType">submit</c:set>
				<ycommerce:testId code="product_productPrice">
					<span class="price"><format:price priceData="${product.price}"/></span>
				</ycommerce:testId>
			</div>

			<c:choose>
				<c:when test="${product.stock.stockLevelStatus.code eq 'outOfStock' }">
					<c:set var="buttonType">button</c:set>
					<spring:theme code="text.addToCart.outOfStock" var="addToCartText"/>
					<span class='listProductLowStock listProductOutOfStock mlist-stock'>${addToCartText}</span>
				</c:when>
			</c:choose>
		</a>

		<div class="cart clearfix">
			<c:if test="${not empty product.averageRating}">
				<product:productStars rating="${product.averageRating}"/>
			</c:if>

			<c:set var="product" value="${product}" scope="request"/>
			<c:set var="addToCartText" value="${addToCartText}" scope="request"/>
			<c:set var="addToCartUrl" value="${addToCartUrl}" scope="request"/>
			<div id="actions-container-for-${component.uid}" class="">
				<action:actions element="div" parentComponent="${component}"/>
			</div>
		</div>

	</div>
	</div>
	</div>
</ycommerce:testId>
