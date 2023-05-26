import { useLocation, Navigate, useNavigate, Outlet, BrowserRouter } from "react-router-dom";



const RequireAuth = ({ allowedRoles }) => {
    const location = useLocation();
 
    let logindetails = sessionStorage.getItem("logindetails")
    if(logindetails){
        let detailsobj = JSON.parse(logindetails);
        let type = detailsobj.userType;

        return (
            allowedRoles.includes(type)  ? <Outlet />:  <Navigate to="/unauthorized"  state={{ from: location }}/>
        
        );
    }
    else{
        return (
            <Navigate to="/unauthorized" />
        )
    }
   
}

export default RequireAuth;