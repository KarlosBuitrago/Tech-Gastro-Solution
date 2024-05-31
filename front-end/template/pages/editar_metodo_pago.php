<?php
// Autenticación para obtener el token
session_start();

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id'];
    $name = $_POST['name'];

    $data = array(
        "id" => $id,
        "name" => $name
    );

    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/metodos_pago/" . $id);
    curl_setopt($curl, CURLOPT_CUSTOMREQUEST, "PUT");
    curl_setopt($curl, CURLOPT_POSTFIELDS, json_encode($data));
    curl_setopt($curl, CURLOPT_HTTPHEADER, array(
        "Content-Type: application/json"
    ));
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        header('Location: ../registrar_metodo_pago.php');
        exit;
    } else {
        echo "Error al actualizar el método de pago: " . $response;
    }
} else {
    $id = $_GET['id'];

    $curl = curl_init();
    curl_setopt($curl, CURLOPT_URL, "http://localhost:9000/gastro-tech/api/v1/metodos_pago/" . $id);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, true);

    $response = curl_exec($curl);
    $httpCode = curl_getinfo($curl, CURLINFO_HTTP_CODE);
    curl_close($curl);

    if ($httpCode == 200) {
        $metodoPago = json_decode($response, true);
    } else {
        echo "Error al obtener los datos del método de pago";
        exit();
    }
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
        <footer class="footer">
          <div class="d-sm-flex justify-content-center justify-content-sm-between">
            <span class="text-center text-sm-left d-block d-sm-inline-block">Copyright © <a href="https://www.bootstrapdash.com/" target="_blank">bootstrapdash.com</a> 2020</span>
            <span class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Free <a href="https://www.bootstrapdash.com/" target="_blank">Bootstrap dashboard </a>templates from Bootstrapdash.com</span>
          </div>
        </footer>
      </div>
    </div>
  </div>
  <?php include '../partials/scripts.php'; ?>
</body>

</html>