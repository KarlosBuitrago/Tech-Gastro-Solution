<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$curl = curl_init();

require_once __DIR__ . '/../config.php';
curl_setopt($curl, CURLOPT_URL, api_url('/gastro-tech/api/v1/orders/dishes-orders-list'));

curl_setopt($curl, CURLOPT_CUSTOMREQUEST, 'GET');

curl_setopt($curl, CURLOPT_HTTPHEADER, array(
  "Authorization:" . $token,
  "Content-Type: application/json"
));

curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

//$response = curl_exec($curl);

if (curl_errno($curl)) {
  echo "Curl error: " . curl_error($curl) . "\n";
  curl_close($curl);
  exit;
}

$response = curl_exec($curl);

$httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);

curl_close($curl);

$responseData = json_decode($response, true);

if ($httpCode >= 400) {
    $responseData = array('error' => 'Error del servidor.');
}

// if (!is_array($responseData)) {
//   echo "Error: Invalid JSON response from API.\n";
//   exit;
// }
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
                  <h4 class="card-title">Reportes de ordenes</h4>
                  <div class="row">
                    <div class="col-md-3">
                      <label for="nombreFiltro">Tipo</label>
                      <div class="form-group">
                            <label for="exampleFormControlSelect1"></label>
                            <select class="form-control" id="exampleFormControlSelect1">
                                <option>Entregado</option>
                                <option>Pendiente</option>
                                <option>En proceso</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                      <label for="apellidoFiltro">Mostrar desde</label>
                      <input type="date" class="form-control" id="dateInit" name="dateInit" required>
                    </div>
                    <div class="col-md-3">
                      <label for="ciudadFiltro">Mostrar hasta</label>
                      <input type="date" class="form-control" id="dateEnd" name="dateEnd" required>
                    </div>
                    <div class="col-md-3">
                    <div class="btn-group" role="group" aria-label="Basic example"  >
                    <a type="button" href="crear_ordenes.php" class="btn btn-secondary"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2"/>
                      </svg>Agregar
                    </a>
                    <a type="button" class="btn btn-secondary" href="#">
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-printer" viewBox="0 0 16 16">
                      <path d="M2.5 8a.5.5 0 1 0 0-1 .5.5 0 0 0 0 1"/>
                      <path d="M5 1a2 2 0 0 0-2 2v2H2a2 2 0 0 0-2 2v3a2 2 0 0 0 2 2h1v1a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2v-1h1a2 2 0 0 0 2-2V7a2 2 0 0 0-2-2h-1V3a2 2 0 0 0-2-2zM4 3a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1v2H4zm1 5a2 2 0 0 0-2 2v1H2a1 1 0 0 1-1-1V7a1 1 0 0 1 1-1h12a1 1 0 0 1 1 1v3a1 1 0 0 1-1 1h-1v-1a2 2 0 0 0-2-2zm7 2v3a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1v-3a1 1 0 0 1 1-1h6a1 1 0 0 1 1 1"/>
                    </svg> Imprimir
                    </a>
                  </div>
                    </div>
                  </div>
                  <table class="table">
                    <thead>
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">Numero de orden</th>
                        <th scope="col">Fecha de solicitud</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Usuario</th>
                        <th scope="col">Mesa</th>
                        <th scope="col">Platillo</th>
                        <th scope="col">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                    <?php
                        if (is_array($responseData)){
                            foreach ($responseData as $orders) {
                                if (is_array($orders)) {
                                    echo "<tr>";
                                    echo "<td>{$orders['id']}</td>";
                                    echo "<td>{$orders['orderNumber']}</td>";
                                    echo "<td>{$orders['orderDate']}</td>";
                                    echo "<td>{$orders['status']}</td>";
                                    echo "<td>{$orders['userDTO']}</td>";
                                    echo "<td>{$orders['tablesDTO']}</td>";
                                    echo "<td>{$orders['paymentDTO']}</td>";
                                    echo "</tr>";
                                }else{
                                  echo "No llega un array, llego una cadena ";                        
                                
                                }
                            }  
                          } else {
                              echo "<tr><td colspan='10'>No hay ordenes registradas.</td></tr>";
                          }
                        ?>
                    </tbody>
                  </table>
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
