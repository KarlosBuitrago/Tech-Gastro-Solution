<?php
// Autenticación para obtener el token

session_start();

if($_SERVER['REQUEST_METHOD'] == 'POST'){
  $usuario = $_POST['username'];
  $password = $_POST['password'];
  
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

    // Hacer la segunda consulta a la API
    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/users/personasUsers/username/" . $usuario); // Cambia esto a la URL de tu API
    curl_setopt($curl, CURLOPT_HTTPGET, true);
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(
        "Content-Type: application/json",
        "Authorization: " . $_SESSION['jwttoken'] // Asegúrate de que tu API soporta la autenticación con Bearer tokens
    ));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        $userDetails = json_decode($response, true); 
        var_dump($userDetails);
        $_SESSION['userDetails'] = $userDetails; // Almacena los detalles del usuario en la sesión
    } else {
        // Manejar el error si la solicitud no fue exitosa
        echo "Error al obtener los detalles del usuario: " ;
    }

    // Redirige a otra página
    header('Location: ../pages/home.php');
    exit;
  } else {
      // Manejar el error si la autenticación no fue exitosa
      echo "Error en la autenticación: " . $responseData['message'];
  }
}
?>
