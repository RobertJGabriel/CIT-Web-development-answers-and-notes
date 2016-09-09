var BrowserSpecific =
{
  registerListeners: function()
  {
    if( Prototype.Browser.IE )
    {
      var inputs = $A(document.getElementsByTagName('input'));
       //Enter key submit handling added only for IE browser.
      if( inputs )
      {
        inputs.each(
                      function( input )
                      {
                        if(input.type === 'text' && !page.util.hasClassName(input,'noFormSubmitIE'))
                        {
                          Event.observe( input, "keypress",
                                         this.checkEnterKeyToSubmit.bindAsEventListener( this, input )
                                        );
                        }
                      }.bind( this )
                   );
      }
   }
 },
 checkEnterKeyToSubmit: function(event, input)
 {
   //if generated character code is equal to ascii 13 (if enter key)
   if(event.keyCode == 13 && input.form)
   {
     var submitButtons = $(input.form).getInputs('submit');
     if(submitButtons && submitButtons.size() > 0)
     {
       submitButtons.first().click();
     }
     Event.stop(event);
   }
   else
   {
     return true;
   }
 },
 // Fix FireFox bug which converts absolute links pasted into a VTBE into relative ones which
 // start with a variable number of "../".
 // https://bugzilla.mozilla.org/show_bug.cgi?id=613517
 handleFirefoxPastedLinksBug: function( baseUrl, vtbeText )
 {
   if ( !baseUrl || !vtbeText )
   {
     return vtbeText;	
   }

   if ( Prototype.Browser.Gecko )
   {
     if( !$( baseUrl.empty() ) && !$( vtbeText.empty() ) )
     {
       //e.g. extract out "http://localhost:80" from "http://localhost:80/webapps/Bb-wiki-BBLEARN/"
       // port is optional
       var absoluteUrlPrefix = baseUrl.match(/https?:[\d]*\/\/[^\/]+/);
       // e.g."../../../bbcswebdav/xid-2202_1" into "http://localhost:80/bbcswebdav/xid-2202_1"
       vtbeText = vtbeText.replace(/(\.\.\/)+(sessions|bbcswebdav|courses|@@)/g, absoluteUrlPrefix + "/" + "$2");
     }
   }
   return vtbeText;
 },

  disableEnterKeyInTextBoxes: function (document)
  {
    var inputs = $A(document.getElementsByTagName('input'));
    if( inputs )
    {
      inputs.each
      (
        function( input )
        { //must add special className for IE textboxes
          if( Prototype.Browser.IE )
          {
            input.addClassName( 'noFormSubmitIE' );
          }
          Event.observe( input, 'keypress', this.disableEnterKey );
        }.bind( this )
      );
    }
  },

  disableEnterKey: function( event )
  {
    if( event.keyCode != Event.KEY_RETURN )
    {
      return;
    }
    Event.stop( event );
    return;
  }
};
