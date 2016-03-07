'use strict';
 
App.controller('CustomerController', ['$scope', 'ApplicationService', function($scope, ApplicationService) {
          var self = this;
          self.customer={id:null,name:'',group:null};
          self.customers=[];
          self.groups=[];
               
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
          self.fetchAllGroups = function(){
        	  ApplicationService.fetchAllGroups()
                  .then(
                               function(d) {
                                    self.groups = d;
                               },
                                function(errResponse){
                                    console.error('Error while fetching Customer groups');
                                }
                       );
          };
            
          self.createCustomer = function(customer){
        	  ApplicationService.createCustomer(customer)
                      .then(
                      self.fetchAllCustomers, 
                              function(errResponse){
                                   console.error('Error while creating Customer.');
                              } 
                  );
          };
 
         self.updateCustomer = function(customer, id){
        	 ApplicationService.updateCustomer(customer, id)
                      .then(
                              self.fetchAllCustomers, 
                              function(errResponse){
                                   console.error('Error while updating Customer.');
                              } 
                  );
          };
 
         self.deleteCustomer = function(id){
        	 ApplicationService.deleteCustomer(id)
                      .then(
                              self.fetchAllCustomers, 
                              function(errResponse){
                                   console.error('Error while deleting Customer.');
                              } 
                  );
          };
 
          self.fetchAllGroups();
          self.fetchAllCustomers();
 
          self.submit = function() {
              if(self.customer.id===null) {
                  console.log('Saving New Customer', self.customer);    
                  self.createCustomer(self.customer);
              }else{
                  self.updateCustomer(self.customer, self.customer.id);
                  console.log('Customer updated with id ', self.customer.id);
              }
              self.reset();
          };

          self.edit = function(id){
              console.log('id to be edited', id);
              for(var i = 0; i < self.customers.length; i++){
                  if(self.customers[i].id === id) {
                     self.customer = angular.copy(self.customers[i]);
                     break;
                  }
              }
          };
               
          self.remove = function(id){
              console.log('id to be deleted', id);
              if(self.customer.id === id) {//clean form if the customer to be deleted is shown there.
                 self.reset();
              }
              self.deleteCustomer(id);
          };
 
           
          self.reset = function(){
              self.customer={id:null,name:'',group:null};
              $scope.myForm.$setPristine(); //reset Form
          };
 
      }]);