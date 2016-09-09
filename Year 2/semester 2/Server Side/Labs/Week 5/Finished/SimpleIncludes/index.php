<html>
<head>

<title></title></head>
<body>
<?php include ("inc_header.html"); ?>
<div style = "width:20%; text-align:center; ? oat:left">
<?php include ("inc_buttonnav.html"); ?>
</div>
<!-- Start of Dynamic Content section -->
<?php
if (isset($_GET['content'])) {
 switch ($_GET['content']) {
 case 'About Me':
 include('inc_about.html');
 break;
 case 'Contact Me':
 include('inc_contact.html');
 break;
 case 'Home': // A value of 'Home' means to
 // display the default page
 default:
 include('inc_home.html');
 break;
 }
}
?>
<!-- End of Dynamic Content section -->
<?php include ("inc_footer.html.php"); ?>
</body>
</html>


