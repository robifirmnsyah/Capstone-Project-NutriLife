const express = require('express');
const path = require('path');

const app = express();
const cors=require("cors");

app.use(cors());
app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', '*');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST, PUT, DELETE' );
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type, Authorization');
  next();
})

// Mengatur folder untuk menyajikan file statis
app.use(express.static(path.join(__dirname, 'public')));

// Menggunakan route untuk file index.html
app.get('/', (req, res) => {
  res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// Mendengarkan pada port yang ditentukan (misalnya, port 3000)
app.listen(3000, () => {
  console.log('Server berjalan pada port 3000');
});
