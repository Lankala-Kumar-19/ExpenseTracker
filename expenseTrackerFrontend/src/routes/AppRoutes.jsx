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
        </Routes>
    )
}

export default AppRoutes;