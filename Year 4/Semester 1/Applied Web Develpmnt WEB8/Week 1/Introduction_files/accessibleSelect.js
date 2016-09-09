var AccessibleSelect = {};

/**
 * Wire up the accessible event listeners to any <select>s on the page that have an onchange listener already
 */
AccessibleSelect.initializePage = function()
{
  var selects = document.getElementsByTagName("select");

  for ( var i = 0; i < selects.length; i++ )
  {
    var currentSelect = selects[i];
    if ( currentSelect.onchange )
    {
      currentSelect.changed = false;
      currentSelect.onfocus = AccessibleSelect.onfocus;
      currentSelect.onkeydown = AccessibleSelect.onkeydown;
      currentSelect.onclick = AccessibleSelect.onclick;
      currentSelect.onchange = AccessibleSelect.createOnchange( currentSelect.onchange );
    }
  }
};

/**
 * Functor that creates an onchange function which if the <select> has actually been changed, will call the
 * specified callback (i.e. the original onchange function )
 */
AccessibleSelect.createOnchange = function( callback )
{
  return function( theElement )
  {
    var theSelect;

    if ( theElement && theElement.value )
    {
      theSelect = theElement;
    }
    else
    {
      theSelect = this;
    }

    if (theSelect.changed)
    {
    // bind "theSelect" as the "this" for the callback.
      callback.apply(theSelect);
      return true;
    }
    else
    {
      return false;
    }
  };
};

/**
 * Event listener called when the <select> is clicked
 */
AccessibleSelect.onclick = function()
{
  this.changed = true;

  // If the select size is greater than 0, then the onchange event occurs before the onclick event
  // and the "changed" attribute is false when the onchange event listener method runs and the select
  // element's onchange method does not get called. Therefore, we are going to call it here.
  if( this.size > 0 )
  {
    this.onchange(this);
  }
};

/**
 * Event listener called when the <select> gains focus
 */
AccessibleSelect.onfocus = function()
{
  this.initValue = this.value;
  return true;
};

/**
 * Event listener called when a key is pressed in the <select>.
 */
AccessibleSelect.onkeydown = function(e)
{
  var theEvent;
  var keyCodeTab = "9";
  var keyCodeEnter = "13";
  var keyCodeEsc = "27";

  if (e)
  {
    theEvent = e;
  }
  else
  {
    theEvent = event;
  }

  var largeSize = (this.size > 0);

  if ((theEvent.keyCode == keyCodeEnter || theEvent.keyCode == keyCodeTab) && ( largeSize || this.value != this.initValue) )
  {
    this.initValue = this.value;
    this.changed = true;
    this.onchange(this);
  // returning true logically denotes that the change has been made, but more importantly it will make sure the default
  // behavior (what would have happened without the onkeydown event) is honored. For example, user pressing 'tab' key will
  // move the focus to the next element on the page
  return true;
  }
  else if (theEvent.keyCode == keyCodeEsc)
  {
    this.value = this.initValue;
  return false;
  }
  else
  {
    this.changed = false;
  }

  return true;
};

// This script does not work for Safari. See AS-110404, AS-110426
if (!/webkit|khtml/i.test(navigator.userAgent)) {
  //When the page is loaded, initialize the select boxes
  if ( window.addEventListener) {
    window.addEventListener('load', AccessibleSelect.initializePage, false);
  } else if ( window.attachEvent ) {
    window.attachEvent('onload', AccessibleSelect.initializePage);
  }
}
