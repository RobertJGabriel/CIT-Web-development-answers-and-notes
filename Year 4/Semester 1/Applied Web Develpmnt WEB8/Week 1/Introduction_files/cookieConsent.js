CookieConsent = Class.create();
CookieConsent.prototype =
  {
      initialize : function( b2URLBase )
      {
        this.b2URLBase = b2URLBase;
        this.CONSENT_COOKIE_NAME = 'COOKIE_CONSENT_ACCEPTED';
        var cookieLB;
      },

      checkCookieAcceptance : function( nonceParameter, isFrameSetSupported )
      {
        var cookieString = getCookie( this.CONSENT_COOKIE_NAME );
        if ( cookieString === null )
        { // cookie wasn't found
          var backURL = top.location.href;
          var consentURL = this.b2URLBase + 'execute/consent?backURL=' + escape( backURL ) + '&preview=false&' + nonceParameter;
          if ( isFrameSetSupported )
          {
            cookieConsent.cookieLB = new lightbox.Lightbox(
            {
                useDefaultDimensionsAsMinimumSize : true,
                closeOnBodyClick : false,
                showCloseLink : false,
                lbTemplate : '<div class="lb-content CookieConsent" aria-live="assertive"></div>',
                ajax :
                {
                    url : consentURL,
                    loadExternalScripts : true
                }
            } );
            cookieConsent.cookieLB.open();
          }
          else
          {
            top.location.href = consentURL;
          }
        }
      },

      decline : function()
      {
        deleteCookie( 'JSESSIONID' );
        deleteCookie( 'session_id' );
        if ( cookieConsent.cookieLB )
        {
          cookieConsent.cookieLB.close();
          cookieConsent.cookieLB = null;
        }
        location.href = this.b2URLBase + 'execute/optOut';
      },

      agree : function( backURL )
      {
        // Set expire to 10 years later.
        var expiredate = new Date();
        expiredate.setFullYear( expiredate.getFullYear() + 10 );
        setCookie( this.CONSENT_COOKIE_NAME, true, expiredate );
        if ( cookieConsent.cookieLB )
        {
          cookieConsent.cookieLB.close();
        }
        else
        {
          location.href = backURL;
        }
      },

      launchPreview : function( previewNonce )
      {
        var privacyPolicyURL = document.getElementById( 'privacyPolicyURL' ).value;
        popup.launch( this.b2URLBase + 'execute/consent?preview=true&privacyPolicyURL=' + privacyPolicyURL + previewNonce, 'preview', height = 600, width = 300 );
      }
  };
