<?php
// Autenticación para obtener el token
session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];


if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = $_POST['name'];

    $data = array(
        "name" => $name
    );

    $curl = curl_init();

    curl_setopt_array($curl, array(
  require_once __DIR__ . '/../config.php';
  CURLOPT_URL => api_url('/gastro-tech/api/v1/orders/pay-method/update'),
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_CUSTOMREQUEST => 'PUT',
        CURLOPT_POSTFIELDS =>  json_encode($data),
        CURLOPT_HTTPHEADER => array(
          "Authorization: " . $token,
          "Content-Type: application/json"
        ),
      ));
    
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        header('Location: ../pages/registrar_metodo_pago.php');
        exit;
    } else {
        echo "Error al guardar el método de pago: " . $response;
    }
} else {
    header('Location: ../samples/error-404.html');
    exit();
}
?>
