function validate() {
	var pass = document.getElementsByName("password")[0].value;
	var confirmpass = document.getElementsByName("confirmpassword")[0].value; 
	if (pass === confirmpass) {
		return true; 
	} else {
		document.getElementById("password").innerHTML = "passwords do not match";
		return false; 
	}
}

