import axios from "axios";

export function getUserLocation(position, callback, errorcallback) {
    const { latitude, longitude } = position.coords;

    axios
      .get(`https://api.opencagedata.com/geocode/v1/json?q=${latitude}+${longitude}&key=bc1c0fb4167d48ffa28d8400635e320f`)
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
