(function(app) {
    document.addEventListener('DOMContentLoaded', function() {
        ng.platform.browser.bootstrap(app.AppComponent); //Angulars browser bootstrap function & applications Root component we wrote.
    });
})(window.app || (window.app = {}));

//calling other module : app.
//use ng. to get something from angular