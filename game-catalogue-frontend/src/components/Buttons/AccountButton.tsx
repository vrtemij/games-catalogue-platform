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

type SignupButtonProps = Omit<IconButtonProps, "aria-label">;

export const AccountButton: React.FC<SignupButtonProps> = () => {
  const isAuthorized = false;
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
              {isAuthorized ? (
                <MenuItem fontSize="md" onClick={() => {}}>
                  Logout
                </MenuItem>
              ) : (
                <Link to="signup">
                  <MenuItem fontSize="md">Log in / Sign up</MenuItem>
                </Link>
              )}
            </MenuList>
          </>
        )}
      </Menu>
    </>
  );
};
