#!C:/Perl/bin/perl

# Perl script for "HTML5 in easy steps" by Mike McGrath at www.ineasysteps.com
# Uploads selected file and displays name=value pair plus size of uploaded file. 
# It should be placed on the web server, in the directory configured for CGI scripts, 
# alongside the upload.css style sheet.
# THIS SCRIPT IS FOR DEMONSTRATION PURPOSES ONLY.
#
# HTML5 in easy steps:example 63.2.

# Process request ----------------------------------------------------------------------
use CGI;
binmode(STDIN);
@data = <STDIN>;
($id,$info,$type) = splice(@data,0,4);
splice(@data,$#data,1);
($tmp,$name,$value) = split(/;/,$info);
$name=~s/.*name="(.*)".*/$1/;
$value=~s/.*filename="(.*)".*/$1/;
$in = join("",@data);
$in = substr($in,0,length($in) - 2);;
open(file, ">".$value );
binmode(file);
print file $in;
close(file);

# Send response ----------------------------------------------------------------------

print "Content-type:text/html\n\n<!DOCTYPE HTML>\n\n<html lang=\"en\">\n\n";
print "<head>\n<meta charset=\"UTF-8\">\n<title>Web Server Response</title>\n";
print "<link rel=\"stylesheet\" href=\"upload.css\">\n</head>\n\n<body>\n";
print "<table>\n<caption><img src=\"perl.png\"></caption>\n";
print "<colgroup><col><col><col><col></colgroup>\n";
print "<thead><tr><th>Status<th>Name<th>Value<th>File Size</tr></thead>\n";
print "<tfoot><tr><td colspan=\"4\"><img src=\"abyss.png\"></tr></tfoot>\n<tbody>\n";
print "<tr><td>Complete<td>".$name."<td>".$value."<td>".length($in)." bytes</tr>";
print "</tbody>\n</table>\n</body>\n\n</html>";

# End ----------------------------------------------------------------------------------