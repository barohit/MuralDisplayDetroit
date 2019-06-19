function validate() {
	var pass = document.getElementsByName("password")[0].value;
	var confirmpass = document.getElementByName("confirmpassword")[0].value; 
	if (pass === confirmpass) {
		return true; 
	} else {
		return false; 
	}
}