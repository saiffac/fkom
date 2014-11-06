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
<c:if test="${ cmsPageRequestContextData.page.uid eq 'productGrid'  || cmsPageRequestContextData.page.uid eq 'category' }">
  <div class="term">
    	<h3><a href="">Terms and conditions</a></h3>
        <p>Orders are not exchangeable nor refundable once payment has been processed.Please direct order queries to <a href="#">habifootwear@gmail.com</a><br>
        Your orders will be shipped to Singapore, for your pick up at the FKOM venue. Orders not collected by 5pm, 13 January, will be given to local charity organization</p>
    </div>
<div id="ourStory" class="we-infor">
      <h3>Habi Footwear - Creating jobs with dignity, with environmentally-friendly shoes </h3>
      
      
      <iframe src="//www.youtube.com/embed/naqvsbbxeYg" frameborder="0" allowfullscreen></iframe>
      <%-- <img src="${commonResourcePath}/../ffac/images/img-infor.jpg" alt=""> --%>
      
      
      <p>Habi Footwear is a Philippines-based social business which promotes responsible fashion, Pinoy pride and social involvement through 100% Filipino-made, environment-friendly footwear. It works in partnership with two marginalized communities in the Philippines, namely Saint Luigi Oriones Creations of Payatas and Bistekville of Fairview</p>
      <p>Managed by three social entrepreneurs, Janine Chiong, Paola Savillo, and Bernadee Uy, Habi Footwear aims for both business growth and making positive social impact through three ways:</p>
      <div class="clear"></div>
      </div>
      
 <div class="provide">
  <div class="box-provie">
  <a href="#"><img src="${commonResourcePath}/../ffac/images/provide1.png" alt=""/></a>
  	<p>Provide livelihood opportunities to mothers in the communities, and further expand their hard and soft skills through trainings.</p>
  </div>
   <div class="box-provie">
  <a href="#"><img src="${commonResourcePath}/../ffac/images/provide2.png" alt=""/></a>
  	<p>Help reduce solid waste in the environment by using upcycled scrap cloth in its products.</p>
  </div>
   <div class="box-provie">
  <a href="#"><img src="${commonResourcePath}/../ffac/images/provide3.png" alt=""/></a>
  	<p>Promote a change in lifestyle through responsible fashion, upholding a statement that is both socially aware and environmentally concerned.</p>
  </div>
  
  </div>
      
      <div id="scrVision" class="helping">
      
<!-- <p id="back-top"> <a href="#top"><span><i class="fa fa-angle-up"></i> </span></a> </p> -->

      <h3>Helping the World Run Better by Improving People's Lives</h3>
       <img src="${commonResourcePath}/../ffac/images/sapcares.jpg" alt=""> 
      <p>SAP's vision is to help the world run better and improve people's lives.
      Nowhere does this vision come to life more than in our social investments.
      When a child is able to go to school and explore the world through technology, new possibilities are created for change.
      When that child grows up to have the confidence to create a different future, the possibilities multiply.
      And when that future includes starting up a new business, spurring job creation and economic growth, the cycle begins all over again.</p>
 <p>We aim to be as strategic and innovative in addressing social challenges as we are in the rest of our business. For this reason, we call on our talent, technology, and capital to multiply our impact. Specifically, we focus on:</p>
<p><b><span>.</span>Engaging our talent</b> - We tap the skills of our employees to drive new approaches.<br/>
<b><span>.</span>Leveraging our technology</b> - We provide software tools to nonprofits to accelerate their ability to serve their mission.<br/>
<b><span>.</span>Investing our capital</b> - We invest in innovative strategies for creating economic opportunity and long-term change.<br/>


     
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
