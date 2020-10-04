<?php 

	require_once 'DbConnect.php';

	$response = array();
	
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			
			case 'signup':
				if(isTheseParametersAvailable(array('username','email','password'))){
					$username = $_POST['username']; 
					$email = $_POST['email']; 
					$password = md5($_POST['password']);
					$clearance = 0;
					
					$stmt = $conn->prepare("SELECT id FROM users WHERE email = ?");
					$stmt->bind_param("s", $email);
					$stmt->execute();
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						$response['error'] = true;
						$response['message'] = 'User already registered';
						$stmt->close();
					}else{
						$stmt = $conn->prepare("INSERT INTO users (username, email, password, clearance) VALUES (?, ?, ?, ?)");
						$stmt->bind_param("sssi", $username, $email, $password, $clearance);

						if($stmt->execute()){
							$stmt = $conn->prepare("SELECT id, id, username, email FROM users WHERE username = ?"); 
							$stmt->bind_param("s",$username);
							$stmt->execute();
							$stmt->bind_result($userid, $id, $username, $email);
							$stmt->fetch();
							
							$user = array(
								'id'=>$id, 
								'username'=>$username, 
								'email'=>$email,
							);
							
							$stmt->close();
							
							$response['error'] = false; 
							$response['message'] = 'User registered successfully'; 
							$response['user'] = $user; 

								try { 
							$headers = "From:Team Deliverinator<no-reply@deliverinator.com>" . "\r\n" ."Reply-To:no-reply@deliverinator.com" . "\r\n";



$mail = mail($email,"Welcome to Deliverinator","Yolo bruh",$headers);

} catch (Exception $e) { 
    echo "Message could not be sent. Mailer Error: {$mail->ErrorInfo}"; 
} 

						}
					}
					
				}else{
					$response['error'] = true; 
					$response['message'] = 'required parameters are not available'; 
				}
				
			break; 
			
			case 'login':
				
				if(isTheseParametersAvailable(array('email', 'password'))){
					
					$email = $_POST['email'];
					$password = md5($_POST['password']); 
					
					$stmt = $conn->prepare("SELECT id, username, email FROM users WHERE email = ? AND password = ?");
					$stmt->bind_param("ss",$email, $password);
					
					$stmt->execute();
					
					$stmt->store_result();
					
					if($stmt->num_rows > 0){
						
						$stmt->bind_result($id, $username, $email);
						$stmt->fetch();
						
						$user = array(
							'id'=>$id, 
							'username'=>$username, 
							'email'=>$email,
						);
						
						$response['error'] = false; 
						$response['message'] = 'Login successfull'; 
						$response['user'] = $user; 
					}else{
						$response['error'] = false; 
						$response['message'] = 'Invalid username or password';
					}
				}
			break; 
			
			case 'updateuser':

				if(isTheseParametersAvailable(array('id','cell','address','city','state','pincode'))){
					$id = $_POST['id'];
					$cell = $_POST['cell']; 
					$address = $_POST['address']; 
					$city = $_POST['city'];
					$state = $_POST['state']; 
					$pincode = $_POST['pincode'];
					
						
						$stmt = $conn->prepare("UPDATE users SET cell = ?, address = ?, city = ?, state = ?, pincode = ? WHERE id = ?" );
						$stmt->bind_param("ssssss", $cell, $address, $city, $state, $pincode,$id);

						if($stmt->execute()){
							
							
							$response['error'] = false; 
							$response['message'] = 'User profile updated successfully'; 
							
						}else{
							$response['error'] = true; 
							$response['message'] = 'User profile updation failed'; 
						}
					
					
				}else{
					$response['error'] = true; 
					$response['message'] = 'required parameters are not available'; 
				}
				break;

			default: 
				$response['error'] = true; 
				$response['message'] = 'Invalid Operation Called';
		}
		
	}else{
		$response['error'] = true; 
		$response['message'] = 'Invalid API Call';
	}
	
	echo json_encode($response);
	
	function isTheseParametersAvailable($params){
		
		foreach($params as $param){
			if(!isset($_POST[$param])){
				return false; 
			}
		}
		return true; 
	}
