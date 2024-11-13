// script.js

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('surveyForm');
    const steeringSheet = document.getElementById('steeringSheet').getElementsByTagName('tbody')[0];

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const depth = document.getElementById('depth').value;
        const azimuth = document.getElementById('azimuth').value;
        const inclination = document.getElementById('inclination').value;
        const toolFace = document.getElementById('toolFace').value;
        const doglegSeverity = document.getElementById('doglegSeverity').value;

        if (depth && azimuth && inclination && toolFace && doglegSeverity) {
            // Create a new row
            const newRow = steeringSheet.insertRow();

            // Create and append cells for the row
            newRow.insertCell(0).textContent = depth;
            newRow.insertCell(1).textContent = azimuth;
            newRow.insertCell(2).textContent = inclination;
            newRow.insertCell(3).textContent = toolFace;
            newRow.insertCell(4).textContent = doglegSeverity;

            // Add delete button
            const deleteCell = newRow.insertCell(5);
            const deleteButton = document.createElement('button');
            deleteButton.textContent = 'Delete';
            deleteButton.classList.add('delete');
            deleteButton.onclick = function () {
                steeringSheet.deleteRow(newRow.rowIndex - 1);
            };
            deleteCell.appendChild(deleteButton);

            // Clear form fields after submission
            form.reset();
        } else {
            alert('Please fill in all fields.');
        }
    });
});
