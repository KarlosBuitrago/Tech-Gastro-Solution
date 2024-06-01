<?php
// Autenticación para obtener el token
session_start();

if (!isset($_SESSION['jwttoken'])) {
    header('Location: ../index.php');
    exit();
  }
  
  $token = $_SESSION['jwttoken'];

  $id = $_GET['id'];

if ($_SERVER['REQUEST_METHOD'] == 'GET') {    

    $curl = curl_init();

    curl_setopt_array($curl, array(
        CURLOPT_URL => 'http://localhost:9000/gastro-tech/api/v1/orders/pay-method/delete/' . $id,
        CURLOPT_RETURNTRANSFER => true,
        CURLOPT_CUSTOMREQUEST => 'DELETE',
        CURLOPT_HTTPHEADER => array(
          "Authorization: " . $token,
          "Content-Type: application/json"
        ),
    ));

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        header('Location: ../pages/registrar_metodo_pago.php');
        exit;
    } else {
        echo "Error al eliminar el método de pago: " . $response;
    }
} else {
    header('Location: ../samples/error-404');
    exit();
}
?>