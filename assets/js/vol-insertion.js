document.addEventListener("DOMContentLoaded", function () {
    
    const avionSelect = document.getElementById("avion");
    const prixPromotionSection = document.getElementById("prixPromotionSection");

    function chargerClassesAvion(avionId) {
        if (!avionId) return;

        // Appel à l'API REST pour récupérer les classes de l'avion sélectionné
        fetch(`/Ticketing/api/classeAvion?idAvion=${avionId}`)
            .then(response => response.json())
            .then(classeAvions => {
                prixPromotionSection.innerHTML = ""; // Nettoyer la section

                if (classeAvions.length === 0) {
                    prixPromotionSection.innerHTML = "<p>Aucune classe disponible pour cet avion.</p>";
                    return;
                }

                // Générer dynamiquement le formulaire pour chaque classe récupérée
                classeAvions.forEach(classe => {
                    prixPromotionSection.appendChild(creerSectionPixPromotion(classe));
                });
            })
            .catch(error => console.error("Erreur lors de la récupération des classes:", error));
    }

    avionSelect.addEventListener("change", function () {
        chargerClassesAvion(this.value);
    });

    // Déclencher l'événement `change` pour charger immédiatement les classes de l'avion sélectionné par défaut
    chargerClassesAvion(avionSelect.value);
});

function creerSectionPixPromotion(classeAvion) {
    const row = document.createElement("div");
    row.classList.add("row", "mb-3");

    row.innerHTML = `
        <label class="col-sm-3 col-form-label text-center">Classe ${classeAvion.classe.type}</label>
        <input type="hidden" name="classeAvions[]" value="${classeAvion.id}" class="form-control" required>

        <div class="col-3 form-floating">
            <input type="text" name="prix[]" class="form-control" required>
            <label>Prix</label>
        </div>
        <div class="col-3 form-floating">
            <input type="text" name="nbs[]" class="form-control" required>
            <label>Nb promotion</label>
        </div>
        <div class="col-3 form-floating">
            <input type="text" name="promotions[]" class="form-control" required>
            <label>Pourcentage</label>
        </div> 
    `;

    return row;
}
