<?
$servername = "localhost";
$database = "u448101502_users";
$username = "u448101502_dbms";
$password = "MnyZ3LLz5wtKV5b";


 
$conn = new mysqli($servername, $username, $password, $database);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}