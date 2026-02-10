import { Route, Routes } from "react-router-dom"
import Login from "../features/auth/pages/Login"
import Logout from "../features/auth/pages/Logout"
import Dashboard from "../features/dashboard/Dashboard"
import Register from "../features/auth/pages/Register"
import Expenses from "../features/expenses/Expenses"
import Categories from "../features/categories/Categories"
import AuthLayout from "../layouts/AuthLayout/AuthLayout"
import PrivateRoute from "./PrivateRoute"
import DashboardLayout from "../layouts/DashboardLayout/DashboardLayout"
import AdminRoutes from "../routes/AdminRoutes";
import AdminDashboard from "../features/admin/pages/AdminDashboard"
import UsersList from "../features/admin/pages/UsersList"
import ExpenseList from "../features/admin/pages/ExpensesList"
import CategoriesList from "../features/admin/pages/CategoriesList"
import AdminDashboardLayout from "../features/admin/pages/AdminDashboardLayout"
const AppRoutes = () => {
    return(
        <Routes>
            <Route element={<AuthLayout />}>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                {/* <Route path="/logout" element={<Logout />} /> */}
            </Route>

            <Route element={<PrivateRoute />}>
                <Route element={<DashboardLayout />}>
                    <Route path="/" element={<Dashboard />} />
                    <Route path="/expenses" element={<Expenses />} />
                    <Route path = "/categories" element={<Categories />} /> 
                </Route>
            </Route>

            <Route path="/admin" element={<AdminRoutes />}>
                <Route element={<AdminDashboardLayout />}>
                    <Route path="dashboard" element={<AdminDashboard />} />
                    <Route path="users" element={<UsersList />} /> 
                    <Route path="expenses" element={<ExpenseList />} />
                    <Route path="categories" element={<CategoriesList />} />
                </Route>  
            </Route>

        </Routes>
    )
}

export default AppRoutes;