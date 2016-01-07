(function(app) {
    //This file exports AppComponent
    app.AppComponent = ng.core.Component({
            selector: 'my-app', // this is a "my-app" selector! Angular displays an instance of our AppComponent when encountring my-app element
            template: '<h1>Prem1 First Angular 2 App</h1>'
        })
        .Class({ //implementation of component here - properties and application logic. Binds to view as appropriate
            constructor: function() {}
        });

    //we call our IFFE with window.app
})(window.app || (window.app = {}));