<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Oturum Aç</title>
<jsp:include page='/views/static_view/css.jsp' />
</head>
<body>
	<jsp:include page='/views/static_view/menu.jsp' />
	<div align="center" class="container">
		<br>
		<h3>Oturum Aç</h3>
		<form id="loginForm" action="login/login.htm" method="POST">
			<div class="form-group">
				<label for="username">Kullanıcı Adı</label> <input type="text"
					class="form-control" id="username" name="username"
					placeholder="Kullanıcı adı giriniz...">
			</div>
			<div class="form-group">
				<label for="password">Şifre</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="Şifre giriniz...">
			</div>
			<div class="form-group">
				<a id="show-password" title="Şifreyi Göster"
					href="javascript:void(0)"><img
					style="width: 64px; height: 64px;"
					src="resources/images/show_password.png" /></a>
			</div>

			<input type="submit" class="btn btn-info" id="submitLogin"
				name="submitLogin" value="Oturum Aç"></input> <br> <br> <a
				id="signup" href='http://localhost:8080/imageUploadList/signup'
				class="">Kayıt Ol</a><br> <br> <a id="forgetPassword"
				href='http://localhost:8080/imageUploadList/forgetPassword' class="">Şifremi
				Unuttum</a> <br> <br> <br> <br>
			<div class="fb-login-button"
				data-max-rows="2"
				data-size="large" data-show-faces="true"
				data-auto-logout-link="true"></div>
		</form>
		<div id="result" align="center"></div>
		<jsp:include page='/views/static_view/loading.jsp' />
	</div>
	<jsp:include page='/views/static_view/footer.jsp' />
</body>
<jsp:include page='/views/static_view/js.jsp' />
<script>
	// This is called with the results from from FB.getLoginStatus().
	function statusChangeCallback(response) {
		console.log('statusChangeCallback');
		console.log(response);
		// The response object is returned with a status field that lets the
		// app know the current login status of the person.
		// Full docs on the response object can be found in the documentation
		// for FB.getLoginStatus().
		if (response.status === 'connected') {
			// Logged into your app and Facebook.
			testAPI();
		} else {
			// The person is not logged into your app or we are unable to tell.
			document.getElementById('status').innerHTML = 'Please log '
					+ 'into this app.';
		}
	}

	// This function is called when someone finishes with the Login
	// Button.  See the onlogin handler attached to it in the sample
	// code below.
	function checkLoginState() {
		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	}

	window.fbAsyncInit = function() {
		FB.init({
			appId : '923735267730138',
			cookie : true, // enable cookies to allow the server to access 
			// the session
			xfbml : true, // parse social plugins on this page
			version : 'v2.8' // use graph api version 2.8
		});

		// Now that we've initialized the JavaScript SDK, we call 
		// FB.getLoginStatus().  This function gets the state of the
		// person visiting this page and can return one of three states to
		// the callback you provide.  They can be:
		//
		// 1. Logged into your app ('connected')
		// 2. Logged into Facebook, but not your app ('not_authorized')
		// 3. Not logged into Facebook and can't tell if they are logged into
		//    your app or not.
		//
		// These three cases are handled in the callback function.

		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});

	};

	// Load the SDK asynchronously
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "//connect.facebook.net/en_US/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));

	// Here we run a very simple test of the Graph API after login is
	// successful.  See statusChangeCallback() for when this call is made.
	function testAPI() {
		FB.login(function(response) {
			var access_token = FB.getAuthResponse()['accessToken'];
			FB.api('/me', {
				fields : 'email,name'
			}, function(response) {
				$.ajax({
					type : 'POST',
					url : 'login/fbLogin',
					data : {
						access_token : access_token,
						contact : response.name , 
						email : response.email
					},
					success : function(data) {

					}
				});
			});
		});
	}
	$("._4z_b").click(function(){
		window.location.reload();
	});
	$.get( "controlSession", function( data ) {
		 console.log("get operation is success");
	});
</script>
</html>