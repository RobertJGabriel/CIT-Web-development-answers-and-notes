/**
 * Only include the contents of this file once - for example, if this is included in a lightbox we don't want to re-run
 * all of this - just use the loaded version.  (i.e. rerunning would clear page.bundle which would remove all the
 * language strings for the current page)
 */
if (!window.page)
{
var page = {};

page.isLoaded = false;

/**
 * Utility for adding and using localized messages on the page.
 */
page.bundle = {};
page.bundle.messages = {};
page.bundle.addKey = function( key, value )
{
  page.bundle.messages[key] = value;
};

page.bundle.getString = function( key /*, arg1, arg2, ..., argN */ )
{
  var result = page.bundle.messages[key];
  if ( !result )
  {
     return "!!!!" + key + "!!!!!";
  }
  else
  {
    if ( arguments.length > 1 )
    {
      for ( var i = 1; i < arguments.length; i++ )
      {
        result = result.replace( new RegExp("\\{"+(i-1)+"\\}","g"), arguments[i] );
      }
    }
    return result;
  }
};

/**
 * Provides support for lazy initialization of javascript behavior when a certain
 * event happens to a certain item.
 */
page.LazyInit = function( event, eventTypes, initCode )
{
  var e = event || window.event;
  var target = Event.element( event );
  // This is because events bubble and we want a reference
  // to the element we registered the handlers on.
  target = page.util.upToClass(target, "jsInit");
  for (var i = 0; i < eventTypes.length; i++ )
  {
    target['on'+eventTypes[i]] = null;
  }
  eval( initCode ); //initCode can reference "target"
};

/**
 * Evaluates any <script> tags in the provided string in the global scope.
 * Useful for evaluating scripts that come back in text from an Ajax call.
 * If signalObject is passed then signalObject.evaluatingScripts will be set to false when done.
 */
page.globalEvalScripts = function(str, evalExternalScripts, signalObject)
{
  //Get any external scripts
  var waitForVars = [];
  var scriptVars = [
                    { script: 'bb_htmlarea', variable: ['HTMLArea'] },
                    { script: 'w_editor', variable: ['WebeqEditors'] },
                    { script: 'wysiwyg.js', variable: ['vtbe_attchfiles'] },
                    { script: 'gradebook_utils.js', variable: ['gradebook_utils'] },
                    { script: 'rubric.js', variable: ['rubricModule'] },
                    { script: 'gridmgmt.js', variable: ['gridMgmt'] },
                    { script: 'calendar-time.js', variable: ['calendar'] },
                    { script: 'widget.js', variable: ['widget'] },
                    { script: 'vtbeTinymce.js', variable: ['tinyMceWrapper'] },
                    { script: 'WhatsNewView.js', variable: ['WhatsNewView'] },
                    { script: 'tiny_mce.js', variable: ['tinymce','tinyMCE'] },
                    { script: 'slider.js', variable: ['Control.Slider'] },
                    { script: 'drawer.js', variable: ['drawer'] },
                    { script: 'activeFilter.js', variable: ['activeFilter'] },
                    { script: 'inventoryList.js', variable: ['inventoryList'] }
                   ];
  if (evalExternalScripts)
  {
    var externalScriptRE = '<script[^>]*src=["\']([^>"\']*)["\'][^>]*>([\\S\\s]*?)<\/script>';
    var scriptMatches = str.match(new RegExp(externalScriptRE, 'img'));
    if (scriptMatches && scriptMatches.length > 0)
    {
      $A(scriptMatches).each(function(scriptTag)
      {
        var matches = scriptTag.match(new RegExp(externalScriptRE, 'im'));
        if (matches && matches.length > 0 && matches[1] != '')
        {
          var scriptSrc = matches[1];
          if (scriptSrc.indexOf('/dwr_open/') != -1)
          {
            // dwr_open calls will ONLY work if the current page's webapp == the caller's webapp,
            // otherwise we'll get a session error.  THis will happen if a lightbox is loaded with
            // dynamic content from a different webapp (say /webapps/blackboard) while the main page
            // is loaded from /webapps/discussionboard.  To avoid this, rewrite the url to use the
            // webapp associated with the current page.
            var newparts = scriptSrc.split('/');
            var oldparts = window.location.pathname.split('/');
            newparts[1] = oldparts[1];
            newparts[2] = oldparts[2];
            scriptSrc = newparts.join('/');
          }
          var scriptElem = new Element('script', {
            async: false,
            type: 'text/javascript',
            src: scriptSrc
          });
          var head = $$('head')[0];
          head.appendChild(scriptElem);

          for ( var i = 0; i < scriptVars.length; i++ )
          {
            if ( scriptSrc.indexOf( scriptVars[i].script ) != -1 )
            {
                 scriptVars[ i ].variable.each( function( s )
                {
                  waitForVars.push( s );
                } );
                break;
            }
          }
        }
      });
    }
  }
//Finding Comments in HTML Source Code Using Regular Expressions and replaces with empty value
//Example: <!-- <script>alert("welcome");</script>--> = ''
//So,that extractScripts won't find commented scripts to extract
//str =str.replace(new RegExp('\<![ \r\n\t]*(--([^\-]|[\r\n]|-[^\-])*--[ \r\n\t]*)\>', 'img'), '');
  page.delayAddExtractedScripts(str.extractScripts(), waitForVars, signalObject);
};

// Evaluate any inline script - delay a bit to give the scripts above time to load
// NOTE that this is not guaranteed to work - if there are delays loading and initializing
// the scripts required then code in these scripts might fail to find the required variables
// If it is for our code then updating waitForVars appropriately per script will work
page.delayAddExtractedScripts = function (scripts, waitForVars, signalObject)
{
  var count = 0;
  if (waitForVars.length === 0)
  {
    page.actuallyAddExtractedScripts(scripts, signalObject);
  }
  else
  {
  new PeriodicalExecuter( function( pe )
  {
    if ( count < 100 )
    {
      count++;
      if ( page.allVariablesDefined(waitForVars) )
      {
        page.actuallyAddExtractedScripts(scripts, signalObject);
        pe.stop();
      }
    }
    else // give up if it takes longer than 5s to load
    {
      page.actuallyAddExtractedScripts(scripts, signalObject);
      pe.stop();
    }
  }.bind(this), 0.05 );
  }
};

page.variableDefined = function (avar)
{

  if ( !window[avar] )
  {
    if (avar.indexOf('.') > 0)
    {
      var parts = avar.split('.');
      var obj = window[parts[0]];
      for (var partNum = 1; obj && partNum < parts.length; partNum++)
      {
        obj = obj[parts[partNum]];
      }
      if (obj)
      {
        return true;
      }
    }
    return false;
  }
  return true;
};
page.allVariablesDefined = function(vars)
{
  var result = true;
  for ( var i = 0; i < vars.length; i++ )
  {
    if ( !page.variableDefined(vars[i]) )
    {
      result = false;
      break;
    }
  }
  return result;
};

page.actuallyAddExtractedScripts = function (scripts, signalObject)
{
  var scriptExecutionDelay = 0;
  if( signalObject )
  {
    scriptExecutionDelay = signalObject.delayScriptExecution;
    if (scriptExecutionDelay === undefined)
    {
      scriptExecutionDelay = 0;
    }
  }
  scripts.each(function(script)
    {
      if ( script != '' )
      {
        if ( Prototype.Browser.IE && window.execScript )
        {
          ( function()
            {
              window.execScript( script );
            }.delay( scriptExecutionDelay ) );
        }
        else
        {
          ( function()
            {
              var scriptElem = new Element( 'script',
              {
                type : 'text/javascript'
              } );
              var head = $$( 'head' )[ 0 ];
              script = document.createTextNode( script );
              scriptElem.appendChild( script );
              head.appendChild( scriptElem );
              head.removeChild( scriptElem );
           }.delay( scriptExecutionDelay ) );
        }
      }
    }
  );
  if (signalObject)
  {
    if ( typeof( signalObject.evaluatingScripts ) === "function" )
    {
      ( function()
      {
    	signalObject.evaluatingScripts();
    	signalObject.evaluatingScripts = false; //reset function so another call will not unintentionally call this callback function.
      }.defer() );
    }
    else
    {
      signalObject.evaluatingScripts = false;
    }
  }
};

//When the browser page is resized, we use page.onResizeCleanSlateIframe( widthRate ) to determine the new width for the iframe.  
page.onResizeCleanSlateIframe = function( widthRatio )
{
  var iframeElement = $$('iframe.cleanSlate')[0];
  var iframeParentWidth = iframeElement.parentElement.scrollWidth;
  
  //We resize the iframe width according its parent element width(container width) and the ratio ( the ratio of iframe width to it's parent width).
  iframeElement.style.width = parseInt( iframeParentWidth * widthRatio) + 'px';
}

//Set iframe height and width for fitting the size of embedded content.
//This function is only called in method renderFileContentBody(...) in ContentRendererUtil.java when the iframe has been loaded.
page.setIframeHeightAndWidth = function ()
{
  try
  {
    var iframeElement = $$('iframe.cleanSlate')[0];
    
    // Set default height and width for the iframe so that if any un-handled situation happens in this function, 
    // the iframe still can be showed properly.
    var defaultHeight = 400;
    iframeElement.style.height = defaultHeight + 'px';
    iframeElement.style.width = '100%';
    
    // If the current url of iframe is not file content url, we don't set the height and width.
    if ( iframeElement.contentDocument.URL.indexOf("/bbcswebdav") === -1 )
    {
      return;
    }
    
    // Start to handle the situation which the embedded file content contains multiple iframes/frames.
    var iframeHeight = iframeElement.contentDocument.body.scrollHeight;
    var iframeWidth = iframeElement.contentDocument.body.scrollWidth
    var containedFrames = [iframeElement.contentDocument.getElementsByTagName("frame") ,
                           iframeElement.contentDocument.getElementsByTagName("iframe")];
    var isContainsMultiFrames = ( containedFrames[0].length === 0 && containedFrames[1].length === 0 ) ? false : true;
    
    if ( isContainsMultiFrames === true )
    {
      var maxSizeArray =  getMaxIframeContentSize();      
      iframeHeight = maxSizeArray.height;
      iframeWidth = maxSizeArray.width;
    }
    
    iframeElement.style.height = (iframeHeight > defaultHeight ? iframeHeight: defaultHeight) + 'px';
    iframeElement.style.width = iframeWidth + 'px';
      
    // As the iframe width is specified in 'px' above, causes the width would stay same when the browser page is resized, 
    // so we attach 'onresize' listener here and then use page.onResizeCleanSlateIframe( widthRate ) to determine the new width for the iframe.  
    var widthRatio = iframeWidth/iframeElement.parentElement.scrollWidth;  // record the ratio of iframe width to its parent element width (container width) for caculating the new width.
    Event.observe( window, 'resize', page.onResizeCleanSlateIframe( widthRatio ) );
  }
  catch(e){}
  
  // Go through all contained frames/Iframes for getting the max height and width.
  // TODO: Should handle iframeset in this function.
  function getMaxIframeContentSize()
  {
     var i = 0;
     var maxHeight = iframeHeight;
     var maxWidth = iframeWidth;
       
     try
     {
       for( i = 0; i < containedFrames.length; i++ )
       {
         var j = 0;           
         for( j = 0; j < containedFrames[i].length; j++ )
         {
            var h = containedFrames[i][j].contentWindow.document.body.scrollHeight;
            var w = containedFrames[i][j].contentWindow.document.body.scrollWidth; 
            maxHeight = ( maxHeight > h ) ? maxHeight : h;
            maxWidth = ( maxWidth > w ) ? maxWidth : w;
         }          
       }
     }
     catch (e){}
     
     return {height:maxHeight , width:maxWidth};
  }
};

page.onResizeChannelIframe = function( channelExtRef )
{
  var frameId = 'iframe' + channelExtRef;
  var listId = 'list_channel' + channelExtRef;
  var f = $( frameId );
  var fli = f.contentWindow.document.getElementById( listId );
  if (fli)
  {
    f.style.height = fli.scrollHeight + 15 + "px";
  }
};

/**
 * Contains page-wide utility methods
 */
page.util = {};

/**
 * Returns whether the specific element has the specified class name.
 * Same as prototype's Element.hasClassName, except it doesn't extend the element (which is faster in IE).
 */
page.util.hasClassName = function ( element, className )
{
  var elementClassName = element.className;
  if ((typeof elementClassName == "undefined") || elementClassName.length === 0)
  {
    return false;
  }
  if (elementClassName == className ||
      elementClassName.match(new RegExp("(^|\\s)" + className + "(\\s|$)")))
  {
    return true;
  }

  return false;
};

page.util.fireClick = function ( elem )
{
  if (Prototype.Browser.IE)
  {
    elem.fireEvent("onclick");
  }
  else
  {
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", true, true);
    elem.dispatchEvent(evt);
  }
};

page.util.useARIA = function ()
{  
  if (/Firefox[\/\s](\d+\.\d+)/.test( navigator.userAgent )) // test for Firefox/x.x or Firefox x.x (ignoring remaining digits);
  {
    var ffversion = parseFloat( RegExp.$1 ); // capture x.x portion and store as a number
    if ( ffversion >= 1.9 )
    {
      return true;
    }
  }
  else if (/MSIE (\d+\.\d+);/.test( navigator.userAgent )) // test for older MSIE x.x;    
  {
    var ieversion = parseFloat( RegExp.$1 ); // capture x.x portion and store as a number
    if (ieversion >= 8)
    {
      return true;
    }
  }
  else if (/Trident\/.*rv[ :]([\d\.]+)/.test( navigator.userAgent )) //test for new MSIE (11 and higher)     
  {
    var ieversion = parseFloat( RegExp.$1 ); // capture x.x portion and store as a number
    if (ieversion >= 11) 
    {
      return true;
    }
  }
  return false;
};

// Find an element with the given className, starting with the element passed in
page.util.upToClass = function ( element, className )
{
  while (element && !page.util.hasClassName(element, className))
  {
    element = element.parentNode;
  }
  return $(element);
};

page.util.isRTL = function ()
{
  var els = document.getElementsByTagName("html");
  var is_rtl = (typeof(els) != 'undefined' &&
          els && els.length == 1 && els[0].dir == 'rtl' );
  return is_rtl ;
};

page.util.allImagesLoaded = function (imgList)
{
  var allDone = true;
  if (imgList)
  {
    for ( var i = 0, c = imgList.length; i < c; i++ )
    {
      var animg = imgList[i];
      // TODO - this doesn't appear to work on IE.
      if ( !animg.complete )
      {
        allDone = false;
        break;
      }
    }
  }
  return allDone;
};

// Exposes (display but keep invisible) an invisible element for measurement
// recursively traverses up the DOM looking for
// a parent node of element whose display == 'none'
// If found, sets its style to: display:block, position:absolute, and visibility:hidden
// and saves it as element.hiddenNode so it can be easily unexposed
page.util.exposeElementForMeasurement = function ( element )
{
  element = $(element);
  var e = element;
  var hiddenNode;
  // find parent node that is hidden
  while ( !hiddenNode && e && e.parentNode)
  {
    if ( $(e).getStyle('display') === 'none')
    {
      hiddenNode = $(e);
    }
    e = $(e.parentNode);
  }
  if ( hiddenNode )
  {
    // save original style attributes: visibility, position, & display
    element.hiddenNode = hiddenNode;
    var style = hiddenNode.style;
    var originalStyles = {
                          visibility: style.visibility,
                          position:   style.position,
                          display:    style.display
                        };
    var newStyles = {
                     visibility: 'hidden',
                     display:    'block'
                   };

     if (originalStyles.position !== 'fixed')
     {
       newStyles.position = 'absolute';
     }
     hiddenNode.originalStyles = originalStyles;
     // set new style for: visibility, position, & display
     hiddenNode.setStyle( newStyles );
  }

};

// undo previous call to exposeElementForMeasurement
page.util.unExposeElementForMeasurement = function ( element )
{
  element = $(element);
  if ( element && element.hiddenNode && element.hiddenNode.originalStyles )
  {
    Element.setStyle( element.hiddenNode, element.hiddenNode.originalStyles );
    element.hiddenNode.originalStyles = null;
    element.hiddenNode = null;
  }

};


/**
 * Returns whether any part of the two elements overlap each other.
 */
page.util.elementsOverlap = function ( e1, e2 )
{
  var pos1 = $(e1).cumulativeOffset();
  var a = { x1: pos1.left, y1: pos1.top, x2: pos1.left + e1.getWidth(), y2: pos1.top + e1.getHeight() };
  var pos2 = $(e2).cumulativeOffset();
  var b = { x1: pos2.left, y1: pos2.top, x2: pos2.left + e2.getWidth(), y2: pos2.top + e2.getHeight() };

  return a.x1 < b.x2 && a.x2 > b.x1 && a.y1 < b.y2 && a.y2 > b.y1;
};
/**
 *  To handle the case where the focus is visible but too close to the
    bottom of the page, scroll the page up a bit.
    Note: when using scrollbar.js, use scrollBar.scrollTo() rather than focusAndScroll
*/

page.util.focusAndScroll= function(elem)
{
  elem.focus();

  return page.util.ensureVisible(elem);
};

page.util.ensureVisible= function(elem)
{
  var scrolltop = document.viewport.getScrollOffsets().top;
  var mytop = elem.cumulativeOffset()[1];
  var height = document.viewport.getDimensions().height;
  var realtop = mytop - scrolltop;
  var thirty = height * 0.3;
  if (realtop > (height-thirty))
  {
    var scrollDistance = realtop - thirty;
    window.scrollBy(0,scrollDistance);
  }
  return false;
};

page.util.processJSProtoString = function (string, checkToken) {
  // This value must match the value passed as the 2nd parameter to this string.
  // The goal is to pass a known value, as a constant, through the javascript: pseudo-protocol
  // handler. We can then examine the result to determine the decoding method used by the current
  // browser.
  var sniffToken = '%C3%A9';

  // There are three known decoding cases, non-translated, UTF8, and unescape
  if (checkToken === unescape(sniffToken)) {
    // Unescape decoded
    return decodeURIComponent(escape(string));
  } else if (checkToken === sniffToken) {
    // Non-translated
    return decodeURIComponent(string);
  } else {
    // UTF8 Decoded/Unknown
    return string;
  }
};

/**
 * Find the first action bar that precedes sourceElement
 * Returns the action bar div element if found, null otherwise
 *
 * @param sourceElement
 */
page.util.findPrecedingActionBar = function( sourceElement )
{
  var actionBar = null;
  // Loop through each ancestor of sourceElement,
  // starting with parent, until an action bar is found
  sourceElement.ancestors().each( function( item )
  {
    actionBar = item.previous('div.tabActionBar') ||
                item.previous('div.actionBarMicro') ||
                item.previous('div.actionBar');
    if (actionBar)
    {
      throw $break;
    }
  });
  return actionBar;
};

page.util.getLargestDimensions = function (element)
{
  var width = 0;
  var height = 0;
  var dim;
  while (element != document)
  {
    dim = $(element).getDimensions();
    if (dim.width > width)
    {
      width = dim.width;
    }
    if (dim.height > height)
    {
      height = dim.height;
    }
    element = element.up();
  }
    return { width: width, height: height };
};

/*
 * Resize the current window so that it will fit the largest dimensions found for the given element.
 * Will also reposition on the screen if required to fit.  Will not size larger than the screen.
 * NOTE that this will only work for popup windows - main windows are typically in a tabset in
 * the browser and they don't allow resizing like this.  That's OK because the main use case
 * for this method is to make sure popup windows are resized appropriately for their content.
 */
page.util.resizeToContent = function (startElement)
{
    var dim = page.util.getLargestDimensions(startElement);
    var newWidth = dim.width;
    newWidth += 25; // TODO: Haven't figured out why I need this extra space yet...
    if (window.innerWidth > newWidth)
    {
      newWidth = window.innerWidth;
    }
    if (newWidth > screen.width)
    {
      newWidth = screen.width;
    }

    var newHeight = dim.height;
    newHeight += 100; // TODO: Haven't figured out why I need this extra space yet
    if (window.innerHeight > newHeight)
    {
      newHeight = window.innerHeight;
    }
    if (newHeight > screen.height)
    {
      newHeight = screen.height;
    }

    var left = 0;
    var top = 0;
    if ( window.screenLeft )
    {
      left = window.screenLeft;
      top = window.screenTop;
    }
    else if ( window.screenX )
    {
      left = window.screenX;
      top = window.screenY;
    }
    if (left + newWidth > screen.width)
    {
      left = screen.width - newWidth;
      if (left < 0)
      {
        left = 0;
      }
    }
    if (top + newHeight > screen.height)
    {
      top = screen.height - newHeight;
      if (top < 0)
      {
        top = 0;
      }
    }
    window.moveTo(left,top);
    window.resizeTo(newWidth,newHeight);
};

/**
 * Sets the css position of all li elements that are contained in action bars on the page.
 * Since z-index only works on positioned elements, this function can be used to ensure that
 * divs with a higher z-index will always appear on top of any action bars on the page.
 *
 * @param cssPosition
 */
page.util.setActionBarPosition = function( cssPosition )
{
  $$( 'div.actionBar',
      'div.tabActionBar',
      'div.actionBarMicro' ).each( function( actionbar )
  {
    actionbar.select( 'li' ).each( function( li )
    {
      li.setStyle( {position: cssPosition} );
    });
  });
};


/**
 * We have many anchor elements with a role of 'button' that present something visually looking like a button but that
 * do not respond to 'space' to press them as you would expect of a button. This will setup space to fire the click
 * event on any element with a role of 'button'.
 */
page.GlobalBehaviour = Class.create();
page.GlobalBehaviour.prototype =
{
  initialize : function()
  {
    Event.observe( window, 'keydown', this.handleKeyDown.bindAsEventListener( this ) );

  },
  handleKeyDown : function( event )
  {
    var code = event.keyCode || event.which;
    var elem = event.element();
    if ( code == 32 && elem.nodeName.toUpperCase() === 'A') // space on an anchor tag.
    {
      var role = elem.getAttribute("role");
      if (role && role == "button")
      {
        page.util.fireClick( elem );
        $(event).preventDefault();
      }
    }
  }
};
/** We want this behaviour everywhere so establish it immediately.*/
page.theGlobalBehaviour = new page.GlobalBehaviour();

/**
 * Class for controlling the course menu-collapser.  Also ensures the menu is
 * the right height
 */
page.PageMenuToggler = Class.create();
page.PageMenuToggler.prototype =
{
  /**
   * initialize
   */
  initialize: function( isMenuOpen,key,temporaryScope )
  {
    page.PageMenuToggler.toggler = this;
    this.key = key;
    if (temporaryScope)
    {
      this.temporaryScope = temporaryScope;
    }
    else
    {
      this.temporaryScope = false;
    }
    this.isMenuOpen = isMenuOpen;
    this.puller = $('puller');
    this.menuPullerLink = $(this.puller.getElementsByTagName('a')[0]);
    this.menuContainerDiv = $('menuWrap');
    this.navigationPane = $('navigationPane');
    this.contentPane = $('contentPanel') || $('contentPane');
    this.navigationPane = $('navigationPane');
    this.locationPane = $(this.navigationPane.parentNode);
    this.breadcrumbBar = $('breadcrumbs');

    this.menu_pTop = parseInt(this.menuContainerDiv.getStyle('paddingTop'), 10);
    this.menu_pBottom = parseInt(this.menuContainerDiv.getStyle('paddingBottom'), 10);
    this.loc_pTop = parseInt(this.locationPane.getStyle('paddingTop'), 10);

    if ( this.breadcrumbBar )
    {
      this.bc_pTop = parseInt(this.breadcrumbBar.getStyle('paddingTop'), 10);
      this.bc_pBottom = parseInt(this.breadcrumbBar.getStyle('paddingBottom'), 10);
    }
    else
    {
      this.bc_pTop = 0;
      this.bc_pBottom = 0;
    }

    this.toggleListeners = [];
    this.onResize( null );  // fix the menu size

    // Doesn't work in IE or Safari..
    //Event.observe( window, 'resize', this.onResize.bindAsEventListener( this ) );
    Event.observe( this.menuPullerLink, 'click', this.onToggleClick.bindAsEventListener( this ) );
  },

  /**
   * Adds a listener for course menu toggle events
   */
  addToggleListener: function( listener )
  {
    this.toggleListeners.push( listener );
  },

  /**
   * Notifies all registered toggle event listeners that a toggle has occurred.
   */
  _notifyToggleListeners: function( isOpen )
  {
    this.toggleListeners.each( function( listener )
    {
      listener( isOpen );
    });
  },

  notifyToggleListeners: function( isOpen )
  {
    // we call once the toggle is complete and the DOM in its new state. 2012 themes add transition, which seems
    // to collide with the logic to get dimensions of dom element, so the delay is a 1 sec to let time for those
    // transitions to be done.
    this._notifyToggleListeners.bind( this, isOpen ).delay( 1 );
  },
  /**
   * getAvailableResponse
   */
  getAvailableResponse : function ( req  )
  {
    var originalMenuOpen = this.isMenuOpen ;
    if ( req.responseText.length > 0 )
    {
      if ( req.responseText == 'true' )
      {
        this.isMenuOpen = true;
      }
      else
      {
        this.isMenuOpen = false;
    }
    }

    if ( originalMenuOpen != this.isMenuOpen )
    {
      this.notifyToggleListeners( this.isMenuOpen );
      this.menuContainerDiv.toggle();
      this.puller.toggleClassName("pullcollapsed");
      this.contentPane.toggleClassName("contcollapsed");
      this.navigationPane.toggleClassName("navcollapsed");
    }
  },



  /**
   * Expands the menu.  This can be used instead of toggling to explicitly
   * change the visibility of the menu.
   */
  expand : function ()
  {
    this.menuContainerDiv.show();
    this.puller.removeClassName("pullcollapsed");
    this.contentPane.removeClassName("contcollapsed");
    this.navigationPane.removeClassName("navcollapsed");

    this.isMenuOpen = true;

    var msg = page.bundle.messages[ "coursemenu.hide" ];
    this.menuPullerLink.title = msg;
    $('expander').alt = msg;

    this.notifyToggleListeners( true );
    if (this.temporaryScope)
    {
      UserDataDWRFacade.setStringTempScope( this.key, true );
    }
    else
    {
      UserDataDWRFacade.setStringPermScope( this.key, true );
    }
  },

  /**
   * Collapses the menu.  This can be used instead of toggling to explicitly
   * change the visibility of the menu.
   */
  collapse : function ()
  {
    this.menuContainerDiv.hide();
    this.puller.addClassName("pullcollapsed");
    this.contentPane.addClassName("contcollapsed");
    this.navigationPane.addClassName("navcollapsed");

    this.isMenuOpen = false;

    var msg = page.bundle.messages[ "coursemenu.show" ];
    this.menuPullerLink.title = msg;
    $('expander').alt = msg;

    this.notifyToggleListeners( false );
    if (this.temporaryScope)
    {
      UserDataDWRFacade.setStringTempScope( this.key, false );
    }
    else
    {
      UserDataDWRFacade.setStringPermScope( this.key, false );
    }
  },

  /**
   * Event triggered when the puller toggle control is clicked.  Changes the
   * menu from open to closed or closed to open depending on existing state.
   */
  onToggleClick: function( event )
  {
    if ( this.isMenuOpen )
    {
      this.collapse();
    }
    else
    {
      this.expand();
    }
    Event.stop( event );
  },

  /**
   * onResize
   */
  onResize: function( event )
  {
      var menuHeight = this.menuContainerDiv.getHeight();
      var contentHeight = this.contentPane.getHeight();
      var maxHeight = ( menuHeight > contentHeight ) ? menuHeight : contentHeight;
      this.contentPane.setStyle({height: maxHeight + 'px'});
      this.navigationPane.setStyle({height: maxHeight + 'px'});
  }
};
page.PageMenuToggler.toggler = null;

/**
 *  Class for controlling the page help toggler in the view toggle area
 */
page.PageHelpToggler = Class.create();
page.PageHelpToggler.prototype =
{
  initialize: function( isHelpEnabled, showHelpText, hideHelpText, assumeThereIsHelp )
  {
    page.PageHelpToggler.toggler = this;
    this.toggleListeners = [];
    this.isHelpEnabled = isHelpEnabled;
    this.showText = showHelpText;
    this.hideText = hideHelpText;
    this.contentPanel = $('contentPanel') || $('contentPane');
    var helperList = [];
    if ( this.contentPanel && !assumeThereIsHelp)
    {
      var allElems = [];
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('p') ) );
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('div') ) );
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('li') ) );
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('span') ) );
      for ( var i = 0; i < allElems.length; i++ )
      {
        var el = allElems[i];
        if ( page.util.hasClassName( el, 'helphelp' ) ||
             page.util.hasClassName( el, 'stepHelp' ) ||
             page.util.hasClassName( el, 'taskbuttonhelp' ) ||
             page.util.hasClassName( el, 'pageinstructions' ) )
        {
          helperList.push( $(el) );
        }
      }
    }

    var helpTextToggleLink = $('helpTextToggleLink');
    if ( ( !helperList || helperList.length === 0) && !assumeThereIsHelp )
    {
      if ( helpTextToggleLink )
      {
        helpTextToggleLink.remove();
      }
    }
    else
    {
      if ( !isHelpEnabled )
      {
        helperList.invoke( "toggle" );
      }

      if ( !this.showText )
      {
        this.showText = page.bundle.getString("viewtoggle.editmode.showHelp");
      }

      if ( !this.hideText )
      {
        this.hideText = page.bundle.getString("viewtoggle.editmode.hideHelp");
      }

      helpTextToggleLink.style.display = 'inline-block';
      this.toggleLink = helpTextToggleLink;
      this.toggleImage = $(this.toggleLink.getElementsByTagName('img')[0]);
      Event.observe( this.toggleLink, "click", this.onToggleClick.bindAsEventListener( this ) );
      $(this.toggleLink.parentNode).removeClassName('hidden');
      this.updateUI();
    }
  },

  addToggleListener: function( listener )
  {
    this.toggleListeners.push( listener );
  },

  _notifyToggleListeners: function()
  {
    this.toggleListeners.each( function( listener )
    {
      listener( this.isHelpEnabled );
    });
  },

  notifyToggleListeners: function()
  {
    // we notify once the whole menu collapse/expand is done, so the DOM is in final state
    this._notifyToggleListeners.bind( this ).delay( );
  },


  updateUI: function( )
  {
    if ( this.isHelpEnabled )
    {
      $("showHelperSetting").value = 'true';
      this.toggleImage.src = "/images/ci/ng/small_help_on2.gif";
      this.toggleLink.setAttribute( "title", this.showText );
      this.toggleImage.setAttribute( "alt", this.showText );
    }
    else
    {
      $("showHelperSetting").value = 'false';
      this.toggleImage.src = "/images/ci/ng/small_help_off2.gif";
      this.toggleLink.setAttribute( "title", this.hideText );
      this.toggleImage.setAttribute( "alt", this.hideText );
    }
  },

  toggleHelpText : function( isEnabled )
  {
    if ( this.contentPanel )
    {
      var allElems = [];
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('p') ) );
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('div') ) );
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('li') ) );
      allElems = allElems.concat( $A(this.contentPanel.getElementsByTagName('span') ) );

      for ( var i = 0; i < allElems.length; i++ )
      {
        var el = allElems[i];
        if ( page.util.hasClassName( el, 'helphelp' ) ||
             page.util.hasClassName( el, 'stepHelp' ) ||
             page.util.hasClassName( el, 'taskbuttonhelp' ) ||
             page.util.hasClassName( el, 'pageinstructions' ) )
        {
          if(isEnabled) //if we upgrade to prototype 7.1, then we can just do $(el).toggle(isEnabled)
          $(el).show();
          else
          $(el).hide();
        }
      }
    }
  },

  onToggleClick: function( event )
  {
    // Toggle all elements that have the css class "helphelp"
    var helperList = [];

    if ( this.isHelpEnabled )
    {
      this.isHelpEnabled = false;
      UserPageInstructionsSettingDWRFacade.setShowPageInstructions( "false" );
    }
    else
    {
      this.isHelpEnabled = true;
      UserPageInstructionsSettingDWRFacade.setShowPageInstructions( "true" );
    }
    //after updating isHelpEnabled value, actually change the text visibility
    this.toggleHelpText( this.isHelpEnabled );

    this.updateUI();
    this.notifyToggleListeners();
    Event.stop( event );
  }
};

