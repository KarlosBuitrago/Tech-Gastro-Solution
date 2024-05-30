<?php

session_start();



if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];
echo $token;

$curl = curl_init();

curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/users/personasUsers");

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
echo $httpCode;
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
              <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Lista de Empleados</h4>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive pt-3">
                    <table class="table table-bordered">
                      <thead>
                        <tr>
                          <th>
                            #
                          </th>
                          <th>
                            Tipo de Identificacion
                          </th>
                          <th>
                            Identificacion
                          </th>
                          <th>
                            Primer Nombre
                          </th>
                          <th>
                            Segundo Nombre
                          </th>
                          <th>
                            primer Apellido
                          </th>
                          <th>
                            Segundo Apellido
                          </th>
                          <th>
                            Correo
                          </th>
                          <th>
                            Telefono
                          </th>
                          <th>
                            Direccion
                          </th>
                          <th>
                            Ciudad
                          </th>
                          <th>
                            Pais
                          </th>
                          <th>
                            Fecha de Nacimiento
                          </th>
                          <th>
                            Edad
                          </th>
                          <th>
                            Usuario
                          </th>
                          <th>
                            Rol
                          </th>
                          <th>
                            Estado
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
                                    echo "<td>{$empleado['typeIdentification']}</td>";
                                    echo "<td>{$empleado['identification']}</td>";
                                    echo "<td>{$empleado['firstName']}</td>";
                                    echo "<td>{$empleado['middleName']}</td>";
                                    echo "<td>{$empleado['secondLastName']}</td>";
                                    echo "<td>{$empleado['email']}</td>";
                                    echo "<td>{$empleado['phone']}</td>";
                                    echo "<td>{$empleado['address']}</td>";
                                    echo "<td>{$empleado['city']}</td>";
                                    echo "<td>{$empleado['country']}</td>";
                                    echo "<td>{$empleado['birthdate']}</td>";
                                    echo "<td>{$empleado['age']}</td>";
                                    echo "<td>{$empleado['username']}</td>";
                                    echo "<td>{$empleado['role']}</td>";
                                    echo "<td>{$empleado['status']}</td>"; 
                                    echo "<td><a href='edit_persona.php?id={$empleado['id']}' class='btn btn-warning btn-sm'>Editar <i class='fa fa-pencil-square-o' aria-hidden='true'></i></a></td>";
                                    echo "<td><a href='list_persona.php?id={$empleado['id']}' class='btn btn-danger btn-sm'> Borrar<i class='fa fa-trash-o' aria-hidden='true'></i></a></td>";
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
                  <a href="registrar_empleado.php" class="btn btn-success ">Agregar Empleado <i class="fa fa-plus-square" aria-hidden="true"></i></a>
                    <a href="home.php" class="btn btn-primary ">Go to Home</a>
                </div>
              </div>
            </div>
              </div>
            </div>            
          </div>
        </div>
        <!-- content-wrapper ends -->
        <!-- partial:../../partials/_footer.html -->
        <footer class="footer">
            <div class="d-sm-flex justify-content-center justify-content-sm-between">
              <span class="text-center text-sm-left d-block d-sm-inline-block">Copyright © <a href="https://www.bootstrapdash.com/" target="_blank">bootstrapdash.com</a> 2020</span>
              <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Free <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap dashboard </a>templates from Bootstrapdash.com</span>
            </div>
          </footer>
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
