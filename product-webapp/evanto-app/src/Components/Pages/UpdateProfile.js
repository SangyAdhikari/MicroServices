import React, { useState, useEffect } from "react";
import { Row, Col, Button, Label } from "reactstrap";
import TextField from "@mui/material/TextField";
import Visibility from "@mui/icons-material/Visibility";
import VisibilityOff from "@mui/icons-material/VisibilityOff";
import IconButton from "@mui/material/IconButton";
import InputLabel from "@mui/material/InputLabel";
import FilledInput from "@mui/material/FilledInput";

import InputAdornment from "@mui/material/InputAdornment";
import FormControl from "@mui/material/FormControl";

import Snackbar from "@mui/material/Snackbar";
import MuiAlert from "@mui/material/Alert";
import {
  getOrganizerDetailsAPI,
  updateProfileAPI,
  updateOrganizerPassword,
  updateUserPassword,
  updateUserProfile,
  getUserDetailsAPI,
} from "../../Services/RegisterService";

const Alert = React.forwardRef(function Alert(props, ref) {
  return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

export default function UpdateProfile() {
  const fontColor = { style: { color: "white" } };

  const [cnfPassErr, setCnfPassErr] = useState(false);
  const [newPassErr, setNewPassErr] = useState(false);
  const [oldPassErr, setOldPassErr] = useState(false);
  const [showPopup, setPopup] = useState(false);

  const [password, setPassword] = useState("");
  const [userType, setUserType] = useState("");
  const [emailId, setEmail] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [showOldPassword, setToggleOldPassword] = useState(false);
  const [showConfirmPassword, setShowConfirmPassword] = useState(false);

  const [Name, setName] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showForm, setForm] = useState(false);
  const [mobileErr, setMobileErr] = useState(false);
  const [MobileErrMsg, setMobileErrMsg] = useState("");

  const [usrNameErr, setUsrNameErr] = useState("");
  const [errUserNameMsg, seterrUserNameMsg] = useState("");
  const [pwdPatternError, setPwdPatternError] = useState("");

  const [showError, setShowError] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  useEffect(() => {
    getInfo();
  }, []);

  const getInfo = () => {
    let UserAccount = sessionStorage.getItem("logindetails");
    UserAccount = JSON.parse(UserAccount);
    if (UserAccount === null) {
      return;
    }

    let { userEmail, userType } = UserAccount;
    setUserType(userType);
    setEmail(userEmail);

    if (userType === "Organizer") {
      getOrganizerDetailsAPI(
        userEmail,
        (res) => {
          let { mobileNo, organizerName, password } = res;

          setMobileNo(mobileNo);
          setName(organizerName);
          setPassword(password);
        },
        (err) => {
          console.log(err);
          setShowError(true);
          setErrorMsg(err.response.data);
        }
      );
    } else {
      getUserDetailsAPI(
        userEmail,
        (res) => {
          let { emailId, mobileNo, userName, password } = res;
          setEmail(emailId);
          setMobileNo(mobileNo);
          setName(userName);
          setPassword(password);
        },
        (err) => {
          console.log(err);
          setShowError(true);
          setErrorMsg(err.response.data.error);
        }
      );
    }
  };
  const onUpdateProfile = (event) => {
    event.preventDefault();

    let valid = true;
    if (Name === "" || Name === null) {
      seterrUserNameMsg("Please Enter User Name");
      setUsrNameErr(true);
      valid = false;
    }

    var regex = /^[a-zA-Z ]{2,30}$/;

    let checKUsername = regex.test(Name);

    if (!checKUsername) {
      seterrUserNameMsg("Please Check User Name");
      setUsrNameErr(true);
      valid = false;
    }

    if (mobileNo === "" || mobileNo === null) {
      setMobileErrMsg("Please Enter Mobile Number");
      setMobileErr(true);
      valid = false;
    }

    if (mobileNo.toString().length !== 10) {
      setMobileErrMsg("Please Check Mobile Number");
      setMobileErr(true);
      valid = false;
    }

    if (!valid) {
      return;
    }

    if (userType === "Organizer") {
      let data = {
        emailId,
        organizerName: Name,
        List: [],
        mobileNo,
        userType,
        password,
      };

      updateProfileAPI(
        emailId,
        data,
        (res) => {
          setPopup(true);
        },
        (err) => {
          console.log(err);
          setShowError(true);
          setErrorMsg(err.response.data);
        }
      );
    } else {
      let data = {
        emailId,
        userName: Name,
        mobileNo,
        userType,
        password,
      };

      updateUserProfile(
        emailId,
        data,
        (res) => {
          setPopup(true);
        },
        (err) => {
          console.log(err);
          setShowError(true);
          setErrorMsg(err.response.data);
        }
      );
    }
  };

  const checkPassword = () => {
    let validpass = /^(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{7,15}$/;

    if (newPassword.match(validpass)) {
      setPwdPatternError("");
      return true;
    } else {
      setPwdPatternError("This field is required");
      return false;
    }
  };

  const onUpdatePassword = (event) => {
    event.preventDefault();

    let validPassword = checkPassword();

    if (!validPassword) {
      return;
    }

    let valid = true;
    if (password !== oldPassword) {
      setOldPassErr(true);
      valid = false;
    }
    if (
      newPassword !== confirmPassword ||
      newPassword === "" ||
      confirmPassword === ""
    ) {
      setCnfPassErr(true);
      setNewPassErr(true);
      valid = false;
    }
    if (!valid) {
      return;
    }

    if (userType === "Organizer") {
      updateOrganizerPassword(
        emailId,
        newPassword,
        (res) => {
          setPopup(true);
          setForm(false)
          setOldPassword("")
          setNewPassword("")
          setConfirmPassword("")
        },
        (err) => {
          console.log(err);
          setShowError(true);
          setErrorMsg(err.response.data);
         
        }
      );
    } else {
      updateUserPassword(
        emailId,
        newPassword,
        (res) => {
          setPopup(true);
          setForm(false)
          setOldPassword("")
          setNewPassword("")
          setConfirmPassword("")
        },
        (err) => {
          console.log(err);
          setShowError(true);
          setErrorMsg(err.response.data);
         
        }
      );
    }
  };

  return (
    <div className="container-fluid muidrawer-padding" id="update_profile">
      <Row className="justify-content-center" style={{ minHeight: "90vh" }}>
        <Col md={9}>
          <form className="mt-4 container" onSubmit={onUpdateProfile}>
            <Row>
              <Col md={12} className="text-center">
                <h2 className="text-orange">Update profile</h2>
              </Col>
            </Row>

            <Row className="mt-3">
              <Col md={12}>
                <TextField
                  name="Email"
                  color="warning"
                  label="Email Id"
                  variant="standard"
                  style={{ width: "100%" }}
                  value={emailId}
                  inputProps={fontColor}
                />
              </Col>
            </Row>
            <Row className="mt-3">
              <Col md={12}>
                <TextField
                  name="UserName"
                  color="warning"
                  label="User Name"
                  variant="standard"
                  style={{ width: "100%" }}
                  value={Name}
                  // required
                  onChange={(e) => {
                    setName(e.target.value);
                    setUsrNameErr(false);
                  }}
                  inputProps={fontColor}
                />
                {usrNameErr && (
                  <div className="mt-1" style={{ fontSize: 12, color:'red' }}>
                    {errUserNameMsg}
                  </div>
                )}
              </Col>
            </Row>

            <Row className="mt-3">
              <Col md={12}>
                <TextField
                  name="MobileNumber"
                  label="Mobile Number"
                  variant="standard"
                  color="warning"
                  type={"number"}
                  value={mobileNo}
                  onChange={(e) => {
                    setMobileNo(e.target.value);
                    setMobileErr(false);
                  }}
                  // required
                  style={{ width: "100%", color: "white" }}
                  inputProps={fontColor}
                />
                {mobileErr && (
                  <div className="mt-1" style={{ fontSize: 12, color:'red' }}>
                    {MobileErrMsg}
                  </div>
                )}
              </Col>
            </Row>
            <Row className="my-4">
              <Col className="text-center p-1">
                <Button
                  color="warning"
                  outline
                  className="text-white"
                  style={{ minWidth: 185 }}
                >
                  UPDATE PROFILE
                </Button>
              </Col>
              {
                password !== "" &&
                <Col className="text-center p-1">
                  <Button
                    style={{ minWidth: 185 }}
                    className="text-white"
                    color="warning"
                    outline
                    type="button"
                    onClick={() => setForm((value) => !value)}
                  >
                    CHANGE PASSWORD
                  </Button>
                </Col>
              }
            </Row>
          </form>

          <form className="mt-2 container" onSubmit={onUpdatePassword}>
            {showForm ? (
              <>
                <Row className="mt-3">
                  <Col md={12}>
                    <FormControl sx={{ width: "100%" }} variant="standard">
                      <InputLabel htmlFor="filled-adornment-password">
                        Old Password
                      </InputLabel>
                      <FilledInput
                        type={showOldPassword ? "text" : "password"}
                        value={oldPassword}
                        variant="standard"
                        inputProps={fontColor}
                        color="warning"
                        required
                        error={oldPassErr}
                        onChange={(e) => {
                          setOldPassword(e.target.value);
                          setOldPassErr(false);
                        }}
                        endAdornment={
                          <InputAdornment position="end">
                            <IconButton
                              style={{ color: "white" }}
                              aria-label="toggle password visibility"
                              onClick={() => setToggleOldPassword((e) => !e)}
                              edge="end"
                            >
                              {showOldPassword ? (
                                <VisibilityOff />
                              ) : (
                                <Visibility />
                              )}
                            </IconButton>
                          </InputAdornment>
                        }
                      />
                      {oldPassErr && (
                        <div
                          className="mt-1"
                          style={{ fontSize: 12, color:'red' }}
                        >
                          Incorrect Old Password
                        </div>
                      )}
                    </FormControl>
                  </Col>
                </Row>
                <Row className="mt-3">
                  <Col md={12}>
                    <FormControl sx={{ width: "100%" }} variant="standard">
                      <InputLabel htmlFor="filled-adornment-password">
                        New Password
                      </InputLabel>
                      <FilledInput
                        type={showPassword ? "text" : "password"}
                        value={newPassword}
                        variant="standard"
                        inputProps={fontColor}
                        color="warning"
                        required
                        error={newPassErr}
                        onChange={(e) => {
                          setNewPassword(e.target.value);
                          setNewPassErr(false);
                        }}
                        endAdornment={
                          <InputAdornment position="end">
                            <IconButton
                              style={{ color: "white" }}
                              aria-label="toggle password visibility"
                              onClick={() => setShowPassword((e) => !e)}
                              edge="end"
                            >
                              {showPassword ? (
                                <VisibilityOff />
                              ) : (
                                <Visibility />
                              )}
                            </IconButton>
                          </InputAdornment>
                        }
                      />

                      {pwdPatternError && (
                        <div
                          className="mt-1"
                          style={{ fontSize: 12, color:'red' }}
                        >
                          Password must be 7-15 character and that includes 1
                          numeric character, 1 Special character
                        </div>
                      )}
                    </FormControl>
                  </Col>
                </Row>
                <Row className="mt-3">
                  <Col md={12}>
                    <FormControl sx={{ width: "100%" }} variant="standard">
                      <InputLabel htmlFor="filled-adornment-password">
                        Confirm Password
                      </InputLabel>
                      <FilledInput
                        type={showConfirmPassword ? "text" : "password"}
                        value={confirmPassword}
                        variant="standard"
                        inputProps={fontColor}
                        color="warning"
                        required
                        error={cnfPassErr}
                        onChange={(e) => {
                          setConfirmPassword(e.target.value);
                          setCnfPassErr(false);
                        }}
                        endAdornment={
                          <InputAdornment position="end">
                            <IconButton
                              style={{ color: "white" }}
                              aria-label="toggle password visibility"
                              onClick={() => setShowConfirmPassword((e) => !e)}
                              edge="end"
                            >
                              {showConfirmPassword ? (
                                <VisibilityOff />
                              ) : (
                                <Visibility />
                              )}
                            </IconButton>
                          </InputAdornment>
                        }
                      />
                      {cnfPassErr && (
                        <div
                          className="mt-1"
                          style={{ fontSize: 12, color:'red' }}
                        >
                          Password does not match
                        </div>
                      )}
                    </FormControl>
                  </Col>
                </Row>
                <Row className="mt-4">
                  <Col md={12}>
                    <Button
                      color="warning"
                      outline
                      className="w-100 text-white"
                    >
                      UPDATE PASSWORD
                    </Button>
                  </Col>
                </Row>
              </>
            ) : null}
          </form>
        </Col>

        <Snackbar
          open={showPopup}
          autoHideDuration={3000}
          onClose={() => setPopup(false)}
          anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        >
          <Alert
            onClose={() => setPopup(false)}
            severity="success"
            sx={{ width: "50%" }}
          >
            Profile Updated Successfully
          </Alert>
        </Snackbar>

        <Snackbar
          open={showError}
          autoHideDuration={3000}
          onClose={() => {
            setShowError(false);
            setErrorMsg("");
          }}
          anchorOrigin={{ vertical: "bottom", horizontal: "center" }}
        >
          <Alert
            onClose={() => {
              setShowError(false);
              setErrorMsg("");
            }}
            severity="error"
            sx={{ width: "100%" }}
          >
            {errorMsg}
          </Alert>
        </Snackbar>
      </Row>
    </div>
  );
}
