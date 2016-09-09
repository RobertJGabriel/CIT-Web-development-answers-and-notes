var popup =
{
  /**
   * Launches a new popup window.  This method is used for an random popup that
   * might be necessary (preview window for example).  If you are launching a
   * "picker" window you should use launchPicker() instead.
   *
   * @param url the url to open the new window using.  This is the first value
   *        passed to window.open().  This is required.
   * @param name the name of the  new window.  This is the second value passed
   *        to window.open().  This is required.
   * @param width the width of the window to launch.  If not provided a default
   *        value will be used.  Do not provide unless your specific requirements
   *        dictate it.
   * @param height the height of the window to launch.  If not provided a default
   *        value will be used.  Do not provide unless your specific requirements
   *        dictate it.
   * @param resizable whether the new window should be resizable.  If not
   *        provided a default value will be used.  Do not provide unless your
   *        specific requirements dictate it.
   * @param showStatus whether the new window should be show the status bar.  If
   *        not provided a default value will be used.  Do not provide unless
   *        your specific requirements dictate it.
   * @param scrolling whether the new window should allow scrolling.  If not
   *        provided a default value will be used.  Do not provide unless your
   *        specific requirements dictate it.
   * @return a reference to the popup window generated
   */
  launch: function( url, name, width, height, resizable, showStatus, scrolling )
  {
    if ( typeof( width ) == 'undefined' )
    {
      // for RTL, the width needs to be wider, to prevent vertical line overlapping in the popup
      if ( page.util.isRTL() )
      {
        width = 890;
      }
      else
      {
        width = 825;  // wide enough to prevent a horizontal scrollbar in most cases
      }
    }
    if ( typeof( height ) == 'undefined' )
    {
      height = 500;
    }
    if ( typeof( resizable ) == 'undefined' )
    {
      resizable = 'yes';
    }
    if ( typeof( showStatus ) == 'undefined' )
    {
      showStatus = 'yes';
    }
    if ( typeof( scrolling ) == 'undefined' )
    {
      scrolling = 'yes';
    }

    // figure out placement of the new window we will open.  If the desired size
    // of the new window is bigger than the screen size then make the window
    // smaller and put it far left.  Otherwise, center the window on screen.
    var screenX = 0;
    if ( screen.width <= width )
    {
      width = screen.width;  // new window should not be wider than the screen
    }
    else
    {
      screenX = ( screen.width - width ) / 2;  // center on the screen
    }

    var popup = window.open( url,
                             name,
                             'width=' + width +
                             ',height=' + height +
                             ',resizable=' + resizable +
                             ',scrollbars=' + scrolling +
                             ',status=' + showStatus +
                             ',top=20' +
                             ',screenY=20' +
                             ',screenX=' + screenX +
                             ',left=' + screenX );
    if ( popup )
    {
      popup.focus();
      if ( !popup.opener )
      {
        popup.opener = self;
      }

      window.top.name = 'bbWin';
    }

    return popup;
  },

  /**
   * Launches a new "picker" window.
   * <p>
   * At the moment the only difference between this method and launch (besides
   * the inability to specify some advanced and rarely used options) is that
   * this method will default the {@code name} value if not provided.  However,
   * you should still use this method if you are launching a "picker" type
   * window as additional differences may be introduced in the future.
   *
   * @param url the url to open the new window using.  This is the first value
   *        passed to window.open().  This is required.
   * @param name the name of the  new window.  This is the second value passed
   *        to window.open().  If not provided a default value will be used.
   * @param width the width of the window to launch.  If not provided a default
   *        value will be used.  Do not provide unless your specific requirements
   *        dictate it.
   * @param height the height of the window to launch.  If not provided a default
   *        value will be used.  Do not provide unless your specific requirements
   *        dictate it.
   * @return a reference to the picker window generated
   */
  launchPicker: function( url, name, width, height )
  {
    if ( typeof( name ) == 'undefined' )
    {
      name = 'picker';
    }

    return popup.launch( url, name, width, height );
  }
};
