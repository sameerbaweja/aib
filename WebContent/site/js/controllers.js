afterIBuyApp.controller('LoginController',
	    ['$scope', '$rootScope', '$location', 'AuthenticationService',
	    function ($scope, $rootScope, $location, AuthenticationService) {
	        // reset login status
	        AuthenticationService.ClearCredentials();
	 
	        $scope.login = function () {
	            $scope.dataLoading = true;
	            AuthenticationService.Login($scope.username, $scope.password, function(response) {
	                if(response.success) {
	                    AuthenticationService.SetCredentials($scope.username, $scope.password);
	                    $location.path('/dashboard');
	                } else {
	                    $scope.error = response.message;
	                    $scope.dataLoading = false;
	                }
	            });
	        };
}]);

afterIBuyApp.controller('globalController', function($rootScope, $scope, $mdDialog, $http, afterIBuyService, $window, Notification){
	$rootScope.dialogOption = {
		  	clickOutsideToClose : true,
			title : "After I Buy",
			textContent : "Welcome",
			show : {
			// effect: "blind",
			// duration: 1000
			},
			hide : {
			// effect: "blind",
			// duration: 1000
			},
			okLabel : "Ok!",
			cancelLabel : "Cancel!",
			dialogStatus : "Done!",
			buttons : {
				OK : function() {
				},
				Cancel : function() {
				},
			},
		};
  //$scope.location = $location.path();
  afterIBuyService.getHeaderData($http, $rootScope);
  
  $rootScope.$on('LOAD',function(){$scope.loading=true});
  $rootScope.$on('UNLOAD',function(){$scope.loading=false});
  //console.log($scope.location);

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
  });*/

  $rootScope.showAlert = function(ev) {
      $mdDialog.show(
         $mdDialog.alert()
            .parent(angular.element(document.querySelector('#right_container')))
            .clickOutsideToClose($scope.dialogOption.clickOutsideToClose)
            .title($scope.dialogOption.title)
            .textContent($scope.dialogOption.textContent)
            .ariaLabel($scope.dialogOption.textContent)
            .ok($scope.dialogOption.okLabel)
            .targetEvent(ev)
      );
   };

   $rootScope.showConfirm = function(ev) {
       var confirm = $mdDialog.confirm()
          .parent(angular.element(document.querySelector('#right_container')))
          .title($scope.dialogOption.title)
          .textContent($scope.dialogOption.textContent)
          .ariaLabel($scope.dialogOption.textContent)
	      .targetEvent(ev)
          .ok($scope.dialogOption.okLabel)
          .cancel($scope.dialogOption.cancelLabel);
       
          $mdDialog.show(confirm).then($scope.dialogOption.buttons.OK, $scope.dialogOption.buttons.Cancel);
    };

    $rootScope.loadProductsPage = function(){
    	afterIBuyService.getProducts($http, $scope);
    	
    	afterIBuyService.getProductTypes($http, $scope);
    	afterIBuyService.getMakes($http, $scope);
    	afterIBuyService.getHeaderData($http, $rootScope);
    };

    	
    $rootScope.loadRequestsPage = function(prodId){
    	if(prodId != null && prodId != ""){
    		//afterIBuyService.getRequests($http, $scope, $window);
    	} else {
    		afterIBuyService.getRequests($http, $scope);
    	}
    	
    	afterIBuyService.getRequestStatusValues($http, $scope);
    	afterIBuyService.getRequestTypes($http, $scope);
    	afterIBuyService.getProductTypes($http, $scope);
    	afterIBuyService.getMakes($http, $scope);
    	afterIBuyService.getHeaderData($http, $rootScope);
    };
});

afterIBuyApp.controller('navCtrl', function ($scope, $location) {
    $scope.navClass = function (page) {
        var currentRoute = $location.path().substring(1) || 'dashboard';
        return page === currentRoute ? 'active' : '';
    };
});

afterIBuyApp.controller('dashboardController', function($scope, $http, afterIBuyService, $window, Notification){
	afterIBuyService.getRecentProducts($http, $scope);
	afterIBuyService.getRecentRequests($http, $scope);
	
	//afterIBuyService.getTotalBrands($http, $scope, $window);
	//afterIBuyService.getTotalProducts($http, $scope, $window);
	//afterIBuyService.getTotalRequests($http, $scope, $window);
});

afterIBuyApp.controller('editProductController', function($scope, $routeParams, $http, afterIBuyService, $window, $filter, Notification){
	$.ajax({url: "../product/productDetails/"+$routeParams.productId, async: false, type: 'POST', success: function(data) {
		//console.log(data);
		$scope.product = data;

		afterIBuyService.getProductTypes($http, $scope);

		var result = $filter('filter')($scope.productTypes, {productTypeId:$scope.product.productTypeId})[0];
		if(result === undefined){
			$scope.brands = [];
		}else{
			$scope.brands = result.makes;
		}
	}});

	$scope.productTypeChange = function(){
		var result = $filter('filter')($scope.productTypes, {productTypeId:$scope.product.productTypeId})[0];
		if(result === undefined){
			$scope.brands = [];
		}else{
			$scope.brands = result.makes;
		}
	};
});

afterIBuyApp.controller('productController', function($scope, $http, afterIBuyService, $window, $filter, Notification){
	$scope.loadProductsPage();
	
	/*$scope.showAlertMessage = function(ev) {
		showAlert(ev, dataObj);
	};*/
	$scope.deleteProduct = function(ev, prodId) {
		$scope.dialogOption.title = "Delete Product";
		$scope.dialogOption.textContent = "The product will be removed. Are you sure?";
		$scope.dialogOption.buttons.OK = function(){
			$scope.$emit('LOAD');
			$.ajax({url: "../product/removeProduct/"+prodId,
				async: false,
				type: 'POST',
				success: function(data, status) {
					console.log(status);
					$scope.$emit('UNLOAD');
					Notification('Product removed');
					$scope.loadProductsPage();
				},
				error: function(data, status) {
					console.log(status);
					$scope.$emit('UNLOAD');
					Notification.error('Error in removing product');
				}
			});
		};
		
		$scope.showConfirm(ev);
	};
});

