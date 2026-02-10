import { Navigate, Outlet } from "react-router-dom";
import { useAuthStore } from "../features/auth/store/authStore"

const AdminRoutes = ()=>{
    const {isAuthenticated , role } = useAuthStore();

    if(!isAuthenticated) return <Navigate to="/login" />
    if(role!="ADMIN") return <Navigate to="/login" />
    return <Outlet />;
}


export default AdminRoutes;