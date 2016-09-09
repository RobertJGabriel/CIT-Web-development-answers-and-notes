#!C:/Perl/bin/perl                                

# This is a Perl script written by Mike McGrath to display, 
# in alphanumeric name order, 
# all name/value pairs submitted from a HTML form.
# It requires Perl to be installed and this script 
# should be placed on the web server, 
# in the directory configured for CGI scripts, 
# alongside the echo.css style sheet.

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

print "Content-Type: text/html\n\n<html>";
print "<head><title>Web Server Response</title>";
print "<link rel='stylesheet' type='text/css' href='echo.css'>";

print "</head><body><div id='Wrapper'>";
print "<b class='b1h'></b><b class='b2h'></b><b class='b3h'></b><b class='b4h'></b>";
print "<div class='heading'><h3>WebServer Response Panel</h3></div>";
print "<div class='content'><div id='Panel'>";


print "The following data was received from a HTML form submission...<br>";
print "<table><col id='C1' ><col id='C2' >";
print "<tr><th class='bright'>Name</th><th>Value</th></tr>";

foreach $key (sort (keys %formdata)){                      
print "<tr><td class='bright'>$key</td><td>$formdata{$key}</td></tr>"; }    

print "</table></div></div>";
print "<b class='b4bh'></b><b class='b3bh'></b><b class='b2bh'></b><b class='b1h'></b>";
print "</div></body></html>";

# End ----------------------------------------------------------------------------------





