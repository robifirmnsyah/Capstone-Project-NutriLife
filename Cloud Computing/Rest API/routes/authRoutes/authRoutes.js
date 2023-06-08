const express = require('express');
const router = express.Router();
const authController = require('./controllers');

// Endpoint register
router.post('/register', authController.register);

// Endpoint login
router.post('/login', authController.login);

// Endpoint delete
router.delete('/delete/:uid', authController.deleteUser);

// Endpoint update
router.put('/update/:uid', authController.updateUser);

// Endpoint /logout
router.get('/user/:uid', authController.user);

// Endpoint /logout
router.post('/logout', authController.logout);

module.exports = router;
