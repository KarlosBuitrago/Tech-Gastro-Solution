<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$curl = curl_init();

curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/dishes/category-dishes/all");

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
                  <h4 class="card-title">Crear Categoria de Plato</h4>
                  <p class="card-description">
                    Crear categoria de platos del restaurante
                  </p>
                  <form class="forms-sample" action="../php/crear_categoria_plato.php" method="post">                    
                    <div class="form-group">
                      <label for="dishes">Nombre Categoria</label>
                      <input type="text" class="form-control" id="name" name="name" Required>
                    </div>
                    <button type="submit" class="btn btn-primary mr-2">Guardar</button>
                    <button class="btn btn-light">Cancelar</button>
                  </form>
                </div>
                <div class="card-body">
                  <h4 class="card-title">Lista de Platos</h4>
                  <div class="table-responsive pt-3">
                    <div class="mt-3">                      
                    </div>
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Categoria
                          </th>
                          <th>
                            Editar
                          </th>
                          <th>
                            Eliminar
                          </th>
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
                                    echo "<td><a href='editar_categoria_plato.php?id={$empleado['id']}' class='btn btn-warning btn-sm'>Editar <i class='fa fa-pencil-square-o' aria-hidden='true'></i></a></td>";
                                    echo "<td><a href='../php/eliminar_categoria_plato.php?id={$empleado['id']}' class='btn btn-danger btn-sm'> Borrar<i class='fa fa-trash-o' aria-hidden='true'></i></a></td>";
                                    echo "</tr>";
                                }else{
                                  echo "No llega un array, llego una cadena ";                        
                                
                                }
                            }  
                          } else {
                              echo "<tr><td colspan='10'>No hay personas registradas.</td></tr>";
                          }
                        ?>                        
                      </tbody>
                    </table>
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
