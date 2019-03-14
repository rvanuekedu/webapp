// stop erase animations from firing on load
document.addEventListener("DOMContentLoaded",function() {
    var q = document.querySelectorAll(".cb");
    for (var i in q) {
        if (+i < q.length) {
            q[i].addEventListener("click",function(){
                let c = this.classList,
                    p = "pristine";
                if (c.contains(p)) {
                    c.remove(p);
                }
            });
        }
    }
});