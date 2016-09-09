var NumberLocalizer = Class.create();

NumberLocalizer.prototype =
{
    initialize : function()
    {
      var thousandsSeparator = LOCALE_SETTINGS[ 'number_format.thousands_sep' ];
      var decimalSeparator = LOCALE_SETTINGS[ 'number_format.decimal_point' ];

      this.thousandsSeparator = ( thousandsSeparator === null ) ? ',' : thousandsSeparator;
      this.needToConvertThousands = ( this.thousandsSeparator !== ',' ) ? true : false;

      this.decimalSeparator = ( decimalSeparator === null ) ? '.' : decimalSeparator;
      this.needToConvertDecimal = ( this.decimalSeparator !== '.' ) ? true : false;
    },

    // Takes a number that is unlocalized and converts it to
    // the current locale format.
    formatNumber : function( f )
    {
      var result;
      result = f.toString();

      // Replace and thousands delimiter with a token so we can
      // replace it with the final symbol after we replace the decimal symbol.
      if ( this.needToConvertThousands )
      {
        result = result.replace( ',', '[comma]' );
      }

      if ( this.needToConvertDecimal )
      {
        result = result.replace( '.', this.decimalSeparator );
      }

      if ( this.needToConvertThousands )
      {
        result = result.replace( '[comma]', this.thousandsSeparator );
      }

      return result;
    },

    // Takes a number that is in the current locale format and
    // converts it back to an unlocalized number.
    parseNumber : function( num )
    {
      var result;
      result = num.toString();

      // Parsing string to return as a float, so we don't need the thousands
      // separator anymore.
      result = result.replace( this.thousandsSeparator, '' );

      if ( this.needToConvertDecimal )
      {
        result = result.replace( this.decimalSeparator, '.' );
      }

      return parseFloat( result );
    }
};