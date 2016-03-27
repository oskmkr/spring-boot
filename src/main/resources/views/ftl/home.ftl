<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

<#-- ===========================
THEME INFO
=========================== -->
    <meta name="description"
          content="A free Bootstrap powerd HTML template exclusively crafted for the super lazy designers like me who designed thousand of websites till today but never got a chance to build one himself.">
    <meta name="keywords"
          content="Free Portfolio Template, Free Template, Free Bootstrap Template, Dribbble Portfolio Template, Free HTML5 Template">
    <meta name="author" content="EvenFly Team">

<#-- DEVEOPER'S NOTE:
This is a free Bootstrap powered HTML template from EvenFly. Feel free to download, modify and use it for yourself or your clients as long there is no money involved.

Please don't try to sale it from anywhere; because I want it to be free, forever. If you sale it, That's me who deserves the money, not you :)
-->

<#-- ===========================
SITE TITLE
=========================== -->
    <title>꿀 소식</title><#-- This is what you see on your browser tab-->

<#-- ===========================
FAVICONS
=========================== -->
    <link rel="icon" href="img/favicon.png">
    <link rel="apple-touch-icon" sizes="144x144" href="img/apple-touch-icon-ipad-retina.png"/>
    <link rel="apple-touch-icon" sizes="114x114" href="img/apple-touch-icon-iphone-retina.png"/>
    <link rel="apple-touch-icon" sizes="72x72" href="img/apple-touch-icon-ipad.png"/>
    <link rel="apple-touch-icon" sizes="57x57" href="img/apple-touch-icon-iphone.png"/>

<#-- ===========================
STYLESHEETS
=========================== -->
<#--
<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
-->
    <link rel="stylesheet" href="/webjars/bootstrap/3.3.6/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/preloader.css">

    <link rel="stylesheet" href="css/responsive.css">
    <link rel="stylesheet" href="css/animate.css">
    <link rel="stylesheet" href="css/bootstrap-material-design.css">
    <link rel="stylesheet" href="css/style.css">

<#-- ===========================
ICONS:
=========================== -->
    <link rel="stylesheet" href="css/simple-line-icons.css">

<#-- ===========================
GOOGLE FONTS
=========================== -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Antic|Raleway:300">

<#-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<#-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

<#-- ===========================
 GOOGLE ANALYTICS (Optional)
 =========================== -->

<#--Replace this line with your analytics code-->

<#-- Analytics end-->

</head>
<body data-spy="scroll">
<#-- Preloader -->
<#--
<div id="preloader">
    <div id="status">
        <div class="loadicon icon-cloud-download wow tada infinite" data-wow-duration="8s"></div>
    </div>
</div>
-->
<header>
<#-- ===========================
HERO AREA
=========================== -->
    <div id="hero">
        <div class="container herocontent">
            <h2 class="wow fadeInUp" data-wow-duration="2s">꿀 소식</h2>
            <h4 class="wow fadeInDown" data-wow-duration="3s">꿀 같은 이벤트 소식을 한눈에</h4>
        </div>

    <#-- Featured image on the Hero area -->
        <img class=" wow bounceInUp" data-wow-duration="4s" src="img/honey_128.png" alt="Featured Work">
    </div><#--HERO AREA END-->

<#-- ===========================
 NAVBAR START
 =========================== -->
    <nav class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">

            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <a class="navbar-brand" href="#hero">
                <#-- Replace Drifolio Bootstrap with your Site Name and remove icon-grid to remove the placeholder icon -->
                    <span class="brandicon icon-grid"></span>
                    <span class="brandname">꿀 소식</span>
                </a>
            </div>

            <div class="collapse navbar-collapse">
                <ul class="nav navbar-nav navbar-right"><#--YOUR NAVIGATION ITEMS STRAT BELOW-->
                    <li><a href="/clien"><span class="btnicon icon-user"></span>클리앙</a></li>
                    <li><a href="/ppompu"><span class="btnicon icon-user"></span>뽐뿌</a></li>
                    <li><a href="/doctc"><span class="btnicon icon-cup"></span>독특닷컴</a></li>
                <#--
                <li><a href="#portfolio"><span class="btnicon icon-rocket"></span>Portfolio</a></li>
                <li><a href="#testimonials"><span class="btnicon icon-bubble"></span>Testimonials</a></li>
                -->
                <#--don't forget to replace my email address below with yours-->
                    <li><a href="mailto:oskmkr@naver.com"><span class="btnicon icon-envelope-open"></span>Contact</a>
                    </li>
                <#--
                <li class="active"><a href=""><span class="btnicon icon-cloud-download"></span>Download CV</a></li>
                -->
                </ul>

            </div><#--.nav-collapse -->
        </div>
    </nav><#--navbar end-->
