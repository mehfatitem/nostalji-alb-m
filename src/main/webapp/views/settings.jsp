<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ayarlar</title>
<jsp:include page='/views/static_view/css.jsp' />
</head>
<body>
	<jsp:include page='/views/static_view/menu.jsp' />
	<div align="center" class="container">
		<h3>Ayarlar</h3>
		<form id="settingsForm" action="settings/settings.htm" method="POST">
			<div class="form-group">
				<label for="contact">Ad Soyad</label> <input type="text"
					class="form-control" id="contact" name="contact" value="${contact}" placeholder="Ad soyad giriniz...">
			</div>
			<div class="form-group">
				<label for="username">Kullanıcı Adı</label> <input type="text"
					class="form-control" id="username" name="username" value="${username}"
					readonly>
			</div>
			<div class="form-group">
				<label for="email">E-posta</label> <input type="text"
					class="form-control" id="email" name="email"
					placeholder="E-posta adresinizi giriniz..." value="${email}">
			</div>
			<div class="form-group">
				<label for="password">Şifre</label> <input type="password"
					class="form-control" id="password" name="password"
					placeholder="Şifre giriniz...">
			</div>
			<div class="form-group">
				<label for="password">Şifre Tekrarı</label> <input type="password"
					class="form-control" id="repassword" name="repassword"
					placeholder="Şifreyi yeniden giriniz...">
			</div>
			<input type="submit" class="btn btn-info" id="submitSignup"
				name="submitSettings" value="Kaydet"></input>
		</form>
		<div id="result" align="center"></div>
		<jsp:include page='/views/static_view/loading.jsp' />
	</div>
	<jsp:include page='/views/static_view/footer.jsp' />
</body>
<jsp:include page='/views/static_view/js.jsp' />
<jsp:include page='/views/static_view/facebook_script.jsp' />
</html>