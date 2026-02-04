import { useState } from "react";
import { useAuthStore } from "../store/authStore";
import { loginUser } from "../services/authService";
import httpClient from "../../../services/httpClient";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const navigate = useNavigate();
  const login = useAuthStore((state) => state.login);

  const [form, setForm] = useState({
    username: "",
    password: ""
  });

  const [error, setError] = useState("");
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");
    setIsLoading(true);

    try {
      const data = await loginUser(form);
      login(data.token);

      const userRes = await httpClient.get("/users/me");
      useAuthStore.getState().setUser(userRes.data);

      navigate("/");
    } catch (err) {
      setError("Invalid username or password");
    } finally {
      setIsLoading(false);
    }
  };

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

    return (
    <form className="auth-form" onSubmit={handleSubmit}>
  <h2 className="auth-title">Welcome Back</h2>
  <p className="auth-subtitle">Sign in to continue</p>

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
      name="password"
      type="password"
      placeholder="Password"
      value={form.password}
      onChange={handleChange}
      required
    />
  </div>

  {error && <p className="auth-error">{error}</p>}

  <button className="auth-btn primary" type="submit" disabled={isLoading}>
    {isLoading ? "Logging in..." : "Login"}
  </button>

  <button
    className="auth-btn secondary"
    type="button"
    onClick={() => navigate("/register")}
  >
    Create account
  </button>
</form>

  );
};

export default Login;
