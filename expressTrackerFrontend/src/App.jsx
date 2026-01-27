import Login from './pages/Login'

import './App.css'
import { BrowserRouter,Route,Router, Routes } from 'react-router-dom'
import Register from './pages/Register'
import Home from './pages/Home'

function App() {
    return(
      <BrowserRouter>
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path="/login" element = {<Login />} />
          <Route path="/Register" element = {<Register />} />
        </Routes>
      </BrowserRouter>
    )
}

export default App
