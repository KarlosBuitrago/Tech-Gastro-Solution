<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];


if($_SERVER['REQUEST_METHOD'] == 'POST'){
    $id = $_POST['id'];
    $tipoIdentificacion = $_POST['typeIdentification'];
    $identificacion = $_POST['identification'];
    $primerNombre = $_POST['firstName'];
    $segundoNombre = $_POST['middleName'];
    $primerApellido = $_POST['firstLastName'];
    $segundoApellido = $_POST['secondLastName'];
    $email = $_POST['email'];
    $telefono = $_POST['phone'];
    $direccion = $_POST['address'];
    $ciudad = $_POST['city'];
    $pais = $_POST['country'];
    $fechaNacimiento = $_POST['birthdate'];
    $role = $_POST['role'];
      
}  
    $data = array("id" => $id,
        "typeIdentification" => $tipoIdentificacion,
                  "identification" => $identificacion,
                  "firstName" => $primerNombre,
                  "middleName" => $segundoNombre,
                  "firstLastName" => $primerApellido,
                  "secondLastName" => $segundoApellido,
                  "email" => $email,
                  "phone" => $telefono,
                  "address" => $direccion,
                  "city" => $ciudad,
                  "country" => $pais,
                  "birthdate" => $fechaNacimiento,
                  "role" => $role);

$curl = curl_init();

curl_setopt_array($curl, array(
  require_once __DIR__ . '/../config.php';
  CURLOPT_URL => api_url('/gastro-tech/api/v1/users/persona/update'),
    CURLOPT_RETURNTRANSFER => true,
    CURLOPT_CUSTOMREQUEST => 'PUT',
    CURLOPT_POSTFIELDS =>  json_encode($data),
    CURLOPT_HTTPHEADER => array(
      "Authorization: Bearer " . $token,
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

if (!is_array($empleado)) {
  echo "Error: Invalid JSON response from API.\n";
  exit;
}
if ($empleado !== null) {
    header('Location: ../pages/lista_empleados.php');
      exit;          
  } else {
      // Manejar el error si la autenticación no fue exitosa
      echo "Error al guardar el usuario";
  }


?>