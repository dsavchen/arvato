'use strict';
 
App.factory('ApplicationService', ['$http', '$q', function($http, $q){
 
    return {
         
            fetchAllCustomers: function() {
                    return $http.get('http://localhost:8080/ArvatoApp/customer/')
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while fetching customers');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
            fetchAllGroups: function() {
                return $http.get('http://localhost:8080/ArvatoApp/groups/')
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while fetching groups');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            fetchInvoice: function(customer) {
                return $http.get('http://localhost:8080/ArvatoApp/invoice/' + customer.id)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while fetching parking information');
                                    return $q.reject(errResponse);
                                }
                        );
            },
            
            createCustomer: function(customer){
                    return $http.post('http://localhost:8080/ArvatoApp/customer/', customer)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while creating customer');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
 
            createParking: function(parking){
                return $http.post('http://localhost:8080/ArvatoApp/parking/', parking)
                        .then(
                                function(response){
                                    return response.data;
                                }, 
                                function(errResponse){
                                    console.error('Error while creating parking');
                                    return $q.reject(errResponse);
                                }
                        );
            },

            updateCustomer: function(customer, id){
                    return $http.put('http://localhost:8080/ArvatoApp/customer/'+id, customer)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while updating customer');
                                        return $q.reject(errResponse);
                                    }
                            );
            },
             
            deleteCustomer: function(id){
                    return $http.delete('http://localhost:8080/ArvatoApp/customer/'+id)
                            .then(
                                    function(response){
                                        return response.data;
                                    }, 
                                    function(errResponse){
                                        console.error('Error while deleting customer');
                                        return $q.reject(errResponse);
                                    }
                            );
            }
         
    };
 
}]);