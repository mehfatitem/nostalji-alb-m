function putZeroFirst(time) {
	time = String(time);
	if (time.length == 1) {
		time = "0" + time;
	}
	return time;
}

function edit(datas) {
	var imgId = datas.substring(0, datas.indexOf(','));
	var imgDesc = datas.replace(imgId + ",", "");
	swal/* .withForm */(
			{
				title : "Resim güncelle",
				text : "Resim Açıklaması:",
				type : "input",
				showCancelButton : true,
				confirmButtonText : "Güncelle",
				cancelButtonText : "İptal",
				closeOnConfirm : false,
				animation : "slide-from-top",
				inputPlaceholder : "Resim Açıklaması Giriniz...",
				inputValue : imgDesc.trim()
			/*
			 * formFields: [ { id: 'editText', placeholder:'Resim Açıklaması
			 * Giriniz...' , value : imgDesc.trim() }, ]
			 */
			},
			function(inputValue) {
				if (inputValue === false)
					return false;
				if (inputValue === "") {
					swal.showInputError("Bu alanı boş bırakmayın!");
				} else {
					$
							.ajax({
								type : 'POST',
								url : 'upload/edit',
								data : {
									imgId : imgId,
									imgDesc : inputValue
								},
								success : function(data) {
									$(".sweet-alert").append(data);
									if (!data
											.includes("  Bu açıklama isimli resim sistemde mevcuttur!"))
										$("#" + imgId).text(inputValue);
									var d = new Date();
									var curr_date = putZeroFirst(d.getDate());
									var curr_month = putZeroFirst(parseInt(d
											.getMonth()) + 1);
									var curr_year = d.getFullYear();
									var curr_hour = putZeroFirst(d.getHours());
									var curr_minutes = putZeroFirst(d
											.getMinutes());
									var curr_seconds = putZeroFirst(d
											.getSeconds());
									$("#" + imgId + "date").text(
											curr_date + "-" + curr_month + "-"
													+ curr_year + " "
													+ curr_hour + ":"
													+ curr_minutes + ":"
													+ curr_seconds);
									setTimeout(
											function() {
												console.log(data);
												$(".alert").remove();
												if (!data
														.includes("  Bu açıklama isimli resim sistemde mevcuttur!")) {
													window.location.reload();
												}
											}, 2000);
								}
							});
				}
			});
	$(".sweet-alert input").keydown(function(e) {
		if (e.which === 32 && e.target.selectionStart === 0) {
			return false;
		}
	});
}
function deleteImage(datas) {
	var imgId = datas.substring(0, datas.indexOf(','));
	var imgName = datas.replace(imgId + ",", "");
	swal(
			{
				title : "Uyarı !",
				text : "Bu kaydı silmek istediğinizden emin misiniz ?",
				type : "warning",
				showCancelButton : true,
				confirmButtonColor : "#DD6B55",
				confirmButtonText : "Evet",
				cancelButtonText : "Hayır",
				closeOnConfirm : true,
			},
			function(isConfirm) {
				if (isConfirm) {
					$
							.ajax({
								type : 'POST',
								url : 'upload/delete',
								data : {
									imgId : imgId,
									imgName : imgName
								},
								success : function(data) {
									if (data == "1") {
										var length = $("#imageTable tbody tr").length;
										if (length - 1 == 0) {
											$("#imageTable_wrapper").remove();
											$("#imageTable").remove();
											$("#operations").remove();
											$('body')
													.append(
															"<div class='alert alert-warning' align='center'><strong>Uyarı!</strong> Resim bulunamadı !</div>");

										}
										$("#tr" + imgId).remove();
										window.location.reload();
									}
								}
							});
				}
			});
}