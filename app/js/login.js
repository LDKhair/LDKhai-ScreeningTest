app.controller(
  'loginCtrl',
  function ($scope, $http, $location, PasswordService, SessionService) {
    $scope.showPassword = true;
    $scope.viewForgotPass = false;
    $scope.username = '';
    $scope.password = '';
    $scope.email = '';

    // Toggle password
    $scope.handleShowPass = function () {
      PasswordService.toggleShowPassword($scope);
    };

    // Login function

    // Account test (hieuthuhai, Admin123)
    $scope.login = function (username, password) {
      var data = {
        username,
        password,
      };

      $http
        .post('http://localhost:8080/LeDucKhai-ScreeningTest/login', data, {
          headers: { 'Content-Type': 'application/json' },
        })
        .then(
          (res) => handleLoginSuccess(res.data),
          (err) => console.log(err)
        );
    };

    // Handle login success response and redirect to dashboard if login successful, otherwise show error message
    function handleLoginSuccess(data) {
      if (data.status == 200) {
        SessionService.setItem('currentUser', data.message);
        $location.path('/dashboard');
      } else {
        alert(data.message);
      }
    }

    // Show view forgot password
    $scope.showViewForgotPassword = function () {
      $scope.viewForgotPass = !$scope.viewForgotPass;
    };

    // Forgot password function
    $scope.forgotPassword = function () {
      if($scope.email) {
        $http.get(`http://localhost:8080/LeDucKhai-ScreeningTest/forgotpass/?email=${$scope.email}`)
        .then( (res) => {
          $scope.email = '';
          alert(res.data.message)
          $location.path("/login")
        });
      }
    };
  }
);
