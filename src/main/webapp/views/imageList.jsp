<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Listele</title>
<jsp:include page='/views/static_view/css.jsp' />
</head>
<body>
	<jsp:include page='/views/static_view/menu.jsp' />
	<div align="center" class="container">
		<h3>Resim Listele</h3>
		${template}
	</div>
	<jsp:include page='/views/static_view/footer.jsp' />
</body>
<jsp:include page='/views/static_view/js.jsp' />
<jsp:include page='/views/static_view/facebook_script.jsp' />
</html>