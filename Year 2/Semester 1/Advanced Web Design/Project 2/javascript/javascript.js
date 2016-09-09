
// Wasnt 100% what to do cause I wasnt allowed to change the html , so WHat i did was send the id of the menu1 information. Which then changes the id of all the menu items.

function bat(id) {
    if (id == "menu1"){
        document.getElementById("menu1").id         =     "menu1a";
              document.getElementById("menu2").id         =     "menu2a";
        document.getElementById("menu3").id         =     "menu3a";
        document.getElementById("menu4").id         =     "menu4a";
    }else{
        document.getElementById("menu1a").id        =    "menu1";
        document.getElementById("menu2a").id        =    "menu2";
        document.getElementById("menu3a").id        =    "menu3";
        document.getElementById("menu4a").id        =    "menu4";
    }
}
