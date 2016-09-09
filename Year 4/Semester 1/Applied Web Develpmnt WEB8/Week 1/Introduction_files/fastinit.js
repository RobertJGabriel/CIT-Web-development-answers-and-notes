/*
*
* Copyright (c) 2007 Andrew Tetlaw
*
* Permission is hereby granted, free of charge, to any person
* obtaining a copy of this software and associated documentation
* files (the "Software"), to deal in the Software without
* restriction, including without limitation the rights to use, copy,
* modify, merge, publish, distribute, sublicense, and/or sell copies
* of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be
* included in all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
* EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
* MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
* NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS
* BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN
* ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
* CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
* SOFTWARE.
* *
*
*
* FastInit
* http://tetlaw.id.au/view/javascript/fastinit
* Andrew Tetlaw
* Version 1.4.1 (2007-03-15)
* Based on:
* http://dean.edwards.name/weblog/2006/03/faster
* http://dean.edwards.name/weblog/2006/06/again/
* Help from:
* http://www.cherny.com/webdev/26/domloaded-object-literal-updated
*
*/
var FastInit = {
  onload : function() {
    if (FastInit.done) { return; }
    FastInit.done = true;
    for(var x = 0, al = FastInit.f.length; x < al; x++) {
      FastInit.f[x]();
    }
    // check for doubleSubmit only if validateForm.js is included in the page and thus nameSpace 'doubleSubmit' is defined
    if(typeof window.doubleSubmit !== "undefined")
    {
      for ( i = 0; i < window.document.forms.length; i++ )
      {
        // In case we end up running fastinit multiple times on a page (i.e. loading a lightbox with a form onto a page with a form), make sure
        // we don't double-check the double-submit.
        if (typeof window.document.forms[i].originalFormSubmit === 'undefined')
        {
          // Below is necessary to make use of both form.onsubmit validations on individual pages
          // and form submit event handlers registered through Event.observe(..."submit"...)
          var originalFormOnSubmit = null;
          if(window.document.forms[i].onsubmit)
          {
            originalFormOnSubmit = window.document.forms[i].onsubmit;
            window.document.forms[i].onsubmit = function() {
              return;
            };
          }
          // Form.submit() doesn't call form submit event handlers registered below, so we have to make
          // sure form submit event handlers get called when form.submit() is used to submit the form
          // Note : Browser does not trigger the onsubmit event if you call the submit method of a form
          // programmatically. Likewise, we don't call form.onsubmit() here and that validation if wanted
          // is up to the developer to do before calling form.submit()
          window.document.forms[i].originalFormSubmit = window.document.forms[i].submit;
          window.document.forms[i].submit = function() {
            if(doubleSubmit.handleFormSubmitEvents( null, this, null ) == false)
            {
              return false;
            }
            return this.originalFormSubmit();
          };
          Event.observe( window.document.forms[i], "submit", doubleSubmit.handleFormSubmitEvents
              .bindAsEventListener( this, window.document.forms[i], originalFormOnSubmit ) );
        }
      }
    }
  },
  addOnLoad : function() {
    var a = arguments;
    for(var x = 0, al = a.length; x < al; x++) {
      if(typeof a[x] === 'function') {
        if (FastInit.done ) {
          a[x]();
        } else {
          FastInit.f.push(a[x]);
        }
      }
    }
  },
  listen : function() {
    if (/WebKit|khtml/i.test(navigator.userAgent)) {
      FastInit.timer = setInterval(function() {
        if (/loaded|complete/.test(document.readyState)) {
          clearInterval(FastInit.timer);
          delete FastInit.timer;
          FastInit.onload();
        }}, 10);
    } else if (document.addEventListener) {
      document.addEventListener('DOMContentLoaded', FastInit.onload, false);
    } else if(!FastInit.iew32) {
      if(window.addEventListener) {
        window.addEventListener('load', FastInit.onload, false);
      } else if (window.attachEvent) {
        return window.attachEvent('onload', FastInit.onload);
      }
    }
  },
  f:[],done:false,timer:null,iew32:false
};
/*@cc_on @*/
/*@if (@_win32)
FastInit.iew32 = true;
document.write('<script id="__ie_onload" defer src="' + ((location.protocol == 'https:') ? '//0' : 'javascript:void(0)') + '"><\/script>');
document.getElementById('__ie_onload').onreadystatechange = function(){if (this.readyState == 'complete') { FastInit.onload(); }};
/*@end @*/
FastInit.listen();
