function validateEmail () {
    var userEmail = document.getElementById('email').innerHTML;
    var userPassword = document.getElementById('userPassword').innerHTML;
    var currentEmail = document.getElementById('currentEmail');
    var newEmail = document.getElementById('newEmail');
    var password = document.getElementById('password');
    var sha1Password = sha1(password.value);
    var email_regexp = /[0-9a-zа-я_A-ZА-Я]+@[0-9a-zа-я_A-ZА-Я^.]+\.[a-zа-яА-ЯA-Z]{2,4}/i;

    if (!email_regexp.test(newEmail.value)) {
        swal({
                   title: "Новый email не валиден!",
                   icon: "error",
                 });
        return false;
    }

    if (userPassword !== sha1Password) {
            swal({
                       title: "Пароль не верен!",
                       icon: "error",
                     });
            return false;
        }

    if (userEmail !== currentEmail.value) {
        swal({
                   title: "Текущий email не верен!",
                   icon: "error",
                 });
        return false;
    }
}
