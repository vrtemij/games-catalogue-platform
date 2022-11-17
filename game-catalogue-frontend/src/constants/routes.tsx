import { App } from "../App";
import { Cart, Home, Signup } from "../pages";

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
        element: <Signup />,
      },
      {
        path: "/checkout",
        element: <Cart />,
      },
    ],
  },
];
