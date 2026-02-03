import { Route, Routes } from "react-router-dom"
import Login from "../features/auth/pages/Login/Login"
import Register from "../features/auth/pages/Register/Register"
import AuthLayout from "../layouts/AuthLayout/AuthLayout"
import PrivateRoute from "./PrivateRoute"
import Dashboard from "../features/dashboard/pages/Dashboard"
import Expenses from "../features/expenses/pages/Expenses"
import Categories from "../features/categories/pages/Categories"
import Logout from "../features/auth/pages/Logout/Logout"

const AppRoutes = () => {
    return(
        <Routes>
            <Route element={<AuthLayout />}>
                <Route path="/login" element={<Login />} />
                <Route path="/register" element={<Register />} />
                <Route path="/logout" element={<Logout />} />
            </Route>

            <Route element={<PrivateRoute />}>
                <Route path="/" element={<Dashboard />} />
                <Route path="/expenses" element={<Expenses />} />
                <Route path = "/categories" element={<Categories />} /> 
            </Route>
        </Routes>
    )
}

export default AppRoutes;