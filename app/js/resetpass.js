app.controller('resetPasswordCtrl', function($scope, $http, $location, PasswordService) {
    $scope.accResetPass = {
        username: '',
        newPassword: ''
    }

    // Toggle password
    $scope.handleShowPass = function () {
        PasswordService.toggleShowPassword($scope);
    };

    // Reset password function  (send new password to server)
    $scope.resetPassword = function() {
        $http.post('http://localhost:8080/LeDucKhai-ScreeningTest/resetpass', $scope.accResetPass)
           .then((res) => handleResetPasswordSuccess(res));
    }

    // Handle reset password success response and redirect to login page if reset successful, otherwise show error message
    function handleResetPasswordSuccess(res) {
        if(res.data.status == 200) {
            alert(res.data.message);
            $location.path('/login')
        } else {
            alert(res.data.message);
        }
    }
})