/**
 * Class for controlling the display of a context menu.
 */
page.ContextMenu = Class.create();
page.ContextMenu.prototype =
{
  initialize: function( contextMenuContainer, divId, forceMenuRefresh )
  {
    this.displayContextMenuLink = contextMenuContainer.down("a.cmimg");
    // in grade-center screen reader mode 'cmimg' class is removed.
    // insertAccessibleContextMenu method in gradebookgrid_cellctrl.js removes the 'cmimg' class
    if ( !this.displayContextMenuLink )
    {
      this.displayContextMenuLink = contextMenuContainer.down('a');
    }
    this.contextMenuContainer = contextMenuContainer;
    this.forceMenuRefresh = forceMenuRefresh;
    this.uniqueId = this.displayContextMenuLink.id.split('_')[1];
    this.contextMenuDiv = this.displayContextMenuLink.savedDiv;
    if ( !this.contextMenuDiv )
    {
      this.contextMenuDiv = contextMenuContainer.down("div");//$('cmdiv_' + this.uniqueId);
      this.displayContextMenuLink.savedDiv = this.contextMenuDiv;
      page.ContextMenu.hiddenDivs.set(divId,this.contextMenuDiv);
    }

    this.originalContextMenuDiv = this.contextMenuDiv.cloneNode(true);
    $(this.contextMenuDiv).setStyle({zIndex: 200});
    this.displayContextMenuLink.appendChild( this.contextMenuDiv ); // Temporarily add the menu back where it started
    this.closeContextMenuLink = contextMenuContainer.down(".contextmenubar_top").down(0);
    this.contextParameters = contextMenuContainer.readAttribute("bb:contextParameters");
    this.menuGeneratorURL = contextMenuContainer.readAttribute("bb:menuGeneratorURL");
    this.nav = contextMenuContainer.readAttribute("bb:navItem");
    this.enclosingTableCell = contextMenuContainer.up("td");
    this.menuOrder = contextMenuContainer.readAttribute("bb:menuOrder");
    this.overwriteNavItems = contextMenuContainer.readAttribute("bb:overwriteNavItems");
    this.beforeShowFunc = contextMenuContainer.readAttribute("bb:beforeShowFunc");
    if (this.beforeShowFunc)
    {
      this.beforeShowFunc = eval(this.beforeShowFunc);
    }

    if ( this.menuOrder )
    {
      this.menuOrder = this.menuOrder.split(',');
    }

    if ( !this.contextParameters )
    {
      this.contextParameters = "";
    }

    if ( !this.menuGeneratorURL )
    {
      this.menuGeneratorURL = "";
    }

    if ( !this.nav )
    {
      this.nav = "";
    }

    this.dynamicMenu = false;

    if ( this.menuGeneratorURL )
    {
      this.dynamicMenu = true;
    }

    if (this.dynamicMenu)
    {
      Event.observe( this.displayContextMenuLink, "click", this.generateDynamicMenu.bindAsEventListener( this ) );
    }
    else
    {
      Event.observe( this.displayContextMenuLink, "click", this.onDisplayLinkClick.bindAsEventListener( this ) );
    }

    Event.observe( this.closeContextMenuLink, "click", this.onCloseLinkClick.bindAsEventListener( this ) );
    Event.observe( this.contextMenuDiv, "keydown", this.onKeyPress.bindAsEventListener( this ) );

    // adding nowrap to table cell containing context menu
    // If no enclosing td is found, try th
    if ( !this.enclosingTableCell )
    {
      this.enclosingTableCell = contextMenuContainer.up("th");
    }

    if ( this.enclosingTableCell )
    {
      if ( !this.enclosingTableCell.hasClassName("nowrapCell") )
      {
        this.enclosingTableCell.addClassName("nowrapCell");
      }

      // if label tag is an immediate parent of context menu span tag, it needs nowrap as well
      if ( this.enclosingTableCell.down("label") && !this.enclosingTableCell.down("label").hasClassName("nowrapLabel"))
      {
        this.enclosingTableCell.down("label").addClassName("nowrapLabel");
      }
    }

    if ( !this.dynamicMenu )
    {
      var contexMenuItems = contextMenuContainer.getElementsBySelector("li > a").each( function (link )
      {
        if ( !link.up('li').hasClassName("contextmenubar_top") )
        {
          Event.observe( link, 'focus', this.onAnchorFocus.bindAsEventListener( this ) );
          Event.observe( link, 'blur', this.onAnchorBlur.bindAsEventListener( this ) );
        }
      }.bind( this ) );
    }

    this.useARIA = page.util.useARIA();

    // remove the context menu div from the page for performance reasons - add it back when we need to show it
    Element.remove( this.contextMenuDiv );
  },

  onKeyPress: function( event )
  {
    var elem, children, index;
    var key = event.keyCode || event.which;
    if ( key == Event.KEY_UP )
    {
      elem = Event.element ( event );
      children = this.contextMenuDiv.getElementsBySelector("li > a");
      index = children.indexOf( elem );
      if ( index > 0 )
      {
        children[index - 1].focus();
      }
      Event.stop( event );
    }
    else if ( key == Event.KEY_DOWN )
    {
      elem = Event.element ( event );
      children = this.contextMenuDiv.getElementsBySelector("li > a");
      index = children.indexOf( elem );
      if ( index < ( children.length - 1 ) )
      {
        children[index + 1].focus();
      }
      Event.stop( event );
    }
    else if ( key == Event.KEY_ESC )
    {
      this.close();
      this.displayContextMenuLink.focus();
      Event.stop( event );
    }
    else if ( key == Event.KEY_TAB )
    {
      elem = Event.element ( event );
      children = this.contextMenuDiv.getElementsBySelector("li > a");
      index = children.indexOf( elem );
      if ( (!event.shiftKey && index == children.length - 1) || (event.shiftKey && index === 0))
      {
        this.close();
        this.displayContextMenuLink.focus();
        Event.stop( event );
      }
    }
    else if ( key == Event.KEY_RETURN )
    {
      if ( this.useARIA )
      {
        elem = Event.element ( event );
        (function() { page.util.fireClick( elem ); }.bind(this).defer());
        Event.stop( event );
      }
    }
  },

  onAnchorFocus: function ( event )
  {
    Event.element( event ).setStyle({ backgroundColor: '#FFFFFF' });
  },

  onAnchorBlur: function( event )
  {
    Event.element( event ).setStyle({ backgroundColor: '' });
  },

  afterMenuGeneration: function( req )
  {
    if ( this.dynamicMenu )
    {
      var result;
      this.dynamicMenu =  this.forceMenuRefresh;
      try
      {
        result = req.responseText.evalJSON( true );
        if ( result.success == "true" )
        {
          // append uniqueId to each li
          var menuHTML = result.contentMenuHTMLList.replace(/(<li.*?id=")(.*?)(".*?>)/g,"$1$2_"+this.uniqueId+"$3");
          if ( this.forceMenuRefresh )
          {
             this.contextMenuDiv.innerHTML = this.originalContextMenuDiv.innerHTML;
          }
          this.contextMenuDiv.insert({bottom:menuHTML});
          $A(this.contextMenuDiv.getElementsByTagName("ul")).each( function( list, index )
          {
            list.id = 'cmul'+index+'_'+this.uniqueId;
          }.bind(this) );
          var contexMenuItems = this.contextMenuDiv.getElementsBySelector("li > a").each( function (link )
          {
            if ( !link.up('li').hasClassName("contextmenubar_top") )
            {
              Event.observe( link, 'focus', this.onAnchorFocus.bindAsEventListener( this ) );
              Event.observe( link, 'blur', this.onAnchorBlur.bindAsEventListener( this ) );
             }
          }.bind( this ) );
        }
        else
        {
          new page.InlineConfirmation("error", result.errorMessage, false );
        }
      }
      catch ( e )
      {
         new page.InlineConfirmation("error", result.errorMessage, false );
      }
    }

    this.showMenu();
    //focus on the first menu item
    (function() { this.contextMenuDiv.down("a").focus(); }.bind(this).defer());
  },

  appendItems: function( items, menuItemContainer )
  {
    if (!menuItemContainer)
    {
      var uls = this.contextMenuDiv.getElementsBySelector("ul");
      menuItemContainer = uls[uls.length-1];
    }

    items.each( function ( item )
    {
      if ( item.type == "seperator" )
      {
        if (menuItemContainer.getElementsBySelector("li").length === 0)
        {
          return;
        }
        var ul = new Element('ul');
        menuItemContainer.parentNode.appendChild( ul );
        menuItemContainer = ul;
        return;
      }
      if ( !this.menuItemTempate )
      {
        var menuItems = this.contextMenuDiv.getElementsBySelector("li");
        this.menuItemTempate = menuItems[menuItems.length-1];
      }
      var mi = this.menuItemTempate.cloneNode( true );
      var a  =  mi.down('a');
      var name = item.key ? page.bundle.getString( item.key ) : item.name ? item.name : "?";
      a.update( name );
      a.title = item.title ? item.title : name;
      a.href = "#";
      menuItemContainer.appendChild( mi );
      Event.observe( a, 'focus', this.onAnchorFocus.bindAsEventListener( this ) );
      Event.observe( a, 'blur', this.onAnchorBlur.bindAsEventListener( this ) );
      Event.observe( a, 'click', this.onItemClick.bindAsEventListener( this, item.onclick, item.doNotSetFocusOnClick ) );
    }.bind( this ) );

  },

  onItemClick: function( evt, func, doNotSetFocusOnClick )
  {
    this.onCloseLinkClick( evt, doNotSetFocusOnClick );
    func();
  },

  setItems: function( items )
  {
    // rather than try to match up new items with existing items, it's easier to delete the existing items
    // (except for the close item) and then add the new items

    // remove existing menu items, except close menu
    var menuItems = this.contextMenuDiv.getElementsBySelector("li").each( function (li )
    {
      if ( !li.hasClassName("contextmenubar_top") )
      {
        if (!this.menuItemTempate)
        {
          this.menuItemTempate = li;
        }
        li.stopObserving();
        li.remove();
      }
    }.bind( this ) );

    // should only be one menuItemContainer
    var menuItemContainers = this.contextMenuDiv.getElementsBySelector("ul").each( function (ul)
    {
      if ( !ul.down("li") )
      {
        ul.remove();
      }
    }.bind( this ) );

    this.appendItems(items, menuItems[0].parentNode);
  },

  showMenu : function()
  {
    if (this.beforeShowFunc)
    {
      this.beforeShowFunc(this);
    }
    page.ContextMenu.registerContextMenu( this );
    this.reorderMenuItems();
    if ( this.useARIA )
    {
      this.initARIA();
    }
    var offset = this.displayContextMenuLink.cumulativeOffset();
    var scrollOffset = this.displayContextMenuLink.cumulativeScrollOffset();
    var viewportScrollOffset = document.viewport.getScrollOffsets();
    if ( this.displayContextMenuLink.up( 'div.lb-content' ) )
    {
      // Fix offset for context menu link inside a lightbox
      offset[0] = offset[0] + viewportScrollOffset[0];
      offset[1] = offset[1] + viewportScrollOffset[1];
    }
    else
    {
      // Fix the offset if the item is in a scrolled container
      offset[0] = offset[0] - scrollOffset[0] + viewportScrollOffset[0];
      offset[1] = offset[1] - scrollOffset[1] + viewportScrollOffset[1];
    }
    document.body.appendChild( this.contextMenuDiv );
    this.contextMenuDiv.setStyle({display: "block"});
    var width = this.contextMenuDiv.getWidth();
    var bodyWidth = $(document.body).getWidth();

    if ( page.util.isRTL() )
    {
      offset[0] = offset[0] + this.displayContextMenuLink.getWidth() - width;
    }

    if ( offset[0] + width > bodyWidth )
    {
      offset[0] = offset[0] - width + 30;
    }

    if ( this.keepMenuToRight )
    {
      // In case the link is very wide (i.e. gradecenter accessible mode cell link for really wide cell)
      // make sure the menu renders to the right side of the link
      var linkWidth = this.displayContextMenuLink.getDimensions().width;
      if (linkWidth > width)
      {
        // Only worry if the link is actually wider than the menu
        offset[0] += (linkWidth-width);
      }
    }

    // Don't start the menu off the left side of the window
    if ( offset[0] < 0 )
    {
      offset[0] = 0;
    }

    var height = this.contextMenuDiv.getHeight();
    var bodyHeight = $(document.body).getHeight();
    if (bodyHeight === 0)
    {
      // TODO This is kindof a hack since body height == 0 on a stream page, but we hacked in a special case for
      // lb-content above so it isn't entirely unheard of... would just be nicer to make this bodyheight choice
      // determined by the calling page rather than trial and error...
      var streamDiv = this.displayContextMenuLink.up( 'div.stream_full' );
      if (streamDiv)
      {
        bodyHeight = streamDiv.getHeight();
      }
    }
    var ypos = offset[1] + this.displayContextMenuLink.getHeight() + 17;
    if ( ( height + ypos ) > bodyHeight )
    {
      ypos -= height;
      ypos -= 34;
    }
    // Don't start the menu off the top of the screen
    if (ypos < 0 )
    {
      ypos = 0;
    }
    if (height > bodyHeight)
    {
      // If the menu is too big to fit on the screen, set it to the height of the screen and allow scrollbars inside the menu
      this.contextMenuDiv.setStyle({ height: bodyHeight + "px", overflowY: "auto", overflowX: "hidden", left: offset[0] + "px", top: ypos + "px" });
    }
    else
    {
      this.contextMenuDiv.setStyle({ left: offset[0] + "px", top: ypos + "px"});
    }
    if ( !this.shim )
    {
      this.shim = new page.popupShim( this.contextMenuDiv );
    }
    this.shim.open();
  },

  initARIA: function()
  {
    if ( !this.initializedARIA )
    {
      this.displayContextMenuLink.setAttribute( "aria-haspopup", "true" );
      this.displayContextMenuLink.setAttribute( "role", "menubutton" );
      this.contextMenuDiv.down( "ul" ).setAttribute( "role", "menu" );
      $A( this.contextMenuDiv.getElementsByTagName('a') ).each ( function( link )
      {
        if( !link.hasClassName("close-menu") ) {
          link.setAttribute( "role", "menuitem" );
          link.parentNode.setAttribute( "role", "presentation" );
        }
        if ( !link.href.include("#") )
        {
          Event.observe( link, 'click', function() {
            if ( this.ohref.toLowerCase().startsWith("javascript") )
            {
              eval( decodeURIComponent(this.ohref) );
            }
            else
            {
              if ( this.target )
              {
                window.open( this.ohref, this.target );
              }
              else
              {
                window.location = this.ohref;
              }
            }
          } );
          link.ohref = link.href;
          link.removeAttribute( "href" );
          link.tabIndex = "0";
          link.setStyle( {cursor: 'pointer'} ); // make it look like a link.
        }
      });
      this.initializedARIA = true; // Only initialize once.
    }
  },

  reorderMenuItems : function()
  {
    if ( !this.menuOrder || this.menuOrder.length < 2 )
    {
      return;
    }

    var orderMap = {};
    var closeItem = null;
    var extraItems = [];  // items not in order

    // Gather up all of the <li> tags in the menu and stick them in a map/object of id to the li object
    $A(this.contextMenuDiv.getElementsByTagName("li")).each( function( listItem )
    {
      if (listItem.hasClassName("contextmenubar_top"))
      {
        closeItem = listItem;
      }
      else
      {
        if (this.menuOrder.indexOf(listItem.id) > -1)
        {
          orderMap[listItem.id] = listItem;  // add item to map
        }
        else
        {
          extraItems.push(listItem); // listItem id not specified in menuOrder, so add listItem to extraItems
        }
      }
    }.bind(this) );

    // Remove all the content from the context menu div
    $A(this.contextMenuDiv.getElementsByTagName("ul")).each( function( list )
    {
      Element.remove(list);
    }.bind(this) );

    // Re-add the special "close" item as the first item.
    var ulElement = $(document.createElement("ul"));
    if ( this.useARIA )
    {
      ulElement.setAttribute('role','presentation');
    }
    this.contextMenuDiv.insert({bottom:ulElement});
    ulElement.insert({bottom:closeItem});

    // Loop through the order, adding a <ul> at the start, and starting a new <ul> whenever a "*separator*"
    //  is encountered, and adding the corresponding <li> for each of the ids in the order using the map/object
    this.menuOrder.each( function( id )
    {
      if (id == "*separator*")
      {
        ulElement = $(document.createElement("ul"));
        if ( this.useARIA )
        {
          ulElement.setAttribute('role','presentation');
        }
        this.contextMenuDiv.insert({bottom:ulElement});
      }
      else
      {
        ulElement.insert({bottom:orderMap[id]});
      }
    }.bind(this) );


    // Add any extraItems to thier own ul
    if (extraItems.length > 0)
    {
      ulElement = $(document.createElement("ul"));
      if ( this.useARIA )
      {
        ulElement.setAttribute('role','presentation');
      }
      this.contextMenuDiv.insert({bottom:ulElement});
      extraItems.each( function( lineItem )
      {
        ulElement.insert({bottom:lineItem});
      }.bind(this) );
    }

    // Remove any empty ULs and ensure that the added <ul>s have id of form "cmul${num}_${uniqueId}"
    $A(this.contextMenuDiv.getElementsByTagName("ul")).findAll( function( list )
    {
      if ( list.childElements().length === 0 )
      {
        list.remove(); return false;
      }
      else
      {
        return true;
      }
    }).each( function( list, index )
    {
      list.id = 'cmul'+index+'_'+this.uniqueId;
    }.bind(this) );

    this.menuOrder = null;  // only re-order once
  },

  generateDynamicMenu : function(event)
  {
    page.ContextMenu.closeAllContextMenus();
    if (this.dynamicMenu)
    {
      var context_parameters = this.contextParameters;
      var menu_generator_url = this.menuGeneratorURL;
      var nav = this.nav;
      var overwriteNavItems = this.overwriteNavItems;

      if ( context_parameters )
      {
        context_parameters = context_parameters.toQueryParams();
      }
      else
      {
        context_parameters = {};
      }

      var params = Object.extend({nav_item: nav }, context_parameters );
      params = Object.extend( params, { overwriteNavItems : overwriteNavItems } );

      new Ajax.Request(menu_generator_url,
      {
        method: 'post',
        parameters: params,
        onSuccess: this.afterMenuGeneration.bind( this )
      });
    }
    else
    {
      this.afterMenuGeneration(this);
    }
    $(event).preventDefault();
  },

  onDisplayLinkClick: function( event )
  {
    page.ContextMenu.closeAllContextMenus();
    if (this.dynamicMenu)
    {
     this.generateDynamicMenu(event);
     this.dynamicMenu = false;
    }
    else
    {
      this.showMenu();
      //focus on the first menu item
      (function() { if (this.contextMenuDiv.style.display != 'none') { this.contextMenuDiv.down("a").focus(); } }.bind(this).defer());
      $(event).preventDefault();
    }
  },

  onCloseLinkClick: function( event, doNotSetFocusOnClick )
  {
    this.close();

    var setFocusOnDisplayContextMenuLink = true;

    // grade center (in non-accessible mode) hides displayContextMenuLink onMouseOut, so we need to make sure it's doNotSetFocusOnClose flag is not set
    // before setting focus.
    if ( this.displayContextMenuLink.doNotSetFocusOnClose !== undefined && this.displayContextMenuLink.doNotSetFocusOnClose )
    {
      setFocusOnDisplayContextMenuLink = false;
    }

    // We may not want to set focus on displayContextMenuLink when one of the menu items (other than Close Menu) is clicked.
    // Initially this behavior was required for Grade Center Quick Comment of a grade in the grid (see getGradeContextMenuItems function in gradebookgrid_cellctrl.js)
    if ( doNotSetFocusOnClick !== undefined && doNotSetFocusOnClick )
    {
      setFocusOnDisplayContextMenuLink = false;
    }

    if ( setFocusOnDisplayContextMenuLink )
    {
      this.displayContextMenuLink.focus();
    }
    if (event)
    {
    Event.stop( event );
    }
  },

  close: function()
  {
    // Delay the removal of the element from the page so firefox will continue to process
    // the click on the menu item chosen (otherwise it stops processing as soon as we remove the
    // element resulting in the menu not actually working)
    (function() {
      this.closeNow();
    }.bind(this).delay(0.1));
  },

  closeNow: function()
  {
    if (this.contextMenuDiv.style.display != "none")
    {
      var links = this.contextMenuDiv.getElementsBySelector("li > a");
      links.each(function(link) {
        link.blur();
      });
      this.contextMenuDiv.style.display = "none";
      Element.remove( this.contextMenuDiv );
      if ( this.shim )
      {
        this.shim.close();
      }
    }
  }
};
/**
 * Function called to change the 'arrow' of a breadcrumb to face downward when they are clicked for the
 * contextual menu.
 * @param uniqId - unique number which identifies the crumb which was clicked
 * @param size - the size of the breadcrumb
 * @return
 */
