var CLIENT_CACHE_GUID_COOKIE_NAME = 'web_client_cache_guid';


var ClientCache =

{
	LZ_STRING_COMPRESSED_KEY_PREFIX : 'lzsc_',
	DOM_QUOTA_REACHED_EXCEPTION : 22,
    clear : function()

    {

      sessionStorage.clear();

    },

    /**
     * Sets an item to the ClientCache. Uses compression if string representation of the data has 524288 characters or
     * more. Compression is also used if a quota exceeded exception is thrown while setting the data without using
     * compression.
     *
     * @param alwaysCompress store the item compressed
     */
    setItem : function( key, value, alwaysCompress )

    {

      this._validateSession();
      var isCompressedMode = false;
      try
      {
        // sessionStorage.setItem stringify non-string variables anyways. We will do it here to figure out its length
        // below.
        if ( typeof ( value ) !== 'string' )
        {
          value = value.toString();
        }
        // store compressed if larger than .5MB or if the caller explicitly asks for compression
        if ( value.length >= 524288 || alwaysCompress )
        {
          isCompressedMode = true;
          sessionStorage.removeItem( key );
          this._setItemLzStrCompressed( key, value, true );
        }
        else
        {
          sessionStorage.removeItem( this.LZ_STRING_COMPRESSED_KEY_PREFIX + key );
          sessionStorage.setItem( key, value );
        }
      }
      catch ( e )
      {
        if ( !isCompressedMode && this.DOM_QUOTA_REACHED_EXCEPTION === this.getClientCacheException( e ) )
        {
          sessionStorage.removeItem( key );
          this._setItemLzStrCompressed( key, value, true );
        }
        else
        {
          throw e;
        }
      }
    },
    /**
     * @private For internal use only. Please use this.setItem(...) instead. Sets lz-string compressed item to the
     *          ClientCache.
     */
    _setItemLzStrCompressed : function( key, value, skipSessionValidation )
    {
      if ( !skipSessionValidation )
      {
        this._validateSession();
      }
      sessionStorage.setItem( this.LZ_STRING_COMPRESSED_KEY_PREFIX + key, LZString.compressToUTF16( value ) );

    },
    /**
     * @param onlyLookupCompressed gets the item stored compressed and skips altogether checking for non-compressed item
     *          with the key
     */
    getItem : function( key, onlyLookupCompressed )
    {
      this._validateSession();
      var item;
      if ( !onlyLookupCompressed )
      {
        item = sessionStorage.getItem( key );
      }
      if ( !item /* undefined or null */|| onlyLookupCompressed )
      {
        item = this._getItemLzStrCompressed( key, true );
      }
      return item;
    },
    

    /**
     * @private For internal use only. Please use this.getItem(...) instead. Gets lz-string compressed item from the
     *          ClientCache.
     */
    _getItemLzStrCompressed : function( key, skipSessionValidation )
    {
      if ( !skipSessionValidation )

    {
      this._validateSession();
    }
      var itemTmp = sessionStorage.getItem( this.LZ_STRING_COMPRESSED_KEY_PREFIX + key );
      if ( itemTmp )
      {
        return LZString.decompressFromUTF16( itemTmp );
      }
      return itemTmp;

    },


    removeItem : function( key )

    {

      this._validateSession();

      sessionStorage.removeItem( key );
      sessionStorage.removeItem( this.LZ_STRING_COMPRESSED_KEY_PREFIX + key );

    },


    _validateSession : function()

    {

      var guidCookie = getCookie( CLIENT_CACHE_GUID_COOKIE_NAME );

      if ( guidCookie == null || guidCookie != sessionStorage.getItem( CLIENT_CACHE_GUID_COOKIE_NAME ) )

      {

        sessionStorage.clear();

        if ( guidCookie != null )

        {

          sessionStorage.setItem( CLIENT_CACHE_GUID_COOKIE_NAME, guidCookie );

        }
      }
  },

  getClientCacheException : function( exception )
  {
    if ( !exception )
    {
      return;
    }
    if ( exception.name &&
         ( exception.name === "NS_ERROR_DOM_QUOTA_REACHED" /* FF */|| exception.name === "QUOTA_EXCEEDED_ERR" /* Safari */|| exception.name === "QuotaExceededError" /* Chrome */) )
    {
      // LRN-84254 this is a solution at least to give a more graceful error message from each consumer of the
      // ClientCache mechanism (ex. Grace Center). We may eventually have to come up with a different solution that
      // doen't get limited by the sessionStorage limit imposed by the browsers.
      return this.DOM_QUOTA_REACHED_EXCEPTION;
    };
    return;
  }
};
