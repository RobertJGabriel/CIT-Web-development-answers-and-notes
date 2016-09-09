<!-- example for PHP 5.0.0 final release -->

<?php

	$to = $_POST['to'];
	$from = $_POST['from'];
	$re = $_POST['re'];
	$comments = $_POST['comments'];

	$att = $_FILES['att'];
	$att_path = $_FILES['att']['tmp_name'];
	$att_name = $_FILES['att']['name'];
	$att_size = $_FILES['att']['size'];
	$att_type = $_FILES['att']['type'];

	#open, read then close the file
	$fp = fopen( $att_path, "rb");
	$file = fread( $fp, $att_size );
	fclose( $fp );

	#create a boundary string
	$num = md5(time());
	$str = "==Multipart_Boundary_x{$num}x";

	#encode the data for safe transit 
	#and intersperse 76-character chunks with \r\n
	$file = chunk_split(base64_encode($file));

	#define header
	$hdr  = "From: $from\n";
	$hdr .= "MIME-Version: 1.0\n";
	$hdr .= "Content-Type: multipart/mixed;\n ";
	$hdr .= "boundary=\"{$str}\"";

	#define message
	$msg = "This is a multi-part message in MIME format\n\n";
	$msg .= "--{$str}\n";
	$msg .= "Content-Type: text/plain; charset=\"iso-8859-1\"\n";
	$msg .= "Content-Transfer-Encoding: 8bit\n\n";
	$msg .= "$comments\n\n";
	$msg .= "--{$str}\n";

	#define the non-text attachment
	$msg .= "Content-Type: {$att_type};\n";
	$msg .= "name=\"{$att_name}\"\n";
	$msg .= "Content-Disposition: attachment;\n";
	$msg .= "filename=\"{$att_name}\"\n";
	$msg .= "Content-Transfer-Encoding: base64\n\n";
	$msg .= "$file\n\n";
	$msg .= "--{$str}";

	#send the email now...
	$ok = mail( $to, $re, $msg, $hdr);
	if($ok) echo "OK";	

?>
