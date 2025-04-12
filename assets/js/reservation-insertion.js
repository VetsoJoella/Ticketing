const btnAjouter = document.getElementById("btnAjouter");
const tableBody = document.querySelector("#tableReservation tbody");
const form = document.getElementById("reservationForm");

let index = 0;

btnAjouter.addEventListener("click", function () {
    const classeSelect = document.getElementById("selectClasse");
    const categorieSelect = document.getElementById("selectCategorie");
    const nbPlacesInput = document.getElementById("nbPlaces");

    const classeId = classeSelect.value;
    const classeText = classeSelect.options[classeSelect.selectedIndex].text;

    const categorieId = categorieSelect.value;
    const categorieText = categorieSelect.options[categorieSelect.selectedIndex].text;

    const nbPlaces = nbPlacesInput.value;

    if (!nbPlaces || nbPlaces <= 0) {
        alert("Veuillez entrer un nombre de places valide.");
        return;
    }

    if (!categorieId) {
        alert("Veuillez entrer une catégorie.");
        return;
    }

    // Ajouter une ligne au tableau
    const row = document.createElement("tr");

    row.innerHTML = `
        <td>${classeText}</td>
        <td>${categorieText}</td>
        <td>${nbPlaces}</td>

        <input type="hidden" name="classeAvions[]" value="${classeId}">
        <input type="hidden" name="categories[]" value="${categorieId}">
        <input type="hidden" name="nbs[]" value="${nbPlaces}">
    `;

    tableBody.appendChild(row);
    index++;

    // Reset input
    nbPlacesInput.value = "";
});
