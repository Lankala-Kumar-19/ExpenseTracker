import { useState } from "react";
import { useNavigate } from "react-router-dom"
import { registerUser } from "../../services/registerService";

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
            <input
                name="mail"
                placeholder="mail"
                onChange={handleChange}
                value={form.mail}
            />
            <button type="submit">Register</button>
            {error && <p>{error}</p>}
        </form>
    );


}

export default Register;