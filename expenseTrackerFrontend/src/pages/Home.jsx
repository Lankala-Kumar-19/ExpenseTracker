import { useNavigate } from "react-router-dom";
import bgImage from "../assets/home.png"; // or use public path

function Home() {
  const navigate = useNavigate();

  return (
    <div
      className="hero"
      style={{ backgroundImage: `url(${bgImage})` }}
    >
      <div className="top-right-buttons">
        <button onClick={() => navigate("/login")}>Login</button>
        <button onClick={() => navigate("/register")}>Register</button>
      </div>

      <div className="auth-container">
        
      </div>
    </div>
  );
}

export default Home;
