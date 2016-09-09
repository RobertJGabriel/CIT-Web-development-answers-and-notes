
function init()
{
 var panel=document.getElementById("panel");

 var i, rand, temp, str, nums = [];

 for( i=1; i<50; i++){ nums[i] = i ; }


 for( i=1; i<50; i++)
 {
   rand = Math.ceil(Math.random()*49);
   temp = nums[i];
   nums[i] = nums[rand];
   nums[rand]=temp;
 }

 str = "Your Six Lucky Numbers:<br>";
 for( i=1; i<7; i++)
 {
   str += nums[i];
   if( i !== 6) { str += " - "; }
 }

 panel.innerHTML = str;


}
window.onload=init;


