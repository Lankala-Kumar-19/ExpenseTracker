import {create} from "zustand";
export const useAuthStore = create((set)=>({
    token : localStorage.getItem("token"),
    isAuthenticated: !!localStorage.getItem("token"),
    user:null,

    login:(token) =>{
        localStorage.setItem("token",token);
        set({
            token,
            isAuthenticated: true,
        });
    },

    setUser:(user) => set({user}),

    logout:() => {
        localStorage.removeItem("token");
        set({
            token:null,
            user:null,
            isAuthenticated:false,
        });
    },
}));