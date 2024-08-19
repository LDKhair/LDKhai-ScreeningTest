const app = angular.module('myApp', ['ngRoute']);

app.factory('PasswordService', function () {
  return {
    toggleShowPassword: function (scope) {
      scope.showPassword = !scope.showPassword;
    },
  };
});

app.factory('SessionService', function ($window) {
  return {
    setItem: function (key, value) {
      $window.sessionStorage.setItem(key, JSON.stringify(value));
    },
    getItem: function (key) {
      return JSON.parse($window.sessionStorage.getItem(key));
    },
    removeItem: function (key) {
      $window.sessionStorage.removeItem(key);
    },
  };
});

app.run(function ($rootScope, $timeout, $location) {
  $rootScope.$on('$routeChangeStart', function (event, next, current) {
    $rootScope.loading = true;
  });
  $rootScope.$on('$routeChangeSuccess', function () {
    $timeout(function () {
      $rootScope.loading = false;
    }, 1600);
  });
});

app.config(function ($routeProvider) {
  $routeProvider
    .when('/login', {
      templateUrl: '/views/login.html',
      controller: 'loginCtrl',
    })
    .when('/dashboard', {
      templateUrl: '/views/dashboard.html',
      controller: 'dashboardCtrl',
      resolve: {
        auth: function ($q, SessionService, $location) {
          var deferred = $q.defer();
          if (SessionService.getItem('currentUser')) {
            deferred.resolve();
          } else {
            deferred.reject();
            $location.path('/login');
          }
          return deferred.promise;
        },
      }
    })
    .when('/resetpassword', {
      templateUrl: '/views/resetpassword.html',
      controller: 'resetPasswordCtrl',
    })
    .otherwise({
      redirectTo: '/login',
    });
});

app.controller('mainCtrl', function () {});
