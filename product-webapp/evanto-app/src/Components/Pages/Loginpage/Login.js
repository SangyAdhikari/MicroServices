import React,{useState,useEffect} from 'react';
import './Login.css';
import { useNavigate} from 'react-router-dom';
import googlelogo from '../../../media/images/google-logo.png'
import {getUserbyEmailid, authenticateUser } from "../../../Services/AuthenticationService";
import swal from 'sweetalert';
import { gapi, loadAuth2 } from 'gapi-script'
import { Link } from "react-router-dom";


const Login = () => {

    const [email,setEmail] = useState('');
    const [password,setPasswordl] = useState('');
    const [passType,setPassype] = useState('password');
    const [errors,setErrors] = useState({
        email: '',
        password: '',
        login: ''
    })

    
    const navigate = useNavigate();

    const setGoogleapi = async () =>{
        const auth2 =  await loadAuth2(gapi, `443985973744-esqeqe2digpb5s6kgrv6h8ehh85fnpps.apps.googleusercontent.com`,'')
        attachSignin(document.getElementById('loginauthsignup'), auth2);
    }

    useEffect(()=>{
        setGoogleapi();
    },[])

    const handlePassclick = (event) =>{
        passType==='password' ?  setPassype('text') :   setPassype('password')
    }


    const handleGoogleUser = (googleuserdet) =>{

        const name = googleuserdet.getBasicProfile().getName();
        // const profileImg = googleuserdet.getBasicProfile().getImageUrl();
        const emailid = googleuserdet.getBasicProfile().getEmail();
        getUserbyEmailid(emailid,(res) => {
            if(res.userEmail){
                let logindetails = {
                    "userEmail": emailid,
                    "userType": res.userType,
                   
                }
                let datastring = JSON.stringify(logindetails);
                sessionStorage.setItem("logindetails",datastring);
                navigate(`/home`);
            }
        })
    }

    const attachSignin = (element, auth2) => {
        auth2.attachClickHandler(element,{},
          (googleUser) => {
            handleGoogleUser(googleUser)
           
          }, (error) => {
          console.log(JSON.stringify(error))
        });
      };

   
    const handlleSignupclick = () =>{
        navigate(`/signup`);
    }
    const checkEmail = () =>{
        if(email){
            setErrors(prevState=>({
                ...prevState,
                email: ''

            }))
            return true;
        }
        else{
            setErrors(prevState=>({
                ...prevState,
                email: 'This field is required'

            }))
            return false;
        }
    }
    const checkPassword = () =>{
        if(password){
            setErrors(prevState=>({
                ...prevState,
                password: ''

            }))
            return true;
        }
        else{
            setErrors(prevState=>({
                ...prevState,
                password: 'This field is required'

            }))
            return false;
        }
    }
  
      
    const handleSubmit = (event) =>{
        event.preventDefault();
        checkEmail();
        checkPassword();
        if(checkEmail()&&checkPassword()){

            getUserbyEmailid(email,(res) => {
              
                if(res.userEmail){
                    let data = {
                        "userEmail": email,
                        "userPassword": password,
                        "userType": res.userType
                    }
                  

                    authenticateUser(data,(response)=>{
                       
                        if(response.data.jwtToken){
                            let logindetails = {
                                "userEmail": email,
                                "userType": res.userType,
                                "jwtToken": response.data.jwtToken
                            }
                            let datastring = JSON.stringify(logindetails);
                            sessionStorage.setItem("logindetails",datastring);
                            navigate(`/home`);
                        }

                    },(err)=>{
                        console.log(err)
                        swal({
                            title: "Login Failed",
                            text: "Please check your email and password",
                            icon: "error",
                          })
                       
                        setErrors(prevState=>({
                            ...prevState,
                            login: 'Login failed'
            
                        }))
                    })
                }
            },(err)=>{
                console.log(err)
                swal({
                    title: "Login Failed",
                    text: "Please check your email and password",
                    icon: "error",
                  })
               
                setErrors(prevState=>({
                    ...prevState,
                    login: 'Login failed'
    
                }))
            })
         
        }
       

    }
    const handlegoogleclick = (event) => {
        event.preventDefault();
     }

    return (
        <div className='container-fluid row text-light gx-0 mx-0 login'>
  <div className='backicon'>
            <Link to={'/'}> <i className="fa-solid fa-circle-left icon"></i></Link>
        </div>
            <div className='col-md-5 d-none d-md-block bg-light text-dark welcome_column'>
                <div className='d-flex flex-column align-items-center justify-content-center welcome_column'>
                    <h1 className='display-2 text-center'>Welcome to <span className='ev_primary_clr'>EVENTO</span></h1>
                    <p className='fs-6 mt-1'>New here? Signup here to explore more</p>
                    <button className='btn mt-4 rounded-1 btn_login' onClick={handlleSignupclick}>Signup</button>
                </div>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FFA500" fillOpacity="1" d="M0,64L34.3,85.3C68.6,107,137,149,206,176C274.3,203,343,213,411,218.7C480,224,549,224,617,192C685.7,160,754,96,823,96C891.4,96,960,160,1029,170.7C1097.1,181,1166,139,1234,138.7C1302.9,139,1371,181,1406,202.7L1440,224L1440,320L1405.7,320C1371.4,320,1303,320,1234,320C1165.7,320,1097,320,1029,320C960,320,891,320,823,320C754.3,320,686,320,617,320C548.6,320,480,320,411,320C342.9,320,274,320,206,320C137.1,320,69,320,34,320L0,320Z"></path></svg>
            </div>
            <div className='col-md-7 gx-0 mx-0 form_column d-flex flex-column align-items-center justify-content-center'>
                <h1 className='display-2 d-block d-md-none text-center'>Welcome to <span className='ev_primary_clr'>EVENTO</span></h1>
                <div className='col-12 d-flex flex-row justify-content-center align-items-center'>
                    <div className='loginform text-start col-10 col-sm-8 col-lg-5 d-flex flex-row justify-content-center'>
                        <form onSubmit={(event)=>handleSubmit(event)} noValidate>

                        

                            <div className='emailarea'>
                                <label htmlFor="exampleInputEmail1" className="form-label mb-0">Email</label>
                                <div className='d-flex flex-row text-dark align-items-baseline inputcontainer email_input rounded-0'>
                                    <div className='inputicon_container'>
                                        <i className="fa-solid fa-envelope text-dark inputicons fs-5"></i>
                                    </div>
                                     <input type="email" className="form-control rounded-0" id="exampleInputEmail1"  placeholder='Enter your email' value={email} autoComplete='off' onChange={(event)=>setEmail(event.target.value)}/>
                                </div>
                                <p className='errors'>{errors.email}</p>
                               
                            </div>

                            <div className='passwordarea inputarea mt-1'>
                                <label htmlFor="password" className="form-label mb-0">Password</label>
                                <div className='d-flex flex-row text-dark align-items-baseline inputcontainer password_input rounded-0'>
                                  <div className='inputicon_container'>
                                     <i className="fa-solid fa-key text-dark inputicons fs-5"></i>
                                    </div>
                                  
                                    <input type={passType} className="form-control rounded-0" id="password" placeholder='Enter your password' value={password} onChange={(event)=>setPasswordl(event.target.value)} />
                                    <div className='eyeicon_container' onClick={(event)=>handlePassclick(event)}>
                                        {
                                            passType ==='password'?  <i className="fa-solid fa-eye eyeicon"></i> : <i className="fa-solid fa-eye-low-vision eyeicon"></i>
                                        }
                                       
                                    </div>
                                </div>
                                <p className='errors'>{errors.password}</p>
                                
                            </div>

                            
                            
                            <div className='rounded-0 mt-2'>
                                <button className='btn rounded-0 w-100'>Login</button>
                            </div>
                            <div className='or_container d-flex justify-content-center'>
                                <p className='or_class text-center pt-3'>Or</p>
                            </div>
                           
                            <button className='btn w-100 rounded-0 d-flex flex-row align-items-center justify-content-center google_btn mt-3' onClick={(event)=>handlegoogleclick(event)} id="loginauthsignup">
                                <div className='googleicon'>
                                <img src={googlelogo} alt="googlelogo" className="w-100"  />

                                </div>
        
                             <p className='ps-3'>Login with google</p>
                             </button>

                             <div className='d-block d-md-none d-flex flex-column jutify-content-center align-items-center pt-3 pb-4'>
                                <p className='fs-6 mt-1 text-center'>New here? Signup here to explore more</p>
                                <button className='btn mt-2 rounded-1 btn_login' onClick={handlleSignupclick}>Signup</button>
                            </div>
                            

                        </form>
                        
                    </div>
                   
                </div>
               
            </div>
         
        </div>
    );
}

export default Login;
