import { initializeApp } from 'firebase/app'
import 'firebase/auth'

const firebaseConfig = {
  apiKey: "AIzaSyD3d9bOIozhYeC4ylymcjhSDZvBejhDSc4",
  authDomain: "mobileotp-f0b5b.firebaseapp.com",
  projectId: "mobileotp-f0b5b",
  storageBucket: "mobileotp-f0b5b.appspot.com",
  messagingSenderId: "82097021321",
  appId: "1:82097021321:web:26370fe9a567f0ae924158"
};
const firebase=initializeApp(firebaseConfig);
  
export default firebase