import axios from "axios";

// let baseURL = "http://localhost:8080";
let baseURL = "http://3.129.112.98:8080";

const headers = {
  'Content-Type': 'application/json',
 
}

export function getAllTheaterAPI(callback, errorcallback) {
  axios
    .get(`${baseURL}/evento/register/get/organizers`)
    .then((res) => {
     
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}

export function getOrganizerDetailsAPI(emailId, callback, errorcallback) {
  axios
    .get(`${baseURL}/evento/register/get/organizer/${emailId}`)
    .then((res) => {
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}


export function updateOrganizerPassword(emailId, password, callback, errorcallback) {

  axios
    .put(`${baseURL}/evento/register/update/update-organizer-password/${emailId}`, { password })    
    .then((res) => {
     
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}


export function updateUserPassword(emailId, password, callback, errorcallback) {

  axios
    .put(`${baseURL}/evento/register/update/update-user-password/${emailId}`, { password })    
    .then((res) => {
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}




export function updateUserProfile(emailId, payload, callback, errorcallback) {
  let {  userName, mobileNo } = payload

  axios
    .put(`${baseURL}/evento/register/update/update-user/${emailId}/${userName}/${mobileNo}`)    
    .then((res) => {
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}




export function updateProfileAPI(emailId, payload, callback, errorcallback) {
  let {  organizerName, mobileNo } = payload
  axios
    .put(`${baseURL}/evento/register/update/update-organizer/${emailId}/${organizerName}/${mobileNo}`)    
    .then((res) => {
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}

export function addNewVenueAPI(emailId, payload, callback, errorcallback) {
  axios
    .put(`${baseURL}/evento/register/update/theatreDetails/${emailId}`, payload)
    .then((res) => {
    
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}

export function updateVenueAPI(id,emailId, payload, callback, errorcallback) {
  axios
    .put(`${baseURL}/evento/register/update-organizer/theatreDetails/${id}/${emailId}`, payload)
    .then((res) => {
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}



export async function getTheaterById(theaterid,callback, errorcallback) {
  await axios
    .get(`${baseURL}/evento/register/get/theatreDetails/${theaterid}`)
    .then((res) => {
      
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}


export async function registerOrganizer(data,callback, errorcallback) {
  await axios
    .post(`${baseURL}/evento/register/register-organizer`,data,{
      headers:headers
    })
    .then((res) => {
      
      if (callback != null) {
        callback(res);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}


export async function registerUser(data,callback, errorcallback) {
  await axios
    .post(`${baseURL}/evento/register/register-user`,data,{
      headers:headers
    })
    .then((res) => {
      
      if (callback != null) {
        callback(res);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}



export function getUserDetailsAPI(emailId, callback, errorcallback) {
  axios
    .get(`${baseURL}/evento/register/get/user/${emailId}`)
    .then((res) => {
      if (callback != null) {
        callback(res.data);
      }
    })
    .catch((err) => {
      if (errorcallback != null) {
        errorcallback(err);
      }
    });
}