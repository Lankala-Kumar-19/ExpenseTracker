import { useState } from "react";
import API from "../services/api";
import "../components/Login.css"; // make sure to create this CSS file
import { Navigate } from "react-router-dom";
function Login() {
  const [form, setForm] = useState({
    username: "",
    password: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await API.post("/login", form);
      localStorage.setItem("token", res.data);
      alert("Logged in successfully");
    } catch (err) {
      alert("Login failed");
    }
  };

  const handleBack = ()=>{
    navigate("/");
  }

  return (
    <div className="login-hero">
      <form className="login-form" onSubmit={handleSubmit}>
        <h2>Login</h2>
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={form.username}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />
        <button type="submit">Login</button>
        
        {/* <button onClick={handleBack}>back</button> */}
      </form>
    </div>
  );
}

export default Login;
