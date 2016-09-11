var RegForm = function () {

    return {
        
        //Registration Form
        initRegForm: function () {
	        // Validation       
	        $("#sky-form4").validate({                   
	            // Rules for form validation
	            rules:
	            {
	                username:
	                {
	                    required: true
	                },
	                email:
	                {
	                    required: true,
	                    email: true
	                },
	                password:
	                {
	                    required: true,
	                    minlength: 5,
	                    maxlength: 20
	                },
	                passwordConfirm:
	                {
	                    required: true,
	                    minlength: 5,
	                    maxlength: 20,
	                    equalTo: '#password'
	                },
                        passwordActual:
	                {
	                    required: true,
	                    minlength: 5,
	                    maxlength: 20,	                    
	                },
	                firstname:
	                {
	                    required: true
	                },
	                lastname:
	                {
	                    required: true
	                },
	                gender:
	                {
	                    required: true
	                },
	                terms:
	                {
	                    required: true
	                }
	            },
	            
	            // Messages for form validation
	            messages:
	            {
	                login:
	                {
	                    required: 'Please enter your login'
	                },
	                email:
	                {
	                    required: 'Introduce una dirección de correo electrónico',
	                    email: 'Introduce una dirección de correo electrónico'
	                },
	                password:
	                {
	                    required: 'Por favor, introduzca su contraseña'
	                },
	                passwordConfirm:
	                {
	                    required: 'Por favor, introduzca su contraseña una vez más',
	                    equalTo: 'Por favor, introduzca la misma contraseña'
	                },
	                firstname:
	                {
	                    required: 'Please select your first name'
	                },
	                lastname:
	                {
	                    required: 'Please select your last name'
	                },
	                gender:
	                {
	                    required: 'Please select your gender'
	                },
	                terms:
	                {
	                    required: 'You must agree with Terms and Conditions'
	                }
	            },                  
	            
	            // Do not change code below
	            errorPlacement: function(error, element)
	            {
	                error.insertAfter(element.parent());
	            }
	        });
        }

    };
}();