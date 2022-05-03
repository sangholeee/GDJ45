$(document).ready(function(){

    // submit 이벤트 처리하기
    var loginForm = $('#login_form');
    loginForm.on('submit', function(ev){
        var now = new Date();
                if(now.getHours() >= 23 && now.getHours() <= 24) {
                    alert('23:00 ~ 24:00 사이 시스템 점검으로 로그인이 제한됩니다.');
                    ev.preventDefault();
                    return false;
                }


                if($('#user_id').val() == '' || $('#user_pw').val() == ''){
                    alert('아이디와 비밀번호를 모두 입력하세요.');
                    ev.preventDefault();
                    return false;
                }

        return true;

    })

    $('#user_id').on('keyup', function(event){
        var idCheckResult = $('#id_check');
        if( $(this).val().length > 6 ) {
            alert('ID는 6글자 이하입니다.');
        }
    });

});