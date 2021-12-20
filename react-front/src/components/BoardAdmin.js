import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import EventBus from "../common/EventBus";
import UserList from "./UserList";

const BoardAdmin = () => {
  const [content, setContent] = useState("");
  const [loaded, setLoaded] = useState(false);

  useEffect(() => {
    UserService.getAllUser().then(
      (response) => {
        setContent(response.data);
        if (!content.includes("Network")) {
          setLoaded(true);
        }
      },
      (error) => {
        const _content =
          (error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString();

        setContent(_content);

        if (error.response && error.response.status === 401) {
          EventBus.dispatch("logout");
        }
      }
    );
  }, []);
  return (
    <div className="container">
      <header className="jumbotron">
        {loaded === true && <UserList users={content} />}
        {/* <h3>{content[0]}</h3> */}
      </header>
    </div>
  );
};

export default BoardAdmin;
