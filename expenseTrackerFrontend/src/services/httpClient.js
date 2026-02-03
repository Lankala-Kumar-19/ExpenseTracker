import axios from "axios";
import { useAuthStore } from "../features/auth/store/authStore";


const httpClient = axios.create({
    baseURL: "http://localhost:8080",
    headers: {
        "Content-Type" : "application/json",
    },
});

httpClient.interceptors.request.use(
    (config) => {
        const token = useAuthStore.getState().token;
        if(token){
            config.headers.Authorization = `Bearer ${token}`;
        }
        
        return config;
    },
    (error) => Promise.reject(error)
);

httpClient.interceptors.response.use(
    (response) => response,
    (error) => {
        if(error.response?.status === 401){
            useAuthStore.getState().logout();
            window.location.href = "/login";
        }

        return Promise.reject(error);
    }
);

export default httpClient;