page.ContextMenu.changeArrowInBreadcrumb = function (uniqId, event)
{

  page.ContextMenu.alignArrowsInBreadcrumb(event);
  $('arrowContext_'+uniqId).addClassName('contextArrowDown').removeClassName('contextArrow');
  //Stop the click event to propagate anymore -else all arrows will be aligned again
  Event.stop( event );
  return false;
};

//To align all breadcrumb arrows in one direction
page.ContextMenu.alignArrowsInBreadcrumb = function (event)
{
  if ($('breadcrumbs') !== null){
    var bList = $($('breadcrumbs').getElementsByTagName('ol')[0]);
    var bs = bList.immediateDescendants();
    if (bs.length !== null && bs.length >1){
      for (var i = 2; i <= bs.length; i++) {
        var arrowSpan = $('arrowContext_'+i);
        if (arrowSpan !== null ){
          $('arrowContext_'+i).addClassName('contextArrow').removeClassName('contextArrowDown');
        }
      }
    }
  }

  return false;
};

// "static" methods
page.ContextMenu.LI = function(event, divId, forceMenuRefresh)
{
  page.LazyInit(event,['focus','mouseover'],'new page.ContextMenu(page.util.upToClass(target,\'contextMenuContainer\'), \'' + divId + '\',' + forceMenuRefresh + ');');
};
page.ContextMenu.contextMenus = []; // _Open_ context menus
page.ContextMenu.registerContextMenu = function( menu )
{
  page.ContextMenu.contextMenus.push( menu );
};
page.ContextMenu.hiddenDivs = $H(); // All the menu divs on the page - only needed for cases such as view_spreadsheet2.js where we try to modify the menus outside this framework
page.ContextMenu.hideMenuDiv = function( uniqueId, firstInsideInventoryCard)
{
  var linkId = 'cmlink_' + uniqueId;
  var link = document.getElementById(linkId);
  if (link && !link.savedDiv ) {
    var elementId = 'cmdiv_' + uniqueId;
    var element = link.nextSibling; // Should be the text between the link and div but check anyways
    if ( !element || element.id != elementId)
    {
      element = element.nextSibling;
      if ( !element || element.id != elementId)
      {
        element = document.getElementById(elementId);
    }
    }
    if (element)
    {
      link.savedDiv = element;
      page.ContextMenu.hiddenDivs.set(uniqueId,element);
      Element.remove( element );
      if (firstInsideInventoryCard)
      {
        var theCard = page.util.upToClass(link, 'block');
        if (theCard)
        {
          var menuSpanToMove = page.util.upToClass(link,'contextMenuContainer inlineMenuItems');
          if (menuSpanToMove)
          {
            menuSpanToMove.style.display='inline';
            theCard.insert({bottom:menuSpanToMove});
          }
        }
      }
    }
  }
};
page.ContextMenu.addDivs = function()
{
  $H(page.ContextMenu.hiddenDivs).values().each(function(ele)
  {
    document.body.appendChild(ele);
  });
};

