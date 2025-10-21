<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$curl = curl_init();

require_once __DIR__ . '/../config.php';
curl_setopt($curl, CURLOPT_URL, api_url('/gastro-tech/api/v1/dishes/category-dishes/all'));

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

$responseData = json_decode($response, true);

if ($httpCode >= 400) {
    $responseData = array('error' => 'Error del servidor.');
}

if (!is_array($responseData)) {
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
                  <h4 class="card-title">Crear Plato</h4>
                  <p class="card-description">
                    Crear platos del restaurante
                  </p>
                  <form class="forms-sample" action="../php/guardar_plato.php" method="post">
                    <div class="form-group">
                      <label for="selectCategory">Categoria</label>
                        <select class="form-control" id="categoryDishesDTO" name="categoryDishesDTO" Required>
                        <option value="">Seleccione categoría</option>
                        <?php foreach ($responseData as $category): ?>
                          <option value="<?= $category['name'] ?>"><?= $category['name'] ?></option>
                        <?php endforeach; ?>
                        </select>
                      </div>
                    <div class="form-group">
                      <label for="dishes">Nombre Plato</label>
                      <input type="text" class="form-control" id="name" name="name" Required>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputEmail3">Descripcion</label>
                      <input type="text" class="form-control" id="description" name="description" Required>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">Precio</label>
                      <input type="number" class="form-control" id="price" name="price" Required>
                    </div>
                    <div class="form-group">
                      <label>File upload</label>                      
                      <div class="input-group col-xs-12">                     
                        <input class="form-control" type="file" name="img[]" id="formFile" multiple>
                      </div>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Guardar</button>
                    <button class="btn btn-light">Cancelar</button>
                    <a class="btn btn-success" href="lista_platos.php">platos</a>
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
