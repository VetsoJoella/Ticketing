<%@ include file="/views/template/header.jsp" %>

<body>

  <main>
    <div class="container">

      <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
        <div class="container">
          <div class="row justify-content-center">
            <div class="col-lg-4 col-md-6 d-flex flex-column align-items-center justify-content-center">

              <div class="d-flex justify-content-center py-4">
                <a href="#" class="logo d-flex align-items-center w-auto">
                  <img src="" alt="">
                  <span class="d-none d-lg-block">Aboard</span>
                </a>
              </div><!-- End Logo -->

              <div class="card mb-3">

                <div class="card-body">

                  <div class="pt-4 pb-2">
                    <h5 class="card-title text-center pb-0 fs-4">Connection client</h5>
                  </div>

                  <form class="row g-3 needs-validation" method="post" action="${pageContext.request.contextPath}/client"  novalidate>

                    <div class="col-12">
                      <label for="yourUsername" class="form-label">Numero téléphone</label>
                      <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend"><img src="http://localhost/CDN-NiceAdmin/img/icon/flag.png" alt="" width="25" height="25"> </span>
                        <input type="text" name="passager.numero" class="form-control" id="yourUsername" required> 
                        <div class="invalid-feedback">Insérer votre nom!</div>
                      </div>
                    </div>

                    <div class="col-12">
                      <label for="yourPassword" class="form-label">Nom</label>
                      <input type="text" name="passager.nom" class="form-control" id="yourPassword" required>
                      <div class="invalid-feedback">Insérer votre nom!</div>
                    </div>

                    <div class="col-12">
                      <button class="btn btn-primary w-100" type="submit">Login</button>
                    </div>
                  </form>

                </div>
              </div>

            </div>
          </div>
        </div>

      </section>

    </div>

<%@ include file="/views/template/footer.html" %>