page.ContextMenu.removeDivs = function()
{
  $H(page.ContextMenu.hiddenDivs).values().each(function(ele)
  {
    Element.remove(ele);
  });
};

page.ContextMenu.closeAllContextMenus = function( event )
{
  var deferClose = false;
  if ( event )
  {
    var e = Event.findElement( event, 'a' );
    if ( e && e.href.indexOf("#contextMenu") >= 0 )
    {
      Event.stop( event );
      return;
    }
    deferClose = true;
  }

  page.ContextMenu.contextMenus.each( function( menu )
  {
    if ( menu != this )
    {
      if (deferClose) {
        menu.close();
      } else {
        menu.closeNow();
      }
    }
  });
  page.ContextMenu.contextMenus = [];
};

/**
 *  Enables flyout menus to be opened using a keyboard or mouse.  Enables
 *  them to be viewed properly in IE as well.
 */
page.FlyoutMenu = Class.create();
page.FlyoutMenu.prototype =
{
  initialize: function( subMenuListItem )
  {
    this.subMenuListItem = $(subMenuListItem);
    this.menuLink = $(subMenuListItem.getElementsByTagName('a')[0]);
    //special case to render iframe shim under new course content build menu
    if (this.subMenuListItem.hasClassName('bcContent'))
    {
      var buildContentDiv = this.subMenuListItem.down("div.flyout");
      if ( !buildContentDiv )
      {
        this.subMenu = $(subMenuListItem.getElementsByTagName('ul')[0]);
      }
      else
      {
        this.subMenu = buildContentDiv;
      }
    }
    else
    {
      this.subMenu = $(subMenuListItem.getElementsByTagName('ul')[0]);
    }
    this.menuLink.flyoutMenu = this;

    // calculate the next/previous tab stops
    this.previousSibling = this.subMenuListItem.previous();
    while ( this.previousSibling && (!this.previousSibling.down('a') || !this.previousSibling.visible()) )
    {
      this.previousSibling = this.previousSibling.previous();
    }
    this.nextSibling = this.subMenuListItem.next();
    while ( this.nextSibling && (!this.nextSibling.down('a') || !this.nextSibling.visible()) )
    {
      this.nextSibling = this.nextSibling.next();
    }

    var rumble = $(this.subMenuListItem.parentNode.parentNode);
    this.inListActionBar = rumble && ( rumble.hasClassName("rumble_top") || rumble.hasClassName("rumble") );

    Event.observe( this.menuLink, 'mouseover', this.onOpen.bindAsEventListener( this ) );
    Event.observe( subMenuListItem, 'mouseout', this.onClose.bindAsEventListener( this ) );
    Event.observe( this.menuLink, 'click', this.onLinkOpen.bindAsEventListener( this ) );
    Event.observe( this.subMenuListItem, 'keydown', this.onKeyPress.bindAsEventListener( this ) );

    $A( this.subMenu.getElementsByTagName('li') ).each ( function( li )
    {
      $A(li.getElementsByTagName('a')).each( function( link )
      {
        Event.observe( link, 'focus', this.onAnchorFocus.bindAsEventListener( this ) );
        Event.observe( link, 'blur', this.onAnchorBlur.bindAsEventListener( this ) );
        Event.observe( link, 'click', this.onLinkClick.bindAsEventListener( this, link ) );
      }.bind( this ) );
    }.bind( this ) );

    // ARIA menus currently don't work properly in IE8, JAWS consumes arrow up/down keys
    this.useARIA = page.util.useARIA() && !Prototype.Browser.IE;
    if ( this.useARIA )
    {
      this.initARIA();
    }
    this.enabled = true;
  },

  initARIA: function()
  {
    var inListActionBar = this.inListActionBar;
    if ( inListActionBar )
    {
      this.subMenuListItem.up('ul').setAttribute( "role", "menubar" );
    }
    this.subMenuListItem.setAttribute( "role", "presentation" );
    this.subMenuListItem.down('a').setAttribute( "role", "menuitem" );
    this.subMenu.setAttribute( "role", "menu" );
    if ( !this.menuLink.hasClassName("notMenuLabel") )
    {
      this.subMenu.setAttribute( "aria-labelledby", this.menuLink.id );
    }
    $A( this.subMenu.getElementsByTagName('a') ).each ( function( link )
    {
      link.setAttribute( "role", "menuitem" );
      link.parentNode.setAttribute( "role", "presentation" );
      // List action bars have onclick handlers that prevent submission of the page
      // if no items are selected, so we can't register new onclicks here because
      // otherwise we can't stop them from executing.
      if ( !inListActionBar )
      {
        if ( !link.href.include("#") )
        {
          Event.observe( link, 'click', function() {
            if ( this.ohref.toLowerCase().startsWith("javascript") )
            {
              eval(decodeURIComponent(this.ohref) );
            }
            else
            {
              if ( this.target )
              {
                window.open( this.ohref, this.target );
              }
              else
              {
                window.location = this.ohref;
              }
            }
          } );
          link.ohref = link.href;
          link.removeAttribute( "href" );
          link.tabIndex = "-1";
          link.style.cursor = 'pointer'; // make it look like a link.
        }
      }
    });

  },

  setEnabled: function( enabled )
  {
    this.enabled = enabled;
    if ( !enabled )
    {
      this.subMenu.setStyle({ display: '' });
    }
  },

  onKeyPress: function( event )
  {
    if (!this.enabled)
    {
      return;
    }
    var key = event.keyCode || event.which;
    var elem = Event.element ( event );
    var children, index, link;
    if ( key == Event.KEY_UP )
    {
      children = this.subMenu.getElementsBySelector("li > a");
      index = children.indexOf( elem );
      if ( index > 0 )
      {
        children[index - 1].focus();
      }
      else if ( index === 0 )
      {
        children[children.length - 1].focus(); // wrap to bottom
      }
      Event.stop( event );
    }
    else if ( key == Event.KEY_DOWN )
    {
      children = this.subMenu.getElementsBySelector("li > a");
      index = children.indexOf( elem );
      if ( index == -1 )
      {
        this.open();
       (function() { this.subMenu.down("li > a").focus(); }.bind(this).defer());
      }
      else if ( index < ( children.length - 1 ) )
      {
        children[index + 1].focus();
      }
      else if ( index == ( children.length - 1 ) )
      {
        children[0].focus(); // wrap to top
      }

      Event.stop( event );
    }
    else if ( key == Event.KEY_LEFT )
    {
      if ( !this.previousSibling || ( this.previousSibling.hasClassName("mainButton") ||
                                  this.previousSibling.hasClassName("mainButtonType") ) )
      {
        this.executeTab( event, true, true );
      }
      else if ( this.previousSibling )
      {
        link = this.previousSibling.getElementsByTagName('a')[0];
        if ( !link || !this.previousSibling.hasClassName("sub") )
        {
          return;
        }
        this.close();
        page.util.fireClick( link );
        Event.stop( event );
      }
    }
    else if ( key == Event.KEY_RIGHT )
    {
      if ( !this.nextSibling || ( this.nextSibling.hasClassName("mainButton") ||
                              this.nextSibling.hasClassName("mainButtonType") ) )
      {
        this.executeTab( event, true, false );
      }
      else if ( this.nextSibling )
      {
        link = this.nextSibling.getElementsByTagName('a')[0];
        if ( !link || !this.nextSibling.hasClassName("sub") )
        {
          return;
        }
        this.close();
        page.util.fireClick( link );
        Event.stop( event );
      }
    }
    else if ( key == Event.KEY_ESC )
    {
      this.close();
      this.menuLink.focus();
      Event.stop( event );
    }
    else if ( key == Event.KEY_RETURN && this.useARIA && !this.inListActionBar )
    {
      page.util.fireClick( elem );
      Event.stop( event );
    }
    else if ( key == Event.KEY_TAB && this.useARIA )
    {
      this.executeTab( event, false, event.shiftKey );
    }
  },

  executeTab: function( event, forceMenuLinkTab, shift )
  {
    var elem = Event.element ( event );
    var link;
    if ( ( elem != this.menuLink ) || forceMenuLinkTab )
    {
      if ( shift )
      {
        // Go to previous menu
        if ( this.previousSibling )
        {
          link = this.previousSibling.getElementsByTagName('a')[0];
          if ( link ) { link.focus(); } else { this.menuLink.focus(); }
        }
        else
        {
          this.menuLink.focus();
        }
      }
      else
      {
        // Go to next menu
        if ( this.nextSibling )
        {
          link = this.nextSibling.getElementsByTagName('a')[0];
          if ( link ) { link.focus(); } else { this.menuLink.focus(); }
        }
        else
        {
          this.menuLink.focus();
        }
      }

      this.close();
      Event.stop( event );
    }
  },

  onOpen: function( event )
  {
    if (!this.enabled)
    {
      return;
    }
    this.open();
  },

  onClose: function( event )
  {
    var to = $(event.relatedTarget || event.toElement);
    if ( !to || to.up('li.sub') != this.subMenuListItem )
    {
      this.close();
    }
  },

  onLinkOpen: function( event )
  {
    if (!this.enabled)
    {
      return;
    }
    this.open();
    (function() { this.subMenu.down("li > a").focus(); }.bind(this).defer());
    Event.stop( event );
  },

  resizeAfterShowHide: function()
  {
  // TODO - ideally this would just resize the outer div, but closing and opening 'works'
  this.close();
  this.open();
  },

  open: function()
  {
    var alreadyShown = this.subMenu.getStyle('display') === 'block';
    // If the menu is already showing (i.e. as_ce4 theme, we don't need to position it)
    if ( !alreadyShown )
    {
      // Set position of action bar elements to static to enable z-index stack order
      page.util.setActionBarPosition( 'static' );

      var menuTop = this.subMenuListItem.getHeight();
      if ( this.subMenu.hasClassName( 'narrow' ) )
      {
        menuTop = 0;
      }
      this.subMenuListItem.setStyle( {position: 'relative'} );
      this.subMenu.setStyle(
      {
        display: 'block',
        zIndex: '999999',
        top: menuTop+'px',
        left: '0px',
        width: '',
        height: '',
        overflowY: ''
      });
      var offset = Position.cumulativeOffset( this.subMenuListItem );
      var menuDims = this.subMenu.getDimensionsEx();
      var menuHeight = menuDims.height;
      var popupWidth = this.subMenu.getWidth();
      var subListItemDims = this.subMenuListItem.getDimensions();
      var menuWidth = subListItemDims.width;

      var viewportDimensions = document.viewport.getDimensions();
      var scrollOffsets = document.viewport.getScrollOffsets();
      var gnav = $('globalNavPageNavArea');
      var gnavHeight = 0;
      if (gnav)
      {
        var gdim = gnav.getDimensions()
        gnavHeight = gdim.height;
      }
      viewportDimensions.height -= gnavHeight;
      var gnavContent = $('globalNavPageContentArea');
      var gnavScroll = 0;
      if (gnavContent)
      {
        gnavScroll = gnavContent.scrollTop;
      }
      scrollOffsets.top += gnavScroll;

      var offsetTop = offset[1] - scrollOffsets.top - gnavHeight;

      this.subMenu.flyoutMenu = this;

      if ( (offsetTop + menuHeight + subListItemDims.height) > viewportDimensions.height)
      {
        if ( (offsetTop - menuHeight) > 0 )
        {
          // if menu goes below viewport but still fits on-page, show it above button
          this.subMenu.setStyle({ top: '-'+menuHeight+'px' });
        }
        else
        {
          // we need to create scrollbars
          var newWidth = this.subMenu.getWidth() + 15;
          popupWidth = newWidth + 5;
          var newMenuHeight = viewportDimensions.height - (offsetTop + subListItemDims.height) - 20;
          var newMenuTop = menuTop;
          if (newMenuHeight < offsetTop)
          {
            // More space above than below
            newMenuHeight = offsetTop;
            newMenuTop = -offsetTop;
          }
          this.subMenu.setStyle(
                                {
                                  display: 'block',
                                  zIndex: '999999',
                                  top: newMenuTop+'px',
                                  left: '0px',
                                  width: newWidth + 'px',
                                  height: newMenuHeight + 'px',
                                  overflowY: 'auto'
                                });
        }
      }

      var offsetLeft = offset[0] - scrollOffsets.left;
      if ( (offsetLeft + popupWidth) > viewportDimensions.width )
      {
        var subMenuWidth = this.subMenuListItem.getWidth();
        var newLeft = popupWidth - (viewportDimensions.width-offsetLeft);
        if ((newLeft > 0) && (newLeft < offsetLeft))
        {
          newLeft = -newLeft;
        }
        else
        {
          newLeft = -offsetLeft;
        }
        this.subMenu.setStyle({ left: newLeft+'px' });
      }

      if ( page.util.isRTL() )
      {
        var newRight = 0;
        if ( (offsetLeft + menuWidth) - popupWidth < 0 )
        {
          newRight = (offsetLeft + menuWidth) - popupWidth;
        }
        this.subMenu.setStyle({ left: '', right: newRight+'px'});
      }

      if (!this.shim)
      {
        this.shim = new page.popupShim( this.subMenu);
      }

      this.shim.open();
    }
  },

  close: function()
  {
    // Reset position of action bar elements to relative
    page.util.setActionBarPosition( 'relative' );

    this.subMenuListItem.setStyle({position: ''});
    this.subMenu.setStyle({
      display: '',
      top: '',
      left: '',
      width: '',
      height: '',
      overflowY: ''
    });
    if ( this.shim )
    {
      this.shim.close();
    }
  },

  onLinkClick: function( event, link )
  {
    if (!this.enabled)
    {
      return;
    }
    setTimeout( this.blurLink.bind( this, link), 100);
  },

  blurLink: function( link )
  {
    link.blur();
    if (page.util.hasClassName( link, "donotclose" ))
    {
      link.focus();
    }
    else
    {
      this.close();
    }

  },

  onAnchorFocus: function ( event )
  {
    if (!this.enabled)
    {
      return;
    }
    var link = Event.element( event );
    link.setStyle({ backgroundColor: '#FFFFFF' });
  },

  onAnchorBlur: function( event )
  {
    var link = Event.element( event );
    link.setStyle({ backgroundColor: '' });
  }
};

/**
 * Class for providing functionality to menu palettes
 */
