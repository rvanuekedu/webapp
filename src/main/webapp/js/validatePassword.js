function validatePassword () {
    var userPassword = document.getElementById('userPassword').innerHTML;
    var currentPassword = document.getElementById('currentPassword');
    var password1 = document.getElementById('password1');
    var password2 = document.getElementById('password2');

    var sha1Password = sha1(currentPassword.value);

    if (userPassword !== sha1Password) {
            swal({
                       title: "Текущий пароль не верен!",
                       icon: "error",
                     });
            return false;
        }

    if (password1.value !== password2.value) {
        swal({
                   title: "Пароли не совпадают!",
                   icon: "error",
                 });
        return false;
    }
}
