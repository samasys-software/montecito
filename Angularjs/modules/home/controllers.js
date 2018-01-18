'use strict';
 
angular.module('Home',["ng-fusioncharts", "ngStomp"])
 
.controller('HomeController',
    ['$stomp','$scope', '$rootScope', '$location','AuthenticationService',
    function ($stomp, $scope, $rootScope, $location, AuthenticationService) {
      
      AuthenticationService.getItemCriticality($rootScope.token, function(data){
          if(!data.error){
              $scope.myItems = data;
          }else{
              console.log(data);
          }
          
      });
        
        AuthenticationService.getConsuptionInfo($rootScope.token,function(data){
                var chartData =[];
            for(var i =0;i<data.length;i++){
                var item = {}
                item["label"]=data[i].item
                item["value"] = data[i].usage
            chartData.push( item );
            }
 
            $scope.myDataSource =  {
                chart: {
                    caption: "Today's Consumption Patterns",
                   
                },
                data: chartData
            };
            $scope.$apply();
 
         });
        
        $scope.myDataSource =  {
                chart: {
                    caption: "Today's Consumption Patterns",
                   },
                data: []
            };
        
        
        
        var webSock = new WebSocket("ws://ec2-52-91-5-22.compute-1.amazonaws.com:8080/montecito/event");
        webSock.onmessage = function(evt){
      
      var chartData = JSON.parse(evt.data);
             var charts = [];
             for(var i =0;i<chartData.length;i++){
                var item = {}
                item["label"]=chartData[i].item
                item["value"] = chartData[i].usage
            charts.push(item);
            }
         $scope.myDataSource =  {
                chart: {
                    caption: "Today's Consumption Patterns",
                },
                data: charts
            };
             $scope.$apply();
               
      };
          

    webSock.onerror = function(evt) {
    alert("Error "+evt.data);
    };
      
         
        
        
    }]);