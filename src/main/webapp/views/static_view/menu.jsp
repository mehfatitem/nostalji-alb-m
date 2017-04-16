<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="container-fluid">
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Menüyü Aç</span> <span class="icon-bar"></span>
					<span class="icon-bar"></span> <span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="javascript:void(0);"onclick="clickLink('');"><img id="logo" alt="ImageUploadList Logo" src="/imageUploadList/resources/images/image-upload-list-logo.png"></a>
			</div>
			<!-- Collect the nav links, forms, and other content for toggling -->
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li id="homePage" ><a href="javascript:void(0);"
						onclick="clickLink('');"><i class="fa fa-home fa-lg"></i>&nbsp;Anasayfa</a></li>
					<li id="imageOperation"><a href="javascript:void(0);" class="id=dropdown-toggle" id="menu1" data-toggle="dropdown"><i class="fa fa-picture-o fa-lg" aria-hidden="true"></i>&nbsp;Resim İşlemleri
	    				<span class="caret"></span></a>
						<ul class="dropdown-menu">
	    					<li id="imageUpload"><a href="javascript:void(0);"
							onclick="clickLink('imageUpload');"><i class="fa fa-upload fa-lg"
								aria-hidden="true"></i>&nbsp;Resim Yükle</a></li>
	   						 <li id="imageList"><a href="javascript:void(0);"
							onclick="clickLink('imageList');"><i
								class="fa fa-file-image-o fa-lg"></i>&nbsp;Resim Listele</a></li>
	  					</ul>
  					</li>
					<li id="signup"><a href="javascript:void(0);"
						onclick="clickLink('signup');"><i
							class="fa fa-plus-circle fa-lg"></i>&nbsp;Kayıt Ol</a></li>
					<li id="settings" style="display:none;"><a href="javascript:void(0);"
						onclick="clickLink('settings');"><i
							class="fa fa-cog fa-lg"></i>&nbsp;Ayarlar</a></li>
					<li id="welcome" style="display:none;"><a href="javascript:void(0);"
						onclick="clickLink('imageUpload');"><i
							class="fa fa-address-card-o fa-lg"></i>&nbsp;Hoş Geldin <font color="red" style='font-weight:bold;'>${sessionScope.sessContact}</font></a></li>
					<li id="logout" style="display:none;"><a href="javascript:void(0);"
						onclick="clickLink('logout');"><i
							class="fa fa-sign-out fa-lg"></i>&nbsp;Oturum Kapat</a></li>
					<li id="login"><a href="javascript:void(0);"
						onclick="clickLink('login');"><i
							class="fa fa-sign-in fa-lg"></i>&nbsp;Oturum Aç</a></li>		
				</ul>
			</div>
		</div>
	</nav>
</div>