var afterIBuyApp = angular.module('afterIBuyApp', ['ngRoute'])
  /*.run(function($window) {
    if (!$window.localStorage['jwtToken']) {
      $window.location.href = "#/";
      return;
    }
  });*/

afterIBuyApp.config(function($routeProvider){
	$routeProvider.when('/', {
		templateUrl: 'dashboard.html',
		controller: 'dashboardController'
	});
	  
  /*$routeProvider.when('/', {
    templateUrl: 'pages/login_signup.html',
    controller: 'loginSignupController'
  }).when('/dashboard', {
    templateUrl: 'pages/dashboard.html',
    controller: 'dashboardController'
  }).when('/products', {
    templateUrl: 'pages/products.html',
    controller: 'productsController'
  }).when('/requests', {
    templateUrl: 'pages/request_log.html',
    controller: 'requestsController'
  }).when('/notifications', {
    templateUrl: 'pages/notifications.html',
    controller: 'notificationsController'
  }).when('/profile', {
    templateUrl: 'pages/profile.html',
    controller: 'profileController'
  });*/
});

afterIBuyApp.service('afterIBuyService', function() {
  this.totalBrands = 0;

  var self = this;

  /*this.userLogin = function($http, $scope, $location, $window, user) {
    var req = {
      method: 'POST',
      url: 'https://radiant-bayou-66868.herokuapp.com/users/login',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        "email": user.email,
        "password": user.password
      }
    };
    $http(req).then(function(response){
      console.log(response);
      console.log(response.data);
      console.log(response.status);
      $headers = response.headers();
      console.log($headers);
      $window.localStorage['jwtToken'] = $headers.auth;
      $location.path('/dashboard');
    }, function(response){
      console.log(response);
    });
  };
  this.userSignup = function($http, $scope, $location, user) {
    var req = {
      method: 'POST',
      url: 'https://radiant-bayou-66868.herokuapp.com/users',
      headers: {
        'Content-Type': 'application/json'
      },
      data: {
        "email": user.email,
        "password": user.password
      }
    };
    $http(req).then(function(response){
      console.log(response);
      console.log(response.data);
      if(response.status === 200) { 
        alert('You are registered with us. Please login to use the product.');
      } 
      if(response.status === 400) {
        alert('You are already registered with us. If not, then please contact administrator.');
      }
      $headers = response.headers();
      console.log($headers);
    }, function(response){
      console.log(response);
    });
  };

 
  this.getProducts = function($http, $scope, $window) {
    var url = 'https://radiant-bayou-66868.herokuapp.com/products';
    var params = 0;
    if($scope.productType) {
      if(params === 0) { 
        url = url + '?type=' + $scope.productType;
        params++;
      } else {
        url = url + '&type=' + $scope.productType;
        params++;
      }
      console.log(url);
    }
    if($scope.make) {
      if(params === 0) { 
        url = url + '?make=' + $scope.make;
        params++;
      } else {
        url = url + '&make=' + $scope.make;
        params++;
      }
      console.log(url);
    }

    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/products',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200) {
        $scope.products = response.data;
        return response.data;
      }
    }, function(response){
      console.log(response);
    });
  };
  
  this.getRequests = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/requests',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200) {
        $scope.requests = response.data;
        return response.data;
      }
    }, function(response){
      console.log(response);
    });

  };
  this.getNotifications = function($http, $scope, $window) {
  
  };
  this.getProfile = function($http, $scope, $window) {
  };

  this.getTotalProducts = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/products/count',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.totalProducts = response.data;
      }
    }, function(response){
      console.log(response);
    });
  
  };
  this.getTotalBrands = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/products/count?q=make',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.totalBrands = response.data;
      }
    }, function(response){
      console.log(response);
    });

  };
  this.getTotalRequests = function($http, $scope, $window) {
    var total = 0;
    var resolved = 0;
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/requests/count',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.totalRequests = response.data;
        $scope.unresolvedRequests = $scope.totalRequests - $scope.resolvedRequests;
      }
    }, function(response){
      console.log(response);
    });

    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/requests/count?status=resolved',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.resolvedRequests = response.data;
        $scope.unresolvedRequests = $scope.totalRequests - $scope.resolvedRequests;
      }
    }, function(response){
      console.log(response);
    });
  };

  this.getRequestStatuses = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/requestStatuses',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.requestStatuses = response.data;
      }
    }, function(response){
      console.log(response);
    });
  };
  this.getRequestTypes = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/requestTypes',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.requestTypes = response.data;
      }
    }, function(response){
      console.log(response);
    });
  };
  this.getProductTypes = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/productTypes',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.productTypes = response.data;
      }
    }, function(response){
      console.log(response);
    });

  };
  this.getMakes = function($http, $scope, $window) {
    var req = {
      method: 'GET',
      url: 'https://radiant-bayou-66868.herokuapp.com/makes',
      headers: {
        'Auth': $window.localStorage['jwtToken']
      }
    };
    $http(req).then(function(response){
      if(response.status === 200){
        $scope.makes = response.data;
      }
    }, function(response){
      console.log(response);
    });
  };

  this.addProduct = function($http, $scope, $window, product) {
    var req = {
      method: 'POST',
      url: 'https://radiant-bayou-66868.herokuapp.com/products',
      headers: {
        'Content-Type': 'application/json',
        'Auth': $window.localStorage['jwtToken']
      },
      data: product
    };
    $http(req).then(function(response){
      console.log(response);
      console.log(response.data);
      console.log(response.status);
      if(response.status === 200) {
        alert('Product is added successfully.');
      }
      $headers = response.headers();
      console.log($headers);
    }, function(response){
      console.log(response);
    });
    $( ".popup" ).dialog( "close" );
  };

  this.addRequest = function($http, $scope, $window, request) {
    var req = {
      method: 'POST',
      url: 'https://radiant-bayou-66868.herokuapp.com/requests',
      headers: {
        'Content-Type': 'application/json',
        'Auth': $window.localStorage['jwtToken']
      },
      data: request
    };
    $http(req).then(function(response){
      console.log(response);
      console.log(response.data);
      console.log(response.status);
      if(response.status === 200) {
        alert('Request is added successfully.');
      }
      $headers = response.headers();
      console.log($headers);
    }, function(response){
      console.log(response);
    });
    $( ".popup_request" ).dialog( "close" );
  };

  this.editProduct = function($http, $scope, product, $window, newProduct) {

  };

  this.deleteProduct = function($http, $scope, $window, product) {

  };

  this.deleteRequest = function($http, $scope, $window, request) {

  };*/

});

