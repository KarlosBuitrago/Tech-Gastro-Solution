<?php
// Autenticación para obtener el token

session_start();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
  $usuario = $_POST['username'];
  $password = $_POST['password'];
    
  echo $usuario;


  $data = array("username" => $usuario,
                "password" => $password);

  $curl = curl_init();
  curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/authenticate");
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

  if ($responseData['jwttoken']) {
    $_SESSION['jwttoken'] = $responseData['jwttoken'];
      // Redirige a otra página
      header('Location: ../pages/home.php');
      exit;
  } else {
      // Manejar el error si la autenticación no fue exitosa
      echo "Error en la autenticación: " . $responseData['message'];
  }
}
?>
