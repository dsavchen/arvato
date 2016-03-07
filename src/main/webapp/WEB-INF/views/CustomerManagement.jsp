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
      <div class="generic-container" ng-controller="CustomerController as ctrl">
          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Customer Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.customer.id" />
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="uname">Name</label>
                              <div class="col-md-7">
                                  <input type="text" ng-model="ctrl.customer.name" id="uname" class="name form-control input-sm" placeholder="Enter your name" required ng-minlength="3"/>
                                  <div class="has-error" ng-show="myForm.$dirty">
                                      <span ng-show="myForm.uname.$error.required">This is a required field</span>
                                      <span ng-show="myForm.uname.$error.minlength">Minimum length required is 3</span>
                                      <span ng-show="myForm.uname.$invalid">This field is invalid </span>
                                  </div>
                              </div>
                          </div>
                      </div>
                         
                       
                      <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="group">Group</label>
                              <div class="col-md-7">
                                <select ng-model="ctrl.customer.group" ng-options="item.name for item in ctrl.groups" id="group" class="form-control input-sm"></select>
                              
                              </div>
                          </div>
                      </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.customer.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>

          <div class="panel panel-default">
              <div class="panel-heading"><span class="lead">Parking Registration Form </span></div>
              <div class="formcontainer">
                  <form ng-submit="ctrl.submitParking()" name="myForm" class="form-horizontal">
                      <input type="hidden" ng-model="ctrl.parking.id" />
                       <div class="row">
                          <div class="form-group col-md-12">
                              <label class="col-md-2 control-lable" for="group">Customer</label>
                              <div class="col-md-7">
                                <select ng-model="ctrl.parking.customer" ng-options="item.name for item in ctrl.customers" id="customer" class="form-control input-sm"></select>
                              </div>
                          </div>
                      </div>
                      
                       <div class="col-md-7">
                            <input type="text" ng-model="ctrl.parking.timeStarted" id="timeStarted" class="name form-control input-sm" placeholder="Enter time started" required/>
                       </div>
                       <div class="col-md-7">
                            <input type="text" ng-model="ctrl.parking.timeEnded" id="timeEnded" class="name form-control input-sm" placeholder="Enter time ended" required/>
                       </div>
 
                      <div class="row">
                          <div class="form-actions floatRight">
                              <input type="submit"  value="{{!ctrl.parking.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid">
                              <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" ng-disabled="myForm.$pristine">Reset Form</button>
                          </div>
                      </div>
                  </form>
              </div>
          </div>
          
          <div class="panel panel-default">
                <!-- Default panel contents -->
              <div class="panel-heading"><span class="lead">List of Customers</span></div>
              <div class="tablecontainer">
                  <table class="table table-hover">
                      <thead>
                          <tr>
                              <th>ID.</th>
                              <th>Name</th>
                              <th>Group</th>
                              <th width="20%"></th>
                          </tr>
                      </thead>
                      <tbody>
                          <tr ng-repeat="u in ctrl.customers">
                              <td><span ng-bind="u.id"></span></td>
                              <td><span ng-bind="u.name"></span></td>
                              <td><span ng-bind="u.group.name"></span></td>
                              <td>
                              <button type="button" ng-click="ctrl.edit(u.id)" class="btn btn-success custom-width">Edit</button>  <button type="button" ng-click="ctrl.remove(u.id)" class="btn btn-danger custom-width">Remove</button>
                              </td>
                          </tr>
                      </tbody>
                  </table>
              </div>
          </div>
      </div>
       
      <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.4/angular.js"></script>
      <script src="<c:url value='/static/js/app.js' />"></script>
      <script src="<c:url value='/static/js/service/customer_service.js' />"></script>
      <script src="<c:url value='/static/js/controller/customer_controller.js' />"></script>
  </body>
</html>