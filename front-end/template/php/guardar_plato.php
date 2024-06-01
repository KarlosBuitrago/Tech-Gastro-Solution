<?php
// Autenticación para obtener el token
session_start();

$token = $_SESSION['jwttoken'];

if ($token == null) {
    header('Location: ../pages/login.php');
    exit;
}

$file_urls = array(); // Inicializa el array vacío
$image_base_url = 'http://localhost/gastro-tech/images/dishes/'; // Define la URL base de las imágenes

if (isset($_FILES['img'])) {
    $errors = array();
    $image_dir_path = $_SERVER['DOCUMENT_ROOT'] . '/gastro-tech/images/dishes/';
    if (!file_exists($image_dir_path)) {
        mkdir($image_dir_path, 0777, true);
    }

    $extensions = array('jpg', 'jpeg', 'png'); // Ajusta esto a las extensiones que quieras permitir

    $all_files = count($_FILES['img']['tmp_name']);

    for ($i = 0; $i < $all_files; $i++) {
        $file_name = $_FILES['img']['name'][$i];
        $file_tmp = $_FILES['img']['tmp_name'][$i];
        $file_type = $_FILES['img']['type'][$i];
        $file_size = $_FILES['img']['size'][$i];
        $file_ext = strtolower(end(explode('.', $_FILES['img']['name'][$i])));

        $file = $image_dir_path . $file_name;

        if(move_uploaded_file($file_tmp, $file)){
            echo "Archivo subido con exito";
            $file_url = $image_base_url . $file_name;
            $file_urls[] = $file_url; // Añade la URL del archivo al array
        } else {
            echo "Error al subir el archivo";
        }

        if (!in_array($file_ext, $extensions)) {
            $errors[] = 'Extension not allowed: ' . $file_name . ' ' . $file_type;
        }
    }

    if ($errors) print_r($errors);
}

$plato = $_POST['name'];
$descripcion = $_POST['description'];
$precio = $_POST['price'];
$categoria = $_POST['categoryDishesDTO'];

echo $categoria;

$data = array("name" => $plato,
              "description" => $descripcion,
              "price" => $precio,
              "categoryDishesDTO" => array(
                "name" => $categoria
              ),
              "photoDishesDTO" => array_map(function($url) {
                return array("url" => $url);
              }, $file_urls)
              );

var_dump($data)     ;         

$curl = curl_init();

curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/dishes/dish");
curl_setopt($curl, CURLOPT_POST, true);
curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
curl_setopt($curl, CURLOPT_HTTPHEADER, array(
  "Authorization: " . $token,
  "Content-Type: application/json"
));
curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);
$response = curl_exec($curl);
$httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
curl_close($curl);

if (curl_errno($curl)) {
  echo "Curl error: " . curl_error($curl) . "\n";
  exit;
}

$responseData = json_decode($response, true);

if ($responseData !== null) {
  header('Location: ../pages/crear_plato.php');
  exit;          
} else {
  // header('Location: ../pages/samples/error_404.html');
  // exit; 
}
?>