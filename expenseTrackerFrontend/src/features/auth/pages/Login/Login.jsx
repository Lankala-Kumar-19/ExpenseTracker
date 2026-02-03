import { useState } from "react";
import { useAuthStore } from "../../store/authStore"
import { loginUser } from "../../services/authService";
import httpClient from "../../../../services/httpClient";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const login = useAuthStore((state) => state.login);
    const[form,setForm] = useState({
        username: "",
        password: ""
    });

    const[error,setError] = useState("");

    const handleSubmit = async(e) => {
        e.preventDefault();
        try{
            const data = await loginUser(form);
            console.log(data.token);
            
            login(data.token);
            const userRes = await httpClient.get("/users/me");
            useAuthStore.getState().setUser(userRes.data);
            navigate("/");

        }catch(err){
            setError("invalid username or password");
        }
    };

    const handleChange = async(e) => {
        setForm({...form,[e.target.name]: e.target.value});
    }

    return(
        <form onSubmit={handleSubmit}>
            <input
                name="username"
                placeholder="Username"
                onChange={handleChange}
                value={form.username}
            />
            <input
                name="password"
                type="password"
                placeholder="password"
                onChange={handleChange}
                value={form.password}
            />
            <button type="submit">Login</button>
            {error && <p>{error}</p>}
        </form>
    );
};

export default Login;