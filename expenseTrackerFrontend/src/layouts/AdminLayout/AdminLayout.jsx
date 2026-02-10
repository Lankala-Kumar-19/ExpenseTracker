import { NavLink } from "react-router-dom"

const AdminLayout = () => {
    return(
        <nav className="nav">
            <NavLink to="/admin/dashboard" className="nav-link">Dashboard</NavLink>
            <NavLink to="/admin/users" className="nav-link">Users</NavLink>
            <NavLink to="/admin/expenses" className="nav-link">Expenses</NavLink>
            <NavLink to="/admin/categories" className="nav-link">Categories</NavLink>
        </nav>
    )
}

export default AdminLayout;