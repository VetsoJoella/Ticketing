<%@ page import="model.vol.ville.Ville"%>
<%@ page import="model.avion.Avion"%>
<%@ page import="model.avion.classe.Classe"%>


<% 
  // Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur") ; 
   Ville[] villes = (Ville[])Bloom.out(request, "villes") ;   
   Classe[] classes = (Classe[])Bloom.out(request, "classes") ; 
   Avion[] avions = (Avion[])Bloom.out(request, "avions") ;  
%>

<%@ include file="/views/template/header.jsp" %>
<%@ include file="/views/admin/template/navbar.jsp" %>

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
              <form method="post" action="${pageContext.request.contextPath}/admin/insertionVol">
                <div class="row mb-3">
                  <label for="decollage" class="col-sm-2 col-form-label">Date décollage</label>
                  <div class="col-sm-10">
                    <input type="datetime-local" class="form-control" name="vol.dateHeureDecollage" id="decollage">
                  </div>
                </div>
                <div class="row mb-3">
                  <label for="derniereReservation" class="col-sm-2 col-form-label">Dernière heure résérvation </label>
                  <div class="col-sm-10">
                    <input type="number" class="form-control" id="derniereReservation" name="vol.dernierReservation">
                  </div>
                </div>
                <div class="row mb-3">
                    <label for="derniereAnnulation" class="col-sm-2 col-form-label">Dernière heure annulation </label>
                    <div class="col-sm-10">
                      <input type="number" class="form-control" id="derniereAnnulation" name="vol.derniereAnnulation">
                    </div>
                </div>
                <div class="row mb-3">
                    <label for="avion" class="col-sm-2 col-form-label">Avion</label>
                    <div class="col-sm-10">
                      <select class="form-select" id="avion" name="vol.avion">
                      <% if(avions!=null && avions.length>0) {
                          for(Avion avion : avions) { %>
                            <option value="<%= avion.getId() %>"><%= avion.getNom() %></option>
                      <%  }
                        } %>
                        
                      </select>
                    </div>
                </div>

                <br><h6 class="card-title">Destination</h6>
                <div class="row mb-3">
                    <div class="col-6">
                        <label for="villeDepart" class="col-form-label">Ville départ</label>
                        <div class="col-sm-10">
                          <select class="form-select" id="villeDepart" name="vol.villeDepart">
                            <% if(villes!=null && villes.length>0) {
                                for(Ville ville : villes) { %>
                                  <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
                            <%  }
                              } %>
                          </select>
                        </div>
                    </div>
                    <div class="col-6">
                        <label for="villeArrive" class="col-form-label">Ville arrivée</label>
                        <div class="col-sm-10">
                          <select class="form-select" id="villeArrive" name="vol.villeDestination">
                            <% if(villes!=null && villes.length>0) {
                                for(Ville ville : villes) { %>
                                  <option value="<%= ville.getId() %>"><%= ville.getNom() %></option>
                            <%  }
                              } %>
                          </select>
                        </div>
                    </div>
                </div>
              
                <h6 class="card-title mb-2">Promotion & Prix</h6>
                <div id="prixPromotionSection"></div>
                <%-- <% if(classes!=null) {
                    for(Classe classe : classes) { %>
                      <div class="row mb-3">
                        <label class="col-sm-3 col-form-label text-center"> Classe <%= classe.getType() %></label>
                        <input type="hidden" name="classes[]" class="form-control" value="<%= classe.getId() %>" required>
                        
                        <div class="col-3 form-floating">
                            <input type="text" name="prix[]" class="form-control" id="prix" required>
                            <label for="prix">Prix</label>
                        </div>
                        <div class="col-3 form-floating">
                            <input type="text" name="nbs[]" class="form-control" id="nb" required>
                            <label for="nb">Nb promotion</label>
                        </div>
                        <div class="col-3 form-floating">
                            <input type="text" name="promotions[]" class="form-control" id="promotion" required>
                            <label for="promotion">Pourcentage</label>
                        </div>
                        
                      </div>
                    <% }
                  }
                %>  --%>

                <div class="text-center">
                  <button type="submit" class="btn btn-primary">Insérer</button>
                  <button type="reset" class="btn btn-secondary">Réinitialiser</button>
                </div>
              </form><!-- End Horizontal Form -->

            </div>
          </div>
        </div>
      </div>
    </section>


<%@ include file="/views/template/footer.html" %>
