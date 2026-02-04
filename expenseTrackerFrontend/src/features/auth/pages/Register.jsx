import { useState } from "react";
import { useNavigate } from "react-router-dom"
import { registerUser } from "../services/registerService";

const Register = () => {
    const navigate = useNavigate();
    const[form, setForm] = useState({
        username: "",
        password: "",
        mail: "",
    });

    const[error, setError] = useState("");
    const[loading,setLoading] = useState(false);

    const handleChange = (e) => {
        setForm({...form, [e.target.name] : e.target.value});
    }

    const handleSubmit = async(e) => {
        e.preventDefault();
        setError("");
        setLoading(true);
        try{
            await registerUser(form);
            navigate("/login");
        }catch(err){
            const errorCode = err.response?.data?.errorCode;

            if(errorCode==="DUPLICATE_USERNAME"){
                setError("Username already exists");
            }else{
                setError("registration failed");
            }
        }finally{
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
      placeholder="Email"
      value={form.mail}
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


}

export default Register;