//sends request when button clicked
$('#input-form').on('submit', function(event) {
    event.preventDefault();
    if (!validateForm()) return;
    let data = $(this).serialize() + '&timezone=' + new Date().getTimezoneOffset();
    window.location.assign("control?" + data);
}).on('reset', function (){
    window.location.assign("control?reset=true")
});

function formatParams(params) {
    return "?" + Object
        .keys(params)
        .map(function (key) {
            return key + "=" + encodeURIComponent(params[key])
        })
        .join("&")
}
//send request if clicked on graph
$('#graph').on('click', function(e) {
    let pos = findPos(this);
    let graph_x = e.pageX - pos.x;
    let graph_y = e.pageY - pos.y;
    if (!validateR()) return;
    let r_val = document.getElementById('r-select').value
    let normalized_x = (graph_x-(x/2)) / (x/2) * r_val;
    let normalized_y = -(graph_y-(y/2)) / (y/2) * r_val;
    let data= {
        'xval':normalized_x,
        'yval':normalized_y,
        'rval':r_val,
        'timezone': new Date().getTimezoneOffset(),
    }
    window.location.assign("control" + formatParams(data));
});
