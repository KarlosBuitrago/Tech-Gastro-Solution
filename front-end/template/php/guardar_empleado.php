<?php
// Autenticación para obtener el token
session_start();

$token = $_SESSION['jwttoken'];

if ($token == null) {
    header('Location: ../pages/login.php');
    exit;
}
if($_SERVER['REQUEST_METHOD'] == 'POST'){
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
    

  $data = array("typeIdentification" => $tipoIdentificacion,
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

                // Set the URL with proper protocol (assuming it's a REST API)
                curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/users/persona");
                
                // Set POST method
                curl_setopt($curl, CURLOPT_POST, true);
                
                // Encode data as JSON
                curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
                
                // Set authorization header with correct format (assuming token includes "Bearer ")
                curl_setopt($curl, CURLOPT_HTTPHEADER, array(
                  "Authorization: " . $token,
                  "Content-Type: application/json"
                ));
                
                // Capture response and HTTP code
                curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
                $response = curl_exec($curl);
                $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
                
                // Close cURL session
                curl_close($curl);
                
                // Check for errors during execution
                if (curl_errno($curl)) {
                  echo "Curl error: " . curl_error($curl) . "\n";
                  exit;
                }
                                
                // Decode JSON response (if applicable)
                $responseData = json_decode($response, true);

  
  if ($responseData !== null) {
    header('Location: ../pages/registrar_empleado.php');
      exit;          
  } else {
      // Manejar el error si la autenticación no fue exitosa
      echo "Error al guardar el usuario";
  }
}
?>
