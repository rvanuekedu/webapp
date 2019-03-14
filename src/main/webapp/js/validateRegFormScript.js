function validateForm () {
    var password1 = document.getElementById('password1');
    var password2 = document.getElementById('password2');
    if (password1.value !== password2.value) {
        swal({
                   title: "Пароли не совпадают!",
                   icon: "error",
                 });
        return false; 
    }
    var email = document.getElementById('email');
    var email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;
    if (!email_regexp.test(email.value)) {
        swal({
                   title: "Проверьте Email!",
                   icon: "error",
                 });
        return false;
    }
}
