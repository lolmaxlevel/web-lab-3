const canvas = document.getElementById("graph"),
    ctx = canvas.getContext('2d');

let x = canvas.width;
let y = canvas.height;
ctx.lineWidth = 1.5;
ctx.strokeStyle = 'black';

let table = [];

function drawAxes() {
    ctx.save();
    drawLineFromTo(x/2,0,x/2,y);
    drawLineFromTo(0,y/2,x,y/2);
    ctx.lineWidth = ctx.lineWidth-0.5;
    //arrows
    drawLineFromTo(x/2, 0, x/2-5, 10);
    drawLineFromTo(x/2, 0, x/2+5, 10);
    drawLineFromTo(x, y/2, x-10, y/2+5);
    drawLineFromTo(x, y/2, x-10, y/2-5);
    //-R/2 lines
    drawLineFromTo(x/4, y/2, x/4, y/2-5);
    drawLineFromTo(x/4, y/2, x/4, y/2+5);
    drawLineFromTo(x/2, 3*(y/4), x/2+5, 3*(y/4));
    drawLineFromTo(x/2, 3*(y/4), x/2-5, 3*(y/4));
    //R/2 lines
    drawLineFromTo(3*(x/4), y/2, 3*(x/4), y/2-5);
    drawLineFromTo(3*(x/4), y/2, 3*(x/4), y/2+5);
    drawLineFromTo(x/2, y/4, x/2+5, y/4);
    drawLineFromTo(x/2, y/4, x/2-5, y/4);
    ctx.restore();

}
function drawTriangle(){
    ctx.save();
    ctx.lineWidth = ctx.lineWidth-0.5;
    let triangle = new Path2D();
    triangle.moveTo(3*x/4, y/2);
    triangle.lineTo(x/2, 3*y/4);
    triangle.lineTo(x/2, y/2);
    triangle.lineTo(x/4, y/2);
    triangle.closePath();
    //ctx.fillStyle = `rgb(51,153,255)`
    ctx.fillStyle = `#81888c`
    ctx.fill(triangle,"evenodd");
    ctx.restore();
}
function drawCircle(){
    ctx.save();
    ctx.lineWidth = ctx.lineWidth-0.5;
    let circle = new Path2D();

    circle.arc(x/2, y/2, x/4, Math.PI + (Math.PI * 3) / 2,  + (Math.PI * 2) / 2);
    circle.lineTo(x/2, y/2);
    //ctx.fillStyle = `rgb(51,153,255)`;
    ctx.fillStyle = `#81888c`
    ctx.fill(circle,"evenodd");
    ctx.restore();
}
function drawRectangle(){
    ctx.save()
    //ctx.fillStyle = `rgb(51,153,255)`;
    ctx.fillStyle = `#81888c`
    ctx.fillRect(x/2,y/2,x/4,-y/2);
    ctx.restore();
}
function drawText(r){
    ctx.save();
    ctx.font = '15px monospace';
    ctx.fillStyle = `black`;
    if (r<=0){
        r = "R/2";
    }
    else r = (r/2).toString();
    ctx.fillText(r,x/2 + 10, y/4+5)
    ctx.fillText(r,3*(x/4)-8, y/2-8)
    ctx.fillText('-'+ r,x/2 + 10, 3*(y/4)+5)
    ctx.fillText('-'+ r,(x/4)-8, y/2-8)
    ctx.restore();
}
function redrawGraph(){
    let r = $('.r-text-input').val();
    console.log("redrawing graph with r = " + r);
    ctx.clearRect(0, 0, x, y);
    ctx.fillStyle = `black`
    drawTriangle();
    drawCircle();
    drawRectangle();
    drawAxes();
    drawText(r);
    getDots()
    drawDots();
}
function drawDots(){
    if (table.length!==0) {
        table.forEach((dot => {
            drawDotOnGraph(dot.x / dot.r * (x / 2) + (x / 2), -dot.y / dot.r * (y / 2) + (y / 2), dot.hit)
        }))
    }
}

function drawLineFromTo(x1,y1,x2,y2){
    ctx.beginPath();
    ctx.moveTo(x1,y1);
    ctx.lineTo(x2,y2);
    ctx.stroke();
}
function drawDotOnGraph(x, y, isHit){
    ctx.beginPath();
    ctx.arc(x, y, 3, 0, 2 * Math.PI, false);
    ctx.fillStyle = isHit? 'green': 'red';
    ctx.fill();
    ctx.lineWidth = 1.5;
    ctx.strokeStyle = isHit? '#003300': '#450100';
    ctx.stroke();
    ctx.strokeStyle = 'black'
    ctx.lineWidth = 1.5;
}

//draw stored points
$(document).ready(function(){
    redrawGraph();
});

function getDots(){
    table = [];
    $("#result-table>tbody tr").each(function (i) {
        const self = $(this);
        const x = self.find(".x").text().trim();
        const y = self.find(".y").text().trim();
        const r = self.find(".r").text().trim();
        const hit = self.find(".hit").text().trim() === "true";
        const raw = {x: x, y: y, r: r, hit: hit};
        table[i]=raw;
    });
}
//redraw graph if R value changed
$('.r-text-input').on('change', function() {
    console.log("R value changed");
    redrawGraph();
});

//reload page if accessed by back/forward buttons
if(!!window.performance && window.performance.getEntriesByType('navigation')[0].type === "back_forward")
{
    window.location.reload();
}