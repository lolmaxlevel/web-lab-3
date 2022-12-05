
//send request if clicked on graph
canvas.onmousedown = function (e) {
    let pos = findPos(this);
    let graph_x = e.pageX - pos.x;
    let graph_y = e.pageY - pos.y;
    let r_val = document.getElementsByClassName('r-text-input').item(0).value;
    let normalized_x = (graph_x-(x/2)) / (x/2) * r_val;
    let normalized_y = -(graph_y-(y/2)) / (y/2) * r_val;
    addAttempt(
        [
            {
                name: "x",
                value: normalized_x.toString()
            },
            {
                name: "y",
                value: normalized_y.toString()
            },
            {
                name: "r",
                value: r_val.toString()
            }
        ]
    )
};
function findPos(obj) {
    let curleft = 0, curtop = 0;
    if (obj.offsetParent) {
        do {
            curleft += obj.offsetLeft;
            curtop += obj.offsetTop;
        } while (obj = obj.offsetParent);
        return { x: curleft, y: curtop };
    }
    return undefined;
}