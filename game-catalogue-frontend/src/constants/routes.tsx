import { App } from "../App";
import { Cart, Home, UserAuth } from "../pages";

export const ROUTES = [
  {
    path: "/",
    element: <App />,
    children: [
      {
        path: "/",
        element: <Home />,
      },
      {
        path: "/signup",
        element: <UserAuth signupForm />,
      },
      {
        path: "/login",
        element: <UserAuth />,
      },
      {
        path: "/checkout",
        element: <Cart />,
      },
    ],
  },
];
