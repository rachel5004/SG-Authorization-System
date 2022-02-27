import React from "react";
const handleUserDetail = (id) => {
  console.log("click");
  //service.getUserDetail(id)
  //modal
};

function User({ user }) {
  return (
    <li onClick={handleUserDetail}>
      <b>{user.name}</b> <span>{user.email}</span>
    </li>
  );
}

function UserList({ users }) {
  console.log(users);
  return (
    <div>
      <ul>
        {users.map((user, index) => (
          <User user={user} key={index} />
        ))}
      </ul>
    </div>
  );
}
export default UserList;
