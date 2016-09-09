/** The collection of classes and methods that comprise the QuickLinks core implementation. */
var quickLinks =
{
    constants :
    {
        /** Constant identifier for identifying frame communications specific to this function */
        APP_CONTEXT : 'QuickLinks',

        /** Hotkey for the Quick Links UI */
        APP_HOTKEY :
        {
            accesskey : 'l',
            modifiers :
            {
                shift : true,
                alt : true
            }
        },

        // Constants for various window actions
        SET : 'set',
        ADD : 'add',
        REMOVE : 'remove',
        SHOW : 'show',
        ACTIVATE : 'activate',
        REMOVE_ALL : 'removeAll',
        DEFINE_KEY : 'defineKey',

        /** The order in which we process windows */
        WINDOW_ORDER_FOR_HEADERS :
        {
            mybbCanvas : 1,
            WFS_Files : 2,
            content : 3,
            WFS_Navigation : 4,
            nav : 5,
            'default' : 100
        },

        /** ARIA roles that we consider 'landmarks' */
        ARIA_LANDMARK_ROLES :
        {
            application : true,
            banner : true,
            complementary : true,
            contentinfo : true,
            form : true,
            main : true,
            navigation : true,
            search : true
        }
    },

    vars :
    {
        /** reference to lightbox object */
        lightbox : null,

        /** cached quick link data */
        data : $H(),

        /** Messages must originate from one of these sources */
        trustedProviders : $H(),

        // Cached references to HTML elements
        lightboxLandmarkList : null,
        lightboxLandmarkSection : null,
        lightboxHeaderList : null,
        lightboxHeaderSection : null,
        lightboxHotkeyList : null,
        lightboxHotkeySection : null,

        /** The instance of helper for the window containing this script */
        helper : null
    },

    /** Initialization of the UI/core implementation */
    initialize : function( trustedProviders )
    {
      // Initialize a lightbox to show collected links
      quickLinks.vars.lightbox = new lightbox.Lightbox(
      {
          title : page.bundle.getString( 'quick_links.lightbox_title' ),
          contents :
          {
            id : 'quickLinksLightboxDiv'
          },
          'dimensions' :
          {
              w : 800,
              h : 600
          }
      } );

      // Add trusted content providers from whom we accept messages
      if ( trustedProviders )
      {
        trustedProviders.each( function( tp )
        {
          if ( tp )
          {
            quickLinks.vars.trustedProviders.set( tp, true );
          }
        } );
      }
      quickLinks.vars.trustedProviders.set( quickLinks.util.getCurrentOrigin(), true );

      // Add listener for frame communications
      Event.observe( window.top, 'message', quickLinks.messageHelper.onMessageReceived );

      // When link is active, modify the wrapping div
      var wrapperDiv = $( 'quick_links_wrap' );
      Event.observe( $( 'quick_links_lightbox_link' ), 'focus', function( event )
      {
        this.addClassName( 'quick_link_wrap_focus' );
      }.bind( wrapperDiv ) );
      Event.observe( $( 'quick_links_lightbox_link' ), 'blur', function( event )
      {
        this.removeClassName( 'quick_link_wrap_focus' );
      }.bind( wrapperDiv ) );

      // Cache references to some elements
      quickLinks.vars.lightboxLandmarkList = $( 'quick_links_landmark_list' );
      quickLinks.vars.lightboxHeaderList = $( 'quick_links_heading_list' );
      quickLinks.vars.lightboxHotkeyList = $( 'quick_links_hotkey_list' );
      quickLinks.vars.lightboxLandmarkSection = $( 'quick_links_landmarks_section' );
      quickLinks.vars.lightboxHeaderSection = $( 'quick_links_headings_section' );
      quickLinks.vars.lightboxHotkeySection = $( 'quick_links_hotkeys_section' );
    },

    /** Factory method that creates a Helper for frames that require it */
    createHelper : function()
    {
      // If this is not a popup and this is not a top-level window without the quick links UI link
      // (for instance if someone opened one of the frames in a separate tab)
      if ( !window.opener && ( window.top !== window || $( 'quick_links_lightbox_link' ) ) )
      {
        if ( !quickLinks.vars.helper )
        {
          quickLinks.vars.helper = new quickLinks.Helper();
        }
      }
    },

    /**
     * Add a hot key definition.
     * 
     * @param hotkey is an object with keys label, accesskey, and modifiers. modifiers is an object with one or more of
     *          the keys -- control, shift, and alt -- set to a value expression that evaluates to true.
     * @param sourceId may be null and will default to the string used for all other quicklinks from the current window.
     */
    addHotKey : function( sourceId, hotkey )
    {
      if ( hotkey )
      {
        quickLinks.messageHelper.postMessage( window.top,
        {
            sourceId : sourceId || quickLinks.util.getCurrentWindowId(),
            context : quickLinks.constants.APP_CONTEXT,
            action : quickLinks.constants.ADD,
            hotkeys : [ hotkey ]
        }, quickLinks.util.getCurrentOrigin() );
      }
    },

    /**
     * Add hot key definition. See #addHotKey.
     * 
     * @param hotkeys hotkeys is an array of hotkey definitions as described in #addHotKey.
     */
    addHotKeys : function( sourceId, hotkeys )
    {
      if ( hotkeys )
      {
        quickLinks.messageHelper.postMessage( window.top,
        {
            sourceId : sourceId || quickLinks.util.getCurrentWindowId(),
            context : quickLinks.constants.APP_CONTEXT,
            action : quickLinks.constants.ADD,
            hotkeys : hotkeys
        }, quickLinks.util.getCurrentOrigin() );
      }
    },

    /**
     * Removes all content for the specified source. If sourceId evaluates to false, all content for the window that
     * calls this method will be removed.
     */
    removeAll : function( sourceId )
    {
      quickLinks.messageHelper.postMessage( window.top,
      {
          sourceId : sourceId,
          context : quickLinks.constants.APP_CONTEXT,
          action : quickLinks.constants.REMOVE_ALL
      }, quickLinks.util.getCurrentOrigin() );
    },

    /** A set of functions that deal with inter-window communication */
    messageHelper :
    {
        /** The handler for messages sent to window.top from other windows (or self) */
        onMessageReceived : function( event )
        {
          var data = quickLinks.messageHelper.translateData( event.data );
          if ( data && data.context === quickLinks.constants.APP_CONTEXT &&
               quickLinks.vars.trustedProviders.get( event.origin ) )
          {
            if ( data.action === quickLinks.constants.SET )
            {
              quickLinks.dataHelper.setQuickLinks( event.source, event.origin, data );
              quickLinks.messageHelper.postHotkey( event.source );
            }
            else if ( data.action === quickLinks.constants.SHOW )
            {
              quickLinks.lightboxHelper.toggleLightbox( data.sourceId, data.activeElementId, event.origin );
            }
            else if ( data.action === quickLinks.constants.REMOVE_ALL )
            {
              if ( data.sourceId )
              {
                quickLinks.vars.data.unset( data.sourceId );
              }
              else
              {
                // Remove all content from calling window
                quickLinks.vars.data.values().each( function( value )
                {
                  if ( value.window === event.source )
                  {
                    quickLinks.vars.data.unset( value.sourceId );
                  }
                } );
              }
            }
            else if ( data.action === quickLinks.constants.ADD )
            {
              quickLinks.dataHelper.addQuickLinks( event.source, event.origin, data );
            }
            else if ( data.action === quickLinks.constants.REMOVE )
            {
              quickLinks.dataHelper.removeQuickLinks( data );
            }
          }
        },

        /** Posts the supplied message to the target window */
        postMessage : function( w, data, target )
        {
          if ( w.postMessage )
          {
            if ( Prototype.Browser.IE && data && typeof ( data ) !== 'string' )
            {
              data = Object.toJSON( data );
            }
            w.postMessage( data, target );
          }
        },

        /** Handle IE's behavior of passing objects as strings */
        translateData : function( data )
        {
          if ( Prototype.Browser.IE && typeof ( data ) === 'string' && data.isJSON() )
          {
            data = data.evalJSON();
          }
          return data;
        },

        /** Sends a message the supplied window instance about the hot-key defined for the QuickLinks UI */
        postHotkey : function( w )
        {
          quickLinks.messageHelper.postMessage( w,
          {
              sourceId : quickLinks.util.getCurrentWindowId(),
              context : quickLinks.constants.APP_CONTEXT,
              action : quickLinks.constants.DEFINE_KEY,
              key : quickLinks.constants.APP_HOTKEY
          }, '*' );
        },

        /** Posts a message requesting the activation of the specified element */
        activateElement : function( sourceId, targetElementId, origin, isQuickLink )
        {
          // Reset focus
          quickLinks.vars.lightbox.cfg.onClose = null;
          quickLinks.vars.lightbox.cfg.focusOnClose = null;

          // Close lightbox
          quickLinks.lightboxHelper.closeLightbox();

          var windowEntry = quickLinks.vars.data.get( sourceId );

          // Focus on the target window
          windowEntry.window.focus();

          // Send a message to that window
          if ( windowEntry )
          {
            quickLinks.messageHelper.postMessage( windowEntry.window,
            {
                sourceId : quickLinks.util.getCurrentWindowId(),
                context : quickLinks.constants.APP_CONTEXT,
                action : quickLinks.constants.ACTIVATE,
                id : targetElementId,
                isQuickLink : isQuickLink
            }, origin );
          }
        }
    },

    /** A set of functions that deal with the management of the quick links data */
    dataHelper :
    {
        /** Create a hash for the hotkey definition */
        getHotKeyHash : function( key )
        {
          var result = key.accesskey;
          if ( key.modifiers )
          {
            result += key.modifiers.alt ? '-A' : '';
            result += key.modifiers.control ? '-C' : '';
            result += key.modifiers.shift ? '-S' : '';
          }
          return result;
        },

        /** Remove supplied quick links */
        removeQuickLinks : function( data )
        {
          var value = quickLinks.vars.data.get( data.sourceId );
          if ( value )
          {
            quickLinks.dataHelper.removeSelectionsById( value.headers, data.headers );
            quickLinks.dataHelper.removeSelectionsById( value.landmarks, data.landmarks );

            var selection =
            {};
            if ( data.hotkeys && value.hotkeys )
            {
              data.hotkeys.each( function( hotkey )
              {
                selection[ hotkey.id || quickLinks.dataHelper.getHotKeyHash( hotkey ) ] = true;
              } );
            }
            quickLinks.dataHelper.removeSelectionsById( value.hotkeys, selection );
          }
        },

        /** Remove those values from 'master' whose 'id' values exist in the 'selections' object */
        removeSelectionsById : function( master, selections )
        {
          if ( master && selections )
          {
            master = master.filter( function( i )
            {
              return i.id && !selections[ i.id ];
            } );
          }
          return master;
        },

        /** Overwrite any existing quick links */
        setQuickLinks : function( sourceWindow, origin, data )
        {
          quickLinks.vars.data.set( data.sourceId,
          {
              'window' : sourceWindow,
              sourceId : data.sourceId,
              origin : origin,
              headers : data.headers || [],
              landmarks : data.landmarks || [],
              hotkeys : quickLinks.dataHelper.normalizeHotKeys( data.hotkeys ) || []
          } );
        },

        /** Normalize the hotkey definition by adding the hash as an id if an id was not provided */
        normalizeHotKeys : function( hotkeys )
        {
          if ( hotkeys )
          {
            hotkeys.each( function( hotkey )
            {
              if ( !hotkey.id )
              {
                hotkey.id = quickLinks.dataHelper.getHotKeyHash( hotkey.key );
              }
            } );
          }
          return hotkeys;
        },

        /** Add quick links */
        addQuickLinks : function( sourceWindow, sourceOrigin, data )
        {
          var value = quickLinks.vars.data.get( data.sourceId );
          if ( !value )
          {
            value =
            {
                'window' : sourceWindow,
                sourceId : data.sourceId,
                origin : sourceOrigin,
                headers : [],
                landmarks : [],
                hotkeys : []
            };
            quickLinks.vars.data.set( data.sourceId, value );
          }
          if ( data.headers )
          {
            value.headers = value.headers.concat( data.headers );
          }
          if ( data.landmarks )
          {
            value.landmarks = value.landmarks.concat( data.landmarks );
          }
          if ( data.hotkeys )
          {
            value.hotkeys = value.hotkeys.concat( quickLinks.dataHelper.normalizeHotKeys( data.hotkeys ) );
          }
        }
    },

    /** A set of functions that deal with the management of the lightbox UI */
    'lightboxHelper' :
    {
        /** Toggles the QuickLinks lightbox state */
        toggleLightbox : function( targetWindowId, activeElementId, origin )
        {
          if ( lightbox.getCurrentLightbox() === quickLinks.vars.lightbox )
          {
            quickLinks.lightboxHelper.closeLightbox();
          }
          else
          {
            quickLinks.lightboxHelper.openLightbox( targetWindowId, activeElementId, origin );
          }
        },

        /** Opens the QuickLinks lightbox */
        openLightbox : function( targetWindowId, activeElementId, origin )
        {
          quickLinks.lightboxHelper.closeAllLightboxes();

          if ( targetWindowId && activeElementId && origin )
          {
            quickLinks.vars.lightbox.cfg.focusOnClose = null;
            quickLinks.vars.lightbox.cfg.onClose = function()
            {
              quickLinks.messageHelper.activateElement( targetWindowId, activeElementId, origin, false );
            }.bind( window.top );
          }
          else
          {
            quickLinks.vars.lightbox.cfg.onClose = null;
            quickLinks.vars.lightbox.cfg.focusOnClose = document.activeElement;
          }

          quickLinks.lightboxHelper.populateLightbox();
          quickLinks.vars.lightbox.open();
        },

        /** Closes the QuickLinks lightbox */
        closeLightbox : function()
        {
          quickLinks.lightboxHelper.clearLightboxContents();
          quickLinks.vars.lightbox.close();
        },

        /**
         * Close all open lightboxes. This will work only for lightboxes created using the core lightbox.js library and
         * opened from a frame that shares the same origin as window.top
         */
        closeAllLightboxes : function( w )
        {
          if ( !w )
          {
            w = window.top;
          }
          try
          {
            // Security errors appear in console even if we catch all exceptions, so try to avoid them
            if ( ( quickLinks.util.getCurrentOrigin() === quickLinks.util.getWindowOrigin( w ) ) && w.lightbox &&
                 w.lightbox.closeCurrentLightbox )
            {
              w.lightbox.closeCurrentLightbox();
            }
          }
          catch ( e )
          {
            // Ignore all exceptions -- probably caused by window of different origin
          }
          for ( var i = 0, iMax = w.frames.length; i < iMax; ++i )
          {
            quickLinks.lightboxHelper.closeAllLightboxes( w.frames[ i ] );
          }
        },

        /** Empties all content from the QuickLinks lightbox */
        clearLightboxContents : function()
        {
          quickLinks.vars.lightboxHeaderList.innerHTML = '';
          quickLinks.vars.lightboxLandmarkList.innerHTML = '';
          quickLinks.vars.lightboxHotkeyList.innerHTML = '';
        },

        /** Add known Quick Links to the lightbox UI after checking that they are still available on the page */
        populateLightbox : function()
        {
          if ( quickLinks.vars.data )
          {
            // Clear existing content
            quickLinks.lightboxHelper.clearLightboxContents();

            var keys = quickLinks.vars.data.keys();
            keys.sort( function( a, b )
            {
              var aWeight = quickLinks.constants.WINDOW_ORDER_FOR_HEADERS[ a ] ||
                            quickLinks.constants.WINDOW_ORDER_FOR_HEADERS[ 'default' ];
              var bWeight = quickLinks.constants.WINDOW_ORDER_FOR_HEADERS[ b ] ||
                            quickLinks.constants.WINDOW_ORDER_FOR_HEADERS[ 'default' ];
              return aWeight - bWeight;
            } );

            keys.each( function( key )
            {
              var value = quickLinks.vars.data.get( key );
              if ( value.window.closed )
              {
                delete quickLinks.vars.data[ key ];
                return;
              }

              if ( value.landmarks )
              {
                value.landmarks.each( quickLinks.lightboxHelper.populateLandmark.bind( value ) );
              }
              if ( value.headers )
              {
                value.headers.each( quickLinks.lightboxHelper.populateHeader.bind( value ) );
              }
              if ( value.hotkeys )
              {
                value.hotkeys.each( quickLinks.lightboxHelper.populateHotkey.bind( value ) );
              }
            } );

            // Display only sections that have content
            quickLinks.lightboxHelper.checkSection( quickLinks.vars.lightboxHeaderList,
                                                    quickLinks.vars.lightboxHeaderSection );
            quickLinks.lightboxHelper.checkSection( quickLinks.vars.lightboxLandmarkList,
                                                    quickLinks.vars.lightboxLandmarkSection );
            quickLinks.lightboxHelper.checkSection( quickLinks.vars.lightboxHotkeyList,
                                                    quickLinks.vars.lightboxHotkeySection );
          }
        },

        /** Figure out if the element has content and display the corresponding section */
        checkSection : function( el, section )
        {
          if ( el.empty() )
          {
            section.hide();
          }
          else
          {
            section.show();
          }
        },

        /** Adds a single landmark to the lightbox UI */
        populateLandmark : function( landmark )
        {
          var li = document.createElement( 'li' );
          quickLinks.vars.lightboxLandmarkList.appendChild( li );

          var a = $( document.createElement( 'a' ) );
          li.appendChild( a );
          a.innerHTML = landmark.label;
          a.setAttribute( 'href', '#' );
          a.setAttribute( 'onclick', 'quickLinks.messageHelper.activateElement("' + this.sourceId + '", "' +
                                     landmark.id + '", "' + this.origin + '", true)' );
          var title = page.bundle.getString( 'quick_links.link.title', this.sourceId, landmark.label, landmark.type );
          a.setAttribute( 'title', title );
        },

        /** Adds a single header to the lightbox UI */
        populateHeader : function( heading )
        {
          var li = document.createElement( 'li' );
          quickLinks.vars.lightboxHeaderList.appendChild( li );
          li.setAttribute( 'class', 'quick_links_header_' + heading.type.toLowerCase() );

          var a = $( document.createElement( 'a' ) );
          li.appendChild( a );
          a.innerHTML = heading.label;
          a.setAttribute( 'href', '#' );
          a.setAttribute( 'onclick', 'quickLinks.messageHelper.activateElement("' + this.sourceId + '", "' +
                                     heading.id + '", "' + this.origin + '", true)' );
          var title = page.bundle.getString( 'quick_links.link.title', this.sourceId, heading.label, heading.type );
          a.setAttribute( 'title', title );
        },

        /** Adds a single hot-key definitions to the lightbox UI */
        populateHotkey : function( hotkey )
        {
          var span;
          var plus = ' ' + page.bundle.getString( 'quick_links.hotkey.combination_divider' ) + ' ';

          var li = document.createElement( 'li' );
          quickLinks.vars.lightboxHotkeyList.appendChild( li );

          var div = $( document.createElement( 'div' ) );
          li.appendChild( div );
          div.setAttribute( 'class', 'keycombo' );

          if ( hotkey.key.modifiers )
          {
            if ( hotkey.key.modifiers.shift )
            {
              span = $( document.createElement( 'span' ) );
              div.appendChild( span );
              span.setAttribute( 'class', 'presskey' );
              span.innerHTML = page.bundle.getString( 'quick_links.hotkey.shift' );

              div.appendChild( document.createTextNode( plus ) );
            }

            if ( hotkey.key.modifiers.control )
            {
              span = $( document.createElement( 'span' ) );
              div.appendChild( span );
              span.setAttribute( 'class', 'presskey' );
              span.innerHTML = page.bundle.getString( 'quick_links.hotkey.control' );

              div.appendChild( document.createTextNode( plus ) );
            }

            if ( hotkey.key.modifiers.alt )
            {
              span = $( document.createElement( 'span' ) );
              div.appendChild( span );
              span.setAttribute( 'class', 'presskey' );
              span.innerHTML = page.bundle.getString( 'quick_links.hotkey.alt' );

              div.appendChild( document.createTextNode( plus ) );
            }
          }

          span = $( document.createElement( 'span' ) );
          div.appendChild( span );
          span.setAttribute( 'class', 'presskey alpha' );
          span.innerHTML = hotkey.key.accesskey;

          div.appendChild( document.createElement( 'br' ) );
          div.appendChild( document.createTextNode( hotkey.label ) );
        }
    },

    /** General helper functions that don't belong elsewhere */
    'util' :
    {
        /** Whether the current frame/page has a Course menu */
        isCoursePage : function()
        {
          return $( 'courseMenuPalette_paletteTitleHeading' ) ? true : false;
        },

        /** Whether the current frame/page is on the Content Collection tab */
        isContentSystemPage : function()
        {
          return quickLinks.util.getCurrentWindowId() === 'WFS_Files';
        },

        /** Returns the origin string for the current window as understood by the window.postMessage API */
        getCurrentOrigin : function()
        {
          return quickLinks.util.getWindowOrigin( window );
        },

        /** Returns the origin string for the supplied window as understood by the window.postMessage API */
        getWindowOrigin : function( w )
        {
          var url = w.location.href;
          return url.substring( 0, url.substring( 8 ).indexOf( '/' ) + 8 );
        },

        /** A name identifying the current window. Not guaranteed to be unique. */
        getCurrentWindowId : function()
        {
          if ( !window.name )
          {
            window.name = Math.floor( ( Math.random() * 10e6 ) + 1 );
          }
          return window.name;
        },

        /** @return "mac" if the client is running on a Mac and "win" otherwise */
        isMacClient : function()
        {
          return navigator.platform.toLowerCase().startsWith( "mac" );
        },

        /** The modifiers for access keys for the current platform/browser */
        getDefaultModifiers : function()
        {
          return ( quickLinks.util.isMacClient() ) ?
          {
              control : true,
              alt : true
          } :
          {
              shift : true,
              alt : true
          };
        },

        /** Whether this aria role is a 'landmark' */
        isAriaLandmark : function( el )
        {
          var role = el.getAttribute( 'role' );
          return role && quickLinks.constants.ARIA_LANDMARK_ROLES[ role.toLowerCase() ];
        }
    },

    /**
     * Class used by all internally-sourced windows (anything that has a page tag that inherits from BasePageTag) to
     * communicate with quickLinks core
     */
    Helper : Class.create(
    {
        /** Constructor */
        initialize : function( config )
        {
          // Default values for configuration parameters.
          this.config = Object.extend(
          {
            trustedServer : quickLinks.util.getCurrentOrigin()
          }, config );

          Event.observe( window, 'message', this.onMessageReceived.bindAsEventListener( this ) );
          Event.observe( window, 'beforeunload', this.removeQuickLinks.bindAsEventListener( this ) );

          // Allow some time for other initialization to occur
          setTimeout( this.sendQuickLinks.bind( this ), 500 );
        },

        /** When window is unloaded */
        removeQuickLinks : function( event )
        {
          quickLinks.removeAll();
        },

        /** The handler for messages received from other window instances */
        onMessageReceived : function( event )
        {
          var data = quickLinks.messageHelper.translateData( event.data );
          if ( data && data.context === quickLinks.constants.APP_CONTEXT && event.origin === this.config.trustedServer )
          {
            if ( data.action === quickLinks.constants.ACTIVATE && data.id )
            {
              this.activateElement( $( data.id ), data.isQuickLink );
            }
            else if ( data.action === quickLinks.constants.DEFINE_KEY && data.key )
            {
              this.defineQuickLinksHotKey( event, data );
            }
          }
        },

        /** Defines the hotkey for the QuickLink UI */
        defineQuickLinksHotKey : function( event, data )
        {
          if ( this.keyDownHandler )
          {
            Event.stopObserving( document, 'keydown', this.keyDownHandler );
            this.keyDownHandler = null;
          }

          var source = event.source;
          var origin = event.origin;
          var key = data.key;

          this.keyDownHandler = function( ev )
          {
            var keyCode = ev.keyCode || ev.which;
            if ( ( String.fromCharCode( keyCode ).toLowerCase() === key.accesskey ) &&
                 ( !key.modifiers.shift || ev.shiftKey ) && ( !key.modifiers.alt || ev.altKey ) &&
                 ( !key.modifiers.control || ev.ctrlKey ) )
            {
              quickLinks.messageHelper.postMessage( source,
              {
                  sourceId : quickLinks.util.getCurrentWindowId(),
                  context : quickLinks.constants.APP_CONTEXT,
                  action : quickLinks.constants.SHOW,
                  activeElementId : document.activeElement ? $( document.activeElement ).identify() : null
              }, origin );
              ev.stop();
              return false;
            }
          }.bindAsEventListener( this );
          Event.observe( document, 'keydown', this.keyDownHandler );
        },

        /** Activates the specified element (focus or click as applicable) */
        activateElement : function( el, isQuickLink )
        {
          if ( el )
          {
            // Allow the element to accept focus temporarily
            var tabidx = el.getAttribute( 'tabindex' );
            if ( isQuickLink && !tabidx && tabidx !== 0 )
            {
              el.setAttribute( 'tabIndex', 0 );
            }

            // Pulsate for a few seconds if the element is visible
            if ( isQuickLink && el.visible() )
            {
              try
              {
                Effect.Pulsate( el );
              }
              catch ( e )
              {
                // Ignore all errors
              }
            }

            // Focus on the element
            el.focus();

            // Remove the tabindex so that we don't stop at this element later
            if ( isQuickLink && !tabidx && ( tabidx !== 0 ) )
            {
              el.setAttribute( 'tabIndex', Prototype.Browser.IE ? '-1' : '' );
            }
          }
        },

        /** Discovers quick links in the current window and sends them to the top window */
        sendQuickLinks : function()
        {
          var helper = this;

          var hotkeys = this.getElements( 'a[accesskey]', false, 'title' );
          if ( window.self === window.top )
          {
            hotkeys.push(
            {
                label : page.bundle.getString( 'quick_links.link_title' ),
                key : quickLinks.constants.APP_HOTKEY
            } );
          }
          var headers = this.getElements( [ 'h1', 'h2', 'h3', 'h4', 'h5', 'h6' ], true );
          if ( quickLinks.util.isCoursePage() || quickLinks.util.isContentSystemPage() )
          {
            headers = this.modifyHeaderOrder( headers );
          }
          var landmarks = this.getElements( '[role]', false, 'role', 'title', quickLinks.util.isAriaLandmark
              .bind( this ) );

          quickLinks.messageHelper.postMessage( window.top,
          {
              sourceId : quickLinks.util.getCurrentWindowId(),
              context : quickLinks.constants.APP_CONTEXT,
              action : quickLinks.constants.SET,
              headers : headers,
              landmarks : landmarks,
              hotkeys : hotkeys
          }, this.config.trustedServer );
        },

        /**
         * Find elements matching the supplied pattern, using the value of the attribute labelAttribute as the label.
         * Returns an array of Objects with each having the properties id, type, label, and key.
         */
        getElements : function( pattern, inspectAncestors, labelAttribute, parenAttribute, isValidQuickLink )
        {
          var helper = this;
          var result = [];
          var modifiers = quickLinks.util.getDefaultModifiers();
          $$( pattern ).each( function( el )
          {
            if ( !helper.isAvailableAsQuickLink( el, inspectAncestors ) )
            {
              return;
            }

            if ( isValidQuickLink && !isValidQuickLink( el ) )
            {
              return;
            }

            var id = el.getAttribute( 'id' );
            if ( !id )
            {
              id = el.identify();
            }
            var label = helper.getLabel( el, labelAttribute, parenAttribute );

            result.push(
            {
                id : id,
                type : el.tagName.toLowerCase(),
                label : label,
                key :
                {
                    modifiers : modifiers,
                    accesskey : el.getAttribute( 'accesskey' )
                }
            } );
          } );
          return result;
        },

        /** Whether the specified element should be shown in the QuickLinks UI */
        isAvailableAsQuickLink : function( element, inspectAncestors )
        {
          // Skip all checks if this is explicitly marked as a quick link or otherwise
          if ( element.hasClassName( 'quickLink' ) )
          {
            return true;
          }
          if ( element.hasClassName( 'hideFromQuickLinks' ) )
          {
            return false;
          }

          // If element is not visible, don't show it.
          if ( ( element.getStyle( 'zIndex' ) !== null ) || !element.visible() )
          {
            return false;
          }

          if ( inspectAncestors )
          {
            // Look for a hidden ancestor
            var elArray = element.ancestors();
            for ( var i = 0, iMax = elArray.length; i < iMax; ++i )
            {
              var el = elArray[ i ];
              var elName = el.tagName.toLowerCase();

              // Stop when we reach the body
              if ( elName === 'body' || elName === 'html' )
              {
                break;
              }

              if ( typeof el.visible === 'function' && !el.visible() )
              {
                return false;
              }
            }
          }

          return true;
        },

        /** Get the QuickLinks label for the specified element */
        getLabel : function( el, labelAttribute, parenAttribute )
        {
          var label = labelAttribute ? el.getAttribute( labelAttribute ) : null;
          if ( !label )
          {
            label = el.innerHTML.stripTags();
          }
          if ( label && parenAttribute )
          {
            var parenValue = el.getAttribute( parenAttribute );
            if ( parenValue )
            {
              label = page.bundle.getString( 'common.pair.paren', label, parenValue );
            }
          }
          return label;
        },

        /** Hack the order of headers for Course and Content System pages. It is Ugly, but it's also a requirement. */
        modifyHeaderOrder : function( headers )
        {
          if ( headers && headers.length > 0 )
          {
            var i, iMax;
            for ( i = 0, iMax = headers.length; i < iMax; ++i )
            {
              if ( headers[ i ].type.toLowerCase() === 'h1' )
              {
                break;
              }
            }
            if ( i !== 0 && i < iMax )
            {
              // move everything above the h1 to the bottom of the list
              var removed = headers.splice( 0, i );
              headers = headers.concat( removed );
            }
          }
          return headers;
        }
    } )
};