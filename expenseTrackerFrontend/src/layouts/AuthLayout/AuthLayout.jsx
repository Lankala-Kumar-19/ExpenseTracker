import { Outlet } from "react-router-dom";
import './AuthLayout.css';

const AuthLayout = () => {
  return (
    <div className="auth-wrapper">
      <div className="auth-left"></div>  {/* background image div */}
      <div className="auth-right">
        <Outlet />
      </div>
    </div>
  );
};

export default AuthLayout;
