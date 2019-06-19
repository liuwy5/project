jQuery(document).ready(function() {
    $.backstretch("/assets/img/login-bg.jpg");
    /*
        Form validation
    */
    $('.login-form input[type="text"], .login-form input[type="password"]').on('focus', function() {
    	$(this).removeClass('input-error');
		$('.login-alert').html("");
    });
    
    $('.login-form').on('submit', function(e) {
		var name = $('input[type="text"]').val();
		var pwd = $('input[type="password"]').val();

		if( name == "" && pwd == ""){
			$('input').addClass('input-error');
			$('.login-alert').html("请输入用户名和密码");
			return false;
		}else{
			$(this).removeClass('input-error');
		}

		if( name == "" ){
			$('input[type="text"]').addClass('input-error');
			$('.login-alert').html("请输入用户名");
			return false;
		}

		if( pwd == ""){
			$('input[type="password"]').addClass('input-error');
			$('.login-alert').html("请输入密码");
			return false;
		}
    });
});