</header><#--header end-->
<#-- SECTION SEPARETOR LINE -->
<#-- ===========================
TESTIMONIAL SECTION START
=========================== -->



<div id="topReadEvent" class="container">
    <div class="sectionhead wow bounceInUp" data-wow-duration="2s">
        <span class="bigicon icon-bubbles"></span>
        <h3>조회수 Top3 이벤트</h3>
        <h4>가장 조회가 많이 된 이벤트 소식</h4>
        <hr class="separetor">
    </div><#-- TESTIMONIAL SECTIONHEAD END -->

    <div class="row">

    <#list top3EventList as event>

        <div class="panel panel-default wow zoomIn" data-wow-duration="1s">
            <a class="black-link" href="${event.link}">
                <div class="panel-body latest-item">
                    <div class="clientsphoto">
                        <img src="img/honey_128.png" alt="">
                    </div>
                    <div class="quote">
                        <div>${event.category} ${event.title}</div>
                        <h5>${event.writeDate}</h5>
                    </div>
                </div>
            </a>
        </div>

    </#list>

    </div>
</div>

<div id="portfolio">
    <div class="sectionhead wow bounceInUp" data-wow-duration="2s">
        <span class="bigicon icon-rocket"></span>
        <h3>A few recent works</h3>
        <hr class="separetor">
    </div><#-- PORTFOLIO SECTION HEAD END -->

<#-- PORTFOLIO ITEMS START -->
    <div class="portfolioitems container">
        <ul>
            <div id="shotsByPlayerId">
                <img src="http://www.ppomppu.co.kr/images/main/201111/logo_small.gif">
            </div>
        </ul><#--REFER TO THE js/drifolio.js-->
    </div><#-- PORTFOLIO ITEMS END -->

<#--Replace the URL below with your dribbble profile URL-->
<#--
<a class="btn btn-default wow fadeInUp" href="#" role="button">
    <span class="btnicon icon-social-dribbble"></span>
    <span class="button_text">View all items</span>
</a>
-->
</div><#-- PORTFOLIO SECTION END -->

<#-- ===========================
FOOTER START
=========================== -->
<footer>
    <div class="container">
        <span class="bigicon icon-speedometer "></span>

        <div class="footerlinks"><#-- FOOTER LINKS START -->
            <ul>
                <li><a href="#hero">Home</a></li>
                <li><a href="#clien">클리앙</a></li>
                <li><a href="#ppompu">뽐뿌</a></li>
                <li><a href="#doctc">독특닷컴</a></li>
            <#--
            <li><a href="#portfolio">Portfolio</a></li>
            <li><a href="#testimonials">Testimonials</a></li>
            -->
            <#--replace the email address below with your email address-->
                <li><a href="mailto:oskmkr@naver.com">Contact</a></li>
            </ul>
        </div><#-- FOOTER LINKS END -->

        <div class="copyright"><#-- FOOTER COPYRIGHT START -->
            <p>Copyrightⓒ. 2016 oskmkr All Rights Reserved.</p>
        </div><#-- FOOTER COPYRIGHT END -->
    <#--
    <div class="footersocial wow fadeInUp" data-wow-duration="3s"><#-- FOOTER SOCIAL ICONS START --#>
        <ul>
            <li><a href="http://facebook.com/MamunSrizon"><span class="icon-social-facebook"></span></a></li>
            <li><a href="http://twitter.com/MamunSrizon"><span class="icon-social-twitter"></span></a></li>
            <li><a href="#"><span class="icon-social-youtube"></span></a></li>
            <li><a href="http://dribbble.com/srizon"><span class="icon-social-dribbble"></span></a></li>
            <li><a href="#"><span class="icon-social-tumblr"></span></a></li>
        </ul>
    </div><#-- FOOTER SOCIAL ICONS END --#>
    -->
    </div>
</footer><#-- FOOTER END -->

<#-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<#--
<script src="//code.jquery.com/jquery-latest.min.js"></script>
-->
<script src="/webjars/jquery/2.2.2/dist/jquery.js"></script>
<#-- Include all compiled plugins (below), or include individual files as needed -->
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>

<#--Other necessary scripts-->
<script src="js/jquery.nicescroll.min.js"></script>
<script src="js/jquery.jribbble-1.0.1.ugly.js"></script>
<script src="js/eventList.js"></script>
<script src="js/wow.min.js"></script>
<script>new WOW().init();</script>
</body>
</html>