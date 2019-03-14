window.onload = function(){

	function timer(){
		var minute = document.getElementById('minute').innerHTML;
		var second = document.getElementById('second').innerHTML;
		var end = false;

		if( second > 0 ) second--;
		else{
			second = 59;

			if( minute > 0 ) minute--;
			else{
				end = true;
			}
		}

		if(end){
		    document.getElementById('testPass').submit();
			clearInterval(intervalID);
		}else{
			document.getElementById('minute').innerHTML = minute;
			document.getElementById('second').innerHTML = second;
		}
	}
	swal({
           title: "В вопросе может быть несколько правильных ответов. Удачи!",
           icon: "info",
         })
    .then((value) => {
        window.intervalID = setInterval(timer, 1000);
    });

}