function init()
{
  var str = "Text Content in JavaScript";
  var num = 100;
  var bln = true;
  var fcn = init;
  var obj = document.getElementById("panel");

  obj.innerHTML =  str + " : " + typeof str;
  obj.innerHTML += "<br>" + num + " : " + typeof num;
  obj.innerHTML += "<br>" + bln + " : " + typeof bln;
  obj.innerHTML += "<br>init() : " + typeof fcn;
  obj.innerHTML += "<br>" + obj + " : " + typeof obj;
}

window.onload=init;