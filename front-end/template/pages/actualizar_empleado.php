<?php

session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$id = $_GET['id'];


if (!isset($id)) {
  header('Location: lista_empleados.php');
  exit();
}

$curl = curl_init();

curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/users/persona/id/".$id);

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
                  <h4 class="card-title">Registrar Empleado</h4>
                  <form class="form-sample" id="actualizarEmpleadoForm" action="../php/actualiza_empleado.php" method="post">
                    <p class="card-description">
                      Datos personales
                    </p>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          
                          <div class="col-sm-9">
                            
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                        <label class="col-sm-3 col-form-label"></label>
                          <div class="col-sm-3">
                          <a type="button" class="btn btn-primary" href="lista_empleados.php">Empleados</a>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Orden</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="id" name="id" value="<?php echo $empleado['id']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <div class="col-sm-9">                            
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Primer nombre</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="firstName" name="firstName" value="<?php echo $empleado['firstName']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Segundo nombre</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="middleName" name="middleName" value="<?php echo $empleado['middleName']; ?>"/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Primer apellido</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="firstLastName" name="firstLastName" value="<?php echo $empleado['firstLastName']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Segundo apellido</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="secondLastName" name="secondLastName" value="<?php echo $empleado['secondLastName']; ?>"/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Tipo de identificacion</label>
                          <div class="col-sm-9">
                            <select class="form-control" id="typeIdentification" name="typeIdentification" value="<?php echo $empleado['typeIdentification']; ?>" Required/>

                              <option>Cedula</option>
                              <option>Tarjeta de Identidad</option>
                              <option>Pasaporte</option>
                              <option>Cedula de extranjeria</option>
                            </select>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Documento</label>
                          <div class="col-sm-9">
                            <input class="form-control"  id="identification" name="identification" value="<?php echo $empleado['identification']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Email</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="email" name="email" value="<?php echo $empleado['email']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Telefono</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="phone" name="phone" value="<?php echo $empleado['phone']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Direccion</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="address" name="address" value="<?php echo $empleado['address']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Ciudad</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="city" name="city" value="<?php echo $empleado['city']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Pais</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="country" name="country" value="<?php echo $empleado['country']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Fecha de nacimiento</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" placeholder="dd-mm-AAAA" id="birthdate" name="birthdate" value="<?php echo $empleado['birthdate']; ?>" Required/>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Edad</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="age" name="age" value="<?php echo $empleado['age']; ?>" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Role</label>
                          <div class="col-sm-9">
                          <select class="form-control" id="role" name="role" value="<?php echo $empleado['role']; ?>" >
                              <option>Admin</option>
                              <option>Mesero</option>
                              <option>Cocinero</option>
                              <option>Administrador</option>
                              <option>Cajero</option>
                            </select>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label"></label>
                          <div class="col-sm-9">
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label"></label>
                          <div class="col-sm-9">
                          <button type="submit" class="btn btn-primary">Guardar</button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </form>
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
