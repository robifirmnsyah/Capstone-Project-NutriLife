function search() {
  const searchInput = document.getElementById('searchInput').value;
  const searchResults = document.getElementById('searchResults');
  searchResults.innerHTML = '';

  if (searchInput.trim() === '') {
    searchResults.classList.add('hidden');
    return;
  }

  fetch('calorie_infos final.csv')
    .then(response => response.text())
    .then(csvData => {
      const rows = csvData.split('\n');
      const results = [];
      const calories = {}; // Objek untuk menyimpan data kalori
      rows.forEach(row => {
        const columns = row.split(',');
        const bahan = columns[0];
        const kalori = columns[1]; // Menambahkan data kalori dari kolom kedua
        if (bahan.toLowerCase().includes(searchInput.toLowerCase())) {
          results.push(bahan);
          calories[bahan] = kalori; // Menyimpan data kalori berdasarkan bahan
        }
      });
      displayResults(results, calories); // Menambahkan argumen 'calories' pada pemanggilan fungsi displayResults
    })
    .catch(error => console.error(error));
}

function displayResults(results, calories) {
  const searchResults = document.getElementById('searchResults');
  searchResults.innerHTML = '';

  const formInput = document.getElementById('formInput');
  const formCalories = document.getElementById('formCalories'); // Element untuk menampilkan data kalori

  results.forEach(result => {
    const option = document.createElement('option');
    option.text = result;
    option.value = result;
    searchResults.appendChild(option);
  });

  searchResults.addEventListener('change', function() {
    const selectedOption = searchResults.options[searchResults.selectedIndex];
    const selectedValue = selectedOption.value;
    formInput.value = selectedValue;
    formCalories.value = calories[selectedValue] || ''; // Menampilkan data kalori berdasarkan bahan yang dipilih
  });

  searchResults.classList.remove('hidden');
}

function submitForm() {
  const formInput = document.getElementById('formInput').value;
  const formCalories = document.getElementById('formCalories').value;
  const formWeight = document.getElementById('formWeight').value;
  const calculatedCalories = parseFloat(formCalories) * parseFloat(formWeight) / 100;

  // Create new table row
  const tableBody = document.getElementById('dataTableBody');
  const row = document.createElement('tr');

  // Create and append table data cells
  const bahanCell = document.createElement('td');
  bahanCell.textContent = formInput;
  row.appendChild(bahanCell);

  const beratCell = document.createElement('td');
  beratCell.textContent = formWeight + ' gr';
  row.appendChild(beratCell);

  const kaloriCell = document.createElement('td');
  kaloriCell.textContent = calculatedCalories.toFixed(2) + ' kkal';
  row.appendChild(kaloriCell);

  // Append the row to the table body
  tableBody.appendChild(row);

  // Update total kalori
  updateTotalCalories();
}

function updateTotalCalories() {
  const tableBody = document.getElementById('dataTableBody');
  const rows = tableBody.getElementsByTagName('tr');
  let totalCalories = 0;

  for (let i = 0; i < rows.length; i++) {
    const kaloriCell = rows[i].getElementsByTagName('td')[2];
    const kaloriText = kaloriCell.textContent.replace(' kkal', '');
    totalCalories += parseFloat(kaloriText);
  }

  const totalCaloriesCell = document.getElementById('totalCalories');
  totalCaloriesCell.textContent = totalCalories.toFixed(2) + ' kkal';
}

const searchInput = document.getElementById('searchInput');
searchInput.addEventListener('input', () => {
  clearTimeout(searchInput.timer);
  searchInput.timer = setTimeout(search, 300);
});