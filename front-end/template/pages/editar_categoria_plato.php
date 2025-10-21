<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$id = $_GET['id'];


$curl = curl_init();

require_once __DIR__ . '/../config.php';
curl_setopt($curl, CURLOPT_URL, api_url('/gastro-tech/api/v1/dishes/category-dishes/id/' . $id));

curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'GET');

curl_setopt($curl, CURLOPT_HTTPHEADER, array(
  "Authorization:" . $token,
  "Content-Type: application/json"
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

?>

<!DOCTYPE html>
<html lang="en">

<?php include 'partials/header.php'; ?>

<body>
  <div class="container-scroller">
    <!-- partial:../../partials/_navbar.html -->
    <?php include 'partials/navbar.php'; ?>    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:../../partials/_settings-panel.html -->
      <?php include 'partials/settings-panel.php'; ?>      
      <!-- partial -->
      <!-- partial:../../partials/_sidebar.html -->
      
      <?php include 'partials/sidebar.php'; ?>    
      <!-- partial -->
      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">           
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Editar Categoria de Plato</h4>
                  <p class="card-description">
                    Editar una categoria de platos del restaurante
                  </p>
                  <form class="forms-sample" action="../php/actualiza_categoria_plato.php" method="post">  
                    <div class="form-group">
                      <label for="dishes">Orden</label>
                      <input type="text" class="form-control" id="id" name="id" value="<?php echo $empleado['id']; ?>" Required>
                    </div>                  
                    <div class="form-group">
                      <label for="dishes">Nombre Categoria</label>
                      <input type="text" class="form-control" id="name" name="name" value="<?php echo $empleado['name']; ?>" Required>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Guardar</button>
                    <button class="btn btn-light">Cancelar</button>
                  </form>
                </div>                
              </div>
            </div>
          </div>
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:../../partials/_footer.html -->
        <?php include 'partials/footer.php'; ?>
        <!-- partial -->
      </div>
      <!-- main-panel ends -->
    </div>
    <!-- page-body-wrapper ends -->
  </div>
  <!-- container-scroller -->
  <!-- base:js -->
  <?php include 'partials/scripts.php'; ?>
  <!-- End custom js for this page-->
</body>

</html>
