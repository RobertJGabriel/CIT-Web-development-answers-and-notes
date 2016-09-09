#!C:/Perl/bin/perl                                

# Perl script for "HTML5 in easy steps" by Mike McGrath at www.ineasysteps.com
# Displays in alphanumeric name order, all name=value pairs submitted from a HTML form. 
# It should be placed on the web server, in the directory configured for CGI scripts, 
# alongside the echo.css style sheet.
# THIS SCRIPT IS FOR DEMONSTRATION PURPOSES ONLY.
#
# HTML5 in easy steps:example 52.2.

# Process request ----------------------------------------------------------------------

  if ($ENV{'REQUEST_METHOD'} eq 'GET') 
  {                                                  
    @pairs = split(/&/, $ENV{'QUERY_STRING'});              
  }                                                   
  elsif ($ENV{'REQUEST_METHOD'} eq 'POST') 
  {                                                    
    read (STDIN, $buffer, $ENV{'CONTENT_LENGTH'});                             
    @pairs = split(/&/, $buffer);                          
    if ($ENV{'QUERY_STRING'}) 
    {                            
      @getpairs =split(/&/, $ENV{'QUERY_STRING'}); 
      push(@pairs,@getpairs);   
    }                                                       
  }                                                    
  else 
  {                                               
    print "Content-type:text/html\n\n";                 
    print "Unrecognized Request Method - Use GET or POST.";
  }                                                                                                     

  foreach $pair (@pairs) 
  {                                                  
    ($key, $value) = split(/=/, $pair);                  
    $key =~ tr/+/ /;                                     
    $key =~ s/%(..)/pack("c", hex($1))/eg;             
    $value =~ tr/+/ /;                                   
    $value =~ s/%(..)/pack("c", hex($1))/eg;           
    $value =~s/<!--(.|\n)*-->//g;  # ignore SSI
    if ($formdata{$key}) 
    {                                         
      $formdata{$key} .= ", $value";                          
    }                                                    
    else 
    { 
    $formdata{$key} = $value; 
    }                                
  }                                                   

# Send response ----------------------------------------------------------------------
$num=1;
print "Content-Type: text/html\n\n<!DOCTYPE HTML>\n\n<html lang=\"en\">\n\n";
print "<head>\n<meta charset=\"UTF-8\">\n<title>Web Server Response</title>\n";
print "<link rel=\"stylesheet\" href=\"echo.css\">\n</head>\n\n<body>\n";
print "<table>\n<caption><img src=\"perl.png\"></caption>\n";
print "<colgroup><col class=\"no\"><col><col></colgroup>\n";
print "<thead><tr><th>Pair No.<th>Name<th>Value</tr></thead>\n";
print "<tfoot><tr><td colspan=\"3\"><img src=\"abyss.png\"></tr></tfoot>\n<tbody>\n";
foreach $key (sort (keys %formdata))
{
	print "<tr";
	if($num%2 == 0){print " class=\"stripe\""};
	print "><td>$num<td>$key<td>$formdata{$key}</tr>\n"; 
	$num++;
}    
print "</tbody>\n</table>\n</body>\n\n</html>";

# End ----------------------------------------------------------------------------------




