import React, { FC } from "react";
import {
  Text,
  Button,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
} from "@chakra-ui/react";
import { UseDisclosureProps } from "@chakra-ui/hooks";
import { Link } from "react-router-dom";

type Props = Pick<UseDisclosureProps, "isOpen" | "onClose">;

export const CheckoutModal: FC<Props> = ({
  isOpen = false,
  onClose = () => {},
}) => {
  return (
    <Modal isOpen={isOpen} onClose={onClose}>
      <ModalOverlay />
      <ModalContent>
        <ModalHeader>Checkout</ModalHeader>
        <ModalCloseButton />
        <ModalBody pb={6}>
          <Text align="center">
            You can continue checkout with your account
          </Text>
          <Flex justify="center" align="center" mt={2}>
            <Link to="signup" onClick={onClose}>
              <Button colorScheme="teal" mr={1}>
                Sing up
              </Button>
            </Link>
            /
            <Link to="login" onClick={onClose}>
              <Button colorScheme="teal" ml={1}>
                Sign in
              </Button>
            </Link>
          </Flex>
          <Text align="center">Or</Text>
          <Flex justify="center" align="center">
            <Link to="checkout" onClick={onClose}>
              <Button colorScheme="teal">Continue without registration</Button>
            </Link>
          </Flex>
        </ModalBody>
      </ModalContent>
    </Modal>
  );
};
