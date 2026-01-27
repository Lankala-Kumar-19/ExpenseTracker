import { useState } from "react";
import API from "../services/api";
import "../components/Register.css"; // create a separate CSS file similar to Login.css
import { useNavigate } from "react-router-dom";

function Register() {
    const navigate = useNavigate();
    const [form, setForm] = useState({
    username: "",
    mail: "",
    password: "",
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await API.post("/users/register", form);
      localStorage.setItem("token", res.data);
      alert("Registration successful!");
    } catch (err) {
      alert("Registration failed");
    }
  };

  const handleBack = ()=>{
    navigate("/");
  }

  return (
    <div className="register-hero">
      <form className="register-form" onSubmit={handleSubmit}>
        <h2>Register</h2>
        <input
          type="text"
          name="username"
          placeholder="Username"
          value={form.username}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="mail"
          placeholder="Email"
          value={form.mail}
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
        <button type="submit">Register</button>
        {/* <button onClick={handleBack}>back</button> */}
      </form>
    </div>
  );
}

export default Register;
