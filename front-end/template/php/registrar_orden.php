<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$curl = curl_init();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $date = $_POST['orderDate'];
    $status = $_POST['status'];
    $table = $_POST['tablesDTO'];
    $usuario = isset($_SESSION['username']['id_persona']) ? $_SESSION['username']['id_persona'] : null;
}  
    $data = array("orderDate" => $date,
                    "status" => $status,
                    "tables" => $table,
                    "user" => $usuario
                    );
  
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/orders/order");
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(
      "Authorization: " . $token,
      "Content-Type: application/json"
    ));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
  

if (curl_errno($curl)) {
  echo "Curl error: " . curl_error($curl) . "\n";
  curl_close($curl);
  exit;
}

$response = curl_exec($curl);

$httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

curl_close($curl);

if ($response === null || $response === '') {
  echo "Error: La respuesta está vacía.\n";
  exit;
}

$responseData = json_decode($response, true);

if ($httpCode >= 400) {
    $responseData = array('error' => 'Error del servidor.');
}

if (!is_array($responseData)) {
  echo "Error: Invalid JSON response from API.\n";
  exit;
}

?>