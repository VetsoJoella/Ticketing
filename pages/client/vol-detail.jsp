
<%@ page import="model.vol.Vol"%>
<%@ page import="model.vol.prix.PrixVol" %>
<%@ page import="model.vol.prix.promotion.Promotion" %>

<% Vol vol = (Vol)Bloom.out(request, "vol") ;  
  if(vol==null) {
    vol = new Vol() ;
  }
       
%>

<%@ include file="/views/template/header.jsp" %>
<%@ include file="/views/client/template/navbar.jsp" %>


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
              <div class="row mb-3">
                <label for="inputEmail3" class="col-sm-2 col-form-label">Date décollage</label>
                <div class="col-sm-10">
                  <input type="hidden" class="form-control" name="vol.id" value="<%= vol.getId() %>">
                  <input type="datetime-local" class="form-control" id="inputEmail" name="vol.dateHeureDecollage" value="<%= vol.getDateHeureDecollage() %>" disabled>
                </div>
              </div>
              <div class="row mb-3">
                <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure réservation </label>
                <div class="col-sm-10">
                  <input type="datetime-local" class="form-control" id="inputPassword" name="vol.dernierReservation" value="<%= vol.getDateDernierReservation() %>" disabled>
                </div>
              </div>
              <div class="row mb-3">
                  <label for="inputPassword3" class="col-sm-2 col-form-label">Dernière heure annulation </label>
                  <div class="col-sm-10">
                    <input type="datetime-local" class="form-control" id="inputPassword" name="vol.derniereAnnulation" value="<%= vol.getDateDerniereAnnulation() %>" disabled>
                  </div>
              </div>
              <div class="row mb-3">
                  <label for="inputEmail3" class="col-sm-2 col-form-label">Avion</label>
                  <div class="col-sm-10">
                      <input type="hidden" name="vol.avion" value="<%= vol.getAvion().getId() %>" disabled>
                      <input type="text" class="form-control" id="inputPassword" value="<%= vol.getAvion().getNom() %>" disabled>
                  </div>
              </div>

              <br><h6 class="card-title">Destination</h6>
              <div class="row mb-3">
                  <div class="col-6">
                      <label for="inputEmail3" class="col-sm-2 col-form-label">Ville départ</label>
                      <div class="col-sm-10">
                          <input type="hidden" name="vol.villeDepart" value="<%= vol.getVilleDepart().getId() %>" disabled>
                          <input type="text" class="form-control" id="inputPassword"  value="<%= vol.getVilleDepart().getNom() %>" disabled>
                      </div>
                  </div>
                  <div class="col-6">
                      <label for="inputEmail3" class="col-sm-2 col-form-label">Ville arrivée</label>
                      <div class="col-sm-10">
                          <input type="hidden" name="vol.villeDestination" value="<%= vol.getVilleDestination().getId() %>" disabled>
                          <input type="text" class="form-control" id="inputPassword" value="<%= vol.getVilleDestination().getNom() %>" disabled> 
                      </div>
                  </div>
              </div>
            
              <h6 class="card-title mb-2">Prix du voyage</h6>
              <% for(int i = 0; i<vol.getPrixVols().length ; i++ ) { 
                  PrixVol prixVol = vol.getPrixVols()[i] ; 
                  Promotion promotion = vol.getPromotion(prixVol) ; 
                  
                %>
                <div class="row mb-3"> 
                  <label class="col-sm-3 col-form-label text-center">Classe <%= prixVol.getClasseAvion().getClasse().getType() %></label>
                  <input type="hidden" name="classeAvions[]" value="<%= prixVol.getClasseAvion().getId() %>" class="form-control" disabled>

                  <div class="col-3 form-floating">
                      <input type="text" name="prix[]" class="form-control" value="<%= prixVol.getPrix() %>" disabled>
                      <label>Prix</label>
                  </div>
                  <div class="col-3 form-floating">
                      <input type="text" name="nbs[]" class="form-control" value = "<%= promotion.getNb() %>" disabled>
                      <label>Nb promotion</label>
                  </div>
                  <div class="col-3 form-floating">
                      <input type="text" name="promotions[]" class="form-control" value = "<%= promotion.getPourcentage() %>" disabled>
                      <label>Pourcentage</label>
                  </div> 
                </div>
              <% } %>
              <div class="text-center">
                <button type="submit" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#basicModal">Reserver</button>
              </div>
            

          </div>
        </div>



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
                    <form method="post" action="${pageContext.request.contextPath}/client/reservation">
                        <input type="hidden" name="vol.id" value="<%= vol.getId() %>">
                        <% for(int i = 0; i<vol.getPrixVols().length ; i++ ) { 
                            PrixVol prixVol = vol.getPrixVols()[i] ; 
                        
                        %>
                        <div class="row mb-3">
                            <label for="inputPassword3" class="col-sm-4 col-form-label text-center"> Classe <%= prixVol.getClasseAvion().getClasse().getType() %></label>
                            <input type="hidden" name="classeAvions[]" value="<%= prixVol.getClasseAvion().getId() %>">

                            <div class="col-4 form-floating">
                                <input type="number" name="nbs[]" class="form-control" >
                                <label for="nb">Nb place</label>
                            </div>
                        </div>
                        <% } %>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Réserver</button>
                        </div>
                    </form>
                </div>
                
            </div>
        </div>
    </div><!-- End Basic Modal-->
  </section>
<%@ include file="/views/template/footer.html" %>

