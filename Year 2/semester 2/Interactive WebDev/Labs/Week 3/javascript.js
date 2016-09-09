function loading() {


    var number = document.getElementsByTagName('p').length;

    var newlink = document.createElement('a');
    newlink.setAttribute('href', '#top');
    txt = document.createTextNode('Back to Top');
    newlink.appendChild(txt);

    
    var i = 0;
    while (i<=number)
  {
   var parent = document.getElementsByTagName('p')[i];
  alert(i);
    parent.insertBefore(newlink,parent.childNodes[0]);
  i++;
  }
  
}