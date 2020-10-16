<?php 

	require_once 'DbConnect.php';
	
	$response = array();
	
	if(isset($_GET['apicall'])){
		
		switch($_GET['apicall']){
			case 'addpackage':

    if (isTheseParametersAvailable(array(
    	'uid',
        'title',
        'description',
        'weight',
        'address',
        'city',
        'state',
        'pincode',
        'type'
    )))
    {
    	$uid = $_POST['uid'];
        $title = $_POST['title'];
        $description = $_POST['description'];
        $weight = $_POST['weight'];
        $address = $_POST['address'];
        $city = $_POST['city'];
        $state = $_POST['state'];
        $pincode = $_POST['pincode'];
        $type = $_POST['type'];
        $status = "Placed";
        $statuscode = 0;

        try{
        $stmt = $conn->prepare("INSERT INTO courier (uid,title,description,weight,address,city, state, pincode,ctype,cstatus, cstatuscode) VALUES (?,?, ?, ?, ?,?, ?,?, ?,?,?)");

        $stmt->bind_param("ississssisi",$uid,$title, $description, intval($weight), $address, $city, $state, $pincode, intval($type),$status, $statuscode);


        if ($stmt->execute())
        {
            $response['error'] = false;
            $response['message'] = 'Package added successfully';
        }else{
        	$response['error'] = true;
            $response['message'] = 'Package addition failed';
        }
        }catch (Exception $e) {
    	echo 'Caught exception: ',  $e->getMessage(), "\n";
}


    }
    else
    {
        $response['error'] = true;
        $response['message'] = 'required parameters are not available';
    }
break;		

case 'getall':

if (isTheseParametersAvailable(array(
    	'uid'
    )))
    {
    	$uid = $_POST['uid'];

        try{
        	$orders = array();
        	$sql = "SELECT title,description,weight,address,city, state, pincode,ctype,cstatus, cstatuscode FROM courier WHERE uid = '$uid'";
			$result = $conn->query($sql);

			if ($result->num_rows > 0) {
  // output data of each row
  				while($row = $result->fetch_assoc()) {
    				array_push($orders,array($row['title'],$row['description'],$row['weight'],$row['address'],$row['city'],$row['state'],$row['pincode'],$row['ctype'],$row['cstatus'],$row['cstatuscode']));
  }
  $response['error'] = false;
        $response['list'] = $orders;
         $response['message'] = "Fetching live orders";
}else{
	$response['error'] = true;
        $response['message'] = "You have no live orders";
}
		
        }catch (Exception $e) {
    	echo 'Caught exception: ',  $e->getMessage(), "\n";
}

	
    }
    else
    {
        $response['error'] = true;
        $response['message'] = 'required parameters are not available';
    }

break;	
	default: 
				$response['error'] = true; 
				$response['message'] = 'Invalid Operation Called';
		}
}
else
{
    $response['error'] = true;
    $response['message'] = 'Invalid API Call';
}

echo json_encode($response);

function isTheseParametersAvailable($params)
{

    foreach ($params as $param)
    {
        if (!isset($_POST[$param]))
        {
            return false;
        }
    }
    return true;
}