page.PaletteController = Class.create();
page.PaletteController.prototype =
{
  /**
   * Constructor
   *
   * @param paletteIdStr        Unique string identifier for a palette
   * @param expandCollapseIdStr Id value of anchor tag to be assigned
   *                            the palette expand/collapse functionality
   * @param closeOtherPalettesWhenOpen Whether to close all other palettes when this one is open
   */
  initialize: function( paletteIdStr, expandCollapseIdStr, closeOtherPalettesWhenOpen, collapsed )
  {
    // palette id string
    this.paletteItemStr = paletteIdStr;

    // palette element
    this.paletteItem = $(this.paletteItemStr);

    // default id string to palette contents container element
    this.defaultContentsContainerId = page.PaletteController.getDefaultContentsContainerId(this.paletteItemStr);

    // the currently active palette contents container element
    this.activeContentsContainer = $(this.defaultContentsContainerId);

    // expand/collapse palette toggle element
    this.paletteToggle = $(expandCollapseIdStr);

    if (this.paletteToggle)
    {
      Event.observe(this.paletteToggle, 'click', this.toggleExpandCollapsePalette.bindAsEventListener(this));
    }

    this.closeOtherPalettesWhenOpen = closeOtherPalettesWhenOpen;

    page.PaletteController.registerPaletteBox(this);
    if (collapsed)
    {
      this.collapsePalette(true);
    }
  },

  /**
   * Set the currently active palette contents container element
   *
   * @param container palette contents container element
   */
  setActiveContentsContainer: function ( container )
  {
    this.activeContentsContainer = container;
  },

  /**
   * Get the currently active palette contents container element
   *
   * @return palette contents container element
   */
  getActiveContentsContainer: function ()
  {
    return this.activeContentsContainer;
  },

  /**
   * Expands the palette if it's not already expanded.
   *
   * @return palette contents container element
   */
  expandPalette: function ( doNotPersist )
  {
    var itemPalClass = [];
    itemPalClass = this.paletteItem.className.split(" ");

    var h2 = $(this.paletteItemStr+"_paletteTitleHeading");
    var expandCollapseLink = h2.getElementsByTagName('a')[0];
    if ( !this.useFirstTagForExpandCollapse( h2 ) )
    {
      expandCollapseLink = h2.getElementsByTagName('a')[1];
    }

    var itemList = this.activeContentsContainer;

    if ( itemList.style.display == "none" )
    {
      itemList.style.display = "block";
      itemPalClass.length = itemPalClass.length - 1;
      this.paletteItem.className = itemPalClass.join(" ");
      h2.className = "";
      var itemTitle = expandCollapseLink.innerHTML.stripTags().trim();
      if ( !this.useFirstTagForExpandCollapse( h2 ) )
      {
        itemTitle = h2.getElementsByTagName('a')[0].innerHTML.stripTags();
      }
      expandCollapseLink.title = page.bundle.getString('expandCollapse.collapse.section.param', itemTitle);
      expandCollapseLink.up().setAttribute("aria-expanded", "true");
    }

    if ( doNotPersist )
    {
      return;
    }

    this.saveSessionStickyInfo( itemList.id, itemList.style.display );
  },

  /**
   * Collapses the palette if it's not already collapsed.
   *
   * @return palette contents container element
   */
  collapsePalette: function ( doNotPersist )
  {
    var itemPalClass = [];
    itemPalClass = this.paletteItem.className.split(" ");

    // Note - h2 is actually a div, not an h2 :)
    var h2 = $(this.paletteItemStr+"_paletteTitleHeading");
    var expandCollapseLink = h2.getElementsByTagName('a')[0];
    if ( !this.useFirstTagForExpandCollapse( h2 ) )
    {
      expandCollapseLink = h2.getElementsByTagName('a')[1];
    }

    var itemList = this.activeContentsContainer;

    if ( itemList.style.display != "none" )
    {
      itemList.style.display = "none";
      itemPalClass[itemPalClass.length] = 'navPaletteCol';
      this.paletteItem.className = itemPalClass.join(" ");

      if (itemPalClass.indexOf('controlpanel') != -1)
      {
      }

      if (itemPalClass.indexOf('listCm')!=-1)
      {
        h2.className = "listCmCol"; // colors h2 background (removes background image)
      }

      if (itemPalClass.indexOf('tools') != -1)
      {
        h2.className = "toolsCol";
      }
      var itemTitle = expandCollapseLink.innerHTML.stripTags();
      if ( !this.useFirstTagForExpandCollapse( h2 ) )
      {
        itemTitle = h2.getElementsByTagName('a')[0].innerHTML.stripTags().trim();
      }
      expandCollapseLink.title = page.bundle.getString('expandCollapse.expand.section.param', itemTitle);
      expandCollapseLink.up().setAttribute("aria-expanded", "false");
    }

    if (doNotPersist)
    {
      return;
    }

    this.saveSessionStickyInfo( itemList.id, itemList.style.display );
  },

  /**
   * Takes in a key value pair to save to the session as sticky data.
   *
   * @param key The key that will have the current course id appended to it to be saved to the session.
   * @param value The value to the key.
   */
  saveSessionStickyInfo: function( key, value )
  {
    /* Get the course id off of the global variable if exists, so that data is saved per
     * user session per course. If course doesn't exist, use empty string.
     */
    var current_course_id = window.course_id ? window.course_id : "";
    UserDataDWRFacade.setStringTempScope( key + current_course_id, value );
  },

  /**
   * Whether the first tag has js onclick event binding on it for palette collapse/expand
   *
   * @param h2
   */
  useFirstTagForExpandCollapse: function ( h2 )
  {
    return h2.getElementsByTagName('a')[0].id.indexOf( "noneExpandCollapseTag" ) > -1 ? false : true;
  },

  /**
   * Toggles a palette from expand to collapse and vice versa.
   *
   * @param event Optional event object if this method was bound to event.
   */
  toggleExpandCollapsePalette: function ( event, doNotPersist )
  {
    // To prevent default event behavior
    if ( event )
    {
      Event.stop( event );
    }

    if ( this.activeContentsContainer.style.display == "none" )
    {
      // palette is currently closed, so we will be expanding it
      if ( this.closeOtherPalettesWhenOpen )
      {
        // if closeOtherPalettesWhenOpen is set to true for this palette, close all other palettes
        page.PaletteController.closeAllOtherPalettes(this.paletteItemStr, doNotPersist);
      }
      this.expandPalette( doNotPersist );
    }
    else
    {
      // palette is currently expanded, so we will be collapsing it
      this.collapsePalette( doNotPersist );
    }
  }
};

// "static" methods

page.PaletteController.paletteBoxes = [];
page.PaletteController.registerPaletteBox = function( paletteBox )
{
  page.PaletteController.paletteBoxes.push( paletteBox );
};

/**
 * Get the palette controller js object by palette id
 *
 * @param paletteId
 */
page.PaletteController.getPaletteControllerObjById = function( paletteId )
{
  return page.PaletteController.paletteBoxes.find( function( pb )
         { return ( pb.paletteItemStr == paletteId ); } );
};


/**
 * Closes all palettes except the specified one
 *
 * @param paletteToKeepOpen
 */
page.PaletteController.closeAllOtherPalettes = function( paletteToKeepOpen, doNotPersist )
{
  for(var i = 0; i < page.PaletteController.paletteBoxes.length; i++)
  {
    var paletteItem = page.PaletteController.paletteBoxes[i];
    if (paletteToKeepOpen !== paletteItem.paletteItemStr)
    {
      paletteItem.collapsePalette( doNotPersist );
    }
  }
};

/**
 * Toggles (expand/collapse) the contents of a nav palette by palette id
 *
 * @param paletteId
 * @param doNotPersist - optional param to suppress persisting state, default is to persist
 */
page.PaletteController.toggleExpandCollapsePalette = function( paletteId, doNotPersist )
{
  var paletteObj = page.PaletteController.getPaletteControllerObjById( paletteId );
  paletteObj.toggleExpandCollapsePalette( null, doNotPersist);
};


/**
 * Collapses the contents of a nav palette by palette id
 *
 * @param paletteId
 * @param doNotPersist - optional param to suppress persisting state, default is to persist
 */
page.PaletteController.collapsePalette = function( paletteId, doNotPersist )
{
  var paletteObj = page.PaletteController.getPaletteControllerObjById( paletteId );
  paletteObj.collapsePalette( doNotPersist);
};


/**
 * Expand the contents of a nav palette by palette id
 *
 * @param paletteId
 * @param doNotPersist - optional param to suppress persisting state, default is to persist
 */
page.PaletteController.expandPalette = function( paletteId, doNotPersist )
{
  var paletteObj = page.PaletteController.getPaletteControllerObjById( paletteId );
  paletteObj.expandPalette( doNotPersist);
};


/**
 * Set the active palette contents container (element containing the body
 * contents of a palette). The active contents container is used to toggle
 * visibility when expanding and collapsing menu palettes.
 *
 * @param paletteId
 * @param paletteContentsContainer Optional container to set.
 *                                 If not given, the palette's active
 *                                 container will not be changed.
 * @return The new active palette contents container element.
 *         If no paletteContentsContainer element was passed,
 *         The current active palette contents container element
 *         will be returned.
 */
page.PaletteController.setActivePaletteContentsContainer = function( paletteId, paletteContentsContainer )
{
  var paletteObj = page.PaletteController.getPaletteControllerObjById( paletteId );
  if ( paletteContentsContainer )
  {
    paletteObj.setActiveContentsContainer( paletteContentsContainer );
  }
  return paletteObj.getActiveContentsContainer();
};

/*
 * Get the default palette contents container id string
 *
 * @param paletteId
 */
page.PaletteController.getDefaultContentsContainerId = function( paletteId )
{
  return paletteId + "_contents";
};


/**
 * Class for providing expand/collapse functionality (with dynamic loading)
 */
page.ItemExpander = Class.create();
page.ItemExpander.prototype =
{
  /**
   * Constructor
   * - expandLink - the link that when clicked will expand/collapse the item
   * - expandArea - the actual area that will get expanded/collapsed (if the item is dynamically loaded, this area will be populated dynamically)
   * - expandText - the text to show as a tooltip on the link for expanding
   * - collapseText - the text to show as a tooltip on the link for collapsing
   * - expandTitleText - the customized text for link title afer expanding the item; if null/undefined, use expandText
   * - collapseTitleText - the customized text for link title after collapsing the item;if null/undefined, use collapseText
   * - dynamic - whether the contents are dynamically loaded
   * - dynamicUrl - the URL to get the contents of the item from
   * - contextParameters - additional URL parameters to add when calling the dynamicUrl
   * - sticky - load/save expand state from UserData; true if null/undefined
   * - expanded - initially expanded; false if null/undefined
   */
  initialize: function( expandLink, expandArea, expandText, collapseText, dynamic, dynamicUrl, contextParameters, expandTitleText, collapseTitleText, sticky, expanded )
  {
    this.expandLink = $(expandLink);
    this.expandArea = $s(expandArea);
    // Register the expander so it can be found
    page.ItemExpander.itemExpanderMap[this.expandLink.id] = this;
    this.expandText = expandText.unescapeHTML();
    this.collapseText = collapseText.unescapeHTML();
    if ( expandTitleText !== null && expandTitleText !== undefined )
    {
      this.expandTitleText = expandTitleText.unescapeHTML();
    }
    else
    {
      this.expandTitleText = this.expandText;
    }
    if ( collapseTitleText !== null && collapseTitleText !== undefined )
    {
      this.collapseTitleText = collapseTitleText.unescapeHTML();
    }
    else
    {
      this.collapseTitleText = this.collapseText;
    }
    this.dynamic = dynamic;
    this.dynamicUrl = dynamicUrl;

    if ( contextParameters !== null && contextParameters !== undefined )
    {
      this.contextParameters = contextParameters.toQueryParams();
    }
    else
    {
      this.contextParameters = {};
    }

    this.sticky = ( sticky !== null && sticky !== undefined ) ? sticky : true;
    this.expanded = ( expanded !== null && expanded !== undefined ) ? expanded : false;
    this.hasContents = !this.dynamic;

    if ( this.sticky )
    {
      // get the course id off of the global variable if exists, because data is saved per user session per course
      var current_course_id = ( (typeof course_id != "undefined") && course_id !== null ) ? course_id : "";
      UserDataDWRFacade.getStringTempScope( this.expandLink.id + current_course_id, this.getAvailableResponse.bind( this ) );
    }
    this.expandCollapse( !this.expanded );
    Event.observe( this.expandLink, "click", this.onToggleClick.bindAsEventListener( this ) );
  },

  getAvailableResponse : function ( response  )
  {
    var originalExpanded = this.expanded ;
    var cachedExpanded = false;
    if ( response.length > 0 )
    {
      if ( response == 'true' )
      {
        cachedExpanded = true;
      }
      else
      {
        cachedExpanded = false;
    }
    }

    if ( originalExpanded != cachedExpanded )
    {
      //because we want the menu to be in the cached state,
      //we pass in the opposite so that expandCollapse changes the menu state.
      this.expandCollapse(originalExpanded);
    }
  },

  onToggleClick: function( event )
  {
    if ( event )
    {
      Event.stop( event );
    }

    this.expandCollapse(this.expanded);

    if ( this.sticky )
    {
      // get the course id off of the global variable if exists, so that data is saved per user session per course
      var current_course_id = ( (typeof course_id != "undefined") && course_id !== null ) ? course_id : "";
      UserDataDWRFacade.setStringTempScope( this.expandLink.id + current_course_id, this.expanded );
    }
  },

  expandCollapse: function(shouldCollapse)
  {
    var combo;
    if ( shouldCollapse ) //Collapse the item
    {
      $(this.expandArea).hide();
      this.expandLink.title = this.expandTitleText;
      this.expandLink.up().setAttribute("aria-expanded", "false");
      if ( this.expandLink.hasClassName("comboLink_active") )
      {
        combo = this.expandLink.up("li").down(".submenuLink_active");
        this.expandLink.removeClassName("comboLink_active");
        this.expandLink.addClassName("comboLink");
        if ( combo )
        {
          combo.removeClassName("submenuLink_active");
          combo.addClassName("submenuLink");
        }
      }
      else
      {
        this.expandLink.removeClassName("open");
      }
      this.expanded = false;
    }
    else //Expand the item
    {
      if ( this.hasContents )
      {
        $(this.expandArea).setStyle({ zoom: 1 });
        this.expandArea.show();
        this.expandLink.title = this.collapseTitleText;
        this.expandLink.up().setAttribute("aria-expanded", "true");
        if ( this.expandLink.hasClassName("comboLink") )
        {
          combo = this.expandLink.up("li").down(".submenuLink");
          this.expandLink.removeClassName("comboLink");
          this.expandLink.addClassName("comboLink_active");
          if ( combo )
          {
            combo.removeClassName("submenuLink");
            combo.addClassName("submenuLink_active");
          }
        }
        else
        {
          this.expandLink.addClassName("open");
        }
      }
      else if ( this.dynamic )
      {
        this.loadData();
      }

      this.expanded = true;
    }
  },

  loadData: function()
  {
    new Ajax.Request( this.dynamicUrl,
    {
      method: "post",
      parameters: this.contextParameters,
      requestHeaders: { cookie: document.cookie },
      onSuccess: this.afterLoadData.bind( this )
    });
  },

  afterLoadData: function( req )
  {
    try
    {
      var result = req.responseText.evalJSON( true );
      if ( result.success != "true" )
      {
        new page.InlineConfirmation("error", result.errorMessage, false );
      }
      else
      {
        this.hasContents = true;
        this.expandArea.innerHTML = result.itemContents;
        $(this.expandArea).setStyle({ zoom: 1 });
        this.expandArea.show();
        this.expandLink.title = this.collapseTitleText;
        this.expandLink.up().setAttribute("aria-expanded", "true");
        if ( this.expandLink.hasClassName("comboLink") )
        {
          var combo = this.expandLink.up("li").down(".submenuLink");
          this.expandLink.removeClassName("comboLink");
          this.expandLink.addClassName("comboLink_active");
          if ( combo )
          {
            combo.removeClassName("submenuLink");
            combo.addClassName("submenuLink_active");
          }
        }
        else
        {
          this.expandLink.addClassName("open");
        }
        this.expanded = true;
      }
    }
    catch ( e )
    {
      //Invalid response
    }
  }
};
page.ItemExpander.itemExpanderMap = {};

/**
 * Class for controlling the "breadcrumb expansion" (i.e. the "..." hiding the inner
 * breadcrumbs)
 */
page.BreadcrumbExpander = Class.create();
page.BreadcrumbExpander.prototype =
{
  initialize: function( breadcrumbBar )
  {
    var breadcrumbListElement = $(breadcrumbBar.getElementsByTagName('ol')[0]);
    var breadcrumbs = breadcrumbListElement.immediateDescendants();
    if ( breadcrumbs.length > 4 )
    {
      this.ellipsis = document.createElement("li");
      var ellipsisLink = document.createElement("a");
      ellipsisLink.setAttribute("href", "#");
      ellipsisLink.setAttribute("title", page.bundle.getString('breadcrumbs.expand') );
      ellipsisLink.innerHTML = "...";
      this.ellipsis.appendChild( ellipsisLink );
      this.ellipsis = Element.extend( this.ellipsis );
      Event.observe( ellipsisLink, "click", this.onEllipsisClick.bindAsEventListener( this ) );
      this.hiddenItems = $A(breadcrumbs.slice(2,breadcrumbs.length - 2));
      breadcrumbListElement.insertBefore( this.ellipsis, this.hiddenItems[0] );
      this.hiddenItems.invoke( "hide" );
    }

    // Make sure the breadcrumbs don't run into the mode switcher
    var breadcrumbContainer = $(breadcrumbListElement.parentNode);
    var modeSwitcher = breadcrumbBar.down('.modeSwitchWrap');
    if ( modeSwitcher )
    {
      var containerWidth = breadcrumbContainer.getWidth();
      var containerOffset = breadcrumbContainer.cumulativeOffset();
      var modeSwitcherOffset = modeSwitcher.cumulativeOffset();
      var modeSwitcherWidth = modeSwitcher.getWidth();
      if ( page.util.isRTL() )
      {
        if ( modeSwitcherOffset[0] + modeSwitcherWidth > containerOffset[0] )
        {
          breadcrumbContainer.setStyle({ paddingLeft: ( modeSwitcherOffset[0] + modeSwitcherWidth ) + 'px'} );
        }
      }
     // else
      //{
       // breadcrumbContainer.setStyle({ paddingRight: ( containerWidth - ( modeSwitcherOffset[0] - containerOffset[0] ) ) + 'px'} );
      //}
    }
  },

  onEllipsisClick: function( event )
  {
    this.hiddenItems.invoke( "show" );
    this.ellipsis.hide();
    Event.stop( event );
  }
};

/**
 * Dynamically creates an inline confirmation.
 *
 * refreshMessage - if we allow only one receipt on the page, refresh the message; even if we display only one instance of message on the page, we can change the content of the message
 */
