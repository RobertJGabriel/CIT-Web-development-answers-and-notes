var money = 10;
document.getElementById("chance").addEventListener("click", slot);


function slot() {

    var slotOne = Math.floor(Math.random() * 4);
    var slotTwo = Math.floor(Math.random() * 4);
    var slotThree = Math.floor(Math.random() * 4);

    document.getElementById("two").innerHTML = slotTwo;
    document.getElementById("one").innerHTML = slotOne;
    document.getElementById("three").innerHTML = slotThree;


    if ((slotOne == slotTwo) && (slotTwo == slotThree) && (slotOne == slotThree)) {
        document.getElementById("winner").innerHTML = "You won &euro;3 ";
        money = (money + 3);
    } else if ((slotOne == slotTwo) || (slotTwo == slotThree) || (slotOne == slotThree)) {
        document.getElementById("winner").innerHTML = "You won &euro;2";
        money = (money + 2);
    } else {
        document.getElementById("winner").innerHTML = "You Lost &euro;3";
        money = (money - 3);
    }
    document.getElementById("money").innerHTML = "Balance : &euro;" + money;
}