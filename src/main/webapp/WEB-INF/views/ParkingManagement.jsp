<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>  
    <title>AngularJS $http Example</title>  
    <style>
      .name.ng-valid {
          background-color: lightgreen;
      }
      .name.ng-dirty.ng-invalid-required {
          background-color: red;
      }
      .name.ng-dirty.ng-invalid-minlength {
          background-color: yellow;
      }
 
 
    </style>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
     <link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
  </head>
  <body ng-app="myApp" class="ng-cloak">
      <p><a href="/ArvatoApp/customers">Customers</a></p>
      <div class="generic-container" ng-controller="ParkingController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Parking Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.parking.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="customer">Customer</label>
                              <div class="col-md-7">
                                <select ng-model="ctrl.parking.customer" ng-options="item.name for item in ctrl.customers" id="customer" class="form-control input-sm"></select>
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="timeStarted">Time Started</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.parking.timeStarted" id="timeStarted" class="name form-control input-sm" placeholder="Enter time started (format: 2016-01-01 10:00)" required ng-minlength="15"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.timeStarted.$error.required">This is a required field</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="timeEnded">Time Ended</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.parking.timeEnded" id="timeEnded" class="name form-control input-sm" placeholder="Enter time ended (format: 2016-01-01 12:00)" required ng-minlength="15"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.timeEnded.$error.required">This is a required field</span>
                                  </div>
                              </div>
                          </div>
                      </div>
                       

                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="Add" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">Parking invoice</span></div>
                 <div class="row">
                     <div class="form-group col-md-12">
                         <label class="col-md-2 control-lable" for="customer">Customer</label>
                         <div class="col-md-7">
                           <select ng-model="ctrl.invoiceCustomer" ng-options="item.name for item in ctrl.customers" class="form-control input-sm"></select>
                         </div>
                     </div>
                 </div>
                <div class="row">
                    <div class="form-actions floatRight">
                        <button type="button" ng-click="ctrl.fetchInvoice()" class="btn btn-warning btn-sm">Get Invoice</button>
                    </div>
                </div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>Time Started</th>
                              <th>Time Ended</th>
                              <th>Price</th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.invoice.checks">
                              <td><span ng-bind="u.info.timeStarted"></span></td>
                              <td><span ng-bind="u.info.timeEnded"></span></td>
                              <td><span ng-bind="u.price"></span></td>
                          </tr>
                          <tr>
                              <td><span></span>Total Price:</td>
                              <td><span></span></td>
                              <td><span ng-bind="ctrl.invoice.totalPrice"></span></td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/application_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/parking_controller.js' />"></script>
  </body>
</html>