afterIBuyApp.controller('addProductController', function($scope, $http, afterIBuyService, $window, $filter, Notification){
	afterIBuyService.getProductTypes($http, $scope);
});

afterIBuyApp.controller('submitProductController', function($scope, $http, afterIBuyService, $window, $filter, Notification){
	$scope.submitProduct = function() {
		var newProduct = {
				productId: $scope.product.productId,
				productNickName: $scope.product.productNickName,
				productTypeId: $scope.product.productTypeId,
				makeId: $scope.product.makeId,
				productModel: $scope.product.productModel,
				productPurchaseDate: $scope.product.productPurchaseDate,
				productWarrantyPeriod: $scope.product.productWarrantyPeriod,
				productBillNumber: $scope.product.productBillNumber,
				productSpecifications: $scope.product.productSpecifications,
				productRemarks: $scope.product.productRemarks
	        };
		
		$scope.$emit('LOAD');
		$http({
			url: '../product/submitproduct',
			method: 'POST',
			data: newProduct
		}).success(function (data, status, headers, config) {
			console.log("status: " + status);
			console.log("data: " + data);
	  		$scope.$emit('UNLOAD');
			Notification.success('Product saved');
        }).error(function (data, status, headers, config) {
			console.log("status: " + status);
			console.log("data: " + data);
            console.log("error");
	  		$scope.$emit('UNLOAD');
			Notification.error('Error in saving product');
        });
		
	};

	$scope.productTypeChange = function(){
		var result = $filter('filter')($scope.productTypes, {productTypeId:$scope.product.productTypeId})[0];
		if(result === undefined){
			$scope.brands = [];
		}else{
			$scope.brands = result.makes;
		}
	};
});

/*afterIBuyApp.controller('deleteProductController', function($scope, $rootScope, $routeParams, $http, afterIBuyService, $window, $filter){
	console.log("deleting product......");

	$scope.$emit('LOAD');
	$.ajax({url: "../product/removeProduct/"+$routeParams.productId,
		async: false,
		type: 'POST',
		success: function(data, status) {
			console.log(status);
			$scope.$emit('UNLOAD');
		},
		error: function(data, status) {
			console.log(status);
			$scope.$emit('UNLOAD');
		}
	});

	afterIBuyService.getProducts($http, $scope, $window);
	
	afterIBuyService.getProductTypes($http, $scope, $window);
	afterIBuyService.getMakes($http, $scope, $window);
});*/

afterIBuyApp.controller('requestController', function($scope, $http, afterIBuyService, $window, $filter, Notification){
	$scope.loadRequestsPage("");

	$scope.withdrawRequest = function(ev, reqId) {
		$scope.dialogOption.title = "Withdraw Request";
		$scope.dialogOption.textContent = "The request entry will be withdrawn. Are you sure?";
		$scope.dialogOption.buttons.OK = function(){
			$scope.$emit('LOAD');
			$.ajax({url: "../request/withdrawRequest/"+reqId,
				async: false,
				type: 'POST',
				success: function(data, status) {
					console.log(data);
					console.log(data.responseText);
					$scope.$emit('UNLOAD');
					Notification.success(data);
					$scope.loadRequestsPage();
				},
				error: function(data, status) {
					console.log(data.status);
					$scope.$emit('UNLOAD');
					Notification.error('Error: '+data.responseText);
				}
			});
		};
		
		$scope.showConfirm(ev);
	};

});

afterIBuyApp.controller('createRequestController', function($scope, $http, afterIBuyService, $window, $filter, Notification){
	afterIBuyService.getProducts($http, $scope);
	console.log("loading request form");
});


afterIBuyApp.controller('submitRequestController', function($scope, $http, afterIBuyService, $window, $filter, Notification){
	console.log("submitting new request.....");
	$scope.submitRequest = function() {
		var newRequest = {
				productId: $scope.request.productId,
				requestTitle: $scope.request.requestTitle,
				requestDesc: $scope.request.requestDesc
	        };
		console.log("Request Desc: "+newRequest.requestDesc);
		$scope.$emit('LOAD');
		$http({
			url: '../request/submitrequest',
			method: 'POST',
			data: newRequest//$scope.request
		}).success(function (data, status, headers, config) {
            //console.log(data);
			console.log(status);
	  		$scope.$emit('UNLOAD');
			Notification.success('Request created');
        }).error(function (data, status, headers, config) {
            //console.log(data);
            console.log(status);
            console.log("error");
	  		$scope.$emit('UNLOAD');
			Notification.error('Error in new request');
        });
		
	};
});

afterIBuyApp.controller('removeRequestController', function($scope, $routeParams, $http, afterIBuyService, $window, $filter, Notification){
	$scope.$emit('LOAD');
	$.ajax({url: "../request/withdrawRequest/"+$routeParams.requestId,
		async: false,
		type: 'POST',
		success: function(data, status) {
			console.log(status);
			$scope.$emit('UNLOAD');
		},
		error: function(data, status) {
			console.log(status);
			$scope.$emit('UNLOAD');
		}
	});
	
	afterIBuyService.getRequests($http, $scope);
	  
	afterIBuyService.getRequestStatusValues($http, $scope);
	afterIBuyService.getRequestTypes($http, $scope);
	afterIBuyService.getProductTypes($http, $scope);
	afterIBuyService.getMakes($http, $scope);
});