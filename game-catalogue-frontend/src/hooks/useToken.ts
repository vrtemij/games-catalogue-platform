import { useState } from "react";

export const useToken = () => {
  const getToken = () => sessionStorage.getItem("token");
  const [token, setToken] = useState(getToken());
  const saveToken = (userToken: string) => {
    sessionStorage.setItem("token", userToken);
    setToken(userToken);
  };

  return {
    setToken: saveToken,
    token,
  };
};
