const uploadForm = document.getElementById('uploadForm');
const responseDiv = document.getElementById('response');
const giziSerupaButton = document.getElementById('giziSerupaButton');
const resepButton = document.getElementById('resepButton');
const recipesDiv = document.getElementById('recipes');
const backHome = document.getElementById('backHome');
const apiSelector = document.getElementById('apiSelector');
const submitButton = document.getElementById('submitButton');
const loading = document.getElementById('loading');
const fileInput = document.getElementById('file');
const uploadedImage = document.getElementById('uploadedImage');

document.addEventListener('DOMContentLoaded', () => {

  fileInput.addEventListener('change', () => {
    const file = fileInput.files[0];

    if (file) {
      const reader = new FileReader();

      reader.addEventListener('load', (event) => {
        uploadedImage.src = event.target.result;
        uploadedImage.style.display = 'block'; 
      });

      reader.readAsDataURL(file);
    }
  });
});

uploadForm.addEventListener('submit', async (e) => {
  e.preventDefault();

  const fileInput = document.getElementById('file');
  const file = fileInput.files[0];
  const formData = new FormData();
  formData.append('file', file);

  try {
    // Mendapatkan elemen tanda loading
    const loading = document.getElementById('loading');

    // Menampilkan tanda loading
    loading.style.display = 'block';

    const api = 'https://capstone-project-386007.et.r.appspot.com/pred/'
    const selectedPath = apiSelector.value;
    const response = await fetch(api+selectedPath, {
      method: 'POST',
      body: formData
    });

    const data = await response.json();
    showResponse(data);
    console.log(data);
  } catch (error) {
    console.error(error);
    showResponse({ error: 'An error occurred' });
  }
});


fileInput.addEventListener('change', () => {
  const file = fileInput.files[0];

  if (file) {
    const reader = new FileReader();

    reader.addEventListener('load', (event) => {
      uploadedImage.src = event.target.result;
    });

    reader.readAsDataURL(file);
  }
});


function showResponse(data) {

  loading.style.display = 'none';
  
  const nutrientInfo = data.nutrient_info;
  let nutrientList = '';

  nutrientInfo.forEach((nutrient, index) => {
    const nutrientValues = Object.entries(nutrient).map(([key, value]) => `${key}: ${value}`);
    const nutrientItem = nutrientValues.map((item) => `<li>${item}</li>`).join('');
    nutrientList += `<ul>${nutrientItem}</ul></br>`;
  });

  let responseHtml = '';

  if (data.error) {
    responseHtml = `<p>Error: ${data.error}</p>`;
  } else {
    responseHtml = `<div class="card mb-3">
                      <h5 class="card-header">Prediction : ${data.prediction}</h5>
                      <div class="card-body">
                        <h5>Info Nutrisi :</h5>
                        <p>${nutrientList}</p>
                      </div>
                    </div>`;
    // Lakukan pengolahan data tambahan sesuai kebutuhan Anda di sini
  }

  responseDiv.innerHTML = responseHtml;

  giziSerupaButton.addEventListener('click', () => {
    toggleGiziSerupa();
  });
  
  function toggleGiziSerupa() {
    const resultDiv = document.getElementById('result');
    
    // Jika resultDiv tidak memiliki kelas 'show', maka tampilkan data
    if (!resultDiv.classList.contains('show')) {
      showGiziSerupa(resultDiv);
    } else {
      hideGiziSerupa(resultDiv);
    }
  }
  
  function showGiziSerupa(resultDiv) {
    resultDiv.innerHTML = `<h5>Rekomendasi Gizi Serupa :</h5>`;
    
    const dataGiziSerupa = data.recommended_foods_and_info;
    
    if (dataGiziSerupa.length > 0) {
      const ul = document.createElement('ul');
    
      dataGiziSerupa.forEach((food, index) => {
        const li = document.createElement('p');
        li.textContent = `${index + 1}. ${food.food}:`;
        
        const nutrientUl = document.createElement('ul');
        const nutrientInfo = food.nutrient_info;
    
        for (const [key, value] of Object.entries(nutrientInfo)) {
          const nutrientLi = document.createElement('li');
          nutrientLi.textContent = `${key}: ${value}`;
          nutrientUl.appendChild(nutrientLi);
        }
    
        li.appendChild(nutrientUl);
        ul.appendChild(li);
      });
    
      resultDiv.appendChild(ul);
    } else {
      resultDiv.textContent = 'Tidak ada data makanan dengan gizi serupa.';
    }
    
    // Tambahkan kelas 'show' ke resultDiv
    resultDiv.classList.add('show');
  }
  
  function hideGiziSerupa(resultDiv) {
    // Hapus semua konten dalam resultDiv
    resultDiv.innerHTML = '';
    
    // Hapus kelas 'show' dari resultDiv
    resultDiv.classList.remove('show');
  }

  // Menambahkan event listener pada tombol "Rekomendasi Resep Makanan"
  localStorage.setItem('recommendedRecipes', JSON.stringify(data.recommended_recipes));
  resepButton.addEventListener('click', () => {
    // Mengarahkan ke halaman resep.html
    window.location.href = 'resep.html';
  });
}




