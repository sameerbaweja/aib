var afterIBuyApp = angular.module('afterIBuyApp', ['ngRoute', 'ngMaterial', 'ngCookies', 'ngTouch', 'ui-notification']);

afterIBuyApp.config(function($routeProvider){
	$routeProvider.when('/', {
		templateUrl: 'login.html',
		controller: 'LoginController',
		activetab: 'dashboard'
	}).when('/login', {
		templateUrl: 'login.html',
		controller: 'LoginController'
	}).when('/dashboard', {
		templateUrl: 'dashboard.html',
		controller: 'dashboardController',
		activetab: 'dashboard'
	}).when('/products', {
		templateUrl: 'products.html',
		controller: 'productController',
		activetab: 'products'
	}).when('/requests', {
		templateUrl: 'request_log.html',
		controller: 'requestController',
		activetab: 'requests'
	}).when('/editproduct/:productId', {
	    templateUrl: 'addproduct.html',
	    controller: 'editProductController',
		activetab: 'addproduct'
	}).when('/addproduct', {
		templateUrl: 'addproduct.html',
		controller: 'addProductController',
		activetab: 'addproduct'
	}).when('/deleteproduct/:productId', {
		templateUrl: 'products.html',
		controller: 'deleteProductController',
		activetab: 'products'
	}).when('/newrequest', {
		templateUrl: 'newrequest.html',
		controller: 'createRequestController',
		activetab: 'newrequest'
	}).when('/withdrawrequest/:requestId', {
		templateUrl: 'request_log.html',
		controller: 'removeRequestController',
		activetab: 'requests'
    })
    .otherwise({ redirectTo: '/login' });
	
	/*.when('/notifications', {
    templateUrl: 'pages/notifications.html',
    controller: 'notificationsController'
  }).when('/profile', {
    templateUrl: 'pages/profile.html',
    controller: 'profileController'
  });*/
})
.run(['$rootScope', '$location', '$cookieStore', '$http',
    function ($rootScope, $location, $cookieStore, $http) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
            // redirect to login page if not logged in
            if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
                $location.path('/login');
            }
        });
}]);

afterIBuyApp.service('afterIBuyService', function() {

	this.getHeaderData = function($http, $scope) {
		$.ajax({url: "../dashboarddata", async: false, type: 'POST', success: function(data) {
		  $scope.totalProducts = data.totalProducts;
		  $scope.totalBrands = data.totalBrands;
		  $scope.totalRequests = data.totalRequests;
		  $scope.resolvedRequests = data.resolvedRequests;
		  $scope.unresolvedRequests = data.unresolvedRequests;
			//console.log(data);
		}});
	};

	this.getRecentProducts = function($http, $scope) {
		$.ajax({url: "../product/recentList", async: false, type: 'POST', success: function(data) {
			$scope.products = data;
			//console.log(data);
		}});
	};

	this.getRecentRequests = function($http, $scope) {
		$.ajax({url: "../request/recentList", async: false, type: 'POST', success: function(data) {
			$scope.requests = data;
			//console.log(data);
		}});
	};

	this.getProducts = function($http, $scope) {
		$scope.$emit('LOAD');
		$.ajax(
			{
			url: "../product/productList",
			async: false,
			type: 'POST',
			success: function(data) {
				$scope.products = data;
				$scope.$emit('UNLOAD');
			//console.log(data);
		}});
	};

	this.getRequests = function($http, $scope) {
		$.ajax({url: "../request/requestList", async: false, type: 'POST', success: function(data) {
			$scope.requests = data;
			//console.log(data);
		}});
	};

	this.getProductTypes = function($http, $scope) {
		$.ajax({url: "../producttypes", async: false, type: 'POST', success: function(data) {
			$scope.productTypes = data;
			//console.log(data);
		}});
	};

	this.getMakes = function($http, $scope) {
		//$scope.brands = [{"makeId":0,"makeValue":"Brand"}];
		$.ajax({url: "../makes", async: false, type: 'POST', success: function(data) {
			$scope.brands = data;
			//console.log(data);
		}});
	};

	this.getRequestTypes = function($http, $scope) {
		$.ajax({url: "../requesttypes", async: false, type: 'POST', success: function(data) {
			$scope.requestTypes = data;
			//console.log(data);
		}});
	};

	this.getRequestStatusValues = function($http, $scope) {
		$.ajax({url: "../requeststatusvalues", async: false, type: 'POST', success: function(data) {
			$scope.requestStatusList = data;
			//console.log(data);
		}});
	};
});




afterIBuyApp.directive("datepicker", function () {
	  return {
	    restrict: "A",
	    require: "ngModel",
	    link: function (scope, elem, attrs, ngModelCtrl) {
	      var updateModel = function (dateVal) {
	        scope.$apply(function () {
	          ngModelCtrl.$setViewValue(dateVal);
	        });
	      };
	      var options = {
		    showOn: "both",
		    buttonImage: "images/icon_calender.png",
		    buttonImageOnly: true,
		    buttonText: "Select date",
		    dateFormat: "dd-MM-yy",
	        onSelect: function (dateVal) {
	        	var dateObj = $(this).datepicker('getDate');
	        	//updateModel(""+dateObj.getFullYear()+dateObj.getMonth()+dateObj.getDate());
	        	updateModel(dateVal);
	        }
	      };
	      elem.datepicker(options);
	    }
	  }
	});

/*afterIBuyApp.controller('loginSignupController', ['$scope', '$http', 'afterIBuyService', '$location', '$window', function($scope, $http, afterIBuyService, $location, $window){

  $scope.userLogin = function() {
    afterIBuyService.userLogin($http, $scope, $location, $window, $scope.userLoginDetails);
  };

  $scope.userSignup = function() {
    afterIBuyService.userSignup($http, $scope, $window, $scope.userSignupDetails);
  };

}]);

afterIBuyApp.controller('notificationsController', ['$scope', '$http', 'afterIBuyService', '$window', function($scope, $http, afterIBuyService, $window){
  $scope.notifications = '';

  afterIBuyService.getTotalBrands($http, $scope, $window);
  afterIBuyService.getTotalProducts($http, $scope, $window);
  afterIBuyService.getTotalRequests($http, $scope, $window);
}]);

afterIBuyApp.controller('profileController', ['$scope', '$http', 'afterIBuyService', '$window', function($scope, $http, afterIBuyService, $window){
  $scope.profile = '';

  afterIBuyService.getTotalBrands($http, $scope, $window);
  afterIBuyService.getTotalProducts($http, $scope, $window);
  afterIBuyService.getTotalRequests($http, $scope, $window);
}]);*/
