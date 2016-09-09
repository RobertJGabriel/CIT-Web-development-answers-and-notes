<?php 

/*


¦¦¦¦¦¦+ ¦¦+   ¦¦+                                                                                      
¦¦+--¦¦++¦¦+ ¦¦++                                                                                      
¦¦¦¦¦¦++ +¦¦¦¦++                                                                                       
¦¦+--¦¦+  +¦¦++                                                                                        
¦¦¦¦¦¦++   ¦¦¦                                                                                         
+-----+    +-+                                                                                         
                                                                                                       
¦¦¦¦¦¦¦¦+¦¦¦¦¦¦+  ¦¦¦¦¦+  ¦¦¦¦¦¦+¦¦+   ¦¦+    ¦¦¦+   ¦¦+¦¦+¦¦+  ¦¦+ ¦¦¦¦¦¦+ ¦¦¦+   ¦¦+                 
+--¦¦+--+¦¦+--¦¦+¦¦+--¦¦+¦¦+----++¦¦+ ¦¦++    ¦¦¦¦+  ¦¦¦¦¦¦+¦¦+¦¦++¦¦+---¦¦+¦¦¦¦+  ¦¦¦                 
   ¦¦¦   ¦¦¦¦¦¦++¦¦¦¦¦¦¦¦¦¦¦      +¦¦¦¦++     ¦¦+¦¦+ ¦¦¦¦¦¦ +¦¦¦++ ¦¦¦   ¦¦¦¦¦+¦¦+ ¦¦¦                 
   ¦¦¦   ¦¦+--¦¦+¦¦+--¦¦¦¦¦¦       +¦¦++      ¦¦¦+¦¦+¦¦¦¦¦¦ ¦¦+¦¦+ ¦¦¦   ¦¦¦¦¦¦+¦¦+¦¦¦                 
   ¦¦¦   ¦¦¦  ¦¦¦¦¦¦  ¦¦¦+¦¦¦¦¦¦+   ¦¦¦       ¦¦¦ +¦¦¦¦¦¦¦¦¦¦++ ¦¦++¦¦¦¦¦¦++¦¦¦ +¦¦¦¦¦                 
   +-+   +-+  +-++-+  +-+ +-----+   +-+       +-+  +---++-++-+  +-+ +-----+ +-+  +---+                 
                                                                                                       
 ¦¦¦¦¦+ ¦¦¦+   ¦¦+¦¦¦¦¦¦+                                                                              
¦¦+--¦¦+¦¦¦¦+  ¦¦¦¦¦+--¦¦+                                                                             
¦¦¦¦¦¦¦¦¦¦+¦¦+ ¦¦¦¦¦¦  ¦¦¦                                                                             
¦¦+--¦¦¦¦¦¦+¦¦+¦¦¦¦¦¦  ¦¦¦                                                                             
¦¦¦  ¦¦¦¦¦¦ +¦¦¦¦¦¦¦¦¦¦¦++                                                                             
+-+  +-++-+  +---++-----+                                                                              
                                                                                                       
¦¦¦¦¦¦+  ¦¦¦¦¦¦+ ¦¦¦¦¦¦+ ¦¦¦¦¦¦¦+¦¦¦¦¦¦+ ¦¦¦¦¦¦¦¦+     ¦¦¦¦¦¦+  ¦¦¦¦¦+ ¦¦¦¦¦¦+ ¦¦¦¦¦¦+ ¦¦¦¦¦¦¦+¦¦+     
¦¦+--¦¦+¦¦+---¦¦+¦¦+--¦¦+¦¦+----+¦¦+--¦¦++--¦¦+--+    ¦¦+----+ ¦¦+--¦¦+¦¦+--¦¦+¦¦+--¦¦+¦¦+----+¦¦¦     
¦¦¦¦¦¦++¦¦¦   ¦¦¦¦¦¦¦¦¦++¦¦¦¦¦+  ¦¦¦¦¦¦++   ¦¦¦       ¦¦¦  ¦¦¦+¦¦¦¦¦¦¦¦¦¦¦¦¦¦++¦¦¦¦¦¦++¦¦¦¦¦+  ¦¦¦     
¦¦+--¦¦+¦¦¦   ¦¦¦¦¦+--¦¦+¦¦+--+  ¦¦+--¦¦+   ¦¦¦       ¦¦¦   ¦¦¦¦¦+--¦¦¦¦¦+--¦¦+¦¦+--¦¦+¦¦+--+  ¦¦¦     
¦¦¦  ¦¦¦+¦¦¦¦¦¦++¦¦¦¦¦¦++¦¦¦¦¦¦¦+¦¦¦  ¦¦¦   ¦¦¦       +¦¦¦¦¦¦++¦¦¦  ¦¦¦¦¦¦¦¦¦++¦¦¦  ¦¦¦¦¦¦¦¦¦¦+¦¦¦¦¦¦¦+
+-+  +-+ +-----+ +-----+ +------++-+  +-+   +-+        +-----+ +-+  +-++-----+ +-+  +-++------++------+
                                                                                                       




*/







    if (isset($_GET['action']))    {
        echo $actions=$_GET['action'];
        switch( $actions ) {
            case 'insert':
                insert();
                break;
            case 'delete':
                delete();
                break;
            case 'update':
                update();
                break;
            case 'lastused':
                lastused();
                break;
            default:
                echo 'Unknown action '.$actions;
            }

    } else {
        echo" ";
    }

   // -- Function Name : insert
	// -- Params : None
