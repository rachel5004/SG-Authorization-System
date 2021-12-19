import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import UserList from "./UserList";

const Home = () => {
  const [content, setContent] = useState("");

  useEffect(() => {
    UserService.getAllUser().then(
      (response) => {
        setContent(response.data);
      },
      (error) => {
        const _content =
          (error.response && error.response.data) ||
          error.message ||
          error.toString();

        setContent(_content);
      }
    );
  }, []);
  console.log(content);

  return (
    <div className="container">
      <header className="jumbotron">
        {content && <UserList users={content} />}
        {/* <h3>{content[0]}</h3> */}
      </header>
    </div>
  );
};

export default Home;
