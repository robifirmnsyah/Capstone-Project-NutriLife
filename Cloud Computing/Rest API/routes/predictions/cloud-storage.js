const axios = require('axios');
const FormData = require('form-data');
const { Storage } = require('@google-cloud/storage');

const storage = new Storage({
  projectId: 'ml-deployment-robi',
  keyFilename: './serviceAccountKey.json',
});

const bucketName = 'predict-gambar';

const predKarbo = async (req, res) => {
  try {
    const file = req.file;

    if (!file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const bucket = storage.bucket(bucketName);

    // Tentukan nama file di bucket
    const uniqueSuffix = Date.now() + '-' + Math.round(Math.random() * 1E9);
    const fileName = file.fieldname + '-' + uniqueSuffix + '.' + file.originalname.split('.').pop();

    const fileUpload = bucket.file(fileName);

    const stream = fileUpload.createWriteStream({
      resumable: false,
      metadata: {
        contentType: file.mimetype,
      },
    });

    // Salin file yang diunggah ke Google Cloud Storage
    await new Promise((resolve, reject) => {
      stream.on('error', reject);
      stream.on('finish', resolve);
      stream.end(file.buffer);
    });

    // Dapatkan URL akses publik untuk file yang diunggah
    const publicUrl = `https://storage.googleapis.com/${bucketName}/${fileName}`;

    const formData = new FormData();
    formData.append('fileUrl', publicUrl);

    const response = await axios.post('https://model-karbo-lmbe52aaka-et.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
};



const predSayur = async (req, res) => {
  try {
    const fileStream = fs.createReadStream(req.file.path);

    const formData = new FormData();
    formData.append('file', fileStream);

    const response = await axios.post('https://model-sayuran-lmbe52aaka-et.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
};


const predKacang = async (req, res) => {
  try {
    const fileStream = fs.createReadStream(req.file.path);

    const formData = new FormData();
    formData.append('file', fileStream);

    const response = await axios.post('https://model-kacang-lmbe52aaka-uc.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
};

const predDaging = async (req, res) => {
  try {
    const fileStream = fs.createReadStream(req.file.path);

    const formData = new FormData();
    formData.append('file', fileStream);

    const response = await axios.post('https://model-daging-lmbe52aaka-et.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
};

const predRempah = async (req, res) => {
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
};

const predBuah = async (req, res) => {
  try {
    const fileStream = fs.createReadStream(req.file.path);

    const formData = new FormData();
    formData.append('file', fileStream);

    const response = await axios.post('https://model-buah-lmbe52aaka-et.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
};

const predDaily = async (req, res) => {
  try {
    const fileStream = fs.createReadStream(req.file.path);

    const formData = new FormData();
    formData.append('file', fileStream);

    const response = await axios.post('https://model-daily-prod-lmbe52aaka-uc.a.run.app/', formData, {
      headers: formData.getHeaders()
    });

    console.log(response.data);

    res.json(response.data);
  } catch (error) {
    console.error(error);
    res.status(500).send('Terjadi kesalahan saat mengunggah file');
  }
};

module.exports = {
  predKarbo,
  predSayur,
  predKacang,
  predDaily,
  predDaging,
  predBuah,
  predRempah
};
