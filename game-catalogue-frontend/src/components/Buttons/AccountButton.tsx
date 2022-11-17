import * as React from "react";
import {
  Button,
  IconButtonProps,
  Menu,
  MenuButton,
  MenuItem,
  MenuList,
} from "@chakra-ui/react";

import { FaRegUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useToken } from "../../hooks";

type SignupButtonProps = Omit<IconButtonProps, "aria-label">;

export const AccountButton: React.FC<SignupButtonProps> = () => {
  const { token, setToken } = useToken();
  const handleLogoutClick = () => {
    setToken("");
  };

  return (
    <>
      <Menu>
        {({ isOpen }) => (
          <>
            <MenuButton
              isActive={isOpen}
              as={Button}
              aria-label="sign-up-button"
              variant="ghost"
              color="current"
              marginLeft="2"
            >
              <FaRegUserCircle />
            </MenuButton>
            <MenuList>
              {!!token ? (
                <MenuItem fontSize="md" onClick={handleLogoutClick}>
                  Logout
                </MenuItem>
              ) : (
                <>
                  <Link to="login">
                    <MenuItem fontSize="md">Log in</MenuItem>
                  </Link>
                  <Link to="signup">
                    <MenuItem fontSize="md">Sign up</MenuItem>
                  </Link>
                </>
              )}
            </MenuList>
          </>
        )}
      </Menu>
    </>
  );
};
