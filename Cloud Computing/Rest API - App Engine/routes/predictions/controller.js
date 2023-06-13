const axios = require('axios');
const FormData = require('form-data');

const predKarbo = async (req, res) => {
  try {
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

    const response = await axios.post('https://model-karbo-lmbe52aaka-et.a.run.app/', formData, {
      headers: {
        ...formData.getHeaders(),
      },
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
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

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
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

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
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

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
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

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
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

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
    if (!req.file) {
      res.status(400).json({ error: 'No file uploaded' });
      return;
    }

    const formData = new FormData();
    formData.append('file', req.file.buffer, { filename: req.file.originalname });

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
