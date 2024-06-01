<?php
// Autenticación para obtener el token
session_start();

$token = $_SESSION['jwttoken'];

if ($token == null) {
    header('Location: ../pages/login.php');
    exit;
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
  if (isset($_FILES['photoDishesDTO'])) {
      $errors = array();
      $path = 'http://localhost/gastro-tech/images/dishes/'; 
      if (!file_exists($path)) {
        mkdir($path, 0777, true);
    }
      $file_urls = array(); // Array para almacenar las URLs de los archivos
      $extensions = array('jpg', 'jpeg', 'png'); // Ajusta esto a las extensiones que quieras permitir

      $all_files = count($_FILES['photoDishesDTO']['tmp_name']);

      for ($i = 0; $i < $all_files; $i++) {
          $file_name = $_FILES['photoDishesDTO']['name'][$i];
          $file_tmp = $_FILES['photoDishesDTO']['tmp_name'][$i];
          $file_type = $_FILES['photoDishesDTO']['type'][$i];
          $file_size = $_FILES['photoDishesDTO']['size'][$i];
          $file_ext = strtolower(end(explode('.', $_FILES['photoDishesDTO']['name'][$i])));

          $file = $path . $file_name;

          if(move_uploaded_file($file_tmp, $file)){
            echo "Archivo subido con exito";
            $file_urls[] = "http://localhost/gastro-tech/images/dishes/" . $file; // Añade la URL del archivo al array
          } else {
            echo "Error al subir el archivo";
          }

          if (!in_array($file_ext, $extensions)) {
              $errors[] = 'Extension not allowed: ' . $file_name . ' ' . $file_type;
          }

          if ($file_size > 2097152) {
              $errors[] = 'File size exceeds limit: ' . $file_name . ' ' . $file_type;
          }
      }

      if ($errors) print_r($errors);
  }

  $plato = $_POST['name'];
  $descripcion = $_POST['description'];
  $precio = $_POST['price'];
  $categoria = $_POST['categoryDishesDTO'];

  $data = array("name" => $plato,
                "description" => $descripcion,
                "price" => $precio,
                "categoryDishesDTO" => $categoria,
                "photoDishesDTO" => $file_urls, // Envía las URLs de los archivos
                );


                $curl = curl_init();

                // Set the URL with proper protocol (assuming it's a REST API)
                curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/dishes/dish");
                
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
    header('Location: ../pages/crear_plato.php');
      exit;          
  } else {
    header('Location: ../pages/samples/error_404.html');
    exit; 
  }
}
?>