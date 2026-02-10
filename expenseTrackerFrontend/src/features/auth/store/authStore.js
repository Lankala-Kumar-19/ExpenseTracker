import {create} from "zustand";
const getRoleFromToken = (token) => {
    if(!token) return null;
    try{
        const payload = JSON.parse(atob(token.split('.')[1]));
        console.log(payload.role);
        return payload.role;
    }catch(err){
        return null;
    }
}
export const useAuthStore = create((set)=>({
    token : localStorage.getItem("token"),
    isAuthenticated: !!localStorage.getItem("token"),
    user:null,

    role: getRoleFromToken(localStorage.getItem("token")),

    login:(token) =>{
        localStorage.setItem("token",token);
        set({
            token,
            isAuthenticated: true,
            role:getRoleFromToken(token),
        });
    },

    setUser:(user) => set({user}),

    logout:() => {
        localStorage.removeItem("token");
        set({
            token:null,
            user:null,
            isAuthenticated:false,
            role:null,
        });
    },
}));