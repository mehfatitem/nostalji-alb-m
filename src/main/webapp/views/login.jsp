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
		</form>
		<div id="result" align="center"></div>
		<jsp:include page='/views/static_view/loading.jsp' />
	</div>
	<jsp:include page='/views/static_view/footer.jsp' />
</body>
<jsp:include page='/views/static_view/js.jsp' />
<jsp:include page='/views/static_view/facebook_script.jsp' />
</html>