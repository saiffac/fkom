<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="cms" uri="http://hybris.com/tld/cmstags"%>


<%-- 		<c:forEach items="${navigationNodes}" var="node">
				<c:if test="${node.visible}">
					<div class="links">
						<div class="title">${node.title}</div>
						<c:forEach items="${node.links}" step="${component.wrapAfter}" varStatus="i">
							<ul>
								<c:forEach items="${node.links}" var="childlink" begin="${i.index}" end="${i.index + component.wrapAfter - 1}">
									<cms:component component="${childlink}" evaluateRestriction="true" element="li" />
								</c:forEach>
							</ul>
						</c:forEach>
					</div>
				</c:if>
		</c:forEach>


<div class="copyright">${notice}</div> --%>
<c:if test="${ cmsPageRequestContextData.page.uid eq 'productGrid' }">
<div id="ourStory" class="we-infor">
      <h3>we are a small group with big heart</h3>
      <p><b>AO.CO</b> is a social enterprise based just outside of Siem Reap, Cambodia. It was founded in 2011 by Joseph and Anna Teo to provide a platform for them to extend their social enterprise work internationally. AO.CO currently provides direct work for 6 women and 1 man, operating out of their apartment as well as in a Pouk Village, 25 minutes away by scooter.  It also collaborates with other needy social enterprises in Cambodia, providing training in sewing, design, contract work and market access for their products. AO.CO has been and continues to enable these women to make a living to support their families and to make a better life for themselves. </p>
      <img src="${commonResourcePath}/../ffac/images/img-infor.jpg" alt=""> 
      <div class="clear"></div>
      </div>
      
      <div id="scrVision" class="helping">
<p id="back-top"> <a href="#top"><span><i class="fa fa-angle-up"></i> </span></a> </p>
      <h3 id="scrVision">Helping the World Run Better by Improving People's Lives</h3>
       <img src="${commonResourcePath}/../ffac/images/sapcares.jpg" alt=""> 
      <p><b>Strategic Vision</b><br>
SAP's CSR vision is based on our vision to help the world run better by improving people's lives</p>
<p><b>CSR Focus</b><br>
Enhance education for underserved youth and propel emerging entrepreneurs to foster 
economic growth</p>

<p><b>CSR Focus</b><br>
Education is fundamental to people’s ability to fulfill their potential and, in today's
interconnected world, technology skills provide entry to the modern economy. Emerging 
entrepreneurs can play a vital role in driving economic growth by addressing societal needs through successful ventures that create new jobs. For SAP, emerging entrepreneurs may become our future customers, and their ideas spark our own innovation. They also help create a stronger business environment that benefits us as well as others
</p>
     
      <div class="clear"></div>
      </div>
</c:if>
      
<footer class="footer-bottom">
	<div class="main">
		<div class="row">
			<h1>THANK YOU FOR HELPING !</h1>
			<a href="#headAnchor">still wanna help ?</a>
			<div class="logo-company">
				<a href="http://www.sap.com"><img src="${commonResourcePath}/../ffac/images/logo-sharp.png" alt=""></a>
				<a href="http://www.sai-it.com"><img src="${commonResourcePath}/../ffac/images/logo-sai.png" alt=""></a>
				<a href="http://www.hybris.com"><img src="${commonResourcePath}/../ffac/images/logo-hybris.png" alt=""></a>
			</div>
		</div>
	</div>
</footer>
