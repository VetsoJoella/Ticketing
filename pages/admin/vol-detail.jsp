
<%@ include file="/views/template/header.html" %>
<%@ include file="/views/admin/template/navbar.html" %>


    <div class="pagetitle">
      <h1>Vol</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="#">Accueil</a></li>
          <li class="breadcrumb-item">Vol</li>
          <li class="breadcrumb-item active">Detail</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
    <section class="section" >
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Détail Vol</h5>

              <!-- Horizontal Form -->
              <form>
                <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">Date décollage</label>
                  <div class="col-sm-10">
                    <input type="datetime-local" class="form-control" name="dateDecollage" id="inputEmail" placeholder="2025-03-20 10:00" disabled>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure réservation </label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="inputPassword" name="derniereReservation" value="2">
                  </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure annulation </label>
                    <div class="col-sm-10">
                      <input type="number" class="form-control" id="inputPassword" name="derniereAnnulation" value="1">
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Avion</label>
                    <div class="col-sm-10">
                        <input type="number" class="form-control" id="inputPassword" name="avion" value="Air Madagascar 737" disabled>
                    </div>
                </div>

                <br><h6 class="card-title">Destination</h6>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="inputEmail3" class="col-sm-2 col-form-label">Ville départ</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputPassword" name="depart" value="Antananarivo" disabled>
                        </div>
                    </div>
                    <div class="col-6">
                        <label for="inputEmail3" class="col-sm-2 col-form-label">Ville arrivée</label>
                        <div class="col-sm-10">
                          <input type="text" class="form-control" id="inputPassword" name="destination" value="Paris" disabled> 
                        </div>
                    </div>
                </div>
              
                <h6 class="card-title mb-2">Prix du voyage</h6>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Premium</label>
                    <div class="col-4 form-floating">
                        <input type="text" name="nb" class="form-control" id="nb" value="10" required>
                        <label for="nb">Nb promotion</label>
                    </div>
                    <div class="col-4 form-floating">
                        <input type="text" name="promotion" class="form-control" id="promotion" value="5" required>
                        <label for="promotion">Pourcentage</label>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Economique</label>
                    <div class="col-4 form-floating">
                        <input type="text" name="nb" class="form-control" id="nb" value="05" required>
                        <label for="nb">Nb promotion</label>
                    </div>
                    <div class="col-4 form-floating">
                        <input type="text" name="promotion" class="form-control" id="promotion" value="1" required>
                        <label for="promotion">Pourcentage</label>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Affaire</label>
                    <div class="col-4 form-floating">
                        <input type="text" name="nb" class="form-control" id="nb" value="2" required>
                        <label for="nb">Nb promotion</label>
                    </div>
                    <div class="col-4 form-floating">
                        <input type="text" name="promotion" class="form-control" id="promotion" value="2" required>
                        <label for="promotion">Pourcentage</label>
                    </div>
                </div>

                <div class="text-center">
                  <button type="submit" class="btn btn-primary">Modifier</button>
                </div>
              </form><!-- End Horizontal Form -->

            </div>
          </div>
        </div>
      </div>
    </section>
<%@ include file="/views/template/footer.html" %>

