<?php
// Autenticación para obtener el token

session_start();

if (!isset($_SESSION['jwttoken'])) {
    header('Location: ../index.php');
    exit();
  }
  
  $token = $_SESSION['jwttoken'];

if($_SERVER['REQUEST_METHOD'] == 'POST'){
  $mesa = $_POST['name'];
  $description = $_POST['description'];
  

  $data = array("name" => $mesa,
                "description" => $description);

  $curl = curl_init();
  curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/table");
  curl_setopt($curl, CURLOPT_POST, true);
  curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
  curl_setopt($curl, CURLOPT_HTTPHEADER, array(
    "Authorization: " . $token,
    "Content-Type: application/json"
  ));
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

  $response = curl_exec($curl);
  $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
  curl_close($curl);

  $responseData = json_decode($response, true);

  if ($responseData !== null) {
    header('Location: ../pages/registrar_mesas.php');
      exit;      
  } else {
    header('Location: ../pages/sample/error-404.html');
    exit;
  }
}
?>