// -- Purpose : Inserts the information from the form into the database.
    function insert() {
      require 'config.php';
        echo "insert";
        $con=mysqli_connect($host,$username,$password,$database);
        
        if (mysqli_connect_errno())            {
            echo "Failed to connect to MySQL: " . mysqli_connect_error();
        }
		$hours  = (int)$_POST[filmDuration];
		$mins  = (int)$_POST[filmDurationMin];
		$timeStamp = (($hours * 60) + ($mins)  )  ;
        $sql="INSERT INTO titles (cert, filmtitle,releaseDate,filmDuration,director,description)
                      VALUES
                      ('$_POST[cert]','$_POST[filmtitle]','$_POST[releaseDate]','$timeStamp','$_POST[director]','$_POST[description]')";
        
        if (!mysqli_query($con,$sql)){
            die('Error: ' . mysqli_error($con));
        } else {
            writeCookie();
        }

        mysqli_close($con);
   header("Location: {$_SERVER['HTTP_REFERER']}");
}


// -- Function Name : delete
// -- Params : None
// -- Purpose : Deletes information from the datbase, using the select tabel
function delete() {
  require 'config.php';
    echo "delete";
    $con=mysqli_connect($host,$username,$password,$database);
    
    if (mysqli_connect_errno())  {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }
    mysqli_query($con,"DELETE FROM titles WHERE ID='$_POST[id]'");
    writeCookie();
    mysqli_close($con);
    header("Location: {$_SERVER['HTTP_REFERER']}");
}



// -- Function Name : update
// -- Params : 
// -- Purpose : Updates the information 
function update() {
  require 'config.php';
    echo "update";
    $con=mysqli_connect($host,$username,$password,$database);
    // Check connection
    if (mysqli_connect_errno())  {
        echo "Failed to connect to MySQL: " . mysqli_connect_error();
    }

 mysqli_query($con,"UPDATE titles SET director='$_POST[director]',description='$_POST[description]',filmtitle='$_POST[filmtitle]'
                        WHERE ID ='$_POST[ID]'");
writeCookie()  ;
    header("Location: {$_SERVER['HTTP_REFERER']}");

}



// -- Function Name : writeCookie
// -- Params : 
// -- Purpose : Writes the cookie
function writeCookie()    {
    //Unsets a Cookie
  setcookie("user", "", time()-3600);
    setcookie("time", "", time()-3600);
    // Expires in ten years :)
    $expire= time() + 3600;
    //Storing user and time
    $user = "Steve Roger";
    //Gets time and stores it 
    $dt = new DateTime();
    $time = $dt->format('H:i:s');
    //setting cookie for time and user
    setcookie("user", $user, $expire);
    setcookie("time", $time, $expire);
}



// -- Function Name : readCookie
// -- Params : 
// -- Purpose : Reads the Cookie
function readCookie()    {
    // reads if cookie is there 
    
    if (isset($_COOKIE["user"])){
        echo "<h3>Lasted Edited </h3>by ". $_COOKIE["user"] . "<br/>";
        echo "Last edited on " . $_COOKIE["time"] . "<br>";
    } else {
        //Displays a message if the cookie isnt set
        echo "Welcome Guest!<br>";
        writeCookie();
    }

}

?>