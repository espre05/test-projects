(function() {
	// closure
	var app = angular.module('store', ['store-products']);

//	app.controller("StoreController", function() {
//		this.products = gems;
//	});
	app.controller("StoreController", [ '$http', function($http) {
		var store = this; // should init to a local variable so it can be used in callback.
		store.products = []; //initialize to empty to avoid error on first render.
		$http.get('/products.json').success(function(data){
			store.products = data;
		});
	
	}]);

	// Move TabController to inside product-panels directive
	app.controller('TabController', function() { // Controlls tab
		Description | Specs | Reviews
		this.tab = 1;

		this.setTab = function(newValue) {
			this.tab = newValue;
		};

		this.isSet = function(tabName) {
			return this.tab === tabName;
		};
	});
	//move to directive productGallery
	app.controller('GalleryController', function() {
		this.current = 0;
		this.setCurrent = function(newGallery) {
			this.current = newGallery || 0;
		};
	});
	//move to directive productReviews
	app.controller('ReviewController', function() { // 07-Forms Post /Accept
		// input
		this.review = {}; // initialize review array

		this.addReview = function(product) {
			this.review.createdOn = Date.now();
			product.reviews.push(this.review);
			this.review = {}; // to clearup review after submit
		};
	});

//	var gems = [
//			{
//				name : 'Azurite',
//				description : "Some gems have hidden qualities beyond their luster, beyond their shine... Azurite is one of those gems.",
//				shine : 8,
//				price : 110.50,
//				rarity : 7,
//				color : '#CCC',
//				faces : 14,
//				images : [ "images/gem-02.gif", "images/gem-05.gif",
//						"images/gem-09.gif" ],
//				reviews : [ {
//					stars : 5,
//					body : "I love this gem!",
//					author : "joe@example.org",
//					createdOn : 1397490980837
//				}]
//			}];
})();
