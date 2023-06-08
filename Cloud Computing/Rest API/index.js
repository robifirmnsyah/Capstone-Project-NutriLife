const express = require('express');
const axios = require('axios');
const multer = require('multer');
const FormData = require('form-data');
const fs = require('fs');

const app = express();
app.use(express.json());

// Konfigurasi multer untuk mengelola file yang diunggah
const upload = multer({ dest: 'uploads/' });

app.post('/', upload.single('file'), async (req, res) => {
  try {
    const fileStream = fs.createReadStream(req.file.path);

    const formData = new FormData();
    formData.append('file', fileStream);

    const response = await axios.post('https://model-rempah-lmbe52aaka-et.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
});

app.listen(3000, () => {
  console.log('Server started on port 3000');
});
