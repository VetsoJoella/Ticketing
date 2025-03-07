
<%@ include file="/views/template/header.html" %>
<%@ include file="/views/client/template/navbar.html" %>


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
              <div>
                <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">Date décollage</label>
                  <div class="col-sm-10">
                    <input type="datetime-local" class="form-control" name="dateDecollage" id="inputEmail" placeholder="2025-03-20 10:00" disabled>
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure réservation </label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="inputPassword" name="derniereReservation" value="2" disabled>
                  </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure annulation </label>
                    <div class="col-sm-10">
                      <input type="number" class="form-control" id="inputPassword" name="derniereAnnulation" value="1" disabled>
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
                        <input type="text" name="nb" class="form-control" id="nb" value="10"  disabled required>
                        <label for="nb">Nb promotion</label>
                    </div>
                    <div class="col-4 form-floating">
                        <input type="text" name="promotion" class="form-control" id="promotion" value="5" disabled required>
                        <label for="promotion">Pourcentage</label>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Economique</label>
                    <div class="col-4 form-floating">
                        <input type="text" name="nb" class="form-control" id="nb" value="05" disabled required>
                        <label for="nb">Nb promotion</label>
                    </div>
                    <div class="col-4 form-floating">
                        <input type="text" name="promotion" class="form-control" id="promotion" value="1" disabled required>
                        <label for="promotion">Pourcentage</label>
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Affaire</label>
                    <div class="col-4 form-floating">
                        <input type="text" name="nb" class="form-control" id="nb" value="2" disabled required>
                        <label for="nb">Nb promotion</label>
                    </div>
                    <div class="col-4 form-floating">
                        <input type="text" name="promotion" class="form-control" id="promotion" value="2" disabled required>
                        <label for="promotion">Pourcentage</label>
                    </div>
                </div>
                <div class="text-center">
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal">
                        Réserver
                    </button>
                </div>
            </div>
                
            <div class="modal fade" id="basicModal" tabindex="-1">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Réservation</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <h6 class="card-title mb-2">Prix du voyage</h6>
                            <form method="post" action="reservation-liste.html">

                                <div class="row mb-3">
                                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Premium</label>
                                    <div class="col-4 form-floating">
                                        <input type="text" name="nb" class="form-control" id="nb" required>
                                        <label for="nb">Nb promotion</label>
                                    </div>
                                    <div class="col-4 form-floating">
                                        <input type="text" name="promotion" class="form-control" id="promotion" required>
                                        <label for="promotion">Pourcentage</label>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Economique</label>
                                    <div class="col-4 form-floating">
                                        <input type="text" name="nb" class="form-control" id="nb" required>
                                        <label for="nb">Nb promotion</label>
                                    </div>
                                    <div class="col-4 form-floating">
                                        <input type="text" name="promotion" class="form-control" id="promotion" required>
                                        <label for="promotion">Pourcentage</label>
                                    </div>
                                </div>
                                <div class="row mb-3">
                                    <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe Affaire</label>
                                    <div class="col-4 form-floating">
                                        <input type="text" name="nb" class="form-control" id="nb" required>
                                        <label for="nb">Nb promotion</label>
                                    </div>
                                    <div class="col-4 form-floating">
                                        <input type="text" name="promotion" class="form-control" id="promotion" required>
                                        <label for="promotion">Pourcentage</label>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Réserver</button>
                                </div>
                            </form>
                        </div>
                        
                </div>
                </div>
            </div><!-- End Basic Modal-->

            </div>
          </div>
        </div>
      </div>
    </section>

<%@ include file="/views/template/footer.html" %>
