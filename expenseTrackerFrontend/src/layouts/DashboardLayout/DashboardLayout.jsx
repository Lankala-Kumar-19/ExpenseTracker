import { Outlet, NavLink, useNavigate } from "react-router-dom";
import { useAuthStore } from "../../features/auth/store/authStore";
import "./dashboard.css";
const DashboardLayout = () => {
  const navigate = useNavigate();
  const logout = useAuthStore((state) => state.logout);
  const {role} = useAuthStore();
  console.log(role);
  
  return (
    <div className="dashboard">
      <aside className="sidebar">
        <h2 className="logo">Expense Tracker</h2>

        <nav className="nav">
          <NavLink to="/" end className="nav-link">
            Dashboard
          </NavLink>
          <NavLink to="/expenses" className="nav-link">
            Expenses
          </NavLink>
          <NavLink to="/categories" className="nav-link">
            Categories
          </NavLink>
        </nav>
      </aside>

      <div className="main">
        <header className="header">
          {role=="ADMIN" && <button className="admin-btn" onClick={()=>navigate("/admin/dashboard")}>admin panel</button>}
          <button className="logout-btn" onClick={logout}>
            Logout
          </button>
          
        </header>

        <main className="content">
          <Outlet />
        </main>
      </div>
    </div>
  );
};

export default DashboardLayout;
