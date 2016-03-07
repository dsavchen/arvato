'use strict';
 
App.controller('ParkingController', ['$scope', 'ApplicationService', function($scope, ApplicationService) {
          var self = this;
          self.parking={id:null,customer: null, timeStarted:null, timeEnded: null};
          self.customers=[];
          self.invoiceCustomer;
          self.invoice;
               
          self.fetchAllCustomers = function(){
        	  ApplicationService.fetchAllCustomers()
                  .then(
                               function(d) {
                                    self.customers = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Customers');
                                }
                       );
          };
          self.fetchInvoice = function() {
        	  ApplicationService.fetchInvoice(self.invoiceCustomer)
              	.then(
              				function(d) {
              					self.invoice = d;
          						console.log('invoice received '+self.invoice);
              					setTimeout(function () {
              						console.log('Apply invoice');
              					   $scope.$apply();
              					}, 100);
              				},
              				function(errResponse){
              					console.error('Error while loading parking info.');
              				} 	
              	);
        	  
          };
          self.createParking = function(parking){
        	  ApplicationService.createParking(parking)
                      .then(
                    		  self.reset(),
                              function(errResponse){
                                   console.error('Error while adding parking info.');
                              } 
                  );
          };
 
          self.fetchAllCustomers();
 
          self.submit = function() {
              console.log('Saving New Parking', self.parking);    
              self.createParking(self.parking);
              self.reset();
          };

          self.reset = function(){
              self.parking={id:null,customer: null, timeStarted:null, timeEnded: null};
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);