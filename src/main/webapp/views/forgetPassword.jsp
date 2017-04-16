<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Şifremi Unuttum</title>
<jsp:include page='/views/static_view/css.jsp' />
</head>
<body>
	<jsp:include page='/views/static_view/menu.jsp' />
	<div align="center" class="container">
		<h3>Şifremi Unuttum</h3>
		<form id="forgetPasswordForm">
			<div class="form-group">
				<label for="username">Kullanıcı adı</label> <input type="text"
					class="form-control" id="username" name="username"
					placeholder="Kullanıcı adınızı giriniz...">
			</div>
			<input type="submit" class="btn btn-info" id="submitButton"
				name="submitButton" value="Gönder"></input>
		</form>
		<div id="result" align="center"></div>
		<jsp:include page='/views/static_view/loading.jsp' />
	</div>
	<jsp:include page='/views/static_view/footer.jsp' />
</body>
<jsp:include page='/views/static_view/js.jsp' />
</html>