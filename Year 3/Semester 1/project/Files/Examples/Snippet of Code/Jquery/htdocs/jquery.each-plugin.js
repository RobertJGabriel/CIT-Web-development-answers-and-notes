( function( $ ) {

  $.fn.spotlight = function( hue ) {

    var hue = hue || "yellow" ;

    return this.each( function ( index ) { 

      if ( index % 2 === 0 ) 
      $( this ).css( "background" , hue ) ;

    } ) ;

  } ;

} ( jQuery ) ) ;
