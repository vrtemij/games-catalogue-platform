import React, { FC, useEffect, useState } from "react";
import {
  Button,
  Flex,
  FormControl,
  FormLabel,
  Input,
  Spinner,
  Text,
} from "@chakra-ui/react";
import { Form, useNavigate } from "react-router-dom";
import { loginUser, registerUser } from "../services/";
import { UserAuthFormData } from "../interfaces";
import { HTTP_STATUSES } from "../constants";
import { useToken } from "../hooks";

interface Props {
  signupForm?: boolean;
}

export const UserAuth: FC<Props> = ({ signupForm = false }) => {
  const [isLoading, setIsLoading] = useState(false);
  const [formData, setFormData] = useState<UserAuthFormData>({
    username: "",
    password: "",
  });
  const { setToken } = useToken();

  const navigate = useNavigate();
  const submitAction = signupForm ? registerUser : loginUser;

  const handleSubmit = (event: any) => {
    setIsLoading(true);
    event.preventDefault();

    if (!formData.username || !formData.password) return;

    submitAction(formData)
      .then((response) => {
        if (response.status === HTTP_STATUSES.OK) {
          if (response?.data?.token) {
            setToken(response.data.token);
          }
          navigate("/");
        }
      })
      .catch(() => {})
      .finally(() => {
        setIsLoading(false);
      });
  };

  useEffect(() => {
    setIsLoading(false);
    setFormData({
      username: "",
      password: "",
    });
  }, [signupForm]);

  return (
    <>
      {isLoading && (
        <Spinner position="absolute" top="50%" left="calc(50% - 10px)" />
      )}
      <Flex align="center" justify="center" opacity={isLoading ? 0.5 : 1}>
        <Form onSubmit={handleSubmit}>
          <Text align="center" fontWeight="bold" fontSize="xl" w="280px" mb={2}>
            {signupForm
              ? "Create new account"
              : "Sign in to an existing account"}
          </Text>
          <FormControl>
            <FormLabel>Username</FormLabel>
            <Input
              value={formData.username}
              onChange={(event) => {
                setFormData({
                  ...formData,
                  username: event.currentTarget.value,
                });
              }}
              autoComplete="username"
              placeholder="John Doe"
            />
          </FormControl>
          <FormControl mt={4}>
            <FormLabel>Password</FormLabel>
            <Input
              value={formData.password}
              onChange={(event) => {
                setFormData({
                  ...formData,
                  password: event.currentTarget.value,
                });
              }}
              autoComplete="new-password"
              type="password"
              placeholder="Secret"
            />
          </FormControl>
          <Button
            disabled={isLoading}
            type="submit"
            colorScheme="blue"
            w="100%"
            mt={4}
          >
            {signupForm ? "Sign up" : "Sign in"}
          </Button>
        </Form>
      </Flex>
    </>
  );
};
