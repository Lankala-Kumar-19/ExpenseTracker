import { useAuthStore } from "../store/authStore"

const Logout = () => {
    const logout = useAuthStore((state)=>state.logout)
    return(
        <div>
            <button onClick={logout()}>Logout</button>
        </div>
    );
}

export default Logout;