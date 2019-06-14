
validate() {
	var pass = document.getElementsByName("password")[0];
	var confirmpass = document.getElementByName("confirmpassword")[0]; 
	if (pass === confirmpass) {
		return true; 
	} else {
		return false; 
	}
}