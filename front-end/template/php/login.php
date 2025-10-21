<?php
// Autenticación para obtener el token
require_once __DIR__ . '/../config.php';
session_start();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
  $usuario = $_POST['username'];
  $password = $_POST['password'];
  
  $data = array("username" => $usuario,
                "password" => $password);

  $curl = curl_init();
  curl_setopt($curl, CURLOPT_URL, api_url('/authenticate'));
  curl_setopt($curl, CURLOPT_POST, true);
  curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
  curl_setopt($curl, CURLOPT_HTTPHEADER, array(
      "Content-Type: application/json"
  ));
  curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

  $response = curl_exec($curl);
  $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
  curl_close($curl);

  $responseData = json_decode($response, true);

  if (!empty($responseData['jwttoken'])) {
    $_SESSION['jwttoken'] = $responseData['jwttoken'];

    // Hacer la segunda consulta a la API
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, api_url('/gastro-tech/api/v1/users/personasUsers/username/' . $usuario));
    curl_setopt($curl, CURLOPT_HTTPGET, true);
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(
        "Content-Type: application/json",
        "Authorization: " . $_SESSION['jwttoken']
    ));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        $userDetails = json_decode($response, true); 
        $_SESSION['userDetails'] = $userDetails;
    } else {
        echo "Error al obtener los detalles del usuario: ";
    }

    header('Location: ../pages/home.php');
    exit;
  } else {
      echo "Error en la autenticación: " . ($responseData['message'] ?? 'Unknown');
  }
}
?>
