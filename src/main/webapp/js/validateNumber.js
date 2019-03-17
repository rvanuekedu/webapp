function validateNumber () {
    var number = document.getElementById('number').value;
    var number_regexp = /^[0-9]+\b/;

    if (number.length == 0 || number == 0) {
        swal({
            title: "Введите время!",
            icon: "error",
        });
        return false;
    }

    if(number.length > 2) {
        swal({
            title: "Не более 2 цифр!",
            icon: "error",
        });
        return false;
    }


    if(!number_regexp.test(number)){
         swal({
            title: "Поле 'Время на прохождение' должно состоять только из цифр!",
            icon: "error",
         });
         return false;
    }
}
