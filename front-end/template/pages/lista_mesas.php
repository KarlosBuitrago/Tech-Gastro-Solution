<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$curl = curl_init();

curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/tables");

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
  header('Location: ../pages/sample/error-404.html');
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
                  <h4 class="card-title">Mesas</h4>
                  <form class="form-sample" id="" action=>
                  <table class="table">
                    <thead>
                        <tr>
                        <th scope="col">#</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Editar</th>
                        <th scope="col">Eliminar</th>
                        </tr>
                    </thead>
                    <tbody>
                      <?php
                      if (is_array($responseData)){
                          foreach ($responseData as $empleado) {
                              if (is_array($empleado)) {
                                  echo "<tr>";                                   
                                  echo "<td>{$empleado['id']}</td>";
                                  echo "<td>{$empleado['name']}</td>";
                                  echo "<td>{$empleado['description']}</td>";
                                  echo "<td><a href='actualizar_empleado.php?id={$empleado['id']}' class='btn btn-warning btn-sm'>Editar <i class='fa fa-pencil-square-o' aria-hidden='true'></i></a></td>";
                                  echo "<td><a href='../php/eliminar_empleado.php?id={$empleado['id']}' class='btn btn-danger btn-sm'> Borrar<i class='fa fa-trash-o' aria-hidden='true'></i></a></td>";
                                  echo "</tr>";
                              }else{
                                echo "No llega un array, llego una cadena ";                        
                              
                              }
                          }  
                        } else {
                            echo "<tr><td colspan='10'>No hay mesas registradas.</td></tr>";
                        }
                      ?>
                    </tbody>
                  </table> 
                  <div class="form-group">
                      <a type="button" class="btn btn-primary" href="registrar_mesas.php" >Registrar nueva mesa</a>
                    </div>                   
                  
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
