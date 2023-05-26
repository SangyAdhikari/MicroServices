import axios from "axios";

// let baseURL = "http://localhost:8080";
let baseURL = "http://3.129.112.98:8080";
const headers = {
    'Content-Type': 'application/json',
}

export function getUserbyEmailid(emailid,callback, errorcallback) {
    axios
      .get(`${baseURL}/evento/authentication/evento/getId/${emailid}`)
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

  export function authenticateUser(data,callback, errorcallback) {
    axios
      .post(`${baseURL}/evento/authentication/authenticate`,data ,{
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