afterIBuyApp.controller('globalController', ['$scope', '$http', 'afterIBuyService', '$location', '$window', function($scope, $http, afterIBuyService, $location, $window){
  console.log($location);
  $scope.location = $location.path();
  console.log($scope.location);
  
  $scope.totalProducts = 0;
  $scope.totalBrands = 0;
  $scope.totalRequests = 0;
  $scope.resolvedRequests = 0;
  $scope.unresolvedRequests = 0;

  /*afterIBuyService.getTotalBrands($http, $scope, $window);
  $scope.$watch('afterIBuyService.getProducts($http, $scope, $window)', function() {
    afterIBuyService.getTotalProducts($http, $scope, $window);
    afterIBuyService.getTotalBrands($http, $scope, $window);
    afterIBuyService.getProducts($http, $scope, $window);
  });

  $scope.$watch('afterIBuyService.getRequests($http, $scope, $window)', function() {
    afterIBuyService.getTotalRequests($http, $scope, $window);
    afterIBuyService.getTotalBrands($http, $scope, $window);
    afterIBuyService.getRequests($http, $scope, $window);
  });

  afterIBuyService.getTotalProducts($http, $scope, $window);
  afterIBuyService.getTotalRequests($http, $scope, $window);

  afterIBuyService.getProducts($http, $scope, $window);
  afterIBuyService.getRequests($http, $scope, $window);
  
  afterIBuyService.getRequestStatuses($http, $scope, $window);
  afterIBuyService.getRequestTypes($http, $scope, $window);
  afterIBuyService.getProductTypes($http, $scope, $window);
  afterIBuyService.getMakes($http, $scope, $window);*/
  
  
  $scope.addProduct = function() {
    console.log($scope.product);
    //afterIBuyService.addProduct($http, $scope, $window, $scope.product);
  };

  $scope.addRequest = function() {
    //afterIBuyService.addRequest($http, $scope, $window, $scope.request);
  };

  $scope.userLogin = function() {
    //afterIBuyService.addProduct($http, $scope, $window, $scope.userLoginDetails);
  };

  $scope.userSignup = function() {
    //afterIBuyService.addProduct($http, $scope, $window, $scope.userSignupDetails);
  };

}]);



afterIBuyApp.controller('dashboardController', ['$scope', '$http', 'afterIBuyService', '$window', function($scope, $http, afterIBuyService, $window){
	//afterIBuyService.getProducts($http, $scope, $window);
	//afterIBuyService.getRequests($http, $scope, $window);
	
	//afterIBuyService.getTotalBrands($http, $scope, $window);
	//afterIBuyService.getTotalProducts($http, $scope, $window);
	//afterIBuyService.getTotalRequests($http, $scope, $window);
	console.log("inside controller");
}]);

/*afterIBuyApp.controller('loginSignupController', ['$scope', '$http', 'afterIBuyService', '$location', '$window', function($scope, $http, afterIBuyService, $location, $window){

  $scope.userLogin = function() {
    afterIBuyService.userLogin($http, $scope, $location, $window, $scope.userLoginDetails);
  };

  $scope.userSignup = function() {
    afterIBuyService.userSignup($http, $scope, $window, $scope.userSignupDetails);
  };

}]);

afterIBuyApp.controller('productsController', ['$scope', '$http', 'afterIBuyService', '$window', function($scope, $http, afterIBuyService, $window){
  $scope.prdocutType = '';
  $scope.make = '';
  afterIBuyService.getProductTypes($http, $scope, $window);
  afterIBuyService.getMakes($http, $scope, $window);
  afterIBuyService.getProducts($http, $scope, $window);

  afterIBuyService.getTotalBrands($http, $scope, $window);
  afterIBuyService.getTotalProducts($http, $scope, $window);
  afterIBuyService.getTotalRequests($http, $scope, $window);
}]);

afterIBuyApp.controller('requestsController', ['$scope', '$http', 'afterIBuyService', '$window', function($scope, $http, afterIBuyService, $window){
  afterIBuyService.getRequests($http, $scope, $window);
  
  afterIBuyService.getRequestStatuses($http, $scope, $window);
  afterIBuyService.getRequestTypes($http, $scope, $window);
  afterIBuyService.getProductTypes($http, $scope, $window);
  afterIBuyService.getMakes($http, $scope, $window);

  afterIBuyService.getTotalBrands($http, $scope, $window);
  afterIBuyService.getTotalProducts($http, $scope, $window);
  afterIBuyService.getTotalRequests($http, $scope, $window);
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
