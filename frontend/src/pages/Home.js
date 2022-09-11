import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import AuthService from "../services/auth.service";

const Home = () => {
  const [content, setContent] = useState("");
  const currentUser = AuthService.getCurrentUser();

  useEffect(() => {
    UserService.getPublicContent().then(
      (response) => {
        setContent(JSON.stringify(response.data.users));
      },
      (error) => {

        setContent(error);
      }
    );
  }, []);

  return (
    <div className="container">
      <header className="jumbotron">
        <h3>{content}</h3>
      </header>
    </div>
  );
};

export default Home;
