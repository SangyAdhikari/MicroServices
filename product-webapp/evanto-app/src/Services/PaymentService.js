import axios from "axios";

// let baseURL = "http://localhost:8080";
let baseURL = "http://3.129.112.98:8080";

const headers = {
    'Content-Type': 'application/json',
   
  }


export async function createOrder(data,callback, errorcallback) {
    await axios
      .post(`${baseURL}/evento/payment-gateway/create-order`,data,{
        headers: headers
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

 


export function addPaymentdetails(data,callback, errorcallback) {
    axios
      .put(`${baseURL}/evento/payment-gateway/payment-details`,data,{
        headers: headers
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

 