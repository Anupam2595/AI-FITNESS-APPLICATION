
// import './App.css'
import { Box, Button } from "@mui/material"
import { useContext, useEffect ,useState} from "react"
import { AuthContext } from "react-oauth2-code-pkce"
import { useDispatch } from "react-redux";
import { BrowserRouter as Router,Navigate,Route,Routes,useLocation } from "react-router"
import { logout, setCredentials } from "./store/authSlice";
import ActivityForm from "./components/ActivityForm";
import ActivityList from "./components/ActivityList";
import ActivityDetails from "./components/ActivityDetails";



const ActivitiesPage=()=>{
  return(
     <Box sx={{ p: 2, border: '1px dashed grey' }}>
     <ActivityForm onActivitesAdded={()=>window.location.reload()}/>
<ActivityList/>

    </Box>
  );
}


function App() {

const{token,tokenData,logIn,logOut,isAuthenticated}
=useContext(AuthContext);
const dispatch= useDispatch();
const [authReady,setAuthReady]=useState(false);

useEffect(()=>{
if(token){
  dispatch(setCredentials({token,user: tokenData}));
  setAuthReady(true);
}
},[token,tokenData,dispatch]);

  return (
    <>
<Router>
{!token?(
  <Button variant="contained"
   onClick={
    ()=>{
      logIn();
      }}>
  Login
</Button>
):(
  <Box  sx={{ p: 2, border: '1px dashed grey' }}>
      <Button variant="contained" onClick={logout}>
        logout
      </Button>
      <Routes>
<Route path="/activities" element={<ActivitiesPage/>}/>
<Route path="/activities/:id" element={<ActivityDetails/>}/>
<Route path="/" element={token?<Navigate to="/activities" replace></Navigate>:
<div>
  Welcome! Please Login
</div>
}/>
      </Routes>
    </Box>
)}
</Router>
    </>
  )
}

export default App
