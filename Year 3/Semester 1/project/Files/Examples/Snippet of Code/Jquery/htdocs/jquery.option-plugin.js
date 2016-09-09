( function( $ ) {

  $.fn.spotlight = function( options ) {

    var defs = { bg: "aqua" , fg: "blue" } ;
    var opts = $.extend(  defs , options ) ;

    return this.each( function ( index ) { 

      if ( index % 2 === 0 ) 
      $( this ).css( { background:opts.bg , color:opts.fg } ) ;

    } ) ;

  } ;

} ( jQuery ) ) ;