page.InlineConfirmation = Class.create();
page.InlineConfirmation.prototype =
{
  initialize: function( type, message, showRefreshLink, oneReceiptPerPage, refreshMessage, focusOnRender )
  {
    if ( Object.isUndefined( focusOnRender ) )
    {
      focusOnRender = true;
    }
    var receiptId = $s('receipt_id');
    // do not insert a duplicate receipt, if one already exists
    if( receiptId && oneReceiptPerPage )
    {
      if( !refreshMessage )
      {
        return;
      }
      else
      {
        var receiptDivElement = $( receiptId );
        receiptDivElement.parentNode.removeChild( receiptDivElement );
      }
    }
    var cssClass = "bad";
    if ( type == "success" )
    {
      cssClass = "good";
    }
    else if ( type == "warning" )
    {
      cssClass = "warningReceipt";
    }
    var contentPane = $('contentPanel') || $('portalPane');
    var receiptHtml = '<div id="receipt_id" class="receipt '+ cssClass +'">'+
                      '<span class="inlineReceiptSpan" tabindex="-1" style="color:#FFFFFF">'+message+'</span>';
    if ( showRefreshLink )
    {
      receiptHtml += ' <a href="#refresh" onClick="document.location.href = document.location.href; return false;">' + page.bundle.getString("inlineconfirmation.refresh") + '</a>';
    }
    receiptHtml += '<a class="close" href="#close" title="'+ page.bundle.getString("inlineconfirmation.close") +'" onClick="Element.remove( $(this).up(\'div.receipt\') ); return false;"><img alt="'+ page.bundle.getString("inlineconfirmation.close") +'" src="/images/ci/ng/close_mini.gif"></a></div>';
    contentPane.insert({top:receiptHtml});
    // use aria live region to announce this confirmation message rather than setting focus to it. (Too many things are fighting over setting focus)
    // Note: if this confirmation is invoked from a menu handler, it may not announce if focus is lost when the menu closes. See how courseTheme.js sets focus before invoking.
    var insertedA = contentPane.down('span.inlineReceiptSpan');
    insertedA.setAttribute("aria-live","assertive");
    insertedA.parentNode.setAttribute("role","application");
    (function() { insertedA.update( insertedA.innerHTML ); }.defer(2));  // update live region so it is announced

    if( focusOnRender )
    {
      page.util.focusAndScroll( insertedA );
    }
  }
};

page.NestedInlineConfirmationIdCounter = 0;
page.NestedInlineConfirmation = Class.create();
page.NestedInlineConfirmation.prototype =
{
  initialize: function( type, message, showRefreshLink, previousElement,showCloseLink, extracss, insertBefore, oneReceiptPerPage, fadeAway, focusDiv, fadingTime, insertTop, receiptDivId, focusOnRender )
  {
    new page.NestedInlineConfirmationEx({
      type : type,
      message : message,
      showRefreshLink : showRefreshLink,
      previousElement : previousElement,
      showCloseLink : showCloseLink,
      extracss : (extracss) ? extracss : "",
      insertBefore : insertBefore,
      oneReceiptPerPage : oneReceiptPerPage,
      fadeAway : fadeAway,
      focusDiv : focusDiv,
      fadingTime : fadingTime,
      insertTop : insertTop,
      receiptDivId : receiptDivId,
      focusOnRender : focusOnRender});
  }
};

page.NestedInlineConfirmationEx = Class.create();
page.NestedInlineConfirmationEx.prototype =
{
  initialize: function( options )
  {
    this.options = Object.extend(
                                 {
                                     type : null,
                                     message : null,
                                     showRefreshLink : null,
                                     previousElement: null,
                                     showCloseLink : null,
                                     extracss: "",
                                     insertBefore: null,
                                     oneReceiptPerPage: null,
                                     fadeAway : null,
                                     focusDiv : null,
                                     fadingTime: null,
                                     insertTop: null,
                                     receiptDivId : null,
                                     focusOnRender : true,
                                     axOnly : false
                                 }, options );

   var receiptId = $s('receipt_nested_id');
    // do not insert a duplicate receipt, if one already exists
   var newDivId = 'receipt_nested_id';
    if(receiptId)
    {
      if (this.options.oneReceiptPerPage)
      {
        return;
      }
      newDivId = newDivId + (page.NestedInlineConfirmationIdCounter++);
    }

    // if receiptDivId is provided, we are explicitly using it as the id for the new receipt replacing the existing receipt with the same id
    if (this.options.receiptDivId)
    {
      // Remove the old message with the same receiptDivId if there is one before adding a new one
      if ( $( this.options.receiptDivId ) != null )
      {
        $( this.options.receiptDivId ).remove();
      }
      newDivId = this.options.receiptDivId;
    }

    var cssClass = "bad";
    if ( this.options.type == "success" )
    {
      cssClass = "good";
    }
    else if (this.options.type == "warning")
    {
      cssClass = "warningReceipt";
    }

    if ( this.options.axOnly )
    {
      cssClass = cssClass + ' hideoff';

      //overrides a few options explicitly for our purpose of using the receipt only for AX
      this.options.fadeAway = true;
      this.options.showCloseLink = false;
    }

    var arrowSpan = '';
    if (this.options.extracss.indexOf( "point", 0 ) != -1)
    {
      arrowSpan = '<span class="arrow"></span>';
    }

    var contentPane = $(this.options.previousElement);
    if (!contentPane)
    {
      // TODO - if we can't find the element we wanted to insert before, is it OK to just drop the notification?
      return;
    }

    var receiptHtml = '<div id="'+newDivId+'" style="display:none" class="receipt '+ cssClass +' '+this.options.extracss +'">'+arrowSpan+
                      '<span class="inlineReceiptSpan areceipt" tabindex="-1">'+this.options.message+'</span>';
    if ( this.options.showRefreshLink )
    {
      receiptHtml += ' <a href="#refresh" onClick="document.location.href = document.location.href; return false;">' + page.bundle.getString("inlineconfirmation.refresh") + '</a>';
    }

    if (this.options.showCloseLink)
    {
      // either this is a JS Snippet to execute on close or a simple true in which case we do nothing extra
      var onCloseFunction = "";
      if ( typeof this.options.showCloseLink === "string" || this.options.showCloseLink instanceof String )
      {
        if ( !page.closeReceiptLinkCounter )
        {
          page.closeReceiptLinkCounter = 0;
        }
        else
        {
          ++page.closeReceiptLinkCounter;
        }
        onCloseFunction = "onReceiptClosed" + page.closeReceiptLinkCounter;
        receiptHtml += "<script type='text/javascript'>window." + onCloseFunction + " = function( ) { " + this.options.showCloseLink + " ; }; </script>";
        onCloseFunction += "( );";
      }
      receiptHtml += '<a class="close" href="#close" style="z-index:1000" title="' + page.bundle.getString("inlineconfirmation.close") + '" onClick="' + onCloseFunction + 'Element.remove( $(this).up(\'div.receipt\') ); return false;"><img alt="' + page.bundle.getString("inlineconfirmation.close") + '" src="/images/ci/ng/close_mini.gif"></a></div>';
    }

    if ( this.options.insertBefore )
    {
      contentPane.insert({before:receiptHtml});
    }
    else if (this.options.insertTop)
    {
      contentPane.insert({top:receiptHtml});
    }
    else
    {
      contentPane.insert({after:receiptHtml});
    }
    this.insertedDiv = this.options.insertBefore?contentPane.previousSibling:(this.options.insertTop?contentPane.firstChild:contentPane.nextSibling);
    $(this.insertedDiv).show();
    var insertedA = $(this.insertedDiv).down('span.inlineReceiptSpan');
    var fadingDuration = this.options.fadingTime ? this.options.fadingTime : 5000;

    // For all cases (focus or not), set the aria assertive attribute to make sure this is announced by the screen reader
    insertedA.setAttribute("aria-live","assertive");
    this.insertedDiv.setAttribute("role","application");
    (function() { insertedA.update( insertedA.innerHTML ); }.defer(2));  // update live region so it is announced (needed for jaws 12)

    if ( this.options.focusOnRender )
    {
        try
        {
         ( function()
            {
           try
           {
              if ( this.options.focusDiv )
              {
                page.util.focusAndScroll( $( this.options.focusDiv ) );
              }
              else
              {
                page.util.focusAndScroll( insertedA );
              }
           }
           catch ( focusError )
           {
             // Ignore focus errors. These can happens sometimes on IE if focus is set on an element that is located
             // inside another element that has recently been switched from a hidden state to a visible one.
           }

            }.defer() );
        }
        catch ( focusError )
        {
          // Ignore focus errors. These can happens sometimes on IE if focus is set on an element that is located
          // inside another element that has recently been switched from a hidden state to a visible one.
        }
    }
    else
    {
        // not setting focus to this confirmation - but still make sure it is visible.
        if ( this.options.focusDiv )
        {
          page.util.ensureVisible( $( this.options.focusDiv ) );
        }
        else
        {
          page.util.ensureVisible( insertedA );
        }
    }
    if ( this.options.fadeAway )
    {
      setTimeout( function()
      {
        Element.fade( $(this.insertedDiv),
        {
          duration : 0.3
        } );
      }.bind(this), fadingDuration );
    }
  },

  close: function()
  {
    if ( this.insertedDiv )
    {
      this.insertedDiv.remove();
    }
  }
};


page.NestedInlineFadeAwayConfirmation = Class.create();
page.NestedInlineFadeAwayConfirmation.prototype =
{
  initialize: function( type, message, showRefreshLink, element,showCloseLink, insertBefore, time  )
  {
    var fadingDuration = time ? time : 2000;
    new page.NestedInlineConfirmation(type, message, showRefreshLink, element,showCloseLink, "", insertBefore, null, true, null, fadingDuration );
  }
};

page.NestedInlineFadeAwaySingleConfirmation = Class.create();
page.NestedInlineFadeAwaySingleConfirmation.prototype =
{
  initialize: function( type, message, showRefreshLink, element,showCloseLink, insertBefore, time, newDivId, insertTop )
  {
    var fadingDuration = time ? time : 2000;
    new page.NestedInlineConfirmation(type, message, showRefreshLink, element,showCloseLink, "", insertBefore, false /*only one instance*/, true, null, fadingDuration, insertTop, newDivId );
  }
};

/**
 * Make sure the container as position: relative so that the offset can work
 */
page.MiniReceipt = Class.create();
page.MiniReceipt.prototype =
{
    initialize: function( message, containerElement, top, left, time )
    {
      var visibleDuration = time ? time : 2000;
      var top = top?top:-22; // usually show receipt above
      var left = left?left:0;
      var alreadyExistingReceipt = $( containerElement ).down( "div.miniReceipt" );
      if  ( alreadyExistingReceipt )
      {
        alreadyExistingReceipt.hide( );
      }
      var receiptHtml = '<div class="miniReceipt adding" style="display: none; top:' + top + 'px; left:'+ left + 'px" role="alert" aria-live="assertive">' + message + '</div>';
      var receiptElement = $( containerElement ).insert( { top:receiptHtml } ).firstDescendant( );
      $( containerElement ).select();
      receiptElement.show( );
      setTimeout(
        function()
        {
          Element.fade( receiptElement, {duration:0.3, afterFinish: function() { receiptElement.remove(); } } );
        }, visibleDuration );
    }
};

page.extendedHelp = function( helpattributes, windowName )
{
  window.helpwin = window.open('/webapps/blackboard/execute/viewExtendedHelp?' +
               helpattributes,windowName,'menubar=1,resizable=1,scrollbars=1,status=1,width=480,height=600');
  window.helpwin.focus();
};

page.decoratePageBanner = function()
{
  var bannerDiv = $('pageBanner');
  // TODO: review this logic - we used to actually add a style to containerDiv but
  // we do not need it anymore - does the pagetitlediv hiding depend on containerdiv existing?  probably, so leaving
  var containerDiv = $('contentPanel') || $('contentPane');
  if ( bannerDiv && containerDiv )
  {
    // hide empty title bar
    if ( !$('pageTitleText') && $('pageTitleDiv') )
    {
      $('pageTitleDiv').hide();
    }
  }
};

page.initializeSinglePopupPage = function( pageId )
{
  // Initialize the single popup page, make sure the window will be closed by clicking submit or cancel, and the parent
  // window will be refreshed after submit.
  var items = document.forms;
  for ( var i = 0; i < items.length; i++ )
  {
    var formItem = items[ i ];
    formItem.observe( 'submit', function()
    {
       (function()
       {
         window.close();
         if( window.opener.refreshConfirm )
         {
            window.opener.refreshConfirm(pageId);
         }
       }.defer());
    } );
    if ( formItem.top_Cancel )
    {
      Event.observe( formItem.top_Cancel, 'click', function( event )
      {
        Event.stop( event );
        window.close();
      } );
    }
    if ( formItem.bottom_Cancel )
    {

      Event.observe( formItem.bottom_Cancel, 'click', function( event )
      {
        Event.stop( event );
        window.close();
      } );
    }
  }
};

page.openLightbox = function( link, title, url, width, height )
{
  var lightboxParam =
  {
      defaultDimensions :
      {
          w : width ? width : 1000,
          h : height ? height : 800
      },
      contents : '<iframe src="' + url + '" width="100%" height="100%"/>',
      title : title,
      closeOnBodyClick : false,
      showCloseLink : true,
      useDefaultDimensionsAsMinimumSize : true
  };
  var lightboxInstance = new lightbox.Lightbox( lightboxParam );
  lightboxInstance.open();
};

page.printAndClose = function()
{
  (function() {
    window.print();
    window.close();
  }.defer());
};

/**
 * Utility for data collection step manipulation
 */
page.steps = {};
page.steps.HIDE = "hide";
page.steps.SHOW = "show";

/**
 * Hide or show an array of steps given the step ids and
 * renumber all visible steps on the page.
 *
 * @param action - either page.steps.HIDE or page.steps.SHOW
 * @param stepIdArr - string array of step ids
 */
page.steps.hideShowAndRenumber = function ( action, stepIdArr )
{
  // hide or show each of the step ids given
  ($A(stepIdArr)).each( function( stepId )
  {
      page.steps.hideShow( action, stepId );
  });

  // get all H3 elements that contain css class of "steptitle"
  var stepTitleTags = [];
  $A(document.getElementsByTagName('h3')).each( function( tag )
  {
    if ( page.util.hasClassName( tag, 'steptitle' ) )
    {
      stepTitleTags.push( $(tag) );
    }
  });

  // starting at number 1, renumber all of the visible steps
  var number = 1;
  stepTitleTags.each(function( stepTitleTag )
  {
    if ( stepTitleTag.up('div').visible() )
    {
      stepTitleTag.down('span').update(number);
      number++;
    }
  });
};

/**
 * Hide or show a single step given the step id.
 *
 * @param action - either page.steps.HIDE or page.steps.SHOW
 * @param stepId - string identifier to a single step
 */
page.steps.hideShow = function ( action, stepId )
{
  if ( action == page.steps.SHOW )
  {
    $(stepId).show();
  }
  else if ( action == page.steps.HIDE )
  {
    $(stepId).hide();
  }
};

/**
 * Initializing the Expand/Collapse of the Step
 */
page.steps.initCollapsibleStep = function ( )
{
  $A( document.getElementsByClassName( "collapsibleStepTitle" ) ).each( function( stepTitle ) {
    Event.observe( $( stepTitle ), "click", this.page.steps.collapsibleStep.bindAsEventListener( this, stepTitle ) );
    Event.observe( $( stepTitle ), "keydown",
         function( event ) {
            var key = event.keyCode || event.which;
            if ( key == Event.KEY_RETURN || key == 32 )
            {
              page.steps.collapsibleStep( event, stepTitle );
            }
         }
    );
  });
};

page.steps.collapsibleStep = function( event, stepTitle )
{
    var content = $( stepTitle ).up().down("div");
    if ( content.visible() )
    {
      stepTitle.removeClassName( "collapsibleStepExpanded" );
      content.removeClassName( "collapsibleStepContentExpanded" );
      Effect.toggle(content.id, "slide", { duration: 0.5 });
      content.setAttribute("aria-hidden", "true");
      stepTitle.setAttribute("aria-expanded", "false");
    }
    else
    {
      stepTitle.addClassName( "collapsibleStepExpanded" );
      content.addClassName( "collapsibleStepContentExpanded" );
      Effect.toggle(content.id, "slide", { duration: 0.5 } );
      content.setAttribute("aria-hidden", "false");
      stepTitle.setAttribute("aria-expanded", "true");
    }
    Event.stop( event );
};

page.showChangeTextSizeHelp = function( )
{
  page.extendedHelp('internalhandle=change_text_size&helpkey=change_text_size','change_text_size' );
  return false;
};

page.showAccessibilityOptions = function()
{
   var win = window.open('/webapps/portal/execute/changePersonalStyle?cmd=showAccessibilityOptions',
       'accessibilityOptions','menubar=1,resizable=1,scrollbars=1,status=1,width=480,height=600');
   win.focus();
};

page.toggleContrast = function( )
{
  new Ajax.Request('/webapps/portal/execute/changePersonalStyle?cmd=toggleContrast',
  {
    onSuccess: function(transport, json)
    {
      var fsWin;
      if (window.top.nav)
      {
        fsWin = window.top;
      }
      else if (window.opener && window.opener.top.nav)
      {
        fsWin = window.opener.top;
        window.close();
      }
      if (fsWin)
      {
        fsWin.nav.location.reload();
        fsWin.content.location.reload();
      }
      else
      {
        window.top.location.reload();
      }
    }
  });
  return false;
};

/**
 * IFrame-based shim used with popups so they render on top of all other page elements (including applets)
 */
page.popupShim = Class.create();
page.popupShim.prototype =
{
  initialize: function( popup )
  {
    this.popup = popup;
  },

  close: function( )
  {
    this.toggleOverlappingEmbeds( false );
  },

  open: function( )
  {
    this.toggleOverlappingEmbeds( true );
  },

  toggleOverlappingEmbeds: function( turnOff )
  {
    ['embed','object','applet','select'].each( function( tag ) {
      var elems = document.getElementsByTagName( tag );
      for ( var i = 0, l = elems.length; i < l; i++ )
      {
        var e = $(elems[i]);

        /* Only show/hide overlapping object if the element is visible in the first place, otherwise there is no point.
         * Note that visible() checks the display property, and behaves differently from the visibility property being
         * set below, so we're safe when this method is being called with turn off|on.
         */
        if( e.visible() )
        {
          if ( !turnOff || ( page.util.elementsOverlap( this.popup, e ) && !e.descendantOf( this.popup ) ) )
          {
            elems[i].style.visibility = ( turnOff ? 'hidden' : '' );
          }
        }
      }
    }.bind( this ) );
  }
};

