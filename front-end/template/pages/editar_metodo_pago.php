<?php
session_start();

if (!isset($_SESSION['jwttoken'])) {
  header('Location: ../index.php');
  exit();
}

$token = $_SESSION['jwttoken'];

$id = $_GET['id'];

$curl = curl_init();

curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/orders/pay-method/id/".$id);

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

$metodoPago = json_decode($response, true);

if ($httpCode >= 400) {
    $mesa = array('error' => 'Error del servidor.');
}

if (!is_array($metodoPago)) {
  echo "Error: Invalid JSON response from API.\n";
  exit;
}

?>

<!DOCTYPE html>
<html lang="en">

<?php include '../partials/header.php'; ?>

<body>
  <div class="container-scroller">
    <?php include '../partials/navbar.php'; ?>
    <div class="container-fluid page-body-wrapper">
      <?php include '../partials/settings-panel.php'; ?>
      <?php include '../partials/sidebar.php'; ?>
      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Editar Método de Pago</h4>
                  <form class="form-sample" id="editarMetodoPagoForm" action="editar_metodo_pago.php" method="post">
                    <input type="hidden" name="id" value="<?php echo $metodoPago['id']; ?>">
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Método de Pago</label>
                          <div class="col-sm-9">
                            <input type="text" class="form-control" id="name" name="name" value="<?php echo $metodoPago['name']; ?>" required/>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-12">
                        <div class="form-group row">
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
        <?php include '../partials/footer.php'; ?>
      </div>
    </div>
  </div>
  <?php include '../partials/scripts.php'; ?>
</body>

</html>