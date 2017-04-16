$(document).ready(function() {
	$("#logout").css("display", "none");
	$("#login").css("display", "inline");
	$("#welcome").css("display", "none");
	$("#settings").css("display", "none");
	$("#signup").css("display", "inline");
	$.ajax({
		url : "controlSession",
		type : "GET",
		success : function(data, textStatus, jqXHR) {
			if (data == "true") {
				$("#logout").css("display", "inline");
				$("#login").css("display", "none");
				$("#welcome").css("display", "inline");
				$("#settings").css("display", "inline");
				$("#signup").css("display", "none");
			} else {
				$("#logout").css("display", "none");
				$("#login").css("display", "inline");
				$("#welcome").css("display", "none");
				$("#settings").css("display", "none");
				$("#signup").css("display", "inline");
			}
		},
		error : function(jqXHR, textStatus, errorThrown) {

		}
	});
	var pageUrl = window.location.href.split("/");
	var linkId = "";
	console.log(pageUrl[4]);
	if (pageUrl[4] == '') {
		linkId = "homePage"
	} else {
		linkId = pageUrl[4];
	}
	$("#" + linkId).addClass("active");
	if (linkId == "imageUpload" || linkId == "imageList") {
		$("#imageOperation").addClass("active");
	}
	/*
	 * window.setInterval(function(){ $.post("login/fbLogin" ,
	 * function(response){ console.log("fblogin response"); }); },1000);
	 */

});
$("#show-password").mousedown(function() {
	$("#password").attr("type", "text");
	$(this).attr("title", "Şifreyi Gizle");
});

$("#show-password").mouseup(function() {
	$("#password").attr("type", "password");
	$(this).attr("title", "Şifreyi Göster");
});
