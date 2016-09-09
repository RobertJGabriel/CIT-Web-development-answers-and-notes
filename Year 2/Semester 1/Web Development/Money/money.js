function input() {
var intRegex = /^\d+$/;
	var coinAmount  = [0,0,0,0,0,0,0,0] ;
	
	var coinVaule = ["2 Euro ","1 Euro ","50 cent ","20 cent","10 cent ","5 cent ","2 cent ","1 cent "];
	do {
		var person = prompt("Please The coins in cents : ");
	} while (!intRegex.test(person));
	document.getElementById("header").innerHTML= "Amount of change for :"+ " &euro;" + person/100 ;
	
	while (person >= 200) {
		person = person - 200;
		coinAmount[0] = coinAmount[0] + 1;
	}
	while (person >= 100) {
		person = person - 100;
		coinAmount[1] = coinAmount[1] + 1;
	}
	while (person >= 50) {
		person = person - 50;
		coinAmount[2] = coinAmount[2] + 1;
	}
	while (person >= 20) {
		person = person - 20;
		coinAmount[3] = coinAmount[3] + 1;
	}
	while (person >= 10) {
		person = person - 10;
		coinAmount[4] = coinAmount[4] + 1;
	}
	while (person >= 5) {
		person = person - 5;
		coinAmount[5] = coinAmount[5] + 1;
	}
	while (person >= 2) {
		person = person - 2;
		coinAmount[6] = coinAmount[6] + 1;
	}
	while (person >= 1) {
		person = person - 1;
		coinAmount[7] = coinAmount[7] + 1;
	}

	for (var i = 0; i < coinAmount.length; i++) {

	var text = "text"+i;
		document.getElementById(text).innerHTML= coinVaule[i]+":  " +coinAmount[i] + "<br>";
	}
	
}
