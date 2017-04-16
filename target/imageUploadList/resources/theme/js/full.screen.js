(function(){
	$('.listImageClass').on("click", function(){
		var clickId = $(this).attr("id");
		fullScreenFunc(this);
		$(this).css("width" , $(window).width());
		$(this).css("height" , $(window).height());
	});
	$(document).keyup(function(e){
		if (e.which == 27){
			$(".listImageClass").css("width" , "50");
			$(".listImageClass").css("height" , "50");
		}
	});
})();

function fullScreenFunc(obj){
	if (obj.requestFullscreen) {
		obj.requestFullscreen();
	}
	else if (obj.mozRequestFullScreen) {
		obj.mozRequestFullScreen();
	}
	else if (obj.webkitRequestFullScreen) {
		obj.webkitRequestFullScreen();
	}
}