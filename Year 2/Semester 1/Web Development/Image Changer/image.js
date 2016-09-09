function load() {
	var key = randon();
	document.getElementById("image").src = key + ".jpg";
}

function randon() {
	image = Math.floor(Math.random() * 4);
	return image;
}
function rollover() {
var key = randon();
	document.getElementById("header").innerText = "Random Bear";
document.getElementById("image").src = "Bear/" +key + ".jpg";
}
function rolloout() {
var key = randon();
	document.getElementById("header").innerText = "Random Puppie";
document.getElementById("image").src =  key + ".jpg";

}

