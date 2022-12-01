function doDate()
{
    let now = new Date();
    let offset = new Date().getTimezoneOffset();
    now.setHours(now.getUTCHours());
    now.setHours(now.getHours() - offset/60);
    document.getElementById("Date").innerHTML = "Time: " + now.toLocaleTimeString();
}

// do something when the DOM is ready
$(document).ready(function () {
    doDate();
    setInterval(doDate, 8000);
});