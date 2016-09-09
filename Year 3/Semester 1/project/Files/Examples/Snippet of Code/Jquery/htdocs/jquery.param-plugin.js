( function ( $ ) {

  $.fn.spotlight = function( hue ) {

    hue = hue || "yellow" ;

    this.css( "background" , hue ) ;

    return this ;

  } ;

} ( jQuery ) ) ;