/**
 * Looks through the children of the specified element for links with the specified
 * class name, and if it finds any, autowires lightboxes to them.  If lightbox.js/effects.js
 * hasn't already been loaded, load it.
 */
page.LightboxInitializer = Class.create(
{
  initialize: function( className, parentElement, justThisParent )
  {
    this.className = className;
    if (justThisParent)
    {
      this.parentElement = parentElement;
    }
    var links = parentElement.getElementsByTagName('a');
    for ( var i = 0, l = links.length; i < l; i++ )
    {
      if ( page.util.hasClassName( links[i], className ) )
      {
        if ( window.lightbox && window.Effect)
        {
          this._autowire();
        }
        else
        {
          this._load();
        }
        break;
      }
    }
  },

  _autowire: function()
  {
    lightbox.autowireLightboxes( this.className, this.parentElement );
  },

  _load: function()
  {
    var h = $$('head')[0];
    // TODO: This code does not take version into account (so immediately after an upgrade this won't get the new file)...
    var scs = ( !window.lightbox ? ['/javascript/ngui/lightbox.js'] : []).concat(
                !window.Effect ? ['/javascript/scriptaculous/effects.js'] : [] );
    scs.each( function( sc )
    {
      var s = new Element('script', { type: 'text/javascript', src: sc } );
      h.appendChild( s );
    });
    this._wait();
  },

  _wait: function()
  {
    var count = 0;
    new PeriodicalExecuter( function( pe )
    {
      if ( count < 100 )
      {
        count++;
        if ( window.lightbox && window.Effect )
        {
          pe.stop();
          this._autowire();
        }
      }
      else // give up if it takes longer than 5s to load lightbox.js/effects.js
      {
        pe.stop();
      }
    }.bind(this), 0.05 );
  }
});

page.YouTubeControls = {
  toggleAXControls : function( playerid, openYtControlsId, event )
  {
    if( $( playerid.sub( 'ytEmbed', 'controls' ) ).style.display != 'block' ) {
      $( playerid.sub( 'ytEmbed', 'controls' ) ).style.display = 'block';
      $( playerid.sub( 'ytEmbed', 'strip' ) ).style.display = 'block';
      $( openYtControlsId ).addClassName( 'liveAreaTab' );
      if ( window.lightbox && lightbox.getCurrentLightbox() )
      {
        lightbox.getCurrentLightbox()._resizeAndCenterLightbox( false );
      }

    }
    else
    {
      $( playerid.sub( 'ytEmbed', 'controls' ) ).style.display = 'none';
      $( playerid.sub( 'ytEmbed', 'strip' ) ).style.display = 'none';
      $( openYtControlsId ).removeClassName( 'liveAreaTab' );
      if ( window.lightbox && lightbox.getCurrentLightbox() )
      {
        lightbox.getCurrentLightbox()._resizeAndCenterLightbox( false );
      }

    }
    Event.stop( event );
  },
  formatTime : function ( sec )
  {
    var duration = parseInt( sec, 10 );
    var totalMinutes = Math.floor( duration / 60 );
    var hours = Math.floor( totalMinutes / 60 );
    var seconds = duration % 60;
    var minutes = totalMinutes % 60;
    if ( hours > 0 )
    {
      return hours + ':' + this.padZero( minutes ) + ':' + this.padZero( seconds );
    }
    else
    {
      return this.padZero( minutes ) + ':' + this.padZero( seconds );
    }
  },
  padZero : function ( number )
  {
    if (number < 10)
    {
      return "0" + number;
    }
    else
    {
      return number;
    }
  },
  updateButtonLabels : function ( ytplayer, muteBtnId, playBtnId, status )
  {
    if( ytplayer.isMuted() )
    {
      $( muteBtnId ).update( page.bundle.getString( 'yt.unmute' ) );
    }
    else
    {
      $( muteBtnId ).update( page.bundle.getString( 'yt.mute' ) );
    }
    if( status == 1 )
    {
      $( playBtnId ).update( page.bundle.getString( 'yt.pause' ) );
    }
    else
    {
      $( playBtnId ).update( page.bundle.getString( 'yt.play' ) );
    }
  },
  updateIframeButtonLabels : function ( ytplayer, muteBtnId, playBtnId, status )
  {
    if ( typeof ytplayer.isMuted !== 'undefined'  ) {
    if( ytplayer.isMuted() )
    {
      $( muteBtnId ).update( page.bundle.getString( 'yt.unmute' ) );
    }
    else
    {
      $( muteBtnId ).update( page.bundle.getString( 'yt.mute' ) );
    }
    }
    if( status == 1 )
    {
      $( playBtnId ).update( page.bundle.getString( 'yt.pause' ) );
    }
    else
    {
      $( playBtnId ).update( page.bundle.getString( 'yt.play' ) );
    }
  }
};

function onYouTubePlayerReady( playerid )
{
  var ytplayer = $( playerid );
  if( !ytplayer )
  { //ie fix: grab object tag instead of embed tag
    var objTagId = playerid.sub( 'ytEmbed', 'ytObject' );
    ytplayer = $( objTagId );
  }
  var playBtnId = playerid.sub( 'ytEmbed', 'playVideo' );
  Event.observe( $( playBtnId ), 'click',
    function( event ) {
      if( ytplayer.getPlayerState() == 1 )
      {
        ytplayer.pauseVideo();
      }
      else
      {
        ytplayer.playVideo();
      }
      Event.stop( event );
    }
  );
  var stopBtnId = playerid.sub( 'ytEmbed', 'stopVideo' );
  Event.observe( $( stopBtnId ), 'click',
    function( event ) {
      ytplayer.pauseVideo();
      ytplayer.seekTo( "0" );
      $( playBtnId ).update( page.bundle.getString( 'yt.play' ) );
      Event.stop( event );
    }
  );
  var volUpBtnId = playerid.sub( 'ytEmbed', 'volUp' );
  Event.observe( $( volUpBtnId ), 'click',
    function( event ) {
      var currVol = ytplayer.getVolume();
      if( currVol > 89 )
      {
        ytplayer.setVolume( 100 );
      }
      else
      {
        ytplayer.setVolume( currVol + 10 );
      }
      Event.stop( event );
    }
  );
  var volDownBtnId = playerid.sub( 'ytEmbed', 'volDown' );
  Event.observe( $( volDownBtnId ), 'click',
    function( event ) {
      var currVol = ytplayer.getVolume();
      if( currVol < 11 )
      {
        ytplayer.setVolume( 0 );
      }
      else
      {
        ytplayer.setVolume( currVol - 10 );
      }
      Event.stop( event );
    }
  );
  var muteBtnId = playerid.sub( 'ytEmbed', 'mute' );
  Event.observe( $( muteBtnId ), 'click',
    function( event ) {
      if( ytplayer.isMuted() )
      {
        ytplayer.unMute();
      }
      else
      {
        ytplayer.mute();
      }
      Event.stop( event );
    }
  );
  var timeDivId = playerid.sub( 'ytEmbed', 'currentTime' );
  var statusDivId = playerid.sub( 'ytEmbed', 'currentStatus');
  var dtTime = new Date();
  new PeriodicalExecuter( function( pe )
  {
    //lightbox closed, so stop this PeriodicalExecuter
    if( !$( timeDivId ) )
    {
      pe.stop();
      return;
    }
    //update the current time
    $( timeDivId ).update( page.YouTubeControls.formatTime( ytplayer.getCurrentTime() ) );
    //update the current status
    var status = ytplayer.getPlayerState();
    var statusStr = page.bundle.getString( 'yt.stopped' );
    switch( status )
    {
    case -1 : statusStr = page.bundle.getString( 'yt.stopped' ); break;
    case 0  : statusStr = page.bundle.getString( 'yt.ended' ); break;
    case 1  : statusStr = page.bundle.getString( 'yt.playing' ); break;
    case 2  : statusStr = page.bundle.getString( 'yt.paused' ); break;
    case 3  : statusStr = page.bundle.getString( 'yt.buffering' ); break;
    case 5  : statusStr = page.bundle.getString( 'yt.cued' ); break;
    }
    page.YouTubeControls.updateButtonLabels( ytplayer, muteBtnId, playBtnId, status );

    $( statusDivId ).update( statusStr );
  }.bind(this), 0.5 );

  //wire the open/close controls wrapper
  var openYtControlsId = playerid.sub( 'ytEmbed', 'openYtControls' );
  Event.observe( $( openYtControlsId ), 'click',
    function( event ) {
      page.YouTubeControls.toggleAXControls( playerid, openYtControlsId, event );
    }
  );
  var closeYtControlsId = playerid.sub( 'ytEmbed', 'closeYtControls' );
  Event.observe( $( closeYtControlsId ), 'click',
    function( event ) {
      page.YouTubeControls.toggleAXControls( playerid, openYtControlsId, event );
    }
  );
};

var youtubeisready;
var tag = document.createElement( 'script' );
tag.src = "//www.youtube.com/iframe_api";
var firstScriptTag = document.getElementsByTagName( 'script' )[ 0 ];
firstScriptTag.parentNode.insertBefore( tag, firstScriptTag );

function onYouTubeIframeAPIReady()
{
  youtubeisready = true;
  page.util.bbPreptextareasforyoutube();
};

page.util.bbPreptextareasforyoutube = function(frameIds)
{
  if( youtubeisready )
  {
    if ( !frameIds )
    {
      frameIds = $$( '.ytIframeClass' );
    }
    var ytPlayers = [];
    frameIds.each( function( frameId )
    {
      var ytPlayerId = frameId.id;
      var controlsId = ytPlayerId.replace("ytEmbed","");
      var frameData = frameId.outerHTML;
      var videoId = "";
      if( frameData.indexOf('//www.youtube.com/embed') != -1 )
      {
        videoId = frameData.match("//www.youtube.com/embed/([\\d\\w-_]+)")[1];
      }

      if( frameData.indexOf('//www.youtube.com/v') != -1 )
      {
        videoId = frameData.match("//www.youtube.com/v/([\\d\\w-_]+)")[1];
      }

      if( videoId  === "" )
        return;

      ytPlayerId.replace("ytEmbed", "videoId");
      var ytplayer = new YT.Player(frameId, {
          videoId : videoId,
          playerVars : {
              wmode: 'transparent',
              rel : 0,
              modestbranding : 1,
              menu: 'disable'
        },
        events : {
          'onReady' : onPlayerReady,
          'onStateChange' : onytplayerStateChange,
          'onError' : onPlayerError
        }
      });

      ytPlayers.push(ytplayer);
      var playBtnId = $("playVideo" + controlsId );
      Event.observe( playBtnId, 'click', function( event )
      {
        if ( ytplayer.getPlayerState() == 1 )
        {
          ytplayer.pauseVideo();
        }
        else
        {
          ytplayer.playVideo();
        }
        Event.stop( event );
      } );
      var stopBtnId = $("stopVideo" + controlsId );
      Event.observe(  stopBtnId , 'click', function( event )
      {
        ytplayer.pauseVideo();
        ytplayer.seekTo( "0" );
        $( playBtnId ).update( page.bundle.getString( 'yt.play' ) );
        Event.stop( event );
      } );
      var volUpBtnId = $("volUp" + controlsId );
      Event.observe( volUpBtnId , 'click',
        function( event ) {
          var currVol = ytplayer.getVolume();
          if( currVol > 89 )
          {
            ytplayer.setVolume( 100 );
          }
          else
          {
            ytplayer.setVolume( currVol + 10 );
          }
          Event.stop( event );
        }
      );
      var volDownBtnId = $("volDown" + controlsId );
      Event.observe( volDownBtnId, 'click',
        function( event ) {
          var currVol = ytplayer.getVolume();
          if( currVol < 11 )
          {
            ytplayer.setVolume( 0 );
          }
          else
          {
            ytplayer.setVolume( currVol - 10 );
          }
          Event.stop( event );
        }
      );
      var muteBtnId = $("mute" + controlsId );
      Event.observe( muteBtnId, 'click',
        function( event ) {
        if ( typeof ytplayer.isMuted !== 'undefined'  ) {
          if( ytplayer.isMuted() )
          {
            ytplayer.unMute();
          }
          else
          {
            ytplayer.mute();
          }
        }
          Event.stop( event );
        }
      );

      var timeDivId = ytPlayerId.sub( 'ytEmbed', 'currentTime' );
      var statusDivId = ytPlayerId.sub( 'ytEmbed', 'currentStatus');
      var dtTime = new Date();
      new PeriodicalExecuter( function( pe )
      {
        //lightbox closed, so stop this PeriodicalExecuter
        if( !$( timeDivId ) )
        {
          pe.stop();
          return;
        }
        //update the current time
        if ( typeof ytplayer.getCurrentTime !== 'undefined'  )
        {
          $( timeDivId ).update( page.YouTubeControls.formatTime( ytplayer.getCurrentTime() ) );
        }
        //update the current status
        var status = -1;
        if ( typeof ytplayer.getPlayerState !== 'undefined'  )
        {
          status = ytplayer.getPlayerState();
        }
        var statusStr = page.bundle.getString( 'yt.stopped' );
        switch( status )
        {
        case -1 : statusStr = page.bundle.getString( 'yt.stopped' ); break;
        case 0  : statusStr = page.bundle.getString( 'yt.ended' ); break;
        case 1  : statusStr = page.bundle.getString( 'yt.playing' ); break;
        case 2  : statusStr = page.bundle.getString( 'yt.paused' ); break;
        case 3  : statusStr = page.bundle.getString( 'yt.buffering' ); break;
        case 5  : statusStr = page.bundle.getString( 'yt.cued' ); break;
        }
        page.YouTubeControls.updateIframeButtonLabels( ytplayer, muteBtnId, playBtnId, status );

        $( statusDivId ).update( statusStr );
      }.bind(this), 0.5 );

      //wire the open/close controls wrapper
      var openYtControlsId =$("openYtControls" + controlsId);
      Event.observe( openYtControlsId, 'click', function( event )
      {
        page.YouTubeControls.toggleAXControls( ytPlayerId, openYtControlsId, event );
      } );
      var closeYtControlsId = ytPlayerId.sub( 'ytEmbed', 'closeYtControls' );
      Event.observe( $( closeYtControlsId ), 'click', function( event )
      {
        page.YouTubeControls.toggleAXControls( ytPlayerId, openYtControlsId, event );
      } );
    }
    );
  }
  return ytPlayers;
};

function onPlayerReady( event )
{
  event.target.stopVideo();
};

function onPlayerError( event )
{
  event.target.stopVideo();
};

function onytplayerStateChange( event )
{
  if ( event.data === 0 )
  {
    event.target.playVideo();
    event.target.stopVideo();
  }
};

page.util.flyoutMenuMainButtonKeyboardHandler = function( event )
{
  var key = event.keyCode || event.which;
  if (key == Event.KEY_LEFT || key == Event.KEY_RIGHT)
  {
    var elem = Event.element( event );
    var target = elem.up( 'li' );
    while ( true )
    {
      if ( key == Event.KEY_LEFT )
      {
        target = target.previous();
      }
      else if ( key == Event.KEY_RIGHT )
      {
        target = target.next();
      }
      if ( !target || page.util.hasClassName( target, 'sub' ) ||
                      page.util.hasClassName( target, 'mainButton' ) ||
                      page.util.hasClassName( target, 'mainButtonType' ) )
      {
        break;
      }
    }
    if ( target )
    {
      var menuLinks = $A( target.getElementsByTagName( 'a' ) );
      if ( menuLinks && menuLinks.length > 0 )
      {
        menuLinks[ 0 ].focus();
        Event.stop( event );
      }
    }
  }
};

page.util.initFlyoutMenuBehaviourForListActionMenuItems = function( container ) {
  //Initialize accessible flyout menu behavior
  if ( !container )
  {
    container = document;
  }
  var uls = document.getElementsByTagName('ul');
  if (uls) {
    var numUls = uls.length;
    for (var i = 0; i < numUls; i++) {
      var ul = uls[i];
      if (page.util.hasClassName(ul, 'nav')) {
        var lis = ul.getElementsByTagName('li');
        if (lis) {
          var numLis = lis.length;
          for (var j = 0; j < numLis; j++) {
            var li = lis[j];
            if (page.util.hasClassName(li, 'sub')) {
              new page.FlyoutMenu($(li));
            } else if (page.util.hasClassName(li, 'mainButton') || page.util.hasClassName(li, 'mainButtonType')) {
              var menuLinks = $A($(li).getElementsByTagName('a'));
              if (menuLinks && menuLinks.length > 0) {
                Event.observe(menuLinks[0], 'keydown', page.util.flyoutMenuMainButtonKeyboardHandler.bindAsEventListener(menuLinks[0]));
              }
            }
          }
        }
      }
    }
  }
};

//LRN-67276 because of the 'same-origin' policy, the browser cannot inspect the DOM of embedded frames/iframes where the
//source is on a different 'origin'. if that happens, catch the exception and make sure we return a valid value for maxHeight
page.util.getMaxContentHeight = function( iframeElement )
{
  var maxHeight = iframeElement.contentWindow.document.body.scrollHeight;
  var frameElements;
  var iframeElements;
  if ( iframeElement.contentDocument )
  {
    // getElementsByTagName() returns a NodeList object, which is immutable and cannot easily be converted to an array
    frameElements = iframeElement.contentDocument.getElementsByTagName("frame");
    iframeElements = iframeElement.contentDocument.getElementsByTagName("iframe");
  }

  var i = 0;
  var frameHeight;
  var frameElement;

  try
  {
    for( i = 0; i < frameElements.length; i++ )
    {
      frameElement = frameElements[i];

      if( frameElement.contentWindow && frameElement.contentWindow.document && frameElement.contentWindow.document.body )
      {
        frameHeight = frameElement.contentWindow.document.body.scrollHeight;
      }

      if( frameHeight > maxHeight )
      {
        maxHeight = frameHeight;
      }
    }
  }
  catch (e)
  {
    // ignore - see same-origin comment at the top of this function
  }

  try
  {
    for( i = 0; i < iframeElements.length; i++ )
    {
      frameElement = iframeElements[i];

      if( frameElement.contentWindow && frameElement.contentWindow.document && frameElement.contentWindow.document.body )
      {
        frameHeight = frameElement.contentWindow.document.body.scrollHeight;
      }

      if( frameHeight > maxHeight )
      {
        maxHeight = frameHeight;
      }
    }
  }
  catch (e)
  {
    // ignore - see same-origin comment at the top of this function
  }

  return maxHeight;
};

