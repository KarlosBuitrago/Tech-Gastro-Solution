<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id = $_POST['id'];
    $categoria = $_POST['name'];
    $description = $_POST['description'];
    
      
} 
    $data = array("id" => $id,
        "name" => $categoria,
        "description" => $description
                  );

$curl = curl_init();

curl_setopt_array($curl, array(
  require_once __DIR__ . '/../config.php';
  CURLOPT_URL => api_url('/gastro-tech/api/v1//table/update'),
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_CUSTOMREQUEST => 'PUT',
    CURLOPT_POSTFIELDS =>  json_encode($data),
    CURLOPT_HTTPHEADER => array(
      "Authorization: " . $token,
      "Content-Type: application/json"
    ),
  ));

curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);


if (curl_errno($curl)) {
  echo "Curl error: " . curl_error($curl) . "\n";
  curl_close($curl);
  exit;
}

$response = curl_exec($curl);

var_dump($response);

$httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
curl_close($curl);

if ($response === null || $response === '') {
  header('Location: ../pages/samples/error-404.html');
    exit;
}

$empleado = json_decode($response, true);


if ($httpCode >= 400) {
    $empleado = array('error' => 'Error del servidor.');
}

if (!is_array($empleado)) {
  echo "Error: Invalid JSON response from API.\n";
  exit;
}
if ($empleado !== null) {
    header('Location: ../pages/lista_mesas.php');
      exit;          
  } else {
    header('Location: ../pages/samples/error-404.html');
    exit;
  }


?>