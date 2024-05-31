<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$id = $_GET['id'];

$curl = curl_init();

curl_setopt_array($curl, array(
    CURLOPT_URL => 'http://localhost:9000/gastro-tech/api/v1/dishes/category-dishes/' . $id,
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_CUSTOMREQUEST => 'DELETE',
    CURLOPT_HTTPHEADER => array(
      "Authorization: " . $token,
      "Content-Type: application/json"
    ),
));

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

$empleado = json_decode($response, true);

if ($httpCode >= 400) {
    $empleado = array('error' => 'Error del servidor.');
}

if ($empleado !== null) {
  header('Location: ../pages/categoria_plato.php');
  exit;           
  } else {
    header('Location: ../pages/samples/error-404.html');
    exit;
  }

?>