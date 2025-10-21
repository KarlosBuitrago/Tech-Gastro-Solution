<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$curl = curl_init();

require_once __DIR__ . '/../config.php';
curl_setopt($curl, CURLOPT_URL, api_url('/gastro-tech/api/v1/tables'));

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

// $curl = curl_init();

// curl_setopt($curl, CURLOPT_URL, api_url('/gastro-tech/api/v1/orders/dishes-orders-list'));

// curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'GET');

// curl_setopt($curl, CURLOPT_HTTPHEADER, array(
//   "Authorization:" . $token,
//   "Content-Type: application/json"
// ));

// curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);


// if (curl_errno($curl)) {
//   echo "Curl error: " . curl_error($curl) . "\n";
//   curl_close($curl);
//   exit;
// }

// $response2 = curl_exec($curl);

// $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

// curl_close($curl);

// $dishesOrders = json_decode($response2, true);

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
                  <h4 class="card-title">Crear ordenes</h4>
                  <form class="forms-sample" action="../php/registrar_orden.php" method="post">
                    <div class="form-group">
                      <label for="dishes">Fecha de solicitud</label>
                      <input type="date" class="form-control" id="orderDate" name="orderDate" required>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputEmail3">Estado</label>
                      <select class="form-control" id="exampleFormControlSelect1" id="status" name="status">
                          <option>Entregado</option>
                          <option>Pendiente</option>
                          <option>En proceso</option>
                        </select>
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword4">Mesa</label>
                      <div class="form-group">
                          <label for="exampleFormControlSelect1"></label>
                          <select class="form-control" id="tablesDTO" name="tablesDTO" Required>
                            <?php foreach ($responseData as $table): ?>
                              <option value="<?= $table['name'] ?>"><?= $table['name'] ?></option>
                            <?php endforeach; ?>
                          </select>
                        </div>
                    </div>
                    <div class="form-group">
                      <label>Patillos</label>
                      <div class="form-group">
                        <label for="exampleFormControlSelect1"></label>
                        <select class="form-control" id="dishesOrders" name="dishesOrders">
                          <?php foreach ($dishesOrders as $category): ?>
                            <option value="<?= $dishesOrders['id'] ?>"><?= $category['id'] ?></option>
                          <?php endforeach; ?>
                        </select>
                        </div>
                    </div>
                    <div class="form-group">
                      <label>Total</label>
                      <input type="number" class="form-control" id="price" name="price">
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
