/* HTML5 in easy steps:example 59.1 */

function choose()
{
  document.getElementById("pepperoni").checked=true;
}

function init()
{
    document.getElementById("btn").onclick=choose;
}

onload=init;