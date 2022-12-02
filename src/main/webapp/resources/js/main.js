
//send request if clicked on graph
canvas.onmousedown = function (e) {
    let pos = findPos(this);
    let graph_x = e.pageX - pos.x;
    let graph_y = e.pageY - pos.y;
    let r_val = document.getElementById('r-select').value
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
                value: rValue.toString()
            }
        ]
    )

    redrawGraph();
};