app.controller(
  'dashboardCtrl',
  function ($scope, $http, $rootScope, $timeout, $location, SessionService) {
    // create variables
    $scope.instructors = [];
    $scope.resData = [];
    $scope.valueSearch = '';
    $scope.sortOrder = 'asc';
    $scope.acc = SessionService.getItem('currentUser');

    $scope.instructorProp = {
      instructorID: true,
      firstName: true,
      lastName: true,
      typeName: true,
      levelName: true,
      startDate: true,
    };

    $scope.menuView = {
      viewTable: false,
      viewForm: false,
      viewFormBan: false,
      viewOverall: false,
    };

    $scope.instructor = {
      id: 0,
      firstName: '',
      lastName: '',
      instructorID: '',
      typeID: '1',
      levelID: '1',
      gender: 'true',
      email: '',
      address: '',
      startDate: new Date(),
    };

    $scope.errorMessage = {
      firstName: '',
      lastName: '',
      startDate: '',
      instructorID: '',
      email: '',
      address: '',
      password: '',
      newPassword: '',
    };

    $scope.isNotifications = true;
    $scope.toggleIconUpDown = true;
    $scope.isDropdownVisible = false;
    $scope.isCreate = false;

    // Toggle content
    document.querySelectorAll('.nav-btn').forEach((btn) => {
      btn.addEventListener('click', () => {
        document
          .querySelectorAll('.nav-btn')
          .forEach((btn) => btn.classList.remove('nav-btn-active'));
        btn.classList.add('nav-btn-active');
      });
    });

    // Fetch all instructors
    $scope.getAllInstructor = function () {
      $scope.viewLoading(2000);
      $http
        .get('http://localhost:8080/LeDucKhai-ScreeningTest/getall')
        .then((res) => {
          $scope.resData = res.data;
          $scope.instructors = $scope.resData;
        });
    };

    // Filter instructors based on the field
    $scope.filter = function (field) {
      $scope.instructorProp[field] = !$scope.instructorProp[field];
      $scope.sortOrder = $scope.sortOrder === 'asc' ? 'desc' : 'asc';

      $scope.instructors.sort((a, b) => {
        let comparison;

        if (typeof a[field] === 'string' && typeof b[field] === 'string') {
          comparison = a[field].localeCompare(b[field]);
        } else if (a[field] instanceof Date && b[field] instanceof Date) {
          comparison = a[field].getTime() - b[field].getTime();
        } else {
          comparison = a[field] - b[field];
        }

        return $scope.sortOrder === 'asc' ? comparison : -comparison;
      });
    };

    // Toggle views in the menu
    $scope.handleSetView = function (prop) {
      $scope.viewLoading(1600);
      for (let key in $scope.menuView) {
        if ($scope.menuView.hasOwnProperty(key)) {
          $scope.menuView[key] = false;
        }
      }
      $scope.menuView[prop] = true;
    };

    // Search instructors based on name or ID
    $scope.handleSearch = function (value) {
      var searchValue = value;

      if (searchValue) {
        searchValue = value.toLowerCase();
      } else {
        $scope.filteredInstructors = [];
        return;
      }

      $scope.filteredInstructors = $scope.instructors.filter((instructor) => {
        return (
          instructor.firstName.toLowerCase().includes(searchValue) ||
          instructor.lastName.toLowerCase().includes(searchValue) ||
          instructor.instructorID.toLowerCase().includes(searchValue)
        );
      });
    };

    // Show/hide the search dropdown result search field
    $scope.showDropdown = function (value) {
      if (value) {
        $scope.isDropdownVisible = true;
      }
    };

    $scope.hideDropdown = function () {
      setTimeout(function () {
        $scope.isDropdownVisible = false;
        $scope.$apply();
      }, 200);
    };

    // Create a new Instructor
    $scope.createInstructor = function () {
      if ($scope.validateForm()) {
        $scope.viewLoading(3000);
        $http
          .post(
            'http://localhost:8080/LeDucKhai-ScreeningTest/instructorservice/create',
            $scope.instructor,
            {
              headers: { 'Content-Type': 'application/json' },
            }
          )
          .then(
            (res) => handleCreateInstructorResponse(res.data),
            (error) => console.error('Error:', error.statusText)
          );
      }
    };

    
    function handleCreateInstructorResponse(result) {
      if (result.status === 200) {
        resetInstructorForm();
        alert(result.message);
      } else {
        displayValidationError(
          'instructorID',
          instructorIDInput,
          result.message
        );
      }
    }

    function resetInstructorForm() {
      $scope.instructor = {
        firstName: '',
        lastName: '',
        instructorID: '',
        typeID: '1',
        levelID: '1',
        gender: 'true',
        startDate: new Date(),
      };
    }

    function displayValidationError(field, message) {
      $scope.errorMessage[field] = message;
    }

    // Validate form submission
    function validateField({ target, field, format, mess }) {
      if (!target[field] || target[field].length === 0) {
        $scope.errorMessage[field] = 'This field is required';
        return false;
      } else if (!format(target[field])) {
        displayValidationError(field, mess);
        return false;
      } else {
        $scope.errorMessage[field] = '';
      }
      return true;
    }

    $scope.validateForm = function () {
      let isValid = true;

      const namePattern = /^[^\d]*$/;
      const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      const idPattern = /^[a-zA-Z0-9]+$/;

      let mess = 'Name should not contain numbers or special characters.';
      let validateFirstName = {
        target: $scope.instructor,
        field: 'firstName',
        format: (value) => namePattern.test(value),
        mess,
      };

      let validateLastName = {
        target: $scope.instructor,
        field: 'lastName',
        format: (value) => namePattern.test(value),
        mess,
      };

      let validateInstructorID = {
        target: $scope.instructor,
        field: 'instructorID',
        format: (value) => idPattern.test(value),
        mess: 'Instructor ID should only contain alphanumeric characters.',
      };

      let validateStartDate = {
        target: $scope.instructor,
        field: 'startDate',
        format: (value) => !(new Date(value) <= new Date()),
        mess: 'Start date should be before today.',
      };

      let validateEmail = {
        target: $scope.instructor,
        field: 'email',
        format: (value) => emailPattern.test(value),
        mess: 'Email invalid.',
      };

      let validateAddress = {
        target: $scope.instructor,
        field: 'address',
        format: (value) => value,
        mess: '',
      };

      isValid = validateField(validateFirstName);
      isValid = validateField(validateLastName);
      isValid = validateField(validateInstructorID);
      isValid = validateField(validateStartDate);
      isValid = validateField(validateEmail);
      isValid = validateField(validateAddress);

      return isValid;
    };

    // Update instructor information
    $scope.showDetail = function (code, string) {
      $scope.viewLoading(1600);

      $scope.handleSetView(string);

      fetchInstructorDetails(code);
    };

    function fetchInstructorDetails(code) {
      return $http
        .get(
          `http://localhost:8080/LeDucKhai-ScreeningTest/getall/getdetail/?id=${code}`
        )
        .then(
          (res) => {
            var result = {
              ...res.data,
              startDate: new Date(res.data.startDate),
              typeID: String(res.data.typeID),
              levelID: String(res.data.levelID),
              gender: res.data.gender ? 'true' : 'false',
            };

            $scope.instructor = result;
          },
          (error) => console.error('Error:', error.statusText)
        );
    }

    $scope.createNew = function () {
      $scope.isCreate = true;

      $scope.instructor = {
        id: 0,
        firstName: '',
        lastName: '',
        instructorID: '',
        typeID: '1',
        levelID: '1',
        gender: 'true',
        startDate: new Date(),
      };
    };

    $scope.updateInfoAdmin = function () {
      var data = {
        id: $scope.instructor.id,
        typeID: parseInt($scope.instructor.typeID),
        levelID: parseInt($scope.instructor.levelID),
        email: $scope.instructor.email,
        address: $scope.instructor.address,
      };

      $http
        .post(
          `http://localhost:8080/LeDucKhai-ScreeningTest/instructorservice/update`,
          data
        )
        .then((res) => {
          $scope.instructor = {
            id: 0,
            firstName: '',
            lastName: '',
            instructorID: '',
            typeID: '1',
            levelID: '1',
            gender: 'true',
            startDate: new Date(),
          };
          alert(res.data.message)
        } );
    }

    $scope.updateInfoInstructor = function () {
      var data = {
        id: $scope.instructor.id,
        typeID: parseInt($scope.instructor.typeID),
        levelID: parseInt($scope.instructor.levelID),
        email: $scope.instructor.email,
        address: $scope.instructor.address,
      };

      $http
        .post(
          `http://localhost:8080/LeDucKhai-ScreeningTest/instructorservice/update`,
          data
        )
        .then((res) => handleUpdateSuccess(res));
    };

    function handleUpdateSuccess(res) {
      if (res.data.status == 200) {

        alert(res.data.message);
        $scope.viewLoading(2000);

        $scope.viewProfile($scope.acc.idInstructor)
      }
    }

    // Function view personal information
    $scope.viewProfile = function (code) {
      fetchInstructorDetails(code);
    };

    $scope.viewLoading = function (time) {
      $rootScope.loading = true;

      $timeout(function () {
        $rootScope.loading = false;
      }, time);
    };

    $scope.logOut = function () {
      $scope.acc = {};
      SessionService.removeItem('currentUser');
      $location.path('/login');
    };

    $scope.infoChangePassword = {
      username: '',
      password: '',
      newPassword: '',
    };

    function validateFieldChangePassword() {
      const passwordPattern = /^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{6,}$/;

      let message =
        'Password must be at least 6 characters long, contain at least one uppercase letter, one lowercase letter, and one number.';

      let validatePassword = {
        target: $scope.infoChangePassword,
        field: 'password',
        format: (value) => passwordPattern.test(value),
        mess: message,
      };

      let validateNewPassword = {
        target: $scope.infoChangePassword,
        field: 'newPassword',
        format: (value) => passwordPattern.test(value),
        mess: message,
      };

      return (
        validateField(validatePassword) || validateField(validateNewPassword)
      );
    }
    // Function change password
    $scope.changePassword = function (password, newPassword) {
      if (validateFieldChangePassword()) {
        $scope.infoChangePassword = {
          username: $scope.acc.username,
          password: password,
          newPassword: newPassword,
        };

        console.log($scope.infoChangePassword)
        $http
          .post(
            `http://localhost:8080/LeDucKhai-ScreeningTest/changepass`,
            $scope.infoChangePassword
          )
          .then((res) => handleChangePassSuccess(res));
      }
    };

    function handleChangePassSuccess(res) {
      if (res.data.status == 200) {
        $scope.logOut();
        alert(res.data.message);
      } else {
        alert(res.data.message);
      }
    }

    $scope.showPassword = {
      current: false,
      new: false,
    };
    $scope.togglePassword = function (inputId) {
      $scope.showPassword[inputId] = !$scope.showPassword[inputId];
    };
  }
);
