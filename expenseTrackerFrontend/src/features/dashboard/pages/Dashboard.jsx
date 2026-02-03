import { useNavigate } from "react-router-dom";

const Dashboard = () =>{

    const navigate = useNavigate();
    return(
        <div>
            <h2>Dashboard</h2>
            <button onClick={()=> navigate("/expenses")}>Expenses</button>
            <button onClick={()=> navigate("/categories")}>Categories</button>
        </div>

    );
};

export default Dashboard;