<?php
session_start();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $username = $_POST['username'];
    $oldPassword = $_POST['oldPassword'];
    $newPassword = $_POST['password'];

    $data = array(
        "username" => $username,
        "password" => $newPassword
    );

    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/changePassword");
    curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(
        "Content-Type: application/json",
    ));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        header('Location: ../index.php');
        exit;
    } else {
        echo "Error al cambiar la contraseña: " . $response;
    }
} else {
    header('Location: ../cambiar_contraseña.php');
    exit();
}
?>
