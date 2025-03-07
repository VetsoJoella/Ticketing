<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="view.*"%>
<% String message = Bloom.out(request, "message") ; 

  if(message!=null){ %>
    <script> alert("<%= message %>") </script>
  <% } 
  
%>
<%@ include file="/views/template/header.html" %>
<%@ include file="/views/admin/template/navbar.html" %>

    <div class="pagetitle">
      <h1>Vol</h1>
      <nav>
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="#">Accueil</a></li>
          <li class="breadcrumb-item">Vol</li>
          <li class="breadcrumb-item active">Insertion</li>
        </ol>
      </nav>
    </div><!-- End Page Title -->
    <section class="section" >
      <div class="row">
        <div class="col-lg-12">

          <div class="card">
            <div class="card-body">
              <h5 class="card-title">Insertion Vol</h5>

              <!-- Horizontal Form -->
              <form>
                <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">Date décollage</label>
                  <div class="col-sm-10">
                    <input type="datetime-local" class="form-control" name="dateDecollage" id="inputEmail">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure résérvation </label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="inputPassword" name="derniereReservation">
                  </div>
                </div>
                <div class="row mb-3">
                    <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure annulation </label>
                    <div class="col-sm-10">
                      <input type="number" class="form-control" id="inputPassword" name="derniereAnnulation">
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="inputEmail3" class="col-sm-2 col-form-label">Avion</label>
                    <div class="col-sm-10">
                      <select class="form-select" id="inputText" name="vol">
                          <option value="">Avion 1</option>
                          <option value="">Avion 2</option>
                          <option value="">Avion 3</option>
                      </select>
                    </div>
                </div>

                <br><h6 class="card-title">Destination</h6>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="inputEmail3" class="col-sm-2 col-form-label">Ville départ</label>
                        <div class="col-sm-10">
                          <select class="form-select" id="inputText" name="villeDepart">
                              <option value="">Ville 1</option>
                              <option value="">Ville 2</option>
                              <option value="">Ville 3</option>
                          </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <label for="inputEmail3" class="col-sm-2 col-form-label">Ville arrivée</label>
                        <div class="col-sm-10">
                          <select class="form-select" id="inputText" name="villeArrive">
                              <option value="">Ville 1</option>
                              <option value="">Ville 2</option>
                              <option value="">Ville 3</option>
                          </select>
                        </div>
                    </div>
                </div>
              
                <h6 class="card-title mb-2">Prix du voyage</h6>
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

                <div class="text-center">
                  <button type="submit" class="btn btn-primary">Modifier</button>
                  <button type="reset" class="btn btn-secondary">Réinitialiser</button>
                </div>
              </form><!-- End Horizontal Form -->

            </div>
          </div>
        </div>
      </div>
    </section>

<%@ include file="/views/template/footer.html" %>
