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
      <h3>ENVIRONMENT-FRIENDLY SHOES MADE BY MOTHERS IN COMMUNITIES IN PHILIPPINES</h3>
     <!-- ><video>
      
      </video> --> 
       <img src="${commonResourcePath}/../ffac/images/img-infor.jpg" alt=""> 
      <p><span>1</span>. <b>Provides livelihood opportunities</b> to the mothers in communities, and further expands their hard and soft skills through trainings <br/><span>2</span>. <b>Reduces solid waste in the environment</b> by using upcycled scrap cloth in its products FADDA <br/><span>3</span>. <b>Promotes responsible fashion</b> upholding a statement that is both socially aware and environmentally concerned </p>
     
      <div class="clear"></div>
      </div>
      
      <div id="scrVision" class="helping">
      
<!-- <p id="back-top"> <a href="#top"><span><i class="fa fa-angle-up"></i> </span></a> </p> -->

      <h3>Helping the World Run Better by Improving People's Lives</h3>
       <img src="${commonResourcePath}/../ffac/images/sapcares.jpg" alt=""> 
      <p><b>Strategic Vision</b><br>
SAP's CSR vision is based on our vision to help the world run better by improving people's lives</p>
<p><b>CSR Focus</b><br>
Enhance education for underserved youth and propel emerging entrepreneurs to foster 
economic growth</p>

<p><b>CSR Focus</b><br>
Education is fundamental to people's ability to fulfill their potential and, in today's
interconnected world, technology skills provide entry to the modern economy. Emerging 
entrepreneurs can play a vital role in driving economic growth by addressing societal needs through successful ventures that create new jobs. For SAP, emerging entrepreneurs may become our future customers, and their ideas spark our own innovation. They also help create a stronger business environment that benefits us as well as others
</p>
     
      <div class="clear"></div>
      </div>
</c:if>
      
<footer class="footer-bottom"><div id="backTop">
     <a href="#top"><span><i class="fa fa-angle-up"></i> </span></a>
</div>
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
