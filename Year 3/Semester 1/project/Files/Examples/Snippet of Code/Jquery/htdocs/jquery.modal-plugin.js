/*
*  Simple jQuery plugin to display/hide modal dialog message.
*  Boolean vis sets visibility and string msg sets a message. 
*/

( function( $ ) {

  $.fn.modal = function( options ) {

    var opts = $.extend( { vis:true , msg: "" } , options ) ;
    $( "#out" ).text( opts.msg ) ; 
    opts.vis ? this.show() : this.hide() ;
    return this ;

  } ;

} ( jQuery ) ) ;
