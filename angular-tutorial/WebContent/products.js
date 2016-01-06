
(function() {
	var app = angular.module('store-products', []);
	app.directive('productGallery', function() {
		return {
			restrict : 'E',
			templateUrl : "08__product-gallery.snip.html",
			controller : function() {
				this.current = 0;
				this.setCurrent = function(imageNumber) {
					this.current = imageNumber || 0;
				};
			},
			controllerAs : "gallery"
		};
	});
	app.directive('productTitle', function() { // Element Directive -Template
		// Expanding
		return {
			restrict : 'E', // Restrict to element only.
			templateUrl : '08__product-title.snip.html'
		};
	});

	app.directive('productTitleAttribDirective', function() { // Attribute
		// Directive -
		// Template
		// extending
		return {
			restrict : 'A', // Restrict to Attribute only.
			templateUrl : '08__product-title.snip.html'
		};
	});

	app.directive('productDescription', function() { // Element Directive -
		// Template extending
		return {
			restrict : 'E', // Restrict to Element only.
			templateUrl : '08__product-description.snip.html'
		};
	});

	app.directive("productReviews", function() {
		return {
			restrict : 'E',
			templateUrl : "08__product-reviews.snip.html",
			controller : function() { // 07-Forms Post /Accept
				// input
				this.review = {}; // initialize review array

				this.addReview = function(product) {
					this.review.createdOn = Date.now();
					product.reviews.push(this.review);
					this.review = {}; // to clearup review after submit
				};
			},
			controllerAs : 'reviewCtrl'
		};
	});

	app.directive('productSpecs', function() {
		return {
			restrict : 'A',
			templateUrl : '08__product-specs.snip.html'
		};
	});

	app.directive('productPanels', function() { // Element Directive - Template
		// extending
		return {
			restrict : 'E', // Restrict to Element only.
			templateUrl : '08__product-panels.snip.html',
			controller : function() {
				this.tab = 1;

				this.setTab = function(newValue) {
					this.tab = newValue;
				};

				this.isSet = function(tabName) {
					return this.tab === tabName;
				};
			},
			controllerAs : 'tab'

		};
	});

})();
