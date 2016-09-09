window.onload = function () {
    (function (jQuery, Firebase, Path) {
        "use strict";

        // the main firebase reference
        var rootRef = new Firebase('https://projectbird.firebaseio.com/');

        // pair our routes to our form elements and controller
        var routeMap = {
            '#/': {
                form: 'frmLogin',
                controller: 'login'
            },
            '#/logout': {
                form: 'frmLogout',
                controller: 'logout'
            },
            '#/profile': {
                form: 'frmProfile',
                controller: 'profile',
                authRequired: true // must be logged in to get here
            },
            '#/notes': {
                form: 'frmNotes',
                controller: 'notes',
                authRequired: true // must be logged in to get here
            },
            '#/calendar': {
                form: 'frmcalendar',
                controller: 'calendar',
                authRequired: true // must be logged in to get here
            },
            '#/dashboard': {
                form: 'frmdashboard',
                controller: 'dashboard',
                authRequired: true // must be logged in to get here
            }
        };

        // create the object to store our controllers
        var controllers = {};

        // store the active form shown on the page
        var activeForm = null;

        var alertBox = $('#alert');

        function routeTo(route) {
            window.location.href = '#/' + route;
        }

        // Handle third party login providers
        // returns a promise
        function thirdPartyLogin(provider) {
            var deferred = $.Deferred();

            rootRef.authWithOAuthPopup(provider, function (err, user) {
                if (err) {
                    deferred.reject(err);
                }

                if (user) {
                    deferred.resolve(user);
                }
            });

            return deferred.promise();
        };

        // Handle Email/Password login
        // returns a promise
        function authWithPassword(userObj) {
            var deferred = $.Deferred();
            console.log(userObj);
            rootRef.authWithPassword(userObj, function onAuth(err, user) {
                if (err) {
                    deferred.reject(err);
                }

                if (user) {
                    deferred.resolve(user);
                }

            });

            return deferred.promise();
        }

        // create a user but not login
        // returns a promsie
        function createUser(userObj) {
            var deferred = $.Deferred();
            rootRef.createUser(userObj, function (err) {

                if (!err) {
                    deferred.resolve();
                } else {
                    deferred.reject(err);
                }

            });

            return deferred.promise();
        }

        // Create a user and then login in
        // returns a promise
        function createUserAndLogin(userObj) {
            return createUser(userObj)
                .then(function () {
                    return authWithPassword(userObj);
                });
        }

        // authenticate anonymously
        // returns a promise
        function authAnonymously() {
            var deferred = $.Deferred();
            rootRef.authAnonymously(function (err, authData) {

                if (authData) {
                    deferred.resolve(authData);
                }

                if (err) {
                    deferred.reject(err);
                }

            });

            return deferred.promise();
        }

        // route to the specified route if sucessful
        // if there is an error, show the alert
        function handleAuthResponse(promise, route) {
            $.when(promise)
                .then(function (authData) {

                    // route
                    routeTo(route);

                }, function (err) {
                    console.log(err);
                    // pop up error
                    showAlert({
                        title: err.code,
                        detail: err.message,
                        className: 'alert-danger'
                    });

                });
        }

        // options for showing the alert box
        function showAlert(opts) {
            var title = opts.title;
            var detail = opts.detail;
            var className = 'alert ' + opts.className;

            alertBox.removeClass().addClass(className);
            alertBox.children('#alert-title').text(title);
            alertBox.children('#alert-detail').text(detail);
        }

        /// Controllers
        ////////////////////////////////////////

        controllers.login = function (form) {

            // Form submission for logging in
            form.on('submit', function (e) {

                var userAndPass = $(this).serializeObject();
                var loginPromise = authWithPassword(userAndPass);
                e.preventDefault();

                handleAuthResponse(loginPromise, 'profile');

            });

            // Social buttons
            form.children('.bt-social').on('click', function (e) {

                var $currentButton = $(this);
                var provider = $currentButton.data('provider');
                var socialLoginPromise;
                e.preventDefault();

                socialLoginPromise = thirdPartyLogin(provider);
                handleAuthResponse(socialLoginPromise, 'dashboard');

            });

            form.children('#btAnon').on('click', function (e) {
                e.preventDefault();
                handleAuthResponse(authAnonymously(), 'profilex');
            });

        };

        // logout immediately when the controller is invoked
        controllers.logout = function (form) {
            document.getElementById('frmLogin').style.display = 'none';
            rootRef.unauth();
        };



        controllers.profile = function (form) {
            // Check the current user
            var user = rootRef.getAuth();
            var userRef;
            document.getElementById('frmLogin').style.display = 'none';
            // If no current user send to register page
            if (!user) {
                routeTo('login');
                return;
            }

            // Load user info
            userRef = rootRef.child('users').child(user.uid);
            userRef.once('value', function (snap) {
                var user = snap.val();
                if (!user) {
                    return;
                }

                // set the fields
                form.find('#txtName').val(user.name);
                form.find('#ddlDino').val(user.favoriteDinosaur);
            });

            // Save user's info to Firebase
            form.on('submit', function (e) {
                e.preventDefault();
                var userInfo = $(this).serializeObject();

                userRef.set(userInfo, function onComplete() {

                    // show the message if write is successful
                    showAlert({
                        title: 'Successfully saved!',
                        detail: 'You are still logged in',
                        className: 'alert-success'
                    });

                });
            });

        };


        controllers.notes = function (form) {
            // Check the current user
            document.getElementById('frmLogin').style.display = 'none';
            var user = rootRef.getAuth();
            var userRef;

            // If no current user send to register page
            if (!user) {
                routeTo('login');
                return;
            }

            // Load user info
            userRef = rootRef.child('notes').child(user.uid);
            userRef.once('value', function (snap) {
                var user = snap.val();
                if (!user) {
                    return;
                }

                // set the fields
                form.find('#subjectInput').val(user.subjectInput);
                form.find('#textArea').val(user.textArea);
            });

            // Save user's info to Firebase
            form.on('submit', function (e) {
                e.preventDefault();
                var userInfo = $(this).serializeObject();

                userRef.set(userInfo, function onComplete() {

                    // show the message if write is successful
                    showAlert({
                        title: 'Successfully saved!',
                        detail: 'You are still logged in',
                        className: 'alert-success'
                    });

                });
            });

        };


        controllers.calendar = function (form) {
            document.getElementById('frmLogin').style.display = 'none';
            // Check the current user
            var user = rootRef.getAuth();
            var userRef;

            // If no current user send to register page
            if (!user) {
                routeTo('login');
                return;
            }

        };

        controllers.dashboard = function (form) {
            document.getElementById('frmLogin').style.display = 'none';
            // Check the current user
            var user = rootRef.getAuth();
            var userRef;

            // If no current user send to register page
            if (!user) {
                routeTo('login');
                return;
            }

        };





        /// Routing
        ////////////////////////////////////////

        // Handle transitions between routes
        function transitionRoute(path) {
            // grab the config object to get the form element and controller
            var formRoute = routeMap[path];
            var currentUser = rootRef.getAuth();
            
                         
            
            // if authentication is required and there is no
            // current user then go to the register page and
            // stop executing
            if (formRoute.authRequired && !currentUser) {
               document.getElementById("loginButton").style.display ='block';
document.getElementById("logoutButton").style.display ='none';
                routeTo('login');
               
                return;
            }else{
                
                 document.getElementById("logoutButton").style.display ='block';
document.getElementById("loginButton").style.display ='none';
            }
            
          
            

            // wrap the upcoming form in jQuery
            var upcomingForm = $('#' + formRoute.form);

            // if there is no active form then make the current one active
            if (!activeForm) {
                activeForm = upcomingForm;
            }

            // hide old form and show new form
            activeForm.hide();
            upcomingForm.show().hide().fadeIn(750);

            // remove any listeners on the soon to be switched form
            activeForm.off();

            // set the new form as the active form
            activeForm = upcomingForm;

            // invoke the controller
            controllers[formRoute.controller](activeForm);
        }

        // Set up the transitioning of the route
        function prepRoute() {
            transitionRoute(this.path);
        }


        /// Routes
        ///  #/         - Login
        //   #/logout   - Logut
        //   #/register - Register
        //   #/profile  - Profile

        Path.map("#/").to(prepRoute);
        Path.map("#/logout").to(prepRoute);
        Path.map("#/calendar").to(prepRoute);
        Path.map("#/profile").to(prepRoute);
        Path.map("#/notes").to(prepRoute);
        Path.map("#/dashboard").to(prepRoute);
        Path.root("#/");




        /// Initialize
        ////////////////////////////////////////

        $(function () {

            // Start the router
            Path.listen();

            // whenever authentication happens send a popup
            rootRef.onAuth(function globalOnAuth(authData) {

                if (authData) {
                    showAlert({
                        title: 'Logged in!',
                        detail: 'Using ' + authData.provider,
                        className: 'alert-success'
                    });
                } else {
                    showAlert({
                        title: 'You are not logged in',
                        detail: '',
                        className: 'alert-info'
                    });
                }

            });

        });

    }(window.jQuery, window.Firebase, window.Path))
}
