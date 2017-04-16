function disableSpace(selector){
	$(selector).keydown(function(e){
		if (e.which === 32 &&  e.target.selectionStart === 0) {
			return false;
		}  
	});
}
$().ready(function() {
	disableSpaceSelector = ["#imageDesc" , "#username" , "#password" , "#repassword" , "#contact" , "#email"];
	for(var i in disableSpaceSelector){
		disableSpace(disableSpaceSelector[i]);
	}
	$.validator.addMethod('filesize', function (value, element, param) {
	    return this.optional(element) || (element.files[0].size <= param)
	}, 'File size must be less than {0}');
	$("#uploadForm").validate({
		rules: {
			imageDesc : {
				required : true
			},
			imageFile : {
				required: true,
				extension: "jpg,png,jpeg,gif",
				filesize: 52428800  
			}
		},
		messages : {
			imageDesc : {	
				required : "Lütfen resim açıklama metnini boş bırakmayınız!",
				
			},
			imageFile : {
				required : "Lütfen resim dosyası yükleyin!" , 
				extension : "Kabul edilen dosya tipleri png , jpeg ve gif uzantılıdır",
				filesize :  "Dosya boyutu maksimum 50 mb olmalıdır!"
			}
		}
	});
	$("#loginForm").validate({
		rules: {
			username : {
				required : true
			},
			password : {
				required: true,
			}
		},
		messages : {
			username : {	
				required : "Lütfen kullanıcı adını boş bırakmayınız!",
				
			},
			password : {
				required : "Lütfen şifreyi boş bırakmayınız!" , 
			}
		},
		submitHandler: function(form) {
			var username = $("#username").val().trim();
			var password = $("#password").val().trim();
			$("#ajax-loading").css("display" , "inline");
			$("body").addClass("make_darkness");
			$("#result").html("");
			$.ajax({
				type : 'POST',
				url : 'login/login',
				data : {
					username : username,
					password : password
				},
				success : function(data) {
					if(data == " "){
						location.href= "/imageUploadList/"
					}else{
						$("#result").html(data);
					}
					$("#result").html(data);
					$("#ajax-loading").css("display" , "none");
					$("body").removeClass("make_darkness");
					$("body, html").animate({ 
					      scrollTop: $("#result").offset().top 
					}, 500);
				}
			});
		}
	});
	$("#signupForm").validate({
		submitHandler: function(form) {
			$("#result").html("");
			var email = $("#email").val().trim();
			$("#ajax-loading").css("display" , "inline");
			$("body").addClass("make_darkness");
			$.ajax({
				type : 'POST',
				url : 'signup/signup',
				data : $("#signupForm").serialize(),
				success : function(data) {
					$("#result").html(data);
					$("#ajax-loading").css("display" , "none");
					$("body").removeClass("make_darkness");
					$("body, html").animate({ 
					      scrollTop: $("#result").offset().top 
					}, 500);
				}
			});
		},
		rules: {
			contact : {
				required : true
			},
			username : {	
				required : true,
				minlength : 5,
				maxlength : 30,
				remote: "validation/username"
				
			},
			email : {
				required : true,
				email:true
			},
			password : {
				required: true,
				minlength : 5,
				maxlength : 30,
				remote: "validation/password.htm"
			},
			repassword : {
				required : true,
				minlength : 5,
				equalTo : "#password"
			}
		},
		messages : {
			contact : {	
				required : "Lütfen adınızı ve soyadınızı giriniz!",
				
			},
			username : {
				required : "Kullanıcı adını boş bırakmayınız!",
				minlength : "Kullanıcı adınız minimum 5 karakterli olmalıdır!",
				maxlength : "Kullanıcı adınız maksimum 30 karakterli olmalıdır!",
				remote : "Kullanıcı adınızın Türkçe karakter ve özel karakter içermemesine dikkat ediniz!"
			},
			password : {
				required : "Şifrenizi boş bırakmayınız!",
				minlength : "Şifreniz minimum 5 karakterli olmalıdır!",
				maxlength : "Şifreniz maksimum 30 karakterli olmalıdır!",
				remote : "Şifreniz Türkçe karakter içermemeli , bir büyük bir de küçük harf ve rakamlar içermelidir!"
			},
			repassword : {
				required : "Şifrenizi boş bırakmayınız!",
				minlength : "Şifreniz minimum 5 karakterli olmalıdır!",
				equalTo : "Girdiğiniz şifre ilk girdiğiniz şifre eşleşmiyor!"
			},
			email : {
				required : "E-postanızı boş bırakmayınız!" , 
				email : "Geçersiz e-posta!"
			}
		}
	});
	$("#settingsForm").validate({
		submitHandler: function(form) {
			$("#result").html("");
			var email = $("#email").val().trim();
			$("#ajax-loading").css("display" , "inline");
			$("body").addClass("make_darkness");
			$.ajax({
				type : 'POST',
				url : 'settings/settings',
				data : $("#settingsForm").serialize(),
				success : function(data) {
					$("#result").html(data);
					$("#ajax-loading").css("display" , "none");
					$("body").removeClass("make_darkness");
					$("body, html").animate({ 
					      scrollTop: $("#result").offset().top 
					}, 500);
				}
			});
		},
		rules: {
			contact : {
				required : true
			},
			username : {	
				required : true,
				minlength : 5,
				maxlength : 30,
				remote: "validation/username"
				
			},
			email : {
				required : true,
				email:true
			},
			password : {
				required: true,
				minlength : 5,
				maxlength : 30,
				remote: "validation/password.htm"
			},
			repassword : {
				required : true,
				minlength : 5,
				equalTo : "#password"
			}
		},
		messages : {
			contact : {	
				required : "Lütfen adınızı ve soyadınızı giriniz!",
				
			},
			username : {
				required : "Kullanıcı adını boş bırakmayınız!",
				minlength : "Kullanıcı adınız minimum 5 karakterli olmalıdır!",
				maxlength : "Kullanıcı adınız maksimum 30 karakterli olmalıdır!",
				remote : "Kullanıcı adınızın Türkçe karakter ve özel karakter içermemesine dikkat ediniz!"
			},
			password : {
				required : "Şifrenizi boş bırakmayınız!",
				minlength : "Şifreniz minimum 5 karakterli olmalıdır!",
				maxlength : "Şifreniz maksimum 30 karakterli olmalıdır!",
				remote : "Şifreniz Türkçe karakter içermemeli , bir büyük bir de küçük harf ve rakamlar içermelidir!"
			},
			repassword : {
				required : "Şifrenizi boş bırakmayınız!",
				minlength : "Şifreniz minimum 5 karakterli olmalıdır!",
				equalTo : "Girdiğiniz şifre ilk girdiğiniz şifre eşleşmiyor!"
			},
			email : {
				required : "E-postanızı boş bırakmayınız!" , 
				email : "Geçersiz e-posta!"
			}
		}
	});
	$("#forgetPasswordForm").validate({
		rules: {
			username : {
				required : true
			}
		},
		messages : {
			username : {
				required : "Kullanıcı adınızı boş bırakmayınız!" , 
			}
		},
		submitHandler: function(form) {
			$("#result").html("");
			var username = $("#username").val().trim();
			$("#ajax-loading").css("display" , "inline");
			$("body").addClass("make_darkness");
			$.ajax({
				type : 'POST',
				url : 'forgetPassword',
				data : {
					username : username
				},
				success : function(data) {
					$("#result").html(data);
					$("#ajax-loading").css("display" , "none");
					$("body").removeClass("make_darkness");
					$("body, html").animate({ 
					      scrollTop: $("#result").offset().top 
					}, 500);
				}
			});
		}
	});
});