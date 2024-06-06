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

    var_dump($name);

    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/orders/pay-method");
    curl_setopt($curl, CURLOPT_POST, true);
    curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(
        "Authorization:" . $token,
        "Content-Type: application/json"
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
