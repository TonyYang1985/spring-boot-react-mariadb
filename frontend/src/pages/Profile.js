import React from "react";
import AuthService from "../services/auth.service";

const Profile = () => {
  const currentUser = AuthService.getCurrentUser();
  const accessToken = AuthService.getCurrentUserToken();
  return (
    <div className="container">
      <header className="jumbotron">
        <h3>
          <strong>{currentUser.username}</strong> Profile
        </h3>
      <p>
        <strong>Id:</strong> {currentUser.id}
      </p>
      <p>
        <strong>username:</strong> {currentUser.username}
      </p>
      <p>
        <strong>Email:</strong> {currentUser.email}
      </p>
      <p>
        <strong>Role:</strong> {currentUser.email}
      </p>
      </header>
    </div>
  );
};

export default Profile;
