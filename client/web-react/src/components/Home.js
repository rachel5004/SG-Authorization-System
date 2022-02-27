import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import UserList from "./UserList";

const Home = () => {
  return (
    <div className="container">
      <header className="jumbotron">
        <h3>Please Login First</h3>
      </header>
    </div>
  );
};

export default Home;
