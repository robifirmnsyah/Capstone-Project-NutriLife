const admin = require('firebase-admin');

// Inisialisasi aplikasi admin Firebase
const serviceAccount = require('./serviceAccountKey.json');
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount)
});

// Menginisialisasi koneksi dengan Firestore
const db = admin.firestore();

// Fungsi untuk mendaftarkan pengguna
const register = async (req, res) => {
  try {
    const { name, email, password } = req.body;

    // Buat pengguna dengan Firebase Auth
    const userRecord = await admin.auth().createUser({
      name: name,
      email: email,
      password: password
    });

    // Simpan data pengguna ke Firestore
    const userData = {
      name: name,
      email: email,
      userId: userRecord.uid
    };
    await db.collection('users').doc(userRecord.uid).set(userData);

    res.status(200).json({ error: false, message: 'Pengguna berhasil terdaftar', userId: userRecord.uid });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Fungsi untuk login pengguna
const login = async (req, res) => {
  try {
    const { email, password } = req.body;

    // Lakukan autentikasi dengan Firebase Auth
    const userRecord = await admin.auth().getUserByEmail(email);
    const token = await admin.auth().createCustomToken(userRecord.uid);

    // Ambil data pengguna dari Firestore
    const doc = await db.collection('users').doc(userRecord.uid).get();
    const userData = doc.data();

    res.status(200).json({
      error: false,
      message: 'success',
      loginResult: {
        userId: userRecord.uid,
        name: userData.name,
        token: token
      }
    });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};


// Fungsi untuk menghapus pengguna
const deleteUser = async (req, res) => {
  try {
    const { uid } = req.params;

    // Hapus pengguna dari Firebase Auth
    await admin.auth().deleteUser(uid);

    // Hapus data pengguna dari Firestore
    await db.collection('users').doc(uid).delete();

    res.status(200).json({ message: 'Pengguna berhasil dihapus' });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Fungsi untuk mengupdate pengguna
const updateUser = async (req, res) => {
  try {
    const { uid } = req.params;
    const { name, email } = req.body;

    // Proses pembaruan pengguna berdasarkan uid di Firebase Auth
    await admin.auth().updateUser(uid, { displayName: name, email });

    // Proses pembaruan pengguna berdasarkan uid di Firestore
    const userRef = db.collection('users').doc(uid);
    await userRef.update({ name, email });

    res.status(200).json({ message: 'Pengguna berhasil diperbarui' });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};



// Fungsi untuk mendapatkan data pengguna berdasarkan userId
const user = async (req, res) => {
  try {
    const { uid } = req.params;

    // Proses mendapatkan data pengguna berdasarkan uid
    const userDoc = await db.collection('users').doc(uid).get();
    if (!userDoc.exists) {
      throw new Error('Data pengguna tidak ditemukan');
    }

    const userData = userDoc.data();
    res.status(200).json({ user: userData });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};


const logout = async (req, res) => {
  try {
    // Lakukan proses logout di sini
    // Misalnya, hapus token yang digunakan oleh pengguna atau sesi yang aktif

    res.status(200).json({ error: false, message: 'Pengguna berhasil logout' });
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

module.exports = {
  register,
  login,
  deleteUser,
  updateUser,
  user,
  logout
}