//LRN-67276 because of the 'same-origin' policy, the browser cannot inspect the DOM of embedded frames/iframes where the
//source is on a different 'origin'. if that happens, catch the exception and make sure we return a valid value for maxWidth
page.util.getMaxContentWidth = function( iframeElement )
{
  var maxWidth = iframeElement.contentWindow.document.body.scrollWidth;
  var frameElements;
  var iframeElements;
  if ( iframeElement.contentDocument )
  {
    // getElementsByTagName() returns a NodeList object, which is immutable and cannot easily be converted to an array
    frameElements = iframeElement.contentDocument.getElementsByTagName("frame");
    iframeElements = iframeElement.contentDocument.getElementsByTagName("iframe");
  }

  var i = 0;
  var frameWidth;
  var frameElement;

  try
  {
    for( i = 0; i < frameElements.length; i++ )
    {
      frameElement = frameElements[i];

      if( frameElement.contentWindow && frameElement.contentWindow.document && frameElement.contentWindow.document.body )
      {
        frameWidth = frameElement.contentWindow.document.body.scrollWidth;
      }

      if( frameWidth > maxWidth )
      {
        maxWidth = frameWidth;
      }
    }
  }
  catch (e)
  {
    // ignore - see same-origin comment at the top of this function
  }

  try
  {
    for( i = 0; i < iframeElements.length; i++ )
    {
      frameElement = iframeElements[i];

      if( frameElement.contentWindow && frameElement.contentWindow.document && frameElement.contentWindow.document.body )
      {
        frameWidth = frameElement.contentWindow.document.body.scrollWidth;
      }

      if( frameWidth > maxWidth )
      {
        maxWidth = frameWidth;
      }
    }
  }
  catch (e)
  {
    // ignore - see same-origin comment at the top of this function
  }

  return maxWidth;
};

page.subheaderCleaner =
{
  init : function( entityKind )
  {
  var allHidden = true;
  var firstUl = null;
  var className = 'portletList-img courseListing ' + entityKind;
  $A( document.getElementsByClassName( className ) ).each( function( ul ) {
      if ( !ul.down() )
      {
        ul.previous( 'h3' ).hide();
        ul.hide();
        if ( !firstUl )
        {
          firstUl = ul;
        }
      }
      else
      {
        allHidden = false;
      }
    });
    if ( allHidden && firstUl )
    {
      firstUl.up( 'div' ).previous( 'div' ).show();
    }
  }
};

 /**
  * Set up any JavaScript that will always be run on load (that doesn't depend on
  * any application logic / localization) here.
  *
  * Please leave this at the bottom of the file so it's easy to find.
  *
  */
FastInit.addOnLoad( function()
{
  Event.observe( document.body, "click", page.ContextMenu.closeAllContextMenus.bindAsEventListener( window ) );

  Event.observe( document.body, "click", page.ContextMenu.alignArrowsInBreadcrumb.bindAsEventListener( window ) );

  Event.observe( document.body, 'keydown', function(event) {
    var key = event.keyCode || event.which;
    if ( key == 116 )  // reload current page on F5 key press
    {
      Event.stop( event );  // prevent browser from reloading complete frameset
      if ( Prototype.Browser.IE )
      {
        event.keyCode = 0;
      }
      (function() { window.location.reload( true ); }.defer());
      return false;
    }
  });

  page.util.initFlyoutMenuBehaviourForListActionMenuItems();

  if ( $('breadcrumbs') )
  {
    new page.BreadcrumbExpander($('breadcrumbs'));
    // If we're in the content wrapper, hide the content wrapper breadcrumb frame
    // so that we don't get stacked breadcrumbs.
    if ( window.name === 'contentFrame' )
    {
      var parent = window.parent;
      if ( parent )
      {
        var frameset = parent.document.getElementById( 'contentFrameset' );
        if ( frameset )
        {
          frameset.rows = "*,100%";
        }
      }
    }
  }

  var contentPane = $('contentPanel') || $('portalPane');
  if ( contentPane )
  {
    new page.LightboxInitializer( 'lb', contentPane );
  }

  // add a label for inventory table checkboxes, if needed
  $A(document.getElementsByTagName("table")).each( function( table )
  {
    if ( !page.util.hasClassName( table, 'inventory' ) )
    {
      return;
    }
    var rows = table.rows;
    if ( rows.length < 2 )
    {
      return;
    }
    for (var r = 0, rlen = rows.length - 1; r < rlen; r++)
    {
      var cells = rows[r+1].cells; // skip header row
      for (var c = 0, clen = cells.length; c < clen; c++)
      {
        var cell = $(cells[c]);
        var inp = cell.down('input');

        if ( !inp || ( inp.type != 'checkbox' && inp.type != 'radio' ) )
        {
          // We're only looking for checkbox/radio cells to label, so move on
          continue;
        }

        var lbl = cell.down('label');

        if (lbl && !lbl.innerHTML.blank())
        {
          break; // skip cells that already have a non-blank label
        }

        if ( !lbl )
        {  // add new label to checkbox
          lbl = new Element('label', {htmlFor: inp.id} );
          lbl.addClassName('hideoff');
          cell.insert({bottom:lbl});
        }
        var headerCell = $(cell.parentNode).down('th');
        if ( !headerCell )
        {
          break; // skip rows without header cell
        }

        // create a temporary clone of the header cell and remove any hidden divs I.e. context menus
        var tempCell = $(headerCell.cloneNode(true));
        var tempCellDivs = tempCell.getElementsByTagName("div");
        for ( var i = 0; i < tempCellDivs.length; i++ )
        {
          var d = tempCellDivs[i];
          if ( d && !$(d).visible() )
          {
            d.remove();
          }
        }
        var lblBody = tempCell.innerHTML.replace( /<\/?[^>]*>/g, '' );  // strip html tags from header
        lblBody = page.bundle.getString('inventoryList.select.item', lblBody);
        lbl.update( lblBody );  // set label to header contents (minus tags)
        break;
      }
    }
  });

  //set default font sizes to display text. hack to fix IE7 default font size issue.
  var sizes = {1:'xx-small', 2:'x-small', 3:'small', 4:'medium', 5:'large', 6:'x-large', 7:'xx-large'};
  var fonts = document.getElementsByTagName('font');
  for ( var i = 0; i < fonts.length; i++ )
  {
    var font = fonts[i];
    if ( font.size )
    {
      // Since some font elements may be manually created by end users we have to handle random
      // values in here.
      if (!font.size.startsWith("+") && !font.size.startsWith("-"))
      {
        var fsize = parseInt(font.size, 10);
        if (fsize > 0 && fsize < 8)
        {
          font.style.fontSize = sizes[fsize];
        }
      }
    }
  }

  page.scrollToEnsureVisibleElement();
  page.isLoaded = true;

});

/**
 * Class for adding an insertion marker within a list
 */
page.ListInsertionMarker = Class.create();
page.ListInsertionMarker.prototype =
{
  initialize: function( listId, position, key, text )
  {
    var list = $(listId);
    var listElements = list.childElements();
    // create a marker list item
    var marker = new Element('li',{'id':listId+':'+key, 'class':'clearfix separator' });
    marker.update('<h3 class="item" id=""><span class="reorder editmode"><span><img alt="" src="/images/ci/icons/generic_updown.gif"></span></span><span class="line"></span><span class="text">'+text+'</span></h3>');
    //marker.setStyle({  position: 'relative', minHeight: '10px', padding: '0px', background: '#CCCCCC' });
    position = ( position > listElements.length ) ? listElements.length : position;

    // add marker to list
    if (listElements.length === 0)
    {
      list.insert({top:marker}); // add marker to top of empty list
    }
    else if (listElements.length == position)
    {
      list.insert({bottom:marker});  // add marker after last element
    }
    else
    {
      listElements[position].insert({before:marker});  // add marker before element at position
    }

    var select = $('reorderControls'+listId).down('select');
    // add a option for the marker to the keyboard repostioning select, if any
    if (select)
    {
      var option = new Element('option',{'value':key}).update( '-- '+text+' --' );
      if (listElements.length === 0)
      {
        select.insert({top:option});
      }
      else if (listElements.length == position)
      {
        select.insert({bottom:option});
      }
      else
      {
        $(select.options[position]).insert({before:option});
      }
    }
  }
};

page.scrollToEnsureVisibleElement = function( )
{
  var params = window.location.search.parseQuery();
  var ensureVisibleId = params.ensureVisibleId;
  if ( !ensureVisibleId )
  {
    return;
  }
  var ensureVisibleElement = $(ensureVisibleId);
  if ( !ensureVisibleElement )
  {
    return;
  }
  var pos = ensureVisibleElement.cumulativeOffset();
  var scrollY = pos.top;
  var bodyHeight = $( document.body ).getHeight();
  if (scrollY + ensureVisibleElement.getHeight() < bodyHeight)
  {
    return; // element is already visible
  }

  var receipt = $('inlineReceipt_good');
  if ( receipt && receipt.visible() ) // pin receipt to top
  {
    var offset = receipt.cumulativeOffset();
    offset.top = globalNavigation.getNavDivHeight();
    var w = parseInt(receipt.getStyle('width'), 10);
    if ( Prototype.Browser.IE ) // width in IE includes border & padding, need to remove it
    {
      var bw = parseInt(receipt.getStyle('borderLeftWidth'), 10) + parseInt(receipt.getStyle('borderRightWidth'), 10);
      var pw = parseInt(receipt.getStyle('paddingLeft'), 10) + parseInt(receipt.getStyle('paddingRight'), 10);
      w = w - bw - pw;
    }
    receipt.setStyle({
      position:"fixed",
      zIndex:"1000",
      left: offset.left + "px",
      top: offset.top + "px",
      width: w + "px"});
    scrollY = scrollY -  2 * receipt.getHeight();
  }
  // scroll window to show ensureVisibleElement
  window.location = '#' + ensureVisibleId;
};

/**
 * Recursively walks up the frameset stack asking each window to change their
 * document.domain attribute in anticipation of making a cross-site scripting
 * call to an LMS integration.
 *
 * <p>This should only be called from popup windows, as changing the document.domain
 * value of a window that is going to be reused later could do surprising things.
 *
 * @param domain Domain name shared by the Learn and LMS servers.
 */
page.setLmsIntegrationDomain = function( domain )
{
  if ( '' == domain )
  {
    return;
  }

  try
  {
    if ( parent.page.setLmsIntegrationDomain )
    {
      parent.page.setLmsIntegrationDomain( domain );
  }
  }
  catch ( err ) { /* Ignore */ }

  document.domain = domain;
};

page.refreshTopFrame = function()
{
  if ( window.top.nav )
  {
    window.top.nav.location.reload();
  }
};

// See BreadcrumbBarRenderer.java for code that calls this method.
page.rewriteTaskStatusUntilDone = function( spanId, taskId, courseId )
{
  var theSpan = $(spanId);
  if (theSpan)
  {
    new Ajax.Request("/webapps/blackboard/execute/getSystemTaskStatus?taskId=" + taskId + "&course_id=" + courseId ,
                     {
                       method: 'post',
                       onSuccess: function(transport, json)
                       {
                         var result = transport.responseText.evalJSON( true );
                         theSpan = $(spanId); // reload it just in case it was removed between the request and response
                         if (theSpan)
                         {
                           theSpan.update(result.text);
                           if (result.complete == "false")
                           {
                             setTimeout(function() {page.rewriteTaskStatusUntilDone(spanId, taskId, courseId);}, 3000);
                           }
                         }
                       },
                       onFailure: function(transport, json)
                       {
                         theSpan = $(spanId); //reload the span as above
                         if (theSpan)
                         {
                           theSpan.hide();
                           $(spanId+'error').show();
                         }
                       }
                     });
  }
};

/*
 * Clean up the task id which associated with the specified course, so that the inline warning does not show up again
 */
page.cleanTaskId = function( courseId )
{
  // we don't care about the result, at worse it will display again on the next page
  var url = "/webapps/blackboard/execute/courseMain?action=cleanTaskId&course_id=" + courseId +
            "&sessionId=" + getCookie( 'JSESSIONID' );
  new Ajax.Request( url, { method: 'post' } );
};

//that doesn't then any code utilizing these methods will not work 'as expected'. Current usage
//as/of the writing of this code is "ok" with that - the user won't get the perfect experience but it won't completely fail either.
page.putInSessionStorage = function( key, value )
{
  if ( typeof sessionStorage !== 'undefined' )
  {
    sessionStorage[ getCookie( 'JSESSIONID' ) + key ] = value;
  }
};

// any code utilizing these methods must have separately included cookie.js
// since we don't always include cookie.js
page.getFromSessionStorage = function( key )
{
  if ( typeof sessionStorage !== 'undefined' )
  {
    return sessionStorage[ getCookie( 'JSESSIONID' ) + key ];
  }
  return undefined;
};

page.aria = {};

page.aria.show = function ( element )
{
  $(element).show();
  element.setAttribute("aria-expanded", "true");
};

page.aria.hide = function ( element )
{
  $(element).hide();
  element.setAttribute("aria-expanded", "false");
};

page.aria.toggle = function ( element )
{
  if (Element.visible($(element)))
  {
    page.aria.hide(element);
  }
  else
  {
    page.aria.show(element);
  }
};

page.util.isIE = function ()
{
  return ( navigator.userAgent.toLowerCase().indexOf( "msie" ) != -1 );
};

page.util.isFirefox = function()
{
  return ( navigator.userAgent.toLowerCase().indexOf( "firefox" ) != -1 );
};

/*
 * We'd probably never want to set an empty title on an html element but unfortunately
 * due to a browser rendering issue(FF 20), there currently is a practical reason to do
 * so. By setting a title to a 'blank space'(!), we can prevent the title from the parent
 * element to show up when the user is interacting with a child {code}element{code}.
 * Currently this is necessary only for FF, and it can be changed as a need arise but you
 * MUST NOT set an empty space title in IE/Chrome because they will render it as an empty
 * title tooltip, which we don't want.
 * NOTE : An empty space title will be set only if a title is not already set to something
 *   other than an empty String.
 * Bug Ref. : LRN-67140
 */
page.util.setEmptyTitle = function( element )
{
  if( page.util.isFirefox() )
  {
    if ( element.title.blank() )
    {
      page.util.setTitle( element, ' ' );
    }
  }
};

page.util.setTitle = function( element, title )
{
  element.setAttribute( 'title', title );
};


page.FocusTrap = Class.create();
page.FocusTrap.prototype =
{
  /**
   * this method injects 2 invisible spans used to trap the focus inside this given div.  Once you tab into the div you
   * stay inside the div - should only be used on pages where you've dynamically loaded the div and placed the user into it
   * to start (otherwise on a full page reload this could be confusing)
   *
   * @param options.mainDiv - the id of the div where we want the focus to be trapped for Accessibility
   * @param options.initialSelector - selector to find the first element to focus on at the top of the div
   * @param options.finalSelector - optional selector to find the last element to focus on (if known)
   */
    initialize : function( options )
    {
      this.options = Object.extend( {
        mainDiv : null,
        initialSelector : null,
        finalSelector : null
      }, options);
      var mainDiv = $( this.options.mainDiv );
      // insert span at the end of the div
      this.endOfDivIndicator = this.getIndicatorSpan('Bot');
      mainDiv.insertBefore( this.endOfDivIndicator, null );
      this.startOfDivIndicator = this.getIndicatorSpan('Top');
      mainDiv.insertBefore( this.startOfDivIndicator, mainDiv.childNodes[0] );
      this.initialSelector = mainDiv.down( this.options.initialSelector );
      if ( this.options.finalSelector )
      {
        this.finalSelector = mainDiv.down( this.options.finalSelector );
      }

      // Note: Going on both keydown and keyup so that you can hold down the TAB key and have it work properly.
      Event.observe( mainDiv, 'keydown', this.handleKeyDown.bind(this) );
      Event.observe( mainDiv, 'keyup', this.handleKeyDown.bind(this) );
    },

    getIndicatorSpan : function (loc)
    {
      var span = new Element( "span" ).addClassName( "focustrap" );
      span.setAttribute( "tabIndex", "0" );
      span.id = 'focusTrap_' + loc + this.options.mainDiv;
      return span
    },

    handleKeyDown : function( event )
    {
      var code = event.keyCode || event.which;
      var elem = event.element();
      if ( code == Event.KEY_TAB)
      {
        // Note that while this logic does "work" it seems to have an issue we might be able to live with:
        // 1) Going backwards this has an explicit 'blank' item that you tab to which will be confusing (i.e. focusing on the end-of-div indicator
        // doesn't help unless we shift-tab again).  We might be able to make this work better by simulating another copy of event... another day though.
        if (event.shiftKey && this.startOfDivIndicator == elem)
        {
          // Going backwards
          if ( this.finalSelector )
          {
            page.util.focusAndScroll( this.finalSelector );
          }
          else
          {
            page.util.focusAndScroll( this.endOfDivIndicator );
            /* Tried a few things - leaving them here as work-in-progress... it doesn't work.
            var evt = document.createEvent( "KeyboardEvent" );
            if ( evt.initKeyEvent )
            {
              evt.initKeyEvent( "keyup", true, true, null, 0, 0, true, 0, Event.KEY_TAB,  Event.KEY_TAB );
            }
            else
            {
              evt.initKeyboardEvent( "keyup", true, true, null, 0, 0, true, 0, Event.KEY_TAB,  Event.KEY_TAB );
            }
            this.endOfDivIndicator.dispatchEvent( evt );
            */
          }
        }
        else if (!event.shiftKey && this.endOfDivIndicator == elem)
        {
          // going forwards.
          page.util.focusAndScroll(this.initialSelector);
        }
      }
    }
};

}
