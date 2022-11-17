import * as React from "react";
import { IconButton, IconButtonProps, useDisclosure } from "@chakra-ui/react";
import { BsBucket } from "react-icons/bs";
import { CheckoutModal } from "../../modals";

type ColorModeSwitcherProps = Omit<IconButtonProps, "aria-label">;

export const CheckoutButton: React.FC<ColorModeSwitcherProps> = (props) => {
  const { isOpen, onOpen, onClose } = useDisclosure();

  return (
    <>
      <IconButton
        aria-label={""}
        size="md"
        fontSize="lg"
        variant="ghost"
        color="current"
        marginLeft="2"
        onClick={onOpen}
        icon={<BsBucket />}
        {...props}
      />
      <CheckoutModal isOpen={isOpen} onClose={onClose} />
    </>
  );
};
