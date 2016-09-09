/* HTML5 in easy steps:example 23.2 */

function toggle()
{
  var tag = document.getElementById("chart");
  var hid = (tag.style.visibility !== "visible");
  tag.style.visibility = (hid) ? "visible" : "hidden" ;
  tag.style.height = (hid) ? "auto" : "0px";
}