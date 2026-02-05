import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../services/registerService";

const Register = () => {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: "",
    mail: "",
    password: "",
    confirmPassword: "",
  });

  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (form.password !== form.confirmPassword) {
      setError("Passwords do not match");
      return;
    }

    setLoading(true);
    try {
      const { confirmPassword, ...payload } = form; // exclude confirmPassword
      await registerUser(payload);
      navigate("/login");
    } catch (err) {
      const backendMessage = err.response?.data?.message || err.response?.data?.errors;

      if (err.response?.data?.errorCode === "DUPLICATE_USERNAME") {
        setError("Username already exists");
      } else if (backendMessage) {
        if (typeof backendMessage === "string") {
          setError(backendMessage);
        } else if (Array.isArray(backendMessage)) {
          setError(backendMessage.join(", "));
        }
      } else {
        setError("Registration failed");
      }
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="auth-form" onSubmit={handleSubmit}>
      <h2 className="auth-title">Create Account</h2>
      <p className="auth-subtitle">Join and track your expenses</p>

      <div className="auth-field">
        <input
          name="username"
          placeholder="Username"
          value={form.username}
          onChange={handleChange}
          required
        />
      </div>

      <div className="auth-field">
        <input
          name="mail"
          type="email"
          placeholder="Email"
          value={form.mail}
          onChange={handleChange}
          required
        />
        <small className="auth-hint">Example: user@example.com</small>
      </div>

      <div className="auth-field">
        <input
          name="password"
          type="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />
        <small className="auth-hint">Password should be 8-16 characters</small>
      </div>

      <div className="auth-field">
        <input
          name="confirmPassword"
          type="password"
          placeholder="Confirm Password"
          value={form.confirmPassword}
          onChange={handleChange}
          required
        />
      </div>

      {error && <p className="auth-error">{error}</p>}

      <button className="auth-btn primary" type="submit" disabled={loading}>
        {loading ? "Creating..." : "Register"}
      </button>

      <button
        className="auth-btn secondary"
        type="button"
        onClick={() => navigate("/login")}
      >
        Back to Login
      </button>
    </form>
  );
};

export default Register;
