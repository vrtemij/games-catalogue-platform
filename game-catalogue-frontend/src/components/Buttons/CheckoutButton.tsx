import * as React from "react";
import { IconButton, IconButtonProps, useDisclosure } from "@chakra-ui/react";
import { BsBucket } from "react-icons/bs";
import { CheckoutModal } from "../../modals";
import { useToken } from "../../hooks";
import { Link } from "react-router-dom";

type ColorModeSwitcherProps = Omit<IconButtonProps, "aria-label">;

export const CheckoutButton: React.FC<ColorModeSwitcherProps> = (props) => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const { token } = useToken();

  const handleCheckoutClick = () => {
    if (!!token) return;
    onOpen();
  };

  return (
    <>
      {!!token ? (
        <Link to="checkout">
          <IconButton
            aria-label={""}
            size="md"
            fontSize="lg"
            variant="ghost"
            color="current"
            marginLeft="2"
            onClick={handleCheckoutClick}
            icon={<BsBucket />}
            {...props}
          />
        </Link>
      ) : (
        <IconButton
          aria-label={""}
          size="md"
          fontSize="lg"
          variant="ghost"
          color="current"
          marginLeft="2"
          onClick={handleCheckoutClick}
          icon={<BsBucket />}
          {...props}
        />
      )}
      <CheckoutModal isOpen={isOpen} onClose={onClose} />
    </>
  );
};
