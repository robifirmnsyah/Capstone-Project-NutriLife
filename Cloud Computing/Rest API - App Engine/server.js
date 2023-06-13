const express = require('express');
const routes = require('./routes/predictions/route');
const authRoutes = require('./routes/authRoutes/authRoutes');
const cors=require("cors");
const app = express();


app.use(cors());
app.use(express.json());

app.get('/', (req, res) => {
    res.send('BERHASIL, Silahkan tambahkan path untuk melakukan request!')
  })
app.use(express.json());
app.use(express.urlencoded({ extended: true }));

app.use('/auth', authRoutes);
app.use('/pred', routes);

app.listen(8080, () => {
  console.log('Server started on port 8080');
});