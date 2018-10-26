function showTime() {
    var d = new Date();
    var y = d.getFullYear();
    var month = d.getMonth()+1;
    var day = d.getDate();
    var h = d.getHours();
    if (h<10){
        h="0"+h;
    }
    var m = d.getMinutes();
    if (m<10){
        m="0"+m;
    }
    var s = d.getSeconds();
    if (s<10){
        s="0"+s;
    }
    document.getElementById("showTime").innerHTML="时间:"+y+"年"+month+"月"+day+"日   "+h+":"+m+":"+s;
}
var show = window.setInterval("showTime()",1000);
