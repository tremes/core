window.applicationContextPath = window.applicationContextPath || "";
var mocks = parent.parent ? parent.parent.mocks : parent.mocks;
mocks = mocks || [];
var dependencies = mocks.concat(["ngResource", "ngRoute"]);
var app = angular.module("DicesApp", dependencies);

if (undefined != mocks.initializeMocks) {
    app.run(mocks.initializeMocks);
}

/*app.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/dices', {
        templateUrl: 'partials/test.html'
        //controller: 'PhoneListCtrl'
      }).
      otherwise({
        redirectTo: '/'
      });
  }]);     */

app.controller("DicesCtrl", function ($scope, DicesDao)
{
    $scope.counter = 0;
    $scope.computerDices = [];

    function refresh()
    {
       $scope.dices = DicesDao.Throw.query();
    }

    $scope.start = function ()
    {
       DicesDao.Player.start(refresh);
    };

    $scope.throwDices = function ()
    {
       if($scope.counter < 2){
            DicesDao.Throw.throwDices(refresh);
            $scope.counter = $scope.counter + 1;
       }

       if($scope.counter == 2){
            $scope.computerDices = DicesDao.Computer.query();
       }
    };

    $scope.hold = function(id){
        DicesDao.Player.hold(id,refresh);
    };

    $scope.resetGame = function(){
        $scope.counter = 0;
        $scope.dices = undefined;
        $scope.computerDices = undefined;
    };

    $scope.score = function(){
        var score = 0;

        if( typeof($scope.dices) != 'undefined'){
            for(var i = 0; i < $scope.dices.length; i++){
                score = score + $scope.dices[i].value;
            }
        }
        return score;
    };

    $scope.computerScore = function(){
        var score = 0;

        if( typeof($scope.computerDices) != 'undefined'){
            for(var i = 0; i < $scope.computerDices.length; i++){
                score = score + $scope.computerDices[i].value;
            }
        }

        return score;
     };

});

app.factory("DicesDao", function ($resource)
{

    return { Player: $resource(window.applicationContextPath + "/dices", {},{
                start: {
                            method: 'POST',
                            params: {},
                            isArray: false
                        },

                hold:   {
                            method: 'PUT',
                            params: {id: '@id'},
                            isArray: false
                        }
             }),

             Throw: $resource(window.applicationContextPath + "/dices/throwing", {},{
                throwDices: {
                            method: 'POST',
                            params: {},
                            isArray: false
                            }
             }),

             Computer: $resource(window.applicationContextPath + "/dices/computer")
    };
});


