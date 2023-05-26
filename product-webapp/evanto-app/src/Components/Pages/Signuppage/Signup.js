import React,{useState,useEffect} from 'react';
import './Signup.css';
import { useNavigate} from 'react-router-dom';
import { registerOrganizer,registerUser } from "../../../Services/RegisterService";
import swal from 'sweetalert';
import googlelogo from '../../../media/images/google-logo.png'
import { gapi, loadAuth2 } from 'gapi-script'
import { Link } from "react-router-dom";




const Signup = () => {


    const [email,setEmail] = useState('');
    const [password,setPasswordl] = useState('');
    const [confirmpassword,setConfirmpassword] = useState('');
    const [usertype,setUsertype] = useState('user');
    const [googleusertype,setgoogleUsertype] = useState('user');

    const [passType,setPassype] = useState('password')
    const [cnfrmpassType,setCnfrmpassype] = useState('password')
    const [showinfo,setShowinfo] = useState(false)
    const [googleuserdet,setgoogleuserdet] = useState('');
    const [Guser,setGuser] = useState({
        name: '',
        emailid: ''
    })
    const [errors,setErrors] = useState(
        {
            email: '',
            password: '',
            confirmpassword: ''
        }
    )
    const navigate = useNavigate();


    const setGoogleapi = async () =>{
        const auth2 =  await loadAuth2(gapi, `443985973744-esqeqe2digpb5s6kgrv6h8ehh85fnpps.apps.googleusercontent.com`,'')
        attachSignin(document.getElementById('googleauthsignup'), auth2);
    }

    useEffect(()=>{
        setGoogleapi();
       
    },[usertype])

    const handlePassclick = (event) =>{
        passType==='password' ?  setPassype('text') :   setPassype('password')
    }

    const handleCnfrmPassclick = (event) =>{
        cnfrmpassType==='password' ?  setCnfrmpassype('text') :   setCnfrmpassype('password')
    }
    const handleInfoclick =() =>{
        showinfo ?  setShowinfo(false) : setShowinfo(true)
    }

    const checkEmail = () =>{

        if(email===''){
            setErrors(prevState=>({
                ...prevState,
                email: 'This field is required'

            }))
            return false;
        }
        else if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email)){
            setErrors(prevState=>({
                ...prevState,
                email: ''

            }))
            return true;
        }
          
        else{
            setErrors(prevState=>({
                ...prevState,
                email: 'Please enter a valid email address'

            }))
        return false;

        }
    }

    const checkPassword = () =>{
        let validpass =  /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;

        if(password===''){
            setErrors(prevState=>({
                ...prevState,
                password: 'This field is required'

            }))
            return false;
        }
        else if(password.match(validpass)){
            setErrors(prevState=>({
                ...prevState,
                password: ''

            }))
            setShowinfo(false)
            return true;
        }
        else{
            setErrors(prevState=>({
                ...prevState,
                password: 'Please enter a valid password'

            }))
            return false;
        }
    }

    const checkConfirmpassword = () =>{

        if(confirmpassword===''){
            setErrors(prevState=>({
                ...prevState,
                confirmpassword: 'This field is required'

            }))
            return false;
        }
        else if(password===confirmpassword){
            setErrors(prevState=>({
                ...prevState,
                confirmpassword: ''

            }))
            return true;
        }
        else{
            setErrors(prevState=>({
                ...prevState,
                confirmpassword: 'This field should match with your password'

            }))
            return false;
        }
    }
    const checkUsertype = () =>{
        if(usertype){
            return true;
        }
        else{
            return false;
        }
    }
    const handlleLoginclick = () =>{
        navigate(`/login`);
    }

    const handleGoogleUser = (googleuserdet) =>{
       

        const name = googleuserdet.getBasicProfile().getName();
       
        const emailid = googleuserdet.getBasicProfile().getEmail();

        setGuser(prevState=>({
            ...prevState,
            name:name,
            emailid: emailid
        }))
   


        swal({
            title: "Please confirm",
            text: `You have selected Your role as a ${usertype}`,
            icon: "warning",
            buttons: {
                true: "Proceed",
                cancel: "Cancel",
            },
            reverseButtons: false,
            dangerMode: true,
          }).then((isconfirm) =>{

                if(isconfirm){

                    if(usertype === "Organizer"){

                        let data = {
                            "emailId": emailid,
                            "password": '',
                            "userType": "Organizer"
                          }
                         
                
                          registerOrganizer(data,(res)=>{
                            console.log(res)
                                if(res){
                                    swal("Registered successfully!")
                                    .then(() => {
                                    
                                        navigate(`/login`);
                        
                                    });

                                }
                                else{
                                    swal({
                                        title: "user Registration Failed",
                                        icon: "error",
                                      })
                                }
                           
                          },(err)=>{
                           
                            if(err.response.data){
                                swal({
                                    title: "user Registration Failed",
                                    icon: "error",
                                  })
                            }
                          })
            
                    }
                    else{
                       
                        let data = {
                            "emailId": emailid,
                            "password": '',
                            "userType": "User"
                          }
                         
                          registerUser(data,(res)=>{
                            console.log(res)
                                if(res){
                                    swal("Registered successfully!")
                                    .then(() => {
                                    
                                        navigate(`/login`);
                        
                                    });

                                }
                                else{
                                    swal({
                                        title: "user Registration Failed",
                                        icon: "error",
                                      })
                                }
                           
                          },(err)=>{
                           
                            if(err.response.data){
                                swal({
                                    title: "user Registration Failed",
                                    icon: "error",
                                  })
                            }
                          })
                         
                    }

                }
          });
     
        
    }

    const attachSignin = (element, auth2) => {
        auth2.attachClickHandler(element,{},
          (googleUser) => {
            setgoogleuserdet(googleUser)
            handleGoogleUser(googleUser)
           
          }, (error) => {
          console.log(JSON.stringify(error))
        });
      };




    const handlegoogleclick = async(event) => { 
       
        event.preventDefault();
        

    }

     
      
    const handleSubmit = (event) =>{
        event.preventDefault();
        checkEmail();
        checkPassword();
        checkConfirmpassword();
        checkUsertype();
        if(checkEmail()&&checkPassword()&&checkConfirmpassword()&&checkUsertype()){
           
            const headers = {
                'Content-Type': 'application/json',
               
              }

              swal({
                title: "Please confirm",
                text: `You have selected Your role as a ${usertype}`,
                icon: "warning",
                buttons: {
                    true: "Proceed",
                    cancel: "Cancel",
                },
                reverseButtons: false,
                dangerMode: true,
              }).then((isconfirm) =>{
               
                if(isconfirm){

                    if(usertype === "Organizer"){
                        let data = {
                            "emailId": email,
                            "password": password,
                            "userType": "Organizer"
                          }
        
                          registerOrganizer(data,(res)=>{
                            navigate(`/login`);
                          },(err)=>{
                           
                            swal({
                                title: "Registration Failed",
                                // text:err,
                                icon: "error",
                              })
                          })
                    }
                    else{
                        let data = {
                            "emailId": email,
                            "password": password,
                            "userType": "User"
                          }
        
                          registerUser(data,(res)=>{
        
                            swal("Registered successfully!")
                            .then(() => {
                            
                                navigate(`/login`);
                              
                            },(err)=>{
                               
                                swal({
                                    title: "Registration Failed",
                                    // text: err,
                                    icon: "error",
                                  })
                            });
        
                           
                          })
                    }

                }
              });


            
         
        }

    }
  

    return (
        <div className='container-fluid row text-light gx-0 mx-0 signup' id='signup'>
         <div className='backicon'>
            <Link to={'/'}><i className="fa-solid fa-circle-left icon"></i></Link>
        </div>

            <div className='col-md-5 d-none d-md-block frst_col'>
                <div className='d-flex flex-column align-items-center justify-content-center welcome_column'>
                    <h1 className='display-2 text-center'>Welcome to <span className='ev_primary_clr'>EVENTO</span></h1>
                    <p className='fs-6 mt-1'>Already a user? Login here to explore more</p>
                    <button className='btn mt-4 rounded-1 btn_login' onClick={handlleLoginclick}>Login</button>
                </div>
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1440 320"><path fill="#FFA500" fillOpacity="1" d="M0,64L34.3,85.3C68.6,107,137,149,206,176C274.3,203,343,213,411,218.7C480,224,549,224,617,192C685.7,160,754,96,823,96C891.4,96,960,160,1029,170.7C1097.1,181,1166,139,1234,138.7C1302.9,139,1371,181,1406,202.7L1440,224L1440,320L1405.7,320C1371.4,320,1303,320,1234,320C1165.7,320,1097,320,1029,320C960,320,891,320,823,320C754.3,320,686,320,617,320C548.6,320,480,320,411,320C342.9,320,274,320,206,320C137.1,320,69,320,34,320L0,320Z"></path></svg>
            </div>
            <div className='col-md-7 bg-light text-dark gx-0 mx-0 form_column d-flex flex-column align-items-center justify-content-center'>
                <h1 className='display-2 d-block d-md-none text-center'>Welcome to <span className='ev_primary_clr'>EVENTO</span></h1>
                <div className='col-12 d-flex flex-row justify-content-center align-items-center'>
                    <div className='signupform text-start col-10 col-sm-8 col-lg-5 d-flex flex-row justify-content-center'>
                        <form noValidate>

                        <div className='d-flex flex-row justify-content-center mt-1'>
                                    <div className="form-check pe-4">
                                        <input className="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault1" value='User' defaultChecked onChange={(event)=>setUsertype(event.target.value)} />
                                        <label className="form-check-label" htmlFor="flexRadioDefault1">
                                            User
                                        </label>
                                    </div>
                                    <div className="form-check">
                                        <input className="form-check-input" type="radio" name="flexRadioDefault" id="flexRadioDefault2" value='Organizer' onChange={(event)=>setUsertype(event.target.value)}/>
                                        <label className="form-check-label" htmlFor="flexRadioDefault2">
                                            Organizer   
                                        </label>
                                    </div>
                            </div>

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
                                <div className='errors '>
                                    {
                                        errors.password ? <div className='d-flex flex-row'>
                                        <p>{errors.password}</p>
                                       <div className='ps-1 border-0 info_area'>
                                            <i className="fa-solid fa-circle-info" onClick={handleInfoclick}></i>
                                            {
                                                showinfo ? 
                                           <div className="card pass_info p-2">
                                           <ul className='mb-0'>
                                                <li>length must be 7-15</li>
                                                <li>must contains atleast one numeric character</li>
                                                <li>must contains atleast one Special character</li>
                                            </ul>  </div>: ""
                                            }
                                            
                                            
                                              
                                          
                                       </div>
                                  </div> : ''
                                    }
                                </div>
                            </div>

                            <div className='passwordarea inputarea mt-1'>
                                <label htmlFor="cnfrmpassword" className="form-label mb-0">Confirm password</label>
                                <div className='d-flex flex-row text-dark align-items-baseline inputcontainer cnfrmpassword_input rounded-0'>
                                   
                                    <div className='inputicon_container'>
                                    <i className="fa-solid fa-lock text-dark inputicons fs-5"></i>
                                    </div>
                                    <input type={cnfrmpassType} className="form-control rounded-0" id="cnfrmpassword" placeholder='Confirm your password'  value={confirmpassword} onChange={(event)=>setConfirmpassword(event.target.value)}/>
                                    <div className='eyeicon_container' onClick={(event)=>handleCnfrmPassclick(event)}>
                                        {
                                            cnfrmpassType ==='password'?  <i className="fa-solid fa-eye eyeicon"></i> : <i className="fa-solid fa-eye-low-vision eyeicon"></i>
                                        }
                                    </div>
                                </div>
                                <p className='errors'>{errors.confirmpassword}</p>
                            </div>

                            
                            <div className='rounded-0 mt-2'>
                                <button className='btn rounded-0 w-100' onClick={(event)=>handleSubmit(event)} >Signup</button>
                            </div>

                          
                            <div className='or_container d-flex justify-content-center'>
                                <p className='or_class text-center pt-3'>Or</p>
                            </div>
                        
                             <button className='btn w-100 rounded-0 d-flex flex-row align-items-center justify-content-center google_btn mt-3'  id="googleauthsignup" onClick={(event)=>handlegoogleclick(event)}>
                                <div className='googleicon'>
                                <img src={googlelogo} alt="googlelogo" className="w-100"  />

                                </div>
        
                             <p className='ps-3'>Signup with google</p>
                             </button>
       
                           
                            <div className='d-block d-md-none d-flex flex-column jutify-content-center align-items-center pt-3 pb-4'>
                                <p className='fs-6 mt-1'>Already a user? Login here to explore more</p>
                                <button className='btn mt-2 rounded-1 btn_login' onClick={handlleLoginclick}>Login</button>
                            </div>
                           

                        </form>
                        
                    </div>
                    
                </div>
              
              
            </div>
         
        </div>
    );
}

export default Signup;
