
//------------------------------- Declare global variables.

var request , xml ;



//------------------------------- Format with trailing zeros to two decimal places.

function formatted(sum)
{

  if ( sum.toString().indexOf(".") < 1 )  { sum += ".00"; }	
  if ( sum.toString().indexOf(".") === ( sum.toString().length - 2 ) ) 
  { 
    sum += '0'; 
  }
  return sum;
}



//------------------------------- Calculate row, column, and grand totals.

function totalize()
{
  var i, sum = 0, row_number = 1, col_number = 0;
  var nums = xml.getElementsByTagName("num");

  for( i = 0 ; i < nums.length ; i++ )			
  { 
    sum += parseFloat( nums[i].firstChild.data );	
			
    if( ( i+1 ) % 5 === 0 )							
    {
      document.getElementById("rt"+row_number).innerHTML = formatted(sum);	
      sum = 0;							
      row_number++;									
    }
  }								
	
  while( col_number != 5)								
  {
    for( i = 0 ; i < nums.length ; i++ )		
    { 		
      if( i % 5 === 0)								
      { 
        sum += parseFloat( nums[i+col_number].firstChild.data ); 
      }
    }
    col_number++ ;									
    document.getElementById("ct"+col_number).innerHTML = formatted(sum);			
    sum = 0;									
  }
	
  for( i = 0 ; i < nums.length ; i++ )			
  {  
    sum += parseFloat(nums[i].firstChild.data);	
  }
  document.getElementById("gt").innerHTML = formatted(sum) ;					
}



//------------------------------- Fill cells with stored XML data.

function populateCells()
{
  var i, nums = xml.getElementsByTagName("num");
 	
  for( i = 0 ; i < nums.length ; i++ )		
  { 
    document.getElementById("n"+i).innerHTML = nums[i].firstChild.data;	
  }
  totalize();			
}



//------------------------------- Handle XMLHttpRequest readystatechange event.
 
function storeXML()								
{
  if ( (request.readyState === 4) && (request.status === 200) )
  {
      xml = request.responseXML ;	
      populateCells();								
  } 
}



//------------------------------- Handle form button's click event.

function update()
{
  var row = document.getElementById("rownum").options.selectedIndex;		
  var col = document.getElementById("colnum").options.selectedIndex;		
  var new_value = document.getElementById("new_value").value;
  var panel = document.getElementById("legend");			

  if(row === 0) 
  { 
    panel.innerHTML = "Select a row"; return;	
  }
	
  if(col === 0) 
  { 
    panel.innerHTML = "Select a column"; return; 
  }
  
  if(!new_value) 
  { 
    panel.innerHTML = "Enter a value"; return; 
  }

  if( isNaN(new_value) ) 
  {
    panel.innerHTML = "Enter a number"; return; 
  }
 
  var target = (((row - 1) * 5) + col)-1;						
  xml.getElementsByTagName("num")[target].firstChild.data = formatted(new_value);	
  populateCells();								
  totalize();									

  document.getElementById("rownum").options[0].selected=true;			
  document.getElementById("colnum").options[0].selected=true;
  document.getElementById("new_value").value="";
  document.getElementById("legend").innerHTML = "Cell Editor";
}



//------------------------------ Send request and nominate event-handlers.

function init()									
{								
  if( XMLHttpRequest ) 
  { 
    request = new XMLHttpRequest();	
  }				
  else if ( ActiveXObject ) 
  { 
    request = new ActiveXObject("Microsoft.XMLHTTP"); 
  }				  
  else 
  { 
    return false;	
  }							
								          
  request.open("GET", "ajax.xml", true);					
  request.send(null);								
  request.onreadystatechange = storeXML;

  document.getElementById("btn").onclick=update;					
}
onload=init;


// ---------------------------- EOF.