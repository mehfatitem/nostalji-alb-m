<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Resim Yükle</title>
<jsp:include page='/views/static_view/css.jsp' />
</head>
<body>
	<jsp:include page='/views/static_view/menu.jsp' />
	<div align="center" class="container">
		<h3>Resim Yükle</h3>
		<form id="uploadForm" action="http://localhost:8080/imageUploadList/upload/process"
			enctype="multipart/form-data" method="POST">
			<div class="form-group">
				<label for="imageDesc">Resim Açıklaması</label> <input type="text"
					class="form-control" id="imageDesc" name="imageDesc"
					placeholder="Resim Açıklaması giriniz...">
			</div>
			<div class="form-group">
				<label for="imageFile">Resim Dosyası Yükle</label> <input
					type="file" class="form-control" id="imageFile" name="imageFile">
			</div>
			<input type="submit" class="btn btn-info" id="submitUpload"
				name="submitUpload" value="Yükle" onclick="clickFunction();"></input>
		</form>
		<div class="alert alert-${type} alert-result" style="display : ${display};position: absolute;margin-left: -120px;display: inline;margin-top: 20px;max-width: 254px;" align="center">
			<strong>${title}</strong> ${messageText}
		</div>
	</div>
	<jsp:include page='/views/static_view/footer.jsp' />
</body>
<jsp:include page='/views/static_view/js.jsp' />
<jsp:include page='/views/static_view/facebook_script.jsp' />
</html>