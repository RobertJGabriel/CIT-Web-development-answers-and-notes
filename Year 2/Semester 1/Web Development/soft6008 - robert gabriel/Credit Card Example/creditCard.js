/*
   _____              _ _ _    _____              _  __      __   _ _     _       _   _             
  / ____|            | (_) |  / ____|            | | \ \    / /  | (_)   | |     | | (_)            
 | |     _ __ ___  __| |_| |_| |     __ _ _ __ __| |  \ \  / /_ _| |_  __| | __ _| |_ _  ___  _ __  
 | |    | '__/ _ \/ _` | | __| |    / _` | '__/ _` |   \ \/ / _` | | |/ _` |/ _` | __| |/ _ \| '_ \ 
 | |____| | |  __/ (_| | | |_| |___| (_| | | | (_| |    \  / (_| | | | (_| | (_| | |_| | (_) | | | |
  \_____|_|  \___|\__,_|_|\__|\_____\__,_|_|  \__,_|     \/ \__,_|_|_|\__,_|\__,_|\__|_|\___/|_| |_|
 |  _ \                                                                                             
 | |_) |_   _                                                                                       
 |  _ <| | | |                                                                                      
 | |_) | |_| |                                                                                      
 |____/ \__, |_               _      _____       _          _      _                                
 |  __ \ __/ | |             | |    / ____|     | |        (_)    | |                               
 | |__) |___/| |__   ___ _ __| |_  | |  __  __ _| |__  _ __ _  ___| |                               
 |  _  // _ \| '_ \ / _ \ '__| __| | | |_ |/ _` | '_ \| '__| |/ _ \ |                               
 | | \ \ (_) | |_) |  __/ |  | |_  | |__| | (_| | |_) | |  | |  __/ |                               
 |_|  \_\___/|_.__/ \___|_|   \__|  \_____|\__,_|_.__/|_|  |_|\___|_|                                                                                                                     
                                                                                                                                                                         
*/

function main() { //Start of Function , Which is to tell which Card it is .
	
    var creditCard = document.getElementById("creditcard").value; // Gets Credit Card input.
	var length = creditCard.length; // Gets length of credit card number.
	var numberOne = creditCard.charAt(0); //Gets first digit in CreditCard.
    
	if ((length <= 16 && length >= 13) && (numberOne == 4)) {
		alert("Card is Visa"); //Is Visa Card
		math(creditCard,length);
	} else if ((length == 15) && (numberOne == 3)) {
		alert("Card is American Express"); //Is Amercian Express
		math(creditCard,length);
	} else if ((length == 16) && (numberOne == 5)) {
		alert("Card is MasterCard"); // Is Master Card
		math(creditCard,length);
	} else if ((length == 16) && (numberOne == 6)) {
		alert("Card is Discover Card"); // Is Discover Card
		math(creditCard,length);
	} else {
		alert("I cant let you do that Dave :-O , Card is wrong");
	}
} //End of Function 



function math(creditCard,length) {
	var cardNumber = [0, 0]; // Arraylist for every second number and everyother number.
	
	for (var i = length - 2; i >= 0; i -= 2) { //Start of For Loop, that adds every senond number and muilpys by 2.
		var number = parseInt(creditCard.charAt(i),10); //Changes string to int.
		var x = number * 2; //Doubles Numbers by 2
		if (x > 10) {//Start of if Statement
			x = split(x); //Calls Spilt Function
		}//End of if Statment
		cardNumber[0] = x + cardNumber[0];
	} //End of For loop
     
	for (var i = length - 1; i >= 0; i -= 2) { //Start of For Loop that adds every first number to array
		var number = parseInt(creditCard.charAt(i),10);
		cardNumber[1] = number + cardNumber[1];
	} //End of For loop
   
   var finished = parseInt(cardNumber[1] + cardNumber[0],10);
   checksum(finished); // Validates credit Card, Calls checksum function 
}



function split(x) { //Start of Function , which spilts digits bigger then 10.
	var n = x.toString();
	var digitOne = parseInt(n.charAt(0),10);//Gets first digit
	var digitTwo = parseInt(n.charAt(1),10);//Gets second Digit.
	x = digitOne + digitTwo;// Adds both together
	return x; //Returns Digit : example 18 = 1 + 8 = 9
} //End of Function 



function checksum(finished) { //Start of Function 

	for (var i = 0; i < finished; i++) { //Start of For Loop
		finished = finished - 10;
	} //End of For Loop
	
	if (finished === 0) {
		alert('Credit Card is correct and Real');
	} else {
		alert('Credit Card is wrong');
	}
} //End of Function