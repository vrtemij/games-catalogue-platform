import * as React from "react";
import { IconButton } from "@chakra-ui/react";
import { FaHome } from "react-icons/fa";
import { Link } from "react-router-dom";

export const HomeButton: React.FC = () => {
  return (
    <Link to="/">
      <IconButton
        aria-label="go-home-button"
        size="md"
        fontSize="lg"
        variant="ghost"
        color="current"
        marginLeft="2"
        icon={<FaHome />}
      />
    </Link>
  );
};
