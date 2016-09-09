#!C:/Perl/bin/perl                                

# This is a Perl script written by Mike McGrath to display, 
# in alphanumeric name order, 
# all name/value pairs submitted from a HTML form.
# It requires Perl to be installed and this script 
# should be placed on the web server, 
# in the directory configured for CGI scripts.

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
print "<head><title>Web Server Response</title></head><div id='Panel'>";
print "The following data was received from a HTML form submission...<br>";
print "<table width='450' border='2' cellpadding='5'><col><col><tr><th>Name</th><th>Value</th></tr>";
foreach $key (sort (keys %formdata)){                      
print "<tr><td>$key</td><td>$formdata{$key}</td></tr>"; }    
print "</table></div></body></html>";

# End ----------------------------------------------------------------------------------





