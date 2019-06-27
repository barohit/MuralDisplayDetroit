function userNameValidate(){
	var username=document.getElementsByName("username")[0].value;
	console.log(username);
	if (username.length<4 || username==null){
		alert("username must be at least 4 characters long");
		return false;
	}
	return true;
}
function passwordValidate(){
	var pass = document.getElementsByName("password")[0].value;
	
	var confirmpass = document.getElementsByName("confirmpassword")[0].value; 
	
	if ((pass == confirmpass) && (pass != "")) {
	
		return true; 
	} else {
		if (pass == "" || confirmpass == "") {
	
			alert("password field cannot be null");
		} else {
			alert("passwords do not match");		
		}
	
		return false; 
	}
}
function validate() {
	
	if(userNameValidate() && passwordValidate()){
		
		return true;
	}else
		{
		
		return false;
		}
}

function InputTypeCheck() {

	var picture = document.getElementsByName("picture")[0].value;
	var address = document.getElementsByName("address")[0].value; 
	if (picture === null || address === null) {
		alert("Invalid");
		return false;
	} else {
		return true; 
	}
}
