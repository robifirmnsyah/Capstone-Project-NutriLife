const express = require('express');
const routes = require('./routes/predictions/route');
const authRoutes = require('./routes/authRoutes/authRoutes');

const app = express();
app.use(express.json());

app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use((req, res, next) => {
  res.setHeader('Access-Control-Allow-Origin', 'http://127.0.0.1:5500');
  res.setHeader('Access-Control-Allow-Methods', 'GET, POST');
  res.setHeader('Access-Control-Allow-Headers', 'Content-Type');
  next();
});


app.get('/', (req, res) => {
  res.send('BERHASIL, Silahkan tambahkan path untuk melakukan request!')
})
app.use('/auth', authRoutes);
app.use('/pred', routes);


// Serve file HTML statis
app.use(express.static('public'));

app.listen(3000, () => {
  console.log('Server started on port 3000');
});