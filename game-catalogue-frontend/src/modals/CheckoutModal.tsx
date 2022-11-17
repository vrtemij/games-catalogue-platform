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
          <Text fontSize="md" fontWeight="semibold" align="center">
            Looks like you don't have an account!
          </Text>
          <Text fontSize="md" fontWeight="semibold" align="center" mb={4}>
            Would you like to create a new one?
          </Text>
          <FormControl>
            <FormLabel>Username</FormLabel>
            <Input autoComplete="username" placeholder="John Doe" />
          </FormControl>
          <FormControl mt={4}>
            <FormLabel>Password</FormLabel>
            <Input
              autoComplete="new-password"
              type="password"
              placeholder="Secret"
            />
          </FormControl>
          <Flex justify="center" align="center" mt={4}>
            ...or
            <Link to="checkout" onClick={onClose}>
              <Button colorScheme="teal" ml={1}>
                Continue without registration
              </Button>
            </Link>
          </Flex>
        </ModalBody>
        <ModalFooter>
          <Button colorScheme="teal" mr={3}>
            Create
          </Button>
          <Button onClick={onClose}>Cancel</Button>
        </ModalFooter>
      </ModalContent>
    </Modal>
  );
};
