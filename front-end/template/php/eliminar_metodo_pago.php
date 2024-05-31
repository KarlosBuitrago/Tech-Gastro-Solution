<?php
// Autenticación para obtener el token
session_start();

if ($_SERVER['REQUEST_METHOD'] == 'GET') {
    $id = $_GET['id'];

    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/metodos_pago/" . $id);
    curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "DELETE");
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        header('Location: ../registrar_metodo_pago.php');
        exit;
    } else {
        echo "Error al eliminar el método de pago: " . $response;
    }
} else {
    header('Location: ../registrar_metodo_pago.php');
    exit();
}
?>